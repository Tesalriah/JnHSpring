package kr.co.jnh.controller;

import kr.co.jnh.domain.PageHandler;
import kr.co.jnh.domain.Product;
import kr.co.jnh.domain.SearchCondition;
import kr.co.jnh.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;

@Controller
@RequestMapping("/admin")
public class AdminContorller {

    @Autowired
    ProductService productService;

    @GetMapping("product-mng")
    public String productMNG(HttpServletRequest request, SearchCondition sc, Model m){
        sc.setPageSize(20); // 한페이지에 가져오는 상품갯수 6개

        // option 기본값 product_id로 설정 (최신순 )
        if(sc.getOption() == null || sc.getOption().equals("")){
            sc.setOption("product_id");
        }
        System.out.println(sc.toString());
        try {
            // SearchCondition 정보에 따른 총 상품갯수 가져오기
            int totalCnt = productService.getProductAdminCnt(sc);
            m.addAttribute("totalCnt", totalCnt);
            // SearchCondition과 총 상품갯수를 PageHandler에 할당하여 페이징처리 계산
            PageHandler ph = new PageHandler(totalCnt, sc);

            // SearchCondition 정보에 따른 상품 list에 저장(20개)
            List<Product> list = productService.getProductAdmin(sc);
            System.out.println("list = " + list);
            System.out.println(totalCnt);
            m.addAttribute("list", list);
            m.addAttribute("ph", ph);
        }catch (Exception e){
            e.printStackTrace();
        }
        return "/admin/product-mng";
    }

    @PostMapping("setPrice")
    @ResponseBody
    public Map<String, Object> setPrice(@RequestBody Map<String, Object> map){
        String product_id = (String)map.get("product_id");
        String size = (String)map.get("size");
        String dynamic_value = (String)map.get("dynamic_value");
        String type = (String)map.get("type");

        System.out.println("dynamic_value = " + dynamic_value);
        System.out.println("size = " + size);
        System.out.println("product_id = " + product_id);
        System.out.println("type = " + type);

        return map;
    }

}
