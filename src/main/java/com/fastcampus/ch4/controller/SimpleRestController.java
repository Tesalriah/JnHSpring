package com.fastcampus.ch4.controller;

import com.fastcampus.ch4.domain.*;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Controller
public class SimpleRestController {
    @GetMapping("/ajax")
    public String ajax() {
        return "ajax";
    }

    @GetMapping("/boardList")
    public String boardList(){ return "boardList";}

    @GetMapping("/test")
    public String test() {
        return "test";
    }

    @PostMapping("/send")
    @ResponseBody
    public Person test(@RequestBody Person p) {
        System.out.println("p = " + p);
        p.setName("ABC");
        p.setAge(p.getAge() + 10);

        return p;
    }

    @PostMapping("/postAgeByName")
    /* inputMap 파라미터를 받아, 미리 저장된 ageMap에서 해당 이름에 맵핑된 나이를 리턴해주는 메소드 */
    public Map<String,Object> postAgeByName(@RequestBody Map<String,Object> inputMap ) {
        Map<String, Integer> ageMap = new HashMap<>();
        ageMap.put("tom", 10); ageMap.put("bob", 20); ageMap.put("kim", 30);

        Map<String,Object> returnMap = new HashMap<>();
        returnMap.put("name", inputMap.get("name"));
        returnMap.put("age", ageMap.get(inputMap.get("name")));

        return returnMap;
    }
}