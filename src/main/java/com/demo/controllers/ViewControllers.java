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
		
		if (name == null || name.isEmpty() ||
	            email == null || email.isEmpty() ||
	            username == null || username.isEmpty() ||
	            password == null || password.isEmpty()) {
	            return "mandatoryfields"; // or return appropriate error message or handle it as needed
	        }
		else if(!name.matches("[a-zA-Z ]+"))
		{
			return "validname";
		}
		else if(!username.matches("[a-zA-Z]+"))
		{
			return "validusername";
		}
		else if(!email.matches("\\b[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.com\\b"))
		{
			return "validemail";
		}
		else if(s.isEmailPresent(email)) 
		{
			return "emailpresent";
		}
		else if(s.isUsernamePresent(username)) 
		{
				return "usernamepresent";
		}
			else {
		s.create(name,email,username,password);
		System.out.println("signup happened");
		return "Signuplogin"; 
		}
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
			System.out.println("login happened.....");
			return "Welcome";
		}
		else
			return "errlogin";
	}

}
