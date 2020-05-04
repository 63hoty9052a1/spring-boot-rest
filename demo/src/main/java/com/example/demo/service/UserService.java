package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dao.UserDao;
import com.example.demo.entity.UserEntity;


@Service
@Transactional
public class UserService {

	@Autowired
	private UserDao userDao;

	public List<UserEntity> getUserInfo() {

		return userDao.getUserInfo();
	}

	public List<UserEntity> getUserByName(String name) {

		return userDao.findByName(name);
	}

	public int deleteUser(List<UserEntity> ueList) {

		return userDao.deleteUser(ueList);
	}

}
