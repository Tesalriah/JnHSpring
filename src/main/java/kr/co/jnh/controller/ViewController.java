package kr.co.jnh.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ViewController {

    @GetMapping("about-as")
    public String abountAs(){
        return "/about-as";
    }

    @GetMapping("group-buy")
    public String groupBuy(){
        return "/group-buy";
    }
}
