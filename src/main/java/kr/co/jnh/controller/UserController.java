package kr.co.jnh.controller;


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
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("mypage/user")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping
    public String user(HttpServletRequest request, Model m){
        String id = SessionIdUtil.getSessionId(request);
        m.addAttribute("id", id);
        return "mypage/check-pwd";
    }

    @PostMapping("check-pwd")
    public String checkPwd(@RequestParam String user_pwd, HttpServletRequest request, Model m){
        String id = SessionIdUtil.getSessionId(request);

        Map map = new HashMap();
        map.put("id" ,id);
        map.put("pwd", user_pwd);
        try{
            User user = userService.showUser(map);
            if(!loginCheck(user, map)){
                return "redirect:jnh/mypage/user";
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    private boolean loginCheck(User user, Map map) {
        return user!=null && user.getUser_pwd().equals(map.get("pwd"));
    }
}
