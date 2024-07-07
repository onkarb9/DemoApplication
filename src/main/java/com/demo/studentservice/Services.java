package com.demo.studentservice;
import java.time.LocalDateTime;
import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.dao.LoginHistoryRepository;
import com.demo.dao.UserRepository;
import com.demo.entities.LoginHistory;
import com.demo.entities.Studentdata;

@Service
public class Services {
	
	@Autowired
	UserRepository us ;
	
	@Autowired
	LoginHistoryRepository lgh;
	

	public void create(String name,String email,String username,String password) {
		Studentdata sd=new Studentdata();
				sd.setName(name);
				sd.setEmail(email);
				sd.setUsername(username);
				sd.setPassword(password);
				
				us.save(sd);
		
	}

	public boolean isAuthenticated(String username, String userpassword) {
		try {
			List<Studentdata> sd=us.findByUsername(username);
			String un = null;
			String up = null;
			for(Studentdata x: sd) {
				un=x.getUsername();
				up=x.getPassword();
			}
			if(un.equals(username)&& up.equals(userpassword)) {
				
				LoginHistory lg=new LoginHistory();
				
				lg.setUsername(username);
				lg.setLoginTime(LocalDateTime.now());
				
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
		List<Studentdata> sd=us.findByUsername(username);
		String name = null;
		for(Studentdata x: sd) {
			name=x.getName();
		}
		return name;
	}

	/*public boolean isPresent(String username,String useremail) {
		
		List<Studentdata> sdun=us.findByUsername(username);
		String uname = null;
		
		List<Studentdata> sdem=us.findByEmail(useremail);
		String uemail = null;
		
		
		for(Studentdata x: sdun) {
			uname=x.getUsername();
		}
		
		for(Studentdata x: sdem) {
			uemail=x.getEmail();
		}
		
		if(uname==null && uemail==null) {
			return false;
		}
		else if(uname.equals(username) && (uemail.equals(useremail))) {
			return true;
		}
		else 
		return false;
	}*/

	public boolean isEmailPresent(String useremail) {
		List<Studentdata> sdem=us.findByEmail(useremail);
		String uemail = null;
		
		for(Studentdata x: sdem) {
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
		List<Studentdata> sdun=us.findByUsername(username);
		String uname = null;
		
		for(Studentdata x: sdun) {
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
	

}
