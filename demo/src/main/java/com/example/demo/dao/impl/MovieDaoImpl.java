package com.example.demo.dao.impl;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Repository;

import com.example.demo.dao.MovieDao;
import com.example.demo.entity.CategoryEntity;
import com.example.demo.entity.MovieCommentEntity;
import com.example.demo.entity.MovieEntity;
import com.example.demo.util.EntityManegarUtil;

@Repository
public class MovieDaoImpl implements MovieDao{

	@PersistenceContext
	private EntityManager em;

	private static final String SELECT_MOVIE_INFO =
			"SELECT " +
			"  t1.id " +
			" ,t1.name " +
			" ,t1.url " +
			" ,t1.thumbnail_url " +
			" ,t1.note " +
			" ,t2.category_id " +
			" ,t1.delete_flag " +
			" ,(SELECT count(t3.mid) FROM movie_comment t3 WHERE t1.id = t3.mid) as comment_count " +
			"FROM  " +
			"	m_movie t1 " +
			"	LEFT JOIN m_category t2 ON t1.category_id = t2.category_id AND t2.delete_flag = 0" +
			"WHERE " +
			"	t1.delete_flag = 0 ";

	private static final String SELECT_MOVIE_CATEGORY =
			"SELECT " +
			"  category_id " +
			" ,category_name " +
			" ,delete_flag " +
			"FROM  " +
			"	m_category " +
			"WHERE " +
			"	delete_flag = 0 ";

	private static final String SELECT_MOVIE_COMMENT =
			"SELECT " +
			"  t1.cid " +
			" ,t1.mid " +
			" ,t1.comment " +
			" ,t1.time " +
			"FROM  " +
			"	movie_comment t1 " +
			"WHERE " +
			"	t1.mid = ?1 " +
			"ORDER BY t1.time ASC";

	private static final String  DELETE_MOVIE =
			"UPDATE m_movie " +
			"  SET delete_flag = 1 " +
			"WHERE " +
			"	id IN ( ";

	private static final String  INSERT_COMMENT =
			"INSERT INTO movie_comment(mid, comment, remote_address) " +
			" VALUES ( ?1, ?2, ?3 )";

	private static final String  INSERT_MOVIE_LINK =
			"INSERT INTO m_movie(name, url, thumbnail_url, note, category_id) " +
			" VALUES ( ?1, ?2, ?3, ?4, ?5 )";

	@Override
	public List<MovieEntity> getMovieInfo() {

		return EntityManegarUtil.getSqlResultList(SELECT_MOVIE_INFO, MovieEntity.class, em);
	}

	@Override
	public List<CategoryEntity> getMovieCategory() {
		return EntityManegarUtil.getSqlResultList(SELECT_MOVIE_CATEGORY, CategoryEntity.class, em);
	}

	@Override
	public int deleteMovie(List<MovieEntity> meList) {
		Map<Integer, Integer> paramMap = new LinkedHashMap<Integer, Integer>();
		String addSqlString = "";

		for(int i=0; i < meList.size(); i++) {
			addSqlString += "?" + (i+1) + ",";
			paramMap.put(i+1, meList.get(i).getId());
		}

		addSqlString = addSqlString.replaceFirst(",$", ")");

		return EntityManegarUtil.executeUpadate(DELETE_MOVIE + addSqlString, em, paramMap);
	}

	@Override
	public List<MovieEntity> findByName(String name) {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	@Override
	public List<MovieCommentEntity> getMovieComment(MovieCommentEntity mce) {
		Map<Integer, Integer> paramMap = new LinkedHashMap<Integer, Integer>();
		paramMap.put(1, mce.getMid());

		return EntityManegarUtil.getSqlResultList(SELECT_MOVIE_COMMENT, MovieCommentEntity.class, em, paramMap);
	}

	@Override
	public int registComment(MovieCommentEntity mce, HttpServletRequest request) {
		Map<Integer, Object> paramMap = new LinkedHashMap<Integer, Object>();
		paramMap.put(1, mce.getMid());
		paramMap.put(2, mce.getComment());
		paramMap.put(3, request.getRemoteAddr());

		return EntityManegarUtil.executeUpadate(INSERT_COMMENT, em, paramMap);
	}

	@Override
	public int registMovieLink(MovieEntity me, HttpServletRequest request) {
		Map<Integer, Object> paramMap = new LinkedHashMap<Integer, Object>();
		paramMap.put(1, me.getName());
		paramMap.put(2, me.getUrl());
		paramMap.put(3, me.getThumbnailUrl());
		paramMap.put(4, me.getNote());
		paramMap.put(5, me.getCategoryId());

		return EntityManegarUtil.executeUpadate(INSERT_MOVIE_LINK, em, paramMap);
	}

}
