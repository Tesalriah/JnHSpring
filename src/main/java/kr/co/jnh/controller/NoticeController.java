package kr.co.jnh.controller;


import kr.co.jnh.domain.NoticeDto;
import kr.co.jnh.domain.PageHandler;
import kr.co.jnh.domain.SearchCondition;
import kr.co.jnh.service.NoticeService;
import kr.co.jnh.util.SessionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/notice")
public class NoticeController {

    @Autowired
    NoticeService noticeService;

    // 공지사항 작성 페이지 이동
    @GetMapping("/write")
    public String noticeWrite(){
        return "notice/notice-write";
    }

    // 공지사항 작성
    @PostMapping("/write")
    public String postNoticeWrite(NoticeDto noticeDto, HttpServletRequest request, Model m){
        String id = SessionUtils.getSessionId(request);
        try {
        } catch (Exception e) {
            e.printStackTrace();
        }

        // 필독이 체크됐을때 0이라는 값을 할당, 아닐 시 1
        String mustread = request.getParameter("mustread");
        int mRead = mustread != null ? 0 : 1;
        noticeDto.setUser_id(id);
        noticeDto.setMust_read(mRead);
        try {
            // 필수 값이 비어있는지 확인 비어있을 시 작성페이지로 이동
            if(noticeRequired(noticeDto)){
                throw new Exception("NOT_BLANK");
            }
            // 필독은 최대 5개까지 가능 5개 초과시 Exception발생
            int mcount = noticeService.Mcount();
            if(mRead==0 && mcount >= 5){
                throw new Exception("MUST_READ_5");
            }
            if(noticeService.write(noticeDto) == 1){
                m.addAttribute("msg", "등록되었습니다.");
                m.addAttribute("url", "/jnh/notice/list");
                return "alert";
            }else{
                throw new Exception("WRT_FAIL");
            }

        } catch (Exception e) {
           e.printStackTrace();
            // 필독 초과 시
           if(e.getMessage().equals("MUST_READ_5")){
               m.addAttribute("msg", e.getMessage());
           }else{
               // 필수 값이 비어있을 시
               if(e.getMessage().equals("NOT_BLANK")){
                   m.addAttribute("msg", e.getMessage());
               }else {
                   m.addAttribute("msg", "WRT_FAIL");

               }
               if(noticeDto.getMust_read() == 0){ // 필독 체크상태 전송
                   m.addAttribute("checkBox", "1");
               }
           }
            m.addAttribute("noticeDto", noticeDto);
        }
        return "notice/notice-write";

    }

