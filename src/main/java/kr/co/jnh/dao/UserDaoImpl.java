package kr.co.jnh.dao;

import kr.co.jnh.domain.User;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.Map;

@Repository
public class UserDaoImpl implements UserDao {
    @Autowired
    DataSource ds;

    @Autowired
    private SqlSession session;
    private static String namespace = "kr.co.jnh.dao.UserMapper.";

    @Override
    public User selectUser(Map map) throws Exception{
        return session.selectOne(namespace + "selectUser", map);
    }

    @Override
    public Integer selectUserGrade(String id) throws Exception{
        return session.selectOne(namespace + "selectUserGrade", id);
    }

    @Override
    public Integer insert(User user) throws Exception{
        return session.insert(namespace + "insert", user);
    }

}
