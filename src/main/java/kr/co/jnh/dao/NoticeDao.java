package kr.co.jnh.dao;

import kr.co.jnh.domain.NoticeDto;
import kr.co.jnh.domain.Product;
import kr.co.jnh.domain.SearchCondition;

import java.util.List;
import java.util.Map;

public interface NoticeDao {
    int count() throws Exception;
    Integer insert(NoticeDto noticeDto) throws Exception;

    List<NoticeDto> selectAll() throws Exception;

    NoticeDto select(Integer bno) throws Exception;

    List<NoticeDto> selectPage(Map map) throws Exception;

    Integer Mcount() throws Exception;

    List<NoticeDto> searchSelectPage(SearchCondition sc) throws Exception;

    int searchResultCnt(SearchCondition sc) throws Exception;

    List<NoticeDto> selectMustRead() throws Exception;

    Map prevNext(Map map) throws Exception;

    }