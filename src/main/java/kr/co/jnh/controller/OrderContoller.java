package kr.co.jnh.controller;

import kr.co.jnh.domain.*;
import kr.co.jnh.service.OrderService;
import kr.co.jnh.service.ProductService;
import kr.co.jnh.service.ReturnsService;
import kr.co.jnh.service.UserService;
import kr.co.jnh.util.SessionIdUtil;
import org.checkerframework.checker.units.qual.C;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping("/mypage/order")
public class OrderContoller {

    @Autowired
    OrderService orderService;

    @Autowired
    ProductService productService;

    @Autowired
    ReturnsService returnsService;

    @Autowired
    UserService userService;

    // 주문목록 가져오기
    @GetMapping("list")
    public String mypage(HttpServletRequest request, SearchCondition sc, Model m) {
        String id = SessionIdUtil.getSessionId(request);
        sc.setPageSize(5); // 한 페이지에 5개의 주문
        HashMap map = new HashMap();
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
                map.remove("order_no"); // 할당된 order_no 초기화
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
        String id = SessionIdUtil.getSessionId(request);
        Map map = new HashMap();
        map.put("id", id);
        map.put("order_no", order_no);

        try {
            int total = 0; // 총 상품가격
            List<Order> orderList = orderService.readOne(map); // 한 주문번호의 주문 상품들을 가져오기
            for(Order order : orderList){
                if(order.getStatus().equals("삭제처리")){
                    m.addAttribute("msg", "삭제된 주문입니다.");
                    m.addAttribute("url", "list?page=" + page);
                    return "alert";
                }
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
        String id = SessionIdUtil.getSessionId(request);
        Map map = new HashMap();
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

}
