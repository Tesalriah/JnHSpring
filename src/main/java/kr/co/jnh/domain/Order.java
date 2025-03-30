package kr.co.jnh.domain;

import java.util.Date;
import java.util.List;

public class Order {

    private Product product;
    private String user_id;
    private String name;
    private String address;
    private String phone;
    private String del_request;
    private String order_no;
    private String product_id;
    private String size;
    private Integer quantity;
    private String color;
    private String status;
    private Date order_date;
    private String tid;
    private String payment_method_type;
    private String issuer_corp;

    public Order(){}

    public Order(String user_id, String name, String address, String phone, String del_request, String order_no) {
        this.user_id = user_id;
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.del_request = del_request;
        this.order_no = order_no;
    }

    @Override
    public String toString() {
        return "Order{" +
                "user_id='" + user_id + '\'' +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                ", del_request='" + del_request + '\'' +
                ", order_no=" + order_no +
                ", product_id='" + product_id + '\'' +
                ", size='" + size + '\'' +
                ", quantity=" + quantity +
                ", status='" + status + '\'' +
                ", order_date=" + order_date +
                '}';
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDel_request() {
        return del_request;
    }

    public void setDel_request(String del_request) {
        this.del_request = del_request;
    }

    public String getOrder_no() {
        return order_no;
    }

    public void setOrder_no(String order_no) {
        this.order_no = order_no;
    }

    public String getProduct_id() {
        return product_id;
    }

    public void setProduct_id(String product_id) {
        this.product_id = product_id;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getOrder_date() {
        return order_date;
    }

    public void setOrder_date(Date order_date) {
        this.order_date = order_date;
    }

    public String getTid() {
        return tid;
    }

    public void setTid(String tid) {
        this.tid = tid;
    }

    public String getPayment_method_type() {
        return payment_method_type;
    }

    public void setPayment_method_type(String payment_method_type) {
        this.payment_method_type = payment_method_type;
    }

    public String getIssuer_corp() {
        return issuer_corp;
    }

    public void setIssuer_corp(String issuer_corp) {
        this.issuer_corp = issuer_corp;
    }
}
