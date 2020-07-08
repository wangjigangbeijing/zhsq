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
	
	/*public String getOffset()
	{
		if(curPage != -1 && pageSize != -1)
			//return " LIMIT "+pageSize+" OFFSET "+(curPage*pageSize);		
			return " LIMIT "+pageSize+","+(curPage*pageSize);
		
		return "";
	}*/
	
	
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
		
		if(sCond.endsWith(" AND "))
			sCond = sCond.substring(0,sCond.length() - 5);
		
		/*if(sort != null && sort.equalsIgnoreCase("") == false)
			sCond += "ORDER BY "+sort+" "+order;
		/*if(curPage != -1 && pageSize != -1)
			sCond = sCond + " LIMIT "+pageSize+" OFFSET "+(curPage*pageSize);*/		
		
		return sCond;
	}
	
	public String getOrder()
	{
		String sOrder = "";
		if(sort != null && sort.equalsIgnoreCase("") == false)
			sOrder += " ORDER BY "+sort+" "+order;
		/*if(curPage != -1 && pageSize != -1)
			sCond = sCond + " LIMIT "+pageSize+" OFFSET "+(curPage*pageSize);*/		
		
		return sOrder;
	}
	
	/**
	 * 带参构造
	 * 
	 * @param request
	 
	public HqlFilter(HttpServletRequest request) {
		this.request = request;
		addFilter(request);
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

	/**
	 * 转换SQL操作符
	 * 
	 * @param operator
	 * @return
	 
	private String getSqlOperator(String operator) {
		String o = operator.trim().toUpperCase();
		if (o.equals("EQ")) {
			return " = ";
		}
		if (o.equals("NE")) {
			return " != ";
		}
		if (o.equals("LT")) {
			return " < ";
		}
		if (o.equals("GT")) {
			return " > ";
		}
		if (o.equals("LE")) {
			return " <= ";
		}
		if (o.equals("GE")) {
			return " >= ";
		}
		if (o.equals("LK") || o.equals("RLK") || o.equals("LLK")) {
			return " like ";
		}
		return "";
	}

	/**
	 * 获得添加过滤字段后的HQL
	 * 
	 * @return
	 
	public String getConditionHql() {
		return hql.toString();
	}
	*/
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
	 * 获得添加过滤字段后加上排序字段的HQL
	 * 
	 * @return
	 
	public String getWhereAndOrderHql() {
		if (sort != null && order != null) {
			if (sort.indexOf(".") < 1) {
				sort = "t." + sort;
			}
			hql.append(" order by " + sort + " " + order + " ");// 添加排序信息
		} else {
			if (request != null) {
				String s = request.getParameter("sort");
				String o = request.getParameter("order");
				if (s != null) {
					sort = s;
				}
				if (o != null) {
					order = o;
				}
				if (sort != null && order != null) {
					if (sort.indexOf(".") < 1) {
						sort = "t." + sort;
					}
					hql.append(" order by " + sort + " " + order + " ");// 添加排序信息
				}
			}
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
	 * 添加过滤
	 * 
	 * @param request
	 
	public void addFilter(HttpServletRequest request) {
		Enumeration<String> names = request.getParameterNames();
		while (names.hasMoreElements()) {
			String name = names.nextElement();
			String value = request.getParameter(name);
			addFilter(name, value);
		}
	}

	/**
	 * 添加过滤
	 * 
	 * 举例，name传递：QUERY_t#id_S_EQ
	 * 
	 * 举例，value传递：0
	 * 
	 * @param params
	 
	public void addFilter(String name, String value) {
		if (name != null && value != null) {
			if (name.startsWith("QUERY_")) {// 如果有需要过滤的字段
				String columnName = name.substring(name.indexOf("_") + 1, name.indexOf("_", 6)).replaceAll("#", ".");// 字段名
				String valueType = name.substring(name.indexOf("_", 6) + 1, name.lastIndexOf("_"));// 字段类型
				String operator = name.substring(name.lastIndexOf("_") + 1);// 操作符
				String placeholder = UUID.randomUUID().toString().replace("-", "");// 生成一个随机的参数名称

				if (hql.toString().indexOf(" where 1=1") < 0) {
					hql.append("  where 1=1 ");
				}
				if(operator.equals("IN")){
					hql.append(" and " + columnName + " in  ("+value+")");// 拼HQL
				}
				else{
					hql.append(" and " + columnName + " " + getSqlOperator(operator) + " :c" + placeholder + " ");// 拼HQL
					params.put("c" + placeholder, getObjValue(valueType, operator, value));// 添加参数
				}
			
			}
		}
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
	
}


