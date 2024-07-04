package com.demo.dao;

import java.util.*;
import org.springframework.data.repository.CrudRepository;

import com.demo.entities.Studentdata;

public interface UserRepository extends CrudRepository<Studentdata, Integer>{
	
	public List<Studentdata> findByUsername(String un);
	
	public List<Studentdata> findByName(String un);
	
	public List<Studentdata> findByEmail(String un);

}
