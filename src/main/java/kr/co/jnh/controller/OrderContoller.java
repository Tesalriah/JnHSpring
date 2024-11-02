package kr.co.jnh.controller;

import kr.co.jnh.domain.Order;
import kr.co.jnh.service.OrderService;
import kr.co.jnh.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
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

        String[] product_id = order.getProduct_id().split(",");
        String[] size = order.getSize().split(",");
        String[] quan = quantity.split(",");
        if(order.getDel_request().equals("")) {
            String referer = request.getHeader("referer");
            try {
                response.sendRedirect(referer);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        order.setUser_id(id);
        if(!address2.isBlank()){
            order.setAddress( order.getAddress() + address2);
        }
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
        String today = format.format(date);
        long order_no;
        try {
            if(orderService.orderIdCheck(today)){
                String str = orderService.returnId(today);
                order_no = Long.parseLong(str) + 1;
                if(order_no + "" == today + "999"){
                    throw new Exception("ORDER_NO_LIMITED");
                }
            }else{
                order_no = Long.parseLong(today + "001");
            }
            order.setOrder_no(order_no + "");
            List<Order> list = new ArrayList<>();
            for (int i = 0; i < product_id.length; i++) {
                Order trigger = new Order(order.getUser_id(),order.getName(),order.getAddress(), order.getPhone(), order.getDel_request(), order.getOrder_no());
                trigger.setProduct_id(product_id[i]);
                trigger.setSize(size[i]);
                trigger.setQuantity(Integer.parseInt(quan[i]));

                list.add(trigger);
            }

            if(orderService.buy(list) != 1){
                throw new Exception("BUY_FAIL");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        request.setAttribute("msg", "주문완료");
        request.setAttribute("url", "myPage/orderList");
        return "alert";
    }
}
