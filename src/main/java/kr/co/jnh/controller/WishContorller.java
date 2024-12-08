package kr.co.jnh.controller;

import kr.co.jnh.domain.PageHandler;
import kr.co.jnh.domain.SearchCondition;
import kr.co.jnh.domain.Wish;
import kr.co.jnh.service.WishService;
import kr.co.jnh.util.SessionIdUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/mypage/wish")
public class WishContorller {

    @Autowired
    WishService wishService;

    @GetMapping("list")
    public String list(SearchCondition sc, HttpServletRequest request, Model m){
        Map map = new HashMap();
        String id = SessionIdUtil.getSessionId(request);
        map.put("id", id);
        map.put("sc", sc);
        sc.setPageSize(5);
        try{
            int totalCnt = wishService.totalCnt(id);
            List<Wish> list =  wishService.read(map);
            PageHandler ph = new PageHandler(totalCnt, sc);
            ph.setNaviSize(5);

            m.addAttribute("totalCnt", totalCnt);
            m.addAttribute("list", list);
            m.addAttribute("ph", ph);
        }catch (Exception e){
            e.printStackTrace();
        }

        return "mypage/wish-list";
    }

    @PostMapping("remove")
    public String remove( SearchCondition sc, HttpServletRequest request, Model m){
        String id = SessionIdUtil.getSessionId(request);
        String product_id = request.getParameter("product_id");
        Wish wish = new Wish(id, product_id);

        try {
            if(wishService.remove(wish) >= 1){
                m.addAttribute("msg", "삭제되었습니다.");
            }else {
                throw new Exception("WISH_REMOVE_FAIL");
            }
        }catch (Exception e){
            e.printStackTrace();
            m.addAttribute("msg", "삭제에 실패했습니다.");
        }
        m.addAttribute("url", "list?page="+sc.getPage()+"&pageSize="+sc.getPageSize());
        return "alert";
    }

    @PostMapping("remove-all")
    public String removeAll(@RequestParam(required = false) String check_box,@RequestParam(required = false) String product_id, HttpServletRequest request, Model m){
        if(check_box == null){
            m.addAttribute("msg","선택된 상품이 없습니다.");
            m.addAttribute("url", "list");
            return "alert";
        }if(product_id == null){
            m.addAttribute("msg","상품이 없습니다.");
            m.addAttribute("url", "list");
            return "alert";
        }
        String id = SessionIdUtil.getSessionId(request);
        String[] checkBox = check_box.split(",");
        String[] productId = product_id.split(",");

        Wish wish = new Wish();
        wish.setUser_id(id);

        try {
            for (int i = 0; i < checkBox.length; i++) {
                wish.setProduct_id(productId[Integer.parseInt(checkBox[i])]);
                if(wishService.remove(wish) < 1){
                    throw new Exception("WISH_REMOVE-ALL_FAIL");
                }
            }
            m.addAttribute("msg", "삭제되었습니다.");
            m.addAttribute("url", "list");
        }catch (Exception e){
            e.printStackTrace();
            m.addAttribute("msg", "삭제에 실패했습니다.");
            m.addAttribute("url", "list");
        }
        return "alert";
    }

    @PostMapping("add")
    @ResponseBody
    public Map<String,Object> addWish(@RequestBody Map<String, Object> wishMap , HttpServletRequest request){
        Map<String, Object> map = new HashMap<>();
        String id = SessionIdUtil.getSessionId(request);
        if(id == null || id.equals("")){ // 만약 로그인 정보가 명확하지 않을경우 알림
            map.put("msg", "로그인을 해주세요.");
            map.put("result", "fail");
            return map;
        }
        Wish wish = new Wish();
        wish.setUser_id(id);
        wish.setProduct_id((String)wishMap.get("product_id"));


        try {
            if(wishService.isThere(wish)){
                map.put("msg", "이미 추가한 상품입니다.");
            }else{
                wishService.write(wish);
                map.put("msg", "찜목록에 추가되었습니다.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }

    @PostMapping("del")
    @ResponseBody
    public Map<String,Object> delWish(@RequestBody Map<String, Object> wishMap , HttpServletRequest request){
        Map<String, Object> map = new HashMap<>();
        String id = SessionIdUtil.getSessionId(request);
        if(id == null || id.equals("")){ // 만약 로그인 정보가 명확하지 않을경우 알림
            map.put("msg", "로그인을 확인해주세요.");
            map.put("result", "fail");
            return map;
        }
        Wish wish = new Wish();
        wish.setUser_id(id);
        wish.setProduct_id((String)wishMap.get("product_id"));

        try {
            if(wishService.remove(wish) == 1){
                map.put("msg", "삭제되었습니다.");
            }else{
                throw new Exception("WISH_DEL_FAIL");
            }
        } catch (Exception e) {
            e.printStackTrace();
            map.put("msg", "삭제에 실패했습니다.");
        }
        return map;
    }
}
