package com.template.controller;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.mysql.cj.util.StringUtils;
import com.template.busi.controller.flow.FlowTemplateController;
import com.template.service.SysUserService;
import com.template.util.ConstValue;

@Controller
@RequestMapping("homeController")
public class HomeController {

private static Logger logger = Logger.getLogger(FlowTemplateController.class);
	
	@Autowired
	private SysUserService userService;
	
	@Autowired
	private  HttpServletRequest request;
	
	/**
	 * 获取用户社区
	 * @return
	 */
	private String getOrganization() {
		String userid = (String) request.getSession().getAttribute(ConstValue.SESSION_USER_ID);
		
		String sql = "select a.organization from sys_user_organization a where a.user=?";
		List<Object> params = new ArrayList<Object>();
		params.add(userid);
		List<HashMap> list = this.userService.findBySql(sql, params);
		if(list == null || list.size() == 0) {
			return null;
		}
		else {
			return (String) list.get(0).get("organization");
		}
	}
	
	/**
	 * 获取用户社区名称
	 * @return
	 */
	private String getOrganizationName() {
		String userid = (String) request.getSession().getAttribute(ConstValue.SESSION_USER_ID);
		
		String sql = "select b.name from sys_user_organization a, sys_organization b where a.organization=b.id and a.user=?";
		List<Object> params = new ArrayList<Object>();
		params.add(userid);
		List<HashMap> list = this.userService.findBySql(sql, params);
		if(list == null || list.size() == 0) {
			return null;
		}
		else {
			return (String) list.get(0).get("name");
		}
	}
	
	@RequestMapping(value="getboundary",method = {RequestMethod.POST,RequestMethod.GET},produces="text/html;charset=UTF-8")
	@ResponseBody
	public String getBoundary() {
		JSONObject jsonObj = new JSONObject();
		
		
		try
		{
			String userid = (String) request.getSession().getAttribute(ConstValue.SESSION_USER_ID);
			
			String sql = "select b.boundry from sys_user_organization a, sys_organization b where a.organization=b.id and a.user=?";
			List<Object> params = new ArrayList<Object>();
			params.add(userid);
			List<HashMap> list = this.userService.findBySql(sql, params);
			if(list == null || list.size() == 0) {
				jsonObj.put("success", false);
			}
			else {
				jsonObj.put("boundary", list.get(0).get("boundry"));
				jsonObj.put("success", true);
			}
			
		}
		catch(Exception e)
		{
			logger.error(e.getMessage(),e);
			jsonObj.put("success", false);
		}
	    return jsonObj.toString();
	}
	
	@RequestMapping(value="getsqname",method = {RequestMethod.POST,RequestMethod.GET},produces="text/html;charset=UTF-8")
	@ResponseBody
	public String getSqname()
	{		
		String name = getOrganizationName();
		JSONObject jsonObj = new JSONObject();
		try
		{
			jsonObj.put("data", name);
			String userid = (String) request.getSession().getAttribute(ConstValue.SESSION_USER_ID);
			List<Object> params = new ArrayList<Object>();
			params.add(userid);
			String sql = "select * from sys_user where id=?";
			List<HashMap> list = this.userService.findBySql(sql, params);
			if(list != null && list.size() > 0) {
				jsonObj.put("username", list.get(0).get("name"));
			}
			jsonObj.put("success", true);
		}
		catch(Exception e)
		{
			logger.error(e.getMessage(),e);
			jsonObj.put("success", false);
		}
	    return jsonObj.toString();
	}
	
	@RequestMapping(value="getgpyip",method = {RequestMethod.POST,RequestMethod.GET},produces="text/html;charset=UTF-8")
	@ResponseBody
	public String getGpyip()
	{		
		JSONObject jsonObj = new JSONObject();
		try
		{
			String userid = (String) request.getSession().getAttribute(ConstValue.SESSION_USER_ID);
			
			String sql = "select b.gpy_ip from sys_user_organization a, sys_organization b where a.organization=b.id and a.user=?";
			List<Object> params = new ArrayList<Object>();
			params.add(userid);
			List<HashMap> list = this.userService.findBySql(sql, params);
			if(list != null && list.size() > 0) {
				jsonObj.put("ip", list.get(0).get("gpy_ip"));
			}
			jsonObj.put("success", true);
		}
		catch(Exception e)
		{
			logger.error(e.getMessage(),e);
			jsonObj.put("success", false);
		}
	    return jsonObj.toString();
	}
	
