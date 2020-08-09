package com.template.busi.controller.nfw;

import java.util.ArrayList;
import java.util.Date;
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
import com.template.model.SysUser;
import com.template.service.SysUserService;
import com.template.util.ConstValue;
import com.template.util.HqlFilter;
import com.template.util.TimeUtil;
import com.template.util.Utility;

@Controller
@RequestMapping("znhffwController")
public class ZNHFFWController {

	private static Logger logger = Logger.getLogger(ZNHFFWController.class);
	@Autowired
	private  HttpServletRequest request;
	
	@Autowired
	private SysUserService userService;
	
	@RequestMapping(value="getznhfdatalist",method = {RequestMethod.GET,RequestMethod.GET},produces="text/html;charset=UTF-8")
    @ResponseBody
	public String getDataList(String jsjbid, String dsrxm, String hfsfcg, String dsrsfmy) {
		logger.info("getznhfdatalist:" + jsjbid);
		
		JSONObject jsonObj = new JSONObject();
		try {
			String sql = "select a.* from nfw_znhffw a where a.jsjbid=?";
			List<Object> vals = new ArrayList<Object>();
			vals.add(jsjbid);
			
			if(!StringUtils.isNullOrEmpty(dsrxm)) {
				sql += " and a.dsrxm like ?";
				vals.add("%" + dsrxm + "%");
			}
			if(!StringUtils.isNullOrEmpty(hfsfcg) && !"null".equals(hfsfcg)) {
				sql += " and a.hfsfcg=?";
				vals.add(hfsfcg);
			}
			if(!StringUtils.isNullOrEmpty(dsrsfmy)) {
				sql += " and a.dsrsfmy=?";
				vals.add(dsrsfmy);
			}
			List<HashMap> nodelist = this.userService.findBySql(sql, vals);
			
			jsonObj.put("success", true);
			jsonObj.put("list", JSONArray.toJSON(nodelist));
		} catch(Exception e) {
			jsonObj.put("success", false);
		}
		
		return jsonObj.toString();
	}
	
	@RequestMapping(value="addOrUpdate",method = RequestMethod.POST,produces="text/html;charset=UTF-8")
	@ResponseBody
	public String addOrUpdate(String id, String jsjbid, String dsr, String dsrxm, String dsrlxdh, String hfsfcg, String sffkqk, String dsrsfmy,String bmqksm, String bz, String hfr, String hfrname, String hfsj, String hfly)//,String duoxuan)Integer longitude,Integer latitude,
	{
		logger.info("addOrUpdate:" + id);
		JSONObject jsonObj = new JSONObject();
		try
		{
			Map<String, Object> map = new HashMap<String, Object>();
			if(id != null && id.length() > 0) {
				String sql = "select * from nfw_znhffw where id=?";
				List<Object> vals = new ArrayList<Object>();
				vals.add(id);
				List list = this.userService.findBySql(sql, vals);
				if(list == null || list.size() == 0) {
					//新增
					map.put("id", Utility.getUniStr());
					map.put("jsjbid", jsjbid);
					map.put("dsrxm", dsrxm);
					map.put("dsrlxdh", dsrlxdh);
					map.put("hfsfcg", hfsfcg);
					map.put("sffkqk", sffkqk);
					map.put("dsrsfmy", dsrsfmy);
					map.put("bmqksm", bmqksm);
					map.put("hfly", hfly);
					map.put("bz", bz);
					map.put("hfr", hfr);
					map.put("hfrname", hfrname);
					map.put("hfsj", hfsj);
					
					int ret = this.userService.addData(map, "nfw_znhffw");
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
					map.put("jsjbid", jsjbid);
					map.put("dsrxm", dsrxm);
					map.put("dsrlxdh", dsrlxdh);
					map.put("hfsfcg", hfsfcg);
					map.put("sffkqk", sffkqk);
					map.put("dsrsfmy", dsrsfmy);
					map.put("bmqksm", bmqksm);
					map.put("hfly", hfly);
					map.put("bz", bz);
					map.put("hfr", hfr);
					map.put("hfrname", hfrname);
					map.put("hfsj", hfsj);
					
					int ret = this.userService.updateData(map, kvs, "nfw_znhffw");
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
				map.put("jsjbid", jsjbid);
				map.put("dsrxm", dsrxm);
				map.put("dsrlxdh", dsrlxdh);
				map.put("hfsfcg", hfsfcg);
				map.put("sffkqk", sffkqk);
				map.put("dsrsfmy", dsrsfmy);
				map.put("bmqksm", bmqksm);
				map.put("hfly", hfly);
				map.put("bz", bz);
				map.put("hfr", hfr);
				map.put("hfrname", hfrname);
				map.put("hfsj", hfsj);
				
				int ret = this.userService.addData(map, "nfw_znhffw");
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
			String sql = "delete from nfw_znhffw where id=?";
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
			String sql = "select a.* from nfw_znhffw a where a.id=?";
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
