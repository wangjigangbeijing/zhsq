package com.template.controller;
import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;

import com.template.busi.safe.AES;
import com.template.model.SysUser;
import com.template.model.SysUserOrganization;
import com.template.service.SysUserOrganizationService;
import com.template.service.SysUserService;
import com.template.service.SysWechatNoticeService;
import com.template.util.HqlFilter;
import com.template.util.ConstValue;
import com.template.util.EncryptUtil;
import com.template.util.Utility;
import com.template.util.TimeUtil;
import java.util.List;
import java.util.ArrayList;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
@Controller
@RequestMapping("sysWechatNoticeController")
public class SysWechatNoticeController {
	private static Logger logger = Logger.getLogger(SysWechatNoticeController.class);
	@Autowired
	private  HttpServletRequest request;
	@Autowired
	private SysWechatNoticeService sysWechatNoticeService;
	
	@RequestMapping(value="load",method = RequestMethod.GET,produces="text/html;charset=UTF-8")
	@ResponseBody
	public String load(String name,String status)
	{
		JSONObject jsonObj = new JSONObject();
		try
		{
			/*HqlFilter hqlFilter = new HqlFilter();
			if(name != null && name.equalsIgnoreCase("") == false && name.equalsIgnoreCase("null") == false)
			{
				hqlFilter.addQryCond("name", HqlFilter.Operator.LIKE, "%"+name+"%");
			}
			if(status != null && status.equalsIgnoreCase("") == false && status.equalsIgnoreCase("null") == false)
			{
				hqlFilter.addQryCond("status", HqlFilter.Operator.LIKE, "%"+status+"%");
			}
			
			hqlFilter.setOrder("asc");
			
			String organization = Utility.getInstance().getOrganization(request);
			
			ArrayList<String> alOrg = new ArrayList<String>(); 
			
			if(organization != null && organization.equalsIgnoreCase("") == false)
			{
				String [] organizationArr = organization.split(",");
				
			
				for(int i=0;i<organizationArr.length;i++)
				{
					alOrg.add("%"+organizationArr[i]+"%");
				}
			}
			
			if(alOrg != null && alOrg.size() != 0)
				hqlFilter.addOrCondGroup("owner", HqlFilter.Operator.LIKE, alOrg);
	
			hqlFilter.setSort("uorder");
			
	        List<SysUser> listObj = sys_userService.findByFilter(hqlFilter);
	        JSONArray jsonArr = new JSONArray();
	        int iTotalCnt = 0;
			for(int i=0;i<listObj.size();i++)
			{
				SysUser sys_user = listObj.get(i);
				JSONObject jsonTmp = new JSONObject();
				jsonTmp.put("id", sys_user.getId());
				jsonTmp.put("name",sys_user.getname());
				jsonTmp.put("loginid",sys_user.getloginid());
				jsonTmp.put("password",sys_user.getpassword());
				jsonTmp.put("gender",sys_user.getgender());
				if(sys_user.getbirthday() != null)
					jsonTmp.put("birthday",TimeUtil.formatDate(sys_user.getbirthday(),"yyyy-MM-dd"));
				else
					jsonTmp.put("birthday","-");
				
				if(sys_user.getjoinday() != null)
					jsonTmp.put("joinday",TimeUtil.formatDate(sys_user.getjoinday(),"yyyy-MM-dd"));
				else
					jsonTmp.put("joinday","-");
				
				jsonTmp.put("mobile",sys_user.getmobile());
				//jsonTmp.put("department",sys_user.getdepartment());
				jsonTmp.put("job",sys_user.getjob());
				jsonTmp.put("role",sys_user.getrole());
				
				String roleTxt = "";
				
				if(sys_user.getrole() != null)
				{
					String [] roleArr = sys_user.getrole().split(",");
					
					for(int j=0;j<roleArr.length;j++)
					{
						String roleId = roleArr[j];
						
						if(ConstValue.roleMap.containsKey(roleId))
						{
							roleTxt += ConstValue.roleMap.get(roleId)+",";
						}
					}
				}
				
				if(roleTxt.endsWith(","))
					roleTxt = roleTxt.substring(0, roleTxt.length() - 1);
				
				jsonTmp.put("status",sys_user.getstatus());
				jsonTmp.put("roleTxt",roleTxt);
				
				String orgIds = "";//ConstValue.userToOrgMap.get(sys_user.getId());
				String orgNames = "";
				
				
				HqlFilter hqlFilterUser = new HqlFilter();
				hqlFilterUser.addQryCond("user", HqlFilter.Operator.EQ, sys_user.getId());
				List<SysUserOrganization> userOrgList = sys_userOrgService.findByFilter(hqlFilterUser);
				
				if(userOrgList.size() == 0)//当前用户不属于要查询的组织
					continue;
				
				for(int j=0;j<userOrgList.size();j++)
				{
					SysUserOrganization userOrg = userOrgList.get(j);
					
					String orgId = userOrg.getorganization();
					
					String orgName = ConstValue.orgMap.get(orgId);
					
					orgIds += orgId + ",";
					orgNames += orgName + ",";
				}
				
				if(orgIds != null && orgIds.endsWith(","))
				{
					orgIds = orgIds.substring(0, orgIds.length() - 1);
					orgNames = orgNames.substring(0, orgNames.length() - 1);
				}
				
				jsonTmp.put("orgIds", orgIds);
				jsonTmp.put("orgNames", orgNames);
	
	       		jsonArr.put(jsonTmp);
	        	iTotalCnt++;
			}
	        jsonObj.put("totalCount", iTotalCnt);
	        jsonObj.put("list", jsonArr);*/
	        jsonObj.put("success", true);
		}
		catch(Exception e)
		{
			logger.error(e.getMessage(),e);
			jsonObj.put("success", false);
		}
	    return jsonObj.toString();
	}
}
