package kr.co.jnh.dao;

import kr.co.jnh.domain.Product;

import java.util.List;
import java.util.Map;

public interface ProductDao {
    Product select(String product_id) throws Exception;

    Integer insert(Product product) throws Exception;

    Integer delete(String product_id) throws Exception;

    List<Product> selectAll(Map map) throws Exception;
}