	@RequestMapping(value="loadbaseinfo1",method = {RequestMethod.POST,RequestMethod.GET},produces="text/html;charset=UTF-8")
	@ResponseBody
	public String loadBaseInfo1()
	{
		String owner = getOrganization();
		logger.debug("loadBaseInfo owner:" + owner);
		JSONObject jsonObj = new JSONObject();
		try
		{
			List<Integer> numlist = new ArrayList<Integer>();
			
			//1
			String sql = "select count(*) as num from jc_community t where t.OWNER like ?";
			List<Object> params = new ArrayList<Object>();
			params.add("%" + owner + "%");
			List<HashMap> list = this.userService.findBySql(sql, params);
			if(list != null && list.size() > 0)
			{
				numlist.add(((BigInteger)list.get(0).get("num")).intValue());
			}
			else
			{
				numlist.add(0);
			}
			
			//2
			sql = "select count(*) as num from jc_residebuilding t where t.buildtype = '楼房' and t.OWNER = ?";
			params.clear();
			params.add(owner);
			list = this.userService.findBySql(sql, params);
			if(list != null && list.size() > 0)
			{
				numlist.add(((BigInteger)list.get(0).get("num")).intValue());
			}
			else
			{
				numlist.add(0);
			}
			
			//3
			sql = "select count(*) as num from jc_room t1 where t1.ofresidebuilding in (select t2.id from jc_residebuilding t2 where t2.buildtype = '楼房' and t2.OWNER = ?)";
			params.clear();
			params.add(owner);
			list = this.userService.findBySql(sql, params);
			if(list != null && list.size() > 0)
			{
				numlist.add(((BigInteger)list.get(0).get("num")).intValue());
			}
			else
			{
				numlist.add(0);
			}
			
			//4
			sql = "select count(*) as num from jc_residebuilding t where t.buildtype = '平房' and t.OWNER = ?";
			params.clear();
			params.add(owner);
			list = this.userService.findBySql(sql, params);
			if(list != null && list.size() > 0)
			{
				numlist.add(((BigInteger)list.get(0).get("num")).intValue());
			}
			else
			{
				numlist.add(0);
			}
			
			//5
			sql = "select count(*) as num from jc_room t1 where t1.ofresidebuilding in (select t2.id from jc_residebuilding t2 where t2.buildtype = '平房' and t2.OWNER = ?)";
			params.clear();
			params.add(owner);
			list = this.userService.findBySql(sql, params);
			if(list != null && list.size() > 0)
			{
				numlist.add(((BigInteger)list.get(0).get("num")).intValue());
			}
			else
			{
				numlist.add(0);
			}
			
			//6
			sql = "select count(*) as num from jc_bizbuilding t where  t.OWNER = ?";
			params.clear();
			params.add(owner);
			list = this.userService.findBySql(sql, params);
			if(list != null && list.size() > 0)
			{
				numlist.add(((BigInteger)list.get(0).get("num")).intValue());
			}
			else
			{
				numlist.add(0);
			}
			
			//7
			sql = "select count(*) as num from jc_qtbuilding t where  t.OWNER = ?";
			params.clear();
			params.add(owner);
			list = this.userService.findBySql(sql, params);
			if(list != null && list.size() > 0)
			{
				numlist.add(((BigInteger)list.get(0).get("num")).intValue());
			}
			else
			{
				numlist.add(0);
			}
			
			jsonObj.put("data", JSONArray.toJSON(numlist));
			jsonObj.put("success", true);
		}
		catch(Exception e)
		{
			logger.error(e.getMessage(),e);
			jsonObj.put("success", false);
		}
	    return jsonObj.toString();
	}
	
