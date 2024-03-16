package kr.co.jnh.interceptor;

import kr.co.jnh.dao.UserDao;
import kr.co.jnh.domain.User;
import kr.co.jnh.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class AdminInterceptor implements HandlerInterceptor {

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
        if( id == null || id == ""){
            return true;
        }
        try {
            // 인터셉터 전처리, 로그인 했을 시 유저등급을 파라미터에 저장
            Integer grade  = userService.getGrade(id);
            if(grade != null){
                request.setAttribute("grade", grade);
            }

        } catch (Exception e) {
            e.printStackTrace();
            // 이상 있을시 로그아웃
            session.invalidate();
        }

        return true;
    }
}
