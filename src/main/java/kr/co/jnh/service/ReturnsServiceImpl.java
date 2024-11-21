package kr.co.jnh.service;

import kr.co.jnh.dao.ProductDao;
import kr.co.jnh.dao.ReturnsDao;
import kr.co.jnh.domain.Product;
import kr.co.jnh.domain.Returns;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ReturnsServiceImpl implements ReturnsService {

    @Autowired
    ReturnsDao returnsDao;

    @Autowired
    ProductDao productDao;

    @Override
    public List<Returns> read(Map map) throws Exception{
        List<Returns> returnsList = returnsDao.selectPage(map);
        for(Returns returns : returnsList){
            Product product = productDao.select(returns.getProduct_id());
            product.setQuantity(returns.getQuantity());
            returns.setProduct(product);
        }
        return returnsList;
    }

    @Override
    public int create(Returns returns) throws Exception{
        return returnsDao.insert(returns);
    }

    @Override
    public int remove(Integer rno) throws Exception{
        return returnsDao.delete(rno);
    }

    @Override
    public int update(Map map) throws Exception{
        return returnsDao.update(map);
    }

    @Override
    public int count(String id) throws Exception{
        return returnsDao.getCount(id);
    }

    @Override
    public List<Returns> readAll() throws Exception{
        return returnsDao.selectAll();
    }
}
