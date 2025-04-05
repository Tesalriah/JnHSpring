package kr.co.jnh.dao;

import kr.co.jnh.domain.Review;
import kr.co.jnh.domain.SearchCondition;

import java.util.List;
import java.util.Map;

public interface ReviewDao {
    List<Review> selectAll() throws Exception;

    Review selectOne(int rno) throws Exception;

    Review selectOneWithOrder(int rno) throws Exception;

    int insert(Review review) throws Exception;

    int update(Review review) throws Exception;

    int cancelDelete(Map map) throws Exception;

    int selectPageCnt(Map map) throws Exception;

    List<Review> selectPage(Map map) throws Exception;

    List<Review> reviewWithOrder(Map map) throws Exception;

    int SelectPageByReviewCnt(SearchCondition sc) throws Exception;

    List<Review> SelectPageByReview(SearchCondition sc) throws Exception;
}
