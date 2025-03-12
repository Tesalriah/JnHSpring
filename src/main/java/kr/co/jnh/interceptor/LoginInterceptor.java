package kr.co.jnh.interceptor;

import kr.co.jnh.domain.User;
import kr.co.jnh.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Component
public class LoginInterceptor implements HandlerInterceptor {
    @Autowired
    UserService userService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception{
        String id = null;

        HttpSession session = request.getSession(false);
        if(session != null){
            User user = (User)session.getAttribute("user");
            if(user != null){
                id = user.getUser_id();
            }
            String requestURI = request.getRequestURI(); // 요청 URI 가져오기
            String method = request.getMethod();
            if(requestURI.equals("/jnh/product") && "GET".equalsIgnoreCase(method)){
                return true;
            }

            String prevPage = null;

            if(id == null || id.isEmpty()){
                if("POST".equalsIgnoreCase(method)){
                    prevPage = request.getHeader("referer");
                    session.setAttribute("prevPage", prevPage);
                }if("GET".equalsIgnoreCase(method)){
                    prevPage = requestURI;
                    prevPage = prevPage.replace("/jnh", "");
                    session.setAttribute("prevPage", prevPage);
                }
                response.sendRedirect("/jnh/login" + "?msg=NEED_LOGIN");
                return false;
            }
        }
        return true;
    }
}
