package com.template.controller;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.servlet.ModelAndView;





import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.Producer;
import com.template.util.ConstValue;


@Controller
@RequestMapping(ConstValue.DASHBOARD_CONTROLLER)
public class DashboardController {
	
	private static Logger logger = Logger.getLogger(DashboardController.class);
	
	private static DecimalFormat m_dataFormat = new DecimalFormat();	
	static
	{
		m_dataFormat.setDecimalSeparatorAlwaysShown(false);
		DecimalFormatSymbols decformat = new DecimalFormatSymbols();
		decformat.setDecimalSeparator('.');
		decformat.setGroupingSeparator(',');
		m_dataFormat.setDecimalFormatSymbols(decformat);	
		m_dataFormat.setMaximumFractionDigits(2);
	}
	
	@Autowired
	private  HttpServletRequest request;
	
	
	/*
	@Autowired
	private EntBaseService entService;
	
	@RequestMapping(value=ConstValue.DATASTAT_CONTROLLER_LOAD_STAT_CHART,method = RequestMethod.GET,produces="text/html;charset=UTF-8")
    @ResponseBody
	public String loadStatChart(String entStreet,String buildType,String fromTime,String toTime)
	{
    	JSONObject jsonObj = new JSONObject();
    	
		try
		{	
			
			String sStatCond = " status != '"+ConstValue.DATA_STATUS_DRAFT+"' and status != '"+ConstValue.DATA_STATUS_DELETE_CONFIRM+"' ";
			boolean bSingleStreet = false;
			
			Integer userType = (Integer)request.getSession().getAttribute(ConstValue.SESSION_USER_TYPE);
			//每日数据采集条数汇总
			String sTrendSql = "select count(*) cnt,date_format(first_add_time, '%m-%d') as collect_date from ent_base ";

			if(entStreet != null && entStreet.equalsIgnoreCase("") == false && entStreet.equalsIgnoreCase("null") == false)//注 这两个条件不会同时为真 
			{
				sTrendSql += " where street = '"+entStreet+"' and "+sStatCond+ " ";
				bSingleStreet = true;
			}
			else if(userType == ConstValue.USER_TYPE_WEB_STREET)
			{
				String userStreet = (String)request.getSession().getAttribute(ConstValue.SESSION_USER_STREET);
				sTrendSql += " where street = '"+userStreet+"' and "+sStatCond+ " ";
				bSingleStreet = true;
			}
			
			if(buildType != null && buildType.equalsIgnoreCase("") == false && buildType.equalsIgnoreCase("null") == false)
			{
				if(sTrendSql.indexOf("where") != -1)
				{
					sTrendSql += " and buildtype ='"+buildType+"' ";
				}
				else
				{
					sTrendSql += " where buildtype ='"+buildType+"' ";
				}
			}
			if(fromTime != null && fromTime.equalsIgnoreCase("") == false && fromTime.equalsIgnoreCase("null") == false)
			{
				if(sTrendSql.indexOf("where") != -1)
				{
					sTrendSql += " and first_add_time >='"+fromTime+"' ";
				}
				else
				{
					sTrendSql += " where first_add_time >='"+fromTime+"' ";
				}
			}
			if(toTime != null && toTime.equalsIgnoreCase("") == false && toTime.equalsIgnoreCase("null") == false)
			{
				if(sTrendSql.indexOf("where") != -1)
				{
					sTrendSql += " and first_add_time <='"+toTime+"' ";
				}
				else
				{
					sTrendSql += " where first_add_time <='"+fromTime+"' ";
				}
			}
			
			sTrendSql += " group by collect_date order by collect_date";
			
			List<HashMap> listTrend = entService.findBySql(sTrendSql);

	        JSONArray jsonArrTrend = new JSONArray();
	        
			for(int i=0;i<listTrend.size();i++)
			{
				HashMap hm = listTrend.get(i);
				
				BigInteger cnt = (BigInteger)hm.get("cnt");
				String sDate = (String)hm.get("collect_date");				
				
				JSONObject jsonTmp = new JSONObject();
				
				jsonTmp.put("collectDate", sDate);
				jsonTmp.put("cnt", cnt);
				jsonArrTrend.put(jsonTmp);
			}
			
			jsonObj.put("trendChart", jsonArrTrend);
			
			
			if(bSingleStreet == true)//只查看某一街道数据的时候, 按照街道汇总饼图
			{
				String sPieChartSql = "select count(*) cnt,parentbusitype from ent_base";
				if(entStreet != null && entStreet.equalsIgnoreCase("") == false && entStreet.equalsIgnoreCase("null") == false)//注 这两个条件不会同时为真 
				{
					sPieChartSql += " where street = '"+entStreet+"' and "+sStatCond+ " ";
				}
				else if(userType == ConstValue.USER_TYPE_WEB_STREET)
				{
					String userStreet = (String)request.getSession().getAttribute(ConstValue.SESSION_USER_STREET);
					sPieChartSql += " where street = '"+userStreet+"' and "+sStatCond+ " ";
				}
				
				if(buildType != null && buildType.equalsIgnoreCase("") == false && buildType.equalsIgnoreCase("null") == false)
				{
					if(sPieChartSql.indexOf("where") != -1)
					{
						sPieChartSql += " and buildtype ='"+buildType+"' ";
					}
					else
					{
						sPieChartSql += " where buildtype ='"+buildType+"' ";
					}
				}
				if(fromTime != null && fromTime.equalsIgnoreCase("") == false && fromTime.equalsIgnoreCase("null") == false)
				{
					if(sPieChartSql.indexOf("where") != -1)
					{
						sPieChartSql += " and first_add_time >='"+fromTime+"' ";
					}
					else
					{
						sPieChartSql += " where first_add_time >='"+fromTime+"' ";
					}
				}
				if(toTime != null && toTime.equalsIgnoreCase("") == false && toTime.equalsIgnoreCase("null") == false)
				{
					if(sPieChartSql.indexOf("where") != -1)
					{
						sPieChartSql += " and first_add_time <='"+toTime+"' ";
					}
					else
					{
						sPieChartSql += " where first_add_time <='"+toTime+"' ";
					}
				}
				
				sPieChartSql += " group by parentbusitype";
				
				List<HashMap> listPie = entService.findBySql(sPieChartSql);

		        JSONArray jsonArrPie = new JSONArray();
		        
				for(int i=0;i<listPie.size();i++)
				{
					HashMap hm = listPie.get(i);
					
					BigInteger cnt = (BigInteger)hm.get("cnt");
					String  sBusiType = (String)hm.get("parentbusitype");				
					
					JSONObject jsonTmp = new JSONObject();
					
					jsonTmp.put("name", sBusiType);
					jsonTmp.put("value", cnt);
					jsonArrPie.put(jsonTmp);
				}
				
				jsonObj.put("pieChart", jsonArrPie);
			}
			else
			{
				//按照分类生成表格
				JSONArray jsonArrMultiStreet = new JSONArray();
				String sBusiTypeSql = "select distinct parent from busi_type";
				
				List<HashMap> listBusiType = entService.findBySql(sBusiTypeSql);
				for(int i=0;i<listBusiType.size();i++)
				{
					HashMap hm = listBusiType.get(i);
					
					String sBusiType = (String)hm.get("parent");
					
					JSONObject jsonBusiType = new JSONObject();
					jsonBusiType.put("A", sBusiType);//保证在页面属性排名的时候排在第一列
					jsonArrMultiStreet.put(jsonBusiType);					
				}
				
				String sStreetSql = "select distinct street from street_community";
				if(entStreet != null && entStreet.equalsIgnoreCase("") == false && entStreet.equalsIgnoreCase("null") == false)//注 这两个条件不会同时为真 
				{
					sStreetSql += " where street = '"+entStreet+"'";
				}
				else if(userType == ConstValue.USER_TYPE_WEB_STREET)
				{
					String userStreet = (String)request.getSession().getAttribute(ConstValue.SESSION_USER_STREET);
					sStreetSql += " where street = '"+userStreet+"'";
				}
				
				List<HashMap> listStreet = entService.findBySql(sStreetSql);
				for(int i=0;i<listStreet.size();i++)
				{
					HashMap hm = listStreet.get(i);
					
					String sStreet = (String)hm.get("street");
					
					for(int j=0;j<jsonArrMultiStreet.length();j++)
					{
						JSONObject jsonBusiType = jsonArrMultiStreet.getJSONObject(j);
						jsonBusiType.put(sStreet,0);
					}
				}
				
				String sTableSql = "select count(*) cnt, parentbusitype,street from ent_base ";

				if(entStreet != null && entStreet.equalsIgnoreCase("") == false && entStreet.equalsIgnoreCase("null") == false)//注 这两个条件不会同时为真 
				{
					sTableSql += " where street = '"+entStreet+"' and "+sStatCond+ " ";
				}
				else if(userType == ConstValue.USER_TYPE_WEB_STREET)
				{
					String userStreet = (String)request.getSession().getAttribute(ConstValue.SESSION_USER_STREET);
					sTableSql += " where street = '"+userStreet+"' and "+sStatCond+ " ";
				}
				if(buildType != null && buildType.equalsIgnoreCase("") == false && buildType.equalsIgnoreCase("null") == false)
				{
					if(sTableSql.indexOf("where") != -1)
					{
						sTableSql += " and buildtype ='"+buildType+"' ";
					}
					else
					{
						sTableSql += " where buildtype ='"+buildType+"' ";
					}
				}
				if(fromTime != null && fromTime.equalsIgnoreCase("") == false && fromTime.equalsIgnoreCase("null") == false)
				{
					if(sTableSql.indexOf("where") != -1)
					{
						sTableSql += " and first_add_time >='"+fromTime+"' ";
					}
					else
					{
						sTableSql += " where first_add_time >='"+fromTime+"' ";
					}
				}
				if(toTime != null && toTime.equalsIgnoreCase("") == false && toTime.equalsIgnoreCase("null") == false)
				{
					if(sTableSql.indexOf("where") != -1)
					{
						sTableSql += " and first_add_time <='"+toTime+"' ";
					}
					else
					{
						sTableSql += " where first_add_time <='"+toTime+"' ";
					}
				}
				
				sTableSql += " group by street,parentbusitype order by street";
				
				List<HashMap> listTable = entService.findBySql(sTableSql);

				for(int i=0;i<listTable.size();i++)
				{
					HashMap hm = listTable.get(i);
					
					BigInteger cnt = (BigInteger)hm.get("cnt");
					String sBusiType = (String)hm.get("parentbusitype");	
					String sStreet = (String)hm.get("street");	
					
					for(int j=0;j<jsonArrMultiStreet.length();j++)
					{
						JSONObject jsonBusiType = jsonArrMultiStreet.getJSONObject(j);
						
						if(jsonBusiType.getString("A").equalsIgnoreCase(sBusiType))
							jsonBusiType.put(sStreet,cnt);
					}
				}
				
				jsonObj.put("multiStreet", jsonArrMultiStreet);
			}
			
			//按照分类生成表格
			JSONArray jsonArrTable = new JSONArray();
			String sStreetSql = "select distinct street from street_community";
			if(entStreet != null && entStreet.equalsIgnoreCase("") == false && entStreet.equalsIgnoreCase("null") == false)//注 这两个条件不会同时为真 
			{
				sStreetSql += " where street = '"+entStreet+"'";
			}
			else if(userType == ConstValue.USER_TYPE_WEB_STREET)
			{
				String userStreet = (String)request.getSession().getAttribute(ConstValue.SESSION_USER_STREET);
				sStreetSql += " where street = '"+userStreet+"'";
			}
			List<HashMap> listStreet = entService.findBySql(sStreetSql);
			for(int i=0;i<listStreet.size();i++)
			{
				HashMap hm = listStreet.get(i);
				
				String sStreet = (String)hm.get("street");
				
				JSONObject jsonStreet = new JSONObject();
				jsonStreet.put("A", sStreet);//保证在页面属性排名的时候排在第一列
				jsonArrTable.put(jsonStreet);
			}
			
			String sBusiTypeSql = "select distinct parent from busi_type";
			
			List<HashMap> listBusiType = entService.findBySql(sBusiTypeSql);
			for(int i=0;i<listBusiType.size();i++)
			{
				HashMap hm = listBusiType.get(i);
				
				String sBusiType = (String)hm.get("parent");
				
				for(int j=0;j<jsonArrTable.length();j++)
				{
					JSONObject jsonStreet = jsonArrTable.getJSONObject(j);
					jsonStreet.put(sBusiType,0);
				}
			}
			
			String sTableSql = "select count(*) cnt, parentbusitype,street from ent_base ";

			if(entStreet != null && entStreet.equalsIgnoreCase("") == false && entStreet.equalsIgnoreCase("null") == false)//注 这两个条件不会同时为真 
			{
				sTableSql += " where street = '"+entStreet+"' and "+sStatCond+ " ";
			}
			else if(userType == ConstValue.USER_TYPE_WEB_STREET)
			{
				String userStreet = (String)request.getSession().getAttribute(ConstValue.SESSION_USER_STREET);
				sTableSql += " where street = '"+userStreet+"' and "+sStatCond+ " ";
			}
			
			if(buildType != null && buildType.equalsIgnoreCase("") == false && buildType.equalsIgnoreCase("null") == false)
			{
				if(sTableSql.indexOf("where") != -1)
				{
					sTableSql += " and buildtype ='"+buildType+"' ";
				}
				else
				{
					sTableSql += " where buildtype ='"+buildType+"' ";
				}
			}
			if(fromTime != null && fromTime.equalsIgnoreCase("") == false && fromTime.equalsIgnoreCase("null") == false)
			{
				if(sTableSql.indexOf("where") != -1)
				{
					sTableSql += " and first_add_time >='"+fromTime+"' ";
				}
				else
				{
					sTableSql += " where first_add_time >='"+fromTime+"' ";
				}
			}
			if(toTime != null && toTime.equalsIgnoreCase("") == false && toTime.equalsIgnoreCase("null") == false)
			{
				if(sTableSql.indexOf("where") != -1)
				{
					sTableSql += " and first_add_time <='"+toTime+"' ";
				}
				else
				{
					sTableSql += " where first_add_time <='"+toTime+"' ";
				}
			}
			
			sTableSql += " group by street,parentbusitype order by street";
			
			List<HashMap> listTable = entService.findBySql(sTableSql);

			for(int i=0;i<listTable.size();i++)
			{
				HashMap hm = listTable.get(i);
				
				BigInteger cnt = (BigInteger)hm.get("cnt");
				String sBusiType = (String)hm.get("parentbusitype");	
				String sStreet = (String)hm.get("street");	
				
				for(int j=0;j<jsonArrTable.length();j++)
				{
					JSONObject jsonStreet = jsonArrTable.getJSONObject(j);
					
					if(jsonStreet.getString("A").equalsIgnoreCase(sStreet))
						jsonStreet.put(sBusiType,cnt);
				}
			}
			
			jsonObj.put("table", jsonArrTable);
			
	        jsonObj.put("singleStreet", bSingleStreet); 
	        jsonObj.put("success", true);
		}
		catch(Exception e)
		{
			logger.error(e.getMessage(),e);
			jsonObj.put("success", false);
			jsonObj.put("errMsg", e.getMessage());
		}
		finally
		{
			
		}	
        return jsonObj.toString();
    }
	*/
	
}
