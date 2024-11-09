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
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("mypage")
public class MyPageContoller {

    @Autowired
    OrderService orderService;

    @Autowired
    ProductService productService;

    @Autowired
    UserService userService;

    @GetMapping("order-list")
    public String mypage(HttpServletRequest request, SearchCondition sc, Model m){
        HttpSession session = request.getSession(false);
        String id = (String)session.getAttribute("id");
        sc.setPageSize(5);
        HashMap map = new HashMap();
        map.put("id", id);
        map.put("pageSize", sc.getPageSize());
        map.put("offset", sc.getOffset());

        List<List<Order>> orderList = new ArrayList<>();
        List<List<Product>> productList = new ArrayList<>();
        try {
            int totalCnt = orderService.readCnt(map);
            List<Order> list = orderService.read(map);
            PageHandler ph = new PageHandler(totalCnt, sc);
            for (int i = 0; i < list.size(); i++) {
                map.put("order_no", list.get(i).getOrder_no());
                List<Order> each = orderService.readEach(map);
                List<Product> products = new ArrayList<>();
                for(int j = 0; j < each.size(); j++){
                    Product product = productService.getProduct(each.get(j).getProduct_id());
                    product.setQuantity(each.get(j).getQuantity());
                    products.add(product);
                }
                map.remove("order_no");
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

    @GetMapping("order-detail")
    public String orderDetail(@RequestParam(required = false) String order_no, @RequestParam(defaultValue = "1") int page, HttpServletRequest request, Model m){
        if(order_no == null){
            return "redirect:/mypage/order-list?page=" + page;
        }
        String id = SessionIdUtil.getSessionId(request);
        Map map = new HashMap();
        map.put("id", id);
        map.put("order_no", order_no);
        List<Product> productList = new ArrayList<>();
        int total = 0;

        try {
            List<Order> orderList = orderService.readOne(map);
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

    @PostMapping("order-del")
    public String orderDel(@RequestParam String order_no, @RequestParam(defaultValue = "1") int page, HttpServletRequest request, Model m){
        String id = SessionIdUtil.getSessionId(request);
        Map map = new HashMap();
        map.put("id", id);
        map.put("order_no", order_no);
        map.put("status", "삭제처리");

        try {
            orderService.updete(map);
        } catch (Exception e) {
            e.printStackTrace();
            m.addAttribute("msg", "삭제에 실패했습니다.");
            m.addAttribute("url", "order-list?page=" + page);
            return "alert";
        }
        return "redirect:/mypage/order-list?page=" + page;
    }

    @PostMapping("repurchase")
    public String repurchase(String product_id, String size, String quantity, HttpServletRequest request){
        String id = SessionIdUtil.getSessionId(request);
        String[] p_id = product_id.split(",");
        String[] product_size = size.split(",");
        String[] product_quantity = quantity.split(",");
        int total = 0;

        List<Product> list = new ArrayList();
        try {
            for(int i=0; i<p_id.length; i++){
                Product product = productService.getProduct(p_id[i]);
                product.setSize(product_size[i]);
                product.setQuantity(Integer.parseInt(product_quantity[i]));
                total += product.getTotal();
                list.add(product);
            }
            User user = userService.getUser(id);
            request.setAttribute("list", list);
            request.setAttribute("total", total);
            request.setAttribute("user", user);
        } catch (Exception e){
            e.printStackTrace();
        }
        return "product/payment";
    }
}
