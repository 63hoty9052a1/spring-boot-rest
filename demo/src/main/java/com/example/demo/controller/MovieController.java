package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MovieController {

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String restPage() {

		return "/index.html";
	}
}
