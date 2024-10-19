package kr.co.jnh.dao;

import kr.co.jnh.domain.Cart;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;
import java.util.Map;

@Repository
public class CartDaoImpl implements CartDao {
    @Autowired
    DataSource ds;

    @Autowired
    private SqlSession session;
    private static String nameSpace = "kr.co.jnh.dao.CartMapper.";

    @Override
    public List<Cart> select(String id) throws Exception{
        return session.selectList(nameSpace + "select", id);
    }

    @Override
    public int insert(Cart cart) throws Exception{
        return session.insert(nameSpace + "insert", cart);
    }

    @Override
    public int delete(Map map) throws Exception{
        return session.delete(nameSpace + "delete", map);
    }

    @Override
    public int update(Map map) throws Exception{
        return  session.update( nameSpace + "update", map);
    }

    @Override
    public Cart checkCart(Map map) throws Exception{
        return session.selectOne(nameSpace + "checkCart", map);
    }
}
