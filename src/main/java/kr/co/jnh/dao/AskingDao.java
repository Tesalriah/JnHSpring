package kr.co.jnh.dao;

import kr.co.jnh.domain.AskingDto;

import java.util.List;
import java.util.Map;

public interface AskingDao {
    int count() throws Exception;

    int idCount(String user_id) throws Exception;

    int insert(AskingDto askingDto) throws Exception;

    List<AskingDto> selectAll() throws Exception;

    int selectNo() throws Exception;

    List<AskingDto> selectId(Map map) throws Exception;

    List<AskingDto> select(int no) throws Exception;

    int update(AskingDto askingDto) throws Exception;

    int updateState(AskingDto askingDto) throws Exception;

    int delete(Map map) throws Exception;

    Map prevNext(Map map) throws Exception;
}
