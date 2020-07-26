package com.template.busi.controller.flow;

import java.util.ArrayList;
import java.util.Date;
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
import com.template.service.SysUserService;
import com.template.util.TimeUtil;
import com.template.util.Utility;

@Controller
@RequestMapping("flowtemplateController")
public class FlowTemplateController {

	private static Logger logger = Logger.getLogger(FlowTemplateController.class);
	
	@Autowired
	private SysUserService userService;
	
	@RequestMapping(value="gettemplatelist",method = {RequestMethod.GET,RequestMethod.GET},produces="text/html;charset=UTF-8")
    @ResponseBody
	public String getTemplateList() {
		logger.info("gettemplatelist");
		
		JSONObject jsonObj = new JSONObject();
		try {
			String sql = "select a.*, (select name from jc_community where id=a.communityid) as communityname from fw_flowtemplateinfo a";
			List<HashMap> templatelist = this.userService.findBySql(sql);
			
			jsonObj.put("success", true);
			jsonObj.put("list", JSONArray.toJSON(templatelist));
		} catch(Exception e) {
			jsonObj.put("success", false);
		}
		
		return jsonObj.toString();
	}
	
	@RequestMapping(value="addOrUpdate",method = RequestMethod.POST,produces="text/html;charset=UTF-8")
	@ResponseBody
	public String addOrUpdate(String id,String templatename, String communityid, String sxdl, String sxxl)//,String duoxuan)Integer longitude,Integer latitude,
	{
		logger.info("addOrUpdate");
		JSONObject jsonObj = new JSONObject();
		try
		{
			Map<String, Object> map = new HashMap<String, Object>();
			if(id != null && id.length() > 0) {
				String sql = "select * from fw_flowtemplateinfo where id=?";
				List<Object> vals = new ArrayList<Object>();
				vals.add(id);
				List list = this.userService.findBySql(sql, vals);
				if(list == null || list.size() == 0) {
					//新增
					map.put("templatename", templatename);
					map.put("communityid", communityid);
					map.put("serviceid", sxdl);
					map.put("subserviceid", sxxl);
					map.put("createtime", TimeUtil.formatDate(new Date(), "yyyy-MM-dd HH:mm:ss"));
					
					int ret = this.userService.addData(map, "fw_flowtemplateinfo");
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
					
					map.put("templatename", templatename);
					map.put("communityid", communityid);
					map.put("serviceid", sxdl);
					map.put("subserviceid", sxxl);
					map.put("createtime", TimeUtil.formatDate(new Date(), "yyyy-MM-dd HH:mm:ss"));
					
					int ret = this.userService.updateData(map, kvs, "fw_flowtemplateinfo");
					if(ret > 0) {
						jsonObj.put("success", true);
					}
					else {
						jsonObj.put("success", false);
					}
				}
				
			}
			else {
				map.put("templatename", templatename);
				map.put("communityid", communityid);
				map.put("serviceid", sxdl);
				map.put("subserviceid", sxxl);
				map.put("createtime", TimeUtil.formatDate(new Date(), "yyyy-MM-dd HH:mm:ss"));
				
				int ret = this.userService.addData(map, "fw_flowtemplateinfo");
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
			String sql = "delete from fw_flowtemplateinfo where id=?";
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
			String sql = "select * from fw_flowtemplateinfo where id=?";
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
	
	@RequestMapping(value="getcommunitylist",method = {RequestMethod.GET,RequestMethod.GET},produces="text/html;charset=UTF-8")
    @ResponseBody
	public String getCommunityList() {
		logger.info("getcommunitylist");
		
		JSONObject jsonObj = new JSONObject();
		try {
			String sql = "select * from jc_community";
			List<HashMap> templateprocesslist = this.userService.findBySql(sql);
			
			jsonObj.put("success", true);
			jsonObj.put("list", JSONArray.toJSON(templateprocesslist));
		} catch(Exception e) {
			jsonObj.put("success", false);
		}
		
		return jsonObj.toString();
	}
	
	@RequestMapping(value="gettemplateprocesslist",method = {RequestMethod.GET,RequestMethod.GET},produces="text/html;charset=UTF-8")
    @ResponseBody
	public String getTemplateProcessList(String templateid) {
		logger.info("gettemplateprocesslist");
		
		JSONObject jsonObj = new JSONObject();
		try {
			String sql = "select a.*, (select nodename from fw_nodeinfo where id=a.nodeid) as nodename, (select nodename from fw_nodeinfo where id=a.prevnodeid) as prevnodename, (select nodename from fw_nodeinfo where id=a.nextnodeid) as nextnodename from fw_flowprocessinfo a where a.templateid=?";
			List<Object> params = new ArrayList<Object>();
			params.add(templateid);
			List<HashMap> templateprocesslist = this.userService.findBySql(sql, params);
			
			jsonObj.put("success", true);
			jsonObj.put("list", JSONArray.toJSON(templateprocesslist));
		} catch(Exception e) {
			jsonObj.put("success", false);
		}
		
		return jsonObj.toString();
	}
	
	@RequestMapping(value="getsxdllist",method = {RequestMethod.GET,RequestMethod.GET},produces="text/html;charset=UTF-8")
    @ResponseBody
	public String getSxdlList() {
		logger.info("getsxdllist");
		
		JSONObject jsonObj = new JSONObject();
		try {
			String sql = "select distinct blsxdl from nfw_sqbsfw";
			List<HashMap> templateprocesslist = this.userService.findBySql(sql);
			
			jsonObj.put("success", true);
			jsonObj.put("list", JSONArray.toJSON(templateprocesslist));
		} catch(Exception e) {
			jsonObj.put("success", false);
		}
		
		return jsonObj.toString();
	}
	
	@RequestMapping(value="getsxxllist",method = {RequestMethod.GET,RequestMethod.POST},produces="text/html;charset=UTF-8")
    @ResponseBody
	public String getSxxlList(String sxdl) {
		logger.info("getsxxllist");
		
		JSONObject jsonObj = new JSONObject();
		try {
			String sql = "select distinct blsxxl from nfw_sqbsfw where blsxdl=?";
			List<Object> params = new ArrayList<Object>();
			params.add(sxdl);
			List<HashMap> templateprocesslist = this.userService.findBySql(sql, params);
			
			jsonObj.put("success", true);
			jsonObj.put("list", JSONArray.toJSON(templateprocesslist));
		} catch(Exception e) {
			jsonObj.put("success", false);
		}
		
		return jsonObj.toString();
	}
	
	@RequestMapping(value="addtemplateprocess",method = RequestMethod.POST,produces="text/html;charset=UTF-8")
	@ResponseBody
	public String addTemplateProcess(String templateid, Integer nodeid, Integer prevnode, String prevlabel, String prevstatus, Integer nextnode, String nextlabel, String nextstatus)//,String duoxuan)Integer longitude,Integer latitude,
	{
		logger.info("addtemplateprocess");
		JSONObject jsonObj = new JSONObject();
		try
		{
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("templateid", templateid);
			map.put("nodeid", nodeid);
			map.put("prevnodeid", prevnode);
			map.put("prevlabel", prevlabel);
			map.put("prevstatus", prevstatus);
			map.put("nextnodeid", nextnode);
			map.put("nextlabel", nextlabel);
			map.put("nextstatus", nextstatus);
			
			int ret = this.userService.addData(map, "fw_flowprocessinfo");
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
	
	@RequestMapping(value="saveprocessdata",method = RequestMethod.POST,produces="text/html;charset=UTF-8")
	@ResponseBody
	public String saveProcessData(String desc, Integer type, String dataid, Integer processid, String stat)//,String duoxuan)Integer longitude,Integer latitude,
	{
		logger.info("saveprocessdata");
		JSONObject jsonObj = new JSONObject();
		try
		{
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("result", type);
			map.put("flowdesc", desc);
			map.put("dataid", dataid);
			map.put("processid", processid);
			map.put("status", stat);
			map.put("inserttime", Utility.getSystemTime());
			
			int ret = this.userService.addData(map, "fw_flowdatainfo");
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
	
	@RequestMapping(value="deleteprocess",method = RequestMethod.POST,produces="text/html;charset=UTF-8")
	@ResponseBody
	public String deleteProcess(int id)
	{
		logger.debug("deleteprocess "+id);
		JSONObject jsonObj = new JSONObject();
		try
		{
			String sql = "delete from fw_flowprocessinfo where id=?";
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
	
	@RequestMapping(value="getdatatemplateprocessinfo",method = {RequestMethod.GET,RequestMethod.GET},produces="text/html;charset=UTF-8")
    @ResponseBody
	public String getCurDataTemplateProcessInfo(String dataid) {
		logger.info("getdatatemplateprocessinfo");
		
		int templateid = 6;
		
		JSONObject jsonObj = new JSONObject();
		try {
			
			String sql = "select * from fw_flowprocessinfo where templateid=?";
			List<Object> params = new ArrayList<Object>();
			params.add(templateid);
			List<HashMap> templateprocesslist = this.userService.findBySql(sql, params);
			if(templateprocesslist == null || templateprocesslist.size() == 0) {
				jsonObj.put("success", false);
			}
			else {
				if(dataid == null || dataid.trim().length() == 0) {
					jsonObj.put("success", true);
					jsonObj.put("finish", false);
					jsonObj.put("data", templateprocesslist.get(0));
				}
				else {
					sql = "select b.* from fw_flowdatainfo a, fw_flowprocessinfo b where a.processid=b.id and a.dataid=? order by a.inserttime desc";
					params.clear();
					params.add(dataid);
					List<HashMap> dataprocesslist = this.userService.findBySql(sql, params);
					if(dataprocesslist != null && dataprocesslist.size() > 0) {
						if(dataprocesslist.get(0).get("nextnodeid") == null) {
							//最终节点
							jsonObj.put("success", true);
							jsonObj.put("finish", true);
						}
						else {
							int nextnodeid = (int) dataprocesslist.get(0).get("nextnodeid");
							System.out.println("NextNodeID：" + nextnodeid);
							sql = "select a.*, (select nodename from fw_nodeinfo where id=a.nodeid) as nodename from fw_flowprocessinfo a where a.templateid=? and a.nodeid=?";
							params.clear();
							params.add(templateid);
							params.add(nextnodeid);
							List<HashMap> templateprocesslist2 = this.userService.findBySql(sql, params);
							if(templateprocesslist2 == null || templateprocesslist2.size() == 0) {
								jsonObj.put("success", false);
							}
							else {
								jsonObj.put("success", true);
								jsonObj.put("finish", false);
								jsonObj.put("data", templateprocesslist2.get(0));
							}
							
						}
					}
					else {
						jsonObj.put("success", false);
					}
				}
			}		
			
		} catch(Exception e) {
			jsonObj.put("success", false);
		}
		
		return jsonObj.toString();
	}
}