	@RequestMapping(value="loadbaseinfo2",method = {RequestMethod.POST,RequestMethod.GET},produces="text/html;charset=UTF-8")
	@ResponseBody
	public String loadBaseInfo2()
	{
		String owner = getOrganization();
		logger.debug("loadBaseInfo2 owner:" + owner);
		JSONObject jsonObj = new JSONObject();
		try
		{
			List<Integer> numlist = new ArrayList<Integer>();
			
			//1
			String sql = "select count(*) as num from jc_partyorganization t where t.OWNER = ?";
			List<Object> params = new ArrayList<Object>();
			params.add(owner);
			List<HashMap> list = this.userService.findBySql(sql, params);
			if(list != null && list.size() > 0)
			{
				numlist.add(((BigInteger)list.get(0).get("num")).intValue());
			}
			else
			{
				numlist.add(0);
			}
			
			//2
			sql = "select count(*) as num from jc_partymember t where t.OWNER = ?";
			params.clear();
			params.add(owner);
			list = this.userService.findBySql(sql, params);
			if(list != null && list.size() > 0)
			{
				numlist.add(((BigInteger)list.get(0).get("num")).intValue());
			}
			else
			{
				numlist.add(0);
			}
			
			//3
			sql = "select count(*) as num from jc_sqorganization t where t.OWNER = ?";
			params.clear();
			params.add(owner);
			list = this.userService.findBySql(sql, params);
			if(list != null && list.size() > 0)
			{
				numlist.add(((BigInteger)list.get(0).get("num")).intValue());
			}
			else
			{
				numlist.add(0);
			}
			
			//4
			sql = "select count(DISTINCT name) as num from jc_sqorgmember where owner = ?";
			params.clear();
			params.add(owner);
			list = this.userService.findBySql(sql, params);
			if(list != null && list.size() > 0)
			{
				numlist.add(((BigInteger)list.get(0).get("num")).intValue());
			}
			else
			{
				numlist.add(0);
			}
			
			//5
			sql = "select count(*) as num from jc_organization t where t.OWNER = ?";
			params.clear();
			params.add(owner);
			list = this.userService.findBySql(sql, params);
			if(list != null && list.size() > 0)
			{
				numlist.add(((BigInteger)list.get(0).get("num")).intValue());
			}
			else
			{
				numlist.add(0);
			}
			
			//6
			sql = "select count(*) as num from jc_volunteerteam t where t.OWNER = ?";
			params.clear();
			params.add(owner);
			list = this.userService.findBySql(sql, params);
			if(list != null && list.size() > 0)
			{
				numlist.add(((BigInteger)list.get(0).get("num")).intValue());
			}
			else
			{
				numlist.add(0);
			}
			
			//7
			sql = "select count(*) as num from jc_volunteer t where t.OWNER = ?";
			params.clear();
			params.add(owner);
			list = this.userService.findBySql(sql, params);
			if(list != null && list.size() > 0)
			{
				numlist.add(((BigInteger)list.get(0).get("num")).intValue());
			}
			else
			{
				numlist.add(0);
			}
			
			//8
			sql = "select count(*) as num from jc_populationgroup t where t.OWNER = ?";
			params.clear();
			params.add(owner);
			list = this.userService.findBySql(sql, params);
			if(list != null && list.size() > 0)
			{
				numlist.add(((BigInteger)list.get(0).get("num")).intValue());
			}
			else
			{
				numlist.add(0);
			}
			
			jsonObj.put("data", JSONArray.toJSON(numlist));
			jsonObj.put("success", true);
		}
		catch(Exception e)
		{
			logger.error(e.getMessage(),e);
			jsonObj.put("success", false);
		}
	    return jsonObj.toString();
	}
	
