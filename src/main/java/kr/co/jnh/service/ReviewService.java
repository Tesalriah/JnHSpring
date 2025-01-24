package kr.co.jnh.service;

import kr.co.jnh.domain.Review;

import java.util.List;
import java.util.Map;

public interface ReviewService {
    List<Review> readAll() throws Exception;

    Review readOne(int no) throws Exception;

    int modify(Review review) throws Exception;

    int remove(int no) throws Exception;

    int selectPageCnt(Map map) throws Exception;

    List<Review> selectPage(Map map) throws Exception;
}
