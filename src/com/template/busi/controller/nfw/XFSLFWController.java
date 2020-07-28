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
import com.template.service.SysUserService;
import com.template.util.Utility;

@Controller
@RequestMapping("xfslfwController")
public class XFSLFWController {

	private static Logger logger = Logger.getLogger(XFSLFWController.class);
	@Autowired
	private  HttpServletRequest request;
	
	@Autowired
	private SysUserService userService;
	
	@RequestMapping(value="getxfsldatalist",method = {RequestMethod.GET,RequestMethod.GET},produces="text/html;charset=UTF-8")
    @ResponseBody
	public String getDataList() {
		logger.info("getxfsldatalist");
		
		JSONObject jsonObj = new JSONObject();
		try {
			String sql = "select a.*, (select status from fw_flowdatainfo where dataid=a.id order by inserttime desc LIMIT 0, 1) as status from nfw_xfslfw a";
			List<HashMap> nodelist = this.userService.findBySql(sql);
			
			jsonObj.put("success", true);
			jsonObj.put("list", JSONArray.toJSON(nodelist));
		} catch(Exception e) {
			jsonObj.put("success", false);
		}
		
		return jsonObj.toString();
	}
	
	@RequestMapping(value="addOrUpdate",method = RequestMethod.POST,produces="text/html;charset=UTF-8")
	@ResponseBody
	public String addOrUpdate(String id,String bt,String lb, String cd, String fkr,String lxdh, String xq, String bz, String fj)//,String duoxuan)Integer longitude,Integer latitude,
	{
		logger.info("addOrUpdate");
		JSONObject jsonObj = new JSONObject();
		try
		{
			Map<String, Object> map = new HashMap<String, Object>();
			if(id != null && id.length() > 0) {
				String sql = "select * from nfw_xfslfw where id=?";
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
					map.put("lxdh", lxdh);
					map.put("xq", xq);
					map.put("bz", bz);
					map.put("fj", fj);
					
					int ret = this.userService.addData(map, "nfw_xfslfw");
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
					map.put("lxdh", lxdh);
					map.put("xq", xq);
					map.put("bz", bz);
					map.put("fj", fj);
					
					int ret = this.userService.updateData(map, kvs, "nfw_xfslfw");
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
				map.put("lxdh", lxdh);
				map.put("xq", xq);
				map.put("bz", bz);
				map.put("fj", fj);
				
				int ret = this.userService.addData(map, "nfw_xfslfw");
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
			String sql = "delete from nfw_xfslfw where id=?";
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
			String sql = "select a.*, (select status from fw_flowdatainfo where dataid=a.id order by inserttime desc LIMIT 0, 1) as status from nfw_xfslfw a where a.id=?";
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
