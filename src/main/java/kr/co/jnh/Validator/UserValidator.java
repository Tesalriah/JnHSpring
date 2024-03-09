package kr.co.jnh.Validator;

import kr.co.jnh.domain.User;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.util.regex.Pattern;

public class UserValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
//		return User.class.equals(clazz); // 검증하려는 객체가 User타입인지 확인
        return User.class.isAssignableFrom(clazz); // clazz가 User 또는 그 자손인지 확인
    }

    @Override
    public void validate(Object target, Errors errors) {
        System.out.println("UserValidator.validate() is called");

        User user = (User)target;

        String id = user.getUser_id();
        String pwd = user.getUser_pwd();

//		if(id==null || "".equals(id.trim())) {
//			errors.rejectValue("id", "required");
//		}
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "user_id",  "required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "user_pwd", "required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "user_name", "required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "phone", "required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "gender", "required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "birth", "required");


        if(id.length() > 1 && id.length() <  5 || id.length() > 20) {
            errors.rejectValue("user_id", "invalidLength", new String[]{"5","20"}, null);
        }if(pwd.length() > 1 && pwd.length() <  5 || pwd.length() > 20) {
            errors.rejectValue("user_pwd", "invalidLength", new String[]{"5","20"}, null);
        }

        String idPattern = "^[a-z0-9]*$";
        String pwdPattern = "^[a-zA-Z0-9`~!@#$%^&*()-_=+]*$";
        String namePattern = "^[가-힣]*$";
        String phonePattern = "^\\d{10,11}$";

        if(!Pattern.matches(idPattern, user.getUser_id())){
            errors.rejectValue("user_id", "idPattern");
        }
        if(!Pattern.matches(pwdPattern, user.getUser_pwd())){
            errors.rejectValue("user_pwd", "pwdPattern");
        }
        if(!Pattern.matches(namePattern, user.getUser_name())){
            errors.rejectValue("user_name", "namePattern");
        }
        if(!Pattern.matches(phonePattern, user.getPhone())){
            errors.rejectValue("phone", "phonePattern");
        }
    }

}
