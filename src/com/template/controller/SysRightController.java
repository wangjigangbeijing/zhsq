package com.template.controller;
import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;

import com.mysql.cj.util.StringUtils;
import com.template.model.SysRole;
import com.template.service.SysRightService;
import com.template.service.SysRoleService;
import com.template.util.HqlFilter;
import com.template.util.ConstValue;
import com.template.util.Utility;
import com.template.util.TimeUtil;
import java.util.List;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
@Controller
@RequestMapping("sysRightController")
public class SysRightController {
	private static Logger logger = Logger.getLogger(SysRightController.class);
	@Autowired
	private  HttpServletRequest request;
	@Autowired
	private SysRightService  sys_rightService;
	
	@RequestMapping(value="getUserRights",method = RequestMethod.POST,produces="text/html;charset=UTF-8")
	@ResponseBody
	public String getUserRights()
	{
		logger.debug("getUserRights");
		
		JSONObject jsonObj = new JSONObject();
		try
		{
			List<String> userRoleList = Utility.getInstance().getUserRole(request);
			
			String roleIds = "";
			for(int i=0;i<userRoleList.size();i++)
			{
				roleIds += "'"+userRoleList.get(i)+"',";			
			}
			
			if(roleIds.endsWith(","))
				roleIds = roleIds.substring(0, roleIds.length() - 1);
			
			String sSql = "SELECT SYS_RIGHT.RIGHTID,RIGHTTYPE FROM SYS_RIGHT,SYS_ROLE_RIGHT WHERE "
					+ "ROLEID IN ("+roleIds+") AND SYS_RIGHT.RIGHTID = SYS_ROLE_RIGHT.RIGHTID";
			if(StringUtils.isNullOrEmpty(roleIds)) {
				sSql = "SELECT SYS_RIGHT.RIGHTID,RIGHTTYPE FROM SYS_RIGHT,SYS_ROLE_RIGHT WHERE SYS_RIGHT.RIGHTID = SYS_ROLE_RIGHT.RIGHTID";
			}
			
			List<HashMap> listObj = sys_rightService.findBySql(sSql);
			
			JSONArray jsonRight = new JSONArray();
			
			for(int i=0;i<listObj.size();i++)
			{
				HashMap hm = listObj.get(i);
				
				JSONObject jsonTmp = new JSONObject();
				
				String rightid = (String)hm.get("RIGHTID");
				String righttype = (String)hm.get("RIGHTTYPE");
				
				jsonTmp.put("rightid", rightid);
				jsonTmp.put("righttype", righttype);
				
				jsonRight.put(jsonTmp);
			}
			
	        jsonObj.put("success", true);
	        jsonObj.put("rightArr", jsonRight);
		}
		catch(Exception e)
		{
			logger.error(e.getMessage(),e);
			jsonObj.put("success", false);
			jsonObj.put("errMsg", e.getMessage());
		}
	    return jsonObj.toString();
	}
}
