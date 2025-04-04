package kr.co.jnh.dao;

import kr.co.jnh.domain.Product;
import kr.co.jnh.domain.SearchCondition;

import java.util.List;
import java.util.Map;

public interface ProductDao {
    Product select(String product_id) throws Exception;

    List<Product> selectOne(String product_id) throws Exception;

    int insert(Product product) throws Exception;

    int updateStock(Product product) throws Exception;

    int updateInfo(Product product) throws Exception;

    int update(Map map) throws Exception;

    int updateReviewAvg() throws Exception;

    int delete(String product_id) throws Exception;

    List<Product> selectAll(Map map) throws Exception;

    String selectId(String proeduct_id) throws Exception;

    int searchResultCnt(SearchCondition sc) throws Exception;

    List<Product> searchSelectPage(SearchCondition sc) throws Exception;

    List<String> selectSize(String product_id) throws Exception;

    Product selectAtSize(Map map) throws Exception;

    List<Product> selectProductAdmin(SearchCondition sc) throws Exception;

    int selectProductAdminCnt(SearchCondition sc) throws Exception;
}
