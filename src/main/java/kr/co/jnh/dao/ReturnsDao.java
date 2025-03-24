package kr.co.jnh.dao;

import kr.co.jnh.domain.Returns;
import kr.co.jnh.domain.SearchCondition;

import java.util.List;
import java.util.Map;

public interface ReturnsDao {
    List<Returns> selectAll() throws Exception;

    List<Returns> selectOne(String return_id) throws Exception;

    int insert(Returns returns) throws Exception;

    int delete(Integer rno) throws Exception;

    int update(Map map) throws Exception;

    int mngUpdate(Map map) throws Exception;

    int getCount(String id) throws Exception;

    List<Returns> selectPage(Map map) throws Exception;

    String selectId(String return_id) throws Exception;

    int selectMngCnt(SearchCondition sc) throws Exception;

    List<Returns> selectMng(SearchCondition sc) throws Exception;
}
