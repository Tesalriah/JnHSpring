package kr.co.jnh.dao;

import kr.co.jnh.domain.Review;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;
import java.util.Map;

@Repository
public class ReviewDaoImpl implements ReviewDao {

    @Autowired
    DataSource ds;

    @Autowired
    private SqlSession session;

    private static String nameSpace = "kr.co.jnh.dao.ReviewMapper.";

    @Override
    public List<Review> selectAll() throws Exception{
        return session.selectList( nameSpace + "selectAll");
    }

    @Override
    public Review selectOne(int no) throws Exception{
        return session.selectOne( nameSpace + "selectOne", no);
    }

    @Override
    public int insert(Review review) throws Exception{
        return session.insert( nameSpace + "insert", review);
    }

    @Override
    public int update(Review review) throws Exception{
        return session.update( nameSpace + "update", review);
    }

    @Override
    public int delete(int no) throws Exception{
        return session.update( nameSpace + "delete", no);
    }

    @Override
    public int selectPageCnt(Map map){
        return session.selectOne( nameSpace + "selectPageCnt", map);
    }

    @Override
    public List<Review> selectPage(Map map){
        return session.selectList( nameSpace + "selectPage", map);
    }
}
