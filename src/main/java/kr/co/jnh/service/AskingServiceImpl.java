package kr.co.jnh.service;


import kr.co.jnh.dao.AskingDao;
import kr.co.jnh.domain.AskingDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class AskingServiceImpl implements AskingService {
    @Autowired
    AskingDao askingDao;

    // 글 갯수 세기
    @Override
    public int getCount() throws Exception{
        return askingDao.count();
    }

    // 해당 id의 글 갯수(문의글, 답변 포함)
    @Override
    public int getIdCount(String user_id) throws Exception{
        return askingDao.idCount(user_id);
    }

    // 글 작성
    @Override
    public int write(AskingDto askingDto) throws Exception{
        return askingDao.insert(askingDto);
    }

    // 최신순, 대기중-답변완료 순으로 글 정렬
    @Override
    public List<AskingDto> readAll() throws Exception{
        return askingDao.selectAll();
    }

    // No순으로 desc
    @Override
    public int readNo() throws Exception{
        return askingDao.selectNo();
    }

    // 해당 id 문의 목록
    @Override
    public List<AskingDto> idList(Map map) throws Exception{
        return askingDao.selectId(map);
    }

    // 문의 게시글
    @Override
    public List<AskingDto> read(int no) throws Exception{
        return askingDao.select(no);
    }

    // 글 수정
    @Override
    public int modify(AskingDto askingDto) throws Exception {
        return askingDao.update(askingDto);
    }

    //대기중, 답변완료 상태변경
    @Override
    public int modifyState(AskingDto askingDto) throws Exception {
        return askingDao.updateState(askingDto);
    }

    // 삭제
    @Override
    public int remove(Map map) throws Exception {
        return askingDao.delete(map);
    }

    public Map getPrevNext(Map map) throws Exception {
        return askingDao.prevNext(map);
    }

}
