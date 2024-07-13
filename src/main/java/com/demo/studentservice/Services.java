package com.demo.studentservice;
import java.time.LocalDateTime;
import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.dao.LoginHistoryRepository;
import com.demo.dao.UserRepository;
import com.demo.entities.LoginHistory;
import com.demo.entities.Userdata;

import jakarta.servlet.http.HttpSession;

@Service
public class Services {
	
	@Autowired
	UserRepository us ;
	
	@Autowired
	LoginHistoryRepository lgh;
	
	@Autowired
    private PasswordService passwordService;
	

	public void create(String name,String email,String username,String password) {
		
		String hashedPassword = passwordService.hashPassword(password);
		Userdata ud=new Userdata();
				ud.setName(name);
				ud.setEmail(email);
				ud.setUsername(username);
				ud.setPassword(hashedPassword);
				ud.setCreated(LocalDateTime.now());
				us.save(ud);
		
	}

	public boolean isAuthenticated(String username, String userpassword) {
		try {
			List<Userdata> sd=us.findByUsername(username);
			String un = null;
			String up = null;
			for(Userdata x: sd) {
				un=x.getUsername();
				up=x.getPassword();
			}
			if(un.equals(username)&& passwordService.verifyPassword(userpassword, up)) {
				Userdata user = sd.get(0);
				LoginHistory lg=new LoginHistory();
				lg.setU(user);
				lg.setUsername(username);
				lg.setLoginTime(LocalDateTime.now());
				
				
				user.setLogincount(user.getLogincount() + 1);
				user.setLatestlogin(LocalDateTime.now());
				
				us.save(user);
		        lgh.save(lg);
				
				return true;
			}

			else {
				return false;
			}
		}
		catch(Exception e)
		{
			return false;
		}
		}
	
	public String getName(String username) {
		List<Userdata> sd=us.findByUsername(username);
		String name = null;
		for(Userdata x: sd) {
			name=x.getName();
		}
		return name;
	}

	
	public boolean isEmailPresent(String useremail) {
		List<Userdata> sdem=us.findByEmail(useremail);
		String uemail = null;
		
		for(Userdata x: sdem) {
			uemail=x.getEmail();
		}
		
		if(uemail==null) {
			return false;
		}
		else if(uemail.equals(useremail)) {
			return true;
		}
		else 
		return false;
	}

	public boolean isUsernamePresent(String username) {
		List<Userdata> sdun=us.findByUsername(username);
		String uname = null;
		
		for(Userdata x: sdun) {
			uname=x.getUsername();
		}
		
		if(uname==null) {
			return false;
		}
		else if(uname.equals(username)) {
			return true;
		}
		else 
		return false;
	}
	
	public void updateuserdetails(String username,String updateField,String newValue,HttpSession session)
	{
		List<Userdata> users = us.findByUsername(username);
		Userdata user = users.get(0);
		switch (updateField) {
		case "name":
			user.setName(newValue);
			break;
		case "email":
			user.setEmail(newValue);
			break;
		case "username":
			user.setUsername(newValue);
			session.setAttribute("loggedInUser", newValue);
			System.out.println(session.getAttribute("loggedinUser"));
			break;
		case "password":
			user.setPassword(passwordService.hashPassword(newValue));
			break;
	}
		us.save(user);
	}
	

}
