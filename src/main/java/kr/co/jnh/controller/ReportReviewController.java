package kr.co.jnh.controller;

import kr.co.jnh.domain.ReportReason;
import kr.co.jnh.domain.ReportReview;
import kr.co.jnh.service.ReportReviewService;
import kr.co.jnh.util.SessionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
public class ReportReviewController {

    @Autowired
    ReportReviewService reportReviewService;

    @PostMapping("report")
    @ResponseBody
    public Map<String, Object> report(@RequestBody Map<String,Object> map, HttpServletRequest request){
        String reporter = SessionUtils.getSessionId(request);
        Integer rno = Integer.parseInt((String)map.get("rno"));
        String reason = (String)map.get("reason");
        String id = (String)map.get("id");
        String contents = (String)map.get("contents");
        // Enum을 사용하여 해당하는 번호에 대한 문자열을 가져옴 다른값이 들어오면 Enum애서 설정한 기본값으로 설정한 값을 사용
        ReportReason reportReason = ReportReason.fromCode(Integer.parseInt(reason));

        try {
            if(reporter == null || reporter.equals("")){
                throw new Exception("NEED_LOGIN");
            }
            ReportReview reportReview = new ReportReview(id, reporter, reportReason.getDescription(), rno, contents);

            if(reportValidator(reportReview)){
                throw new Exception("WRONG_APPROACH");
            }

            int cnt = reportReviewService.checkDup(map);
            if(cnt > 0){
                map.put("msg", "이미 신고한 리뷰입니다.");
            }else{
                int result = reportReviewService.write(reportReview);
                if(result != 1){
                    throw new Exception("REPORT_WRT_FAIL");
                }else {
                    map.put("msg", "신고처리 되었습니다.");
                }
            }
            map.put("result", "close");
        } catch (Exception e) {
            e.printStackTrace();
            if(e.getMessage() != null && e.getMessage().equals("NEED_LOGIN")){
                map.put("msg", "로그인을 해주세요.");
            }else if(e.getMessage() != null && e.getMessage().equals("WRONG_APPROACH")){
                map.put("msg", "입력하지 않은 값을 확인해주세요.");
            } else{
                map.put("msg", "작성에 실패했습니다. 다시 시도해주세요.");
            }
            map.put("result", "open");
        }

        return map;
    }

    private boolean reportValidator(ReportReview reportReview){
        if(reportReview.getUser_id() == null || reportReview.getUser_id().equals("")){
            return true;
        }if(reportReview.getContents() == null || reportReview.getContents().equals("")){
            return true;
        }if(reportReview.getReason() == null || reportReview.getReason().equals("")){
            return true;
        }if(reportReview.getRno() == null){
            return true;
        }
        return false;
    }
}
