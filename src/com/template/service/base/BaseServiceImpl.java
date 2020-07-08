package com.template.service.base;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.math.BigInteger;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.template.dao.BaseDao;
import com.template.util.HqlFilter;

/**
 * 
 * 
 * @author
 * 
 * @param <T>
 */
@Transactional
@Service
public class BaseServiceImpl<T> implements BaseServiceI<T> {

	private static Logger logger = Logger.getLogger(BaseServiceImpl.class);
	@Autowired
	private BaseDao<T> baseDao;

	@Override
	public Serializable save(T o) {
		return baseDao.save(o);
	}

	@Override
	public void delete(T o) {
		baseDao.delete(o);
	}

	@Override
	public void update(T o) {
		baseDao.update(o);
	}

	@Override
	public void saveOrUpdate(T o) {
		baseDao.saveOrUpdate(o);
	}

	@Override
	public T getById(Serializable id) {
		Class<T> c = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
		return baseDao.getById(c, id);
	}

	@Override
	public T getByHql(String hql) {
		return baseDao.getByHql(hql);
	}

	@Override
	public T getByHql(String hql, Map<String, Object> params) {
		return baseDao.getByHql(hql, params);
	}
	
	@Transactional
	@Override
	public T getByFilter(HqlFilter hqlFilter) {
		String className = ((Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0]).getName();
		String hql = "select distinct t from " + className + " t";
		
		String sCond = hqlFilter.getCondition(className);
		
		if(sCond != null && sCond.equalsIgnoreCase("") == false)
		{
			hql += " where "+sCond;
		}
		
		String sOrder = hqlFilter.getOrder();
		if(sOrder != null && sOrder.equalsIgnoreCase("") == false)
			hql += sOrder;
		
		return baseDao.getByHql(hql);
	}
	
	/*
	@Override
	public List<T> find() {
		return findByFilter(new HqlFilter());
	}*/
	
	@Override
	public List<T> find(String hql) {
		return baseDao.find(hql);
	}
	
	/*
	@Override
	public List<T> find(String hql, Map<String, Object> params) {
		return baseDao.find(hql, params);
	}
	
	*/
	@Override
	public List<T> findByFilter(HqlFilter hqlFilter) {
		String className = ((Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0]).getName();
		String hql = "select distinct t from " + className + " t";
		
		String sCond = hqlFilter.getCondition(className);
		
		if(sCond != null && sCond.equalsIgnoreCase("") == false)
		{
			hql += " where "+sCond;
		}
		
		String sOrder = hqlFilter.getOrder();
		if(sOrder != null && sOrder.equalsIgnoreCase("") == false)
			hql += sOrder;
		
		if(hqlFilter.getCurPage() != -1 && hqlFilter.getPageSize() != -1)
		{
			return find(hql,hqlFilter.getCurPage(),hqlFilter.getPageSize());
		}
		
		return find(hql);
	}
	
	
	@Override
	public List<T> find(String hql, int page, int rows) {
		return baseDao.find(hql, page, rows);
	}
	/*
	@Override
	public List<T> find(String hql, Map<String, Object> params, int page, int rows) {
		return baseDao.find(hql, params, page, rows);
	}
	/*
	@Override
	public List<T> find(int page, int rows) {
		return findByFilter(new HqlFilter(), page, rows);
	}
	
	
	@Override
	public List<T> findByFilter(HqlFilter hqlFilter, int page, int rows) {
		String className = ((Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0]).getName();
		String hql = "select distinct t from " + className + " t";
		return find(hql + hqlFilter.getWhereAndOrderHql(), hqlFilter.getParams(), page, rows);
	}
	*/

	@Override
	public Long count(String hql) {
		return baseDao.count(hql);
	}

	/*
	@Override
	public Long count(String hql, Map<String, Object> params) {
		return baseDao.count(hql, params);
	}*/

	@Override
	public Long countByFilter(HqlFilter hqlFilter) {
		String className = ((Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0]).getName();
		String hql = "select count(*) from " + className + "";
		
		String sCond = hqlFilter.getCondition(className);
		
		if(sCond != null && sCond.equalsIgnoreCase("") == false)
		{
			hql += " where "+sCond;
		}
		
		return count(hql);
	}

	@Override
	public Long count() {
		return countByFilter(new HqlFilter());
	}

	/*@Override
	public int executeHql(String hql) {
		return baseDao.executeHql(hql);
	}

	@Override
	public int executeHql(String hql, Map<String, Object> params) {
		return baseDao.executeHql(hql, params);
	}
	*/
	@Override
	public List findBySql(String sql) {
		return baseDao.findBySql(sql);
	}

	@Override
	public List findBySql(String sql, int page, int rows) {
		return baseDao.findBySql(sql, page, rows);
	}

	@Override
	public List findBySql(String sql, Map<String, Object> params) {
		return baseDao.findBySql(sql, params);
	}

	@Override
	public List findBySql(String sql, Map<String, Object> params, int page, int rows) {
		return baseDao.findBySql(sql, params, page, rows);
	}

	@Override
	public int executeSql(String sql){
		
		baseDao.executeSql(sql);
		/*
		try
		{
			baseDao.executeSql(sql);
			baseDao.getCurrentSession().getTransaction().commit();
			baseDao.getCurrentSession().getTransaction().begin();
		}
		catch(Exception e)
		{
			logger.error(e.getMessage(),e);
			return -1;
		}*/
		return 0;
	}

	@Override
	public int executeSql(String sql, Map<String, Object> params) {
		return baseDao.executeSql(sql, params);
	}

	@Override
	public BigInteger countBySql(String sql) {
		return baseDao.countBySql(sql);
	}

	@Override
	public BigInteger countBySql(String sql, Map<String, Object> params) {
		return baseDao.countBySql(sql, params);
	}
	/*
	@Override
	public List<T> updateSEQ(HqlFilter hqlFilter) {
		String className = ((Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0]).getName();
		String hql = "select distinct t   from " + className + " t";
		return baseDao.findSeq(hql + hqlFilter.getWhereAndOrderHql(), hqlFilter.getParams());
	}
	

	@Override
	public List<T> find() {
		// TODO Auto-generated method stub
		return null;
	}
	 
	@Override
	public List<T> find(int page, int rows) {
		// TODO Auto-generated method stub
		return null;
	}*/
}
