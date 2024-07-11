package com.demo.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.time.LocalDateTime;

@Entity
//@Table(name = "login_history")
public class LoginHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    private String username;
    
    private LocalDateTime loginTime;
    
    @ManyToOne
    @JoinColumn(name="user_id",referencedColumnName="id")
    private Userdata u;
    
    

	public LoginHistory() {
		super();
		// TODO Auto-generated constructor stub
	}

	

	public LoginHistory(int id, String username, LocalDateTime loginTime, Userdata u) {
		super();
		this.id = id;
		this.username = username;
		this.loginTime = loginTime;
		this.u = u;
	}



	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public LocalDateTime getLoginTime() {
		return loginTime;
	}

	public void setLoginTime(LocalDateTime loginTime) {
		this.loginTime = loginTime;
	}

	public Userdata getU() {
		return u;
	}

	public void setU(Userdata u) {
		this.u = u;
	}

    
	
 
}
