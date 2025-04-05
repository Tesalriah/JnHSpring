package kr.co.jnh.dao;

import kr.co.jnh.domain.SearchCondition;
import kr.co.jnh.domain.User;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;
import java.util.Map;

@Repository
public class UserDaoImpl implements UserDao {
    @Autowired
    DataSource ds;

    @Autowired
    private SqlSession session;

    private static String namespace = "kr.co.jnh.dao.UserMapper.";

    @Override
    public User selectUser(String id) throws Exception{
        return session.selectOne(namespace + "selectUser", id);
    }

    @Override
    public User selectUserById(String id) throws Exception{
        return session.selectOne(namespace + "selectUserById", id);
    }

    @Override
    public String selectId(String email) throws Exception{
        return session.selectOne(namespace + "selectId", email);
    }

    @Override
    public int insert(User user) throws Exception{
        return session.insert(namespace + "insert", user);
    }

    @Override
    public int update(Map map) throws Exception{
        return session.update( namespace + "update", map);
    }

    @Override
    public int searchSelectUserCnt(SearchCondition sc) throws Exception{
        return session.selectOne(namespace + "searchSelectUserCnt", sc);
    }

    @Override
    public List<User> searchSelectUser(SearchCondition sc) throws Exception{
        return session.selectList(namespace + "searchSelectUser", sc);
    }

}
