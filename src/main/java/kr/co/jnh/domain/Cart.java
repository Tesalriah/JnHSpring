package kr.co.jnh.domain;

public class Cart {

    private Product product;
    private String user_id;
    private String product_id;
    private String size;
    private Integer quantity;

    public Cart(){}

    public Cart(String user_id, String product_id, String size, Integer quantity) {
        this.user_id = user_id;
        this.product_id = product_id;
        this.size = size;
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "Cart{" +
                "user_id='" + user_id + '\'' +
                ", product_id='" + product_id + '\'' +
                ", size='" + size + '\'' +
                ", quantity=" + quantity +
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
}
