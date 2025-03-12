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
import java.net.http.HttpRequest;
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
            m.addAttribute("list", list);
            m.addAttribute("ph", ph);
        }catch (Exception e){
            e.printStackTrace();
        }
        return "/admin/product-mng";
    }

    @PostMapping("update")
    @ResponseBody
    public Map<String, Object> update(@RequestBody Map<String, Object> map, HttpServletRequest request){
        // 상품을 구별하는 product_id와 size는 map에 이미 할당되어있으므로 type에 따른 동적값을 map에 저장
        String dynamic_value = (String)map.get("dynamic_value");
        String type = (String)map.get("type");
        map.put(type, dynamic_value);

        map.forEach((key, value) -> System.out.println(key + ": " + value));

        if(!type.equals("stock")){ // stock(재고)를 특정사이즈가 아닌 해당 product_id의 모든 값을 변경
            map.remove("size");
        }

        try {
            if(productService.updateProduct(map) <= 0){
                throw new Exception("PRODUCT_UPDATE_FAIL");
            }
            map.put("msg", "값이 변경되었습니다.");
        } catch (Exception e) {
            e.printStackTrace();
            map.put("msg", "변경에 실패했습니다.");
        }

        return map;
    }

}
