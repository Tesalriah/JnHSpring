package kr.co.jnh.dao;

import kr.co.jnh.domain.Cart;

import java.util.List;
import java.util.Map;

public interface CartDao {
    List<Cart> select(String id) throws Exception;

    int insert(Cart cart) throws Exception;

    int delete(Map map) throws Exception;

    int update(Cart cart) throws Exception;

    Cart checkCart(Map map) throws Exception;
}
