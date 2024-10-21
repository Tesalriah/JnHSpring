package kr.co.jnh.service;

import kr.co.jnh.domain.NoticeDto;
import kr.co.jnh.domain.SearchCondition;

import java.util.List;
import java.util.Map;

public interface NoticeService {
    int getCount() throws Exception;

    List<NoticeDto> getList() throws Exception;

    List<NoticeDto> getSelectPage(Map map)throws Exception;

    NoticeDto read(Integer bno) throws Exception;

    Integer Mcount() throws Exception;

    Integer write(NoticeDto noticeDto) throws Exception;

    List<NoticeDto> getSearchSelectPage(SearchCondition sc) throws Exception;

    int getSearchResultCnt(SearchCondition sc) throws Exception;

    List<NoticeDto> getSelectMustRead() throws Exception;

    Map getPrevNext(Map map) throws Exception;

    int remove(Map map) throws Exception;
    int modify(NoticeDto noticeDto) throws Exception;

    }
