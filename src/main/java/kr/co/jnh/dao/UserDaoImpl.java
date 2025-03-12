package kr.co.jnh.dao;

import kr.co.jnh.domain.User;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.Map;

@Repository
public class UserDaoImpl implements UserDao {
    @Autowired
    DataSource ds;

    @Autowired
    private SqlSession session;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
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
        String hashedPwd = passwordEncoder.encode(user.getUser_pwd());
        user.setUser_pwd(hashedPwd);
        return session.insert(namespace + "insert", user);
    }

    @Override
    public int update(Map map) throws Exception{
        if(map.get("user_pwd") != null){
            String hashedPwd = passwordEncoder.encode((String)map.get("user_pwd"));
            map.put("user_pwd", hashedPwd);
        }
        return session.update( namespace + "update", map);
    }

}