    // 공지사항 목록 가져오기
    @GetMapping("/list")
    public String noticeList(SearchCondition sc, Model m){
        try {
            // SearchCondition 정보에 따른 필독이 아닌 전체 게시물 갯수
            int totalCnt = noticeService.getSearchResultCnt(sc);

            // 전체 게시물 갯수를 통해 PageHandler를 이용한 페이징 처리
            PageHandler pageHandler = new PageHandler(totalCnt,sc);
            m.addAttribute("ph", pageHandler);

            // 상단에 고정할 필독 모두 가져오기
            List<NoticeDto> mustRead = noticeService.getSelectMustRead();
            m.addAttribute("mustRead",mustRead);

            // 필독 하단에 위치할 SearchCondition 정보에 따른 게시물
            List<NoticeDto> list = noticeService.getSearchSelectPage(sc);
            m.addAttribute("list",list);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "notice/notice-list";
    }

    // 하나의 공지사항 게시물 읽기
    @GetMapping("/read")
    public String notice(Integer bno ,SearchCondition sc ,Model m){
        if(bno == null){
            m.addAttribute("msg","게시물이 존재하지 않습니다.");
            m.addAttribute("url", "notice/list"+sc.getOptionQueryString());
            return "alert";
        }
        try {
            // bno를 통한 해당 게시물 정보 불러오기
            NoticeDto noticeDto = noticeService.read(bno);
            m.addAttribute("sc", sc);
            m.addAttribute("noticeDto", noticeDto);

            Map<String,Object> bnoCategory = new HashMap<>();
            bnoCategory.put("bno", bno);
            bnoCategory.put("option", sc.getOption());

            // 현재 bno와 sc.category의 기준으로 앞뒤 2개의 게시물의 bno 확인
            Map<String,Object> map = noticeService.getPrevNext(bnoCategory); // Map형태로 arr0(이후후) arr1(이후) arr2(현재) arr3(이전) arr4(이전전) 게시물을의 bno 반환
            List<NoticeDto> list = new ArrayList<>();
            ArrayList<Integer> arr = new ArrayList<>();
            Integer[] prevNext = new Integer[2]; // 이전 이후 게시글을 확인하기 위함

            // arr0 ~ arr4의 실제 값이 있는지 확인해서 arr에 순서대로 bno값 추가 (없을 시 생략됨)
            for(int i=0; i<5; i++){
                if(map.get("arr" + i) != null){
                    arr.add(Integer.parseInt(map.get("arr" + i).toString()));
                }
            }
            for(int i=0; i<arr.size(); i++){ // 저장된 arr의 bno값을 토대로 게시글을 list에 순서대로 저장
                list.add(noticeService.read(arr.get(i)));
            }

            if(map.get("arr3") != null){ // 이전게시글이 있을시 해당 bno를 prevNext[0]에 저장
                prevNext[0] = Integer.parseInt(map.get("arr3").toString());
            }if(map.get("arr1") != null){ // 이후게시글이 있을시 해당 bno를 prevNext[1]에 저장
                prevNext[1] = Integer.parseInt(map.get("arr1").toString());
            }

            m.addAttribute("list", list);
            m.addAttribute("prevNext", prevNext);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "notice/notice";
    }

    // 게시물 제거
    @PostMapping("/remove")
    public String remove(Integer bno ,SearchCondition sc, RedirectAttributes rattr, Model m){
        Map<String,Object> map = new HashMap<>();
        map.put("bno", bno);
        try {
            if (noticeService.remove(map)!=1){ // 삭제실패시 Exception 발생
                throw new Exception("DEL_FAIL");
            }
            // 삭제성공 시 목록으로
            rattr.addFlashAttribute("msg", "REMOVE_OK");
            return "redirect:/notice/list"+sc.getOptionQueryString();
        } catch (Exception e) {
            e.printStackTrace();
            m.addAttribute("msg", "삭제에 실패했습니다.");
            m.addAttribute("url", "notice/list"+sc.getOptionQueryString());
            return "alert";
        }
    }

    // 게시물 수정페이지 요청
    @GetMapping("/modify")
    public String getMod(int bno, SearchCondition sc, Model m){
        NoticeDto noticeDto = null;
        try {
            noticeDto = noticeService.read(bno); // 해당 게시글 정보 가져오기
            m.addAttribute("noticeDto", noticeDto);
            //
            if(noticeDto.getMust_read() == 0){  // 필독 체크상태 전송
                m.addAttribute("checkBox", "1");
            }
            m.addAttribute("modify","1"); // 수정일시 페이지에서 처리하기위해 전송
            m.addAttribute("sc", sc);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "notice/notice-write";
    }

    // 게시물 수정
    @PostMapping("/modify")
    public String modify(NoticeDto noticeDto,SearchCondition sc , HttpServletRequest request, RedirectAttributes rattr, Model m){
        // 필독이 체크됐을때 0이라는 값을 할당, 아닐 시 1
        String mustread = request.getParameter("mustread");
        int mRead = mustread != null ? 0 : 1;
        noticeDto.setMust_read(mRead);
        try {
            // 필수 값이 비어있는지 확인 비어있을 시 작성페이지로 이동
            if(noticeRequired(noticeDto)){
                throw new Exception("NOT_BLANK");
            }
            // 실패시 Exception 발생
            if (noticeService.modify(noticeDto)!=1){
                throw new Exception("MOD_FAIL");
            }

            // 수정완료 시 목록으로
            rattr.addFlashAttribute("msg", "mod_OK");
            return "redirect:/notice/list"+sc.getOptionQueryString();
        } catch (Exception e) {
            e.printStackTrace();
            // 필수값이 비어있을시
            if(e.getMessage().equals("NOT_BLANK")){
                m.addAttribute("msg",e.getMessage());
            }else{ // 이외의 경우
                m.addAttribute("msg","WRT_FAIL");
            }
            if(noticeDto.getMust_read() == 0){ // 필독 체크상태 전송
                m.addAttribute("checkBox", "1");
            }
            m.addAttribute("noticeDto", noticeDto);
            return "notice/notice-write";
        }
    }

    private boolean noticeRequired(NoticeDto noticeDto){
        return noticeDto.getCategory().isBlank() || noticeDto.getTitle().isBlank() || noticeDto.getContents().isBlank();
    }

}
