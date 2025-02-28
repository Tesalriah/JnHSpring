package kr.co.jnh.service;

import kr.co.jnh.dao.ReportReviewDao;
import kr.co.jnh.domain.ReportReview;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ReportReviewServiceImpl implements ReportReviewService {

    @Autowired
    ReportReviewDao reportReviewDao;

    @Override
    public List<ReportReview> readAll() throws Exception{
        return reportReviewDao.selectAll();
    }

    @Override
    public int write(ReportReview reportReviewre) throws Exception{
        return reportReviewDao.insert(reportReviewre);
    }

    @Override
    public int modify(Integer no) throws Exception{
        return reportReviewDao.update(no);
    }

    @Override
    public int remove(Integer no) throws Exception{
        return reportReviewDao.delete(no);
    }

    @Override
    public int readPageCnt(Map map) throws Exception{
        return reportReviewDao.selectPageCnt(map);
    }

    @Override
    public List<ReportReview> readPage(Map map) throws Exception{
        return reportReviewDao.selectPage(map);
    }
}
