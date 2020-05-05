package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.CategoryEntity;
import com.example.demo.entity.MovieCommentEntity;
import com.example.demo.entity.MovieEntity;
import com.example.demo.service.MovieService;

@RestController
public class TestRestController {

	@Autowired
	MovieService movieService;

	@RequestMapping(value = "/movie_list", produces = "application/json", method = RequestMethod.POST)
	public List<Object> showUserInfo() {

		List<Object> obj = new ArrayList<>();

		List<MovieEntity> meList = movieService.getMovieInfo();
		List<CategoryEntity> ceList = movieService.getMovieCategory();

		obj.add(meList);
		obj.add(ceList);

		return obj;
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

	@RequestMapping(path = "/update_movie", produces = "application/json", method= RequestMethod.POST)
	public int updateMovie(@RequestBody MovieEntity me, HttpServletRequest request) {
		return movieService.updateMovie(me, request);
	}
}
