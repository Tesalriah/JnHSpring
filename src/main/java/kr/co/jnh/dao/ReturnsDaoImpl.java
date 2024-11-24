package kr.co.jnh.dao;

import kr.co.jnh.domain.Returns;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;
import java.util.Map;

@Repository
public class ReturnsDaoImpl implements ReturnsDao {
    @Autowired
    DataSource ds;

    @Autowired
    private SqlSession session;
    private static String nameSpace = "kr.co.jnh.dao.ReturnsMapper.";

    @Override
    public List<Returns> selectAll() throws Exception{
        return session.selectList(nameSpace + "selectAll");
    }

    @Override
    public int insert(Returns returns) throws Exception{
        return session.insert(nameSpace + "insert", returns);
    }

    @Override
    public int delete(Integer rno) throws Exception{
        return session.delete(nameSpace + "delete", rno);
    }

    @Override
    public int update(Map map) throws Exception{
        return  session.update( nameSpace + "update", map);
    }

    @Override
    public int getCount(String id) throws Exception{
        return session.selectOne(nameSpace + "selectPageCnt", id);
    }

    @Override
    public List<Returns> selectPage(Map map) throws Exception{
        return session.selectList(nameSpace + "selectPage", map);
    }

    @Override
    public String selectId(String return_id) throws Exception{
        return session.selectOne( nameSpace + "selectId", return_id);
    }
}
