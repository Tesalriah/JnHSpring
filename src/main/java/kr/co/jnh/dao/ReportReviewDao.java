package kr.co.jnh.dao;

import kr.co.jnh.domain.ReportReview;
import kr.co.jnh.domain.SearchCondition;

import java.util.List;
import java.util.Map;

public interface ReportReviewDao {
    List<ReportReview> selectAll() throws Exception;

    int insert(ReportReview reportReviewre) throws Exception;

    int update(Integer no) throws Exception;

    int delete(Integer no) throws Exception;

    int selectDup(Map map) throws Exception;

    int selectPageCnt(SearchCondition sc) throws Exception;

    List<ReportReview> selectPage(SearchCondition sc) throws Exception;
}
