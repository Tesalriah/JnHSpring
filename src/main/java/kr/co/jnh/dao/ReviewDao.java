package kr.co.jnh.dao;

import kr.co.jnh.domain.Review;
import kr.co.jnh.domain.SearchCondition;

import java.util.List;
import java.util.Map;

public interface ReviewDao {
    List<Review> selectAll() throws Exception;

    Review selectOne(int no) throws Exception;

    int insert(Review review) throws Exception;

    int update(Review review) throws Exception;

    float reviewAvg(String product_id) throws Exception;

    int delete(int no) throws Exception;

    int selectPageCnt(Map map);

    List<Review> selectPage(Map map);

    int SelectPageByReportCnt(SearchCondition sc);

    List<Review> SelectPageByReport(SearchCondition sc);
}
