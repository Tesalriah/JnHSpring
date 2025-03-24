package kr.co.jnh.dao;

import kr.co.jnh.domain.Faq;
import kr.co.jnh.domain.SearchCondition;

import java.util.List;

public interface FaqDao {
    int insert(Faq faq) throws Exception;

    int count(SearchCondition sc) throws Exception;

    List<Faq> selectAll(SearchCondition sc) throws Exception;

    Faq selectNo(int faq) throws Exception;

    int update(Faq faq) throws Exception;

    int delete(Integer faq) throws Exception;
}
