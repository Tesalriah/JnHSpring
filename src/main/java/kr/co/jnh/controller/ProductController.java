package kr.co.jnh.controller;

import kr.co.jnh.domain.*;
import kr.co.jnh.service.OrderService;
import kr.co.jnh.service.ProductService;
import kr.co.jnh.service.UserService;
import kr.co.jnh.service.WishService;
import kr.co.jnh.util.FileMultiSaveUtil;
import kr.co.jnh.util.SessionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
public class ProductController {
    @Autowired
    ProductService productService;
    @Autowired
    OrderService orderService;
    @Autowired
    UserService userService;
    @Autowired
    WishService wishService;

    // 상품정보 읽어오기
    @GetMapping("product")
    public String product(@RequestParam String product_id, SearchCondition sc, Model m, HttpServletRequest request){
        String id = SessionUtils.getSessionId(request);
        m.addAttribute("sc", sc);
        String[] sizeFrame = {"XS", "S", "M", "L", "XL", "XXL", "XXXL"}; // 사이즈 순으로 정렬하기 위해 선언
        try {
            if(id != null){
                Wish wish = new Wish(id, product_id);
                if(wishService.isThere(wish)){
                    m.addAttribute("wish", true);
                }
            }
            // 상품 id를 통해 상품의 정보 가져오기
            Product product = productService.getProduct(product_id);
            product.setQuantity(1); // 할인된 가격 계산을위해 1개의 갯수 설정
            m.addAttribute( "product", product);

            List<String> list =  productService.getSize(product_id); // 해당 상품id가 가지고있는 사이즈를 List에 받기
            List<String> sizeList =  new ArrayList<>(); // 정렬받기 위한 List
            // sizeFrame의 반복문을 돌면서 일치하는 항목이 있을 시 sizeList에 추가
            for(int i=0; i<sizeFrame.length; i++){
                for(int j=0; j<list.size(); j++){
                    if(sizeFrame[i].equals(list.get(j))){
                        sizeList.add(sizeFrame[i]);
                    }
                }
            }
            if(sizeList.size() != list.size()){ // 두 사이즈의 갯수가 맞지않으면 이전에 받아온 list 그대로 사용
                sizeList = list;
            }
            m.addAttribute("sizeList", sizeList);
            return "product/product-info";
        } catch (Exception e) {
            e.printStackTrace();
            m.addAttribute("msg", "상품을 읽어오는데 실패했습니다.");
            m.addAttribute("url", "/product-list" + sc.getQueryString());
            return "alert";
        }
    }

    // 해당 상품 결제 페이지 이동
    @PostMapping("product")
    public String PostProduct(@RequestParam String product_id, Integer quantity, String size, SearchCondition sc, HttpServletRequest request, Model m){
        String id = SessionUtils.getSessionId(request);
        product_id = product_id.split(",")[0];

        try {
            if(size.equals("")){
                m.addAttribute("msg", "사이즈를 선택해주세요.");
                m.addAttribute("url", "product"+ sc.getQueryString() + "&product_id=" + product_id);
                throw new Exception("SIZE_IS_REQUIRED");
            }
            Product product = productService.getProduct(product_id); // 해당 상품정보 가져오기
            if(product == null){
                m.addAttribute("msg", "상품을 읽어오는데 실패했습니다.");
                m.addAttribute("url", "/product" + sc.getQueryString());
                throw new Exception("BRING_PRODUCT_FAIL");
            }
            product.setQuantity(quantity); // 가격 계산을위해 갯수 지정
            product.setSize(size); // 구매하는 사이즈
            User user = userService.getUser(id); // 배송지 정보를 받기위해 유저정보 가져오기
            // List에 담아 보내기 (Cart에서도 동일한 jsp를 사용하기때문)
            List<Product> list = new ArrayList<>();
            list.add(product);
            m.addAttribute("list", list);
            m.addAttribute("total", product.getTotal());
            m.addAttribute("user", user);
            return "product/payment";
        } catch (Exception e) {
            e.printStackTrace();
            return "alert";
        }
    }

    // 상품목록 읽어오기
    @GetMapping("product-list")
    public String productList(SearchCondition sc, Model m){
        sc.setPageSize(6); // 한페이지에 가져오는 상품갯수 6개
        // option 기본값 product_id로 설정 (최신순 )
        if(sc.getOption() == null || sc.getOption().equals("")){
            sc.setOption("product_id");
        }
        try {
            // SearchCondition 정보에 따른 총 상품갯수 가져오기
            int totalCnt = productService.getSearchResultCnt(sc);
            m.addAttribute("totalCnt", totalCnt);
            // SearchCondition과 총 상품갯수를 PageHandler에 할당하여 페이징처리 계산
            PageHandler ph = new PageHandler(totalCnt, sc);
            ph.setNaviSize(5);
            ph.doPaging(totalCnt, sc);

            // SearchCondition 정보에 따른 상품가져오기 (6개씩)
            List<Product> list = productService.getSearchSelectPage(sc);
            m.addAttribute("list", list);
            m.addAttribute("ph", ph);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "product/product-list";
    }

    // 주문했던 상품 재구매
    @PostMapping("/repurchase")
    public String repurchase(String product_id, String size, String quantity, HttpServletRequest request) {
        String id = SessionUtils.getSessionId(request);
        // 받아온 상품id, 사이즈, 갯수가 여러개일 수 있으므로 배열에 각각 저장
        String[] p_id = product_id.split(",");
        String[] product_size = size.split(",");
        String[] product_quantity = quantity.split(",");
        int total = 0;

        try {
            List<Product> list = new ArrayList<>();
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

    /*// 상품 결제 처리
    @PostMapping("/buy")
    public String buy(Order order, String quantity, String address2, HttpServletRequest request, HttpServletResponse response){
        String id = SessionUtils.getSessionId(request);

        // 하나이상의 주문을 각각 처리하기 위해 배열에 나눠서 저장
        String[] product_id = order.getProduct_id().split(",");
        String[] size = order.getSize().split(",");
        String[] quan = quantity.split(",");
        order.setUser_id(id);
        // 상세주소를 입력했을 시 추가
        if(!address2.isBlank()){
            order.setAddress(order.getAddress() + address2);
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
                Order trigger = new Order(order.getUser_id(),order.getName(),order.getAddress(),
                        order.getPhone(), order.getDel_request(), order.getOrder_no()); // 공통된 항목 trigger에 저장
                // 하나 또는 그 이상의 주문상품 저장부분
                Product product = productService.getProduct(product_id[i]);
                trigger.setProduct_id(product_id[i]);
                trigger.setSize(size[i]);
                trigger.setQuantity(Integer.parseInt(quan[i]));
                trigger.setColor(product.getColor());
                // 품절된 상품 확인
                if(orderService.checkStock(product_id[i], quan[i], size[i])){
                    request.setAttribute("msg", "상품번호 : " + product_id[i] + " 상품이 품절되었거나 재고가 모자릅니다. 확인 후 다시 시도해주세요.");
                    request.setAttribute("url", "product-list");
                    return "alert";
                }

                list.add(trigger);
            }

            // 주문처리
            if(orderService.buy(list) != 1){
                throw new Exception("BUY_FAIL");
            }
            request.setAttribute("msg", "주문완료");
            request.setAttribute("url", "mypage/order/list");
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("msg", "주문에 실패했습니다.");
            request.setAttribute("url", "product-list");
        }
        return "alert";
    }*/
}
