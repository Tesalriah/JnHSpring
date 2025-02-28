package kr.co.jnh.dao;

import kr.co.jnh.domain.ReportReview;

import java.util.List;
import java.util.Map;

public interface ReportReviewDao {
    List<ReportReview> selectAll() throws Exception;

    int insert(ReportReview reportReviewre) throws Exception;

    int update(Integer no) throws Exception;

    int delete(Integer no) throws Exception;

    int selectPageCnt(Map map) throws Exception;

    List<ReportReview> selectPage(Map map) throws Exception;
}
