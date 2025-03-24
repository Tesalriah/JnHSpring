package kr.co.jnh.service;

import kr.co.jnh.dao.ReportReviewDao;
import kr.co.jnh.dao.ReviewDao;
import kr.co.jnh.dao.UserDao;
import kr.co.jnh.domain.ReportReview;
import kr.co.jnh.domain.Review;
import kr.co.jnh.domain.SearchCondition;
import kr.co.jnh.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ReportReviewServiceImpl implements ReportReviewService {

    @Autowired
    ReportReviewDao reportReviewDao;

    @Autowired
    UserDao userDao;

    @Autowired
    ReviewDao reviewDao;

    @Override
    public List<ReportReview> readAll() throws Exception{
        return reportReviewDao.selectAll();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int write(ReportReview reportReviewre) throws Exception{
        int result = reportReviewDao.insert(reportReviewre);
        // 해당유저의 누적신고 수를 가져와서 + 1을 하여 저장
        User user = userDao.selectUserById(reportReviewre.getUser_id());
        int cumulative_report = user.getCumulative_report() + 1;

        // 누적신고 + 1 한 값을 해당유저 테이블에 저장
        Map map = new HashMap();
        map.put("user_id", reportReviewre.getUser_id());
        map.put("cumulative_report", cumulative_report);
        if(userDao.update(map) != 1){
            throw new Exception("USER_UPDATE_FAIL");
        }

        // 리뷰테이블의 신고횟수를 +1 하여 저장
        Review review = reviewDao.selectOne(reportReviewre.getRno());
        review.setReport_cnt(review.getReport_cnt() + 1);
        if(reviewDao.update(review) != 1){
            throw new Exception("REVIEW_UPDATE_FAIL");
        }

        return result;
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
    public int checkDup(Map map) throws Exception{
        return reportReviewDao.selectDup(map);
    }

    @Override
    public int readPageCnt(SearchCondition sc) throws Exception{
        return reportReviewDao.selectPageCnt(sc);
    }

    @Override
    public List<ReportReview> readPage(SearchCondition sc) throws Exception{
        List<ReportReview> reportReviews = reportReviewDao.selectPage(sc);
        for (ReportReview reportReview : reportReviews) {
            String contents = reportReview.getContents();
            contents = contents.replace("\n", "<br>");
            reportReview.setContents(contents);
        }
        return reportReviews;
    }
}
