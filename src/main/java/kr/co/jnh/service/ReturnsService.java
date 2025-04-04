package kr.co.jnh.service;

import kr.co.jnh.domain.Returns;
import kr.co.jnh.domain.SearchCondition;
import org.springframework.http.HttpHeaders;

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

    // 카카오페이 측에 요청 시 헤더부에 필요한 값
    default HttpHeaders getHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "SECRET_KEY DEVE62E64A0197A61931D33976CAB12F2BF95F0E");
        headers.set("Content-type", "application/json");

        return headers;
    }
}
