package kr.co.jnh.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class SessionIdUtil {
    public static String getSessionId(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        String id = (String)session.getAttribute("id");
        if(session != null || id != null || !id.equals("")){
            return id;
        }
        return null;
    }
}
