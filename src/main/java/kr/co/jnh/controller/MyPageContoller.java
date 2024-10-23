package kr.co.jnh.controller;

import kr.co.jnh.util.SessionIdUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("myPage")
public class MyPageContoller {


    @GetMapping("orderList")
    public String mypage(HttpServletRequest request, RedirectAttributes rattr){
        String id = SessionIdUtil.getSessionId(request, rattr);
        if(id == null || id.equals("")){
            return "redirect:login";
        }
        return "myPage/orderList";
    }
}
