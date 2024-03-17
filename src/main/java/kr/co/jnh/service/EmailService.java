package kr.co.jnh.service;

import kr.co.jnh.domain.MailAuthDto;
import kr.co.jnh.domain.MailDto;

public interface EmailService {
    void sendMail(MailDto mailDto);

    int addAuth(MailAuthDto mailAuthDto) throws Exception;

}
