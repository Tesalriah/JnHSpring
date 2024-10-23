package kr.co.jnh.controller;

import kr.co.jnh.domain.*;
import kr.co.jnh.service.CartService;
import kr.co.jnh.service.ProductService;
import kr.co.jnh.service.UserService;
import kr.co.jnh.util.SessionIdUtil;
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

    @GetMapping("cart")
    public String getCart(HttpServletRequest request, SearchCondition sc, RedirectAttributes rattr){
        String id = SessionIdUtil.getSessionId(request, rattr);
        if(id == null || id.equals("")){
            rattr.addFlashAttribute("prevPage", "/cart");
            return "redirect:login";
        }

        try {
            List<Cart> cartList = cartService.showCart(id);
            List<Product> productList = new ArrayList<>();
            for(int i=0; i<cartList.size(); i++){
                Product product = productService.getProduct(cartList.get(i).getProduct_id());
                product.setQuantity(cartList.get(i).getQuantity());
                productList.add(product);
            }
            request.setAttribute("cartList", cartList);
            request.setAttribute("productList", productList);
            request.setAttribute("sc", sc);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "product/cart";
    }

    @PostMapping("cart")
    public String postCart(Cart cart, String quantity, String check_box, HttpServletRequest request, RedirectAttributes rattr){
        String id = SessionIdUtil.getSessionId(request, rattr);
        if(id == null || id.equals("")){
            return "redirect:login";
        }

        if(check_box == null || check_box.equals("")){
            request.setAttribute("msg", "상품을 선택해주세요.");
            request.setAttribute("url", "cart");
            return "alert";
        }
        int total = 0;
        String[] product_id = cart.getProduct_id().split(",");
        String[] size = cart.getSize().split(",");
        String[] quan = quantity.split(",");
        String[] checkbox = check_box.split(",");
        Product product = null;
        List<Product> list = new ArrayList();
        try {
            for(int i=0; i<product_id.length; i++){
                for(int j=0; j<checkbox.length; j++){
                    if( i == Integer.parseInt(checkbox[j])){
                        product = null;
                        product = productService.getProduct(product_id[i]);
                        product.setSize(size[i]);
                        product.setQuantity(Integer.parseInt(quan[i]));
                        total += product.getTotal();
                        list.add(product);
                    }
                }
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

    @PostMapping("addCart")
    public String addCart(Cart cart, SearchCondition sc, HttpServletRequest request, RedirectAttributes rattr){
        String id = SessionIdUtil.getSessionId(request, rattr);
        if(id == null || id.equals("")){
            return "redirect:login";
        }

        Map map = new HashMap();
        map.put("user_id", id);
        map.put("product_id", cart.getProduct_id());
        map.put("size", cart.getSize());
        int result = -1;
        cart.setUser_id(id);
        try {
            if(cart.getSize().equals("")){
                request.setAttribute("msg", "사이즈를 선택해주세요.");
                throw new Exception("SIZE_IS_REQUIRED");
            }
            if(cartService.checkCart(map) != null){
                request.setAttribute("msg", "이미 등록된 상품입니다.");
                throw new Exception("ALREADY_ADDED");
            }

            result = cartService.addCart(cart);
            if(result == 1){
                request.setAttribute("msg", "장바구니에 상품을 추가하였습니다.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        request.setAttribute("url", "product" + sc.getQueryString() + "&product_id=" + cart.getProduct_id());
        return "alert";
    }

    @PostMapping("delCart")
    public String delCart(String check_box, Cart cart, HttpServletRequest request, RedirectAttributes rattr){
        String id = SessionIdUtil.getSessionId(request, rattr);
        if(id == null || id.equals("")){
            return "redirect:login";
        }
        if(check_box == null || check_box.equals("")){
            request.setAttribute("msg", "선택된 상품이 없습니다.");
            request.setAttribute("url", "cart");
            return "alert";
        }

        Map map = new HashMap();
        map.put("user_id", id);
        String[] product_id = cart.getProduct_id().split(",");
        String[] size = cart.getSize().split(",");
        String[] check = check_box.split(",");
        try {
            for (int i=0; i<product_id.length; i++) {
                for(int j=0; j<check.length; j++){
                    if(i  == Integer.parseInt(check[j])){
                        map.put("size", size[i]);
                        map.put("product_id", product_id[i]);
                        if(cartService.delCart(map) != 1){
                            throw new Exception("DEL_FAIL");
                        }
                    }
                    map.remove("product_id");
                    map.remove("size");
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

    @PostMapping("delOneCart")
    public String delOneCart(String del_product_id, String del_size, HttpServletRequest request, RedirectAttributes rattr){
        String id = SessionIdUtil.getSessionId(request, rattr);
        if(id == null || id.equals("")){
            return "redirect:login";
        }
        Map map = new HashMap();
        map.put("product_id", del_product_id);
        map.put("size", del_size);
        map.put("user_id", id);

        try {
            if(cartService.delCart(map) != 1){
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

    @PostMapping("setQuantity")
    @ResponseBody
    public Map<String, Object> setQuantity(@RequestBody Map<String, Object> cartMap, HttpServletRequest request, RedirectAttributes rattr){
        Map<String, Object> map = new HashMap<>();
        String id = SessionIdUtil.getSessionId(request, rattr);
        if(id == null || id.equals("")){
            map.put("msg", "로그인을 확인해주세요.");
            return map;
        }

        Cart cart = new Cart();
        cart.setUser_id(id);
        cart.setProduct_id((String)cartMap.get("product_id"));
        cart.setSize((String)cartMap.get("size"));
        cart.setQuantity((Integer)cartMap.get("quantity"));
        try {
            cartService.modQuantity(cart);
        } catch (Exception e) {
            e.printStackTrace();
        }
        map.put("quantity", cart.getQuantity());

        return map;
    }
}
