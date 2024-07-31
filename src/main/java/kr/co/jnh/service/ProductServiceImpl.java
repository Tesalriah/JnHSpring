package kr.co.jnh.service;

import kr.co.jnh.dao.ProductDao;
import kr.co.jnh.domain.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductDao productDao;

    @Override
    public boolean productIdCheck(String date) throws Exception{
        boolean result = false;
        if(productDao.select(date) != null){
            return true;
        }
        return result;
    }
}
