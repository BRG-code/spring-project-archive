package org.zerock.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
	@GetMapping(value = "/")
	public String home() {
		return "redirect:/book/list";
	}

	@GetMapping("/login")
	public String redirectLogin() {
		return "redirect:/member/login";
	}
}
