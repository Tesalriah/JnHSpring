package kr.co.jnh.service;

import kr.co.jnh.domain.Returns;
import kr.co.jnh.domain.SearchCondition;

import java.util.List;
import java.util.Map;

public interface ReturnsService {
    List<List<Returns>> read(Map map) throws Exception;

    int create(Returns returns) throws Exception;

    int remove(Integer rno) throws Exception;

    int modify(Map map) throws Exception;

    int mngModify(Map map) throws Exception;

    int count(String id) throws Exception;

    List<Returns> readAll() throws Exception;

    String readId(String return_id) throws Exception;

    int returns(List<Returns> list) throws Exception;

    int readMngCnt(SearchCondition sc) throws Exception;

    List<Returns> readMng(SearchCondition sc) throws Exception;
}
