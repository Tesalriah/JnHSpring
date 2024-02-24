package com.fastcampus.ch4.controller;

import com.fastcampus.ch4.domain.CommentDto;
import com.fastcampus.ch4.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class CommentController {

    @Autowired
    CommentService commnetService;

    @ResponseBody
    @PatchMapping("/comments/{cno}")
    public ResponseEntity<String> modify(@PathVariable Integer cno, @RequestBody CommentDto commentDto) {
        String commenter = ("asdf");
        commentDto.setCno(cno);
        commentDto.setCommenter(commenter);
        System.out.println("commentDto = " + commentDto);

        try {
            if(commnetService.modify(commentDto) != 1){
                throw new Exception("Modify Failed");
            }

            return new ResponseEntity<String>("MOD_OK", HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<String>("MOD_ERR", HttpStatus.BAD_REQUEST);
        }

    }

    @ResponseBody
    @PostMapping("/comments")
    public ResponseEntity<String> write(@RequestBody CommentDto commentDto, Integer bno, HttpSession session){
        String commenter = ("asdf");
        commentDto.setCommenter(commenter);
        commentDto.setBno(bno);
        System.out.println("commentDto = " + commentDto);

        try {
            if(commnetService.write(commentDto) != 1){
                throw new Exception("Write Failed");
            }
            return new ResponseEntity<String>("WRT_OK", HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<String>("WRT_ERR", HttpStatus.BAD_REQUEST);
        }
    }


    @DeleteMapping("/comments/{cno}")
    @ResponseBody
    public ResponseEntity<String> remove(@PathVariable Integer cno, Integer bno, HttpSession session){
        String commenter = ("asdf");

        try {
            int rowCnt = commnetService.remove(cno, bno, commenter);

            if(rowCnt != 1){
                throw new Exception("Delete Failed");
            }

            return new ResponseEntity<String>("DEL_OK", HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<String>("DEL_ERR", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/comments")
    @ResponseBody public ResponseEntity<List<CommentDto>> list(Integer bno){
        List<CommentDto> list = null;
        try{
            list = commnetService.getList(bno);
            return new ResponseEntity<List<CommentDto>>(list, HttpStatus.OK); // 200
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<List<CommentDto>>(HttpStatus.BAD_REQUEST); // 400
    }
}
