package kr.co.jnh.controller;

import kr.co.jnh.util.SessionUtils;
import kr.co.jnh.util.MailAuthUtil;
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
    @PostMapping("/register")
    public String signUp(Model m){
        m.addAttribute("user", new User());
        return "account/signup";
    }

    // 이메일 인증코드 발송
    @GetMapping("/email-auth")
    public String mailAuth(HttpServletRequest request){
        HttpSession session = request.getSession();
        User user = (User)session.getAttribute("user");
        if(user == null || user.getStatus() != 3){
            session.setAttribute("msg", "잘못된 접근입니다.");
            return "redirect:/";
        }

        // 랜덤 6자리 인증번호 발급
        Integer authNumber = makeRandomNumber();
        MailAuthDto mailAuthDto = null;

        try {
            // post로 받아온 이메일 값이 없을때 (회원가입을 통해 경로로 들어오지 않았을떄 ) 세션 아이디에서 이메일값 받아오기
            String email = user.getEmail();
            // 현재 페이지에서 재요청시 이메일을 다시 발송하지 않게 처리
            String prevPage = request.getHeader("Referer");
            if(prevPage != null){
                if(prevPage.contains("email-auth")){
                    session.setAttribute("msg", "이메일을 확인해주세요.");
                    return "account/email-auth";
                }
            }
            // 이메일, 인증번호 db에 저장
            mailAuthDto = new MailAuthDto(email, authNumber+"");
            if(emailService.addAuth(mailAuthDto) != 1){
                throw new Exception("AUTH_ADD_FAIL");
            }
            // 이메일 전송
            MailDto mailDto = new MailDto(email, MailAuthUtil.customMsg(authNumber));
            emailService.sendMail(mailDto); // dto (메일관련 정보)를 sendMail에 저장함
            return "account/email-auth";
        } catch (Exception e) {
            e.printStackTrace();
            session.removeAttribute("user");
            session.setAttribute("msg", "이메일 발송에 실패했습니다."); // 이메일 발송이 실패되었다는 메시지를 출력
            return "redirect:/";
        }
    }

    // 이메일 인증
    @PostMapping("/email-auth")
    public String auth(HttpServletRequest request, HttpSession session){
        String id = SessionUtils.getSessionId(request);

        try {
            String email = userService.getUser(id).getEmail(); // 해당 유저의 이메일값 반환
            String authNumber = request.getParameter("auth_num"); // input으로 넘어온 인증번호

            // 메일과 해당하는 인증번호가 일치하는지 확인 후 서비스 내부에서 인증 완료 시 유저의 status 수정
            if(userService.emailAuth(new MailAuthDto(email, authNumber), id) != 1){
                throw new Exception("AUTH_FAIL");
            }
        } catch (Exception e) {
            e.printStackTrace();
            session.setAttribute("msg", "메일 인증에 실패했습니다.");
            return "redirect:/email-auth";
        }
        session.removeAttribute("user");
        session.setAttribute("msg", "이메일이 인증되었습니다. 다시 로그인해주세요.");
        return "redirect:/";
    }


    // 회원가입 Post
    @PostMapping("/signup")
    public String signUp(@ModelAttribute("user") @Valid User user, BindingResult result, HttpServletRequest request, Model m, HttpSession session){
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
            // 성공하여 db에 저장된 아이디정보를 가져와 로그인처리
            User doneUser =  userService.getUser(user.getUser_id());
            session.setAttribute("user", doneUser);
            return "redirect:/email-auth";
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
            return "account/signup";
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
