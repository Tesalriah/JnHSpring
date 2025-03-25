package kr.co.jnh.controller;

import kr.co.jnh.domain.MailAuthDto;
import kr.co.jnh.domain.MailDto;
import kr.co.jnh.domain.User;
import kr.co.jnh.service.EmailService;
import kr.co.jnh.service.UserService;
import kr.co.jnh.util.CacheControlUtil;
import kr.co.jnh.util.SessionIdUtil;
import kr.co.jnh.util.mailAuthUtil;
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
public class LoginController {

    @Autowired
    UserService userService;

    @Autowired
    EmailService emailService;

    // 로그아웃
    @GetMapping("/logout")
    public String logout(HttpSession session, HttpServletRequest request) {
        // 로그아웃 세션 비우기
        session.invalidate();
        // 이전페이지가 있을 경우 이전페이지로 리다이렉트
        String referer = request.getHeader("referer");
        System.out.println("referer = " + referer);
        if(referer.contains("mypage")){
            return "redirect:/";
        }
        if(referer != null || !referer.equals("")){
            return "redirect:"+referer;
        }
        return "redirect:/";
    }

    // 아이디 찾기 페이지 요청
    @GetMapping("/find-id")
    public String findId(HttpServletRequest request){
        // 로그인 했을 시 메인페이지로
        if(SessionIdUtil.getSessionId(request) != null){
            return "redirect:/";
        }
        return "find-id";
    }

    // 아이디 찾기
    @PostMapping("/find-id")
    public String findIdAuth(MailAuthDto mailAuthDto, HttpServletRequest request, Model m){
        try {
            String id = userService.emailAuth(mailAuthDto); // 인증번호가 일치하면 해당 id값 반환
            if(id.isBlank()){ // 반환된 id가 없으면 Exception 발생
                throw new Exception("Wrong approach");
            }
            HttpSession session = request.getSession();
            session.setAttribute("msg", "회원님의 ID는 " + id + " 입니다.");
            return "redirect:/jnh/login";
        } catch (Exception e) { // 인증 실패시 원래 페이지로 이동
            e.printStackTrace();
            m.addAttribute("name", request.getParameter("name"));
            m.addAttribute("email", mailAuthDto.getEmail());
            m.addAttribute("msg","AUTH_FAIL");
            return "account/find-id";
        }
    }

