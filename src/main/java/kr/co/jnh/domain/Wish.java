package kr.co.jnh.domain;

import java.util.Arrays;

public class Wish {

    private String user_id;
    private String product_id;
    private String size[];

    public Wish(){}

    public Wish(String user_id, String product_id) {
        this.user_id = user_id;
        this.product_id = product_id;
    }

    @Override
    public String toString() {
        return "Wish{" +
                "user_id='" + user_id + '\'' +
                ", product_id='" + product_id + '\'' +
                ", size=" + Arrays.toString(size) +
                '}';
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

    public String[] getSize() {
        return size;
    }

    public void setSize(String[] size) {
        this.size = size;
    }
}
