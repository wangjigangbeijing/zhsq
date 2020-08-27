package com.template.controller;

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

import com.template.service.SysUserService;

@Controller
@RequestMapping("utilController")
public class UtilController {

	private static Logger logger = Logger.getLogger(UtilController.class);
	
	@Autowired
	private SysUserService userService;
	
	@Autowired
	private  HttpServletRequest request;
	
	@RequestMapping(value="getsxsqsjdl",method = {RequestMethod.POST,RequestMethod.GET},produces="text/html;charset=UTF-8")
	@ResponseBody
	public String getSxsqsjDl() {
		JSONObject jsonObj = new JSONObject();
		
		try
		{			
			String sql = "select distinct sxdl from sxsqsj_fl";
			List<HashMap> list = this.userService.findBySql(sql);
			if(list == null || list.size() == 0) {
				jsonObj.put("success", false);
			}
			else {
				jsonObj.put("list", list);
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
	
	@RequestMapping(value="getsxsqsjxl",method = {RequestMethod.POST,RequestMethod.GET},produces="text/html;charset=UTF-8")
	@ResponseBody
	public String getSxsqsjXl(String sxdl) {
		JSONObject jsonObj = new JSONObject();
		
		try
		{
			
			String sql = "select sxxl from sxsqsj_fl where sxdl=?";
			List<Object> params = new ArrayList<Object>();
			params.add(sxdl);
			List<HashMap> list = this.userService.findBySql(sql, params);
			if(list == null || list.size() == 0) {
				jsonObj.put("success", false);
			}
			else {
				jsonObj.put("list", list);
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
}
