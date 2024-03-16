package kr.co.jnh.interceptor;

import kr.co.jnh.dao.UserDao;
import kr.co.jnh.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class StatusInterceptor implements HandlerInterceptor {
    @Autowired
    UserService userService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler){
        String id = null;

        HttpSession session = request.getSession(false);
        if(session != null){
            id = (String)session.getAttribute("id");
        }else{
            return true;
        }
        if( id == null || id.equals("") ){
            return true;
        }
        try {
            // 전처리 로그인 돼있을시 스테이터스 확인
            Integer status  = userService.getStatus(id);
            if(status != null){
                if(status != 0){
                    // 정지된 유저 로그아웃
                    if(status == 1){
                        session.invalidate();
                        response.sendRedirect("/jnh");
                    }
                    // 회원탈퇴된 유저 로그아웃
                    if(status == 2){
                        session.invalidate();
                        response.sendRedirect("/jnh");
                    }
                    // 이메일 미인증 유저 이메일 인증으로
                    if(status == 3){
                        response.sendRedirect("/jnh/emailAuth");
                    }
                    return true;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            // 이상 있을시 로그아웃
            session.invalidate();
        }

        return true;
    }
}
