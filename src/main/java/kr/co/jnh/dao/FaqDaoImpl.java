package kr.co.jnh.dao;

import kr.co.jnh.domain.Faq;
import kr.co.jnh.domain.SearchCondition;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class FaqDaoImpl implements FaqDao {
    @Autowired
    DataSource ds;

    @Autowired
    private SqlSession session;
    private static String namespace = "kr.co.jnh.dao.FaqMapper.";

    @Override
    public int insert(Faq faq) throws Exception{
        return session.insert(namespace+"insert", faq);
    }

    @Override
    public int count(SearchCondition sc) throws Exception{
        return session.selectOne(namespace+"count", sc);
    }

    @Override
    public List<Faq> selectAll(SearchCondition sc) throws Exception{
        return session.selectList(namespace+"selectAll", sc);
    }

    @Override
    public Faq selectNo(int faq) throws Exception{
        return session.selectOne(namespace + "selectNo", faq);
    }

    @Override
    public int update(Faq faq) throws Exception{
        return session.update(namespace+"update",faq);
    }

    @Override
    public int delete(Integer faq) throws Exception {
        return session.delete(namespace + "delete", faq);
    }

}
