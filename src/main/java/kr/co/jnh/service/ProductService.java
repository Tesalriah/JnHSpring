package kr.co.jnh.service;

import kr.co.jnh.domain.Product;
import kr.co.jnh.domain.SearchCondition;

import java.util.List;
import java.util.Map;

public interface ProductService {
    Product getProduct(String product_id) throws Exception;

    int addProduct(Product product) throws Exception;

    boolean productIdCheck(String date) throws Exception;

    String returnId(String product_id) throws Exception;

    int getSearchResultCnt(SearchCondition sc) throws Exception;

    List<Product> getSearchSelectPage(SearchCondition sc) throws Exception;

    List<String> getSize(String product_id) throws Exception;

    Product getProductAtSize(Map map) throws Exception;

    List<Product> getProductAdmin(SearchCondition sc) throws Exception;

    int getProductAdminCnt(SearchCondition sc) throws Exception;

    int updateProduct(Map map) throws Exception;
}
