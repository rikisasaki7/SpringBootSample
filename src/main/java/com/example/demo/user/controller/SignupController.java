package com.example.demo.user.controller;

import java.util.Locale;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.user.application.UserApplicationService;
import com.example.demo.user.form.SignupForm;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/user")
public class SignupController {

	private final UserApplicationService userApplicationService;

	public SignupController(UserApplicationService userApplicationService) {
		this.userApplicationService = userApplicationService;
	}

	/** ユーザー登録画面を表示 */
	@GetMapping("/signup")
	public String getSignup(
			Model model,
			Locale locale,
			@ModelAttribute SignupForm signupForm) {
		Map<String, Integer> genderMap = userApplicationService.getGengerMap(locale);
		model.addAttribute("genderMap", genderMap);

		// ユーザー登録画面へ遷移
		return "user/signup";
	}

	/** ユーザー登録処理 */
	@PostMapping("/signup")
	public String postSignup(
			Model model,
			@Validated @ModelAttribute SignupForm signupForm,
			BindingResult bindingResult,
			Locale locale) {
		log.info("SignupForm: {}", signupForm);

		// 入力チェック結果
		if (bindingResult.hasErrors()) {
			log.info("入力エラーあり:{}", signupForm);
			log.info("bindingresult:{}", bindingResult);
			return getSignup(model, Locale.getDefault(), signupForm);
		}
		// ログイン画面にリダイレクト
		return "redirect:/login";
	}

}
