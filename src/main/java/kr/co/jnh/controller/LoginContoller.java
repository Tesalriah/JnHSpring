package kr.co.jnh.controller;

import kr.co.jnh.dao.UserDao;
import kr.co.jnh.domain.MailAuthDto;
import kr.co.jnh.domain.MailDto;
import kr.co.jnh.domain.User;
import kr.co.jnh.service.EmailService;
import kr.co.jnh.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.*;
import java.net.http.HttpRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

@Controller
public class LoginContoller {

    @Autowired
    UserService userService;

    @Autowired
    EmailService emailService;

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        // 로그아웃 세션 비우기
        session.invalidate();
        return "redirect:/";
    }

    @GetMapping("/findId")
    public String findId(){return "findId";}

    @ResponseBody
    @PostMapping("/findId")
    public Map findIdAuth(@RequestBody MailAuthDto mailAuthDto){
        Map map = new HashMap();
        try {
            String id = userService.emailAuth(mailAuthDto);
            if(id!=null){
                map.put("id", id);
            }
        } catch (Exception e) {
            e.printStackTrace();
            map.put("msg", "잘못된 인증번호입니다. 다시 입력해주세요.");
        }
        return map;
    }

    @ResponseBody
    @PostMapping("/idauth")
    public Map idauth(@RequestBody User user){
        String name = user.getUser_name();
        String email = user.getEmail();
        Map map = new HashMap();

        System.out.println("name = " + name);
        System.out.println("email = " + email);
        Integer authNumber = makeRandomNumber();
        MailAuthDto mailAuthDto = new MailAuthDto(email, authNumber+"");

        try {
            String foundName = userService.findName(email);
            if(foundName.equals(name)){
                emailService.addAuth(mailAuthDto);
                MailDto mailDto = new MailDto(email, authNumber+"");
                emailService.sendMail(mailDto);
                map.put("msg", "전송완료");
                return map;
            }
            map.put("msg", "이름과 이메일이 일치하지 않습니다.");
            return map;
        } catch (Exception e) {
            e.printStackTrace();
            map.put("msg", "이메일 전송 실패");
            return map;
        }
    }

    @GetMapping("/findPwd")
    public String findpwd(){return "findPwd";}

    @GetMapping("/login")
    public String LoginForm(HttpServletRequest request){
        HttpSession session = request.getSession();
        String id = (String)session.getAttribute("id");
        if( id != null && id.isBlank()){
            return "redirect:/";
        }
        // 이전페이지를 받아서 파라미터로 넘겨주기
        if(request.getParameter("prevPage") == null){
            String prevPage = request.getHeader("Referer");
            request.setAttribute("prevPage", prevPage);
        }
        return "login";
    }

    @PostMapping("/login")
    public String Login(String id, String pwd, Model m, String prevPage, boolean rememberId,
                        HttpServletRequest request, HttpServletResponse response, RedirectAttributes rattb){
        Map map = new HashMap();
        map.put("id",id);
        map.put("pwd",pwd);

        try {
            User user = userService.showUser(map);
            // 로그인 실패시
            if(!loginCheck(user, map)){
                rattb.addFlashAttribute("msg", "LOGIN_FAIL");
                rattb.addFlashAttribute("prevPage", prevPage);
                return "redirect:/login";
            }
            // 정지된 유저, 탈퇴 유저, 이메일 미인증 유저 확인
            if(user.getStatus() == 1){
                rattb.addFlashAttribute("msg", "SANCTIONED_USER");
                rattb.addFlashAttribute("prevPage", prevPage);
                return "redirect:/login";
            }if(user.getStatus() == 2){
                rattb.addFlashAttribute("msg", "WITHDREW_USER");
                rattb.addFlashAttribute("prevPage", prevPage);
                return "redirect:/login";
            }if(user.getStatus() == 3){
                HttpSession session = request.getSession();
                session.setAttribute("id", id);
                return "redirect:/emailAuth";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // 로그인 처리
        HttpSession session = request.getSession();
        session.setAttribute("id", id);
        // 아이디 저장 체크시 아이디를 쿠키에 저장
        if(rememberId) {
            // 쿠키를 생성
            Cookie cookie = new Cookie("id", id);
            // 응답에 저장
            response.addCookie(cookie);
        } else {
            // 쿠키를 삭제
            Cookie cookie = new Cookie("id", id);
            cookie.setMaxAge(0); // 쿠키를 삭제 (만효기간을 0으로해서 삭제됨 )
            // 응답에 저장
            response.addCookie(cookie);
        }
        // 파라미터로 넘겨받은 이전페이지가 있을시 이전페이지로 리다이렉트
        prevPage = prevPage.equals("") || prevPage == null ? "/" : prevPage;
        return "redirect:"+prevPage;
    }

    private boolean loginCheck(User user, Map map) {
        return user!=null && user.getUser_pwd().equals(map.get("pwd"));
    }

    private Integer makeRandomNumber() {
        Random r = new Random();
        String randomNumber = "";
        for(int i = 0; i < 6; i++) {
            randomNumber += Integer.toString(r.nextInt(10));

            if(i == 0 && randomNumber.equals("0")){
                randomNumber = String.valueOf((int)(Math.random()*1+9));
            }
        }

        return Integer.parseInt(randomNumber);
    }
}
