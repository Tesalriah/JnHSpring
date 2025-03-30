package kr.co.jnh.util;

import kr.co.jnh.domain.User;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Objects;

public class SessionUtils {
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

    public static void addAttribute(String name, Object value) {
        Objects.requireNonNull(RequestContextHolder.getRequestAttributes()).setAttribute(name, value, RequestAttributes.SCOPE_SESSION);
    }

    public static String getStringAttributeValue(String name) {
        return String.valueOf(getAttribute(name));
    }

    public static Object getAttribute(String name) {
        return Objects.requireNonNull(RequestContextHolder.getRequestAttributes()).getAttribute(name, RequestAttributes.SCOPE_SESSION);
    }
}
