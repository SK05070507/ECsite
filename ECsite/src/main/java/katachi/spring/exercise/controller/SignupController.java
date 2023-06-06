package katachi.spring.exercise.controller;

import java.util.Locale;
import java.util.Map;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import katachi.spring.exercise.application.service.UserApplicationService;
import katachi.spring.exercise.form.SignupForm;
import katachi.spring.exercise.model.MUser;
import katachi.spring.exercise.service.UserService;

/**
 * 会員登録を行うControllerクラス
 * @author K.Shirakawa
 *
 */
@Controller
public class SignupController {

	@Autowired
	private UserApplicationService applicationService;

	@Autowired
	private UserService userService;

	@Autowired
	private ModelMapper modelMapper;
	
	/**
	 * ユーザー登録画面に遷移
	 * @param model　
	 * @param locale
	 * @param form 会員登録フォーム
	 * @return ユーザー登録画面
	 */
	@GetMapping("/signup")
	public String getSignup(Model model, Locale locale,
			@ModelAttribute SignupForm form) {
		//性別を取得
		Map<String, Integer> genderMap = applicationService.getGenderMap(locale);
		model.addAttribute("genderMap", genderMap);								//性別選択肢
		model.addAttribute("signupForm", form);									//会員登録フォーム
		//都道府県リスト取得
		model.addAttribute("list", applicationService.getPrefectureList());		//都道府県リスト
		return "signup/signup";
	}

	/**
	 * 入力内容確認画面へ遷移
	 * @param model
	 * @param locale
	 * @param form 会員登録フォーム
	 * @param bindingResult
	 * @param errors
	 * @return 入力内容確認
	 */
	@PostMapping(value = "/signup", params = "submit")
	public String postSignup(
			Model model, 
			Locale locale, 
			@ModelAttribute @Validated SignupForm form,
			BindingResult bindingResult, 
			Errors errors) {
		model.addAttribute("SignupForm", form);
		//重複チェック
		if (!userService.userIdOne(form.getUserId())) {
			errors.rejectValue("userId", "userId.error");
		}
		//重複時エラーメッセージを表示
		if (bindingResult.hasErrors()) {
			return getSignup(model, locale, form);
		}
		return "signup/confirmation";
	}

	/**
	 * ユーザー登録
	 * @param form 会員登録フォーム
	 * @return ユーザー登録完了
	 */
	@PostMapping(value = "/user/signup", params = "complet")
	public String postCompletSignup(@ModelAttribute SignupForm form) {
		MUser user = modelMapper.map(form, MUser.class);
		//ユーザー登録処理
		userService.signup(user);
		return "signup/success";
	}

	/**
	 * ユーザー登録内容修正
	 * @param model
	 * @param locale
	 * @param form　会員登録フォーム
	 * @return ユーザー登録画面
	 */
	@PostMapping(value = "/user/signup", params = "cancel")
	public String postCancelSignup(Model model, Locale locale,
			@ModelAttribute SignupForm form) {
		// 性別を取得
		Map<String, Integer> genderMap = applicationService.getGenderMap(locale);
		model.addAttribute("genderMap", genderMap);								//性別選択肢
		model.addAttribute("signupForm", form);									//会員登録フォーム
		//都道府県リストリスト取得
		model.addAttribute("list", applicationService.getPrefectureList());		//都道府県リスト
		// ユーザー登録画面に遷移
		return "signup/signup";
	}
}
