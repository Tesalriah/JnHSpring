package kr.co.jnh.validation;

import kr.co.jnh.domain.ChangePwd;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public class ChangePwdValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return ChangePwd.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        ChangePwd form = (ChangePwd) target;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "newPwd", "required");

        String newPwd = form.getNewPwd();
        String checkNewPwd = form.getCheckNewPwd();

        if(newPwd.length() > 1 && newPwd.length() <  5 || newPwd.length() >= 20) {
            errors.rejectValue("newPwd", "invalidLength", new String[]{"5","20"}, null);
        }

        if (newPwd == null || checkNewPwd == null || !newPwd.equals(checkNewPwd)) {
            errors.rejectValue("checkNewPwd", "mismatch");
        }
    }
}
