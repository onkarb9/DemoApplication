package com.demo.studentservice;
import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.demo.dao.UserRepository;
import com.demo.entities.Studentdata;

@Service
public class Services {
	
	@Autowired
	UserRepository us ;
	
	

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
	

}
