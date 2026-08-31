package com.example.demo.user.controller;

import java.util.Locale;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.user.application.UserApplicationService;


@Controller
@RequestMapping("/user")
public class SignupController {

	private final UserApplicationService userApplicationService;

	public SignupController(UserApplicationService userApplicationService) {
		this.userApplicationService = userApplicationService;
	}

	/** ユーザー登録画面を表示 */
	@GetMapping("/signup")
	public String getSigｎup(Model model, Locale locale) {
		Map<String, Integer> genderMap = userApplicationService.getGengerMap(locale);
		model.addAttribute("genderMap", genderMap);

		// ユーザー登録画面へ遷移
		return "user/signup";
	}

	/** ユーザー登録処理 */
	@PostMapping("/signup")
	public String postSignup() {

		// ログイン画面にリダイレクト
		return "redirect:/login";
	}


}
