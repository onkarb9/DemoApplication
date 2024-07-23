package com.demo.dao;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.demo.entities.Expense;

public interface ExpenseRepository extends CrudRepository<Expense, Long> {
	
	public Expense findByUsername(String un);
	
	@Query("SELECT e FROM Expense e WHERE e.username = :username AND e.expenseDate LIKE :datePattern ORDER BY e.expenseDate ASC")
	public List<Expense> findexpenses(@Param("username") String username,@Param("datePattern") String date);
	
	@Query("SELECT SUM(e.amount) FROM Expense e WHERE e.username = :username AND e.expenseDate LIKE :datePattern")
    public BigDecimal sumExpenses(@Param("username") String username, @Param("datePattern") String date);
}