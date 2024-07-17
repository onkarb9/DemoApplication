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
	
	@GetMapping("/success")
    public String handleGetRequest() {
        // Redirect to signup page if a GET request is made to /success
        return "redirect:/signup";
    }
	
	@PostMapping("/success")
	public String success(@RequestParam("name") String name, @RequestParam("email") String email,
			@RequestParam("username") String username, @RequestParam("password") String password)
	{
		String result=uds.validateUserDetails(name, email, username, password);
		
		switch(result) {
		
		case "mandatoryfields":
			return "mandatoryfields";
			
		case "validname":
			return "validname";
			
		case "validemail":
			return "validemail";
			
		case "validusername":
			return "validusername";
			
		case "emailpresent":
			return "emailpresent";
			
		case "usernamepresent":
			return "usernamepresent";
			
		case "valid":
			s.create(name,email,username,password);
			System.out.println("signup happened");
			return "Signuplogin"; 
			
		default:
			return "signup"; 
			
		}
		
		}
	
	@GetMapping("/login")
	public String login(HttpSession session,Model m)
	{
		 String token = (String) session.getAttribute("sessionToken");
		 
		 if (token != null) {
			    Session dbSession = sessionService.findByToken(token);
			    if (dbSession != null && token.equals(dbSession.getToken())) {
			    	
			    	String username = dbSession.getUsername();
				    String Fullname=s.getName(username);
					m.addAttribute("Fullname",Fullname);
		            return "Welcome"; // Directly go to welcome page if already logged in
			    }   
	        }
		 
		return "login";
	}
	
	@GetMapping("/loginsuccess")
    public String handleGetRequest1(HttpSession session,Model m) {
		String token = (String) session.getAttribute("sessionToken");
		 
		if (token != null) {
		    Session dbSession = sessionService.findByToken(token);
		    if (dbSession != null && token.equals(dbSession.getToken())) {
			 	
			 String username = sessionService.findByToken(token).getUsername();
			 	
			 String Fullname=s.getName(username);
				m.addAttribute("Fullname",Fullname);
	            return "Welcome"; // Directly go to welcome page if already logged in
	        }
		}
        return "redirect:/login";
    }
	
	@PostMapping("/loginsuccess")
    public String loginsuccess(@RequestParam("name") String username, @RequestParam("password") String userpassword,HttpSession session, Model m)
	{
		
		if(s.isAuthenticated(username,userpassword)) {
			
			String Fullname=s.getName(username);
			m.addAttribute("Fullname",Fullname);
			
			
			String token = sessionService.createSession(username);
            session.setAttribute("sessionToken", token);
			
			
			
			/*m.addAttribute("username",username);
			session.setAttribute("loggedInUser", username); // Store username in session*/
			System.out.println("login happened.....");
			return "Welcome";
		}
		else
			return "errlogin";
	}
	
	@GetMapping("/updateuserdetails")
    public String updateuserdetails(HttpSession session) {
		String token = (String) session.getAttribute("sessionToken");
		 
		if (token != null) {
		    Session dbSession = sessionService.findByToken(token);
		    if (dbSession != null && token.equals(dbSession.getToken())) {
            return "updateuserdetails";
		    } 
		    
		}
		
            return "login"; // Redirect to login if not authenticated
	}
	
	
	
	
	@GetMapping("/updateDetails")
    public String updateDetailsget(HttpSession session,Model m) {
		String token = (String) session.getAttribute("sessionToken");
		 
		if (token != null) {
		    Session dbSession = sessionService.findByToken(token);
		    if (dbSession != null && token.equals(dbSession.getToken())) {
		    	
		    	 String username = sessionService.findByToken(token).getUsername();
				 String Fullname=s.getName(username);
				 m.addAttribute("Fullname",Fullname);
				 return "Welcome";
		    } 
		    
		}
		
            return "login"; // Redirect to login if not authenticated
	}
	
	
	
	
	
	
	
	
	@PostMapping("/updateDetails")
	public String updateuser(HttpSession session,@RequestParam("currentPassword") String userpassword,
			@RequestParam("updateField") String updateField,@RequestParam("newValue") String newValue, Model m)
	{
		String token = (String) session.getAttribute("sessionToken");
		
		String username = sessionService.findByToken(token).getUsername();
		 
		 if (username != null && sessionService.findByToken(token) != null) {
		
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
	
	@GetMapping("/logout")
	public String logout(HttpSession session)
	{
		String token = (String) session.getAttribute("sessionToken");
		 
		if (token != null) {
		    Session dbSession = sessionService.findByToken(token);
		    if (dbSession != null && token.equals(dbSession.getToken())) {
			 sessionService.deleteByToken(token);
			session.invalidate();
			return "home";
		}
		}
			return "login";
				 
		
	}

}
