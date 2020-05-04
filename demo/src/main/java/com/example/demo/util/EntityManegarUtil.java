package com.example.demo.util;

import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.Query;

public class EntityManegarUtil {

	@SuppressWarnings("unchecked")
	public static <T> List<T> getSqlResultList(String sqlString, Class<T> entity, EntityManager em) {

		Query q = em.createNativeQuery(sqlString, entity);

		return q.getResultList();
	}


	public static <T> Query getSqlQueryIns(String sqlString, Class<T> entity, EntityManager em) {

		Query q = em.createNativeQuery(sqlString, entity);

		return q;
	}

	@SuppressWarnings("unchecked")
	public static <T> List<T> getSqlResultList(String sqlString, Class<T> entity, EntityManager em, Map<Integer, ?> paramMap) {

		Query q = em.createNativeQuery(sqlString, entity);

		paramMap.forEach((position, value) -> {
			q.setParameter(position, value);
		});

		return q.getResultList();
	}

	public static int executeUpadate(String sqlString, EntityManager em, Map<Integer, ?> paramMap) {

		Query q = em.createNativeQuery(sqlString);

		paramMap.forEach((position, value) -> {
			q.setParameter(position, value);
		});

		return q.executeUpdate();
	}
}
