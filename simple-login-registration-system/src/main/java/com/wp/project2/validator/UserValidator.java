package com.wp.project2.validator;

import com.wp.project2.model.User;
import com.wp.project2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
// 입력한 정보가 조건에 맞는지 확인하는 Validator
public class UserValidator implements Validator {
    @Autowired
    private UserService userService;

    @Override
    public boolean supports(Class<?> aClass) {
        return User.class.equals(aClass);
    }

    @Override
    // 조건에 맞는지 확인하는 함수
    public void validate(Object o, Errors errors) {
        // User를 o로 정의 -> 아마도 Object의 인듯
        User user = (User) o;

        // rejectIfEmptyOrWhitespace = 값이 NULL or 길이가 0 or 공백 문자로 구성되어 있는 경우의 에러코드 추가
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "NotEmpty");
        // 6 ~ 18자 체크
        if (user.getUsername().length() < 6 || user.getUsername().length() > 18) {
            errors.rejectValue("username", "Size.userForm.username");
        }

        // UserService에 username이 존재하는지= 확인함. username에 값이 있는 경우 중복 ID가 있다는 뜻
        if (userService.findByUsername(user.getUsername()) != null) {
            // 중복 ID가 있다면, reject 함.
            errors.rejectValue("username", "Duplicate.userForm.username");
        }

        // rejectIfEmptyOrWhitespace = 값이 NULL or 길이가 0 or 공백 문자로 구성되어 있는 경우의 에러코드 추가
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "NotEmpty");
        // 6 ~ 32자 체크
        if (user.getPassword().length() < 8 || user.getPassword().length() > 32) {
            errors.rejectValue("password", "Size.userForm.password");
        }

        // 입력한 비밀번호와 비밀번호 재입력이 서로 맞지 않는 경우
        if (!user.getPasswordConfirm().equals(user.getPassword())) {
            // reject 함.
            errors.rejectValue("passwordConfirm", "Diff.userForm.passwordConfirm");
        }
    }
}
