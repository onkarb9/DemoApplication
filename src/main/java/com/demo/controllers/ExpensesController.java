package com.demo.controllers;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.demo.dao.ExpenseRepository;
import com.demo.entities.Expense;
import com.demo.studentservice.ExpenseService;
import com.demo.studentservice.Services;
import com.demo.studentservice.SessionService;

import jakarta.servlet.http.HttpSession;

@Controller
public class ExpensesController {
	
	@Autowired
	ExpenseService expenseservice;
	
	@Autowired
	SessionService sessionService;
	
	@Autowired
	ExpenseRepository exrepo;
	
	@Autowired
	Services s;
	
	@GetMapping("/expenses")
	public String expenses(HttpSession session) {
		
		String token = (String) session.getAttribute("sessionToken");
		 
		if(token!=null) {
			if(sessionService.isSessionValid(token)) {
					return "expenses";
			}
		}
		return "redirect:/login";
	}
	
	@PostMapping("/expenses/add")
	public String addexpenses(HttpSession session, @RequestParam("amount") BigDecimal amount, 
			@RequestParam("purpose") String purpose, 
			@RequestParam("exdate") String date) {
		
		String token = (String) session.getAttribute("sessionToken");
		 
		if(token!=null) {
			if(sessionService.isSessionValid(token)) {
				String username = sessionService.findByToken(token).getUsername();
				expenseservice.create(username,amount,purpose,date);
				return "redirect:/expenses";
			}
		}
		
		return "redirect:/login";
		
	}
	
	@PostMapping("/previous")
	public String previousexpenses(HttpSession session, @RequestParam("monthYear") String monthYear,Model m) {
		
		String token = (String) session.getAttribute("sessionToken");
		 
		if(token!=null) {
			if(sessionService.isSessionValid(token)) {
				String username = sessionService.findByToken(token).getUsername();
				String date=monthYear+"%";
				List<Expense> ex=exrepo.findexpenses(username, date);
				
					BigDecimal sum=exrepo.sumExpenses(username,date);
					String Fullname=s.getName(username);
					m.addAttribute("Fullname",Fullname);
					m.addAttribute("Expenses",ex);
					m.addAttribute("Sum",sum);
					m.addAttribute("date",monthYear);
					
					System.out.println(ex);
					return "viewexpenses";
				
			}
		}
		
		return "redirect:/login";
		
		
	}


}
