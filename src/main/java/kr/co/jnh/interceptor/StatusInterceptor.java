package kr.co.jnh.interceptor;

import kr.co.jnh.dao.UserDao;
import kr.co.jnh.domain.User;
import kr.co.jnh.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Component
public class StatusInterceptor implements HandlerInterceptor {
    @Autowired
    UserService userService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception{
        HttpSession session = request.getSession(false);
        if(session != null){
            User user = (User)session.getAttribute("user");
            if(user != null){
                String id = user.getUser_id();
                // 전처리 로그인 돼있을시 스테이터스 확인
                if(session != null || id != null || !id.equals("")){
                    Integer status = user.getStatus();

                    if (status != null) {
                        if (status != 0) {
                            // 정지된 유저 로그아웃, 회원탈퇴된 유저 로그아웃
                            if (status == 1 || status == 2) {
                                session.invalidate();
                                response.sendRedirect("/jnh");
                            }
                            // 이메일 미인증 유저 이메일 인증으로
                            if (status == 3) {
                                response.sendRedirect("/jnh/email-auth");
                            }
                        }
                    }
                }
            }
        }
        return true;
    }
}
