package kr.co.jnh.service;

import kr.co.jnh.domain.ReportReview;
import kr.co.jnh.domain.SearchCondition;

import java.util.List;
import java.util.Map;

public interface ReportReviewService {
    List<ReportReview> readAll() throws Exception;

    int write(ReportReview reportReviewre) throws Exception;

    int modify(Integer no) throws Exception;

    int remove(Integer no) throws Exception;

    int checkDup(Map map) throws Exception;

    int readPageCnt(SearchCondition sc) throws Exception;

    List<ReportReview> readPage(SearchCondition sc) throws Exception;
}
