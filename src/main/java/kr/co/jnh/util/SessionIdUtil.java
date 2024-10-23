package kr.co.jnh.util;

import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class SessionIdUtil {
    public static String getSessionId(HttpServletRequest request, RedirectAttributes rattr){
        HttpSession session = request.getSession();
        String id = (String)session.getAttribute("id");
        rattr.addFlashAttribute("msg", "NEED_LOGIN");

        return id;
    }
}
