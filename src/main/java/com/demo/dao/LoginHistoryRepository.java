package com.demo.dao;
import org.springframework.data.repository.CrudRepository;

import com.demo.entities.LoginHistory;

public interface LoginHistoryRepository extends CrudRepository<LoginHistory, Integer> {
}