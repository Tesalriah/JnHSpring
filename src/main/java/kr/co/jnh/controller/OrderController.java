package kr.co.jnh.controller;

import kr.co.jnh.domain.*;
import kr.co.jnh.service.*;
import kr.co.jnh.util.SessionUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.*;

@Slf4j
@Controller
@RequestMapping("/mypage/order")
public class OrderController {

    @Autowired
    OrderService orderService;

    @Autowired
    ProductService productService;

    @Autowired
    ReturnsService returnsService;

    @Autowired
    UserService userService;

    @Autowired
    KakaoPayService kakaoPayService;

    // 주문목록 가져오기
    @GetMapping("list")
    public String mypage(HttpServletRequest request, SearchCondition sc, Model m) {
        String id = SessionUtils.getSessionId(request);
        sc.setPageSize(5); // 한 페이지에 5개의 주문
        Map<String, Object> map = new HashMap<>();
        map.put("id", id);
        map.put("sc", sc);

        List<List<Order>> orderList = new ArrayList<>(); // 하나의 주문에 여러가지 상품이 있을 수도 있으므로 List<Order>를 리스트로 덮어서 분류
        try {
            int totalCnt = orderService.readCnt(map); // 페이징 처리를 하기위해 해당 id의 총 주문의 갯수
            PageHandler ph = new PageHandler(totalCnt, sc); // 총 주문갯수를 SearchCondition에 따라 PageHandler로 페이징 처리

            List<Order> list = orderService.read(map); // 일단 SearchCondition에 따른 해당 아이디의 5개의 다른 주문번호의 정보 가져오기
            for (int i = 0; i < list.size(); i++) {
                // 각 주문번호의 주문상품들을 each에 추가
                map.put("order_no", list.get(i).getOrder_no());
                List<Order> each = orderService.readOne(map);
                System.out.println("each = " + each);
                orderList.add(each);
            }
            m.addAttribute("orderList", orderList);
            m.addAttribute("totalCnt", totalCnt);
            m.addAttribute("ph", ph);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return "mypage/order-list";
    }

    // 해당 주문정보 가져오기
    @GetMapping("detail")
    public String orderDetail(@RequestParam(required = false) String order_no, @RequestParam(defaultValue = "1") int page, HttpServletRequest request, Model m) {
        if (order_no == null) { // 받아온 order_no이 없을때 list로 리다이렉트
            return "redirect:/mypage/order/list?page=" + page;
        }
        String id = SessionUtils.getSessionId(request);
        Map<String,Object> map = new HashMap<>();
        map.put("id", id);
        map.put("order_no", order_no);

        try {
            int total = 0; // 총 상품가격
            List<Order> orderList = orderService.readOne(map); // 한 주문번호의 주문 상품들을 가져오기
            for(Order order : orderList){
                total += order.getProduct().getTotal();
            }
            if(orderList.isEmpty()){
                throw new Exception("WRONG_APPROACH");
            }
            m.addAttribute("orderList", orderList);
            m.addAttribute("total", total);
            m.addAttribute("page", page);
            return "mypage/order-detail";
        } catch (Exception e) {
            e.printStackTrace();
            m.addAttribute("msg", "잘못된 접근입니다.");
            m.addAttribute("url","list?page=" + page);
            return "alert";
        }
    }

    // 주문 삭제처리
    @PostMapping("del")
    public String orderDel(@RequestParam String order_no, @RequestParam(defaultValue = "1") int page, HttpServletRequest request, Model m) {
        String id = SessionUtils.getSessionId(request);
        Map<String,Object> map = new HashMap<>();
        map.put("id", id);
        map.put("order_no", order_no);
        map.put("is_deleted", "1");

        // 해당 주문번호의 status를 모두 삭체처리로 update
        try {
            if (orderService.updete(map) <= 0) { // update 실패 시 Exception 발생
                throw new Exception("ORDER_DEL_FAIL");
            }
        } catch (Exception e) {
            e.printStackTrace();
            m.addAttribute("msg", "삭제에 실패했습니다.");
            m.addAttribute("url", "list?page=" + page);
            return "alert";
        }
        return "redirect:/mypage/order/list?page=" + page;
    }

    @PostMapping("/pay/ready")
    public @ResponseBody ReadyResponse payReady(@RequestBody OrderList orderList, HttpServletRequest request) {
        String id = SessionUtils.getSessionId(request);

        List<Order> list = orderList.getOrderList();

        // 현재날짜 + 001~999까지의 세자리 수로 주문번호 만들기
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
        String today = format.format(date);
        long order_no;
        try{
            // 현재날짜의 주문이 있을 시 마지막 번호 다음번호로 주문번호 생성
            if(orderService.orderIdCheck(today)){
                String str = orderService.returnId(today);
                order_no = Long.parseLong(str) + 1;
                // 최대 999개의 주문번호 수용
                if(order_no + "" == today + "999"){
                    throw new Exception("ORDER_NO_LIMITED");
                }
            }else{  // 현재날짜 주문이 없을시 첫번째 주문번호 생성
                order_no = Long.parseLong(today + "001");
            }

            // user_id order_no 할당
            for (Order order : list) {
                order.setUser_id(id);
                order.setOrder_no(order_no + "");
            }

            // 카카오 결제 준비하기
            ReadyResponse readyResponse = kakaoPayService.payReady(list);

            // 세션에 결제 고유번호(tid) 저장
            SessionUtils.addAttribute("tid", readyResponse.getTid());
            // order_no도 저장
            SessionUtils.addAttribute("order_no", order_no);
//            log.info("결제 고유번호: " + readyResponse.getTid());
            return readyResponse;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @GetMapping("/pay/completed")
    public String payCompleted(@RequestParam("pg_token") String pgToken, HttpServletRequest request) {
        String id = SessionUtils.getSessionId(request);
        HttpSession session = request.getSession();

        String tid = SessionUtils.getStringAttributeValue("tid");
        String order_no = SessionUtils.getStringAttributeValue("order_no");
//        log.info("결제승인 요청을 인증하는 토큰: " + pgToken);
//        log.info("결제 고유번호: " + tid);
//        log.info("주문번호: " + order_no);

        Map<String,Object> map = new HashMap<>();
        map.put("id", id);
        map.put("order_no", order_no);
        try{
            Order order = orderService.readOne(map).get(0);

            // 카카오 결제 요청하기
            ApproveResponse approveResponse = kakaoPayService.payApprove(tid, pgToken, order);

            session.setAttribute("msg", "주문이 완료되었습니다.");
        }catch (Exception e){
            e.printStackTrace();
            session.setAttribute("msg", "주문에 실패했습니다.");
            if(e.getMessage().equals("STOCK_NOT_ENOUGH")){
                session.setAttribute("msg", "상품이 품절되었거나 재고가 모자릅니다. 확인 후 다시 시도해주세요.");
            }
        }
        return "redirect:/mypage/order/list";
    }
    @GetMapping("/pay/cancel")
    public String payCancel(HttpSession session) {
        session.setAttribute("msg", "상품 결제에 실패했습니다.");
        return "redirect:/product";
    }
    @GetMapping("/pay/fail")
    public String payFail(HttpSession session) {
        session.setAttribute("msg", "취소되었습니다.");
        return "redirect:/product";
    }
}
