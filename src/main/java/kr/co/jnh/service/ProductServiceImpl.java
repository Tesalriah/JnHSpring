package kr.co.jnh.service;

import kr.co.jnh.dao.ProductDao;
import kr.co.jnh.domain.Product;
import kr.co.jnh.domain.SearchCondition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductDao productDao;

    @Override
    public Product getProduct(String product_id) throws Exception{
        return productDao.select(product_id);
    }

    @Override
    public List<Product> readOne(String product_id) throws Exception{
        return productDao.selectOne(product_id);
    }

    @Override
    public int addProduct(Product product) throws Exception{
        return productDao.insert(product);
    }
    @Override
    public boolean productIdCheck(String date) throws Exception{
        if(productDao.select(date) != null){
            return true;
        }
        return false;
    }

    @Override
    public String returnId(String product_id) throws Exception{
        return productDao.selectId(product_id);
    }

    @Override
    public int getSearchResultCnt(SearchCondition sc) throws Exception{
        return productDao.searchResultCnt(sc);
    }

    @Override
    public List<Product> getSearchSelectPage(SearchCondition sc) throws Exception{
        return productDao.searchSelectPage(sc);
    }

    @Override
    public List<String> getSize(String product_id) throws Exception{
        return productDao.selectSize(product_id);
    }

    @Override
    public Product getProductAtSize(Map map) throws Exception{
        return productDao.selectAtSize(map);
    }

    @Override
    public List<Product> getProductAdmin(SearchCondition sc) throws Exception{
        return productDao.selectProductAdmin(sc);
    }

    @Override
    public int getProductAdminCnt(SearchCondition sc) throws Exception{
        return productDao.selectProductAdminCnt(sc);
    }

    @Override
    public int updateProduct(Map map) throws Exception{
        return productDao.update(map);
    }

    @Override
    public int modifyInfo(Product product) throws Exception{
        return productDao.updateInfo(product);
    }

    @Override
    public int remove(String product_id) throws Exception{
        return productDao.delete(product_id);
    }
}
