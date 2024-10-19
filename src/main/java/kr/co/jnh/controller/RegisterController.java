package kr.co.jnh.controller;

import kr.co.jnh.validation.UserValidator;
import kr.co.jnh.domain.MailAuthDto;
import kr.co.jnh.domain.MailDto;
import kr.co.jnh.domain.User;
import kr.co.jnh.service.EmailService;
import kr.co.jnh.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.Random;
import java.util.regex.Pattern;

@Controller
public class RegisterController {

    @Inject
    EmailService emailService;

    @Autowired
    UserService userService;

    @InitBinder("user")
    public void toDate(WebDataBinder binder) {
        binder.setValidator(new UserValidator()); // UserValidator를 WebDataBinder의 로컬 validator로 등록
    }

    // 약관동의
    @GetMapping("/register")
    public String register(){
        return "account/terms";
    }

    // 회원가입 Get
    @GetMapping("/signUp")
    public String signUp(){
        return "account/signUp";
    }

    // 이메일 인증코드 발송
    @GetMapping("/emailAuth")
    public String mailAuth(@ModelAttribute("email") String email, HttpServletRequest request, Model m, RedirectAttributes rattb){
        String id = getSessionId(request);

        // 랜덤 6자리 인증번호 발급
        Integer authNumber = makeRandomNumber();
        MailAuthDto mailAuthDto = null;

        try {
            // post로 받아온 이메일 값이 없을때 (회원가입을 통해 경로로 들어오지 않았을떄 ) 세션 아이디에서 이메일값 받아오기
            if(email != null || email.isEmpty()){
                email = userService.findEmail(id);
                m.addAttribute("email", email);
            }
            // 현재 페이지에서 재요청시 이메일을 다시 발송하지 않게 처리
            String prevPage = request.getHeader("Referer");
            System.out.println("prevPage = " + prevPage);
            if(prevPage != null){
                if(prevPage.contains("emailAuth")){
                    m.addAttribute("msg", "CHECK_EMAIL");
                    return "account/emailAuth";
                }
            }
            // 이메일, 인증번호 db에 저장
            mailAuthDto = new MailAuthDto(email, authNumber+"");
            if(emailService.addAuth(mailAuthDto) != 1){
                throw new Exception("AUTH_ADD_FAIL");
            }
            // 이메일 전송
            MailDto mailDto = new MailDto(email, authNumber+"");
            emailService.sendMail(mailDto); // dto (메일관련 정보)를 sendMail에 저장함
            rattb.addFlashAttribute("email", email);
            return "account/emailAuth";
        } catch (Exception e) {
            e.printStackTrace();
            m.addAttribute("message", "SEND_FAIL"); // 이메일 발송이 실패되었다는 메시지를 출력
            return "redirect:/";
        }
    }

    @PostMapping("/emailAuth")
    public String auth(HttpServletRequest request, Model m, RedirectAttributes rattb){
        String id = getSessionId(request);
        String email = "";

        try {
            email = userService.findEmail(id);
            String authNumber = request.getParameter("auth_num");

            if(userService.emailAuth(new MailAuthDto(email, authNumber), id) != 1){
                throw new Exception("Auth Fail");
            }
        } catch (Exception e) {
            e.printStackTrace();
            m.addAttribute("msg", "AUTH_FAIL");
            return "redirect:/emailAuth";
        }
        rattb.addFlashAttribute("msg","REG_OK");
        return "redirect:/";
    }


    // 회원가입 Post
    @PostMapping("/signUp")
    public String signUp(@Valid User user, BindingResult result, HttpServletRequest request, Model m, RedirectAttributes rattb, HttpSession session){
        // 따로 받은 주소 값 합치기
        String address = request.getParameter("address1") + request.getParameter("address2");
        user.setAddress(address);

        // 기타 검증
        userValidation(user, result, request);

        try {
            // 아이디 중복확인
            if(userService.idDupl(user.getUser_id())){
                result.rejectValue("user_id", "duplicate");
            }
            if(userService.emailDupl(user.getEmail())){
                result.rejectValue("email", "duplicate");
            }
            // 검증에러 있을시 예외처리
            if(result.hasErrors()) {
                throw new Exception("Validation Error");
            }
            // 회원가입 실패시
            if(userService.addUser(user) != 1){
                throw new Exception("Register Fail");
            }
            // 성공
            session.setAttribute("id", user.getUser_id());
            rattb.addFlashAttribute("email" ,user.getEmail());
            return "account/emailAuth";
        } catch (Exception e) {
            e.printStackTrace();
            // 실패시 원래 페이지에 생년월일 값을 반환받기 위함
            if(user.getBirth()!= null){
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                m.addAttribute("birth", formatter.format(user.getBirth()));
            }
            m.addAttribute("msg", "REG_ERR");
            // 실패시 원래 페이지에 user객체와 주소값 반환
            m.addAttribute("user", user);
            m.addAttribute("address1", request.getParameter("address1"));
            m.addAttribute("address2", request.getParameter("address2"));
            return "account/signUp";
        }
    }

    // 기타 검증 처리
    private void userValidation(User user, BindingResult result,HttpServletRequest request){
        // 비밀번호, 비밀번호 확인 같은지 검증
        if(!user.getUser_pwd().equals((String)request.getParameter("pwd_check"))){
            result.rejectValue("user_pwd", "notLikePwd");
        }
        // 주소 값 비어있는지 검증
        if(user.getAddress() != null &&  user.getAddress().isEmpty()){
            result.rejectValue("address", "required");
        }

        // 주소 값 한글, 영문, 숫자, 대쉬(-)만 가능
        String addressPattern = "^[가-힣0-9a-zA-Z() -]*$";

        if(!Pattern.matches(addressPattern, user.getAddress())){
            result.rejectValue("address", "adressPattern");
        }
    }

    public String getSessionId(HttpServletRequest request){
        HttpSession session =  request.getSession();
        return (String)session.getAttribute("id");
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
