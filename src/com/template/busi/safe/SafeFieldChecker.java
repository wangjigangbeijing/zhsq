package com.template.busi.safe;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.template.model.jcsqsj.Resident;
import com.template.service.base.BaseServiceImpl;

import net.sf.json.JSONArray;

public class SafeFieldChecker {

	private BaseServiceImpl service;
	
	/**
	 * 获取模型对应的表名
	 * @param o
	 * @return
	 */
	private String getTableName(Object o) {
		Annotation[] as = o.getClass().getAnnotations();
		if(as == null || as.length == 0) {
			System.out.println("没有找到备注");
			return null;
		}
		
		try {
			for(int i = 0; i < as.length; i++) {
				System.out.println(as[i].annotationType());
				System.out.println(as[i].annotationType().getName());
				if("javax.persistence.Table".equals(as[i].annotationType().getName())) {
					String s = as[i].toString();
					int dot = s.indexOf("(");
					s = s.substring(dot + 1);
					String[] subs = s.split(",");
					dot = subs[0].indexOf("=");
					return subs[0].substring(dot + 1).trim().toLowerCase();
				}
			}
		} catch(Exception e) {
			
		}
		return null;
	}
	
	private List getSafeFields(String tablename){
		String sql = "select * from sys_table_attribute where table_id=? and encryption='是'";
		List<Object> vals = new ArrayList<Object>();
		vals.add(tablename);
		return this.service.findBySql(sql, vals);
	}
	
	private boolean checkSafeField(String tablename, String fieldname){
		String sql = "select * from sys_table_attribute where table_id=? and attribute_enname=? and encryption='是'";
		List<Object> vals = new ArrayList<Object>();
		vals.add(tablename);
		vals.add(fieldname);
		List list = this.service.findBySql(sql, vals);
		if(list != null || list.size() > 0) {
			return true;
		}
		return false;
	}
	
	/**
	 * 对单个字段进行校验
	 * @param tablename
	 * @param fieldname
	 * @param content
	 * @return
	 */
	public String checkField(BaseServiceImpl service, String tablename, String fieldname, String content) {
		this.service = service;
		boolean check = checkSafeField(tablename, fieldname);
		if(!check) {
			return content;
		}
		return AES.encrypt(content);
	}
	
	/**
	 * 对数据模型进行校验
	 * @param service
	 * @param o
	 */
	public void checkModel(BaseServiceImpl service, String tablename, Object o) {
		this.service = service;
		Field[] fields = o.getClass().getDeclaredFields();
		if(fields == null || fields.length == 0) {
			System.out.println("没有找到对象字段");
			return;
		}
		
//		String tablename = getTableName(o);
//		if(tablename == null) {
//			System.out.println("没有获取到实体对象表名");
//			return;
//		}
		
		List fieldlist = getSafeFields(tablename);
		if(fieldlist == null || fieldlist.size() == 0) {
			System.out.println("该实体不需要加密处理");
			return;
		}
		
		List<String> fieldnames = new ArrayList<String>();
		for(int i = 0; i < fieldlist.size(); i++) {
			fieldnames.add((String) ((Map<String, Object>)fieldlist.get(i)).get("ATTRIBUTE_ENNAME"));
		}
		
		System.out.println("TableName:" + tablename + ", fieldnames size: " + fieldnames.size());
		for(int i = 0; i < fields.length; i++) {
			String fieldname = fields[i].getName();
			if("id".equals(fieldname)) {
				continue;
			}
			if(fieldnames.contains(fieldname)) {
				//需要进行加密
				//先获取值进行加密，再重新赋值
				String setmethod = "set" + fieldname;
				String getmethod = "get" + fieldname;
				
				try {
					Method m1 = o.getClass().getMethod(getmethod);
					Method m2 = o.getClass().getMethod(setmethod, String.class);
					String content = (String) m1.invoke(o, null);
					String s = AES.encrypt(content);
					m2.invoke(o, s);			
					
				} catch(Exception e) {
					e.printStackTrace();
				}
			}
		}
		
		
	}
	
	public static void main(String[] args) {
		SafeFieldChecker checker = new SafeFieldChecker();
		Resident resident = new Resident();
		resident.setname("张三");
		checker.checkModel(null, "", resident);
		System.out.println(resident.getname());
		System.out.println(AES.decrypt(resident.getname()));
	}
}
