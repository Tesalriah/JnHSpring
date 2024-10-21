package kr.co.jnh.service;

import kr.co.jnh.dao.CartDao;
import kr.co.jnh.domain.Cart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    CartDao cartDao;

    @Override
    public List<Cart> showCart(String id) throws Exception{
        return cartDao.select(id);
    }

    @Override
    public int addCart(Cart cart) throws Exception{
        return cartDao.insert(cart);
    }

    @Override
    public int delCart(Map map) throws Exception{
        return cartDao.delete(map);
    }

    @Override
    public int modQuantity(Cart cart) throws Exception{
        return cartDao.update(cart);
    }

    @Override
    public Cart checkCart(Map map) throws Exception{
        return cartDao.checkCart(map);
    }
}
