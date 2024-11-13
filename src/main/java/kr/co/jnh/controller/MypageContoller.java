package kr.co.jnh.controller;

import kr.co.jnh.domain.*;
import kr.co.jnh.service.OrderService;
import kr.co.jnh.service.ProductService;
import kr.co.jnh.service.UserService;
import kr.co.jnh.util.SessionIdUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("mypage")
public class MypageContoller {

    @Autowired
    OrderService orderService;

    @Autowired
    ProductService productService;

    @Autowired
    UserService userService;

    // 주문목록 가져오기
    @GetMapping("order-list")
    public String mypage(HttpServletRequest request, SearchCondition sc, Model m){
        String id = SessionIdUtil.getSessionId(request);
        sc.setPageSize(5); // 한 페이지에 5개의 주문
        HashMap map = new HashMap();
        map.put("id", id);
        map.put("sc", sc);

        List<List<Order>> orderList = new ArrayList<>(); // 하나의 주문에 여러가지 상품이 있을 수도 있으므로 List<Order>를 리스트로 덮어서 분류
        List<List<Product>> productList = new ArrayList<>(); // Order를 보여줄때 Product에 있는 갯수에 따른 가격정보와 이미지 정보를 가져오기 위해 Product도 Order와 같은 모양의 리스트로 저장
        try {
            // 페이징 처리를 하기위해 해당 id의 총 주문의 갯수
            int totalCnt = orderService.readCnt(map);
            PageHandler ph = new PageHandler(totalCnt, sc); // 총 주문갯수를 SearchCondition에 따라 PageHandler로 페이징 처리

            List<Order> list = orderService.read(map); // 일단 SearchCondition에 따른 해당 아이디의 5개의 다른 주문번호의 정보 가져오기
            for (int i = 0; i < list.size(); i++) {
                // 각 주문번호의 주문상품들을 each에 추가
                map.put("order_no", list.get(i).getOrder_no());
                List<Order> each = orderService.readEach(map);
                List<Product> products = new ArrayList<>();
                for(int j = 0; j < each.size(); j++){  // each에 저장된 목록의 상품id를 통해 product 또한 추가
                    Product product = productService.getProduct(each.get(j).getProduct_id());
                    product.setQuantity(each.get(j).getQuantity());
                    products.add(product);
                }
                map.remove("order_no"); // 할당된 order_no 초기화
                // 저장된 product와 order정보를 각각의 List에 추가
                productList.add(products);
                orderList.add(each);
            }

            m.addAttribute("productList", productList);
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
    public String orderDetail(@RequestParam(required = false) String order_no, @RequestParam(defaultValue = "1") int page, HttpServletRequest request, Model m){
        if(order_no == null){ // 받아온 order_no이 없을때 list로 리다이렉트
            return "redirect:/mypage/order-list?page=" + page;
        }
        String id = SessionIdUtil.getSessionId(request);
        Map map = new HashMap();
        map.put("id", id);
        map.put("order_no", order_no);

        try {
            int total = 0; // 총 상품가격
            List<Order> orderList = orderService.readOne(map); // 한 주문번호의 주문 상품들을 가져오기
            // 갯수를 set하여 얻어온 상품 가격 총 상품가격에 더하고 주문상품들의 정보를 productList에 추가
            List<Product> productList = new ArrayList<>();
            for (int i = 0; i <  orderList.size(); i++) {
                Product product = productService.getProduct(orderList.get(i).getProduct_id());
                product.setQuantity(orderList.get(i).getQuantity());
                productList.add(product);
                total += product.getTotal();
            }
            m.addAttribute("orderList", orderList);
            m.addAttribute("productList", productList);
            m.addAttribute("total", total);
            m.addAttribute("page",page);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "mypage/order-detail";
    }

    // 주문 삭제처리
    @PostMapping("order-del")
    public String orderDel(@RequestParam String order_no, @RequestParam(defaultValue = "1") int page, HttpServletRequest request, Model m){
        String id = SessionIdUtil.getSessionId(request);
        Map map = new HashMap();
        map.put("id", id);
        map.put("order_no", order_no);
        map.put("status", "삭제처리");

        // 해당 주문번호의 status를 모두 삭체처리로 update
        try {
            if(orderService.updete(map) <= 0){ // update 실패 시 Exception 발생
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
    public String repurchase(String product_id, String size, String quantity, HttpServletRequest request){
        String id = SessionIdUtil.getSessionId(request);
        // 받아온 상품id, 사이즈, 갯수가 여러개일 수 있으므로 배열에 각각 저장
        String[] p_id = product_id.split(",");
        String[] product_size = size.split(",");
        String[] product_quantity = quantity.split(",");
        int total = 0;

        try {
            List<Product> list = new ArrayList();
            for(int i=0; i<p_id.length; i++){ // 받아온 정보를 토대로 객체에 할당하여 각각 list에 추가
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
        } catch (Exception e){
            e.printStackTrace();
        }
        return "product/payment";
    }

    // 교환 또는 반품하는 상품을 선택하는 step1페이지
    @PostMapping("return-step1")
    public String returnStep1(@RequestParam(required = false) String order_no, int page, HttpServletRequest request){
        if(order_no == null){ // 받아온 order_no이 없을때 list로 리다이렉트
            return "redirect:/mypage/order-list?page=" + page;
        }
        String id = SessionIdUtil.getSessionId(request);


        return "return-step1";
    }
}
