package kr.co.jnh.service;

import kr.co.jnh.dao.FaqDao;
import kr.co.jnh.domain.Faq;
import kr.co.jnh.domain.SearchCondition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FaqServiceImpl implements FaqService {
    @Autowired
    FaqDao faqDao;

    /* FAQ 작성하기 -> 관리자만 작성할 수 있게 구현*/
    @Override
    public int write(Faq faq) throws Exception{
        return faqDao.insert(faq);
    }

    /* 글 갯수 세기 */
    @Override
    public int getCount(SearchCondition sc) throws Exception{
        return faqDao.count(sc);
    }

    /* 전체 게시글 불러오기*/
    @Override
    public List<Faq> readAll(SearchCondition sc) throws Exception{
        return faqDao.selectAll(sc);
    }

    /* 해당 번호의 게시글 보여주기 */
    public Faq readNo(int faq) throws Exception{
        return faqDao.selectNo(faq);
    }


    /* 게시글 수정 */
    @Override
    public int modify(Faq faq) throws Exception{
        return faqDao.update(faq);
    }

    /* 게시글 삭제 */
    @Override
    public int delete(Integer faq) throws Exception{
        return faqDao.delete(faq);
    }

}
