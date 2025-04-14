package kr.co.jnh.interceptor;

import kr.co.jnh.domain.User;
import kr.co.jnh.service.UserService;
import kr.co.jnh.util.SessionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Component
public class AdminInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception{
        HttpSession session = request.getSession(false);
        String id = SessionUtils.getSessionId(request);

        if (session != null && id != null && !id.equals("")) {
            User user = (User) session.getAttribute("user");
            Integer grade = user.getGrade();

            if (grade != null && grade == 0) {
                return true;
            }
        }

        // 관리자가 아닌경우 alert 메세지를 띄운 후 메인페이지로
        String redirectUrl = "/jnh"; // 이동할 URL
        request.setAttribute("msg", "잘못된 접근입니다.");
        request.setAttribute("url", redirectUrl);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/alert.jsp");
        dispatcher.forward(request, response);
        return false; // 요청 진행 중단
    }
}
