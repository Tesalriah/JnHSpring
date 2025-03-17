package kr.co.jnh.domain;

import java.util.Date;

public class ReportReview {
    private Integer no;
    private String user_id;
    private String reporter_id;
    private String reason;
    private Integer rno;
    private String contents;
    private Date reg_date;

    @Override
    public String toString() {
        return "ReportReview{" +
                "no=" + no +
                ", user_id='" + user_id + '\'' +
                ", reporter_id='" + reporter_id + '\'' +
                ", reason='" + reason + '\'' +
                ", rno=" + rno +
                ", contents='" + contents + '\'' +
                ", reg_date=" + reg_date +
                '}';
    }

    public ReportReview(){}

    public ReportReview(String user_id, String reporter_id, String reason, Integer rno, String contents) {
        this.user_id = user_id;
        this.reporter_id = reporter_id;
        this.reason = reason;
        this.rno = rno;
        this.contents = contents;
    }

    public Integer getNo() {
        return no;
    }

    public void setNo(Integer no) {
        this.no = no;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getReporter_id() {
        return reporter_id;
    }

    public void setReporter_id(String reporter_id) {
        this.reporter_id = reporter_id;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Integer getRno() {
        return rno;
    }

    public void setRno(Integer rno) {
        this.rno = rno;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public Date getReg_date() {
        return reg_date;
    }

    public void setReg_date(Date reg_date) {
        this.reg_date = reg_date;
    }
}
