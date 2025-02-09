package kr.co.jnh.service;

import kr.co.jnh.dao.CartDao;
import kr.co.jnh.dao.ProductDao;
import kr.co.jnh.dao.WishDao;
import kr.co.jnh.domain.Cart;
import kr.co.jnh.domain.Product;
import kr.co.jnh.domain.Wish;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class WishServiceImpl implements WishService {

    @Autowired
    WishDao wishDao;
    @Autowired
    ProductDao productDao;
    @Autowired
    CartDao cartDao;

    @Override
    public List<Wish> read(Map map) throws Exception{
        String[] sizeFrame = {"XS", "S", "M", "L", "XL", "XXL", "XXXL"}; // 사이즈 순으로 정렬하기 위해 선언

        List<Wish> wishList = new ArrayList<>();
        for(Wish wish : wishDao.select(map)){
            Product product = productDao.select(wish.getProduct_id());
            product.setQuantity(1);
            List<String> size = productDao.selectSize(wish.getProduct_id());
            List<String> realSize = new ArrayList<>();
            for(String frame : sizeFrame) {
                for(String each : size){
                    if (frame.equals(each)){
                        realSize.add(each);
                    }
                }
            }
            wish.setSize(realSize);
            wish.setProduct(product);
            wishList.add(wish);
        }
        return wishList;
    }

    @Override
    public boolean isThere(Wish wish) throws Exception{ // 찜이 존재하면 1 존재하지않으면 0 반환
        Wish check = wishDao.selectOne(wish);
        if(check != null){
            return true;
        }else {
            return false;
        }
    }

    @Override
    public List<Wish> readAll() throws Exception{
        return wishDao.selectAll();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int write(Wish wish) throws Exception{
        int result = -1;
        Map map = new HashMap();
        result = wishDao.insert(wish);
        if(result > 0){
            Product product = productDao.select(wish.getProduct_id());

            Product set = new Product();
            set.setWish_cnt(product.getWish_cnt() + 1);
            set.setProduct_id(wish.getProduct_id());
            productDao.update(set);
        }
        return result;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int remove(Wish wish) throws Exception{
        int result = -1;
        Map map = new HashMap();
        result = wishDao.delete(wish);
        if(result > 0){
            Product product = productDao.select(wish.getProduct_id());

            Product set = new Product();
            set.setWish_cnt(product.getWish_cnt() - 1);
            set.setProduct_id(wish.getProduct_id());
            productDao.update(set);
        }
        return result;
    }

    @Override
    public int totalCnt(String id) throws Exception{
        return wishDao.getCount(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int addCart(Wish wish, String size) throws Exception{
        int result = -1;
        Cart cart = new Cart(wish.getUser_id(), wish.getProduct_id(), size, 1);
        if(cartDao.insert(cart) != 0){
            result = wishDao.delete(wish);
        }else{
            throw new Exception("WISH_ADDCART_FAIL");
        }
        if(result != 1){
            throw new Exception("ADDCART_WISH_DEL_FAIL");
        }
        return result;
    }
}
