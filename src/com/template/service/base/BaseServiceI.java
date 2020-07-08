package com.template.service.base;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.List;
import java.util.Map;

import com.template.util.HqlFilter;


public interface BaseServiceI<T> {

	
	public Serializable save(T o);

	
	public void delete(T o);

	
	public void update(T o);

	
	public void saveOrUpdate(T o);

	
	public T getById(Serializable id);

	
	public T getByHql(String hql);

	
	public T getByHql(String hql, Map<String, Object> params);

	
	public T getByFilter(HqlFilter hqlFilter);

	/*
	public List<T> find();
	*/
	
	public List<T> find(String hql);

	
	//public List<T> find(String hql, Map<String, Object> params);

	
	public List<T> findByFilter(HqlFilter hqlFilter);
	
	public List<T> find(String hql, int page, int rows);

	/*
	public List<T> find(String hql, Map<String, Object> params, int page, int rows);

	/*
	public List<T> find(int page, int rows);

	public List<T> findByFilter(HqlFilter hqlFilter, int page, int rows);
	*/
	
	public Long count(String hql);

	//public Long count(String hql, Map<String, Object> params);

	public Long countByFilter(HqlFilter hqlFilter);

	public Long count();

	/*public int executeHql(String hql);

	public int executeHql(String hql, Map<String, Object> params);
	*/
	public List findBySql(String sql);

	public List findBySql(String sql, int page, int rows);

	public List findBySql(String sql, Map<String, Object> params);

	public List findBySql(String sql, Map<String, Object> params, int page, int rows);

	public int executeSql(String sql);

	public int executeSql(String sql, Map<String, Object> params);

	public BigInteger countBySql(String sql);

	public BigInteger countBySql(String sql, Map<String, Object> params);

	/**
	 *
	 * @param hqlFilter
	 *            参数
	 * @return
	 
	public List<T> updateSEQ(HqlFilter hqlFilter);
	*/
}
