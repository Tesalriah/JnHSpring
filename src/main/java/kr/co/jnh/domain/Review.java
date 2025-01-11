package kr.co.jnh.domain;

import java.util.Date;

public class Review {

    private int rno;
    private String order_no;
    private String user_id;
    private String product_id;
    private String contents;
    private float reting;
    private Date reg_date;
    private Date up_date;
    private String image;
    private int whether;
    private int report_cnt;
    private Order order;

    public Review(){}

    public Review(String order_no, String user_id, String product_id) {
        this.order_no = order_no;
        this.user_id = user_id;
        this.product_id = product_id;
    }

    @Override
    public String toString() {
        return "Review{" +
                "rno=" + rno +
                ", order_no='" + order_no + '\'' +
                ", user_id='" + user_id + '\'' +
                ", product_id='" + product_id + '\'' +
                ", contents='" + contents + '\'' +
                ", reting=" + reting +
                ", reg_date=" + reg_date +
                ", up_date=" + up_date +
                ", image='" + image + '\'' +
                ", whether=" + whether +
                ", report_cnt=" + report_cnt +
                '}';
    }

    public int getRno() {
        return rno;
    }

    public void setRno(int rno) {
        this.rno = rno;
    }

    public String getOrder_no() {
        return order_no;
    }

    public void setOrder_no(String order_no) {
        this.order_no = order_no;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getProduct_id() {
        return product_id;
    }

    public void setProduct_id(String product_id) {
        this.product_id = product_id;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        contents = contents;
    }

    public float getReting() {
        return reting;
    }

    public void setReting(float reting) {
        this.reting = reting;
    }

    public Date getUp_date() {
        return up_date;
    }

    public void setUp_date(Date up_date) {
        this.up_date = up_date;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getWhether() {
        return whether;
    }

    public void setWhether(int whether) {
        this.whether = whether;
    }

    public int getReport_cnt() {
        return report_cnt;
    }

    public void setReport_cnt(int report_cnt) {
        this.report_cnt = report_cnt;
    }

    public Date getReg_date() {
        return reg_date;
    }

    public void setReg_date(Date reg_date) {
        this.reg_date = reg_date;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
}
