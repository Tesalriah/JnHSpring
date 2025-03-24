package kr.co.jnh.domain;

import java.util.List;

public class OrderList {
    private List<Order> orderList;

    public OrderList() { } // 기본 생성자 필수!

    public List<Order> getOrderList() {
        return orderList;
    }

    public void setOrderList(List<Order> orderList) {
        this.orderList = orderList;
    }
}
