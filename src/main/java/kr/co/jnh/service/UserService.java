package kr.co.jnh.service;

import kr.co.jnh.domain.MailAuthDto;
import kr.co.jnh.domain.User;

import java.util.Map;

public interface UserService {
    int addUser(User user) throws Exception;

    User showUser(Map map) throws Exception;

    String findEmail(String id) throws Exception;

    boolean idDupl(String id) throws Exception;

    int emailAuth(MailAuthDto mailAuthDto, String id) throws Exception;
}
