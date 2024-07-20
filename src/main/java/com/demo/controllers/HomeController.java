package com.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.demo.entities.Session;
import com.demo.studentservice.Services;
import com.demo.studentservice.SessionService;
import com.demo.studentservice.UserDetailValidationService;

import jakarta.servlet.http.HttpSession;

@Controller
public class HomeController {
	
	@Autowired
	Services s;
	
	@Autowired
	UserDetailValidationService uds;
	
	@Autowired
	SessionService sessionService;
	
	@GetMapping("/")
	public String home()
	{
		return "home";
	}
	
	@GetMapping("/signup")
	public String signup()
	{
		return "signup";
	}
	
	@GetMapping("/login")
	public String login(HttpSession session,Model m)
	{
		 String token = (String) session.getAttribute("sessionToken");
		 
		 if(token!=null) {
			 System.out.println(token);
				if(sessionService.isSessionValid(token)) {
					System.out.println("session validated");
					String username = sessionService.findByToken(token).getUsername();
				    String Fullname=s.getName(username);
					m.addAttribute("Fullname",Fullname);
		            return "Welcome"; // Directly go to welcome page if already logged in
			    }   
	        }
		 
		return "login";
	}
	

}
