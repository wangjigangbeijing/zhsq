package com.template.busi.controller.nfw;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.template.service.SysUserService;
import com.template.util.Utility;

@Controller
@RequestMapping("myslfwController")
public class MYSLFWController {

	private static Logger logger = Logger.getLogger(MYSLFWController.class);
	@Autowired
	private  HttpServletRequest request;
	
	@Autowired
	private SysUserService userService;
	
	@RequestMapping(value="getmysldatalist",method = {RequestMethod.GET,RequestMethod.GET},produces="text/html;charset=UTF-8")
    @ResponseBody
	public String getDataList(String bt, String lb, String fkrname) {
		logger.info("getmysldatalist");
		
		JSONObject jsonObj = new JSONObject();
		try {
			List<Object> params = new ArrayList<Object>();
			String sql = "select a.*, (select status from fw_flowdatainfo where dataid=a.id order by inserttime desc LIMIT 0, 1) as status from nfw_myslfw a where 1=1";
			if(!StringUtils.isNullOrEmpty(bt)  && !"null".equals(bt)) {
				sql += " and a.bt like ?";
				params.add("%" + bt + "%");
			}
			if(!StringUtils.isNullOrEmpty(lb) && !"null".equals(lb)) {
				sql += " and a.lb=?";
				params.add(lb);
			}
			if(!StringUtils.isNullOrEmpty(fkrname)) {
				sql += " and a.fkrname like ?";
				params.add("%" + fkrname + "%");
			}
			List<HashMap> nodelist = this.userService.findBySql(sql, params);
			
			jsonObj.put("success", true);
			jsonObj.put("list", JSONArray.toJSON(nodelist));
		} catch(Exception e) {
			jsonObj.put("success", false);
		}
		
		return jsonObj.toString();
	}
	
	@RequestMapping(value="addOrUpdate",method = RequestMethod.POST,produces="text/html;charset=UTF-8")
	@ResponseBody
	public String addOrUpdate(String id,String bt,String lb, String cd, String fkr, String fkrname, String lxdh, String slsj, String xq, String bz, String fj)//,String duoxuan)Integer longitude,Integer latitude,
	{
		logger.info("addOrUpdate");
		JSONObject jsonObj = new JSONObject();
		try
		{
			Map<String, Object> map = new HashMap<String, Object>();
			if(id != null && id.length() > 0) {
				String sql = "select * from nfw_myslfw where id=?";
				List<Object> vals = new ArrayList<Object>();
				vals.add(id);
				List list = this.userService.findBySql(sql, vals);
				if(list == null || list.size() == 0) {
					//新增
					map.put("id", Utility.getUniStr());
					map.put("bt", bt);
					map.put("lb", lb);
					map.put("cd", cd);
					map.put("fkr", fkr);
					map.put("fkrname", fkrname);
					map.put("lxdh", lxdh);
					map.put("slsj", slsj);
					map.put("xq", xq);
					map.put("bz", bz);
					map.put("fj", fj);
					
					int ret = this.userService.addData(map, "nfw_myslfw");
					if(ret > 0) {
						jsonObj.put("dataid", map.get("id"));
						jsonObj.put("success", true);
					}
					else {
						jsonObj.put("success", false);
					}
				}
				else {
					//更新
					
					Map<String, Object> kvs = new HashMap<String, Object>();
					kvs.put("id", id);
					
					map.put("bt", bt);
					map.put("lb", lb);
					map.put("cd", cd);
					map.put("fkr", fkr);
					map.put("fkrname", fkrname);
					map.put("lxdh", lxdh);
					map.put("slsj", slsj);
					map.put("xq", xq);
					map.put("bz", bz);
					map.put("fj", fj);
					
					int ret = this.userService.updateData(map, kvs, "nfw_myslfw");
					if(ret > 0) {
						jsonObj.put("dataid", id);
						jsonObj.put("success", true);
					}
					else {
						jsonObj.put("success", false);
					}
				}
				
			}
			else {
				map.put("id", Utility.getUniStr());
				map.put("bt", bt);
				map.put("lb", lb);
				map.put("cd", cd);
				map.put("fkr", fkr);
				map.put("fkrname", fkrname);
				map.put("lxdh", lxdh);
				map.put("slsj", slsj);
				map.put("xq", xq);
				map.put("bz", bz);
				map.put("fj", fj);
				
				int ret = this.userService.addData(map, "nfw_myslfw");
				if(ret > 0) {
					jsonObj.put("dataid", map.get("id"));
					jsonObj.put("success", true);
				}
				else {
					jsonObj.put("success", false);
				}
			}
		}
		catch(Exception e)
		{
			logger.error(e.getMessage(),e);
			jsonObj.put("success", false);
			jsonObj.put("errMsg", e.getMessage());
		}
	    return jsonObj.toString();
	}
	
	@RequestMapping(value="delete",method = RequestMethod.POST,produces="text/html;charset=UTF-8")
	@ResponseBody
	public String delete(String id)
	{
		logger.debug("delete "+id);
		JSONObject jsonObj = new JSONObject();
		try
		{
			String sql = "delete from nfw_myslfw where id=?";
			List<Object> params = new ArrayList<Object>();
			params.add(id);
			int ret = this.userService.executeSql(sql, params);
			if(ret > 0) {
				jsonObj.put("success", true);
			}
			else {
				jsonObj.put("success", false);
			}
		}
		catch(Exception e)
		{
			logger.error(e.getMessage(),e);
			jsonObj.put("success", false);
			jsonObj.put("errMsg", e.getMessage());
		}
	    return jsonObj.toString();
	}
	
	@RequestMapping(value="get",method = {RequestMethod.POST,RequestMethod.GET},produces="text/html;charset=UTF-8")
	@ResponseBody
	public String get(String id)
	{
		JSONObject jsonObj = new JSONObject();
		try
		{
			String sql = "select a.*, (select status from fw_flowdatainfo where dataid=a.id order by inserttime desc LIMIT 0, 1) as status from nfw_myslfw a where a.id=?";
			List<Object> params = new ArrayList<Object>();
			params.add(id);
			List<HashMap> nodelist = this.userService.findBySql(sql, params);
			if(nodelist != null && nodelist.size() > 0)
			{
				jsonObj.put("data", JSONObject.wrap(nodelist.get(0)));
				jsonObj.put("success", true);
			}
			else
			{
				logger.error("object is not found...");
				jsonObj.put("success", false);
				jsonObj.put("errMsg", "Object can not found...");
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
