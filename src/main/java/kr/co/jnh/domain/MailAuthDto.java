package kr.co.jnh.domain;

public class MailAuthDto {

    private String email;
    private String auth_number;

    public MailAuthDto() {}

    public MailAuthDto(String email, String auth_number) {
        this.email = email;
        this.auth_number = auth_number;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAuth_number() {
        return auth_number;
    }

    public void setAuth_number(String auth_number) {
        this.auth_number = auth_number;
    }

    @Override
    public String toString() {
        return "MailAuth{" +
                "email='" + email + '\'' +
                ", auth_number='" + auth_number + '\'' +
                '}';
    }
}
