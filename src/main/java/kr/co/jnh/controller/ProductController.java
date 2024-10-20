package kr.co.jnh.controller;

import kr.co.jnh.dao.ProductDao;
import kr.co.jnh.domain.PageHandler;
import kr.co.jnh.domain.Product;
import kr.co.jnh.domain.SearchCondition;
import kr.co.jnh.domain.User;
import kr.co.jnh.service.ProductService;
import kr.co.jnh.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
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
    UserService userService;

    @GetMapping("/product")
    public String product(@RequestParam String product_id, SearchCondition sc, Model m){
        m.addAttribute("sc", sc);
        String[] sizeFrame = {"XS", "S", "M", "L", "XL", "XXL", "XXXL"};
        try {
            Product product = productService.getProduct(product_id);
            product.setQuantity(1);
            m.addAttribute("product", product);
            List<String> list =  productService.getSize(product_id);
            List<String> sizeList =  new ArrayList<>();
            for(int i=0; i<sizeFrame.length; i++){
                for(int j=0; j<list.size(); j++){
                    if(sizeFrame[i].equals(list.get(j))){
                        sizeList.add(sizeFrame[i]);
                    }
                }
            }
            if(sizeList.size() != list.size()){
                sizeList = list;
            }
            m.addAttribute("sizeList", sizeList);
            return "product/productInfo";
        } catch (Exception e) {
            e.printStackTrace();
            return "product/productInfo";
        }
    }

    @PostMapping("product")
    public String PostProduct(String product_id, Integer quantity, String size, SearchCondition sc, HttpServletRequest request, RedirectAttributes rattr){
        HttpSession session = request.getSession();
        String id = (String)session.getAttribute("id");
        if(id == "" || id == null){
            rattr.addFlashAttribute("msg", "NEED_LOGIN");
            return "redirect:login";
        }

        try {
            if(size.equals("")){
                request.setAttribute("msg", "사이즈를 선택해주세요.");
                request.setAttribute("url", "product"+ sc.getQueryString() + "&product_id=" + product_id);
                throw new Exception("SIZE_IS_REQUIRED");
            }
            Product product = productService.getProduct(product_id);
            product.setQuantity(quantity);
            product.setSize(size);
            User user = userService.getUser(id);
            List<Product> list = new ArrayList<>();
            list.add(product);
            request.setAttribute("list", list);
            request.setAttribute("total", product.getTotal());
            request.setAttribute("user", user);
            return "product/payment";
        } catch (Exception e) {
            e.printStackTrace();
            return "alert";
        }
    }

    @GetMapping("/productList")
    public String productList(SearchCondition sc, Model m){
        sc.setPageSize(6);
        if(sc.getOption() == null || sc.getOption().equals("")){
            sc.setOption("product_id");
        }
        try {
            int totalCnt = productService.getSearchResultCnt(sc);
            m.addAttribute("totalCnt", totalCnt);
            PageHandler ph = new PageHandler(totalCnt, sc);

            List<Product> list = productService.getSearchSelectPage(sc);
            m.addAttribute("list", list);
            m.addAttribute("ph", ph);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "product/productList";
    }

    @GetMapping("/addProduct")
    public String getAddProduct(HttpServletRequest request){
        HttpSession session = request.getSession();
        String id = (String)session.getAttribute("id");
        try {
            if(userService.getGrade(id) == 0){
                return "product/addProduct";
            }else{
                request.setAttribute("msg", "관리자만 접근가능합니다.");
                request.setAttribute("url", "productList");
                throw new Exception("NOT_ADMIN");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "alert";
        }
    }

    @PostMapping("/addProduct")
    public String addProduct(Product product, HttpServletRequest request, @RequestParam("uploadFile")MultipartFile file){
        String msg = "";
        msg = productValidation(product, file);
        if(!msg.isBlank()){
            request.setAttribute("msg", msg);
            request.setAttribute("product_name", product.getProduct_name());
            request.setAttribute("price", product.getPrice());
            request.setAttribute("discount", product.getDiscount());
            request.setAttribute("color", product.getColor());
            return "product/addProduct";
        }
        int result = -1;
        String[] sizeArr = product.getSize().split(",");
        String[] stockArr = product.getStock().split(",");
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
            String savePath = "C:/Users/Tesalriah/IdeaProjects/JnHSpring/src/main/webapp/resources/img/upload/";
            savePath += product_id + "/";
            Path directory = Paths.get(savePath);
            Files.createDirectories(directory);
            // 이미지 파일이름을 product_id로 변경하여 저장
            String originalFileName = file.getOriginalFilename();
            String[] fileNameArr = originalFileName.split("\\.");
            fileNameArr[0] = product_id + "";
            originalFileName = fileNameArr[0] + "." + fileNameArr[1];
            File newFile = new File(savePath + originalFileName);
            file.transferTo(newFile);

            product.setImage(originalFileName);

            if(sizeArr.length > 1 || stockArr.length > 1){
                for(int i=0; i<sizeArr.length; i++){
                    product.setSize(sizeArr[i]);
                    product.setStock(stockArr[i]);
                    result = productService.addProduct(product);
                }
            }else{
                result = productService.addProduct(product);
            }
            if(result != 1){
                throw new Exception("ADD_FAIL");
            }
            return "redirect:/productList";
        } catch (Exception e) {
            e.printStackTrace();
            return "product/addProduct";
        }
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
