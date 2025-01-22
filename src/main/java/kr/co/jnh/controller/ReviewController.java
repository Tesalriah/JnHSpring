package kr.co.jnh.controller;

import kr.co.jnh.domain.PageHandler;
import kr.co.jnh.domain.Review;
import kr.co.jnh.domain.SearchCondition;
import kr.co.jnh.service.ReviewService;
import kr.co.jnh.util.CacheControlUtil;
import kr.co.jnh.util.FileMultiSaveUtil;
import kr.co.jnh.util.SessionIdUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
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

    @GetMapping("write")
    public String writeReview(@RequestParam(required = false) Integer rno, HttpServletRequest request, Model m){
        String id = SessionIdUtil.getSessionId(request);

        try{
            if(rno == null){
                throw new Exception("WRONG_APPROACH");
            }
            Review review = reviewService.selectOne(rno);
            if(review == null){
                throw new Exception("FAIL");
            }
            if(!id.equals(review.getUser_id())){
                throw new Exception("WRONG_APPROACH");
            }
            if(review.getWhether() == 2){
                throw new Exception("ALREADY_REMOVED");
            }
            m.addAttribute("review", review);
            return "mypage/review-write";
        }catch (Exception e){
            e.printStackTrace();
            if(e.getMessage().equals("WRONG_APPROACH")){
                m.addAttribute("msg", "잘못된 접근입니다.");
                m.addAttribute("url", "/jnh");
            }else if(e.getMessage().equals("ALREADY_REMOVED")){
                m.addAttribute("msg", "삭제된 리뷰입니다.");
                m.addAttribute("url", "able");
            }
            else {
                m.addAttribute("msg", "페이지 접근에 실패했습니다. 지속될 경우 고객센터에 문의해주세요.");
                m.addAttribute("url", "able");
            }
            return "alert";
        }
    }

    @PostMapping("write")
    public String writeReview(Review review, HttpServletRequest request, HttpServletResponse response, @RequestParam(value = "uploadFile", required = false) MultipartFile file, Model m){
        CacheControlUtil.setNoCacheHeaders(response);
        String id = SessionIdUtil.getSessionId(request);
        String not_change = request.getParameter("not_change");

        try{
            String rId = reviewService.selectOne(review.getRno()).getUser_id();
            // 리뷰 작성권한이 없는 아이디는 접근못하게 차단
            if(!id.equals(rId)){
                throw new Exception("WRONG_APPROACH");
            }
            // 사진 변경 요청이없을시 이미지 파일 새로 저장
            if(not_change == null){
                // 업로드 할 이미지가 있을시에만 처리
                if(!file.isEmpty()){
                    // 이미지를 경로에 저장하고 생성하여 저장된 파일이름을 반환하는 메서드
                    String filename = FileMultiSaveUtil.uploadImg(file, request, "review-img", review.getRno() + "");
                    review.setImage(filename);
                }
            }
            review.setUser_id(id);
            review.setReg_date(new Date());
            review.setWhether(1);

            if(reviewService.update(review) != 1){
                throw new Exception("REVIEW_WRITE_FAIL");
            }

            m.addAttribute("msg", "등록되었습니다.");
            m.addAttribute("url", "wrote");
        }catch (Exception e){
            e.printStackTrace();
            if(e.getMessage().equals("WRONG_APPOACH")) {
                m.addAttribute("msg", "잘못된 접근입니다.");
            }else{
                m.addAttribute("msg", "작성에 실패했습니다.");
            }
            m.addAttribute("url", "list");
        }

        return "alert";
    }

    @GetMapping("wrote")
    public String wroteReview(SearchCondition sc, HttpServletRequest request, HttpServletResponse response, Model m){
        String id = SessionIdUtil.getSessionId(request);

        Map<String, Object> map = new HashMap<>();
        map.put("id", id);
        map.put("whether", 1);
        map.put("sc", sc);

        try{
            int totalCnt = reviewService.selectPageCnt(map);
            List<Review> list = reviewService.selectPage(map);
            PageHandler ph = new PageHandler(totalCnt, sc);

            m.addAttribute("list", list);
            m.addAttribute("ph", ph);
        }catch (Exception e){
            e.printStackTrace();
        }
        return "mypage/review-wrote";
    }

    @GetMapping("modify")
    public String modifyReview(@RequestParam(required = false) Integer rno, HttpServletRequest request, Model m){
        String id = SessionIdUtil.getSessionId(request);

        try{
            Review review =  reviewService.selectOne(rno);
            if(!review.getUser_id().equals(id)){
                throw new Exception("LONG_APPROACH");
            }
            m.addAttribute("review", review);
            m.addAttribute("modify", "modify");

            return "mypage/review-write";
        }catch (Exception e){
            e.printStackTrace();
            if(e.getMessage().equals("LONG_APPROACH")){
                m.addAttribute("msg","잘못된 접근입니다.");
                m.addAttribute("url", "/jnh");
            }else{
                m.addAttribute("msg","페이지 접근에 실패했습니다. 지속될 경우 고객센터에 문의해주세요.");
                m.addAttribute("url", "wrote");
            }

            return "alert";
        }
    }

    @PostMapping("remove")
    public String removeReview(@RequestParam(required = false) Integer rno, HttpServletRequest request, Model m){
        String id = SessionIdUtil.getSessionId(request);

        try{
            if(rno == null){
                throw new Exception("WRONG_APPROACH");
            }
            Review review = reviewService.selectOne(rno);
            if(review == null){
                throw new Exception("FAIL");
            }
            if(!id.equals(review.getUser_id())){
                throw new Exception("WRONG_APPROACH");
            }
            if(review.getWhether() != 0){
                throw new Exception("ALREADY_WRITTEN");
            }
            m.addAttribute("review", review);
            return "mypage/review-write";
        }catch (Exception e){
            e.printStackTrace();
            if(e.getMessage().equals("WRONG_APPROACH")){
                m.addAttribute("msg", "잘못된 접근입니다.");
                m.addAttribute("url", "/jnh");
            }
            else {
                m.addAttribute("msg", "페이지 접근에 실패했습니다. 지속될 경우 고객센터에 문의해주세요.");
                m.addAttribute("url", "able");
            }
            return "alert";
        }

    }
}
