package com.example.demo.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dao.MovieDao;
import com.example.demo.entity.CategoryEntity;
import com.example.demo.entity.MovieCommentEntity;
import com.example.demo.entity.MovieEntity;


@Service
@Transactional
public class MovieService {

	@Autowired
	private MovieDao movieDao;

	public List<MovieEntity> getMovieInfo() {

		return movieDao.getMovieInfo();
	}

	public List<CategoryEntity> getMovieCategory() {

		return movieDao.getMovieCategory();
	}

	public List<MovieEntity> getMovieByName(String name) {

		return movieDao.findByName(name);
	}

	public List<MovieCommentEntity> getMovieComment(MovieCommentEntity mce) {

		return movieDao.getMovieComment(mce);
	}

	public int deleteMovie(List<MovieEntity> meList) {

		return movieDao.deleteMovie(meList);
	}

	public int registComment(MovieCommentEntity mce, HttpServletRequest request) {

		return movieDao.registComment(mce, request);
	}

	public int registMovieLink(MovieEntity me, HttpServletRequest request) {

		boolean isYoutubeUrl = me.getUrl().matches("^https?:\\/\\/www.youtube.com\\/watch\\?v=.*");

		String embeddedUrl = "https://www.youtube.com/embed/";
		String thumbnailUrl = "https://i.ytimg.com/vi/";
		String jpgScal = "/hqdefault.jpg";

		if(isYoutubeUrl) {
			int vIndex = me.getUrl().indexOf("v=");
			int aIndex = me.getUrl().indexOf("&");

			String mValue = aIndex == -1 ? me.getUrl().substring(vIndex + 2, me.getUrl().length())
					: me.getUrl().substring(vIndex + 2, aIndex);

			me.setUrl(embeddedUrl + mValue);
			me.setThumbnailUrl(thumbnailUrl + mValue + jpgScal);

		} else {
			return 0;
		}

		return movieDao.registMovieLink(me, request);
	}

	public int updateMovie(MovieEntity me, HttpServletRequest request) {

		return movieDao.updateMovie(me, request);
	}
}
