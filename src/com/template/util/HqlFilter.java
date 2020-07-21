package com.template.util;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.Date;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;


public class HqlFilter {

	public enum Operator {
        EQ,
        NEQ,
        LIKE,
        GE,
        LE,
        IN
    }
	
	//private HttpServletRequest request;// 为了获取request里面传过来的动态参数
	private Map<String, Object> params = new HashMap<String, Object>();// 条件参数
	private StringBuffer hql = new StringBuffer();
	private String sort;// 排序字段
	private String order = "asc";// asc/desc
	
	private ArrayList<QueryCondition> alQryCond = new ArrayList<QueryCondition>();
	
	private ArrayList<ORCondition> alORCond = new ArrayList<ORCondition>(); 
	
	public void addQryCond(String fieldName,Operator operator,Object val)
	{
		String sOperator = "";
		if(operator == Operator.EQ)
		{
			sOperator = " = ";
		}
		if(operator == Operator.NEQ)
		{
			sOperator = " != ";
		}
		if(operator == Operator.LIKE)
		{
			sOperator = " LIKE ";
		}
		if(operator == Operator.GE)
		{
			sOperator = " >= ";
		}
		if(operator == Operator.LE)
		{
			sOperator = " <= ";
		}
		if(operator == Operator.IN)
		{
			sOperator = " IN ";
		}
		
		QueryCondition qc = new QueryCondition(fieldName,sOperator,val);
		alQryCond.add(qc);
	}
	
	public void addOrCondGroup(String fieldName,Operator operator,ArrayList<String> val)
	{
		String sOperator = "";
		if(operator == Operator.EQ)
		{
			sOperator = " = ";
		}
		if(operator == Operator.NEQ)
		{
			sOperator = " != ";
		}
		if(operator == Operator.LIKE)
		{
			sOperator = " LIKE ";
		}
		if(operator == Operator.GE)
		{
			sOperator = " >= ";
		}
		if(operator == Operator.LE)
		{
			sOperator = " <= ";
		}
		if(operator == Operator.IN)
		{
			sOperator = " IN ";
		}
		
		ORCondition qc = new ORCondition(fieldName,sOperator,val);
		alORCond.add(qc);
	}
	
	/**
	 * 默认构造
	 */
	public HqlFilter() {

	}
	
	private int curPage = -1;
	private int pageSize = -1;
	
	public int getCurPage()
	{
		return this.curPage;
	}
	
	
	public int getPageSize()
	{
		return this.pageSize;
	}
	
	public HqlFilter(int curPage,int pageSize) {
		this.curPage = curPage;
		this.pageSize = pageSize;
	}
	