	@RequestMapping(value="loadbaseinfo3",method = {RequestMethod.POST,RequestMethod.GET},produces="text/html;charset=UTF-8")
	@ResponseBody
	public String loadBaseInfo3()
	{
		String owner = getOrganization();
		logger.debug("loadBaseInfo3 owner:" + owner);
		JSONObject jsonObj = new JSONObject();
		try
		{
			List<Integer> numlist = new ArrayList<Integer>();
			
			//1
			String sql = "select count(*) as num from jc_culturefacilities t where t.OWNER = ?";
			List<Object> params = new ArrayList<Object>();
			params.add(owner);
			List<HashMap> list = this.userService.findBySql(sql, params);
			if(list != null && list.size() > 0)
			{
				numlist.add(((BigInteger)list.get(0).get("num")).intValue());
			}
			else
			{
				numlist.add(0);
			}
			
			//2
			sql = "select count(*) as num from jc_shelter t where t.OWNER = ?";
			params.clear();
			params.add(owner);
			list = this.userService.findBySql(sql, params);
			if(list != null && list.size() > 0)
			{
				numlist.add(((BigInteger)list.get(0).get("num")).intValue());
			}
			else
			{
				numlist.add(0);
			}
			
			//3
			sql = "select count(*) as num from jc_rubbish t where t.OWNER = ?";
			params.clear();
			params.add(owner);
			list = this.userService.findBySql(sql, params);
			if(list != null && list.size() > 0)
			{
				numlist.add(((BigInteger)list.get(0).get("num")).intValue());
			}
			else
			{
				numlist.add(0);
			}
			
			//4
			sql = "select count(*) as num from jc_advertisement t where t.OWNER = ?";
			params.clear();
			params.add(owner);
			list = this.userService.findBySql(sql, params);
			if(list != null && list.size() > 0)
			{
				numlist.add(((BigInteger)list.get(0).get("num")).intValue());
			}
			else
			{
				numlist.add(0);
			}
			
			//5
			sql = "select count(*) as num from jc_pubfacilities_gy t where t.OWNER = ?";
			params.clear();
			params.add(owner);
			list = this.userService.findBySql(sql, params);
			if(list != null && list.size() > 0)
			{
				numlist.add(((BigInteger)list.get(0).get("num")).intValue());
			}
			else
			{
				numlist.add(0);
			}
			
			//6
			sql = "select count(*) as num from jc_pubfacilities_jt t where t.OWNER = ?";
			params.clear();
			params.add(owner);
			list = this.userService.findBySql(sql, params);
			if(list != null && list.size() > 0)
			{
				numlist.add(((BigInteger)list.get(0).get("num")).intValue());
			}
			else
			{
				numlist.add(0);
			}
			
			//7
			sql = "select count(*) as num from jc_pubfacilities_hj t where t.OWNER = ?";
			params.clear();
			params.add(owner);
			list = this.userService.findBySql(sql, params);
			if(list != null && list.size() > 0)
			{
				numlist.add(((BigInteger)list.get(0).get("num")).intValue());
			}
			else
			{
				numlist.add(0);
			}
			
			//7
			sql = "select count(*) as num from jc_pubfacilities_hj t where t.OWNER = ?";
			params.clear();
			params.add(owner);
			list = this.userService.findBySql(sql, params);
			if(list != null && list.size() > 0)
			{
				numlist.add(((BigInteger)list.get(0).get("num")).intValue());
			}
			else
			{
				numlist.add(0);
			}
			
			//8
			sql = "select count(*) as num from jc_pubfacilities_lh t where t.OWNER = ?";
			params.clear();
			params.add(owner);
			list = this.userService.findBySql(sql, params);
			if(list != null && list.size() > 0)
			{
				numlist.add(((BigInteger)list.get(0).get("num")).intValue());
			}
			else
			{
				numlist.add(0);
			}
			
			//9
			sql = "select count(*) as num from jc_pubfacilities_qt t where t.OWNER = ?";
			params.clear();
			params.add(owner);
			list = this.userService.findBySql(sql, params);
			if(list != null && list.size() > 0)
			{
				numlist.add(((BigInteger)list.get(0).get("num")).intValue());
			}
			else
			{
				numlist.add(0);
			}
			
			jsonObj.put("data", JSONArray.toJSON(numlist));
			jsonObj.put("success", true);
		}
		catch(Exception e)
		{
			logger.error(e.getMessage(),e);
			jsonObj.put("success", false);
		}
	    return jsonObj.toString();
	}
	
	@RequestMapping(value="loadbaseinfo4",method = {RequestMethod.POST,RequestMethod.GET},produces="text/html;charset=UTF-8")
	@ResponseBody
	public String loadBaseInfo4()
	{
		String owner = getOrganization();
		logger.debug("loadbaseinfo4 owner:" + owner);
		JSONObject jsonObj = new JSONObject();
		try
		{
			List<Integer> numlist = new ArrayList<Integer>();
			for(int i = 0; i < 14; i++) numlist.add(0);
			
			//1
			String sql = "select type, count(*) as num from jc_service_store t where t.owner like ? group by t.type";
			List<Object> params = new ArrayList<Object>();
			params.add("%" + owner + "%");
			List<HashMap> list = this.userService.findBySql(sql, params);
			if(list == null || list.size() == 0) {
				
			}
			else {
				for(int i = 0; i < list.size(); i++) {
					String key = (String) list.get(i).get("type");
					int num = ((BigInteger)list.get(i).get("num")).intValue();
					
					if("政府机关".equals(key)) 	numlist.set(0, num);
					if("房产居住".equals(key)) 	numlist.set(1, num);
					if("购物百货".equals(key)) 	numlist.set(2, num);
					if("生活服务".equals(key)) 	numlist.set(3, num);
					if("休闲娱乐".equals(key)) 	numlist.set(4, num);
					if("餐饮小吃".equals(key)) 	numlist.set(5, num);
					if("企业实业".equals(key)) 	numlist.set(6, num);
					if("金融保险".equals(key)) 	numlist.set(7, num);
					if("旅游住宿".equals(key)) 	numlist.set(8, num);
					if("文化教育".equals(key)) 	numlist.set(9, num);
					if("医疗卫生".equals(key)) 	numlist.set(10, num);
					if("交通运输".equals(key)) 	numlist.set(11, num);
					if("汽车服务".equals(key)) 	numlist.set(12, num);
					if("社会团体".equals(key)) 	numlist.set(13, num);
				}
			}
			
			jsonObj.put("data", numlist);
			jsonObj.put("success", true);
		}
		catch(Exception e)
		{
			logger.error(e.getMessage(),e);
			jsonObj.put("success", false);
		}
	    return jsonObj.toString();
	}
	
