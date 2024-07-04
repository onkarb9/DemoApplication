package com.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.demo.entities.Studentdata;
import com.demo.studentservice.Services;

@Controller
public class ViewControllers {
	
	@Autowired
	Services s;
	
	
	@RequestMapping("/")
	public String home()
	{
		return "home";
	}
	
	
	@RequestMapping("/signup")
	public String signup()
	{
		return "signup";
	}
	
	@RequestMapping("/success")
	public String success(@RequestParam("name") String name, @RequestParam("email") String email,
			@RequestParam("username") String username, @RequestParam("password") String password)
	{
		if (username.matches("[a-zA-Z]+") && (name.matches("[a-zA-Z ]+"))) {
			if(s.isPresent(username,email)) {
				return "errpresent";
			}
			else {
		s.create(name,email,username,password);
		System.out.println("signup happened");
		return "Signuplogin"; 
		}
			}
		else
			return "errusername";
	}
	
	@RequestMapping("/login")
	public String login()
	{
		return "login";
	}
	
	@RequestMapping("/loginsuccess")
	public String loginsuccess(@RequestParam("name") String username, @RequestParam("password") String userpassword,Model m)
	{
		if(s.isAuthenticated(username,userpassword)) {
			
			String Fullname=s.getName(username);
			m.addAttribute("Fullname",Fullname);
			return "Welcome";
		}
		else
			return "errlogin";
	}

}
