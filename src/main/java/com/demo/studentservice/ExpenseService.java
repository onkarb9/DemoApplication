package com.demo.studentservice;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.dao.ExpenseRepository;
import com.demo.entities.Expense;

@Service
public class ExpenseService {
	
	@Autowired
	ExpenseRepository exrepo;
	
	public void create(String username,BigDecimal amount,String purpose,String date) {
		
		Expense obj=new Expense();
		obj.setUsername(username);
		obj.setAmount(amount);
		obj.setPurpose(purpose);
		obj.setExpenseDate(date);
		
		exrepo.save(obj);
	}


}
