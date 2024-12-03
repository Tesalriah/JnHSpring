package kr.co.jnh.controller;

import kr.co.jnh.domain.*;
import kr.co.jnh.service.OrderService;
import kr.co.jnh.service.ProductService;
import kr.co.jnh.service.ReturnsService;
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
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping("/mypage/return")
public class ReturnsContorller {

    @Autowired
    ReturnsService returnsService;
    @Autowired
    OrderService orderService;

    @Autowired
    UserService userService;

    @Autowired
    ProductService productService;

    // 교환 또는 반품하는 상품을 선택하는 step1페이지
    @PostMapping("step1")
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

    @PostMapping("step2")
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

    @PostMapping
    public String returns(Returns returns, @RequestParam String quan, HttpServletRequest request){
        String id = SessionIdUtil.getSessionId(request);
        returns.setUser_id(id);
        System.out.println("returns.getProduct_id() = " + returns.getProduct_id());
        String type = "";
        if(returns.getType().equals("exchange")){
            type = "교환";
        }if(returns.getType().equals("return")){
            type = "반품";
        }
        String[] productIdArr = returns.getProduct_id().split(",");
        String[] sizeArr = returns.getSize().split(",");
        String[] cSizeArr = returns.getC_size().split(",");
        String[] quanArr = quan.split(",");
        String address2 = request.getParameter("address2");
        // 상세주소를 입력했을 시 추가
        if(!address2.isBlank()){
            returns.setAddress( returns.getAddress() + address2);
        }

        // 현재날짜 + 001~999까지의 세자리 수로 return_id 만들기
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
        String today = format.format(date);
        long return_id;

        try{
            Map map = new HashMap();
            map.put("id", returns.getUser_id());
            map.put("order_no", returns.getOrder_no());
            List<Order> orderList = orderService.readOne(map);
            returns.setOrder_date(orderList.get(0).getOrder_date());
            String return_id_str = returnsService.readId(today);
            if(return_id_str != null){
                return_id = Long.parseLong(return_id_str);
                return_id += 1;
                if(return_id + "" == today + "999"){
                    throw new Exception("RETURNS_LIMITED");
                }
            }else{
                return_id = Long.parseLong(today + "001");
            }
            returns.setReturn_id(return_id + "");

            // list에 반품,교환 상품 저장
            List<Returns> list = new ArrayList<>();
            for (int i = 0; i < productIdArr.length; i++) {
                returns.setProduct_id(productIdArr[i]);
                returns.setSize(sizeArr[i]);
                returns.setQuantity(Integer.parseInt(quanArr[i]));
                if(returns.getType().equals("exchange")){
                    returns.setC_size(cSizeArr[i]);
                }
                Returns setReturns = new Returns(returns);
                list.add(setReturns);
            }

            if(returnsService.returns(list) != 1){
                throw new Exception("RETURN_FAIL");
            }
            request.setAttribute("msg", type + "신청 완료");
            request.setAttribute("url", "return-list");
        }catch(Exception e){
            e.printStackTrace();
            request.setAttribute("msg", type + "에 실패했습니다.");
            request.setAttribute("url", "order-list");
        }
        return "alert";
    }

    @GetMapping("list")
    public String returnList(SearchCondition sc, HttpServletRequest request, Model m){
        sc.setPageSize(5);
        String id = SessionIdUtil.getSessionId(request);

        Map map = new HashMap();
        map.put("sc", sc);
        map.put("id", id);

        try {
            int totalCnt = returnsService.count(id);
            PageHandler ph = new PageHandler(totalCnt, sc);

            List<List<Returns>> returnsList = returnsService.read(map);

            m.addAttribute("returnsList" ,returnsList);
            m.addAttribute("ph", ph);
            m.addAttribute("totalCnt", totalCnt);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "mypage/return-list";
    }
}