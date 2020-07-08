package com.template.controller;
import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;

import com.template.model.SysMenu;
import com.template.service.MenuService;
import com.template.util.HqlFilter;
import com.template.util.ConstValue;
import com.template.util.Utility;
import com.template.util.TimeUtil;

import java.util.List;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
@Controller
@RequestMapping("menuController")
public class MenuController {
	private static Logger logger = Logger.getLogger(MenuController.class);
	@Autowired
	private  HttpServletRequest request;
	@Autowired
	private MenuService menuService;
	
	@RequestMapping(value="addOrUpdate",method = RequestMethod.POST)
	@ResponseBody
	public String addOrUpdate(String id,String menu_enname,String menu_zhname,String menu_type,String table_id,String file_name,String external_url)
	{
		JSONObject jsonObj = new JSONObject();
		try
		{
			SysMenu menu;
			if(id==null || id.equalsIgnoreCase(""))
			{
				menu = new SysMenu();
				menu.setId(Utility.getUniStr());
			}
			else
			{
				menu = menuService.getById(id);
			}
			
			menu.setMenuENName(menu_enname);
			menu.setMenuZHName(menu_zhname);
			menu.setMenuType(menu_type);
			menu.setTableId(table_id);
			menu.setFileName(file_name);
			menu.setExternalUrl(external_url);
			
			menuService.saveOrUpdate(menu);
	        jsonObj.put("success", true);
		}
		catch(Exception e)
		{
			logger.error(e.getMessage(),e);
			jsonObj.put("success", false);
			jsonObj.put("errMsg", e.getMessage());
		}
	    return jsonObj.toString();
	}
	@RequestMapping(value="delete",method = RequestMethod.POST)
	@ResponseBody
	public String delete(String id)
	{
		logger.debug("delete "+id);
		JSONObject jsonObj = new JSONObject();
		try
		{
			SysMenu first = menuService.getById(id);
			menuService.delete(first);
	        jsonObj.put("success", true);
		}
		catch(Exception e)
		{
			logger.error(e.getMessage(),e);
			jsonObj.put("success", false);
			jsonObj.put("errMsg", e.getMessage());
		}
	    return jsonObj.toString();
	}
	@RequestMapping(value="load",method = RequestMethod.GET,produces="text/html;charset=UTF-8")
	@ResponseBody
	public String load(String menu_zhname)
	{
		JSONObject jsonObj = new JSONObject();
		try
		{
			HqlFilter hqlFilter = new HqlFilter();
			
			if(menu_zhname != null && menu_zhname.equalsIgnoreCase("") == false)
			{
				hqlFilter.addQryCond("menu_zhname", HqlFilter.Operator.LIKE, "%"+menu_zhname+"%");
			}
			
	        List<SysMenu> listObj = menuService.findByFilter(hqlFilter);
	        JSONArray jsonArr = new JSONArray();
	        int iTotalCnt = 0;
			for(int i=0;i<listObj.size();i++)
			{
				SysMenu menu = listObj.get(i);
				JSONObject jsonTmp = new JSONObject();
				jsonTmp.put("id", menu.getId());
				jsonTmp.put("menu_enname",menu.getMenuENName());
				jsonTmp.put("menu_zhname",menu.getMenuZHName());
				jsonTmp.put("menu_type",menu.getMenuType());
				jsonTmp.put("table_id",menu.getTableId());
				jsonTmp.put("file_name",menu.getFileName());
				jsonTmp.put("external_url",menu.getExternalUrl());
	
	       		jsonArr.put(jsonTmp);
	        	iTotalCnt++;
			}
	        jsonObj.put("totalCount", iTotalCnt);
	        jsonObj.put("list", jsonArr);
	        jsonObj.put("success", true);
		}
		catch(Exception e)
		{
			logger.error(e.getMessage(),e);
			jsonObj.put("success", false);
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
			SysMenu menu = menuService.getById(id);
			if(menu != null)
			{
				jsonObj.put("id", menu.getId());
				jsonObj.put("menu_enname",menu.getMenuENName());
				jsonObj.put("menu_zhname",menu.getMenuZHName());
				jsonObj.put("menu_type",menu.getMenuType());
				jsonObj.put("table_id",menu.getTableId());
				jsonObj.put("file_name",menu.getFileName());
				jsonObj.put("external_url",menu.getExternalUrl());
	
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
