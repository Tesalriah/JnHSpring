package com.fastcampus.ch4.domain;

import org.springframework.web.util.UriComponentsBuilder;

public class PageHandler {
//    private int page; // 현제페이지
//    private int pageSize; // 한 페이지 크기
//    private String option;
//    private String keyword;
    private SearchCondition sc;

    private int totalCnt; // 총 개시물 갯수

    private int navsize = 10; // 페이지 네비게이션의 크기
    private int totalPage; // 전체 페이지의 갯수
    private int beginPage; // 네비게이션의 첫번쨰 페이지
    private int endPage; // 네비게이션의 마지막 페이지
    private boolean showPrev; // 이전 페이지로 이동하는 링크를 보여줄 것인지의 여부
    private boolean showNext; // 다음 페이지로 이동하는 링크를 보여줄 것인지의 여부

    public PageHandler(int totalCnt, SearchCondition sc){
        this.totalCnt = totalCnt;
        this.sc = sc;

        doPaging(totalCnt, sc , navsize);
    }

    public void doPaging(int totalCnt, SearchCondition sc, int navsize){
        this.totalCnt = totalCnt;

        totalPage = (int)Math.ceil(totalCnt / (double)sc.getPageSize());
        beginPage = (sc.getPage()-1) / navsize * navsize + 1;
        endPage = Math.min(beginPage + navsize - 1, totalPage);
        showPrev = beginPage != 1;
        showNext = endPage != totalPage;
    }


    public int getTotalCnt() {
        return totalCnt;
    }

    public void setTotalCnt(int totalCnt) {
        this.totalCnt = totalCnt;
    }

    public int getNavsize() {
        return navsize;
    }

    public void setNavsize(int navsize) {
        this.navsize = navsize;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getBeginPage() {
        return beginPage;
    }

    public void setBeginPage(int beginPage) {
        this.beginPage = beginPage;
    }

    public int getEndPage() {
        return endPage;
    }

    public void setEndPage(int endPage) {
        this.endPage = endPage;
    }

    public boolean isShowPrev() {
        return showPrev;
    }

    public void setShowPrev(boolean showPrev) {
        this.showPrev = showPrev;
    }

    public boolean isShowNext() {
        return showNext;
    }

    public void setShowNext(boolean showNext) {
        this.showNext = showNext;
    }

    public SearchCondition getSc() {
        return sc;
    }

    public void setSc(SearchCondition sc) {
        this.sc = sc;
    }

    void print(){
        System.out.println("page = " + sc.getPage());
        System.out.print(showPrev ? "< " : "");
        for(int i=beginPage; i<=endPage; i++){
            System.out.print(i+" ");
        }
        System.out.println(showNext ? ">" : "");
    }

    @Override
    public String toString() {
        return "PageHandler{" +
                "sc=" + sc +
                ", totalCnt=" + totalCnt +
                ", navsize=" + navsize +
                ", totalPage=" + totalPage +
                ", beginPage=" + beginPage +
                ", endPage=" + endPage +
                ", showPrev=" + showPrev +
                ", showNext=" + showNext +
                '}';
    }
}
