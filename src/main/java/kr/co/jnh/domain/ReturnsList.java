package kr.co.jnh.domain;

import java.util.List;

public class ReturnsList {
    private List<Returns> returnsList;

    public ReturnsList() { } // 기본 생성자 필수!

    public List<Returns> getReturnsList() {
        return returnsList;
    }

    public void setReturnsList(List<Returns> returnsList) {
        this.returnsList = returnsList;
    }
}
