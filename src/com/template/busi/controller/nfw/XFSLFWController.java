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
import com.template.util.ConstValue;
import com.template.util.Utility;

@Controller
@RequestMapping("xfslfwController")
public class XFSLFWController {

	private static Logger logger = Logger.getLogger(XFSLFWController.class);
	@Autowired
	private  HttpServletRequest request;
	
	@Autowired
	private SysUserService userService;
	
	/**
	 * 获取用户社区
	 * @return
	 */
	private String getOrganization() {
//		String userid = (String) request.getSession().getAttribute(ConstValue.SESSION_USER_ID);
//		
//		String sql = "select a.organization from sys_user_organization a where a.user=?";
//		List<Object> params = new ArrayList<Object>();
//		params.add(userid);
//		List<HashMap> list = this.jsjbfwService.findBySql(sql, params);
//		if(list == null || list.size() == 0) {
//			return null;
//		}
//		else {
//			return (String) list.get(0).get("organization");
//		}
		return Utility.getInstance().getOrganization(request);
	}
	
	@RequestMapping(value="getxfsldatalist",method = {RequestMethod.GET,RequestMethod.GET},produces="text/html;charset=UTF-8")
    @ResponseBody
	public String getDataList(String bt, String lb, String fkrname, String status) {
		logger.info("getxfsldatalist");
		
		String organization = this.getOrganization();
		
		JSONObject jsonObj = new JSONObject();
		try {
			List<Object> params = new ArrayList<Object>();
			String sql = "select a.*, (select status from fw_flowdatainfo where dataid=a.id order by inserttime desc LIMIT 0, 1) as status from nfw_xfslfw a where 1=1";
			if(!StringUtils.isNullOrEmpty(organization)) {
				sql += " and a.owner=?";
				params.add(organization);
			}
			if(!StringUtils.isNullOrEmpty(bt)) {
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
			if(!StringUtils.isNullOrEmpty(status)) {
				sql = "select b.* from (" + sql + ") as b where b.status=?";
				params.add(status);
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
					map.put("fkrname", fkrname);
					map.put("lxdh", lxdh);
					map.put("slsj", slsj);
					map.put("xq", xq);
					map.put("bz", bz);
					map.put("fj", fj);
					map.put("owner", getOrganization());
					
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
					map.put("fkrname", fkrname);
					map.put("lxdh", lxdh);
					map.put("slsj", slsj);
					map.put("xq", xq);
					map.put("bz", bz);
					map.put("fj", fj);
					map.put("owner", getOrganization());
					
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
				map.put("fkrname", fkrname);
				map.put("lxdh", lxdh);
				map.put("slsj", slsj);
				map.put("xq", xq);
				map.put("bz", bz);
				map.put("fj", fj);
				map.put("owner", getOrganization());
				
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
