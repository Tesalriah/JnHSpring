package kr.co.jnh.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("myPage")
public class MyPageContoller {


    @GetMapping("orderList")
    public String mypage(){
        return "myPage/orderList";
    }
}
