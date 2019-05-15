package com.spring.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.spring.util.CommonUtil;

@Controller
public class MainController {

	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public final ModelAndView defaultPage() {
		ModelAndView model = new ModelAndView();
		if (CommonUtil.checkUserLogin()) {
			model.setViewName("index");
		} else {
			model.setViewName("redirect:/");
		}
		return model;
	}
	@RequestMapping(value = { "/", "/login**" }, method = RequestMethod.GET)
	public final ModelAndView login(@RequestParam(value = "error", required = false) final String error,
			@RequestParam(value = "logout", required = false) final String logout, final HttpServletRequest request) {
		ModelAndView model = new ModelAndView();
		if (error != null) {
			model.addObject("error", getErrorMessage(request, "SPRING_SECURITY_LAST_EXCEPTION"));
			model.setViewName("login");
			return model;
		}
		if (logout != null) {
			model.addObject("msg", "You've been logged out successfully.");
			model.setViewName("login");
			return model;
		}
		if (CommonUtil.checkUserLogin()) {
			model.setViewName("redirect:index.htm");
			return model;
		} else {
			model.setViewName("login");
			return model;
		}
	}
	
	@RequestMapping(value = "logout", method = RequestMethod.GET)
	public ModelAndView logout(final HttpServletRequest request, final HttpServletResponse response) {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		new SecurityContextLogoutHandler().logout(request, response, auth);
		return new ModelAndView("login");
	}
	

	private String getErrorMessage(final HttpServletRequest request, final String key) {
		Exception exception = (Exception) request.getSession().getAttribute(key);
		String error = "";
		if (exception instanceof BadCredentialsException) {
			error = "Invalid username and password!";
		} else if (exception instanceof LockedException) {
			error = exception.getMessage();
		} else {
			error = "Invalid username and password!";
		}
		return error;
	}

	@RequestMapping(value = {"/403", "/400", "/404"}, method = RequestMethod.GET)
	public final ModelAndView accesssDenied() {
		ModelAndView model = new ModelAndView();
		// check if user is login
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken)) {
			UserDetails userDetail = (UserDetails) auth.getPrincipal();
			model.addObject("username", userDetail.getUsername());
		}
		model.setViewName("error");
		return model;
	}
}