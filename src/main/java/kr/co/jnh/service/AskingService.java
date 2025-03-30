package kr.co.jnh.service;

import kr.co.jnh.domain.AskingDto;
import kr.co.jnh.domain.SearchCondition;

import java.util.List;
import java.util.Map;

public interface AskingService {
    // 글 갯수 세기
    int getCount() throws Exception;

    // 해당 id의 글 갯수(문의글, 답변 포함)
    int getIdCount(String user_id) throws Exception;

    // 글 작성
    int write(AskingDto askingDto) throws Exception;


    // 최신순, 대기중-답변완료 순으로 글 정렬
    List<AskingDto> readAll(SearchCondition sc) throws Exception;

    // No순으로 desc
    int readNo() throws Exception;

    // 해당 id 문의 목록
    List<AskingDto> idList(Map map) throws Exception;

    // 문의 게시글
    List<AskingDto> read(int no) throws Exception;

    // 글 수정
    int modify(AskingDto askingDto) throws Exception;

    //상태변경
    int modifyState(AskingDto askingDto) throws Exception;

    // 삭제
    int remove(Map<String, Object> map) throws Exception;

    // 답변작성 후 상태변경
    int adminWrite(AskingDto askingDto) throws Exception;

    AskingDto getAnswer(int no) throws Exception;

    // 현재 no 를 기준으로 앞뒤 2개의 게시물의 no 확인
    Map getPrevNext(Map map) throws Exception;



}
