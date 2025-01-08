package kr.co.jnh.service;

import kr.co.jnh.dao.OrderDao;
import kr.co.jnh.dao.ProductDao;
import kr.co.jnh.dao.ReviewDao;
import kr.co.jnh.domain.Order;
import kr.co.jnh.domain.PageHandler;
import kr.co.jnh.domain.Review;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ReviewServiceImpl implements ReviewService {
    @Autowired
    ReviewDao reviewDao;
    @Autowired
    ProductDao productDao;
    @Autowired
    OrderDao orderDao;

    @Override
    public List<Review> selectAll() throws Exception{
        return reviewDao.selectAll();
    }
    
    @Override
    public Review selectOne(int no) throws Exception{
        Review review = reviewDao.selectOne(no);
        if(review == null){
            return null;
        }
        getOrderAndProduct(review);
        return review;
    }

    @Override
    public int update(Review review) throws Exception{
        return reviewDao.update(review);
    }

    @Override
    public int delete(int no) throws Exception{
        return reviewDao.delete(no);
    }

    @Override
    public int selectPageCnt(Map map) throws Exception{
        return reviewDao.selectPageCnt(map);
    }

    @Override
    public List<Review> selectPage(Map map) throws Exception{
        List<Review> reviews = reviewDao.selectPage(map);
        for( Review review : reviews){
            getOrderAndProduct(review);
        }
        return reviews;
    }

    private void getOrderAndProduct(Review review) throws Exception{
        Map<String, Object> map = new HashMap<>();
        map.put("id", review.getUser_id());
        map.put("order_no", review.getOrder_no());
        Order order = orderDao.selectOne(map).get(0);
        order.setProduct(productDao.select(review.getProduct_id()));
        review.setOrder(order);
    }
}