	public String getCondition(String className)
	{
		String sCond = "";
		
		try {
			Class<?> cls=Class.forName(className);
			
			for(int i=0;i<alQryCond.size();i++)
			{
				QueryCondition qc = alQryCond.get(i);
				
				String sFieldName = qc.fieldName;
				String sOperator = qc.operator;
				Object val = qc.val;
				
				Field field = cls.getDeclaredField(sFieldName);
				
				Class<?> fieldType = field.getType();
				
				if(fieldType == String.class && sOperator.trim().equalsIgnoreCase("IN") == false)
				{
					sCond += sFieldName+" "+sOperator+" '"+val+"' AND ";
				}
				else if(fieldType == String.class && sOperator.trim().equalsIgnoreCase("IN"))
				{
					String sVal = (String)val;
					String [] valArr = sVal.split(",");
					
					sVal = "";
					
					for(int j=0;j<valArr.length;j++)
					{
						sVal += "'"+valArr[j]+"',";
					}
					
					if(sVal.endsWith(","))
						sVal = sVal.substring(0,sVal.length() - 1);
					
					sCond += sFieldName+" "+sOperator+" ("+sVal+") AND ";
				}
				else if(fieldType == Integer.class || fieldType == Double.class || fieldType == Long.class)
				{
					sCond += sFieldName+" "+sOperator+" "+val+" AND ";
				}
				else if(fieldType == Date.class)
				{
					//sCond += sFieldName+" "+sOperator+" to_date('"+val+"','yyyy-mm-dd hh24:mi:ss') AND ";
					sCond += sFieldName+" "+sOperator+" '"+val+"' AND ";
				}
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		}
		
		String sORConds = getORCondition(className);
		
		if(sORConds != null && sORConds.equalsIgnoreCase("") == false)
			sCond += sORConds;
		
		if(sCond.endsWith(" AND "))
			sCond = sCond.substring(0,sCond.length() - 5);
		
		return sCond;
	}
	
	public String getORCondition(String className)
	{
		String sCond = "";
		
		try {
			
			for(int i=0;i<alORCond.size();i++)
			{
				ORCondition qc = alORCond.get(i);
				
				String sFieldName = qc.fieldName;
				String sOperator = qc.operator;
				
				String sORCond = "";
				for(int j=0;j<qc.val.size();j++)
				{
					String sExp = getExprByFiled(className,sFieldName,sOperator,qc.val.get(j));
					
					sORCond += sExp + " OR ";
				}
				
				if(sORCond.endsWith(" OR "))
					sORCond = sORCond.substring(0,sORCond.length() - 4);
				
				sCond += "  ( "+sORCond + " ) AND ";
				
			}
		} catch (SecurityException e) {
			e.printStackTrace();
		}
		
		if(sCond.endsWith(" AND "))
			sCond = sCond.substring(0,sCond.length() - 5);
		
		return sCond;
	}
	
	public String getExprByFiled(String className,String sFieldName,String sOperator,Object val)
	{
		String sExp = "";
		
		try {
			Class<?> cls=Class.forName(className);
			
			Field field = cls.getDeclaredField(sFieldName);
			
			Class<?> fieldType = field.getType();
			
			if(fieldType == String.class && sOperator.trim().equalsIgnoreCase("IN") == false)
			{
				sExp = sFieldName+" "+sOperator+" '"+val+"' ";
			}
			else if(fieldType == String.class && sOperator.trim().equalsIgnoreCase("IN"))
			{
				String sVal = (String)val;
				String [] valArr = sVal.split(",");
				
				sVal = "";
				
				for(int j=0;j<valArr.length;j++)
				{
					sVal += "'"+valArr[j]+"',";
				}
				
				if(sVal.endsWith(","))
					sVal = sVal.substring(0,sVal.length() - 1);
				
				sExp = sFieldName+" "+sOperator+" ("+sVal+") ";
			}
			else if(fieldType == Integer.class || fieldType == Double.class || fieldType == Long.class)
			{
				sExp = sFieldName+" "+sOperator+" "+val+" ";
			}
			else if(fieldType == Date.class)
			{
				//sCond += sFieldName+" "+sOperator+" to_date('"+val+"','yyyy-mm-dd hh24:mi:ss') AND ";
				sExp = sFieldName+" "+sOperator+" '"+val+"' ";
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		}
		
		return sExp;
	}
	
	
	
	
	public String getOrder()
	{
		String sOrder = "";
		if(sort != null && sort.equalsIgnoreCase("") == false)
			sOrder += " ORDER BY "+sort+" "+order;
		return sOrder;
	}
	
	/**
	 * 添加排序字段
	 * 
	 * @param sort
	 */
	public void setSort(String sort) {
		this.sort = sort;
	}

	/**
	 * 添加排序方法，默认asc升序
	 * 
	 * @param order
	 */
	public void setOrder(String order) {
		this.order = order;
	}

	public String getGroupHql(String group) {
	    if (group != null) {
            if (group.indexOf(".") < 1) {
                group = "t." + group;
            }
            hql.append(" group by " + group + " ");// 添加分组统计信息
        }
	    
	    return hql.toString();
	}
	
	/**
	 * 获得过滤字段参数和值
	 * 
	 * @return
	 */
	public Map<String, Object> getParams() {
		return params;
	}

	/**
	 * 将String值转换成Object
	 * 
	 * S:String L:Long I:Integer D:Date ST:Short BD:BigDecimal FT:Float
	 * 
	 * @param valueType
	 * @param operator
	 * @param value
	 * @return
	 */
	private Object getObjValue(String valueType, String operator, String value) {
		if (valueType.equals("S")) {
			if (operator.equals("LK")) {
				value = "%%" + value + "%%";
			} else if (operator.equals("RLK")) {
				value = value + "%%";
			} else if (operator.equals("LLK")) {
				value = "%%" + value;
			}
			return value;
		}
		if (valueType.equals("L")) {
			return Long.parseLong(value);
		}
		if (valueType.equals("I")) {
			return Integer.parseInt(value);
		}
		if (valueType.equals("D")) {
			/*try {
				//return DateUtils.parseDate(value, new String[] { "yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm" });
			} catch (ParseException e) {
				e.printStackTrace();
			}*/
		}
		if (valueType.equals("ST")) {
			return Short.parseShort(value);
		}
		if (valueType.equals("BD")) {
			return BigDecimal.valueOf(Long.parseLong(value));
		}
		if (valueType.equals("FT")) {
			return Float.parseFloat(value);
		}
		return null;
	}

	class QueryCondition
	{
		public QueryCondition(String fieldName,String operator,Object val)
		{
			this.fieldName = fieldName;
			this.operator = operator;
			this.val = val;
		}
		public String fieldName = "";
		public String operator = "";
		public Object val = null;
	}
	
	class ORCondition
	{
		public ORCondition(String fieldName,String operator,ArrayList<String> val)
		{
			this.fieldName = fieldName;
			this.operator = operator;
			this.val = (ArrayList<String>)val.clone();
		}
		public String fieldName = "";
		public String operator = "";
		public ArrayList<String> val = null;
	}
}


