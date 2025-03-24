package kr.co.jnh.dao;

import kr.co.jnh.domain.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml"})
public class UserDaoImplTest {
    @Autowired
    private UserDao userdao;

    @Test
    public void selectUser() throws Exception{
//        Map map = new HashMap();
//        map.put("id", "asdf");
//        map.put("pwd", "1234");

        User user = userdao.selectUser("asdf");
        System.out.println("user.toString() = " + user.toString());
    }

    @Test
    public void updateUser() throws Exception{
        Map map = new HashMap();
        map.put("user_id", "zxcv");
        map.put("user_pwd", "1234");

        int result = userdao.update(map);
        System.out.println(result);
    }

    @Test
    public void select() throws Exception{
        User user = new User("zxcv","1234","김유신","rladbtls12@naver.com", "01000000000", "서울시 성북구", "남성", new Date());

        userdao.insert(user);
    }


    private JavaMailSender mailSender;


    @Test
    public void sendEmail() throws Exception {

        MimeMessage mimeMessage = mailSender.createMimeMessage();
        // MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
        MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, true, "UTF-8");

        //test용 메일 내용
        messageHelper.setFrom("proella8@gmail.com"); // 보내는사람 이메일 여기선 google 메일서버 사용하는 아이디를 작성하면됨
        messageHelper.setTo("shopjnhmall@naver.com"); // 받는사람 이메일
        messageHelper.setSubject("[티켓예약] 안녕하세요 ORDER BY TICKET 입니다" ); // 메일제목
        //messageHelper.setText("예약하신 회원님은"+ name + last_name + "입니다."+" 연락처는 " + phone + "입니다. 입력한 Email은 " + email + "입니다."); // 메일 내용
        //messageHelper.setText("text/html","<div style='border: 3px solid blue'><a href='https://www.naver.com/'>message</a></div>");
        //로그인 폼 테스트HTML ->//messageHelper.setText("text/html","<html> <head> <meta name=\"viewport\" content=\"width=device-width, height=device-height, minimum-scale=1.0, maximum-scale=1.0, initial-scale=1.0\"> </head> <body> <header> <h2>Login</h2> </header> <form action=\"\" method=\"POST\"> <div class=\"input-box\"> <input id=\"username\" type=\"text\" name=\"username\" placeholder=\"아이디\"> <label for=\"username\">아이디</label> </div> <div class=\"input-box\"> <input id=\"password\" type=\"password\" name=\"password\" placeholder=\"비밀번호\"> <label for=\"password\">비밀번호</label> </div> <div id=\"forgot\">비밀번호 찾기</div> <input type=\"submit\" value=\"로그인\"> </form> </body> </html>");

        mailSender.send(mimeMessage);
    }
}