package kr.co.jnh.dao;

import kr.co.jnh.domain.Product;
import kr.co.jnh.domain.SearchCondition;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;
import java.util.Map;

@Repository
public class ProductDaoImpl implements ProductDao {
    @Autowired
    DataSource ds;

    @Autowired
    private SqlSession session;
    private static String nameSpace = "kr.co.jnh.dao.ProductMapper.";

    @Override
    public Product select(String product_id) throws Exception{
        return session.selectOne(nameSpace + "select", product_id);
    }

    @Override
    public Integer insert(Product product) throws Exception{
        return session.insert(nameSpace + "insert", product);
    }

    @Override
    public Integer delete(String product_id) throws Exception{
        return session.delete(nameSpace + "delete", product_id);
    }

    @Override
    public List<Product> selectAll(Map map) throws Exception{
        return session.selectList(nameSpace + "selectAll", map);
    }

    @Override
    public String selectId(String proeduct_id) throws Exception{
        return session.selectOne( nameSpace + "selectId", proeduct_id);
    }

    @Override
    public int searchResultCnt(SearchCondition sc) throws Exception{
        return session.selectOne( nameSpace + "searchResultCnt", sc);
    }

    @Override
    public List<Product> searchSelectPage(SearchCondition sc) throws Exception{
        return session.selectList( nameSpace + "searchSelectPage", sc);
    }

    public List<String> selectSize(String product_id) throws Exception{
        return session.selectList( nameSpace + "selectSize", product_id);
    }
}
