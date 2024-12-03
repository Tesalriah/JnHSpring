package kr.co.jnh.service;

import kr.co.jnh.dao.CartDao;
import kr.co.jnh.dao.ProductDao;
import kr.co.jnh.dao.WishDao;
import kr.co.jnh.domain.Cart;
import kr.co.jnh.domain.Wish;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class WishServiceImpl implements WishService {

    @Autowired
    WishDao wishDao;
    @Autowired
    ProductDao productDao;
    @Autowired
    CartDao cartDao;

    @Override
    public List<Wish> read(String id) throws Exception{
        return wishDao.select(id);
    }

    @Override
    public int readOne(Wish wish) throws Exception{ // 찜이 존재하면 1 존재하지않으면 0 반환
        Wish check = wishDao.selectOne(wish);
        if(check != null){
            return 1;
        }else {
            return 0;
        }
    }

    @Override
    public List<Wish> readAll() throws Exception{
        return wishDao.selectAll();
    }

    @Override
    public int write(Wish wish) throws Exception{
        return wishDao.insert(wish);
    }

    @Override
    public int remove(Wish wish) throws Exception{
        return wishDao.delete(wish);
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
