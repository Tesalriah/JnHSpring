package kr.co.jnh.service;

import kr.co.jnh.domain.Review;
import kr.co.jnh.domain.SearchCondition;

import java.util.List;
import java.util.Map;

public interface ReviewService {
    List<Review> readAll() throws Exception;

    Review readOne(int no) throws Exception;

    Review readOneWithOrder(int rno) throws Exception;

    int modify(Review review) throws Exception;

    int selectPageCnt(Map map) throws Exception;

    List<Review> readPage(Map map) throws Exception;

    List<Review> readPageWithOrder(Map map) throws Exception;

    int readPageByReviewCnt(SearchCondition sc) throws Exception;

    List<Review> readPageByReview(SearchCondition sc) throws Exception;
}
