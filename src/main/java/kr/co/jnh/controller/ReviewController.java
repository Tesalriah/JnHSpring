package kr.co.jnh.controller;

import kr.co.jnh.domain.PageHandler;
import kr.co.jnh.domain.Review;
import kr.co.jnh.domain.SearchCondition;
import kr.co.jnh.service.ReviewService;
import kr.co.jnh.util.CacheControlUtil;
import kr.co.jnh.util.FileMultiSaveUtil;
import kr.co.jnh.util.SessionUtils;
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
        String id = SessionUtils.getSessionId(request);

        try {
            Map<String, Object> map = new HashMap<>();
            map.put("id", id);
            // whether가 0이면 리뷰가 작성가능한 상태
            map.put("whether", 0);
            map.put("sc", sc);

            int totalCnt = reviewService.selectPageCnt(map);
            List<Review> list = reviewService.readPageWithOrder(map);
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
        String id = SessionUtils.getSessionId(request);

        try{
            // rno를 못불러올때 예외
            if(rno == null){
                throw new Exception("WRONG_APPROACH");
            }
            Review review = reviewService.readOneWithOrder(rno);
            // review 데이터 검증
            if(reviewValidator(review, id)){
                throw new Exception("WRONG_APPROACH");
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

    @PostMapping("write") // 작성가능한 리뷰를 작성하고 수정에서 또한 작동하는 메서드
    public String writeReview(Review review, HttpServletRequest request, HttpServletResponse response, @RequestParam(value = "uploadFile", required = false) MultipartFile file, Model m){
        CacheControlUtil.setNoCacheHeaders(response);
        String id = SessionUtils.getSessionId(request);
        // 이미지를 변경하지 않았을때 not_change에서 true라는 문자를 받아옴
        String not_change = request.getParameter("not_change");

        try{
            Review realReview = reviewService.readOne(review.getRno());
            realReview.setContents(review.getContents());
            realReview.setRating(review.getRating());
            // 변경하려는 리뷰번호에 할당된 아이디와 세션의 아이디가 일치하지 않을시 예외발생
            if(reviewValidator(realReview, id)){
                throw new Exception("WRONG_APPROACH");
            }
            // not_change값이 지정한 값 true가 아닐경우 새로운 이미지 저장
            if(not_change != "true"){
                // 업로드 할 이미지가 있을시에만 처리
                if(!file.isEmpty()){
                    // 이미지를 경로에 저장하고 생성하여 저장된 파일이름을 반환하는 util메서드 호출
                    String filename = FileMultiSaveUtil.uploadImg(file, request, "review-img", realReview.getRno() + "");
                    realReview.setImage(filename);
                }
            }
            // reg_date가 null이 아닐때(이미 작성된 리뷰 일때) up_date만 set
            if(realReview.getReg_date() != null){
                realReview.setUp_date(new Date());
            }else{ // 처음 작성하는 리뷰일때 reg_date를 set
                realReview.setReg_date(new Date());
                realReview.setUp_date(new Date());
            }
            realReview.setWhether(1);

            if(reviewService.modify(realReview) != 1){
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
    public String wroteReview(SearchCondition sc, HttpServletRequest request, Model m){
        String id = SessionUtils.getSessionId(request);

        Map<String, Object> map = new HashMap<>();
        map.put("id", id);
        // whether가 1일때 이미 작성된 리뷰
        map.put("whether", 1);
        sc.setOption("reg_date");
        map.put("sc", sc);

        try{
            int totalCnt = reviewService.selectPageCnt(map);
            List<Review> list = reviewService.readPageWithOrder(map);
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
        String id = SessionUtils.getSessionId(request);

        try{
            // rno값을 통해 해당 리뷰 가져오기
            Review review =  reviewService.readOneWithOrder(rno);
            // review 데이터 검증
            if(reviewValidator(review, id)){
                throw new Exception("WRONG_APPROACH");
            }
            m.addAttribute("review", review);
            m.addAttribute("modify", "modify");

            return "mypage/review-write";
        }catch (Exception e){
            e.printStackTrace();
            if (e.getMessage() != null) {
                if(e.getMessage().equals("ALREADY_REMOVED")){
                    m.addAttribute("msg","삭제된 리뷰입니다.");
                    m.addAttribute("url", "wrote");
                }if(e.getMessage().equals("WRONG_APPROACH")){
                    m.addAttribute("msg","잘못된 접근입니다.");
                    m.addAttribute("url", "/jnh");
                }else{
                    m.addAttribute("msg","페이지 접근에 실패했습니다. 지속될 경우 고객센터에 문의해주세요.");
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
        String id = SessionUtils.getSessionId(request);

        // 이전페이지를 확인하여 작성된 리뷰를 삭제하는지 작성목록에서 삭제하는지 구분하여 완료시 갱신되는 페이지 구분
        String referer = request.getHeader("referer");
        String url = "able";
        if(referer.contains("wrote")){
            url = "wrote";
        }if(referer.contains("able")){
            url = "able";
        }

        try{
            Review review = reviewService.readOne(rno);
            // review 데이터 검증
            if(reviewValidator(review, id)){
                throw new Exception("WRONG_APPROACH");
            }
            // 실제로 삭제하지않고 삭제처리 상태를 2(삭제처리)로 수정, 작성하지 않은 리뷰삭제는 3으로 처리
            if(url.equals("able")){
                review.setWhether(3);
            }else{
                review.setWhether(2);
            }
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

    // 상품정보 리뷰리스트 Ajax
    @PostMapping("list")
    @ResponseBody
    public Map<String, Object> list(@RequestBody Map<String, Object> map, SearchCondition sc, HttpServletRequest request){
        String id = SessionUtils.getSessionId(request);

        // map에 이미 product_id가 담겨있으므로 해당 product가 가지고있는 리뷰 목록

        sc.setPageSize(5); // 한 페이지에 보여주는 리뷰 5개
        sc.setPage((int)map.get("page")); // 현재 요청 page 대입
        sc.setOption((String)map.get("option"));
        map.put("sc",sc);
        // whether가 1이면 작성한 리뷰
        map.put("whether", 1);

        try {
            int totalCnt = reviewService.selectPageCnt(map);
            List<Review> list = reviewService.readPage(map);
            PageHandler ph = new PageHandler(totalCnt, sc);

            map.put("list", list);
            map.put("ph", ph);
            // 세션에 저장된 아이디를 알림으로써 사용자를 확인하여 프론트에 다른 옵션생성
            map.put("id", id);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return map;
    }

    @PostMapping("removeAjax")
    @ResponseBody
    public Map<String, Object> removeAjax(@RequestBody Map<String, Object> map, HttpServletRequest request){
        String id = SessionUtils.getSessionId(request);
        int rno = (Integer)map.get("rno");

        try{
            Review review = reviewService.readOne(rno);
            // review 데이터 검증
            if(reviewValidator(review, id)){
                throw new Exception("WRONG_APPROACH");
            }
            // 실제로 삭제하지않고 삭제처리 상태를 2(삭제처리)로 수정
            review.setWhether(2);
            reviewService.modify(review);
            map.put("msg", "삭제되었습니다.");
            map.put("success", true);
        }catch (Exception e){
            e.printStackTrace();
            if(e.getMessage() != null){
                if(e.getMessage().equals("WRONG_APPROACH")){
                    map.put("msg", "잘못된 접근입니다.");
                }
            }else{
                map.put("msg", "삭제에 실패했습니다. 지속될 경우 고객센터에 문의해주세요.");
            }
        }
        return map;
    }

    // if문에서 이 검증 메서드를 사용하여 true면 접근불가능하게 Exception 발생예정
    private boolean reviewValidator(Review review, String id){
        // rno를 통해 가져온 Review에 할당된 아이디값과 세션에 로그인된 아이디값이 같지 않을경우 접근불가
        // 삭제 처리된(whether=2) 리뷰일경우 접근불가
        if(review.getWhether() == 2){
            return true;
        }
        // 세션에서 받아온 id와 review에 저장된 id가 같지않을경우 접근불가
        if(!review.getUser_id().equals(id)){
            return true;
        }
        return false;
    }
}
