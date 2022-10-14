package com.example.demo.ifaces;

import java.util.List;

public interface CrudRepository<T> {
	
	public int add(T t);
	public List<T> findAll();
	public T findById(int id);
	public T update(T t);
	public int remove(int id);
	public int update(String course,double revisedAmount);
	

}
