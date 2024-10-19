package kr.co.jnh.controller;


import com.mysql.cj.Session;
import kr.co.jnh.dao.NoticeDao;
import kr.co.jnh.domain.NoticeDto;
import kr.co.jnh.domain.PageHandler;
import kr.co.jnh.domain.SearchCondition;
import kr.co.jnh.service.NoticeService;
import kr.co.jnh.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
//@RequestMapping("/notice")
public class NoticeController {

    @Autowired
    NoticeService noticeService;
    @Autowired
    UserService userService;


    @GetMapping("/noticeWrite")
    public String noticeWrite(HttpSession session, Model m){
        String id = (String)session.getAttribute("id");
        return "notice/noticeWrite";
//        try {
//            int grade = userService.getGrade(id);
//            if(grade == 0){
//                return "notice/noticeWrite";
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        m.addAttribute("msg", "관리자만 작성가눙합니다.");
//        m.addAttribute("url", "/jnh/noticeList");
//        return "notice/alert";
    }

    @PostMapping("/noticeWrite")
    public String postNoticeWrite(NoticeDto noticeDto ,HttpServletRequest request){
        // 모든 칸 필수입력
       /* NoticeDto dto = new NoticeDto();
        dto.setTitle(request.getParameter("title"));
        dto.setContents(request.getParameter("contents"));*/

        if(noticeDto.getCategory().isBlank() || noticeDto.getTitle().isBlank() || noticeDto.getContents().isBlank()){
            request.setAttribute("msg", "NOT_BLANK");
            request.setAttribute("noticeDto", noticeDto);
            return "notice/noticeWrite";
        }

        // 로그인 확인
        HttpSession session = request.getSession();
        String id = (String)session.getAttribute("id");


        //필독 (5개)
        String mustread = request.getParameter("mustread");
        System.out.println("mustread = " + mustread);
        int mRead = mustread != null ? 0 : 1;
        noticeDto.setId(id);
        noticeDto.setMust_read(mRead);
        try {
            int mcount = noticeService.Mcount();

            if(mcount > 5 && mRead==0){
                request.setAttribute("msg", "MUST_READ_5");
                request.setAttribute("noticeDto", noticeDto);
                return "notice/noticeWrite";
            }
            noticeService.write(noticeDto);
            request.setAttribute("msg", "등록되었습니다.");
            request.setAttribute("url", "/jnh/noticeList");
            return "notice/alert";

        } catch (Exception e) {
           e.printStackTrace();
            System.out.println("fail");
        }
        return "notice/noticeList";
    }

    @GetMapping("/noticeList")
    public String noticeList(SearchCondition sc, Model m){
        /*try {
            List<NoticeDto> list = noticeService.getList();
            m.addAttribute("list" ,list);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "notice/noticeList";*/
        System.out.println("sc.toString() = " + sc.toString());


        try {
            /*boardService의 getCount 메소드를 이용해 총 글의 개수(totalCnt)를 얻는다.
            totalCnt와 page, pageSize를 생성자 매개변수로 하여 pageHandler를 얻는다.
            System.out.println("pageHandler.getTo = " + pageHandler.getTo);
            map에는 건너뛸 글의 갯수인 (page-1)*10 와 보여줄 글의 갯수 pageSize를 저장한 후
            boardService의 getPage 메소드를 이용하여 해당 페이지에서 보여줄 글들을 list에 저장하였다.
            list와 pageHandler를 Model에 저장하여 View인 boardList.jsp에 전달한다.*/
            int totalCnt = noticeService.getSearchResultCnt(sc);

            PageHandler pageHandler = new PageHandler(totalCnt,sc);
            m.addAttribute("ph", pageHandler);

            System.out.println("pageHandler.toString() = " + pageHandler.toString());

            List<NoticeDto> mustRead = noticeService.getSelectMustRead();
            m.addAttribute("mustRead",mustRead);

            List<NoticeDto> list = noticeService.getSearchSelectPage(sc);
            m.addAttribute("list",list);


        } catch (Exception e) {
            e.printStackTrace();
        }

        return "notice/noticeList";
    }

    @GetMapping("/notice")
    public String notice(Integer bno ,SearchCondition sc ,Model m){
        try {
            NoticeDto noticeDto = noticeService.read(bno);
            m.addAttribute("sc", sc);
            m.addAttribute("noticeDto", noticeDto);

            Map bnoCategory = new HashMap();
            bnoCategory.put("bno", bno);
            if(sc.getOption().equals(noticeDto.getCategory()) || sc.getOption().equals("")){
                bnoCategory.put("option", sc.getOption());
            }else{
                bnoCategory.put("option", noticeDto.getCategory());
            }
            Map map = noticeService.getPrevNext(bnoCategory);
            List<NoticeDto> list = new ArrayList<>();

            ArrayList<Integer> arr = new ArrayList<>(); // (436,435,434,433) (String)Object(431).toString(); Integer.parsInt("431"); (int)431

            for(int i = 0; i<5; i++){
                if(map.get("arr" + i) != null){
                    arr.add(Integer.parseInt(map.get("arr" + i).toString()));
                }
            }
            for(int i=0; i<arr.size(); i++){
                list.add(noticeService.read(arr.get(i)));
            }
                m.addAttribute("list", list);
        } catch (Exception e) {
            e.printStackTrace();
        };
        return "notice/notice";
    }



}
