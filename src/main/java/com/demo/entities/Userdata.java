package com.demo.entities;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.LocalDateTime;

@Entity
@Table(name="Account")
public class Userdata {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userid;
    private String name;
    private String email;
	private String username;
	private String password;
	private LocalDateTime created;
	private LocalDateTime latestlogin;
	private int logincount;
	
	
	
	public Userdata() {
		super();
		// TODO Auto-generated constructor stub
	}



	public Userdata(Long userid, String name, String email, String username, String password, LocalDateTime created,
			LocalDateTime latestlogin, int logincount) {
		super();
		this.userid = userid;
		this.name = name;
		this.email = email;
		this.username = username;
		this.password = password;
		this.created = created;
		this.latestlogin = latestlogin;
		this.logincount = logincount;
	}



	public Long getUserid() {
		return userid;
	}



	public void setUserid(Long userid) {
		this.userid = userid;
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public String getEmail() {
		return email;
	}



	public void setEmail(String email) {
		this.email = email;
	}



	public String getUsername() {
		return username;
	}



	public void setUsername(String username) {
		this.username = username;
	}



	public String getPassword() {
		return password;
	}



	public void setPassword(String password) {
		this.password = password;
	}



	public LocalDateTime getCreated() {
		return created;
	}



	public void setCreated(LocalDateTime created) {
		this.created = created;
	}



	public LocalDateTime getLatestlogin() {
		return latestlogin;
	}



	public void setLatestlogin(LocalDateTime latestlogin) {
		this.latestlogin = latestlogin;
	}



	public int getLogincount() {
		return logincount;
	}



	public void setLogincount(int logincount) {
		this.logincount = logincount;
	}
	
	
	
	
}
