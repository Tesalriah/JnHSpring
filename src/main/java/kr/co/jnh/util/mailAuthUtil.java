package kr.co.jnh.util;

public class mailAuthUtil {

    public static String customMsg(Integer authNum){
        String content = "<div style='font-family: Arial, sans-serif; text-align: center; padding: 20px; border: 1px solid #ddd; border-radius: 10px; width: 400px; margin: auto;'>"
                + "<h2 style='color: #007bff;'>인증번호 확인</h2>"
                + "<p>아래 인증번호를 입력하여 회원가입을 완료하세요.</p>"
                + "<h3 style='font-size: 24px; color: #ff5722; margin: 10px 0;'>" + authNum + "</h3>"
                + "<hr>"
                + "<p style='font-size: 12px; color: #777;'>이메일을 보낸 적이 없다면 무시해주세요.</p>"
                + "</div>";
        return  content;
    }
}
