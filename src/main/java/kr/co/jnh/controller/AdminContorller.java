package kr.co.jnh.controller;

import kr.co.jnh.domain.PageHandler;
import kr.co.jnh.domain.Product;
import kr.co.jnh.domain.SearchCondition;
import kr.co.jnh.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminContorller {

    @Autowired
    ProductService productService;

    @GetMapping("product-mng")
    public String productMNG(HttpServletRequest request, SearchCondition sc, Model m){
        sc.setPageSize(20); // 한페이지에 가져오는 상품갯수 6개
        String keyword = request.getParameter("keyword");
        if(keyword != null){
            sc.setKeyword(keyword);
        }
        // option 기본값 product_id로 설정 (최신순 )
        if(sc.getOption() == null || sc.getOption().equals("")){
            sc.setOption("product_id");
        }
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
}
