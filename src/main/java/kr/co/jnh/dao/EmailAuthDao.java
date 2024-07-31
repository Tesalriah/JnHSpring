package kr.co.jnh.dao;

import kr.co.jnh.domain.MailAuthDto;

public interface EmailAuthDao {
    String selectAuthNum(String email) throws Exception;

    Integer insertAuth(MailAuthDto mailAuth) throws Exception;

    Integer deleteAuth(String email) throws Exception;
}
