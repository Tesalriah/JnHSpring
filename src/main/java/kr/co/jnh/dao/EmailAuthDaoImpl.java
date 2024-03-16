package kr.co.jnh.dao;

import kr.co.jnh.domain.MailAuthDto;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;

@Repository
public class EmailAuthDaoImpl implements EmailAuthDao {

    @Autowired
    DataSource ds;

    @Autowired
    private SqlSession session;

    private static String namespace = "kr.co.jnh.dao.EmailMapper.";

    public String selectAuthNum(String email) throws Exception{
        return session.selectOne(namespace + "select", email);
    }

    public Integer insertAuth(MailAuthDto mailAuth) throws Exception{
        return session.insert(namespace + "insert", mailAuth);
    }

    public Integer deleteAuth(String email) throws Exception{
        return  session.delete(namespace + "delete", email);
    }
}
