package com.template.controller;
import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;

import com.template.model.SysRole;
import com.template.service.SysRoleService;
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
@RequestMapping("sysRoleController")
public class SysRoleController {
	private static Logger logger = Logger.getLogger(SysRoleController.class);
	@Autowired
	private  HttpServletRequest request;
	@Autowired
	private SysRoleService  sys_roleService;
@RequestMapping(value="addOrUpdate",method = RequestMethod.POST,produces="text/html;charset=UTF-8")
@ResponseBody
public String addOrUpdate(String id,String name,String code,String note)
{
	JSONObject jsonObj = new JSONObject();
	try
	{
		SysRole  sys_role;
		if(id==null || id.equalsIgnoreCase(""))
		{
			 sys_role = new SysRole();
			 sys_role.setId(Utility.getUniStr());
		}
		else
		{
			 sys_role =  sys_roleService.getById(id);
		}
		 sys_role.setname(name);
		 sys_role.setcode(code);
		 sys_role.setnote(note);

         sys_roleService.save( sys_role);
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
@RequestMapping(value="delete",method = RequestMethod.POST,produces="text/html;charset=UTF-8")
@ResponseBody
public String delete(String id)
{
	logger.debug("delete "+id);
	JSONObject jsonObj = new JSONObject();
	try
	{
		SysRole  sys_role =  sys_roleService.getById(id);
		 sys_roleService.delete( sys_role);
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
public String load(String name,String code)
{
	JSONObject jsonObj = new JSONObject();
	try
	{
		HqlFilter hqlFilter = new HqlFilter();
if(name != null && name.equalsIgnoreCase("") == false && name.equalsIgnoreCase("null") == false)
{
	hqlFilter.addQryCond("name", HqlFilter.Operator.LIKE, "%"+name+"%");
}
if(code != null && code.equalsIgnoreCase("") == false && code.equalsIgnoreCase("null") == false)
{
	hqlFilter.addQryCond("code", HqlFilter.Operator.LIKE, "%"+code+"%");
}

        List<SysRole> listObj =  sys_roleService.findByFilter(hqlFilter);
        JSONArray jsonArr = new JSONArray();
        int iTotalCnt = 0;
		for(int i=0;i<listObj.size();i++)
		{
			SysRole  sys_role = listObj.get(i);
			JSONObject jsonTmp = new JSONObject();
			jsonTmp.put("id",  sys_role.getId());
			jsonTmp.put("name", sys_role.getname());
			jsonTmp.put("code", sys_role.getcode());
			jsonTmp.put("note", sys_role.getnote());

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
		SysRole  sys_role =  sys_roleService.getById(id);
		if( sys_role != null)
		{
			jsonObj.put("name", sys_role.getname());
			jsonObj.put("code", sys_role.getcode());
			jsonObj.put("note", sys_role.getnote());

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
