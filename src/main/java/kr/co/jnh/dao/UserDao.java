package kr.co.jnh.dao;

import kr.co.jnh.domain.User;

import java.util.Map;

public interface UserDao {
    User selectUser(Map map) throws Exception;

    User selectUserById(String id) throws Exception;

    String selectId(String email) throws Exception;

    int insert(User user) throws Exception;

    int updatePwd(Map map) throws Exception;

    int updateStatus(Map map) throws Exception;
}
