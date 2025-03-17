package kr.co.jnh.service;

import kr.co.jnh.dao.EmailAuthDao;
import kr.co.jnh.dao.EmailAuthDaoImpl;
import kr.co.jnh.domain.MailAuthDto;
import kr.co.jnh.domain.MailDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;

@Service
public class EmailServiceImpl implements EmailService {

    @Inject
    JavaMailSender mailSender;

    @Autowired
    EmailAuthDao emailAuthDao;

    @Override
    public void sendMail(MailDto mailDto){
        try {
            // 이메일 객체 생성
            MimeMessage msg = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(msg, true, "utf-8");

            // 수신자 설정
            helper.setTo(mailDto.getReceiveMail());

            // 발신자 설정
            helper.setFrom(mailDto.getSenderMail(), mailDto.getSenderName());

            // 제목 설정
            helper.setSubject(mailDto.getSubject());

            // 본문 설정 (HTML 적용)
            helper.setText(mailDto.getMessage(), true); // true를 설정하면 HTML 적용됨

            // 이메일 보내기
            mailSender.send(msg);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int addAuth(MailAuthDto mailAuthDto) throws Exception{
        if(emailAuthDao.selectAuthNum(mailAuthDto.getEmail()) != null){
            emailAuthDao.deleteAuth(mailAuthDto.getEmail());
        }
        return emailAuthDao.insertAuth(mailAuthDto);
    }

    @Override
    public int removeAuth(String email) throws Exception{
        return emailAuthDao.deleteAuth(email);
    }
}