	@RequestMapping(value="loadbaseinfo5",method = {RequestMethod.POST,RequestMethod.GET},produces="text/html;charset=UTF-8")
	@ResponseBody
	public String loadBaseInfo5()
	{
		String owner = getOrganization();
		logger.debug("loadBaseInfo5 owner:" + owner);
		JSONObject jsonObj = new JSONObject();
		try
		{
			List<Integer> numlist = new ArrayList<Integer>();
			
			//1
			String sql = "select count(*) as num from jc_xftd t where t.OWNER = ?";
			List<Object> params = new ArrayList<Object>();
			params.add(owner);
			List<HashMap> list = this.userService.findBySql(sql, params);
			if(list != null && list.size() > 0)
			{
				numlist.add(((BigInteger)list.get(0).get("num")).intValue());
			}
			else
			{
				numlist.add(0);
			}
			
			//2
			sql = "select count(*) as num from jc_xfss t where t.OWNER = ?";
			params.clear();
			params.add(owner);
			list = this.userService.findBySql(sql, params);
			if(list != null && list.size() > 0)
			{
				numlist.add(((BigInteger)list.get(0).get("num")).intValue());
			}
			else
			{
				numlist.add(0);
			}
			
			jsonObj.put("data", JSONArray.toJSON(numlist));
			jsonObj.put("success", true);
		}
		catch(Exception e)
		{
			logger.error(e.getMessage(),e);
			jsonObj.put("success", false);
		}
	    return jsonObj.toString();
	}
	
	@RequestMapping(value="loadbaseinfo6",method = {RequestMethod.POST,RequestMethod.GET},produces="text/html;charset=UTF-8")
	@ResponseBody
	public String loadBaseInfo6()
	{
		String owner = getOrganization();
		logger.debug("loadBaseInfo6 owner:" + owner);
		JSONObject jsonObj = new JSONObject();
		try
		{
			List<Integer> numlist = new ArrayList<Integer>();
			
			//1
			String sql = "select count(*) as num from jc_tc_ybtcc t where t.OWNER = ?";
			List<Object> params = new ArrayList<Object>();
			params.add(owner);
			List<HashMap> list = this.userService.findBySql(sql, params);
			if(list != null && list.size() > 0)
			{
				numlist.add(((BigInteger)list.get(0).get("num")).intValue());
			}
			else
			{
				numlist.add(0);
			}
			
			//2
			sql = "select count(*) as num from jc_tc_dltcc t where t.OWNER = ?";
			params.clear();
			params.add(owner);
			list = this.userService.findBySql(sql, params);
			if(list != null && list.size() > 0)
			{
				numlist.add(((BigInteger)list.get(0).get("num")).intValue());
			}
			else
			{
				numlist.add(0);
			}
			
			//3
			sql = "select sum(t1.numbers) as num from jc_tc_tcw t1 where t1.inparkname in (select t2.id from jc_tc_ybtcc t2 where t2.OWNER = ?)";
			params.clear();
			params.add(owner);
			list = this.userService.findBySql(sql, params);
			if(list != null && list.size() > 0)
			{
				try {
					numlist.add(((BigDecimal)list.get(0).get("num")).intValue());
				} catch(Exception e) {
					numlist.add(0);
				}
			}
			else
			{
				numlist.add(0);
			}
			
			//4
			sql = "select sum(t1.numbers) as num from jc_tc_tcw t1 where t1.inparkname in (select t2.id from jc_tc_dltcc t2 where t2.OWNER = ?)";
			params.clear();
			params.add(owner);
			list = this.userService.findBySql(sql, params);
			if(list != null && list.size() > 0)
			{
				try {
					numlist.add(((BigDecimal)list.get(0).get("num")).intValue());
				} catch(Exception e) {
					numlist.add(0);
				}
			}
			else
			{
				numlist.add(0);
			}
			
			//5
			sql = "select count(*) as num from jc_tc_tcw t where t.OWNER = ?";
			params.clear();
			params.add(owner);
			list = this.userService.findBySql(sql, params);
			if(list != null && list.size() > 0)
			{
				numlist.add(((BigInteger)list.get(0).get("num")).intValue());
			}
			else
			{
				numlist.add(0);
			}
			
			//6
			sql = "select count(*) as num from jc_tc_fjdctcw t where t.OWNER = ?";
			params.clear();
			params.add(owner);
			list = this.userService.findBySql(sql, params);
			if(list != null && list.size() > 0)
			{
				numlist.add(((BigInteger)list.get(0).get("num")).intValue());
			}
			else
			{
				numlist.add(0);
			}
			
			jsonObj.put("data", JSONArray.toJSON(numlist));
			jsonObj.put("success", true);
		}
		catch(Exception e)
		{
			logger.error(e.getMessage(),e);
			jsonObj.put("success", false);
		}
	    return jsonObj.toString();
	}
	
