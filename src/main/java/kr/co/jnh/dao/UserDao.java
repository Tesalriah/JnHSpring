package kr.co.jnh.dao;

import kr.co.jnh.domain.User;

import java.util.Map;

public interface UserDao {
    User selectUser(Map map) throws Exception;

}
