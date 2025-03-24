package kr.co.jnh.controller;


import kr.co.jnh.domain.User;
import kr.co.jnh.service.UserService;
import kr.co.jnh.util.CacheControlUtil;
import kr.co.jnh.util.SessionIdUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("mypage/user")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping
    public String user(HttpServletRequest request, HttpServletResponse response, Model m) {
        // 캐싱 방지 헤더
        CacheControlUtil.setNoCacheHeaders(response);

        String id = SessionIdUtil.getSessionId(request);
        m.addAttribute("id", id);
        return "mypage/check-pwd";
    }

    @PostMapping("check-pwd")
    public String checkPwd(@RequestParam String user_pwd, HttpServletRequest request, Model m) {
        String id = SessionIdUtil.getSessionId(request);

        Map<String, String> map = new HashMap<>();
        map.put("id", id);
        map.put("pwd", user_pwd);
        try {
            // 비밀번호 확인
            if (!userService.loginCheck(map)) {
                throw new Exception("DOES_NOT_MATCH");
            }
            // 비밀번호 일치할시 조건을 세션에 저장
            HttpSession session = request.getSession();
            session.setAttribute("passwordVerified", true);
            return "redirect:info";
        } catch (Exception e) {
            e.printStackTrace();
            m.addAttribute("msg", "비밀번호가 일치하지 않습니다.");
            m.addAttribute("url", "/jnh/mypage/user");
            return "alert";
        }
    }

    @GetMapping("info")
    public String userinfo(HttpServletRequest request, HttpServletResponse response, Model m){
        // 캐싱 방지 헤더
        CacheControlUtil.setNoCacheHeaders(response);

        String id = SessionIdUtil.getSessionId(request);
        HttpSession session = request.getSession(false);
        try {
            // 비밀번호 확인에서 확인된 정보가 세션에 있을시에만 처리
            if(session == null || !(boolean)session.getAttribute("passwordVerified")){
                throw new Exception("WRONG_APPROACH");
            }
            User user = userService.getUser(id);
            if(user == null){
                throw new Exception("WRONG_APPROACH");
            }
            // 비밀번호 확인 조건을 재사용 되지않기 위해 세션에서 제거
            session.removeAttribute("passwordVerified");
            m.addAttribute("user", user);
            return "mypage/user-info";
        } catch (Exception e) {
            e.printStackTrace();
            m.addAttribute("msg", "잘못된 접근입니다.");
            m.addAttribute("url", "/jnh");
            return "alert";
        }
    }

    @PostMapping("change-address")
    public String changeAddress(@RequestParam String address,@RequestParam(required = false) String address2, HttpServletRequest request, Model m){
        HttpSession session = request.getSession(false);

        String id = SessionIdUtil.getSessionId(request);
        String c_address = address;
        if(!address2.equals("")){ // 상세주소를 입력했을시 추가
            if(c_address.charAt(c_address.length() - 1) != ' '){
                c_address += " ";
            }
            c_address += address2;
        }
        try{
            // info로 돌아가기 위해 확인 정보를 세션에 저장
            session.setAttribute("passwordVerified", true);
            if(userService.changeAddress(id, c_address) != 1){
                throw new Exception("ADDRESS_CHANGE_FAIL");
            }
            m.addAttribute("msg", "주소가 변경되었습니다.");
        }catch (Exception e){
            e.printStackTrace();
            m.addAttribute("msg", "변경에 실패했습니다.");
        }
        m.addAttribute("url", "info");
        return "alert";
    }

    @PostMapping("change-pwd")
    public String changePwd(String user_pwd, String new_pwd, String new_pwd_check, HttpServletRequest request, Model m){
        HttpSession session = request.getSession(false);
        String id = SessionIdUtil.getSessionId(request);

        // id, pwd 확인에 필요한 map 생성
        Map<String, String> map = new HashMap();
        map.put("id", id);
        map.put("pwd", user_pwd);

        try{
            // 변경 비밀번호, 비밀번호 확인 체크
            if(!new_pwd.equals(new_pwd_check)){
                m.addAttribute("msg","두 비밀번호가 일치하지 않습니다.");
                throw new Exception("CHECK_DOES_NOT_MATCH");
            }
            // 현재 비밀번호 확인
            if(!userService.loginCheck(map)){
                m.addAttribute("msg","현재 비밀번호가 일치하지 않습니다.");
                throw new Exception("DOES_NOT_MATCH");
            }
            if(userService.changePassword(id, new_pwd) != 1){
                m.addAttribute("비밀번호 변경에 실패앴습니다.");
                throw new Exception("PWD_CHANGE_FAIL");
            }
            m.addAttribute("msg", "비밀번호가 변경되었습니다.");
            m.addAttribute("url", "/jnh");
        }catch (Exception e){
            e.printStackTrace();
            session.setAttribute("passwordVerified", true);
            m.addAttribute("url","info");
        }
        return "alert";
    }

    @PostMapping("del-account")
    public String delAccount(HttpServletRequest request, HttpSession session, Model m){
        String id = SessionIdUtil.getSessionId(request);

        try {
            if(userService.changeStatus(id, 2) != 1){
                m.addAttribute("회원탈퇴에 실패했습니다. 다시 시도해주세요.");
                throw new Exception("WITHDRAWAL_FAIL");
            }
            m.addAttribute("msg", "회원탈퇴 처리되었습니다.");
            m.addAttribute("url", "/jnh");
            session.invalidate();
        } catch (Exception e) {
            e.printStackTrace();
            session.setAttribute("passwordVerified", true);
            m.addAttribute("url","info");
        }
        return "alert";
    }
}
