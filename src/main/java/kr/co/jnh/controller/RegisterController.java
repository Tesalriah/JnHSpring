package kr.co.jnh.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RegisterController {

    @GetMapping("/register")
    public String register(){
        return "terms";
    }

    @GetMapping("/signUp")
    public String signUp(){
        return "signUp";
    }

    @GetMapping("/jusoPopup")
    public String jusoPopup(){
        return "jusoPopup";
    }
}
