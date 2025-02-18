package kr.co.jnh.service;

import kr.co.jnh.dao.OrderDao;
import kr.co.jnh.dao.ProductDao;
import kr.co.jnh.dao.ReviewDao;
import kr.co.jnh.domain.Order;
import kr.co.jnh.domain.PageHandler;
import kr.co.jnh.domain.Product;
import kr.co.jnh.domain.Review;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    public List<Review> readAll() throws Exception{
        return reviewDao.selectAll();
    }
    
    @Override
    public Review readOne(int no) throws Exception{
        Review review = reviewDao.selectOne(no);
        if(review == null){
            return null;
        }
        getOrderAndProduct(review);
        return review;
    }

    @Override
    @Transactional(rollbackFor = {Exception.class})
    public int modify(Review review) throws Exception {
        int result = reviewDao.update(review);
        Review get = reviewDao.selectOne(review.getRno());
        Product product = new Product();
        product.setProduct_id(get.getProduct_id());
        Map<String, Object> map = new HashMap<>();
        map.put("product_id", get.getProduct_id());
        map.put("whether", 1);
        System.out.println("reviewDao.reviewAvg(get.getProduct_id()) = " + reviewDao.reviewAvg(get.getProduct_id()));
        int cnt = reviewDao.selectPageCnt(map);
        if(cnt > 0){
            double rating = Math.round(reviewDao.reviewAvg(get.getProduct_id()) * 10) / 10.0;
            map.put("rating",(float)rating);
        }else{
            map.put("rating", 0);
        }
        product.setReview_cnt(cnt);
        productDao.update(map);
        return result;
    }

    @Override
    @Transactional
    public int remove(int no) throws Exception{
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
        map.put("product_id", review.getProduct_id());
        map.put("size", review.getSize());
        Order order = orderDao.selectOne(map).get(0);
        order.setProduct(productDao.select(review.getProduct_id()));
        review.setOrder(order);
    }
}
