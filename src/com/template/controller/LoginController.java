package com.template.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.servlet.ModelAndView;




import com.template.util.EncryptUtil;
import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.Producer;
import com.template.model.SysOrganization;
import com.template.model.SysUser;
import com.template.model.SysUserOrganization;
import com.template.service.SysOrganizationService;
import com.template.service.SysUserOrganizationService;
import com.template.service.SysUserService;
import com.template.util.ConstValue;
import com.template.util.HqlFilter;
import com.template.util.TimeUtil;
import com.template.util.Utility;

@Controller
@RequestMapping(ConstValue.LOGIN_CONTROLLER)
public class LoginController {
	
	private static Logger logger = Logger.getLogger(LoginController.class);
	
	@Autowired
	private  HttpServletRequest request;
	
	@Autowired
	private SysUserService userService;

	
	@Autowired
	private SysUserOrganizationService userOrganizationService;
	
	@Autowired
	private SysOrganizationService organizationService;
	
	@RequestMapping(value=ConstValue.LOGIN_CONTROLLER_LOGIN,method = RequestMethod.POST,produces="text/html;charset=UTF-8")
    @ResponseBody
	public String login(String username,String password,String timestamp,
			String appVersion,String deviceId,String mobileModel,String osVersion)
	{
		logger.info(username+" is trying to login");
		
		logger.debug("appVersion:	"+appVersion);
		logger.debug("deviceId:		"+deviceId);
		logger.debug("mobileModel:	"+mobileModel);
		logger.debug("osVersion:	"+osVersion);
		
    	JSONObject jsonObj = new JSONObject();
    	
		try
		{
			if(username.equalsIgnoreCase("admin") && password.equalsIgnoreCase("zhsq2020"))
			{
				request.getSession().setAttribute(ConstValue.SESSION_USER_TYPE, ConstValue.USER_TYPE_ADMIN);
				request.getSession().setAttribute(ConstValue.SESSION_USER_NAME, username);
				request.getSession().setAttribute(ConstValue.SESSION_USER_ID, "admin");
				request.getSession().setAttribute(ConstValue.SESSION_USER_ORG, "");
				
				jsonObj.put("border", "116.3713932,39.828271,116.4296722,39.8599433");
				jsonObj.put("userType", ConstValue.USER_TYPE_ADMIN);	
				jsonObj.put("userName", "管理员");	
				jsonObj.put("success", true);	
				jsonObj.put("homePage", "/app/homepage.html");
				
				return jsonObj.toString();
			}
			
			if(username.equalsIgnoreCase("dhm") && password.equalsIgnoreCase("zhsq2020"))
			{
				request.getSession().setAttribute(ConstValue.SESSION_USER_TYPE, ConstValue.USER_TYPE_ADMIN);
				request.getSession().setAttribute(ConstValue.SESSION_USER_NAME, username);
				request.getSession().setAttribute(ConstValue.SESSION_USER_ID, "7a004f74-7862-4384-92c9-75426e33467f");
				request.getSession().setAttribute(ConstValue.SESSION_USER_ORG, "");
				
				jsonObj.put("border", "116.3713932,39.828271,116.4296722,39.8599433");
				jsonObj.put("userType", ConstValue.USER_TYPE_ADMIN);	
				jsonObj.put("userName", "大红门街道管理员");	
				jsonObj.put("success", true);	
				jsonObj.put("organizations", "d216599c-7b85-48ab-8e49-049178f5a285");
				jsonObj.put("organizationNames", "大红门街道办事处");
				jsonObj.put("homePage", "/app/homepage.html");
				
				return jsonObj.toString();
			}
			
			HqlFilter hqlFilter = new HqlFilter();
			hqlFilter.addQryCond("loginid", HqlFilter.Operator.EQ, username);
			//hqlFilter.addQryCond("password", HqlFilter.Operator.EQ, password);
			
			ArrayList<String> alPassword = new ArrayList<String>();
			alPassword.add(password);
			alPassword.add(EncryptUtil.encodeStr(password));
			
			hqlFilter.addOrCondGroup("password", HqlFilter.Operator.EQ, alPassword);
			
			//hqlFilter.addQryCond("status", HqlFilter.Operator.NEQ, ConstValue.USER_STATUS_DISABLED);
			List<SysUser> userInfoList = userService.findByFilter(hqlFilter);

			if(userInfoList.size() == 0)
			{
				jsonObj.put("success", false);	
				jsonObj.put("errMsg", "无效的用户名或密码");
				
				logger.info("invalid username or password:"+username+"	"+password);
				
				return jsonObj.toString();
			}
			else
			{
				SysUser sysUser = userInfoList.get(0);
				
				//Date expireTime = sysUser.getExpireTime();
				
				/*Date currentDate = new Date();
				
				if(expireTime != null && expireTime.getTime() < currentDate.getTime())
				{
					jsonObj.put("success", false);	
					jsonObj.put("errMsg", "用户已过期");
					
					return jsonObj.toString();
				}
				*/
				jsonObj.put("success", true);	
				jsonObj.put(ConstValue.HTTP_HEADER_USERID, sysUser.getId());	
				jsonObj.put("token", sysUser.getId());	
				jsonObj.put("homePage", "/app/homepage.html");
				jsonObj.put("userName", sysUser.getname());
				//jsonObj.put("mobile", sysUser.getMobile());
				//jsonObj.put("nickName", sysUser.getUsername());
				
				HqlFilter hqlFilterOrganzation = new HqlFilter();
				hqlFilterOrganzation.addQryCond("user", HqlFilter.Operator.EQ, sysUser.getId());
				
				List<SysUserOrganization> userOrgList = userOrganizationService.findByFilter(hqlFilterOrganzation);

				String organizations = "";
				String organizationNames = "";
				String border = "";
				for(int i=0;i<userOrgList.size();i++)
				{
					String orgid = userOrgList.get(i).getorganization();
					
					SysOrganization organization = getCommunityOrgByOrgId(orgid);//organizationService.getById(orgid);
					
					if(organization == null)
						continue;
					
					organizationNames  = organization.getname();
					organizations = organization.getId();
					border = organization.getboundary();
					if(organization.getorg_type() != null && organization.getorg_type().equalsIgnoreCase("社区"))
					{
						break;
					}					
				}
				
				//if(organizations.endsWith(","))
				//	organizations = organizations.substring(0, organizations.length() - 1);
				
				jsonObj.put("organizations", organizations);
				jsonObj.put("organizationNames", organizationNames);
				jsonObj.put("border", border);
				
				request.getSession().setAttribute(ConstValue.SESSION_USER_ORG, organizations);
				
				request.getSession().setAttribute(ConstValue.SESSION_USER_ID, userInfoList.get(0).getId());
				
				request.getSession().setAttribute(ConstValue.SESSION_USER_NAME, username);
				
				String sSource = request.getHeader(ConstValue.HTTP_HEADER_SOURCE);//app
				
				request.getSession().setAttribute(ConstValue.SESSION_USER_TYPE, ConstValue.USER_TYPE_COMMUNITY);
				
				if(sSource != null && sSource.equalsIgnoreCase(ConstValue.HTTP_HEADER_SOURCE_APP))//用户从APP端登陆
				{
					logger.info("login success from app:"+username);
					
					/*String logoutMessage = "{\"type\":\"system\",\"action\":\"logout\",\"content\":\"\",\"timestamp\":\""+timestamp+"\"}";
					
					String logoutTarget = sysUser.getMobile();
					
					//if(workMode != null && workMode.equalsIgnoreCase("RTK双模") )
					if(rtkmode != null && rtkmode.equalsIgnoreCase("1") )
					{
						logoutTarget = "rtk"+logoutTarget;
					}
					new LocalUDPDataSender.SendCommonDataAsync(logoutMessage, logoutTarget)
					{
						@Override
						protected void onPostExecute(Integer code)
						{
							System.out.println(code);
						}
					}.execute();  
					*/

					sysUser.setversion(appVersion);
					sysUser.setdeviceid(deviceId);
					sysUser.setmobilemodel(mobileModel);
					sysUser.setosversion(osVersion);
					sysUser.setlastlogintime(TimeUtil.formatDate(new Date()));
					
					userService.save(sysUser);
				}
				
				jsonObj.put("success", true);	
			}
			
		}
		catch(Exception e)
		{
			logger.error(e.getMessage(),e);
			jsonObj.put("success", false);
			jsonObj.put("errMsg", "Excpetion during login");
		}
		finally
		{
			
		}
		
        return jsonObj.toString();
    }
	
