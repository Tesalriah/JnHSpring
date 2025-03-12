package kr.co.jnh.interceptor;

import kr.co.jnh.domain.User;
import kr.co.jnh.service.UserService;
import kr.co.jnh.util.SessionIdUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Component
public class GradeInterceptor implements HandlerInterceptor {

    @Autowired
    UserService userService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler){
        HttpSession session = request.getSession(false);
        String id = SessionIdUtil.getSessionId(request);

        if(session != null && id != null && !id.isEmpty()){
            // 인터셉터 전처리, 로그인 했을 시 유저등급을 파라미터에 저장
            User user = (User)session.getAttribute("user");
            Integer grade = user.getGrade();
            if(grade != null){
                request.setAttribute("grade", grade);
            }
        }
        return true;
    }
}
