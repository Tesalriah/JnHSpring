package kr.co.jnh.domain;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class ChangePwd {

    String newPwd;
    String checkNewPwd;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date birth;

    public ChangePwd() {};

    public String getNewPwd() {
        return newPwd;
    }

    public void setNewPwd(String newPwd) {
        this.newPwd = newPwd;
    }

    public String getCheckNewPwd() {
        return checkNewPwd;
    }

    public void setCheckNewPwd(String checkNewPwd) {
        this.checkNewPwd = checkNewPwd;
    }

    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }
}
