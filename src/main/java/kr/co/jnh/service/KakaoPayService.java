package kr.co.jnh.service;

import kr.co.jnh.domain.ApproveResponse;
import kr.co.jnh.domain.CancelResponse;
import kr.co.jnh.domain.Order;
import kr.co.jnh.domain.ReadyResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface KakaoPayService {
    // 카카오페이 결제창 연결
    @Transactional(rollbackFor = Exception.class)
    ReadyResponse payReady(List<Order> list) throws Exception;

    // 카카오페이 결제 승인
    // 사용자가 결제 수단을 선택하고 비밀번호를 입력해 결제 인증을 완료한 뒤,
    // 최종적으로 결제 완료 처리를 하는 단계
    ApproveResponse payApprove(String tid, String pgToken, Order order) throws Exception;

    // 카카오페이 결제 취소
    CancelResponse payCancel(List<Order> list, String type) throws Exception;

    // 카카오페이 측에 요청 시 헤더부에 필요한 값
    default HttpHeaders getHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "SECRET_KEY DEVE62E64A0197A61931D33976CAB12F2BF95F0E");
        headers.set("Content-type", "application/json");

        return headers;
    }
}
