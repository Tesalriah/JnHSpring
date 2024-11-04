package kr.co.jnh.controller;

import kr.co.jnh.domain.Order;
import kr.co.jnh.domain.PageHandler;
import kr.co.jnh.domain.Product;
import kr.co.jnh.domain.SearchCondition;
import kr.co.jnh.service.OrderService;
import kr.co.jnh.service.ProductService;
import kr.co.jnh.util.SessionIdUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("myPage")
public class MyPageContoller {

    @Autowired
    OrderService orderService;

    @Autowired
    ProductService productService;

    @GetMapping("orderList")
    public String mypage(HttpServletRequest request, SearchCondition sc){
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

            request.setAttribute("productList", productList);
            request.setAttribute("orderList", orderList);
            request.setAttribute("totalCnt", totalCnt);
            request.setAttribute("ph", ph);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return "myPage/orderList";
    }
}
