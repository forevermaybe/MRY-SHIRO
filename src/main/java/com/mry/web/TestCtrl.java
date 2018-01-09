package com.mry.web;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mry.entity.User;
import com.mry.service.UserService;

@Controller
public class TestCtrl {

	@Autowired
	private UserService userService;

	@RequestMapping("/add")
	@ResponseBody
	public String add(User user) {
		return userService.addone(user);
	}

	@RequestMapping("/checklogin")
	public String checklogin(User user,HttpServletRequest request) {
		UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(user.getName(),user.getPassword() );
		usernamePasswordToken.setRememberMe(true);
		Subject subject = SecurityUtils.getSubject();
		try {
			subject.login(usernamePasswordToken);
			return "index";
		} catch (Exception e) {
			String path = WebUtils.getSavedRequest(request).getRequestUrl();
			return "redirect:"+ path;
		}
	}

}
