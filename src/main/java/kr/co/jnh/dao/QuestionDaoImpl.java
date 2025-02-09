package kr.co.jnh.dao;

import kr.co.jnh.domain.Question;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;
import java.util.Map;

@Repository
public class QuestionDaoImpl implements QuestionDao {

    @Autowired
    DataSource ds;

    @Autowired
    private SqlSession session;
    private static String namespace = "kr.co.jnh.dao.QuestionMapper.";

    @Override
    public int count(Map map) throws Exception{
        return session.selectOne(namespace+"count",map);
    }

    @Override
    public int lastqno() throws Exception{
        return session.selectOne(namespace+"lastqno");
    }

    @Override
    public int insert(Question question) throws Exception{
        return session.insert(namespace+"insert",question);
    }

    @Override
    public List<Question> selectAll() throws Exception{
        return session.selectList(namespace+"selectAll");
    }
    @Override
    public List<Question> selectId(Map map) throws Exception{
        return session.selectList(namespace+"selectId",map);
    }
    @Override
    public List<Question> selectInfo(Map map) throws Exception{
        return session.selectList(namespace+"selectInfo",map);
    }

    @Override
    public List<Question> select(int qno) throws Exception {
        return session.selectList(namespace+"select",qno);
    }

    @Override
    public int update(Question question) throws Exception{
        return session.update(namespace+"update",question);
    }

    @Override
    public int delete(Map map) throws Exception{
        return session.delete(namespace+"delete",map);
    }
}
