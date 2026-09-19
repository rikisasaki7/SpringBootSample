package com.example.demo.user.controller;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;
import java.util.Map;

import org.modelmapper.ModelMapper;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.user.application.UserApplicationService;
import com.example.demo.user.domain.model.MUser;
import com.example.demo.user.domain.service.UserService;
import com.example.demo.user.form.SignupForm;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/user")
@RequiredArgsConstructor
public class SignupController {

	private final UserApplicationService userApplicationService;

	private final UserService userService;

	private final ModelMapper modelMapper;

	// public SignupController(UserApplicationService userApplicationService) {
	// this.userApplicationService = userApplicationService;
	// }

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

		// formをMUserクラスに変換
		MUser user = modelMapper.map(signupForm, MUser.class);
		// ユーザー登録
		userService.signup(user);

		// ログイン画面にリダイレクト
		return "redirect:/login";
	}

	/** ユーザーID重複の例外処理 */
	@ExceptionHandler(DuplicateKeyException.class)
	public String duplicateKeyExceptionHandler(
			DuplicateKeyException e,
			Model model,
			HttpServletRequest request,
			RedirectAttributes redirectAttributes) {

		// 入力内容の取得
		SignupForm signupForm = generateFormFromRequest(request);
		redirectAttributes.addFlashAttribute("signupForm", signupForm);

		// エラーメッセージ
		String errorMsg = "ユーザーIDが既に使用されています。";
		redirectAttributes.addFlashAttribute("errorMessage", errorMsg);
		return "redirect:/user/signup";
	}

	/** その他の例外処理 */
	@ExceptionHandler(Exception.class)
	public String exceptionHandler(
			Exception e,
			Model model,
			HttpServletRequest request,
			RedirectAttributes redirectAttributes) {

		// 入力内容の取得
		SignupForm signupForm = generateFormFromRequest(request);
		redirectAttributes.addFlashAttribute("signupForm", signupForm);

		// エラーメッセージ
		String errorMsg = "ユーザー登録に失敗しました。";
		redirectAttributes.addFlashAttribute("errorMessage", errorMsg);
		return "redirect:/user/signup";

	}

	private SignupForm generateFormFromRequest(HttpServletRequest request) {
		SignupForm signupForm = new SignupForm();
		signupForm.setUserId(request.getParameter("userId"));
		signupForm.setPassword(request.getParameter("password"));
		signupForm.setUserName(request.getParameter("userName"));
		signupForm.setAge(Integer.valueOf(request.getParameter("age")));
		signupForm.setGender(Integer.valueOf(request.getParameter("gender")));

		String birthdayStr = request.getParameter("birthday");
		DateTimeFormatter dateTimeFormat = DateTimeFormatter.ofPattern("yyyy/MM/dd");
		LocalDate localDate = LocalDate.parse(birthdayStr, dateTimeFormat);
		Date birthday = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
		signupForm.setBirthday(birthday);
		return signupForm;
	}

}
