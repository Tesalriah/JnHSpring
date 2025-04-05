package kr.co.jnh.service;

import kr.co.jnh.dao.OrderDao;
import kr.co.jnh.dao.ProductDao;
import kr.co.jnh.dao.ReviewDao;
import kr.co.jnh.domain.*;
import org.aspectj.weaver.ast.Or;
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
    public Review readOne(int rno) throws Exception{
        return reviewDao.selectOne(rno);
    }

    @Override
    public Review readOneWithOrder(int rno) throws Exception{
        return reviewDao.selectOneWithOrder(rno);
    }

    @Override
    @Transactional(rollbackFor = {Exception.class})
    public int modify(Review review) throws Exception {
        int result = reviewDao.update(review);
        productDao.updateReviewAvg();
        return result;
    }

    @Override
    public int selectPageCnt(Map map) throws Exception{
        return reviewDao.selectPageCnt(map);
    }

    @Override
    public List<Review> readPage(Map map) throws Exception{
        return reviewDao.selectPage(map);
    }

    @Override
    public List<Review> readPageWithOrder(Map map) throws Exception{
        return reviewDao.reviewWithOrder(map);
    }

    @Override
    public int readPageByReviewCnt(SearchCondition sc) throws Exception{
        return reviewDao.SelectPageByReviewCnt(sc);
    }

    @Override
    public List<Review> readPageByReview(SearchCondition sc) throws Exception{
        List<Review> reviews = reviewDao.SelectPageByReview(sc);
        for (Review review : reviews) {
            Product product = productDao.select(review.getProduct_id());
            Order order = new Order();
            order.setProduct(product);
            review.setOrder(order);
        }
        return reviews;
    }
}
