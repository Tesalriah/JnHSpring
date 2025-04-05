package kr.co.jnh.dao;

import kr.co.jnh.domain.Review;
import kr.co.jnh.domain.SearchCondition;
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
    public Review selectOne(int rno) throws Exception{
        return session.selectOne( nameSpace + "selectOne", rno);
    }

    @Override
    public Review selectOneWithOrder(int rno) throws Exception{
        return session.selectOne( nameSpace + "selectOneWithOrder", rno);
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
    public int cancelDelete(Map map) throws Exception{
        return session.update( nameSpace + "cancelDelete", map);
    }

    @Override
    public int selectPageCnt(Map map) throws Exception{
        return session.selectOne( nameSpace + "selectPageCnt", map);
    }

    @Override
    public List<Review> selectPage(Map map) throws Exception{
        return session.selectList( nameSpace + "selectPage", map);
    }

    @Override
    public List<Review> reviewWithOrder(Map map) throws Exception {
        return  session.selectList( nameSpace + "reviewWithOrder", map);
    }

    @Override
    public int SelectPageByReviewCnt(SearchCondition sc) throws Exception{
        return session.selectOne( nameSpace + "SelectPageByReviewCnt", sc);
    }

    @Override
    public List<Review> SelectPageByReview(SearchCondition sc) throws Exception{
        return session.selectList( nameSpace + "SelectPageByReview", sc);
    }
}
