package kr.co.jnh.controller;


import com.mysql.cj.Session;
import kr.co.jnh.dao.NoticeDao;
import kr.co.jnh.domain.NoticeDto;
import kr.co.jnh.domain.PageHandler;
import kr.co.jnh.domain.SearchCondition;
import kr.co.jnh.service.NoticeService;
import kr.co.jnh.service.UserService;
import kr.co.jnh.util.SessionIdUtil;
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
import java.net.http.HttpRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/notice")
public class NoticeController {

    @Autowired
    NoticeService noticeService;
    @Autowired
    UserService userService;


    @GetMapping("/write")
    public String noticeWrite(HttpServletRequest request){
        return "notice/noticeWrite";
    }

    @PostMapping("/write")
    public String postNoticeWrite(Integer bno ,SearchCondition sc,NoticeDto noticeDto ,HttpServletRequest request){
        HttpSession session = request.getSession();
        String id = (String)session.getAttribute("id");
        try {
            if(userService.getGrade(id) != 0){
                request.setAttribute("msg","관리자만 작성 가능합니다.");
                request.setAttribute("url", "notice/list"+sc.getOptionQueryString());
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
        int mRead = mustread != null ? 0 : 1;
        noticeDto.setId(id);
        noticeDto.setMust_read(mRead);
        try {
            int mcount = noticeService.Mcount();
            if(mcount > 5 && mRead==0){
                throw new Exception("FAIL");
            }
            noticeService.write(noticeDto);
            request.setAttribute("msg", "등록되었습니다.");
            request.setAttribute("url", "/jnh/notice/list");
            return "alert";

        } catch (Exception e) {
           e.printStackTrace();
            request.setAttribute("msg", "MUST_READ_5");
            request.setAttribute("noticeDto", noticeDto);
            return "notice/noticeWrite";
        }

    }

    @GetMapping("/list")
    public String noticeList(SearchCondition sc, Model m){
        try {
            int totalCnt = noticeService.getSearchResultCnt(sc);

            PageHandler pageHandler = new PageHandler(totalCnt,sc);
            m.addAttribute("ph", pageHandler);

            List<NoticeDto> mustRead = noticeService.getSelectMustRead();
            m.addAttribute("mustRead",mustRead);

            List<NoticeDto> list = noticeService.getSearchSelectPage(sc);
            m.addAttribute("list",list);


        } catch (Exception e) {
            e.printStackTrace();
        }

        return "notice/noticeList";
    }

    @GetMapping("/read")
    public String notice(Integer bno ,SearchCondition sc ,Model m){
        if(bno == null){
            m.addAttribute("msg","게시물이 존재하지 않습니다.");
            m.addAttribute("url", "notice/list"+sc.getOptionQueryString());
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
                request.setAttribute("url", "notice/list"+sc.getOptionQueryString());
                return "alert";
            }
            if (noticeService.remove(map)!=1){
                throw new Exception("삭제가 실패했습니다.");
            }
            rattr.addFlashAttribute("msg", "REMOVE_OK");
            return "redirect:/notice/list"+sc.getOptionQueryString();
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("msg","관리자만 삭제가 가능합니다.");
            request.setAttribute("url", "notice/list"+sc.getOptionQueryString());
            return "alert";
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
        try {
            if(userService.getGrade(id) != 0){
                request.setAttribute("msg","관리자만 수정 가능합니다.");
                request.setAttribute("url", "notice/list"+sc.getOptionQueryString());
                return "alert";
            }
            if (noticeService.modify(noticeDto)!=1){
                throw new Exception("fail.");
            }
            rattr.addFlashAttribute("msg", "mod_OK");
            return "redirect:/notice/list"+sc.getOptionQueryString();
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("msg","수정에 실패하였습니다.");
            request.setAttribute("url", "notice/list"+sc.getOptionQueryString());
            return "alert";
        }

    }

}
