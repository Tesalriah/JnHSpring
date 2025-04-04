package kr.co.jnh.dao;

import kr.co.jnh.domain.Question;

import java.util.List;
import java.util.Map;

public interface QuestionDao {
    int count(Map map) throws Exception;

    int lastQno() throws Exception;

    int insert(Question question) throws Exception;

    List<Question> selectAll() throws Exception;

    List<Question> selectMng(Map map) throws Exception;

    int selectMngCnt() throws Exception;

    List<Question> selectInfo(Map map) throws Exception;

    List<Question> select(int qno) throws Exception;

    int update(Question question) throws Exception;

    int delete(Map map) throws Exception;
}
