package kr.co.jnh.dao;

import kr.co.jnh.domain.Wish;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class WishDaoImpl implements WishDao {
    @Autowired
    DataSource ds;

    @Autowired
    private SqlSession session;
    private static String nameSpace = "kr.co.jnh.dao.CartMapper.";

    @Override
    public List<Wish> select(String id) throws Exception{
        return session.selectList( nameSpace + "selectPage", id);
    }

    @Override
    public Wish selectOne(Wish wish) throws Exception{
        return  session.selectOne( nameSpace + "selectOne", wish);
    }

    @Override
    public List<Wish> selectAll() throws Exception{
        return session.selectList( nameSpace + "selectAll");
    }

    @Override
    public int insert(Wish wish) throws Exception{
        return session.insert( nameSpace + "insert", wish);
    }

    @Override
    public int delete(Wish wish) throws Exception{
        return session.delete( nameSpace + "delete", wish);
    }

    @Override
    public int getCount(String id) throws Exception{
        return session.selectOne( nameSpace + "selectPageCnt", id);
    }
}
