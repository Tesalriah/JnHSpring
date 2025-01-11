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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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
    public String writeReview(@RequestParam(required = false) Integer no, HttpServletRequest request, Model m){
        String id = SessionIdUtil.getSessionId(request);

        try{
            if(no == null){
                throw new Exception("WRONG_APPROACH");
            }
            Review review = reviewService.selectOne(no);
            if(review == null){
                throw new Exception("FAIL");
            }
            if(review.getWhether() != 0){
                throw new Exception("ALREADY_WRITTEN");
            }
            if(!id.equals(review.getUser_id())){
                throw new Exception("WRONG_APPROACH");
            }
            m.addAttribute("review", review);
            return "mypage/review-write";
        }catch (Exception e){
            e.printStackTrace();
            if(e.getMessage().equals("WRONG_APPROACH")){
                m.addAttribute("msg", "잘못된 접근입니다.");
                m.addAttribute("url", "/jnh");
            }if(e.getMessage().equals("ALREADY_WRITTEN")){
                m.addAttribute("msg", "이미 작성된 리뷰입니다.");
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
    public String writeReview(Review review, HttpServletRequest request, @RequestParam("uploadFile") MultipartFile file){
            String id = SessionIdUtil.getSessionId(request);

        try{
            String rId = reviewService.selectOne(review.getRno()).getUser_id();
            if(!id.equals(rId)){
                throw new Exception("WRONG_APPROACH");
            }
            // 이미지를 폴더에 저장하기 위해 경로생성
            String savePath = request.getServletContext().getRealPath("webapp/resources/img/upload/review-img");
            savePath += review.getProduct_id() + "/";
            Path directory = Paths.get(savePath);
            Files.createDirectories(directory);
            // 이미지 파일의 확장자를 나누고 이름을 rno으로 바꾸어 확장자와 함께 저장
            String originalFileName = file.getOriginalFilename();
            String[] fileNameArr = originalFileName.split("\\.");
            fileNameArr[0] = review.getRno() + "";
            originalFileName = fileNameArr[0] + "." + fileNameArr[1];
            File newFile = new File(savePath + originalFileName);
            file.transferTo(newFile);
            System.out.println(savePath + originalFileName);

            review.setImage(originalFileName);

            System.out.println("review = " + review);
        }catch (Exception e){
            e.printStackTrace();
        }

        return "redirect:/";
    }
}
