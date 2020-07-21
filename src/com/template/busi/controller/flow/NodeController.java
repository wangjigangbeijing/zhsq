package com.template.busi.controller.flow;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.template.model.jcsqsj.Community;
import com.template.service.SysUserService;
import com.template.util.TimeUtil;

@Controller
@RequestMapping("flowController")
public class NodeController {

	private static Logger logger = Logger.getLogger(NodeController.class);
	
	@Autowired
	private SysUserService userService;
	
	@RequestMapping(value="getnodelist",method = {RequestMethod.GET,RequestMethod.GET},produces="text/html;charset=UTF-8")
    @ResponseBody
	public String getNodeList() {
		logger.info("getnodelist");
		
		JSONObject jsonObj = new JSONObject();
		try {
			String sql = "select * from fw_nodeinfo";
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
	public String addOrUpdate(String id,String nodename,String node_category,Integer canback,Integer cangoon)//,String duoxuan)Integer longitude,Integer latitude,
	{
		logger.info("addOrUpdate");
		JSONObject jsonObj = new JSONObject();
		try
		{
			Map<String, Object> map = new HashMap<String, Object>();
			if(id != null && id.length() > 0) {
				String sql = "select * from fw_nodeinfo where id=?";
				List<Object> vals = new ArrayList<Object>();
				vals.add(id);
				List list = this.userService.findBySql(sql, vals);
				if(list == null || list.size() == 0) {
					//新增
					map.put("nodename", nodename);
					map.put("node_category", node_category);
					map.put("canback", canback);
					map.put("cangoon", cangoon);
					
					int ret = this.userService.addData(map, "fw_nodeinfo");
					if(ret > 0) {
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
					
					map.put("nodename", nodename);
					map.put("node_category", node_category);
					map.put("canback", canback);
					map.put("cangoon", cangoon);
					
					int ret = this.userService.updateData(map, kvs, "fw_nodeinfo");
					if(ret > 0) {
						jsonObj.put("success", true);
					}
					else {
						jsonObj.put("success", false);
					}
				}
				
			}
			else {
				map.put("nodename", nodename);
				map.put("node_category", node_category);
				map.put("canback", canback);
				map.put("cangoon", cangoon);
				
				int ret = this.userService.addData(map, "fw_nodeinfo");
				if(ret > 0) {
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
	public String delete(int id)
	{
		logger.debug("delete "+id);
		JSONObject jsonObj = new JSONObject();
		try
		{
			String sql = "delete from fw_nodeinfo where id=?";
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
			String sql = "select * from fw_nodeinfo where id=?";
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
