package kr.co.jnh.service;

import kr.co.jnh.dao.CartDao;
import kr.co.jnh.dao.ProductDao;
import kr.co.jnh.domain.Cart;
import kr.co.jnh.domain.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    CartDao cartDao;
    @Autowired
    ProductDao productDao;

    @Override
    public List<Cart> showCart(String id) throws Exception{
        List<Cart> cartList = cartDao.select(id);
        for(Cart cart : cartList){
            Map map = new HashMap();
            map.put("product_id", cart.getProduct_id());
            map.put("size", cart.getSize());

            Product product = productDao.selectAtSize(map);
            product.setQuantity(cart.getQuantity());
            cart.setProduct(product);
        }
        return cartList;
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
