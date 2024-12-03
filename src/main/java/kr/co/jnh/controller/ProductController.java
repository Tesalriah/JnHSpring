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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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

    // 상품정보 읽어오기
    @GetMapping("product")
    public String product(@RequestParam String product_id, SearchCondition sc, Model m){
        m.addAttribute("sc", sc);
        String[] sizeFrame = {"XS", "S", "M", "L", "XL", "XXL", "XXXL"}; // 사이즈 순으로 정렬하기 위해 선언
        try {
            // 상품 id를 통해 상품의 정보 가져오기
            Product product = productService.getProduct(product_id);
            product.setQuantity(1); // 할인된 가격 계산을위해 1개의 갯수 설정
            m.addAttribute("product", product);

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
        String id = SessionIdUtil.getSessionId(request);
        product_id = product_id.split(",")[0];

        try {
            if(size.equals("")){
                m.addAttribute("msg", "사이즈를 선택해주세요.");
                m.addAttribute("url", "product"+ sc.getQueryString() + "&product_id=" + product_id);
                throw new Exception("SIZE_IS_REQUIRED");
            }
            Product product = productService.getProduct(product_id); // 해당 상품정보 가져오기
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
            m.addAttribute("msg", "상품을 읽어오는데 실패했습니다.");
            m.addAttribute("url", "/product" + sc.getQueryString());
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

            // SearchCondition 정보에 따른 상품가져오기 (6개씩)
            List<Product> list = productService.getSearchSelectPage(sc);
            m.addAttribute("list", list);
            m.addAttribute("ph", ph);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "product/product-list";
    }

    // 상품 추가 페이지 이동 (인터셉터로 관리자 확인)
    @GetMapping("add-product")
    public String getAddProduct(){
        return "product/add-product";
    }

    // 상품 추가
    @PostMapping("/add-product")
    public String addProduct(Product product, HttpServletRequest request, @RequestParam("uploadFile")MultipartFile file, Model m){
        // 메서드를 이용하여 검증
        String msg = productValidation(product, file);
        if(!msg.isBlank()){
            m.addAttribute("msg", msg);
            m.addAttribute("product_name", product.getProduct_name());
            m.addAttribute("price", product.getPrice());
            m.addAttribute("discount", product.getDiscount());
            m.addAttribute("color", product.getColor());
            return "product/add-product";
        }
        int result = -1;
        // 사이즈, 갯수가 여러개일 경우 각각의 배열에 저장
        String[] sizeArr = product.getSize().split(",");
        String[] stockArr = product.getStock().split(",");
        // 상품 id를 생성하기위해 현재 날짜 가져오기
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
        String today = format.format(date);
        long product_id;

        try {
            // 현재 날짜에 생성한 상품이 없으면 현재날짜001로 생성, 상품이 있으면 마지막 product_id에 +1한 product_id값 생성 일일 최대 999개 생성
            if(productService.productIdCheck(today)){
                String product_id_str = productService.returnId(today);
                product_id = Long.parseLong(product_id_str);
                product_id += 1;
                if(product_id + "" == today + "999"){
                    throw new Exception("PRODUCT_LIMITED");
                }
            }else{
                product_id = Long.parseLong(today + "001");
            }
            product.setProduct_id(product_id + "");
            product.setState("판매");

            // 이미지를 폴더에 저장하기 위해 경로 위에 폴더 생성
            String savePath = request.getServletContext().getRealPath("resources/img/upload/");
            savePath += product_id + "/";
            Path directory = Paths.get(savePath);
            Files.createDirectories(directory);
            // 이미지 파일의 확장자를 나누고 이름을 product_id로 바꾸어 확장자와 함께 저장
            String originalFileName = file.getOriginalFilename();
            String[] fileNameArr = originalFileName.split("\\.");
            fileNameArr[0] = product_id + "";
            originalFileName = fileNameArr[0] + "." + fileNameArr[1];
            File newFile = new File(savePath + originalFileName);
            file.transferTo(newFile);

            // 바꾼 이미지이름+확장자를 product.image에 set
            product.setImage(originalFileName);

            // 사이즈가 하나가 아닐때 모든 정보 데이터베이스에 추가
            if(sizeArr.length > 1 || stockArr.length > 1){
                for(int i=0; i<sizeArr.length; i++){
                    product.setSize(sizeArr[i]);
                    product.setStock(stockArr[i]);
                    result = productService.addProduct(product);
                }
            }else{ // 사이즈가 하나일때
                result = productService.addProduct(product);
            }
            if(result != 1){
                throw new Exception("ADD_FAIL");
            }
            m.addAttribute("msg", "상품을 등록하였습니다.");
            m.addAttribute("url", "product-list");
            return "alert";
        } catch (Exception e) {
            e.printStackTrace();
            m.addAttribute("msg", "상품 등록에 실패했습니다.");
            return "product/add-product";
        }
    }

    // 상품 결제 처리
    @PostMapping("/buy")
    public String buy(Order order, String quantity, String address2, HttpServletRequest request, HttpServletResponse response){
        String id = SessionIdUtil.getSessionId(request);

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
                Order trigger = new Order(order.getUser_id(),order.getName(),order.getAddress(),
                        order.getPhone(), order.getDel_request(), order.getOrder_no()); // 공통된 항목 trigger에 저장
                // 하나 또는 그 이상의 주문상품 저장부분
                trigger.setProduct_id(product_id[i]);
                trigger.setSize(size[i]);
                trigger.setQuantity(Integer.parseInt(quan[i]));
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
            request.setAttribute("url", "mypage/order-list");
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("msg", "주문에 실패했습니다.");
            request.setAttribute("url", "product-list");
        }
        return "alert";
    }

    // 주문했던 상품 재구매
    @PostMapping("/repurchase")
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


    private String productValidation(Product product, MultipartFile file){
        if(product.getProduct_name().isBlank() || product.getGender().isBlank() || product.getCategory().isBlank()
            || product.getColor().isBlank() || (product.getSize() == null || product.getSize().isEmpty())
            || (product.getStock() == null || product.getStock().isEmpty()) || (product.getPrice() < 1
            || product.getPrice() == null)){
            return "모든 값을 입력해주세요.";
        }
        String[] arr = product.getSize().split(",");
        String[] arr2 = product.getStock().split(",");
        if(arr.length < 1 || arr2.length < 1){
            return "모든 값을 입력해주세요.";
        }
        if(file.isEmpty()){
            return "이미지를 추가해주세요.";
        }
        if(product.getPrice() > 1000000000){
            return "가격은 1,000,000,000을 초과 할 수 없습니다.";
        }
        if((product.getDiscount() < 0 || product.getDiscount() > 90) && product.getDiscount() != null){
            return "할인율은 90까지 입력가능합니다.";
        }

        if(product.getDiscount() == null){
            product.setDiscount(0);
        }
        return "";
    }
}
