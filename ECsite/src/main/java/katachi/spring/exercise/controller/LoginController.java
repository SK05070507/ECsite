package katachi.spring.exercise.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller

public class LoginController {

	//ログイン画面を表示
	/**
	 * 
	 * @return ログイン画面
	 */
	@GetMapping("/login")
	public String getLogin() {
		return "/login/login";
	}

}
