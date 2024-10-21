package kr.co.jnh.service;

import kr.co.jnh.dao.NoticeDao;
import kr.co.jnh.domain.NoticeDto;
import kr.co.jnh.domain.SearchCondition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class NoticeServiceImpl implements NoticeService {

    @Autowired
    NoticeDao noticeDao;

    @Override
    public int getCount() throws Exception {
        return noticeDao.count();
    }
    @Override
    public List<NoticeDto> getList() throws Exception {
        return noticeDao.selectAll();
    }

    @Override
    public List<NoticeDto> getSelectPage(Map map)throws Exception {
        return noticeDao.selectPage(map);
    }

    @Override
    public NoticeDto read(Integer bno) throws Exception {
        return noticeDao.select(bno);
    }

    @Override
    public Integer Mcount() throws Exception {
        return noticeDao.Mcount();
    }

    @Override
    public Integer write(NoticeDto noticeDto) throws Exception{
        return noticeDao.insert(noticeDto);
    }

    @Override
    public List<NoticeDto> getSearchSelectPage(SearchCondition sc) throws Exception{
        return noticeDao.searchSelectPage(sc);
    }   // 검색하여 얻은 게시물을 list에 담음

    @Override
    public int getSearchResultCnt(SearchCondition sc) throws Exception {
        return noticeDao.searchResultCnt(sc);
    } // 검색하여 얻은 게시물 수

    @Override
    public List<NoticeDto> getSelectMustRead() throws Exception {
        return noticeDao.selectMustRead();
    }

    @Override
    public Map getPrevNext(Map map) throws Exception {
        return noticeDao.prevNext(map);
    }

    @Override
    public int remove(Map map) throws Exception {
        return noticeDao.delete(map);
    }

    @Override
    public int modify(NoticeDto noticeDto) throws Exception {
        return noticeDao.update(noticeDto);
    }


}

