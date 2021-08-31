package com.cocoblue.securitytest.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {
    @RequestMapping("/main")
    public String main() {
        return "redirect:/board/posts";
    }

    @GetMapping("/login")
    public String login() {
        return "members/login";
    }
}