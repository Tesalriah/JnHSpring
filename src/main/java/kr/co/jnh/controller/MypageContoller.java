package kr.co.jnh.controller;

import kr.co.jnh.domain.*;
import kr.co.jnh.service.*;
import kr.co.jnh.util.SessionIdUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping("/mypage")
public class MypageContoller {

    @Autowired
    OrderService orderService;

    @Autowired
    ProductService productService;

    @Autowired
    ReturnsService returnsService;

    @Autowired
    UserService userService;

    // 주문목록 가져오기
    @GetMapping("order-list")
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
    @GetMapping("order-detail")
    public String orderDetail(@RequestParam(required = false) String order_no, @RequestParam(defaultValue = "1") int page, HttpServletRequest request, Model m) {
        if (order_no == null) { // 받아온 order_no이 없을때 list로 리다이렉트
            return "redirect:/mypage/order-list?page=" + page;
        }
        String id = SessionIdUtil.getSessionId(request);
        Map map = new HashMap();
        map.put("id", id);
        map.put("order_no", order_no);

        try {
            int total = 0; // 총 상품가격
            List<Order> orderList = orderService.readOne(map); // 한 주문번호의 주문 상품들을 가져오기
            for(Order order : orderList){
                total += order.getProduct().getTotal();
            }
            m.addAttribute("orderList", orderList);
            m.addAttribute("total", total);
            m.addAttribute("page", page);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "mypage/order-detail";
    }

    // 주문 삭제처리
    @PostMapping("order-del")
    public String orderDel(@RequestParam String order_no, @RequestParam(defaultValue = "1") int page, HttpServletRequest request, Model m) {
        String id = SessionIdUtil.getSessionId(request);
        Map map = new HashMap();
        map.put("id", id);
        map.put("order_no", order_no);
        map.put("status", "삭제처리");

        // 해당 주문번호의 status를 모두 삭체처리로 update
        try {
            if (orderService.updete(map) <= 0) { // update 실패 시 Exception 발생
                throw new Exception("ORDER_DEL_FAIL");
            }
        } catch (Exception e) {
            e.printStackTrace();
            m.addAttribute("msg", "삭제에 실패했습니다.");
            m.addAttribute("url", "order-list?page=" + page);
            return "alert";
        }
        return "redirect:/mypage/order-list?page=" + page;
    }

    // 주문했던 상품 재구매
    @PostMapping("repurchase")
    public String repurchase(String product_id, String size, String quantity, HttpServletRequest request) {
        String id = SessionIdUtil.getSessionId(request);
        // 받아온 상품id, 사이즈, 갯수가 여러개일 수 있으므로 배열에 각각 저장
        String[] p_id = product_id.split(",");
        String[] product_size = size.split(",");
        String[] product_quantity = quantity.split(",");
        int total = 0;

        try {
            List<Product> list = new ArrayList();
            for (int i = 0; i < p_id.length; i++) { // 받아온 정보를 토대로 객체에 할당하여 각각 list에 추가
                Product product = productService.getProduct(p_id[i]);
                product.setSize(product_size[i]);
                product.setQuantity(Integer.parseInt(product_quantity[i]));
                total += product.getTotal();
                list.add(product);
            }
            User user = userService.getUser(id); // 배송지 정보를 할당하기위해 user정보 가져오기
            request.setAttribute("list", list);
            request.setAttribute("total", total);
            request.setAttribute("user", user);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "product/payment";
    }

    // 교환 또는 반품하는 상품을 선택하는 step1페이지
    @PostMapping("return-step1")
    public String returnStep1(@RequestParam(required = false) String order_no, @RequestParam(required = false) int page, HttpServletRequest request, Model m) {
        if (order_no == null) { // 받아온 order_no이 없을때 list로 리다이렉트
            return "redirect:/mypage/order-list?page=" + page;
        }
        String id = SessionIdUtil.getSessionId(request);
        Map map = new HashMap();
        map.put("id", id);
        map.put("order_no", order_no);

        try {
            List<Order> orderList = orderService.readOne(map);

            m.addAttribute("orderList", orderList);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "mypage/return-step1";
    }

    @PostMapping("return-step2")
    public String returnStep2(@RequestParam(required = false) String order_no, @RequestParam(required = false) String check_box, HttpServletRequest request, Model m) {
        String id = SessionIdUtil.getSessionId(request);
        String[] sizeFrame = {"XS", "S", "M", "L", "XL", "XXL", "XXXL"}; // 사이즈 순으로 정렬하기 위해 선언
        Map map = new HashMap();
        map.put("id", id);
        map.put("order_no", order_no);
        String[] checkBox = check_box.split(",");

        try {
            User user = userService.getUser(id);
            List<Order> orderList = orderService.readOne(map);
            List<Order> realList = new ArrayList<>(); // 선택된 주문 목록
            List<List<String>> sizeList = new ArrayList<>(); // 상품이 가지고있는 정렬된 사이즈 목록

            for (int i = 0; i < orderList.size(); i++) {
                for (int j = 0; j < checkBox.length; j++) {
                    if (Integer.parseInt(checkBox[j]) == i) { // 선택한 항목만 처리하여 realList에 저장
                        realList.add(orderList.get(i));
                        String product_id = orderList.get(i).getProduct_id();
                        List<String> list =  productService.getSize(product_id);

                        List<String> afterSize = new ArrayList<>(); // 정렬하기 이전의 사이즈
                        for(int k=0; k<sizeFrame.length; k++) {
                            for (int n = 0; n < list.size(); n++) {
                                if (sizeFrame[k].equals(list.get(n))) {
                                    afterSize.add(sizeFrame[k]);
                                }
                            }
                        }
                        sizeList.add(afterSize); // 정렬된 사이즈 추가
                    }
                }
            }
            m.addAttribute("user", user);
            m.addAttribute("orderList", realList);
            m.addAttribute("sizeList", sizeList);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "mypage/return-step2";
    }

    @PostMapping("return")
    public String returns(Returns returns, @RequestParam String quan, HttpServletRequest request){
        String id = SessionIdUtil.getSessionId(request);
        returns.setUser_id(id);
        System.out.println("returns.toString() = " + returns.toString());
        System.out.println("quan = " + quan);
        String[] productIdArr = returns.getProduct_id().split(",");
        String[] sizeArr = returns.getSize().split(",");
        String[] cSizeArr = returns.getC_size().split(",");
        String[] quanArr = quan.split(",");

        // 현재날짜 + 001~999까지의 세자리 수로 return_id 만들기
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
        String today = format.format(date);
        long return_id;

        return "redirect:/mypage/return-list";
    }

    @GetMapping("return-list")
    public String returnList(SearchCondition sc, HttpServletRequest request, Model m){
        sc.setPageSize(5);
        String id = SessionIdUtil.getSessionId(request);

        Map map = new HashMap();
        map.put("sc", sc);
        map.put("id", id);

        try {
            int totalCnt = returnsService.count(id);
            PageHandler ph = new PageHandler(totalCnt, sc);

            List<Returns> returnsList = returnsService.read(map);

            m.addAttribute("returnsList" ,returnsList);
            m.addAttribute("ph", ph);
            m.addAttribute("totalCnt", totalCnt);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "mypage/return-list";
    }
}
