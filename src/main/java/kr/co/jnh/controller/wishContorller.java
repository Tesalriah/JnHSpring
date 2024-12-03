package kr.co.jnh.controller;

import kr.co.jnh.domain.Wish;
import kr.co.jnh.service.WishService;
import kr.co.jnh.service.WishServiceImpl;
import kr.co.jnh.util.SessionIdUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/mypage/wish")
public class wishContorller {

    @Autowired
    WishService wishService;

    @PostMapping("add")
    @ResponseBody
    public Map<String,Object> addWish(@RequestBody Map<String, Object> wishMap , HttpServletRequest request){
        Map<String, Object> map = new HashMap<>();
        String id = SessionIdUtil.getSessionId(request);
        if(id == null || id.equals("")){ // 만약 로그인 정보가 명확하지 않을경우 알림
            map.put("msg", "로그인을 해주세요.");
            return map;
        }
        Wish wish = new Wish();
        wish.setUser_id(id);
        wish.setProduct_id((String)wishMap.get("product_id"));

        try {
            if(wishService.readOne(wish) == 0){

            }else{

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }
}
