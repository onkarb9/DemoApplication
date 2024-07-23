package com.demo.entities;

import java.math.BigDecimal;
import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Expense {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String username;
	private BigDecimal amount;
	private String purpose;
    private String expenseDate;
    
    
    
	public Expense() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Expense(long id, String username, BigDecimal amount, String purpose, String expenseDate) {
		super();
		this.id = id;
		this.username = username;
		this.amount = amount;
		this.purpose = purpose;
		this.expenseDate = expenseDate;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	public String getPurpose() {
		return purpose;
	}
	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}
	public String getExpenseDate() {
		return expenseDate;
	}
	public void setExpenseDate(String expenseDate) {
		this.expenseDate = expenseDate;
	}

}
