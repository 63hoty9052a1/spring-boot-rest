package com.example.demo.dao;

import java.util.List;

import com.example.demo.entity.UserEntity;

public interface UserDao {

	public List<UserEntity> getUserInfo();

	public List<UserEntity> findByName(String name);

	public int deleteUser(List<UserEntity> ueList);
}
