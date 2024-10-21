package kr.co.jnh.service;

import kr.co.jnh.domain.Order;

import java.util.List;
import java.util.Map;

public interface OrderService {
    List<Order> read(String id) throws Exception;

    List<Order> selectAll() throws Exception;

    String returnId(String order_no) throws Exception;

    int buy(List<Order> order) throws Exception;

    int delete(String order_no) throws Exception;

    int updete(Map map) throws Exception;

    boolean orderIdCheck(String date) throws Exception;

}
