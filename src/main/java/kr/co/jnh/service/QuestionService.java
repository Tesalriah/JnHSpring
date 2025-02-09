package kr.co.jnh.service;

import kr.co.jnh.domain.Question;

import java.util.List;
import java.util.Map;

public interface QuestionService {
    /*아이디 or 상품명에 맞춰 정보불러오기*/
    int getCount(Map map) throws Exception;

    /*마지막 번호보여주기*/
    int getLastqno() throws Exception;

    /*상품문의하기*/
    int write(Question question) throws Exception;

    /*전체 문의글 정렬*/
    List<Question> readAll() throws Exception;

    /*해당 아이디에 대한 정보 불러오기
        질문:ano=1 / 답변:ano=2*/
    List<Question> readId(Map map) throws Exception;

    /*아이디 or 상품명에 맞춰 정보불러오기*/
    List<Question> readInfo(Map map) throws Exception;

    /*해당 번호에 대한 정보 불러오기*/
    List<Question> read(int qno) throws Exception;

    /*내용수정*/
    int modify(Question question) throws Exception;

    /*삭제*/
    int remove(Map map) throws Exception;
}
