package kr.co.jnh.dao;

import kr.co.jnh.domain.NoticeDto;
import kr.co.jnh.domain.SearchCondition;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;
import java.util.Map;


@Repository
public class NoticeDaoImpl implements NoticeDao {
    @Autowired
    DataSource ds;

    @Autowired
    private SqlSession session;
    private static String namespace = "kr.co.jnh.dao.NoticeMapper.";


    @Override
    public int count() throws Exception{
        return session.selectOne(namespace+"count");
    }

    @Override
    public Integer insert(NoticeDto noticeDto) throws Exception{
        return session.insert(namespace + "insert", noticeDto);
    }

    @Override
    public List<NoticeDto> selectAll() throws Exception {
        return session.selectList(namespace+"selectAll");
    }

    @Override
    public NoticeDto select(Integer bno) throws Exception {
        return session.selectOne(namespace+"select" ,bno);
    }

    @Override
    public List<NoticeDto> selectPage(Map map) throws Exception {
        return session.selectList(namespace+"selectPage", map);
    }

    @Override
    public List<NoticeDto> selectMustRead() throws Exception {
        return session.selectList(namespace+"selectMustRead");
    }


    @Override
    public Integer Mcount() throws Exception {
        return session.selectOne(namespace+"mustReadCount");
    }

    @Override
    public List<NoticeDto> searchSelectPage(SearchCondition sc) throws Exception{
        return session.selectList(namespace+"searchSelectPage", sc);
    }
    @Override
    public int searchResultCnt(SearchCondition sc) throws Exception {
        return session.selectOne(namespace + "searchResultCnt", sc);
    }

    @Override
    public Map prevNext(Map map) throws Exception {
        return session.selectOne(namespace + "prevNext", map);
    }

    @Override
    public int delete(Map map) throws Exception {
        return session.delete(namespace + "delete", map);
    }

    @Override
    public int update(NoticeDto noticeDto) throws Exception {
        return session.update(namespace + "update", noticeDto);
    }




    /*@Override
    public NoticeDto update() throws Exception {
        return session.selectOne(namespace+"mustReadCount");
    }*/


}