	@RequestMapping(value="loadminqininfo",method = {RequestMethod.POST,RequestMethod.GET},produces="text/html;charset=UTF-8")
	@ResponseBody
	public String loadMinqinInfo()
	{
		String owner = getOrganization();
		logger.debug("loadminqininfo owner:" + owner);
		JSONObject jsonObj = new JSONObject();
		try
		{
			List<Integer> numlist = new ArrayList<Integer>();
			for(int i = 0; i < 25; i++) numlist.add(0);
			
			//1
			String sql = "select characteristics, count(*) as num from jc_resident t where t.owner like ? group by t.characteristics";
			List<Object> params = new ArrayList<Object>();
			params.add("%" + owner + "%");
			List<HashMap> list = this.userService.findBySql(sql, params);
			if(list == null || list.size() == 0) {
				
			}
			else {
				for(int i = 0; i < list.size(); i++) {
					String key = (String) list.get(i).get("characteristics");
					if(StringUtils.isNullOrEmpty(key)) {
						continue;
					}
					int num = ((BigInteger)list.get(i).get("num")).intValue();
					
					if(key.contains("中共党员")) 	numlist.set(0, num + numlist.get(0));
					if(key.contains("志愿者")) 	numlist.set(1, num + numlist.get(1));
					if(key.contains("居民代表")) 	numlist.set(2, num + numlist.get(2));
					if(key.contains("楼门长")) 	numlist.set(3, num + numlist.get(3));
					if(key.contains("军人")) 	numlist.set(4, num + numlist.get(4));
					if(key.contains("文体骨干")) 	numlist.set(5, num + numlist.get(5));
					if(key.contains("优抚对象")) 	numlist.set(6, num + numlist.get(6));
					if(key.contains("少数民族")) 	numlist.set(7, num + numlist.get(7));
					if(key.contains("0-6岁儿童")) 	numlist.set(8, num + numlist.get(8));
					if(key.contains("7-13岁青少年")) 	numlist.set(9, num + numlist.get(9));
					if(key.contains("13-18岁青少年")) 	numlist.set(10, num + numlist.get(10));
					if(key.contains("老年人")) 	numlist.set(11, num + numlist.get(11));
					if(key.contains("80岁以上老人")) 	numlist.set(12, num + numlist.get(12));
					if(key.contains("90岁以上老人")) 	numlist.set(13, num + numlist.get(13));
					if(key.contains("独居老人")) 	numlist.set(14, num + numlist.get(14));
					if(key.contains("失独老人")) 	numlist.set(15, num + numlist.get(15));
					if(key.contains("空巢老人")) 	numlist.set(16, num + numlist.get(16));
					if(key.contains("失业人员")) 	numlist.set(17, num + numlist.get(17));
					if(key.contains("待业人员")) 	numlist.set(18, num + numlist.get(18));
					if(key.contains("精神病人")) 	numlist.set(19, num + numlist.get(19));
					if(key.contains("残疾人")) 	numlist.set(20, num + numlist.get(20));
					if(key.contains("重点人员")) 	numlist.set(21, num + numlist.get(21));
					if(key.contains("两劳释放")) 	numlist.set(22, num + numlist.get(22));
					if(key.contains("在押在教")) 	numlist.set(23, num + numlist.get(23));
					if(key.contains("社区矫正人员")) 	numlist.set(24, num + numlist.get(24));
				}
			}
			
			jsonObj.put("data", numlist);
			jsonObj.put("success", true);
		}
		catch(Exception e)
		{
			logger.error(e.getMessage(),e);
			jsonObj.put("success", false);
		}
	    return jsonObj.toString();
	}
	
