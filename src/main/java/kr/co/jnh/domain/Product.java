package kr.co.jnh.domain;

import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

public class Product {

    private String product_id;
    private String product_name;
    private String gender;
    private String category;
    private String color;
    private String size;
    private String stock;
    private int price;
    private int discount;
    private String image;
    private Date reg_date;
    private Date up_date;
    private float rating;
    private int bougth_cnt;
    private String state;

    private MultipartFile uploadFile;

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
                ", bougth_cnt=" + bougth_cnt +
                ", state='" + state + '\'' +
                ", uploadFile=" + uploadFile +
                '}';
    }

    public Product(String product_id, String product_name, String gender, String category, String color, String size,
                   String stock, int price, int discount, String image, int bougth_cnt, MultipartFile uploadFile) {
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
        this.bougth_cnt = bougth_cnt;
        this.uploadFile = uploadFile;
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

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
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

    public int getBougth_cnt() {
        return bougth_cnt;
    }

    public void setBougth_cnt(int bougth_cnt) {
        this.bougth_cnt = bougth_cnt;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public MultipartFile getUploadFile() { return uploadFile; }

    public void setUploadFile(MultipartFile uploadFile) { this.uploadFile = uploadFile; }
}
