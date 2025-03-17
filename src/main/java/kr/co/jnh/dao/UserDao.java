package kr.co.jnh.dao;

import kr.co.jnh.domain.SearchCondition;
import kr.co.jnh.domain.User;

import java.util.List;
import java.util.Map;

public interface UserDao {
    User selectUser(String id) throws Exception;

    User selectUserById(String id) throws Exception;

    String selectId(String email) throws Exception;

    int insert(User user) throws Exception;

    int update(Map map) throws Exception;

    int searchSelectUserCnt(SearchCondition sc) throws Exception;

    List<User> searchSelectUser(SearchCondition sc) throws Exception;
}
