package com.example.demo.services;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import com.example.demo.entity.Fee;
import com.example.demo.repos.FeeRepository;

public class FeeService {
	
	private FeeRepository repo=null;

	public FeeService() {
		super();
		this.repo=new FeeRepository();
	}
	
	public Fee add(Fee entity ) throws SQLException {
		
		return this.repo.add(entity)==1?entity:null;
	}
	
	public List<Fee> findAll(){
		return this.repo.findAll();
	}
	
	public Optional<Fee> findById(int key){
		return this.repo.findById(key);
	}
	
	public int removeById(int id) {
		return this.repo.remove(id);
	}
	
	public int updateByCourse(String course,double amount) {
		return this.repo.update(course, amount);
	}

}
