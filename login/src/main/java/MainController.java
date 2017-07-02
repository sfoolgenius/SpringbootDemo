package com.github.springbootdemo;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;

/* Controller */
@Controller
public class MainController {

	@GetMapping("/")
	public String index(@SessionAttribute(WebSecurityConfig.SESSION_KEY) String account, Model model) {
		model.addAttribute("name", account);
		return "index";
	}

	@GetMapping("/login")
	public String login() {
		return "login";
	}

	@PostMapping("/loginPost")
	public @ResponseBody Map<String, Object> loginPost(String account, String password, HttpSession session) {
		Map<String, Object> map = new HashMap<>();
		if (!"123456".equals(password)) {
			map.put("success", false);
			map.put("message", "wrong password");
			return map;
		}

		// set session
		session.setAttribute(WebSecurityConfig.SESSION_KEY, account);

		map.put("success", true);
		map.put("message", "login success");
		return map;
	}

	@GetMapping("/logout")
	public String logout(HttpSession session) {
		// remove session
		session.removeAttribute(WebSecurityConfig.SESSION_KEY);
		return "redirect:/login";
	}

}
