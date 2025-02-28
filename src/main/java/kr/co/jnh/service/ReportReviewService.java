package kr.co.jnh.service;

import kr.co.jnh.domain.ReportReview;

import java.util.List;
import java.util.Map;

public interface ReportReviewService {
    List<ReportReview> readAll() throws Exception;

    int write(ReportReview reportReviewre) throws Exception;

    int modify(Integer no) throws Exception;

    int remove(Integer no) throws Exception;

    int readPageCnt(Map map) throws Exception;

    List<ReportReview> readPage(Map map) throws Exception;
}
