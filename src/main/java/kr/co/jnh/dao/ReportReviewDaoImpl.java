package kr.co.jnh.dao;

import kr.co.jnh.domain.ReportReview;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;
import java.util.Map;

@Repository
public class ReportReviewDaoImpl implements ReportReviewDao {
    @Autowired
    DataSource ds;

    @Autowired
    private SqlSession session;
    private static String nameSpace = "kr.co.jnh.dao.ReportReviewMapper.";

    @Override
    public List<ReportReview> selectAll() throws Exception{
        return session.selectList(nameSpace + "selectAll");
    }

    @Override
    public int insert(ReportReview reportReviewre) throws Exception{
        return session.insert(nameSpace + "insert", reportReviewre);
    }

    @Override
    public int update(Integer no) throws Exception{
        return  session.update( nameSpace + "update", no);
    }

    @Override
    public int delete(Integer no) throws Exception{
        return session.delete(nameSpace + "delete", no);
    }

    @Override
    public int selectPageCnt(Map map) throws Exception{
        return session.selectOne(nameSpace + "selectPageCnt", map);
    }

    @Override
    public List<ReportReview> selectPage(Map map) throws Exception{
        return session.selectList(nameSpace + "selectPage", map);
    }
}