	@RequestMapping(value="loadsxsq",method = {RequestMethod.POST,RequestMethod.GET},produces="text/html;charset=UTF-8")
	@ResponseBody
	public String loadSxsq()
	{
		String owner = getOrganization();
		logger.debug("loadsxsq owner:" + owner);
		JSONObject jsonObj = new JSONObject();
		try
		{
			List<Integer> numlist = new ArrayList<Integer>();
			for(int i = 0; i < 6; i++) numlist.add(0);
			
			//1
			String sql = "select sxdl, count(*) as num from sxsqsj t where t.owner like ? group by t.sxdl";
			List<Object> params = new ArrayList<Object>();
			params.add("%" + owner + "%");
			List<HashMap> list = this.userService.findBySql(sql, params);
			if(list == null || list.size() == 0) {
				
			}
			else {
				for(int i = 0; i < list.size(); i++) {
					String key = (String) list.get(i).get("sxdl");
					int num = ((BigInteger)list.get(i).get("num")).intValue();
					
					if("社区党建事项".equals(key)) 	numlist.set(0, num);
					if("民主自治事项".equals(key)) 	numlist.set(1, num);
					if("社区服务事项".equals(key)) 	numlist.set(2, num);
					if("平安建设事项".equals(key)) 	numlist.set(3, num);
					if("社区环境事项".equals(key)) 	numlist.set(4, num);
					if("卫生健康事项".equals(key)) 	numlist.set(5, num);
				}
			}
			
			jsonObj.put("data", numlist);
			jsonObj.put("success", true);
		}
		catch(Exception e)
		{
			logger.error(e.getMessage(),e);
			jsonObj.put("success", false);
		}
	    return jsonObj.toString();
	}
	
	@RequestMapping(value="loadsqbs",method = {RequestMethod.POST,RequestMethod.GET},produces="text/html;charset=UTF-8")
	@ResponseBody
	public String loadSqbs()
	{
		String owner = getOrganization();
		logger.debug("loadsqbs owner:" + owner);
		JSONObject jsonObj = new JSONObject();
		try
		{
			List<Integer> numlist = new ArrayList<Integer>();
			int total = 0;
			//1
			String sql = "select count(*) as num from nfw_sqbsfw t where t.blsxdl = '民政服务' and t.owner = ?";
			List<Object> params = new ArrayList<Object>();
			params.add(owner);
			List<HashMap> list = this.userService.findBySql(sql, params);
			if(list != null && list.size() > 0)
			{
				numlist.add(((BigInteger)list.get(0).get("num")).intValue());
				total += numlist.get(0);
			}
			else
			{
				numlist.add(0);
			}
			
			//2
			sql = "select count(*) as num from nfw_sqbsfw t where t.blsxdl = '社保服务' and t.owner = ?";
			params.clear();
			params.add(owner);
			list = this.userService.findBySql(sql, params);
			if(list != null && list.size() > 0)
			{
				numlist.add(((BigInteger)list.get(0).get("num")).intValue());
				total += numlist.get(1);
			}
			else
			{
				numlist.add(0);
			}
			
			//3
			sql = "select count(*) as num from nfw_sqbsfw t where t.blsxdl = '助残服务' and t.owner = ?";
			params.clear();
			params.add(owner);
			list = this.userService.findBySql(sql, params);
			if(list != null && list.size() > 0)
			{
				numlist.add(((BigInteger)list.get(0).get("num")).intValue());
				total += numlist.get(2);
			}
			else
			{
				numlist.add(0);
			}
			
			//4
			sql = "select count(*) as num from nfw_sqbsfw t where t.blsxdl = '计生服务' and t.owner = ?";
			params.clear();
			params.add(owner);
			list = this.userService.findBySql(sql, params);
			if(list != null && list.size() > 0)
			{
				numlist.add(((BigInteger)list.get(0).get("num")).intValue());
				total += numlist.get(3);
			}
			else
			{
				numlist.add(0);
			}
			
			//5
			sql = "select count(*) as num from nfw_zmblfw t where t.owner = ?";
			params.clear();
			params.add(owner);
			list = this.userService.findBySql(sql, params);
			if(list != null && list.size() > 0)
			{
				numlist.add(((BigInteger)list.get(0).get("num")).intValue());
				total += numlist.get(4);
			}
			else
			{
				numlist.add(0);
			}
			
			//6
			sql = "select count(*) as num from nfw_wwzffw t where t.owner = ?";
			params.clear();
			params.add(owner);
			list = this.userService.findBySql(sql, params);
			if(list != null && list.size() > 0)
			{
				numlist.add(((BigInteger)list.get(0).get("num")).intValue());
				total += numlist.get(5);
			}
			else
			{
				numlist.add(0);
			}
			
			//7
			sql = "select count(*) as num from nfw_xfslfw t where t.owner = ?";
			params.clear();
			params.add(owner);
			list = this.userService.findBySql(sql, params);
			if(list != null && list.size() > 0)
			{
				numlist.add(((BigInteger)list.get(0).get("num")).intValue());
				total += numlist.get(6);
			}
			else
			{
				numlist.add(0);
			}
			
			numlist.add(total);
			
			jsonObj.put("data", JSONArray.toJSON(numlist));
			jsonObj.put("success", true);
		}
		catch(Exception e)
		{
			logger.error(e.getMessage(),e);
			jsonObj.put("success", false);
		}
	    return jsonObj.toString();
	}
	
