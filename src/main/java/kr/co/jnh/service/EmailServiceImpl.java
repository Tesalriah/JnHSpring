package kr.co.jnh.service;

import kr.co.jnh.dao.EmailAuthDao;
import kr.co.jnh.dao.EmailAuthDaoImpl;
import kr.co.jnh.domain.MailAuthDto;
import kr.co.jnh.domain.MailDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
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
            // 이메일 객체
            MimeMessage msg = mailSender.createMimeMessage();

            // 받는 사람을 설정 (수신자, 받는사람의 이메일 주소 객체를 생성해서 수신자 이메일주소를 담음)
            msg.addRecipient(RecipientType.TO, new InternetAddress(mailDto.getReceiveMail()));

            /*
             * createMimeMessage() : MimeMessage객체를 생성시킴 (이것을 이용해서 메시지를 구성한 뒤 메일 발송)
             * addRecipient() : 메시지의 발신자를 설정 InternetAddress() : 이메일 주소 getReceiveMail() :
             * 수신자 이메일 주소
             */

            // 보내는 사람(이메일주소+이름)
            // (발신자, 보내는 사람의 이메일 주소와 이름을 담음)
            // 이메일 발신자
            msg.addFrom(new InternetAddress[] { new InternetAddress(mailDto.getSenderMail(), mailDto.getSenderName()) });

            // 이메일 제목 (인코딩을 해야 한글이 깨지지 않음)
            msg.setSubject(mailDto.getSubject(), "utf-8");
            // 이메일 본문 (인코딩을 해야 한글이 깨지지 않음)
            msg.setText(mailDto.getMessage(), "utf-8");
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
}
