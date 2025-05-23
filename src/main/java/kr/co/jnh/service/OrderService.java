package kr.co.jnh.service;

import kr.co.jnh.domain.Order;
import kr.co.jnh.domain.SearchCondition;

import java.util.List;
import java.util.Map;

public interface OrderService {
    List<Order> read(Map map) throws Exception;

    int readCnt(Map map) throws Exception;

    List<Order> readAll() throws Exception;

    List<Order> readOne(Map map) throws Exception;

    String returnId(String order_no) throws Exception;

    // int buy(List<Order> order) throws Exception;

    int delete(String order_no) throws Exception;

    int updete(Map map) throws Exception;

    int statusModify(Map map) throws Exception;

    boolean orderIdCheck(String date) throws Exception;

    boolean checkStock(String product_id, String quantity, String size) throws Exception;

    List<Order> readMng(SearchCondition sc) throws Exception;

    int readMngCnt(SearchCondition sc) throws Exception;
}
