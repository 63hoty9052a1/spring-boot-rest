package com.example.demo.dao.impl;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.demo.dao.UserDao;
import com.example.demo.entity.UserEntity;
import com.example.demo.repository.UserRepository;
import com.example.demo.util.EntityManegarUtil;

@Repository
public class UserDaoImpl implements UserDao{

	@Autowired
	private UserRepository userRepository;

	@PersistenceContext
	private EntityManager em;

	private static final String  SELECT_USER_ALL = "SELECT * FROM m_user";

	private static final String  SELECT_USER_BY_NAME = "SELECT * FROM m_user t1 LEFT JOIN user_address t2 ON t1.user_id = t2.user_id WHERE t1.user_name = ?1";

	private static final String SELECT_USER_INFO =
			"SELECT " +
			"  t1.user_id " +
			" ,t1.pass_wd " +
			" ,t1.user_name " +
			" ,t1.delete_flag " +
			" ,t2.address " +
			" ,t2.zip_code " +
			"FROM  " +
			"	m_user t1 " +
			"	LEFT JOIN user_address t2 ON t1.user_id = t2.user_id " +
			"WHERE " +
			"	t1.delete_flag = 0 ";

	private static final String  DELETE_USER =
			"UPDATE m_user " +
			"  SET delete_flag = 1 " +
			"WHERE " +
			"	user_id IN ( ";


	/**
	 * プロパティファイルに記載したSQLを実行する例
	 * 以下の点で実用性が低いので使うことはたぶんない。
	 * ・プロパティファイルに記載したSQLは整形が出来ないので可読性が悪い。
	 * ・動的にSQLを組み立てる必要がある場合に使えそうにない。
	 *
	 */
	public List<UserEntity> getUserInfo(String userId) {
		return userRepository.findById(userId);
	}

	@Override
	public List<UserEntity> getUserInfo() {

		return EntityManegarUtil.getSqlResultList(SELECT_USER_INFO, UserEntity.class, em);
	}

	@Override
	public List<UserEntity> findByName(String name) {
		Map<Integer, String> paramMap = new LinkedHashMap<Integer, String>();
		paramMap.put(1, name);

		return EntityManegarUtil.getSqlResultList(SELECT_USER_BY_NAME, UserEntity.class, em, paramMap);
	}

	@Override
	public int deleteUser(List<UserEntity> ueList) {
		Map<Integer, String> paramMap = new LinkedHashMap<Integer, String>();
		String addSqlString = "";

		for(int i=0; i < ueList.size(); i++) {
			addSqlString += "?" + (i+1) + ",";
			paramMap.put(i+1, ueList.get(i).getUserId());
		}

		addSqlString = addSqlString.replaceFirst(",$", ")");

		return EntityManegarUtil.executeUpadate(DELETE_USER + addSqlString, em, paramMap);
	}

}
