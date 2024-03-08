package kr.co.jnh.dao;

import kr.co.jnh.domain.User;

import java.util.Map;

public interface UserDao {
    User selectUser(Map map) throws Exception;

    String selectUserId(String id) throws Exception;

    String selectEmail(String id) throws Exception;

    Integer selectUserGrade(String id) throws Exception;

    Integer insert(User user) throws Exception;

    Integer updateStatus(Map map) throws Exception;
}
