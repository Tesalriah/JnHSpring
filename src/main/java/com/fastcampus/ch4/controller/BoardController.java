package com.fastcampus.ch4.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.fastcampus.ch4.domain.BoardDto;
import com.fastcampus.ch4.domain.PageHandler;
import com.fastcampus.ch4.domain.SearchCondition;
import com.fastcampus.ch4.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/board")
public class BoardController {
    @Autowired
    BoardService boardService;

    @PostMapping("/modify")
    public String modify(Integer page, Integer pageSize, BoardDto boardDto, Model m ,HttpSession session, RedirectAttributes rattr){
        String writer = (String)session.getAttribute("id");
        boardDto.setWriter(writer);

        try {
            BoardDto check =  boardService.read(boardDto.getBno());
            if(check.getTitle() == null)
                throw new NullPointerException("Removed board");

            int rowCnt = boardService.modify(boardDto);

            if(rowCnt != 1)
                throw new Exception("Modify Failed");

            rattr.addFlashAttribute("msg", "MOD_OK");

            return "redirect:/board/list";
        }
        catch (NullPointerException e) {
            e.printStackTrace();
            m.addAttribute("mode", "mod");
            m.addAttribute("msg", "REMOVED");
        }catch (Exception e) {
            e.printStackTrace();
            m.addAttribute("mode", "new");
            m.addAttribute("msg", "MOD_ERR");
        }
        m.addAttribute(boardDto);
        return "board";
    }

    @PostMapping("/write")
    public String write(BoardDto boardDto, Model m ,HttpSession session, RedirectAttributes rattr){
        String writer = (String)session.getAttribute("id");
        boardDto.setWriter(writer);

        try {
            int rowCnt = boardService.write(boardDto);

            if(rowCnt != 1)
                throw new Exception("Writer Failed");

            rattr.addFlashAttribute("msg", "WRT_OK");

            return "redirect:/board/list";
        } catch (Exception e) {
            e.printStackTrace();
            m.addAttribute(boardDto);
            m.addAttribute("mode", "new");
            m.addAttribute("msg", "WRT_ERR");
            return "board";
        }
    }

    @GetMapping("/write")
    public String write(Model m){
        try {
            m.addAttribute("mode", "new");
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "board";
    }

    @PostMapping("/remove")
    public String read(Integer bno, Integer page, Integer pageSize, Model m, HttpSession session, RedirectAttributes rattr){
        String writer = (String)session.getAttribute("id");
        try {
            m.addAttribute("page", page);
            m.addAttribute("pageSize", pageSize);

            int rowCnt = boardService.remove(bno,writer);

            if(rowCnt != 1)
                throw new Exception("board romove error");

            rattr.addFlashAttribute("msg", "DEL_OK");
        } catch (Exception e) {
            e.printStackTrace();
            rattr.addFlashAttribute("msg", "DEL_ERR");
        }

        return "redirect:/board/list?page="+page+"&pageSize="+pageSize;
    }

    @GetMapping("/read")
    public String read(Integer bno, Integer page, Integer pageSize, Model m, RedirectAttributes rattr){
        try {
            BoardDto boardDto = boardService.read(bno);
            if(boardDto.getTitle() == null)
                throw new Exception("board is removed");

            m.addAttribute(boardDto);
            m.addAttribute("page", page);
            m.addAttribute("pageSize", pageSize);
        } catch (Exception e) {
            e.printStackTrace();
            rattr.addFlashAttribute("msg","REMOVED");

            return "redirect:/board/list?page="+page+"&pageSize="+pageSize;
        }

        return "board";
    }

    @ResponseBody
    @GetMapping("/paging")
    public ResponseEntity<PageHandler> paging(SearchCondition sc){
        try {
            int totalCnt = boardService.getSearchResultCnt(sc);
            PageHandler ph = new PageHandler(totalCnt, sc);

            return new ResponseEntity<PageHandler>(ph, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        }
            return new ResponseEntity<PageHandler>(HttpStatus.BAD_REQUEST);
    }

    @ResponseBody
    @PostMapping("/paging")
    public ResponseEntity<List<BoardDto>> list(@RequestBody SearchCondition sc){
        try {
            List<BoardDto> list =  boardService.getSearchResultPage(sc);

            System.out.println("list = " + list);
            return new ResponseEntity<List<BoardDto>>(list, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<List<BoardDto>>(HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/list")
    public String list(SearchCondition sc, Model m, HttpServletRequest request) {
        if(!loginCheck(request))
            return "redirect:/login/login?toURL="+request.getRequestURL();  // 로그인을 안했으면 로그인 화면으로 이동

        try {
            int totalCnt = boardService.getSearchResultCnt(sc);
            m. addAttribute("totalCnt", totalCnt);
            PageHandler ph = new PageHandler(totalCnt, sc);

            List<BoardDto> list =  boardService.getSearchResultPage(sc);
            m.addAttribute("list", list);
            m.addAttribute("ph", ph);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return "boardList"; // 로그인을 한 상태이면, 게시판 화면으로 이동
    }

    private boolean loginCheck(HttpServletRequest request) {
        // 1. 세션을 얻어서
        HttpSession session = request.getSession();
        // 2. 세션에 id가 있는지 확인, 있으면 true를 반환
        return session.getAttribute("id")!=null;
    }
}