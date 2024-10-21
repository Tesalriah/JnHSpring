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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
//        return "alert";
    }

    @PostMapping("/noticeWrite")
    public String postNoticeWrite(Integer bno ,SearchCondition sc,NoticeDto noticeDto ,HttpServletRequest request, RedirectAttributes rattr){
        // 모든 칸 필수입력
       /* NoticeDto dto = new NoticeDto();
        dto.setTitle(request.getParameter("title"));
        dto.setContents(request.getParameter("contents"));*/

        // 로그인 확인
        HttpSession session = request.getSession();
        String id = (String)session.getAttribute("id");
        try {
            if(userService.getGrade(id) != 0){
                request.setAttribute("msg","관리자만 작성 가능합니다.");
                request.setAttribute("url", "noticeList"+sc.getOptionQueryString());
                return "alert";
            }

            if(noticeDto.getCategory().isBlank() || noticeDto.getTitle().isBlank() || noticeDto.getContents().isBlank()){
                request.setAttribute("msg", "NOT_BLANK");
                request.setAttribute("noticeDto", noticeDto);
                return "notice/noticeWrite";
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        //필독 (5개)
        String mustread = request.getParameter("mustread");
        System.out.println("mustread = " + mustread);
        int mRead = mustread != null ? 0 : 1;
        noticeDto.setId(id);
        noticeDto.setMust_read(mRead);
        try {
            int mcount = noticeService.Mcount();
            if(mcount > 5 && mRead==0){
                throw new Exception("FAIL");
            }
            noticeService.write(noticeDto);
            /*request.setAttribute("msg", "등록되었습니다.");
            request.setAttribute("url", "/jnh/noticeList");
            return "alert";*/
//            request.setAttribute("msg", "WRT_OK");
//            return "notice/noticeList";
            request.setAttribute("msg","관리자만 작성 가능합니다.");
            request.setAttribute("url", "noticeList"+sc.getOptionQueryString());
            return "alert";

        } catch (Exception e) {
           e.printStackTrace();
            request.setAttribute("msg", "MUST_READ_5");
            request.setAttribute("noticeDto", noticeDto);
            return "notice/noticeWrite";
        }

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
        if(bno == null){
            m.addAttribute("msg","게시물이 존재하지 않습니다.");
            m.addAttribute("url", "noticeList"+sc.getOptionQueryString());
            return "alert";
        }
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
            ArrayList<Integer> arr = new ArrayList<>();
            Integer[] prevNext = new Integer[2];
            // (key arr1 436, arr2 435) (String)Object(431).toString();
            // Integer.parsInt("431"); (int)431

            for(int i=0; i<5; i++){
                if(map.get("arr" + i) != null){
                    arr.add(Integer.parseInt(map.get("arr" + i).toString()));
                }
            }
            for(int i=0; i<arr.size(); i++){
                list.add(noticeService.read(arr.get(i)));
            }

            if(map.get("arr3") != null){
                prevNext[0] = Integer.parseInt(map.get("arr3").toString());
            }if(map.get("arr1") != null){
                prevNext[1] = Integer.parseInt(map.get("arr1").toString());
            }

            m.addAttribute("list", list);
            m.addAttribute("prevNext", prevNext);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return "notice/notice";
    }

    @PostMapping("/remove")
    public String remove(Integer bno ,SearchCondition sc , HttpServletRequest request, RedirectAttributes rattr){
        HttpSession session = request.getSession();
        String id= (String) session.getAttribute("id");
        Map map = new HashMap();
        map.put("bno", bno);
        try {
            if(userService.getGrade(id) != 0){
                request.setAttribute("msg","관리자만 삭제가 가능합니다.");
                request.setAttribute("url", "noticeList"+sc.getOptionQueryString());
                return "alert";
            }
            if (noticeService.remove(map)!=1){
                throw new Exception("삭제가 실패했습니다.");
            }
            rattr.addFlashAttribute("msg", "REMOVE_OK");
            return "redirect:/noticeList"+sc.getOptionQueryString();
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("msg","관리자만 삭제가 가능합니다.");
            request.setAttribute("url", "noticeList"+sc.getOptionQueryString());
            return "alert";
        }
    }

    @GetMapping("/wriT")
    public String wriT(){
        return "notice/noticeT";
    }

    @PostMapping("/wriT")
    public String wriTPost(NoticeDto noticeDto, String mustread, Model m){
        String id = "asdf";
        System.out.println("noticeDto = " + noticeDto);
        int mread = mustread != null ? 0 : 1;
        noticeDto.setMust_read(mread);
        noticeDto.setId(id);
        System.out.println("noticeDto = " + noticeDto);
        try {
            if(noticeService.write(noticeDto) != 1){
                throw new Exception("FAIL");
            }
            m.addAttribute("msg", "성공");
            m.addAttribute("url" , "noticeList");
            return  "alert";
        } catch (Exception e) {
            e.printStackTrace();
            m.addAttribute("msg", "FAIL");
            m.addAttribute("asd", noticeDto);
            return "notice/noticeT";
        }
    }

    @GetMapping("/modT")
    public String modT(Integer bno, HttpServletRequest request){
        HttpSession session = request.getSession();
        System.out.println("bno = " + bno);
        String id = (String)session.getAttribute("id");
        try {
            NoticeDto dto = noticeService.read(bno);
            System.out.println(dto);
            request.setAttribute("asd", dto);
            request.setAttribute("modi", 1);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return "notice/noticeT";
    }

    @PostMapping("/modT")
    public String modTPost(NoticeDto noticeDto, Model m){
        System.out.println("noticeDto = " + noticeDto);
        try {
            if(noticeService.modify(noticeDto) != 1){
                throw new Exception();
            }
            m.addAttribute("msg", "수정 성공");
            m.addAttribute("url", "notice?bno=" + noticeDto.getBno());
            return "alert";
        } catch (Exception e) {
            e.printStackTrace();
            m.addAttribute("msg", "FAIL");
            m.addAttribute("modi", 1);
            m.addAttribute("asd", noticeDto);
            return "notice/noticeT";
        }
    }

    @GetMapping("/modify")
    public String getMod(int bno, SearchCondition sc, Model m){
        NoticeDto noticeDto = null;
        try {
            noticeDto = noticeService.read(bno);
            m.addAttribute("noticeDto", noticeDto);
            if(noticeDto.getMust_read() == 0){
                m.addAttribute("checkBox", "1");
            }
            m.addAttribute("modify","1");
            m.addAttribute("sc", sc);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "notice/noticeWrite";
    }

    @PostMapping("/modify")
    public String modify(NoticeDto noticeDto,SearchCondition sc , HttpServletRequest request, RedirectAttributes rattr){
        HttpSession session = request.getSession();
        String id= (String) session.getAttribute("id");
        System.out.println(noticeDto.toString());
        try {
            if(userService.getGrade(id) != 0){
                request.setAttribute("msg","관리자만 수정이 가능합니다.");
                request.setAttribute("url", "noticeList"+sc.getOptionQueryString());
                return "alert";
            }
            if (noticeService.modify(noticeDto)!=1){
                throw new Exception("fail.");
            }
            rattr.addFlashAttribute("msg", "mod_OK");
            return "redirect:/noticeList"+sc.getOptionQueryString();
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("msg","수정에 실패하였습니다.");
            request.setAttribute("url", "noticeList"+sc.getOptionQueryString());
            return "alert";
        }

    }



}
