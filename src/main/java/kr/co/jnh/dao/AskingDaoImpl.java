package kr.co.jnh.dao;

import kr.co.jnh.domain.AskingDto;
import kr.co.jnh.domain.SearchCondition;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class AskingDaoImpl implements AskingDao {
    @Autowired
    DataSource ds;

    @Autowired
    private SqlSession session;
    private static String namespace = "kr.co.jnh.dao.AskingMapper.";

    @Override
    public int count() throws Exception{
        return session.selectOne(namespace+"count");
    }

    @Override
    public int idCount(String user_id) throws Exception{
        return session.selectOne(namespace+"idCount",user_id);
    }

    @Override
    public int insert(AskingDto askingDto) throws Exception{
        return session.insert(namespace+"insert", askingDto);
    }

    @Override
    public List<AskingDto> selectAll(SearchCondition sc) throws Exception{
        return session.selectList(namespace+"selectAll", sc);
    }

    @Override
    public List<AskingDto> selectId(Map map) throws Exception{
        List<AskingDto> dto = session.selectList(namespace+"selectId",map);
        return dto;
    }

    @Override
    public int selectNo() throws Exception{
        return session.selectOne(namespace+"lastBno");
    }

    @Override
    public List<AskingDto> select(int no) throws Exception{
        return session.selectList(namespace+"select",no);
    }

    @Override
    public int update(AskingDto askingDto) throws Exception {
        return session.update(namespace+"update", askingDto);
    }

    @Override
    public int updateState(AskingDto askingDto) throws Exception {
        return session.update(namespace+"updateState", askingDto);
    }

    @Override
    public int delete(Map map) throws Exception {
        return session.delete(namespace+"delete", map);
    }

    @Override
    public AskingDto selectAnswer(int no) throws Exception {
        return session.selectOne(namespace+"selectAnswer", no);
    }


    @Override
    public Map prevNext(Map map) throws Exception {
        return session.selectOne(namespace+"prevNext",map);
    }



}
