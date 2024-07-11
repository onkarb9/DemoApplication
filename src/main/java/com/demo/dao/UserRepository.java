package com.demo.dao;

import java.util.*;
import org.springframework.data.repository.CrudRepository;

import com.demo.entities.Userdata;

public interface UserRepository extends CrudRepository<Userdata, Long>{
	
	public List<Userdata> findByUsername(String un);
	
	public List<Userdata> findByName(String un);
	
	public List<Userdata> findByEmail(String un);

}
