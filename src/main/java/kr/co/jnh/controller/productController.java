package kr.co.jnh.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class productController {

    @GetMapping("addProduct")
    public String addProduct(){
        return "addProduct";
    }

    @PostMapping("/upload")
    public String addProduct(HttpServletRequest request){

        return "";
    }
}
