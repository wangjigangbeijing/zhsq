package com.template.controller;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.text.NumberFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.template.util.ConstValue;
import com.template.util.HqlFilter;
import com.template.util.TimeUtil;
import com.template.util.Utility;
import com.template.model.SysUser;
import com.template.service.UserService;

@Controller
@RequestMapping(ConstValue.USER_CONTROLLER)
public class UserController {
	
	private static Logger logger = Logger.getLogger(UserController.class);
	
	@Autowired
	private  HttpServletRequest request;
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value=ConstValue.USER_CONTROLLER_ADD_OR_UPDATE_USER,method = RequestMethod.POST,produces="text/html;charset=UTF-8")
    @ResponseBody
	public String addOrUpdateUser(String userId,String userName,String password,String imageFileName,
			String mobile,String loginId,String status)
	{
		logger.debug("addOrUpdateUser");
    	JSONObject jsonObj = new JSONObject();
    	
		try
		{
			HqlFilter hqlFilterByOfficerName = new HqlFilter();
			hqlFilterByOfficerName.addQryCond("loginId", HqlFilter.Operator.EQ, loginId);
			List<SysUser> listOfficer = userService.findByFilter(hqlFilterByOfficerName);
			
			if(userId == null || userId.equalsIgnoreCase(""))//新增用户需要做重名判断
			{
				if(listOfficer.size() != 0)
				{
					jsonObj.put("success", false);
					jsonObj.put("errMsg", "用户名已存在");
					return jsonObj.toString();
				}
			}
			
			SysUser sysUser = new SysUser();
			if(userId == null || userId.equalsIgnoreCase(""))
			{
				userId = Utility.getUniStr();
				
				sysUser.setId(userId);
				
			}
			else
			{
				sysUser = userService.getById(userId);
			}
			sysUser.setStatus(status);
			sysUser.setMobile(mobile);
			sysUser.setLoginId(loginId);
			sysUser.setPassword(password);
			
			sysUser.setUsername(userName);
			userService.saveOrUpdate(sysUser);
			
			jsonObj.put("success", true);
		}
		catch(Exception e)
		{
			logger.error(e.getMessage(),e);
			jsonObj.put("success", false);
			jsonObj.put("errMsg", "创建用户失败,请联系系统管理员");
		}
        return jsonObj.toString();
    }
	
	
	
	@RequestMapping(value=ConstValue.USER_CONTROLLER_DELETE_USER,method = RequestMethod.POST,produces="text/html;charset=UTF-8")
    @ResponseBody
	public String deleteUser(String userId)
	{
		logger.debug("deleteUser "+userId);
    	JSONObject jsonObj = new JSONObject();
    	
		try
		{
			HqlFilter hqlFilter = new HqlFilter();
			
			if(userId != null && userId.equalsIgnoreCase("") == false && userId.equalsIgnoreCase("null") == false)//正常的情况下,group_user字段里是两个user_id
			{
				hqlFilter.addQryCond("group_user", HqlFilter.Operator.LIKE, "%"+userId+"%");
			}
			
			HqlFilter hqlFilterProject = new HqlFilter();
			if(userId != null && userId.equalsIgnoreCase("") == false && userId.equalsIgnoreCase("null") == false)//正常的情况下,group_user字段里是两个user_id
			{
				hqlFilterProject.addQryCond("project_manager", HqlFilter.Operator.LIKE, "%"+userId+"%");
			}
			SysUser user = userService.getById(userId);
			
			String sMobilie = user.getMobile();
			
			userService.delete(user);
			
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
	
	
	
	@RequestMapping(value=ConstValue.USER_CONTROLLER_LOAD_USER,method = RequestMethod.GET,produces="text/html;charset=UTF-8")
    @ResponseBody
	public String loadUser(String userName,String mobile,String hireDate)
	{
		JSONObject jsonObj = new JSONObject();
    	
		try
		{
			
			HqlFilter hqlFilter = new HqlFilter();
			if(userName != null && userName.equalsIgnoreCase("") == false)
				hqlFilter.addQryCond("username", HqlFilter.Operator.LIKE, "%"+userName+"%");
			if(mobile != null && mobile.equalsIgnoreCase("") == false)
				hqlFilter.addQryCond("mobile", HqlFilter.Operator.LIKE, "%"+mobile+"%");
			if(hireDate != null && hireDate.equalsIgnoreCase("") == false)
				hqlFilter.addQryCond("hireDate", HqlFilter.Operator.LIKE, hireDate+"%");
			
	        List<SysUser> listObj = userService.findByFilter(hqlFilter);
			
	        JSONArray jsonArr = new JSONArray();
	        
	        int iTotalCnt = 0;
			for(int i=0;i<listObj.size();i++)
			{
				SysUser user = listObj.get(i);
				
				JSONObject jsonTmp = new JSONObject();
				
				jsonTmp.put("id", user.getId());
				jsonTmp.put("loginId", user.getLoginId());
				jsonTmp.put("mobile", user.getMobile());
				jsonTmp.put("status", user.getStatus());
				jsonTmp.put("userName", user.getUsername());
				
	        	jsonArr.put(jsonTmp);
	        	
	        	iTotalCnt++;
			}
	        
	        jsonObj.put("totalCount", iTotalCnt);
	        jsonObj.put("userList", jsonArr);
	        
	        jsonObj.put("success", true);
		}
		catch(Exception e)
		{
			logger.error(e.getMessage(),e);
			jsonObj.put("success", false);
		}
        return jsonObj.toString();
    }
	
	@RequestMapping(value=ConstValue.USER_CONTROLLER_GET_USER,method = {RequestMethod.POST,RequestMethod.GET},produces="text/html;charset=UTF-8")
    @ResponseBody
	public String getUser(String userId)
	{
		logger.debug("getUser");
    	JSONObject jsonObj = new JSONObject();
    	
		try
		{
			//String sUser = (String)request.getSession().getAttribute(ConstValue.SESSION_USER_NAME);
			
			SysUser user = userService.getById(userId);
			
			if(user != null)
			{
				jsonObj.put("success", true);
				jsonObj.put("userName", user.getUsername());
				jsonObj.put("loginId", user.getLoginId());
				jsonObj.put("mobile", user.getMobile());
				jsonObj.put("password", user.getPassword());
				jsonObj.put("status", user.getStatus());
			}
			else
			{
				logger.error("user is not found:"+userId+"...");
				
				jsonObj.put("success", false);
				jsonObj.put("errMsg", "User can not found...");
			}
		}
		catch(Exception e)
		{
			logger.error(e.getMessage(),e);
			jsonObj.put("success", false);
		}
        return jsonObj.toString();
    }
	
	
	/*
	@RequestMapping(value=ConstValue.USER_CONTROLLER_GET_USER_IMAGE,method = RequestMethod.GET,produces="text/html;charset=UTF-8") 
	@ResponseBody
	public void getOfficerImage(String officerId,HttpServletRequest request, HttpServletResponse response)
	{  
		OutputStream os = null;
		try
		{
			SysUser officer = userService.getById(officerId);
			
			String imgFile = officer.getImage();
			
			String sURL = request.getRequestURI();
			sURL = java.net.URLDecoder.decode(sURL,"UTF-8");
						
			os = response.getOutputStream();
            response.reset();  
            //response.setHeader("Content-Disposition", "attachment; filename=" + fileName);  
            
            if(imageDir.endsWith("/") == false)
            	imageDir += "/";
            
			String sCImgName = imgFile.substring(0, imgFile.length()-4)+"_C.png";//缩略图文件名称,直接返回给客户端,客户端在用户点击缩略图的时候访问不带_C路径    如20160101001_C.png
			
			File fileC = new File(imageDir+sCImgName);
			if(fileC.exists() == false)
			{
				ImgCompress narrowImage = new ImgCompress();  
		        narrowImage.writeHighQuality(narrowImage.zoomImage(imageDir+imgFile), imageDir+sCImgName);  
			}
			
            os.write(FileUtils.readFileToByteArray(new File(imageDir+sCImgName)));  
            os.flush();
        } catch (Exception e) {
			//图片查找失败,使用默认图片
        	try
        	{
	        	String imgFile = "man.png";
	        	String sURL = request.getRequestURI();
				sURL = java.net.URLDecoder.decode(sURL,"UTF-8");
							
				os = response.getOutputStream();
	            response.reset();  
	            //response.setHeader("Content-Disposition", "attachment; filename=" + fileName);  
	            
	            if(imageDir.endsWith("/") == false)
	            	imageDir += "/";
	            
				String sCImgName = imgFile.substring(0, imgFile.length()-4)+"_C.png";//缩略图文件名称,直接返回给客户端,客户端在用户点击缩略图的时候访问不带_C路径    如20160101001_C.png
				
				File fileC = new File(imageDir+sCImgName);
				if(fileC.exists() == false)
				{
					ImgCompress narrowImage = new ImgCompress();  
			        boolean bSucceed = narrowImage.writeHighQuality(narrowImage.zoomImage(imageDir+imgFile), imageDir+sCImgName);  
			        
			        if(bSucceed == false)
			        	sCImgName = imgFile;
				}
				
	            os.write(FileUtils.readFileToByteArray(new File(imageDir+sCImgName)));  
	            os.flush();
        	}
        	catch(Exception ex)
        	{
        		
        	}        	
		} 
        finally {
			try {
            	if (os != null) os.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
        }  
    }  */
}
