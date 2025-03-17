package kr.co.jnh.service;

import kr.co.jnh.dao.OrderDao;
import kr.co.jnh.dao.ProductDao;
import kr.co.jnh.dao.ReturnsDao;
import kr.co.jnh.domain.Product;
import kr.co.jnh.domain.Returns;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ReturnsServiceImpl implements ReturnsService {

    @Autowired
    ReturnsDao returnsDao;

    @Autowired
    ProductDao productDao;

    @Autowired
    OrderDao orderDao;

    @Override
    public List<List<Returns>> read(Map map) throws Exception{
        List<Returns> returnsList = returnsDao.selectPage(map);
        List<List<Returns>> returnsListList = new ArrayList<>();
        for(Returns returns : returnsList){
            List<Returns> each = returnsDao.selectOne(returns.getReturn_id());
            for(Returns setProduct : each){
                Product product = productDao.select(setProduct.getProduct_id());
                product.setQuantity(setProduct.getQuantity());
                setProduct.setProduct(product);
            }
            returnsListList.add(each);
        }
        return returnsListList;
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

    @Override
    public String readId(String return_id) throws Exception{
        return returnsDao.selectId(return_id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int returns(List<Returns> list) throws Exception{
        int result = -1;
        Map map = new HashMap();
        for (int i = 0; i < list.size(); i++) {
            Returns returns = list.get(i);
            result = returnsDao.insert(returns);
            if(result != 1){
                throw new Exception("RETURNS_INSERT_FAIL");
            }
            if(returns.getType().equals("exchange")){
                map.put("status", "교환접수");
            }if(returns.getType().equals("return")){
                map.put("status", "반품접수");
            }if(returns.getType().equals("cancel")){
                map.put("status", "취소완료");
            }
            map.put("order_no", returns.getOrder_no());
            map.put("id", returns.getUser_id());
            map.put("product_id", returns.getProduct_id());
            map.put("size", returns.getSize());
            if(orderDao.returnUpdate(map) <= 0){
                throw new Exception("ORDER_STATUS_UPDATE_FAIL");
            }
        }

        return result;
    }
}