	@RequestMapping(value="loadinfodata",method = {RequestMethod.POST,RequestMethod.GET},produces="text/html;charset=UTF-8")
	@ResponseBody
	public String loadInfoData()
	{
		String owner = getOrganization();
		logger.debug("loadinfodata owner:" + owner);
		JSONObject jsonObj = new JSONObject();
		try
		{
			List<Integer> numlist = new ArrayList<Integer>();
			for(int i = 0; i < 18; i++) numlist.add(0);
			
			//1
			String sql = "select sms_type, count(*) as num from sms_message t where t.owner like ? group by t.sms_type";
			List<Object> params = new ArrayList<Object>();
			params.add("%" + owner + "%");
			List<HashMap> list = this.userService.findBySql(sql, params);
			if(list == null || list.size() == 0) {
				
			}
			else {
				for(int i = 0; i < list.size(); i++) {
					String key = (String) list.get(i).get("sms_type");
					int num = ((BigInteger)list.get(i).get("num")).intValue();
					
					if("社区党建".equals(key)) 	numlist.set(0, num);
					if("民主自治".equals(key)) 	numlist.set(1, num);
					if("社区服务".equals(key)) 	numlist.set(2, num);
					if("平安建设".equals(key)) 	numlist.set(3, num);
					if("文化教育".equals(key)) 	numlist.set(4, num);
					if("社区环境".equals(key)) 	numlist.set(5, num);
					if("卫生健康".equals(key)) 	numlist.set(6, num);
					if("社区工作".equals(key)) 	numlist.set(7, num);
					int total = numlist.get(0) + numlist.get(1) + numlist.get(2) + numlist.get(3) + numlist.get(4) + numlist.get(5) + numlist.get(6) + numlist.get(7);
					numlist.set(8, total);
				}
			}
			
			//1
			sql = "select sjfl, count(*) as num from sys_tel_publish t where t.owner like ? group by t.sjfl";
			params.clear();
			params.add("%" + owner + "%");
			list = this.userService.findBySql(sql, params);
			if(list == null || list.size() == 0) {
				
			}
			else {
				for(int i = 0; i < list.size(); i++) {
					String key = (String) list.get(i).get("sjfl");
					int num = ((BigInteger)list.get(i).get("num")).intValue();
					
					if("社区党建".equals(key)) 	numlist.set(9, num);
					if("民主自治".equals(key)) 	numlist.set(10, num);
					if("社区服务".equals(key)) 	numlist.set(11, num);
					if("平安建设".equals(key)) 	numlist.set(12, num);
					if("文化教育".equals(key)) 	numlist.set(13, num);
					if("社区环境".equals(key)) 	numlist.set(14, num);
					if("卫生健康".equals(key)) 	numlist.set(15, num);
					if("社区工作".equals(key)) 	numlist.set(16, num);
					int total = numlist.get(9) + numlist.get(10) + numlist.get(11) + numlist.get(12) + numlist.get(13) + numlist.get(14) + numlist.get(15) + numlist.get(16);
					numlist.set(17, total);
				}
			}
			
			jsonObj.put("data", numlist);
			jsonObj.put("success", true);
		}
		catch(Exception e)
		{
			logger.error(e.getMessage(),e);
			jsonObj.put("success", false);
		}
	    return jsonObj.toString();
	}
}
