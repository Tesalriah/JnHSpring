package kr.co.jnh.service;

import kr.co.jnh.dao.CartDao;
import kr.co.jnh.dao.OrderDao;
import kr.co.jnh.dao.ProductDao;
import kr.co.jnh.domain.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class KakaoPayServiceImpl implements KakaoPayService {

    @Autowired
    OrderDao orderDao;

    @Autowired
    ProductDao productDao;

    @Autowired
    CartDao cartDao;

    // 카카오페이 결제창 연결
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ReadyResponse payReady(List<Order> list) throws Exception{
        if(checkStock(list)){
            throw new Exception("STOCK_NOT_ENOUGH");
        }

        String productName = "";
        int totalPrice = 0;

        // 카카오페이에 출력할 상품명 출력 1개 이상일시 ' 외 n개의 상품' 추가로 출력
        Product firstProduct = productDao.select(list.get(0).getProduct_id());
        productName = firstProduct.getProduct_name();
        if(list.size() > 1){
            int orthersCnt = list.size()-1;
            productName += " 외 " + orthersCnt + "개의 상품";
        }

        for (Order order : list) {
            Product product = productDao.select(order.getProduct_id());
            order.setColor(product.getColor()); // order의 color set
            product.setQuantity(order.getQuantity());
            totalPrice += product.getTotal();

            if(orderDao.insert(order) != 1){
                throw new Exception("ORDER_INSERT_FAIL");
            }

            Map<String, Object> map = new HashMap();
            map.put("user_id", order.getUser_id());
            map.put("product_id", order.getProduct_id());
            map.put("size", order.getSize());

            // product의 stock 구매수량만큼 감소
            int calStock = Integer.parseInt(product.getStock()) - order.getQuantity();
            map.put("stock", calStock);
            map.put("size", order.getSize());
            map.put("bought_cnt", product.getBought_cnt() + order.getQuantity());
            productDao.update(map);

            // 구매시 카트의 해당 상품 삭제
            if(cartDao.checkCart(map) != null){
                if(cartDao.delete(map) != 1){
                    throw new Exception("CART_DELETE_FAIL");
                }
            }
        }

        // 배송비 추가
        totalPrice += 3000;

//        log.info("주문 상품 이름: " + productName);
//        log.info("주문 금액: " + totalPrice);

        Map<String, String> parameters = new HashMap<>();
        parameters.put("cid", "TC0ONETIME");                                    // 가맹점 코드(테스트용)
        parameters.put("partner_order_id", list.get(0).getOrder_no());                       // 주문번호
        parameters.put("partner_user_id", list.get(0).getUser_id());                          // 회원 아이디
        parameters.put("item_name", productName);                                      // 상품명
        parameters.put("quantity", list.size() + "");                                        // 상품 수량
        parameters.put("total_amount", String.valueOf(totalPrice));             // 상품 총액
        parameters.put("tax_free_amount", "0");                                 // 상품 비과세 금액
        parameters.put("approval_url", "http://www.jnh.kro.kr/jnh/mypage/order/pay/completed"); // 결제 성공 시 URL
        parameters.put("cancel_url", "http://www.jnh.kro.kr/jnh/mypage/order/pay/cancel");      // 결제 취소 시 URL
        parameters.put("fail_url", "http://www.jnh.kro.kr/jnh/mypage/order/pay/fail");          // 결제 실패 시 URL

        // HttpEntity : HTTP 요청 또는 응답에 해당하는 Http Header와 Http Body를 포함하는 클래스
        HttpEntity<Map<String, String>> requestEntity = new HttpEntity<>(parameters, this.getHeaders());

        // RestTemplate
        // : Rest 방식 API를 호출할 수 있는 Spring 내장 클래스
        //   REST API 호출 이후 응답을 받을 때까지 기다리는 동기 방식 (json, xml 응답)
        RestTemplate template = new RestTemplate();
        String url = "https://open-api.kakaopay.com/online/v1/payment/ready";
        // RestTemplate의 postForEntity : POST 요청을 보내고 ResponseEntity로 결과를 반환받는 메소드
        ResponseEntity<ReadyResponse> responseEntity = template.postForEntity(url, requestEntity, ReadyResponse.class);
        log.info("결제준비 응답객체: " + responseEntity.getBody());

        return responseEntity.getBody();
    }

    // 카카오페이 결제 승인
    // 사용자가 결제 수단을 선택하고 비밀번호를 입력해 결제 인증을 완료한 뒤,
    // 최종적으로 결제 완료 처리를 하는 단계
    @Override
    public ApproveResponse payApprove(String tid, String pgToken, Order order) throws Exception{
        Map<String, String> parameters = new HashMap<>();
        parameters.put("cid", "TC0ONETIME");              // 가맹점 코드(테스트용)
        parameters.put("tid", tid);                       // 결제 고유번호
        parameters.put("partner_order_id", order.getOrder_no()); // 주문번호
        parameters.put("partner_user_id", order.getUser_id());    // 회원 아이디
        parameters.put("pg_token", pgToken);              // 결제승인 요청을 인증하는 토큰

        HttpEntity<Map<String, String>> requestEntity = new HttpEntity<>(parameters, this.getHeaders());

        RestTemplate template = new RestTemplate();
        String url = "https://open-api.kakaopay.com/online/v1/payment/approve";
        ApproveResponse approveResponse = template.postForObject(url, requestEntity, ApproveResponse.class);
        log.info("결제승인 응답객체: " + approveResponse);

        Map<String,Object> map = new HashMap<>();
        map.put("order_no",order.getOrder_no());
        map.put("id", order.getUser_id());
        map.put("status","주문완료");
        map.put("tid", tid);
        map.put("payment_method_type", approveResponse.getPayment_method_type());
        if(!approveResponse.getPayment_method_type().equals("MONEY")){
            map.put("issuer_corp", approveResponse.getCard_info().getKakaopay_issuer_corp());
        }
        if(orderDao.updete(map) == 0){
            throw new Exception("ORDER_STATUS_UPDATE_FAIL");
        }

        return approveResponse;
    }

    // 카카오페이 결제 취소
    @Override
    public CancelResponse payCancel(List<Order> list, String type) throws Exception{
        int totalPrice = 0;
        if(type.equals("cancel")){
            totalPrice += 3000;
        }
        for (Order order : list) {
            Product product = productDao.select(order.getProduct_id());
            product.setQuantity(order.getQuantity());
            totalPrice = product.getTotal();
        }

        Map<String, String> parameters = new HashMap<>();
        parameters.put("cid", "TC0ONETIME");                            // 가맹점 코드(테스트용)
        parameters.put("tid", list.get(0).getTid());                    // 결제 고유번호
        parameters.put("cancel_amount", totalPrice+"");
        parameters.put("cancel_tax_free_amount", "0");
        parameters.put("partner_order_id", list.get(0).getOrder_no());  // 주문번호
        parameters.put("partner_user_id", list.get(0).getUser_id());    // 회원 아이디

        HttpEntity<Map<String, String>> requestEntity = new HttpEntity<>(parameters, this.getHeaders());

        RestTemplate template = new RestTemplate();
        String url = "https://open-api.kakaopay.com/online/v1/payment/cancel";
        CancelResponse cancelResponse = template.postForObject(url, requestEntity, CancelResponse.class);
        log.info("취소승인 응답객체: " + cancelResponse);

        return cancelResponse;
    }

    private boolean checkStock(List<Order> list) throws Exception {
        for (Order order : list) {
            Map<String,Object> map = new HashMap<>();
            map.put("product_id", order.getProduct_id());
            map.put("size", order.getSize());
            Product product = productDao.selectAtSize(map);

            int stock = Integer.parseInt(product.getStock());
            int quan = order.getQuantity();
            if(stock < quan || stock - quan < 0){
                return true;
            }
        }
        return false;
    }

}
