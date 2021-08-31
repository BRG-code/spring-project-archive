package com.wp.project2.web;

import com.wp.project2.model.User;
import com.wp.project2.service.SecurityService;
import com.wp.project2.service.UserService;
import com.wp.project2.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

// 각 페이지의 컨트롤러
@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private UserValidator userValidator;

    // 회원 가입 화면을 띄우는 Mapping
    // Request 형식이 GET이면, 이 코드가 작동함.
    @RequestMapping(value = "/signup", method = RequestMethod.GET)
    public String registration(Model model) {
        model.addAttribute("userForm", new User());
        return "register";
    }

    // 회원 가입을 하는 코드
    // Request 형식이 POST이면, 이 코드가 작동함. 즉, submit 버튼을 누르면 이 곳에서 작동한다는 뜻
    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public String registration(@ModelAttribute("userForm") User userForm, BindingResult bindingResult) {
        userValidator.validate(userForm, bindingResult);

        if (bindingResult.hasErrors()) {  return "register"; }

        // DB에 INSERT 작업을 함
        userService.save(userForm);

        // 회원가입한 ID로 자동 로그인
        securityService.autologin(userForm.getUsername(), userForm.getPasswordConfirm());

        return "redirect:/welcome";
    }

    // 로그인 화면
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(Model model, String error, String logout) {
        // 로그인 중 에러가 있다는 Param이 있으면 표시할 메시지
        if (error != null) {
            model.addAttribute("error", "ID 또는 비밀번호가 유효하지 않습니다.");
        }

        // 로그아웃을 한 경우 표시할 메시지
        if (logout != null) {
            model.addAttribute("message", "로그아웃이 완료되었습니다.");
        }

        return "login";
    }

    // 첫 링크를 /welcome로 대체
    @RequestMapping(value = {"/", "/welcome"}, method = RequestMethod.GET)
    public String welcome(Model model) {
        return "welcome";
    }
}
