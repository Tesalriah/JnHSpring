package kr.co.jnh.service;

import kr.co.jnh.domain.MailAuthDto;
import kr.co.jnh.domain.MailDto;

public interface EmailService {
    public void sendMail(MailDto mailDto);

    Integer addAuth(MailAuthDto mailAuthDto) throws Exception;

    Integer removeAuth(String email) throws Exception;
}
