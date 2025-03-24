package kr.co.jnh.service;

import kr.co.jnh.dao.OrderDao;
import kr.co.jnh.dao.ProductDao;
import kr.co.jnh.dao.ReturnsDao;
import kr.co.jnh.dao.ReviewDao;
import kr.co.jnh.domain.Order;
import kr.co.jnh.domain.Product;
import kr.co.jnh.domain.Returns;
import kr.co.jnh.domain.SearchCondition;
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

    @Autowired
    ReviewDao reviewDao;

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
    public int modify(Map map) throws Exception{
        return returnsDao.update(map);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int mngModify(Map map) throws Exception{
        int result = returnsDao.mngUpdate(map);
        String type = (String)map.get("type");
        String status = (String)map.get("status");
        if(type.equals("exchange")){
            type = "교환";
        }if(type.equals("return")){
            type = "반품";
        }if(type.equals("cancel")){
            type = "취소";
        }

        Map<String,Object> orderMap = new HashMap<>();
        map.forEach((key,value) -> orderMap.put((String)key, value));
        orderMap.put("status", type+status);
        if(orderDao.returnUpdate(orderMap) != 1){
            throw new Exception("ORDER_STATUS_UPDATE_FAIL");
        }
        return result;
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

        for (Returns returns : list) {
            result = returnsDao.insert(returns);
            if(result != 1){
                throw new Exception("RETURNS_INSERT_FAIL");
            }
            if(returns.getType().equals("exchange")){
                map.put("status", "대기중");
            }if(returns.getType().equals("return")){
                map.put("status", "대기중");
            }if(returns.getType().equals("cancel")){
                map.put("status", "완료");
                map.put("return_id", returns.getReturn_id());
                if(returnsDao.update(map) == 0){
                    throw new Exception("RETURNS_UPDATE_FAIL");
                }
            }
            map.put("order_no", returns.getOrder_no());
            map.put("id", returns.getUser_id());
            map.put("product_id", returns.getProduct_id());
            map.put("size", returns.getSize());
            if(reviewDao.cancelDelete(map) <= 0){
                throw new Exception("CANCEL_REVIEW_DELETE_FAIL");
            }
            if(orderDao.returnUpdate(map) <= 0){
                throw new Exception("ORDER_STATUS_UPDATE_FAIL");
            }
        }

        return result;
    }

    @Override
    public int readMngCnt(SearchCondition sc) throws Exception{
        return returnsDao.selectMngCnt(sc);
    }

    @Override
    public List<Returns> readMng(SearchCondition sc) throws Exception{
        List<Returns> returnsList = returnsDao.selectMng(sc);
        for (Returns returns : returnsList) {
            Map map = new HashMap();
            map.put("product_id", returns.getProduct_id());
            map.put("order_no", returns.getOrder_no());
            map.put("id", returns.getUser_id());
            map.put("size", returns.getSize());
            Order order = orderDao.selectOne(map).get(0);

            returns.setOrder(order);
        }
        return returnsList;
    }
}
