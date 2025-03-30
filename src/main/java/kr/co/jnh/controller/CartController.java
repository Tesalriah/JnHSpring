package kr.co.jnh.controller;

import kr.co.jnh.domain.*;
import kr.co.jnh.service.CartService;
import kr.co.jnh.service.ProductService;
import kr.co.jnh.service.UserService;
import kr.co.jnh.util.SessionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Controller
public class CartController {
    @Autowired
    CartService cartService;
    @Autowired
    UserService userService;
    @Autowired
    ProductService productService;

    // 장바구니 목록 가져오기
    @GetMapping("cart")
    public String getCart(HttpServletRequest request, SearchCondition sc){
        String id = SessionUtils.getSessionId(request);

        try {
            List<Cart> cartList = cartService.showCart(id); // 해당 id의 장바구니 모두 가져오기
            request.setAttribute("cartList", cartList);
            request.setAttribute("sc", sc);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "product/cart";
    }

    // 장바구니에서 선택한 항목을 결제페이지로 보내기
    @PostMapping("cart")
    public String postCart(Cart cart, String quantity, String check_box, HttpServletRequest request){
        String id = SessionUtils.getSessionId(request);

        if(check_box == null || check_box.equals("")){ // 선택한 항목이 없을시 장바구니로 리다이렉트
            request.setAttribute("msg", "상품을 선택해주세요.");
            request.setAttribute("url", "cart");
            return "alert";
        }
        int total = 0; // 총 상품 가격
        // 받아온 상품id, 사이즈, 갯수가 여러개일 수 있으므로 배열에 각각 저장
        String[] product_id = cart.getProduct_id().split(",");
        String[] size = cart.getSize().split(",");
        String[] quan = quantity.split(",");
        String[] checkbox = check_box.split(",");

        try {
            List<Product> list = new ArrayList<>();
            for(int i=0; i<product_id.length; i++){ // 받아온 정보를 토대로 객체에 할당하여 각각 list에 추가
                for(int j=0; j<checkbox.length; j++){
                    if( i == Integer.parseInt(checkbox[j])){ // 해당 상품의 checkbox를 선택하지 않았으면 추가하지않음
                        Product product = productService.getProduct(product_id[i]);
                        product.setSize(size[i]);
                        product.setQuantity(Integer.parseInt(quan[i]));
                        total += product.getTotal();
                        list.add(product);
                    }
                }
            }
            User user = userService.getUser(id); // 배송지 정보를 할당하기위해 user정보 가져오기
            request.setAttribute("list", list);
            request.setAttribute("total", total);
            request.setAttribute("user", user);
        } catch (Exception e){
            e.printStackTrace();
        }
        return "product/payment";
    }

    // 해당 상품 장바구니에 추가
    @PostMapping("add-cart")
    public String addCart(Cart cart, SearchCondition sc, HttpServletRequest request){
        String id = SessionUtils.getSessionId(request);

        Map<String,Object> map = new HashMap<>(); // 등록한 상품인지 확인하기 위한 Map
        map.put("user_id", id);
        map.put("product_id", cart.getProduct_id());
        map.put("size", cart.getSize());
        cart.setUser_id(id);
        try {
            if(cart.getSize().equals("") || cart.getSize() == null){
                request.setAttribute("msg", "사이즈를 선택해주세요.");
                throw new Exception("SIZE_IS_REQUIRED");
            }
            if(cartService.checkCart(map) != null){ // Map에 등록한 정보를 통해 이미 등록한 상품인지 확인
                request.setAttribute("msg", "이미 등록된 상품입니다.");
                throw new Exception("ALREADY_ADDED");
            }

            // 장바구니에 상품 추가
            if(cartService.addCart(cart) == 1){
                request.setAttribute("msg", "장바구니에 상품을 추가하였습니다.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 추가 후 상품 정보 페이지로 리다이렉트
        request.setAttribute("url", "product" + sc.getQueryString() + "&product_id=" + cart.getProduct_id());
        return "alert";
    }

    // 장바구니에서 체크한 상품들 제거
    @PostMapping("del-cart")
    public String delCart(String check_box, Cart cart, HttpServletRequest request){
        String id = SessionUtils.getSessionId(request);

        if(check_box == null || check_box.equals("")){
            request.setAttribute("msg", "선택된 상품이 없습니다.");
            request.setAttribute("url", "cart");
            return "alert";
        }

        Map<String,Object> map = new HashMap<>();
        map.put("user_id", id);
        // 상품을 제거하기 위해 상품id와 size 그리고 선택된지 확인하기위해 checkbox의 값 각각 배열에 할당
        String[] product_id = cart.getProduct_id().split(",");
        String[] size = cart.getSize().split(",");
        String[] check = check_box.split(",");
        try {
            for (int i=0; i<product_id.length; i++) { // 모든 상품 확인
                for(int j=0; j<check.length; j++){ // 체크된 상품일 시 해당 상품제거
                    if(i  == Integer.parseInt(check[j])){
                        map.put("size", size[i]);
                        map.put("product_id", product_id[i]);
                        if(cartService.delCart(map) != 1){
                            throw new Exception("DEL_FAIL");
                        }
                        // 제거 후 map안의 product_id, size값 초기화
                        map.remove("product_id");
                        map.remove("size");
                    }
                }
            }
            request.setAttribute("msg", "삭제되었습니다.");
            request.setAttribute("url", "cart");
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("msg", "삭제에 실패했습니다.");
            request.setAttribute("url", "cart");
        }

        return "alert";
    }

    // 장바구니 목록에서 해당 라인의 삭제버튼 클릭 시 장바구니 목록에서 제거
    @PostMapping("del-one-cart")
    public String delOneCart(String del_product_id, String del_size, HttpServletRequest request, RedirectAttributes rattr){
        String id = SessionUtils.getSessionId(request);

        // 삭제하기위해 해당유저 id와 product_id, size 값 Map에 할당
        Map<String,Object> map = new HashMap<>();
        map.put("product_id", del_product_id);
        map.put("size", del_size);
        map.put("user_id", id);

        try {
            if(cartService.delCart(map) != 1){ // map에 할당된 정보를 통해 장바구니 목록에서 제거
                request.setAttribute("msg", "삭제에 실패했습니다.");
                throw new Exception("DEL_FAIL");
            }
            request.setAttribute("msg", "삭제되었습니다.");
        } catch (Exception e) {
            e.printStackTrace();
        }
        request.setAttribute("url", "cart");
        return "alert";
    }

    // 장바구니 목록에서 갯수가 바뀔시 실제 데이터베이스의 갯수도 변경
    @PostMapping("set-quantity")
    @ResponseBody
    public Map<String, Object> setQuantity(@RequestBody Map<String, Object> cartMap, HttpServletRequest request){
        Map<String, Object> map = new HashMap<>();
        String id = SessionUtils.getSessionId(request);
        if(id == null || id.equals("")){ // 만약 로그인 정보가 명확하지 않을경우 알림
            map.put("msg", "로그인을 확인해주세요.");
            return map;
        }

        // 해당유저의 장바구니 상품의 갯수 변경을 위해 Cart에 정보 할당
        Cart cart = new Cart();
        cart.setUser_id(id);
        cart.setProduct_id((String)cartMap.get("product_id"));
        cart.setSize((String)cartMap.get("size"));
        cart.setQuantity((Integer)cartMap.get("quantity"));
        try {
            cartService.modQuantity(cart); // 할당된 정보를 통해 갯수 변경
        } catch (Exception e) {
            e.printStackTrace();
        }
        map.put("quantity", cart.getQuantity()); // 변경된 갯수 반환

        return map;
    }
}
