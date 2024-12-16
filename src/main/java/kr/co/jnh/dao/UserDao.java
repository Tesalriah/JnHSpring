package kr.co.jnh.dao;

import kr.co.jnh.domain.User;

import java.util.Map;

public interface UserDao {
    User selectUser(Map map) throws Exception;

    User selectUserById(String id) throws Exception;

    String selectId(String email) throws Exception;

    int insert(User user) throws Exception;

    int update(User user) throws Exception;

}
