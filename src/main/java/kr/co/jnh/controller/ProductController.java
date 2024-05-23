package kr.co.jnh.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ProductController {

    @GetMapping("addProduct")
    public String getAddProduct(){return "addProduct";}

    @PostMapping("/addProduct")
    public String addProduct(){

        return "main";
    }
}
