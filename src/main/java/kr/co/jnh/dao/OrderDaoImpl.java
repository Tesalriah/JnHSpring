package kr.co.jnh.dao;

import kr.co.jnh.domain.Order;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;
import java.util.Map;

@Repository
public class OrderDaoImpl implements OrderDao {

    @Autowired
    DataSource ds;

    @Autowired
    private SqlSession session;
    private static String nameSpace = "kr.co.jnh.dao.OrderMapper.";

    @Override
    public List<Order> select(Map map) throws Exception{
        return session.selectList(nameSpace + "select", map);
    }

    @Override
    public int selectCnt(Map map) throws Exception{
        return session.selectOne(nameSpace + "selectCnt", map);
    }

    public List<Order> selectOne(Map map) throws Exception{
        return session.selectList(nameSpace + "selectOne", map);
    }

    @Override
    public List<Order> selectAll() throws Exception{
        return session.selectList(nameSpace + "selectAll");
    }

    @Override
    public int insert(Order order) throws Exception{
        return session.insert(nameSpace + "insert", order);
    }

    @Override
    public int delete(String order_no) throws Exception{
        return session.delete(nameSpace + "delete", order_no);
    }

    @Override
    public int updete(Map map) throws Exception{
        return session.update(nameSpace + "update", map);
    }

    @Override
    public String selectId(String order_no) throws Exception{
        return session.selectOne(nameSpace + "selectId", order_no);
    }
}
