package com.example.demo.dao;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.example.demo.entity.CategoryEntity;
import com.example.demo.entity.MovieCommentEntity;
import com.example.demo.entity.MovieEntity;

public interface MovieDao {

	public List<MovieEntity> getMovieInfo();

	public List<CategoryEntity> getMovieCategory();

	public List<MovieEntity> findByName(String name);

	public List<MovieCommentEntity> getMovieComment(MovieCommentEntity mce);

	public int deleteMovie(List<MovieEntity> ueList);

	public int registComment(MovieCommentEntity mce, HttpServletRequest request);

	public int registMovieLink(MovieEntity me, HttpServletRequest request);
}
