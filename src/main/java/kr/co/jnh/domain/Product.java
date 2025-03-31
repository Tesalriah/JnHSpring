package kr.co.jnh.domain;

import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import java.util.Date;

public class Product {

    private String product_id;
    private String product_name;
    private String gender;
    private String category;
    private String color;
    private String size;
    private String stock;
    private Integer price;
    private Integer discount = 0;
    private String image;
    private Date reg_date;
    private Date up_date;
    private float rating;
    private int bought_cnt;
    private String state;
    private Integer wish_cnt;
    private Integer review_cnt;
    private Integer quantity;
    private Integer total;
    private Integer dis_price;

    public Product(){}

    @Override
    public String toString() {
        return "Product{" +
                "product_id='" + product_id + '\'' +
                ", product_name='" + product_name + '\'' +
                ", gender='" + gender + '\'' +
                ", category='" + category + '\'' +
                ", color='" + color + '\'' +
                ", size='" + size + '\'' +
                ", stock='" + stock + '\'' +
                ", price=" + price +
                ", discount=" + discount +
                ", image='" + image + '\'' +
                ", reg_date=" + reg_date +
                ", up_date=" + up_date +
                ", rating=" + rating +
                ", bought_cnt=" + bought_cnt +
                ", state='" + state + '\'' +
                ", wish_cnt=" + wish_cnt +
                ", quantity=" + quantity +
                ", total=" + total +
                ", dis_price=" + dis_price +
                '}';
    }

    public Product(String product_id, String product_name, String gender, String category, String color, String size,
                   String stock, Integer price, Integer discount, String image, int bought_cnt) {
        this.product_id = product_id;
        this.product_name = product_name;
        this.gender = gender;
        this.category = category;
        this.color = color;
        this.size = size;
        this.stock = stock;
        this.price = price;
        this.discount = discount;
        this.image = image;
        this.bought_cnt = bought_cnt;
    }

    public String getProduct_id() {
        return product_id;
    }

    public void setProduct_id(String product_id) {
        this.product_id = product_id;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getStock() {
        return stock;
    }

    public void setStock(String stock) {
        this.stock = stock;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getDiscount() { return discount; }

    public void setDiscount(Integer discount) {
        this.discount = discount;
    }

    public Integer getDis_price() {
        return dis_price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Date getReg_date() {
        return reg_date;
    }

    public void setReg_date(Date reg_date) {
        this.reg_date = reg_date;
    }

    public Date getUp_date() {
        return up_date;
    }

    public void setUp_date(Date up_date) {
        this.up_date = up_date;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public int getBought_cnt() {
        return bought_cnt;
    }

    public void setBought_cnt(int bought_cnt) {
        this.bought_cnt = bought_cnt;
    }


    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Integer getWish_cnt() {
        return wish_cnt;
    }

    public void setWish_cnt(Integer wish_cnt) {
        this.wish_cnt = wish_cnt;
    }

    public Integer getReview_cnt() {
        return review_cnt;
    }

    public void setReview_cnt(Integer review_cnt) {
        this.review_cnt = review_cnt;
    }

    public Integer getQuantity() { return quantity; }

    public void setQuantity(Integer quantity) {
        dis_price = price - (int) ((double)price * (double)discount / 100);
        total = dis_price * quantity;
        this.quantity = quantity;
    }

    public Integer getTotal() { return total; }
}
