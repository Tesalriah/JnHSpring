package kr.co.jnh.interceptor;

import kr.co.jnh.dao.UserDao;
import kr.co.jnh.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class AdminInterceptor implements HandlerInterceptor {

    @Autowired
    UserDao userDao;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
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
        // 인터셉터 전처리, 로그인 했을 시 유저등급을 파라미터에 저장
        int grade  = userDao.selectUserGrade(id);
        request.setAttribute("grade", grade);

        return true;
    }
}
