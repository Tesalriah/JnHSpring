package kr.co.jnh.controller;


import com.mysql.cj.Session;
import kr.co.jnh.domain.User;
import kr.co.jnh.service.UserService;
import kr.co.jnh.util.SessionIdUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("mypage/user")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping
    public String user(HttpServletRequest request, Model m) {
        String id = SessionIdUtil.getSessionId(request);
        m.addAttribute("id", id);
        return "mypage/check-pwd";
    }

    @PostMapping("check-pwd")
    public String checkPwd(@RequestParam String user_pwd, HttpServletRequest request, Model m) {
        String id = SessionIdUtil.getSessionId(request);

        Map map = new HashMap();
        map.put("id", id);
        map.put("pwd", user_pwd);
        try {
            if (!userService.loginCheck(map)) {
                throw new Exception("DOES_NOT_MATCH");
            }
            HttpSession session = request.getSession();
            session.setAttribute("passwordVerified", true);
            return "redirect:info";
        } catch (Exception e) {
            e.printStackTrace();
            m.addAttribute("msg", "비밀번호가 일치하지 않습니다.");
            m.addAttribute("url", "/jnh/mypage/user");
            return "alert";
        }
    }

    @GetMapping("info")
    public String userinfo(HttpServletRequest request, Model m){
        String id = SessionIdUtil.getSessionId(request);
        HttpSession session = request.getSession(false);
        try {
            if(session == null || !(boolean)session.getAttribute("passwordVerified")){
                throw new Exception("WRONG_APPROACH");
            }
            User user = userService.getUser(id);
            if(user == null){
                throw new Exception("WRONG_APPROACH");
            }
            session.removeAttribute("passwordVerified");
            m.addAttribute("user", user);
            return "mypage/user-info";
        } catch (Exception e) {
            e.printStackTrace();
            m.addAttribute("msg", "잘못된 접근입니다.");
            m.addAttribute("url", "/jnh");
            return "alert";
        }
    }

    @PostMapping("change-address")
    public String changeAddress(@RequestParam String address,@RequestParam(required = false) String address2, HttpServletRequest request, Model m){
        HttpSession session = request.getSession(false);

        String id = SessionIdUtil.getSessionId(request);
        String c_address = address;
        if(!address2.equals("")){
            c_address += address2;
        }
        try{
            session.setAttribute("passwordVerified", true);
            if(userService.changeAddress(id, c_address) != 1){
                throw new Exception("ADDRESS_CHANGE_FAIL");
            }
            m.addAttribute("msg", "주소가 변경되었습니다.");
        }catch (Exception e){
            e.printStackTrace();
            m.addAttribute("msg", "변경에 실패했습니다.");
        }
        m.addAttribute("url", "info");
        return "alert";
    }

    @PostMapping("change-pwd")
    public String changePwd(String user_pwd, String new_pwd, String new_pwd_check, HttpServletRequest request, Model m){
        HttpSession session = request.getSession(false); // 여기해야함~~~~~~~~
        String id = SessionIdUtil.getSessionId(request);
        Map map = new HashMap();
        map.put("id", id);
        map.put("pwd", user_pwd);

        try{
            if(!userService.loginCheck(map)){
                m.addAttribute("비밀번호가 일치하지 않습니다.");
                throw new Exception("DOES_NOT_MATCH");
            }
        }catch (Exception e){
            e.printStackTrace();
            m.addAttribute("url","info");
        }

        return "";
    }
}
