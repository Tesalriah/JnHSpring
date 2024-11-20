package kr.co.jnh.domain;

import java.util.Date;

public class Returns {

    private Product product;
    private String[] product_id_arr;
    private String[] size_arr;
    private String[] c_size_arr;
    private Integer rno;
    private String user_id;
    private String address;
    private String order_no;
    private String product_id;
    private String type;
    private String size;
    private Integer quantity;
    private String status;
    private Date order_date;
    private Date return_date;
    private String reason;
    private String contents;
    private String c_size;

    public Returns(){};

    public Returns(String user_id, String address, String order_no, String product_id, String type, String size, Integer quantity, String status, Date order_date, String reason, String contents, String c_size) {
        this.user_id = user_id;
        this.address = address;
        this.order_no = order_no;
        this.product_id = product_id;
        this.type = type;
        this.size = size;
        this.quantity = quantity;
        this.status = status;
        this.order_date = order_date;
        this.reason = reason;
        this.contents = contents;
        this.c_size = c_size;
    }

    @Override
    public String toString() {
        return "Returns{" +
                "rno=" + rno +
                ", user_id='" + user_id + '\'' +
                ", address='" + address + '\'' +
                ", order_no='" + order_no + '\'' +
                ", product_id='" + product_id + '\'' +
                ", type='" + type + '\'' +
                ", size='" + size + '\'' +
                ", quantity=" + quantity +
                ", status='" + status + '\'' +
                ", order_date=" + order_date +
                ", return_date=" + return_date +
                ", reason='" + reason + '\'' +
                ", contents='" + contents + '\'' +
                ", c_size='" + c_size + '\'' +
                '}';
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public String[] getProduct_id_arr() {
        return product_id_arr;
    }

    public void setProduct_id_arr(String[] product_id_arr) {
        this.product_id_arr = product_id_arr;
    }

    public String[] getSize_arr() {
        return size_arr;
    }

    public void setSize_arr(String[] size_arr) {
        this.size_arr = size_arr;
    }

    public String[] getC_size_arr() {
        return c_size_arr;
    }

    public void setC_size_arr(String[] c_size_arr) {
        this.c_size_arr = c_size_arr;
    }

    public Integer getRno() {
        return rno;
    }

    public void setRno(Integer rno) {
        this.rno = rno;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

    public Date getReturn_date() {
        return return_date;
    }

    public void setReturn_date(Date return_date) {
        this.return_date = return_date;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public String getC_size() {
        return c_size;
    }

    public void setC_size(String c_size) {
        this.c_size = c_size;
    }
}
