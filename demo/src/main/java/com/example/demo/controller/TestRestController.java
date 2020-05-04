package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.CategoryEntity;
import com.example.demo.entity.MovieCommentEntity;
import com.example.demo.entity.MovieEntity;
import com.example.demo.entity.UserEntity;
import com.example.demo.service.MovieService;

@RestController
public class TestRestController {

	@Autowired
	MovieService movieService;

	@RequestMapping(value = "/list_rest", produces = "application/json", method = RequestMethod.POST)
	public List<Object> showUserInfo(@RequestBody String xxx) {

		List<Object> obj = new ArrayList<>();

		List<MovieEntity> meList = movieService.getMovieInfo();
		List<CategoryEntity> ceList = movieService.getMovieCategory();

		obj.add(meList);
		obj.add(ceList);

		return obj;
	}

	@RequestMapping(value = "/category", produces = "application/json", method = RequestMethod.POST)
	public List<CategoryEntity> getCategory() {

		List<CategoryEntity> ceList = movieService.getMovieCategory();

		return ceList;
	}

	@RequestMapping(value = "/comment", produces = "application/json", method = RequestMethod.POST)
	public List<MovieCommentEntity> getComment(@RequestBody MovieCommentEntity mce) {

		return movieService.getMovieComment(mce);
	}

	@RequestMapping(value = "/delete", produces = "application/json", method = RequestMethod.POST)
	public int deleteMovie(@RequestBody List<MovieEntity> meList) {

		return movieService.deleteMovie(meList);
	}

	@RequestMapping(value = "/regist_comment", produces = "application/json", method = RequestMethod.POST)
	public int registComment(@RequestBody MovieCommentEntity mce, HttpServletRequest request) {

		return movieService.registComment(mce, request);
	}

	@RequestMapping(path = "/regist_movie_link", produces = "application/json", method= RequestMethod.POST)
	public int registMovieLink(@RequestBody MovieEntity me, HttpServletRequest request) {
		return movieService.registMovieLink(me, request);
	}

	@RequestMapping(path = "/regist/{data}", produces = "application/json", method= RequestMethod.GET)
	public List<UserEntity> registData(@PathVariable("data") String data) {
		return null;
	}
}
