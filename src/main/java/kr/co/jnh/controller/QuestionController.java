package kr.co.jnh.controller;

import kr.co.jnh.domain.PageHandler;
import kr.co.jnh.domain.Question;
import kr.co.jnh.domain.SearchCondition;
import kr.co.jnh.service.QuestionService;
import kr.co.jnh.util.SessionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("question")// jnh/question/list
public class QuestionController {

    @Autowired
    QuestionService questionService;
    @PostMapping("list")
    @ResponseBody
    public Map<String, Object> list(@RequestBody Map<String, Object> map, SearchCondition sc){
        String product_id = (String)map.get("product_id");
        int currentPage = (int)map.get("page");

        sc.setPage(currentPage);

        map.put("product_id", product_id);
        map.put("sc", sc);

        try {
            int totalCnt = questionService.getCount(map);
            List<Question> questionList = questionService.readInfo(map);
            PageHandler ph = new PageHandler(totalCnt,sc);
            ph.setNaviSize(5);
            map.put("list", questionList);
            map.put("ph", ph);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }

    @PostMapping("write")
    @ResponseBody
    public Map<String, Object> write(@RequestBody Map<String, Object> map ,HttpServletRequest request){
        String user_id = SessionUtils.getSessionId(request);
        String product_id = (String)map.get("product_id");
        String contents = (String)map.get("contents");

        Question question = new Question();
        question.setUser_id(user_id);
        question.setProduct_id(product_id);
        question.setContents(contents);
        try {
            if (user_id==null){
                throw new Exception("NEED_LOGIN");
            }
            int qno = questionService.getLastqno()+1;
            question.setQno(qno);

            if (questionService.write(question)!=1) {
                throw new Exception();
            }
            map.put("success", "작성되었습니다.");
        } catch (Exception e) {
            e.printStackTrace();
            if(e.getMessage() != null){
                if(e.getMessage().equals("NEED_LOGIN")){
                    map.put("fail","로그인이 필요합니다.");
                }
            }else {
                map.put("fail","작성에 실패했습니다.");
            }
        }
        return map;
    }


    @PostMapping("write1")
    @ResponseBody
    public Map<String, Object> w (@RequestBody Map<String, Object> map, HttpServletRequest re){
        String user_id = SessionUtils.getSessionId(re);
        String pid1 =(String) map.get("pid1"); // productID
        String cnt1 =(String) map.get("cnot1"); // contents

        Question q = new Question();
        q.setProduct_id(pid1);
        q.setContents(cnt1);
        q.setUser_id(user_id);

            try {
                if (user_id==null) {
                    throw new Exception("NEED LOGIN");
                }

                int qno1 = questionService.getLastqno()+1;
                q.setQno(qno1);


                if (questionService.write(q)!=1){
                    throw new Exception();
                }
                map.put("suc","작성 완");
            } catch (Exception e) {
                e.printStackTrace();
            }
        return map;
    }



}