	private SysOrganization getCommunityOrgByOrgId(String orgId)
	{
		SysOrganization organization = organizationService.getById(orgId);
		
		if(organization != null && organization.getorg_type() != null && organization.getorg_type().equalsIgnoreCase("社区"))
		{
			return organization;
		}
		else
		{
			String parentId = organization.getparentid();
			
			if(parentId == null || parentId.equalsIgnoreCase(""))
				return organization;
			else
				return getCommunityOrgByOrgId(parentId);
		}
	}
	
	@RequestMapping(value=ConstValue.LOGIN_CONTROLLER_LOGOUT,method = RequestMethod.GET,produces="text/html;charset=UTF-8")
    @ResponseBody
    public String logout()
	{
		logger.info("logout.......");
		
		int port = request.getServerPort();
		String ip = request.getServerName();
		
		request.getSession().removeAttribute(ConstValue.SESSION_USER_ID);
		
    	return ip+":"+port;
    }
	
	@RequestMapping(value=ConstValue.LOGIN_CONTROLLER_GET_CURRENT_LOGIN_USER_INFO,method = RequestMethod.GET,produces="text/html;charset=UTF-8")
    @ResponseBody
    public String getCurrentLoginUserInfo()
	{
		JSONObject jsonObj = new JSONObject();
		
		try
		{
			String sUserId = (String)request.getSession().getAttribute(ConstValue.SESSION_USER_ID);
			String sUserName = (String)request.getSession().getAttribute(ConstValue.SESSION_USER_NAME);
			Integer userType = (Integer)request.getSession().getAttribute(ConstValue.SESSION_USER_TYPE);
			String sOrgid = (String)request.getSession().getAttribute(ConstValue.SESSION_USER_ORG);
			
			jsonObj.put("userOrg", sOrgid);
			jsonObj.put("userId", sUserId);
			jsonObj.put("userType", userType);
			jsonObj.put("userName", sUserName);
			
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
			
			if(sUserId.equalsIgnoreCase("admin") || sUserId.equalsIgnoreCase("7a004f74-7862-4384-92c9-75426e33467f"))
				sSql = "SELECT RIGHTID,RIGHTTYPE FROM SYS_RIGHT";
				
			List<HashMap> listObj = organizationService.findBySql(sSql);
			
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
		}
		
    	return jsonObj.toString();
    }
	
	
	@RequestMapping(value="checkSessionStatus",method = RequestMethod.GET,produces="text/html;charset=UTF-8")
    @ResponseBody
    public String checkSessionStatus()
	{
		logger.debug("checkSessionStatus");
		JSONObject jsonObj = new JSONObject();
		
		try
		{
			if(request.getSession().getAttribute(ConstValue.SESSION_USER_ID) == null)
			{
				jsonObj.put("status", false);
			}
			else
			{
				jsonObj.put("status", true);
			}
		}
		catch(Exception e)
		{
			logger.error(e.getMessage(),e);
		}
		
    	return jsonObj.toString();
    }
}
