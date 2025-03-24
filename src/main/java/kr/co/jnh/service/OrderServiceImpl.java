package kr.co.jnh.service;

import kr.co.jnh.dao.CartDao;
import kr.co.jnh.dao.OrderDao;
import kr.co.jnh.dao.ProductDao;
import kr.co.jnh.dao.ReviewDao;
import kr.co.jnh.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    OrderDao orderDao;

    @Autowired
    CartDao cartDao;

    @Autowired
    ProductDao productDao;

    @Autowired
    ReviewDao reviewDao;


    @Override
    public List<Order> read(Map map) throws Exception{
        return orderDao.select(map);
    }

    @Override
    public int readCnt(Map map) throws Exception{
        return orderDao.selectCnt(map);
    }

    @Override
    public List<Order> readAll() throws Exception{
        return orderDao.selectAll();
    }

    @Override
    public List<Order> readOne(Map map) throws Exception{
        List<Order> orderList = orderDao.selectOne(map);
        for(Order order : orderList){
            Product product = productDao.select(order.getProduct_id());
            product.setQuantity(order.getQuantity());
            order.setProduct(product);
        }
        return orderList;
    }

    @Override
    public String returnId(String order_no) throws Exception{
        return orderDao.selectId(order_no);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int buy(List<Order> list) throws Exception{
        int result = -1;
        Order order;
        for (int i = 0; i < list.size(); i++) {
            order = list.get(i);

            result = orderDao.insert(order);

            if(result != 1){
                throw new Exception("ORDER_FAIL");
            }
            Map<String, Object> map = new HashMap();
            map.put("user_id", order.getUser_id());
            map.put("product_id", order.getProduct_id());
            map.put("size", order.getSize());

            Product product = productDao.select(order.getProduct_id());
            int calStock = Integer.parseInt(product.getStock()) - order.getQuantity();
            map.put("stock", calStock);
            map.put("size", order.getSize());
            map.put("bought_cnt", product.getBought_cnt() + order.getQuantity());
            productDao.update(map);
            if(productDao.updateStock(product) != 1){
                throw new Exception("STOCK_ERROR");
            }

            if(cartDao.checkCart(map) != null){
                if(cartDao.delete(map) != 1){
                    throw new Exception("CART_DELETE_FAIL");
                }
            }
        }
        return result;
    }

    @Override
    public int delete(String order_no) throws Exception{
        return orderDao.delete(order_no);
    }

    @Override
    public int updete(Map map) throws Exception {
        return orderDao.updete(map);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int statusModify(Map map) throws Exception{
        if(map.get("status").equals("배송중")){
            Review review = new Review((String)map.get("order_no"), (String)map.get("id"),(String)map.get("product_id"),(String)map.get("size"));
            reviewDao.insert(review);
        }
        return orderDao.returnUpdate(map);
    }

    @Override
    public boolean orderIdCheck(String date) throws Exception {
        if(orderDao.selectId(date) != null){
            return true;
        }
        return false;
    }

    @Override
    public boolean checkStock(String product_id, String quantity, String size) throws Exception {
        HashMap map = new HashMap();
        map.put("product_id", product_id);
        map.put("size", size);
        Product product = productDao.selectAtSize(map);

        int stock = Integer.parseInt(product.getStock());
        int quan = Integer.parseInt(quantity);
        if(stock < quan || stock - quan < 0){
            return true;
        }
        return false;
    }

    @Override
    public List<Order> readMng(SearchCondition sc) throws Exception{
        return orderDao.selectMng(sc);
    }

    @Override
    public int readMngCnt(SearchCondition sc) throws Exception{
        return orderDao.selectMngCnt(sc);
    }
}
