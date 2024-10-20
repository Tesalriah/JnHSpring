package kr.co.jnh.service;

import kr.co.jnh.dao.CartDao;
import kr.co.jnh.dao.OrderDao;
import kr.co.jnh.domain.Cart;
import kr.co.jnh.domain.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    OrderDao orderDao;

    @Autowired
    CartDao cartDao;


    @Override
    public List<Order> read(String id) throws Exception{
        return orderDao.select(id);
    }

    @Override
    public List<Order> selectAll() throws Exception{
        return orderDao.selectAll();
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
            Map map = new HashMap();
            map.put("user_id", order.getUser_id());
            map.put("product_id", order.getProduct_id());
            map.put("size", order.getSize());
            if(cartDao.checkCart(map) == null){
                return result;
            }
            if(cartDao.delete(map) != 1){
                throw new Exception("CART_DELETE_FAIL");
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
    public boolean orderIdCheck(String date) throws Exception {
        if(orderDao.selectId(date) != null){
            return true;
        }
        return false;
    }
}
