package kr.co.jnh.domain;

import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import java.util.Date;

public class User {

    private Integer grade;
    private String user_id;
    private String user_pwd;
    private String user_name;
    private String email;
    private String phone;
    private String address;
    private String gender;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date birth;
    private Integer status;
    private Integer cumulative_report;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private Date member_since;

    public User(){};

    public User(String user_id, String user_pwd, String user_name, String email, String phone, String address, String gender, Date birth) {
        this.user_id = user_id;
        this.user_pwd = user_pwd;
        this.user_name = user_name;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.gender = gender;
        this.birth = birth;
    }

    @Override
    public String toString() {
        return "User{" +
                "grade=" + grade +
                ", user_id='" + user_id + '\'' +
                ", user_pwd='" + user_pwd + '\'' +
                ", user_name='" + user_name + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", address='" + address + '\'' +
                ", gender='" + gender + '\'' +
                ", birth=" + birth +
                ", status='" + status + '\'' +
                ", cumulative_report=" + cumulative_report +
                ", member_since='" + member_since + '\'' +
                '}';
    }

    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getUser_pwd() {
        return user_pwd;
    }

    public void setUser_pwd(String user_pwd) {
        this.user_pwd = user_pwd;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getCumulative_report() {
        return cumulative_report;
    }

    public void setCumulative_report(Integer cumulative_report) {
        this.cumulative_report = cumulative_report;
    }

    public Date getMember_since() {
        return member_since;
    }

    public void setMember_since(Date member_since) {
        this.member_since = member_since;
    }
}
