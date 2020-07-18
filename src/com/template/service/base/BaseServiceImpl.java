package com.template.service.base;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
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
	
	public List findBySql(String sql, List<Object> params) {
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
	
	public int executeSql(String sql, List<Object> params) {
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
	
	/**
	 * 新增数据
	 * @param map
	 */
	public int addData(Map<String, Object> map, String tablename){
		if(map == null || map.size() == 0){
			return 0;
		}
		Map<String, Object> params = new HashMap<String, Object>();
		int index = 0;
		List<Object> values = new ArrayList<Object>();
		String sql = "insert into " + tablename + " (";
		Iterator<String> it = map.keySet().iterator();
		while(it.hasNext()){
			String key = it.next();
			Object v = map.get(key);
			if(index == 0){
				sql += key;
			}
			else {
				sql += ", " + key;
			}
			values.add(v);
			index++;
		}
		sql += ") values (";
		for(int i = 0; i < values.size(); i++){
			if(i == 0){
				sql += "?";
			}
			else {
				sql += ", ?";
			}
		}
		sql += ")";
		try {
			return baseDao.executeSql(sql, values);
		} catch(Exception e){
			e.printStackTrace();
			return 0;
		}
	}
	
	/**
	 * 修改数据
	 * @param map
	 * @param trailid
	 * @return
	 */
	public int updateData(Map<String, Object> map, Map<String, Object> keyv, String tablename){
		if(map == null || map.size() == 0){
			return 0;
		}
		Iterator<String> itt = keyv.keySet().iterator();
		String kkey = itt.next();
		Object keyval = keyv.get(kkey);
		
		int index = 0;
		List<Object> values = new ArrayList<Object>();
		String sql = "update " + tablename + " set ";
		Iterator<String> it = map.keySet().iterator();
		while(it.hasNext()){
			String key = it.next();
			Object v = map.get(key);
			if(index == 0){
				sql += (key + "=?");
			}
			else {
				sql += ", " + key + "=?";
			}
			values.add(v);
			index++;
		}
		sql += " where " + kkey + "=?";
		values.add(keyval);
		try {
			return this.baseDao.executeSql(sql, values);
		} catch(Exception e){
			return 0;
		}
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
