package kr.co.jnh.interceptor;

import kr.co.jnh.service.UserService;
import kr.co.jnh.util.SessionIdUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Component
public class AdminInterceptor implements HandlerInterceptor {

    @Autowired
    UserService userService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception{
        HttpSession session = request.getSession(false);
        String id = SessionIdUtil.getSessionId(request);

        if (session != null && id != null && !id.equals("")) {
            Integer grade = (Integer) session.getAttribute("grade");

            if (grade != null && grade == 0) {
                return true;
            }
        }

        // 관리자가 아닌 경우 이전 페이지로 리다이렉트
        String referer = request.getHeader("referer");
        if(referer == null || referer.equals("")){
            response.sendRedirect("/jnh?msg=WRONG_APPROACH");
        }else{
            response.sendRedirect(referer);
        }
        return false;
    }
}
