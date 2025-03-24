package kr.co.jnh.controller;

import kr.co.jnh.domain.AskingDto;
import kr.co.jnh.domain.PageHandler;
import kr.co.jnh.domain.SearchCondition;
import kr.co.jnh.service.AskingService;
import kr.co.jnh.service.UserService;
import kr.co.jnh.util.SessionIdUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/admin")
public class AdminContorller {

    @Autowired
    AskingService askingService;

    @GetMapping("product")
    public String productMNG(){

        return "/admin/product-mng";
    }

    /* 문의목록 가져오기 */
    @GetMapping("/ask-mng")
    public String ask(Model m, SearchCondition sc){
        sc.setPageSize(15);


        try {
            // 전체게시글 갯수
            int total = askingService.getCount();

            // 전체 게시글 불러오기
            List< AskingDto> readAll = askingService.readAll(sc);
            m.addAttribute("readAll", readAll);

            // 전체 게시물 갯수를 통해 PageHandler를 이용한 페이징 처리
            PageHandler ph = new PageHandler(total,sc);
            m.addAttribute("ph",ph);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return "/admin/ask-mng";
    }

    /* 하나의 게시물 읽기 */
    @GetMapping("/ask-details")
    public String askDetail(Model m, SearchCondition sc, @RequestParam int no){
        try {
            // no를 통해 게시물 불러오기
            List<AskingDto> askingDto = askingService.read(no);
            m.addAttribute("askingDto", askingDto);
            m.addAttribute("sc", sc);


        } catch (Exception e) {
            e.printStackTrace();
        }

        return "/admin/ask-details";
    }

    @PostMapping("/remove")
    public String remove(Model m, AskingDto askingDto,
                         @RequestParam("no") Integer no,
                         @RequestParam(value = "cno", required = false, defaultValue = "2") Integer cno){
        try {
            // no 값이 없으면 오류 처리
            if (no == null) {
                m.addAttribute("msg", "삭제할 게시글을 찾을 수 없습니다.");
                m.addAttribute("url", "/jnh/admin/ask-mng");
                return "alert";
            }

            Map<String, Integer> map = new HashMap<>();
            map.put("no", no);
            map.put("cno", cno);

            // 삭제 실행
            int result = askingService.remove(map);

            // 삭제 성공의 경우
            if (result <= 0) {
                throw new Exception("ASKING-MNG_REMOVE_FAIL");
            }
            m.addAttribute("msg", "삭제되었습니다.");
            m.addAttribute("url", "/jnh/admin/ask-mng");

        } catch (Exception e) {
            e.printStackTrace();
            m.addAttribute("msg", "삭제에 실패했습니다.");
            m.addAttribute("url", "/jnh/admin/ask-mng");
        }
        return "alert";

    }

}
