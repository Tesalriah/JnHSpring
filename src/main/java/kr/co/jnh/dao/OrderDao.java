package kr.co.jnh.dao;

import kr.co.jnh.domain.Order;

import java.util.List;
import java.util.Map;

public interface OrderDao {
    List<Order> select(Map map) throws Exception;

    int selectCnt(Map map) throws Exception;

    List<Order> selectAll() throws Exception;

    List<Order> selectOne(Map map) throws Exception;

    int insert(Order order) throws Exception;

    int delete(String order_no) throws Exception;

    int updete(Map map) throws Exception;

    String selectId(String order_no) throws Exception;
}
