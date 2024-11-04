package kr.co.jnh.dao;

import kr.co.jnh.domain.MailAuthDto;

public interface EmailAuthDao {
    String selectAuthNum(String email) throws Exception;

    int insertAuth(MailAuthDto mailAuth) throws Exception;

    int deleteAuth(String email) throws Exception;
}
