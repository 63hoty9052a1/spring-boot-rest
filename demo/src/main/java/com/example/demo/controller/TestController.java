package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.demo.entity.UserEntity;
import com.example.demo.service.UserService;

@Controller
public class TestController {

	@Autowired
	UserService userService;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String index() {
		return "/index.html";
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String sample(Model model) {

		//List<UserEntity> userList = userService.findByName("チカリン");
		List<UserEntity> userList = userService.getUserInfo();

		model.addAttribute("userList", userList);

		return "/index.html";
	}

	@RequestMapping(value = "/sample_rest", method = RequestMethod.GET)
	public String restSample() {

		return "/index_rest.html";
	}
}
