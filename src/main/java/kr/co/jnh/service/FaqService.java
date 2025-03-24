package kr.co.jnh.service;

import kr.co.jnh.domain.Faq;
import kr.co.jnh.domain.SearchCondition;

import java.util.List;
import java.util.Map;

public interface FaqService {
    /* FAQ 작성하기 -> 관리자만 작성할 수 있게 구현*/
    int write(Faq faq) throws Exception;

    /* 글 갯수 세기 */
    int getCount(SearchCondition sc) throws Exception;

    /* 전체 게시글 불러오기*/
    List<Faq> readAll(SearchCondition sc) throws Exception;

    /* 해당 번호의 게시글 보여주기 */
    Faq readNo(int faq) throws Exception;

    /* 게시글 수정 */
    int modify(Faq faq) throws Exception;

    /* 게시글 삭제 */
    int delete(Integer no) throws Exception;
}
