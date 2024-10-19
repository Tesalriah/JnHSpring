package kr.co.jnh.service;

import kr.co.jnh.domain.MailAuthDto;
import kr.co.jnh.domain.User;

import java.util.Date;
import java.util.Map;

public interface UserService {
    int addUser(User user) throws Exception;

    User showUser(Map map) throws Exception;

    Integer getGrade(String id) throws Exception;

    Integer getStatus(String id) throws Exception;

    String findEmail(String id) throws Exception;

    String findId(String email) throws Exception;

    String findName(String email) throws Exception;

    boolean checkBirth(String id, Date birth) throws Exception;

    int changePwd(String id, String pwd) throws Exception;

    boolean idDupl(String id) throws Exception;

    boolean emailDupl(String email) throws Exception;

    int emailAuth(MailAuthDto mailAuthDto, String id) throws Exception;

    String emailAuth(MailAuthDto mailAuthDto) throws Exception;

    User getUser(String id) throws Exception;
}
