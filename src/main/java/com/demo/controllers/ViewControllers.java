package com.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.demo.entities.Session;
import com.demo.studentservice.Services;
import com.demo.studentservice.SessionService;
import com.demo.studentservice.UserDetailValidationService;

import jakarta.servlet.http.HttpSession;


@Controller
public class ViewControllers {
	
	@Autowired
	Services s;
	
	@Autowired
	UserDetailValidationService uds;
	
	@Autowired
	SessionService sessionService;
	
	
	@GetMapping("/updateuserdetails")
    public String updateuserdetails(HttpSession session) {
		String token = (String) session.getAttribute("sessionToken");
		 
		if(token!=null) {
			if(sessionService.isSessionValid(token)) {
            return "updateuserdetails";
		    } 
		    
		}
		
            return "redirect:/login"; // Redirect to login if not authenticated
	}
	

	
	@GetMapping("/updateDetails")
    public String updateDetailsget(HttpSession session,Model m) {
		String token = (String) session.getAttribute("sessionToken");
		 
		if(token!=null) {
			if(sessionService.isSessionValid(token)) {
				String username = sessionService.findByToken(token).getUsername();
				 String Fullname=s.getName(username);
				 m.addAttribute("Fullname",Fullname);
				 return "Welcome";
		    } 
		    
		}
		
            return "redirect:/login"; // Redirect to login if not authenticated
	}
	
	
	@PostMapping("/updateDetails")
	public String updateuser(HttpSession session,@RequestParam("currentPassword") String userpassword,
			@RequestParam("updateField") String updateField,@RequestParam("newValue") String newValue, Model m)
	{
		String token = (String) session.getAttribute("sessionToken");
		
		 if (token != null && sessionService.isSessionValid(token)) {
			 String username = sessionService.findByToken(token).getUsername();
		
		if(s.isAuthenticated(username,userpassword)) {
			
			s.updateuserdetails(username,updateField,newValue,session);
			
			String uname = sessionService.findByToken(token).getUsername();
		 	
			 String Fullname=s.getName(uname);
			
			m.addAttribute("Fullname",Fullname);
			
			return "Welcome";
		}
			return "updateuserdetails";
		}
			else {
		
	
		return "redirect:/login";
			}
		
	}
	
	

}
