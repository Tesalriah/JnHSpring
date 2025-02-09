package kr.co.jnh.service;

import kr.co.jnh.dao.QuestionDao;
import kr.co.jnh.domain.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class QuestionServiceImlp implements QuestionService {
    @Autowired
    QuestionDao questionDao;

    /*아이디 or 상품명에 맞춰 정보불러오기*/
    @Override
    public int getCount(Map map) throws Exception{
        return questionDao.count(map);
    }

    /*마지막 번호보여주기*/
    @Override
    public int getLastqno() throws Exception{
        return questionDao.lastqno();
    }

    /*상품문의하기*/
    @Override
    public int write(Question question) throws Exception{
        return questionDao.insert(question);
    }

    /*전체 문의글 정렬*/
    @Override
    public List<Question> readAll() throws Exception{
        return questionDao.selectAll();
    }

    /*해당 아이디에 대한 정보 불러오기
    질문:ano=1 / 답변:ano=2*/
    @Override
    public List<Question> readId(Map map) throws Exception{
        return questionDao.selectId(map);
    }

    /*아이디 or 상품명에 맞춰 정보불러오기*/
    @Override
    public List<Question> readInfo(Map map) throws Exception{
        return questionDao.selectInfo(map);
    }

    /*해당 번호에 대한 정보 불러오기*/
    @Override
    public List<Question> read(int qno) throws Exception{
        return questionDao.select(qno);
    }

    /*내용수정*/
    @Override
    public int modify(Question question) throws Exception{
        return questionDao.update(question);
    }

    /*삭제*/
    @Override
    public int remove(Map map) throws Exception{
        return questionDao.delete(map);
    }
}
