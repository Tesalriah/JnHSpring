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
@RequestMapping("mypage/review")
public class ReviewController {
    @Autowired
    private ReviewService reviewService;

    @GetMapping("able") // 리뷰 작성가능 목록 리스트
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

    @GetMapping("write") // 작성가능한 리뷰를 작성할수있게 가져오는 메서드
    public String writeReview(@RequestParam(required = false) Integer rno, HttpServletRequest request, Model m){
        String id = SessionIdUtil.getSessionId(request);

        try{
            // rno를 못불러올때 예외
            if(rno == null){
                throw new Exception("WRONG_APPROACH");
            }
            Review review = reviewService.readOne(rno);
            // 아이디가 일치하지 않을때
            if(!id.equals(review.getUser_id())){
                throw new Exception("WRONG_APPROACH");
            }
            // 이미 삭제된 리뷰일때
            if(review.getWhether() == 2){
                throw new Exception("ALREADY_REMOVED");
            }
            m.addAttribute("review", review);
            return "mypage/review-write";
        }catch (Exception e){
            e.printStackTrace();
            if(e.getMessage() != null){
                if(e.getMessage().equals("WRONG_APPROACH")){
                    m.addAttribute("msg", "잘못된 접근입니다.");
                    m.addAttribute("url", "/jnh");
                }if(e.getMessage().equals("ALREADY_REMOVED")){
                    m.addAttribute("msg", "삭제된 리뷰입니다.");
                    m.addAttribute("url", "able");
                }
            }else {
                m.addAttribute("msg", "페이지 접근에 실패했습니다. 지속될 경우 고객센터에 문의해주세요.");
                m.addAttribute("url", "able");
            }
            return "alert";
        }
    }

    @PostMapping("write") // 작성가능한 리뷰를 작성하고 수정하는 메서드
    public String writeReview(Review review, HttpServletRequest request, HttpServletResponse response, @RequestParam(value = "uploadFile", required = false) MultipartFile file, Model m){
        CacheControlUtil.setNoCacheHeaders(response);
        String id = SessionIdUtil.getSessionId(request);
        // 이미지를 변경하지 않았을때 not_change에서 true라는 문자를 받아옴
        String not_change = request.getParameter("not_change");

        try{
            String rId = reviewService.readOne(review.getRno()).getUser_id();
            // 리뷰 작성권한이 없는 아이디는 접근못하게 차단
            if(!id.equals(rId)){
                throw new Exception("WRONG_APPROACH");
            }
            // not_change값이 없을시 이미지 파일 새로 저장
            if(not_change == null){
                // 업로드 할 이미지가 있을시에만 처리
                if(!file.isEmpty()){
                    // 이미지를 경로에 저장하고 생성하여 저장된 파일이름을 반환하는 util메서드 호출
                    String filename = FileMultiSaveUtil.uploadImg(file, request, "review-img", review.getRno() + "");
                    review.setImage(filename);
                }
            }
            review.setUser_id(id);
            // reg_date가 null이 아닐때(이미 작성된 리뷰 일때) up_date를 set
            if(review.getReg_date() != null){
                review.setUp_date(new Date());
            }else{ // 처음 작성하는 리뷰일때 reg_date를 set
                review.setReg_date(new Date());
            }
            review.setWhether(1);

            if(reviewService.modify(review) != 1){
                throw new Exception();
            }
            m.addAttribute("msg", "등록되었습니다.");
            m.addAttribute("url", "wrote");
        }catch (Exception e){
            e.printStackTrace();
            if(e.getMessage() != null) {
                m.addAttribute("msg", "잘못된 접근입니다.");
                m.addAttribute("url", "/jnh");
            }else{
                m.addAttribute("msg", "작성에 실패했습니다. 지속될 경우 고객센터에 문의해주세요.");
                m.addAttribute("url", review.getReg_date() != null ? "wrote" : "able");
            }
        }

        return "alert";
    }

    @GetMapping("wrote")// 작성한 리뷰 목록 리스트
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

    @GetMapping("modify") // 수정할 리뷰를 가져오는 메서드
    public String modifyReview(@RequestParam(required = false) Integer rno, HttpServletRequest request, Model m){
        String id = SessionIdUtil.getSessionId(request);

        try{
            // rno값을 통해 해당 리뷰 가져오기
            Review review =  reviewService.readOne(rno);
            if(!review.getUser_id().equals(id)){
                throw new Exception("LONG_APPROACH");
            }
            if(review.getWhether() == 2){
                throw new Exception("ALREADY_REMOVED");
            }
            m.addAttribute("review", review);
            m.addAttribute("modify", "modify");

            return "mypage/review-write";
        }catch (Exception e){
            e.printStackTrace();
            if (e.getMessage() != null) {
                if(e.getMessage().equals("LONG_APPROACH")){
                    m.addAttribute("msg","잘못된 접근입니다.");
                    m.addAttribute("url", "/jnh");
                }if(e.getMessage().equals("ALREADY_REMOVED")){
                    m.addAttribute("msg","삭제된 리뷰입니다.");
                    m.addAttribute("url", "wrote");
                }
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

        String referer = request.getHeader("referer");
        String url = "able";
        if(referer.contains("wrote")){
            url = "wrote";
        }if(referer.contains("able")){
            url = "able";
        }

        try{
            Review review = reviewService.readOne(rno);

            if(!id.equals(review.getUser_id())){
                throw new Exception("WRONG_APPROACH");
            }
            if(review.getWhether() == 2){
                throw new Exception("WRONG_APPROACH");
            }
            Review remove = new Review();
            remove.setUser_id(id);
            remove.setRno(rno);
            remove.setWhether(2);
            // 실제로 삭제하지않고 삭제처리 상태를 2(삭제처리)로 수정
            review.setWhether(2);
            reviewService.modify(review);
            m.addAttribute("msg", "삭제되었습니다.");
            m.addAttribute("url", url);
        }catch (Exception e){
            e.printStackTrace();
            if(e.getMessage() != null){
                if(e.getMessage().equals("WRONG_APPROACH")){
                    m.addAttribute("msg", "잘못된 접근입니다.");
                    m.addAttribute("url", "/jnh");
                }
            }else{
                m.addAttribute("msg", "삭제에 실패했습니다. 지속될 경우 고객센터에 문의해주세요.");
                m.addAttribute("url", url);
            }
        }
        return "alert";
    }

    @PostMapping("list")
    @ResponseBody
    public Map<String, Object> list(@RequestBody Map<String, Object> map, SearchCondition sc, HttpServletRequest request){
        String id = SessionIdUtil.getSessionId(request);
        // map에 product_id가 담겨있음
        sc.setPageSize(5); // 한 페이지에 보여주는 리뷰 5개
        sc.setPage((int)map.get("page")); // 현재 요청 page 대입
        map.put("sc",sc);
        map.put("whether", 1);

        try {
            int totalCnt = reviewService.selectPageCnt(map);
            List<Review> list = reviewService.selectPage(map);
            PageHandler ph = new PageHandler(totalCnt, sc);

            map.put("list", list);
            map.put("ph", ph);
            map.put("id", id);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return map;
    }
}
