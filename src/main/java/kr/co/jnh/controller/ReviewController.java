package kr.co.jnh.controller;

import kr.co.jnh.dao.ReviewDao;
import kr.co.jnh.domain.PageHandler;
import kr.co.jnh.domain.Review;
import kr.co.jnh.domain.SearchCondition;
import kr.co.jnh.service.ReviewService;
import kr.co.jnh.util.SessionIdUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/mypage/review")
public class ReviewController {
    @Autowired
    ReviewService reviewService;

    @GetMapping("able")
    public String reviewAble(SearchCondition sc, HttpServletRequest request, Model m){
        String id = SessionIdUtil.getSessionId(request);

        try {
            Map<String, Object> map = new HashMap<>();
            map.put("id", id);
            map.put("whether", 0);
            map.put("sc", sc);

            int totalCnt = reviewService.selectPageCnt(map);
            List<Review> list = reviewService.selectPage(map);
            PageHandler ph = new PageHandler(totalCnt, sc);

            m.addAttribute("totalCnt", totalCnt);
            m.addAttribute("list", list);
            m.addAttribute("ph", ph);
        }catch (Exception e){
            e.printStackTrace();
        }
        return "mypage/review-able";
    }
}