    // 아이디 찾기 전 인증번호 전송
    @ResponseBody
    @PostMapping("/id-auth")
    public Map<String,Object> idAuth(@RequestBody Map<String,Object> map){
        String name = (String)map.get("user_name");
        String email = (String)map.get("email");

        Integer authNumber = makeRandomNumber(); // 랜덤한 6자리 인증번호 생성
        MailAuthDto mailAuthDto = new MailAuthDto(email, authNumber+""); // 이메일인증 데이터베이스에 저장하기위해 할당

        try {
            String foundName = userService.findName(email); // 이메일을 토대로 이름 가져오기
            // 입력한 이름과 해당 이메일 사용자의 이름이 일치하면 실행
            if(foundName.equals(name)){
                emailService.addAuth(mailAuthDto); // 데이터베이스에 인증번호와 요청 이메일을 저장
                MailDto mailDto = new MailDto(email, mailAuthUtil.customMsg(authNumber)); // 이메일 수신자, 내용(인증번호)을 할당
                emailService.sendMail(mailDto); // 이메일 전송
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

    // 비밀번호 찾기 페이지 요청
    @GetMapping("/find-pwd")
    public String findPwd(HttpServletRequest request){
        // 로그인 했을 시 메인페이지로
        if(SessionIdUtil.getSessionId(request) != null){
            return "redirect:/";
        }
        return "account/find-pwd";
    }

    // 비밀번호 찾기
    @PostMapping("/find-pwd")
    public String postFindPwd(MailAuthDto mailAuthDto, @RequestParam(required = false) String inputId, HttpSession session, Model m) {

        try {
            String id = userService.emailAuth(mailAuthDto); // 인증번호가 일치하면 해당 id값 반환
            if (id.isBlank()) { // 반환된 id가 없으면 Exception 발생
                throw new Exception("Wrong approach");
            }
            if (id.equals(inputId)) { // 입력한 id와 인증을 통해 받은 id값이 일치한 경우 실행
                // 세션에 해당 id를 저장하여 넘겨주기 (세션에 주는 이유는 보안을 높이기 위해 )
                session.setAttribute("changePwdID", id);
                return "redirect:/change-pwd"; // 비밀번호 변경으로 이동
            }else {
                throw new Exception("AUTH_FAIL");
            }
        } catch (Exception e) { // 인증 실패시 원래 페이지로 이동
            e.printStackTrace();
            m.addAttribute("id", inputId);
            m.addAttribute("email", mailAuthDto.getEmail());
            session.setAttribute("msg", "잘못된 인증번호입니다. 다시 입력해주세요.");
            return "account/find-pwd";
        }
    }

    // 비밀번호 찾기 전 인증번호 전송
    @ResponseBody
    @PostMapping("/pwd-auth")
    public Map<String,Object> pwdAuth(@RequestBody Map<String, Object> map){
        String id = (String)map.get("user_id");
        String email = (String)map.get("email");

        Integer authNumber = makeRandomNumber(); // 랜덤한 6자리 인증번호 생성
        MailAuthDto mailAuthDto = new MailAuthDto(email, authNumber+""); // 이메일에 인증번호 전송하기 위해 할당

        try {
            String foundId = userService.findId(email); // 이메일을 토대로 id 가져오기
            // 입력한 id와 해당 이메일 사용자의 id가 일치하면 실행
            if(foundId.equals(id)){
                emailService.addAuth(mailAuthDto); // 데이터베이스에 인증번호와 요청 이메일을 저장
                MailDto mailDto = new MailDto(email, mailAuthUtil.customMsg(authNumber)); // 이메일 수신자, 내용(인증번호)을 할당
                emailService.sendMail(mailDto); // 이메일 전송
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

    // 비밀번호 찾기 성공 시 이동되는 비밀번호 변경 페이지
    @GetMapping("/change-pwd")
    public String changePwd(HttpServletRequest request){
        // 로그인했을 시 메인페이지로
        if(SessionIdUtil.getSessionId(request) != null){
            return "redirect:/";
        }

        // 비밀번호 찾기에서 세션에 등록한 id값이 없을 시 메인페이지로 보내기
        HttpSession session = request.getSession();
        String id = (String)session.getAttribute("changePwdID");
        if(id == null || id.equals("")){
            session.setAttribute("msg", "잘못된 시도입니다.");
            return "redirect:/";
        }
        return "account/change-pwd";
    }

    // 비밀번호 변경
    @PostMapping("/change-pwd")
    public String changePwd(HttpSession session, @RequestParam(required = false) String pwd, @RequestParam(required = false) String checkPwd,@RequestParam(required = false)String birth, Model m){
        // 비밀번호 찾기에서 저장한 changePwdID 값 가져오기
        String id = (String)session.getAttribute("changePwdID");
        // input 받은 값들 저장
        m.addAttribute("birth",birth);

        if(!pwd.equals(checkPwd)){ // 비밀번호, 비밀번호 확인 일치하는지 체크
            session.setAttribute("msg", "비밀번호 확인이 일치하지 않습니다.");
            return "account/change-pwd";
        }
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        try {
            // 입력받은 생년월일과 해당id의 생년월일이 일치하는지 확인
            Date birthToDate = formatter.parse(birth);
            if(!userService.checkBirth(id, birthToDate)){
                session.setAttribute("msg", "생년월일이 일치하지 않습니다.");
                return "account/change-pwd";
            };
            // 생년월일이 일치했을경우 비밀번호 변경
            userService.changePwd(id, pwd);
            session.removeAttribute("changePwdID");
            session.setAttribute("msg", "비밀번호가 변경되었습니다.");
            return "redirect:/login";
        } catch (Exception e) {
            e.printStackTrace();
            session.setAttribute("msg", "변경에 실패하였습니다.");
            return "account/change-pwd";
        }
    }

    // 로그인 페이지 요청
    @GetMapping("/login")
    public String loginForm(HttpServletRequest request, HttpServletResponse response) {
        CacheControlUtil.setNoCacheHeaders(response);

        HttpSession session = request.getSession();
        String id = SessionIdUtil.getSessionId(request);
        if(id == null){ // 로그인하지 않았을때만 보여주기
            // 이전페이지가 없을 시 받아서 세션으로 넘겨주기
            if(session.getAttribute("prevPage") == null){
                String prevPage = request.getHeader("Referer");
                session.setAttribute("prevPage", prevPage);
            }
            return "account/login";
        }
        return "redirect:/";
    }

    // 로그인 처리
    @PostMapping("/login")
    public String login(String id, String pwd,@SessionAttribute(name = "prevPage", required = false) String prevPage, boolean rememberId,
                        HttpServletRequest request, HttpServletResponse response, RedirectAttributes rattb){
        Map<String, String> map = new HashMap<>();
        map.put("id",id);
        map.put("pwd",pwd);
        HttpSession session = request.getSession();

        User user = null;

        try {
            // pwd가 일치하는지 확인
            if(!userService.loginCheck(map)){
                throw new Exception("LOGIN_FAIL");
            }
            user = userService.getUser(id);
            Integer status = user.getStatus();
            // 정지된 유저, 탈퇴 유저, 이메일 미인증 유저 확인
            if (status != null) {
                if(status == 1){
                    session.setAttribute("msg", "제재된 사용자입니다. 고객센터에 문의해주세요.");
                    return "redirect:/login";
                }if(status == 2){
                    session.setAttribute("msg", "탈퇴한 사용자입니다. 고객센터에 문의해주세요.");
                    return "redirect:/login";
                }if(status == 3){
                    session.setAttribute("user", user);
                    return "redirect:/email-auth";
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            session.setAttribute("msg", "로그인에 실패하였습니다. 아이디와 비밀번호를 확인해주세요.");
            return "redirect:/login";
        }
        // 페이지에서 필요한 유저 정보 세션에 할당 (로그인 처리)
        session.setAttribute("user", user);
        session.removeAttribute("prevPage");

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

        // 세션에서 넘겨받은 이전페이지가 있을시 이전페이지로 리다이렉트
        prevPage = prevPage == null || prevPage.equals("") ? "/" : prevPage;
        return "redirect:"+prevPage;
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
