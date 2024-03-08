package kr.co.jnh.controller;

import kr.co.jnh.dao.UserDao;
import kr.co.jnh.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.*;
import java.net.http.HttpRequest;
import java.util.HashMap;
import java.util.Map;

@Controller
public class LoginContoller {

    @Autowired
    UserDao userDao;

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        // 로그아웃 세션 비우기
        session.invalidate();
        return "redirect:/";
    }

    @GetMapping("/login")
    public String LoginForm(HttpServletRequest request){
        // 이전페이지를 받아서 파라미터로 넘겨주기
        String prevPage = request.getHeader("Referer");
        request.setAttribute("prevPage", prevPage);
        return "login";
    }

    @GetMapping("findId")
    public String findId(){
        return "/findId";
    }

    @GetMapping("fintpwd")
    public String findpwd(){
        return "/findId";
    }

    @PostMapping("/login")
    public String Login(String id, String pwd, Model m, String prevPage, boolean rememberId,
                        HttpServletRequest request, HttpServletResponse response, RedirectAttributes rattb){
        Map map = new HashMap();
        map.put("id",id);
        map.put("pwd",pwd);

        // 로그인 실패
        if(!loginCheck(map)){
            rattb.addFlashAttribute("msg", "LOGIN_FAIL");
            return "redirect:/login";
        }
        try {
            // 정지된 유저, 탈퇴 유저 확인
            if(userDao.selectUser(map).getStatus() == 1){
                rattb.addFlashAttribute("msg", "SANCTIONED_USER");
                return "redirect:/login";
            }if(userDao.selectUser(map).getStatus() == 2){
                rattb.addFlashAttribute("msg", "WITHDREW_USER");
                return "redirect:/login";
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

    private boolean loginCheck(Map map) {
        User user = null;

        try {
            user = userDao.selectUser(map);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        return user!=null && user.getUser_pwd().equals(map.get("pwd"));
    }
}
