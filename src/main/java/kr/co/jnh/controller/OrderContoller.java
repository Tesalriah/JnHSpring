package kr.co.jnh.controller;

import kr.co.jnh.domain.Order;
import kr.co.jnh.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class OrderContoller {

    @Autowired
    OrderService orderService;

    @PostMapping("buy")
    public String buy(Order order, String quantity, String address2, HttpServletRequest request, HttpServletResponse response){
        HttpSession session = request.getSession();
        String id = (String)session.getAttribute("id");

        // 하나이상의 주문을 각각 처리하기 위해 배열에 나눠서 저장
        String[] product_id = order.getProduct_id().split(",");
        String[] size = order.getSize().split(",");
        String[] quan = quantity.split(",");
        // 선택된 주문요청사항이 없을시 이전페이지로
        if(order.getDel_request().equals("")) {
            String referer = request.getHeader("referer");
            try {
                response.sendRedirect(referer);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        order.setUser_id(id);
        // 상세주소를 입력했을 시 추가
        if(!address2.isBlank()){
            order.setAddress( order.getAddress() + address2);
        }
        // 현재날짜 + 001~999까지의 세자리 수로 주문번호 만들기
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
        String today = format.format(date);
        long order_no;
        try {
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
            order.setOrder_no(order_no + "");
            // list에 주문 상품 순서대로 저장
            List<Order> list = new ArrayList<>();
            for (int i = 0; i < product_id.length; i++) {
                // 공통된 항목 trigger에 저장
                Order trigger = new Order(order.getUser_id(),order.getName(),order.getAddress(), order.getPhone(), order.getDel_request(), order.getOrder_no());
                trigger.setProduct_id(product_id[i]);
                trigger.setSize(size[i]);
                trigger.setQuantity(Integer.parseInt(quan[i]));
                if(orderService.checkStock(product_id[i], quan[i], size[i])){
                    request.setAttribute("msg", "품절된 상품이 있습니다 확인 후 다시 시도해주세요.");
                    request.setAttribute("url", "productList");
                    return "alert";
                }

                list.add(trigger);
            }

            // 주문처리
            if(orderService.buy(list) != 1){
                throw new Exception("BUY_FAIL");
            }
            request.setAttribute("msg", "주문완료");
            request.setAttribute("url", "myPage/orderList");
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("msg", "주문에 실패했습니다.");
            request.setAttribute("url", "productList");
        }
        return "alert";
    }
}
