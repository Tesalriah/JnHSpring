package kr.co.jnh.controller;

import kr.co.jnh.domain.MailAuthDto;
import kr.co.jnh.domain.MailDto;
import kr.co.jnh.domain.SearchCondition;
import kr.co.jnh.domain.User;
import kr.co.jnh.service.EmailService;
import kr.co.jnh.service.UserService;
import kr.co.jnh.util.SessionIdUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
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
    public String findId(HttpSession session){
        if(sessionCheck(session)){
            return "redirect:/";
        }
        return "account/findId";
    }

    @PostMapping("/findId")
    public String findIdAuth(MailAuthDto mailAuthDto, HttpServletRequest request, Model m){
        try {
            String id = userService.emailAuth(mailAuthDto);
            if(id.isBlank()){
                throw new Exception("Wrong approach");
            }
            request.setAttribute("msg", "회원님의 ID는 " + id + " 입니다.");
            request.setAttribute("url", "/jnh/login");
            return "alert";
        } catch (Exception e) {
            e.printStackTrace();
            m.addAttribute("name", request.getParameter("name"));
            m.addAttribute("email", mailAuthDto.getEmail());
            m.addAttribute("msg","AUTH_FAIL");
            return "account/findId";
        }
    }

    @ResponseBody
    @PostMapping("/idAuth")
    public Map idauth(@RequestBody User user){
        String name = user.getUser_name();
        String email = user.getEmail();
        Map map = new HashMap();

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
    public String findpwd(HttpSession session){
        if(sessionCheck(session)){
            return "redirect:/";
        }
        return "account/findPwd";
    }

    @PostMapping("/findPwd")
    public String postFindPwd(MailAuthDto mailAuthDto, HttpServletRequest request, Model m){
        String inputId = request.getParameter("id");
        try {
            String id = userService.emailAuth(mailAuthDto);
            if(id.isBlank()){
                throw new Exception("Wrong approach");
            }
            if(id.equals(inputId)){
               HttpSession session = request.getSession();
               session.setAttribute("changePwdID", id);
            }
            return "redirect:/changePwd";
        } catch (Exception e) {
            e.printStackTrace();
            m.addAttribute("id", inputId);
            m.addAttribute("email", mailAuthDto.getEmail());
            m.addAttribute("msg", "AUTH_FAIL");
            return "account/findPwd";
        }
    }

    @ResponseBody
    @PostMapping("/pwdAuth")
    public Map pwdAuth(@RequestBody User user){
        String id = user.getUser_id();
        String email = user.getEmail();
        Map map = new HashMap();

        Integer authNumber = makeRandomNumber();
        MailAuthDto mailAuthDto = new MailAuthDto(email, authNumber+"");

        try {
            String foundId = userService.findId(email);
            if(foundId.equals(id)){
                emailService.addAuth(mailAuthDto);
                MailDto mailDto = new MailDto(email, authNumber+"");
                emailService.sendMail(mailDto);
                map.put("msg", "전송완료");
                return map;
            }
            map.put("msg", "아이디와 이메일이 일치하지 않습니다.");
            return map;
        } catch (Exception e) {
            e.printStackTrace();
            map.put("msg", "이메일 전송 실패");
            return map;
        }
    }

    @GetMapping("/changePwd")
    public String changePwd(HttpSession session){
        if(sessionCheck(session)){
            return "redirect:/";
        }
        return "account/changePwd";
    }

    @PostMapping("/changePwd")
    public String PostChangePwd(HttpServletRequest request, Model m){
        HttpSession session = request.getSession();
        String id = (String)session.getAttribute("changePwdID");
        String pwd = request.getParameter("new_pwd");
        String checkPwd = request.getParameter("check_new_pwd");
        String birth = request.getParameter("birth");
        m.addAttribute("birth",birth);

        if(!pwd.equals(checkPwd)){
            m.addAttribute("msg", "NOT_MATCH_PWD");
            return "account/changePwd";
        }
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date birthToDate = formatter.parse(birth);
            if(!userService.checkBirth(id, birthToDate)){
                m.addAttribute("msg", "NOT_MATCH_BIRTH");
                return "account/changePwd";
            };
            userService.changePwd(id, pwd);
            m.addAttribute("msg","CHANGED_PWD");
            session.invalidate();
            return "redirect:/login";
        } catch (Exception e) {
            e.printStackTrace();
            m.addAttribute("msg", "CHANGE_FAIL");
            return "account/changePwd";
        }
    }

    @GetMapping("/login")
    public String LoginForm(HttpServletRequest request) {
        String id = SessionIdUtil.getSessionId(request);
        if( id == null || id.equals("")){
            // 이전페이지를 받아서 파라미터로 넘겨주기
            if(request.getParameter("prevPage") == null){
                String prevPage = request.getHeader("Referer");
                if(request.getParameter("product_id") != null){
                    prevPage += "&product_id=" + request.getParameter("product_id");
                }
                request.setAttribute("prevPage", prevPage);
            }
            return "account/login";
        }
        return "redirect:/";
    }

    @PostMapping("/login")
    public String Login(String id, String pwd, String prevPage, boolean rememberId,
                        HttpServletRequest request, HttpServletResponse response, RedirectAttributes rattb){
        Integer status = null;
        Map map = new HashMap();
        map.put("id",id);
        map.put("pwd",pwd);
        HttpSession session = request.getSession(false);

        try {
            User user = userService.showUser(map);
            // 로그인 실패시
            if(!loginCheck(user, map)){
                rattb.addFlashAttribute("msg", "LOGIN_FAIL");
                rattb.addFlashAttribute("prevPage", prevPage);
                return "redirect:/login";
            }
            status = user.getStatus();
            // 정지된 유저, 탈퇴 유저, 이메일 미인증 유저 확인
            if (status != null) {
                if(status == 1){
                    rattb.addFlashAttribute("msg", "SANCTIONED_USER");
                    rattb.addFlashAttribute("prevPage", prevPage);
                    return "redirect:/login";
                }if(status == 2){
                    rattb.addFlashAttribute("msg", "WITHDREW_USER");
                    rattb.addFlashAttribute("prevPage", prevPage);
                    return "redirect:/login";
                }if(status == 3){
                    session.setAttribute("id", id);
                    return "redirect:/emailAuth";
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            Integer grade = userService.getGrade(id);
            if(grade != null){
                session.setAttribute("grade", grade);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 로그인 처리
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
        // 세션에 따로 저장된 이전페이지가 있을 시 그곳으로 리다이렉트
        if(session != null && session.getAttribute("prevPage") != null){
            prevPage = (String)session.getAttribute("prevPage");
            session.removeAttribute("prevPage");
        }
        if(status != 0 || status != null){
            session.setAttribute("status", status);
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

    private boolean sessionCheck(HttpSession session){
        return session.getAttribute("id") != null;
    }
}
