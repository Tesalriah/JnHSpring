package kr.co.jnh.service;

import kr.co.jnh.domain.Review;

import java.util.List;
import java.util.Map;

public interface ReviewService {
    List<Review> selectAll() throws Exception;

    Review selectOne(int no) throws Exception;

    int update(Review review) throws Exception;

    int delete(int no) throws Exception;

    int selectPageCnt(Map map) throws Exception;

    List<Review> selectPage(Map map) throws Exception;
}
