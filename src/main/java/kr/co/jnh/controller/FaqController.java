package kr.co.jnh.controller;


import kr.co.jnh.domain.Faq;
import kr.co.jnh.domain.PageHandler;
import kr.co.jnh.domain.SearchCondition;
import kr.co.jnh.service.FaqService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/FAQ")
public class FaqController {
    @Autowired
    FaqService faqService;

    /* 관리자만 글 작성 */
    @GetMapping("/write")
    public String getwrt(){
        return "notice/faq-write";
    }
    @PostMapping("/write")
    public String wrt(Model m, Faq faq) {
        try {
            // 필수 값이 비어있는지 확인 비어있을 시 작성페이지로 이동
            if ( faq.getQuestion_type().isBlank() ||
                    faq.getQuestion().isBlank() ||
                    faq.getAnswer().isBlank()) {
                m.addAttribute("msg", "필수값을 입력해주세요.");
                m.addAttribute("url", "write?no=" + (faq.getNo() != null ? faq.getNo() : ""));
                //return "notice/faq-write";
                return "alert";
            }

            // 작성 성공의 경우
            if (faqService.write(faq) > 0) {
                m.addAttribute("msg", "등록되었습니다.");
                m.addAttribute("url", "/jnh/FAQ/list");
                return "alert";
            } else {
                m.addAttribute("msg", "등록에 실패했습니다.");
                m.addAttribute("url", "write?no=" + faq.getNo());
                return "alert";
            }
        } catch (Exception e) {
            e.printStackTrace();
            m.addAttribute("msg","오류가 발생했습니다.");
            m.addAttribute("url","/jnh/FAQ/list");
            return "alert";
        }
    }

    /* FAQ 글 목록 불러오기 */
    @GetMapping("/list")
    public String faq(SearchCondition sc, Model m){
        try {
            // 전체 게시물 갯수
            int totalCnt=faqService.getCount(sc);

            // 전체 게시물 갯수를 통해 PageHandler를 이용한 페이징 처리
            PageHandler ph=new PageHandler(totalCnt,sc);
            m.addAttribute("ph",ph);

            // 전체 글 가져오기
            List<Faq> listAll = faqService.readAll(sc);
            m.addAttribute("listAll", listAll);
        } catch (Exception e) {
            e.printStackTrace();
        }


        return "notice/faq";
    }


    @GetMapping("/modify")
    public String getMod(SearchCondition sc, Model m, int no){
        Faq faq = null;
        try {
            // 해당 번호의 FAQ 정보를 불러오기
            faq = faqService.readNo(no);
            m.addAttribute("faq",faq);

            // 수정 페이지 구분용
            m.addAttribute("modify","1");

        } catch (Exception e) {
            e.printStackTrace();
        }
        return "notice/faq-write";
    }

    @PostMapping("/modify")
    public String mod( Model m, Faq faq){
        try {
            m.addAttribute("faq",faq);

            // 수정 완료시
            if (faqService.modify(faq)>0) {
                m.addAttribute("msg","수정이 완료되었습니다.");
                m.addAttribute("url", "/jnh/FAQ/list");
                return "alert";
            }else {
                m.addAttribute("msg", "등록에 실패했습니다.");
                m.addAttribute("url", "/jnh/FAQ/list");
                return "alert";
            }
        } catch (Exception e) {
            e.printStackTrace();
            m.addAttribute("msg","오류가 발생했습니다.");
            m.addAttribute("url","/jnh/FAQ/list");
            return "alert";
        }

    }

    @PostMapping("/remove")
    public String remove(Model m, @RequestParam("no") Integer no){
        try {
            // no 값이 없으면 오류 처리
            if (no == null) {
                m.addAttribute("msg", "삭제할 게시글을 찾을 수 없습니다.");
                m.addAttribute("url", "/jnh/FAQ/list");
                return "alert";
            }
           // 삭제 성공의 경우
            if (faqService.delete(no) > 0) {
                m.addAttribute("msg", "삭제되었습니다.");
                m.addAttribute("url", "/jnh/FAQ/list");
                return "alert";

            } else {
                m.addAttribute("msg", "삭제에 실패했습니다.");
                m.addAttribute("url", "/jnh/FAQ/list");
                return "alert";
            }

        } catch (Exception e) {
            e.printStackTrace();
            m.addAttribute("msg","오류가 발생했습니다.");
            m.addAttribute("url","/jnh/FAQ/list");
            return "alert";
        }
    }

}
