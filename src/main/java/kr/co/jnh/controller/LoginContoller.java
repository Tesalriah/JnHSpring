package kr.co.jnh.controller;

import kr.co.jnh.dao.UserDao;
import kr.co.jnh.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Controller
public class LoginContoller {

    @Autowired
    UserDao userDao;

    @GetMapping("/login")
    public String LoginForm(){return "login";}

    @PostMapping("/login")
    public String Login(String id, String pwd, String toURL, boolean rememberId,  HttpServletRequest request){
        Map map = new HashMap();
        map.put("id",id);
        map.put("pwd",pwd);

        try {
            User user = userDao.selectUser(map);

            System.out.println("user.toString() = " + user.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }

        HttpSession session = request.getSession();
        session.setAttribute("id",id);

        return "main";
    }
}
