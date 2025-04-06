package kr.co.jnh.service;

import kr.co.jnh.dao.ProductDao;
import kr.co.jnh.dao.QuestionDao;
import kr.co.jnh.domain.Product;
import kr.co.jnh.domain.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class QuestionServiceImpl implements QuestionService {
    @Autowired
    QuestionDao questionDao;

    @Autowired
    ProductDao productDao;

    /*아이디 or 상품명에 맞춰 정보불러오기*/
    @Override
    public int getCount(Map map) throws Exception{
        return questionDao.count(map);
    }

    /*마지막 번호보여주기*/
    @Override
    public int getLastQno() throws Exception{
        return questionDao.lastQno();
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

    // 답변이없는 문의만 가져오기
    @Override
    public List<Question> readMng(Map map) throws Exception{
        return questionDao.selectMng(map);
    }

    @Override
    public int readMngCnt() throws Exception{
        return questionDao.selectMngCnt();
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
