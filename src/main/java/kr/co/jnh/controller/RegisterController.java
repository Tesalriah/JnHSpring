package kr.co.jnh.controller;

import kr.co.jnh.dao.UserDao;
import kr.co.jnh.domain.User;
import kr.co.jnh.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class RegisterController {

    @Inject
    EmailService emailService;
    @Autowired
    UserDao userDao;

    @GetMapping("/register")
    public String register(){
        return "terms";
    }

    @GetMapping("/signUp")
    public String signUp(){
        return "signUp";
    }

    @GetMapping("/asd")
    public String asd(Model model){
        try {
            emailService.sendMail(); // dto (메일관련 정보)를 sendMail에 저장함
            model.addAttribute("message", "이메일이 발송되었습니다."); // 이메일이 발송되었다는 메시지를 출력시킨다.

        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("message", "이메일 발송 실패..."); // 이메일 발송이 실패되었다는 메시지를 출력
        }
        return "asd";
    }

    @PostMapping("/signUp")
    public String signUp(User user, HttpServletRequest request, Model m, HttpSession session){
        String address = request.getParameter("address1") + request.getParameter("address2");
        user.setAddress(address);
        System.out.println("user.toString() = " + user.toString());

        try {
            if(userDao.insert(user) == 1){
                session.setAttribute("id", user.getUser_id());
            }
            return "redirect:/";
        } catch (Exception e) {
            e.printStackTrace();
            if(user.getBirth()!= null){
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                m.addAttribute("birth", formatter.format(user.getBirth()));
            }
            m.addAttribute("msg", "REG_ERR");
            m.addAttribute("user", user);
            m.addAttribute("address1", request.getParameter("address1"));
            m.addAttribute("address2", request.getParameter("address2"));

            return "/signUp";
        }
    }

}
