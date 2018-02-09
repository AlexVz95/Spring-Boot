package com.udemy.backendninja.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.udemy.backendninja.constant.ViewConstant;
import com.udemy.backendninja.model.UserCredential;

@Controller
public class LoginController {
	
	private static final Log LOGGER = LogFactory.getLog(LoginController.class);

	@GetMapping("/")
	public String redirectToLogin() {
		LOGGER.info("METHOD: redirectToLogin()");
		return "redirect:/login";
	}
	
	@GetMapping("/login")
	public String showLoginForm(Model model,
			@RequestParam(name="error", required=false) String error,
			@RequestParam(name="logout", required=false) String logout) {
		LOGGER.info("METHOD: showLoginForm() -- PARAMS: error: " + error + ", Logout" + logout);
		model.addAttribute("error", error);
		model.addAttribute("logout", logout);
		
		model.addAttribute("userCredentials", new UserCredential());
		
		LOGGER.info("Returning to login view");
		return ViewConstant.LOGIN_VIEW;
	}
	
	@PostMapping("/logincheck")
	public String loginCheck(@ModelAttribute(name="userCredentials") UserCredential userCredential) {
		LOGGER.info("METHOD: loginCheck() -- PARAMS: " + userCredential.toString());
		
		if(userCredential.getUsername().equals("user") && userCredential.getPassword().equals("user")) {
			LOGGER.info("Returning to contacts view");
			return "redirect:/contacts/showcontacts";
		}
		
		LOGGER.info("Returning to login?error view");
		return "redirect:/login?error";
	}
}
