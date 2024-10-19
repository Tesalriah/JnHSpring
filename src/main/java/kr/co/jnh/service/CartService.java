package kr.co.jnh.service;

import kr.co.jnh.domain.Cart;

import java.util.List;
import java.util.Map;

public interface CartService {
    List<Cart> showCart(String id) throws Exception;

    int addCart(Cart cart) throws Exception;

    int delCart(Map map) throws Exception;

    int modCart(Map map) throws Exception;

    Cart checkCart(Map map) throws Exception;
}
