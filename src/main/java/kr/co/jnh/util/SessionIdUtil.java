package kr.co.jnh.util;

import kr.co.jnh.domain.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class SessionIdUtil {
    public static String getSessionId(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if(session != null){
            User user = (User)session.getAttribute("user");
            if(user != null){
                String id = user.getUser_id();
                if(id != null && !id.equals("")){
                    return id;
                }
            }
        }
        return null;
    }
}
