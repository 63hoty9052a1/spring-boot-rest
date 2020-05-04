package com.example.demo.dao;

import java.util.List;

public interface Dao<T>{

	public List<T> findById(String id);
}
