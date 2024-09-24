package kr.co.jnh.domain;

import org.apache.ibatis.session.ResultHandler;
import org.springframework.web.util.UriComponentsBuilder;

public class SearchCondition{
    private Integer page = 1;
    private Integer pageSize = 10;
    //    private Integer offset = 0;
    private String keyword = "";
    private String option = "";
    private String category = "";
    private String gender = "";

    public SearchCondition(){}

    public SearchCondition(Integer page, Integer pageSize, String keyword, String option, String category, String gender) {
        this.page = page;
        this.pageSize = pageSize;
        this.keyword = keyword;
        this.option = option;
        this.category = category;
        this.gender = gender;
    }

    public String getQueryString(Integer page){
        return UriComponentsBuilder.newInstance()
                .queryParam("page", page)
                .queryParam("pageSize", pageSize)
                .queryParam("gender", gender)
                .queryParam("category", category)
                .queryParam("keyword", keyword)
                .queryParam("option", option)
                .build().toString();
    }

    public String getNonOption(Integer page){
        return UriComponentsBuilder.newInstance()
                .queryParam("page", page)
                .queryParam("pageSize", pageSize)
                .queryParam("gender", gender)
                .queryParam("category", category)
                .queryParam("keyword", keyword)
                .build().toString();
    }

    public String getQueryString(){
        return getQueryString(page);
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getOffset() {
        return (page-1) * pageSize;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String getOption() { return option; }

    public void setOption(String option) { this.option = option; }

    public String getCategory() { return category; }

    public void setCategory(String category) { this.category = category; }

    public String getGender() { return gender; }

    public void setGender(String gender) { this.gender = gender; }

    @Override
    public String toString() {
        return "SearchCondition{" +
                "page=" + page +
                ", pageSize=" + pageSize +
                ", offset=" + getOffset() +
                ", keyword='" + keyword + '\'' +
                ", option='" + option + '\'' +
                ", category='" + category + '\'' +
                ", gender='" + gender + '\'' +
                '}';
    }
}
