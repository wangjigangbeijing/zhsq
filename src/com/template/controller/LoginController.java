package com.template.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.servlet.ModelAndView;





import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.Producer;
import com.template.util.ConstValue;
import com.template.util.HqlFilter;

@Controller
@RequestMapping(ConstValue.LOGIN_CONTROLLER)
public class LoginController {
	
	private static Logger logger = Logger.getLogger(LoginController.class);
	
	@Autowired
	private  HttpServletRequest request;
	
	@RequestMapping(value=ConstValue.LOGIN_CONTROLLER_LOGIN,method = RequestMethod.POST,produces="text/html;charset=UTF-8")
    @ResponseBody
	public String login(String username,String password)
	{
    	JSONObject jsonObj = new JSONObject();
    	
		try
		{
			if(username.equalsIgnoreCase("admin") && password.equalsIgnoreCase("admin"))
			{
				request.getSession().setAttribute(ConstValue.SESSION_USER_TYPE, ConstValue.USER_TYPE_ADMIN);
				request.getSession().setAttribute(ConstValue.SESSION_USER_NAME, username);
				request.getSession().setAttribute(ConstValue.SESSION_USER_ID, "admin");
				jsonObj.put("userType", ConstValue.USER_TYPE_ADMIN);	
				jsonObj.put("userName", "管理员");	
				jsonObj.put("success", true);	
				jsonObj.put("homePage", "/app/homepage.html");
				
				return jsonObj.toString();
			}
			jsonObj.put("success", false);	
			jsonObj.put("errMsg", "用户名或者密码错误");
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
			
			jsonObj.put("userId", sUserId);
			jsonObj.put("userType", userType);
			jsonObj.put("userName", sUserName);
		}
		catch(Exception e)
		{
			logger.error(e.getMessage(),e);
		}
		
    	return jsonObj.toString();
    }
	
}
