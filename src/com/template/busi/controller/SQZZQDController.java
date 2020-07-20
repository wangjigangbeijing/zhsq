package com.template.busi.controller;

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
import com.template.model.SQZZQD;
import com.template.model.SysUser;
import com.template.service.SQZZQDService;
import com.template.service.UserService;

@Controller
@RequestMapping("sqzzqdController")
public class SQZZQDController {
	
	private static Logger logger = Logger.getLogger(SQZZQDController.class);
	
	@Autowired
	private  HttpServletRequest request;
	
	@Autowired
	private SQZZQDService sqzzqdService;
	
	@RequestMapping(value="addOrUpdate",method = RequestMethod.POST,produces="text/html;charset=UTF-8")
    @ResponseBody
	public String addOrUpdate(String id,String bh,String zzly,String zzy,String gzzz,
			String lb,String yjflfgmc,String fj)
	{
		logger.debug("addOrUpdate");
    	JSONObject jsonObj = new JSONObject();
    	
		try
		{
			SQZZQD sqzzqd = new SQZZQD();
			
			if(id != null && id.equalsIgnoreCase("") == false)
			{
				sqzzqd = sqzzqdService.getById(id);
			}
			else
			{
				sqzzqd.setId(Utility.getUniStr());
			}
			
			sqzzqd.setbh(bh);
			sqzzqd.setzzly(zzly);
			sqzzqd.setzzy(zzy);
			sqzzqd.setgzzz(gzzz);
			sqzzqd.setlb(lb);
			sqzzqd.setyjflfgmc(yjflfgmc);
			sqzzqd.setFJ(fj);
			
			sqzzqdService.saveOrUpdate(sqzzqd);
			
			jsonObj.put("success", true);
		}
		catch(Exception e)
		{
			logger.error(e.getMessage(),e);
			jsonObj.put("success", false);
			jsonObj.put("errMsg", "创建职责清单 失败,请联系系统管理员");
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
			SQZZQD sqzzqd = sqzzqdService.getById(id);
			
			sqzzqdService.delete(sqzzqd);
			
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
	public String load(String gzzz,String zzy,String lb)
	{
		JSONObject jsonObj = new JSONObject();
    	
		try
		{
			HqlFilter hqlFilter = new HqlFilter();
			if(zzy != null && zzy.equalsIgnoreCase("") == false)
				hqlFilter.addQryCond("gzzz", HqlFilter.Operator.LIKE, "%"+gzzz+"%");
			
			if(zzy != null && zzy.equalsIgnoreCase("") == false)
				hqlFilter.addQryCond("zzy", HqlFilter.Operator.EQ, zzy);
			if(lb != null && lb.equalsIgnoreCase("") == false)
				hqlFilter.addQryCond("lb", HqlFilter.Operator.EQ, lb);
			
	        List<SQZZQD> listObj = sqzzqdService.findByFilter(hqlFilter);
			
	        JSONArray jsonArr = new JSONArray();
	        
	        int iTotalCnt = 0;
			for(int i=0;i<listObj.size();i++)
			{
				SQZZQD sqzzqd = listObj.get(i);
				
				JSONObject jsonTmp = new JSONObject();
				
				jsonTmp.put("id", sqzzqd.getId());
				jsonTmp.put("bh", sqzzqd.getbh());
				jsonTmp.put("zzly", sqzzqd.getzzly());
				jsonTmp.put("zzy", sqzzqd.getzzy());
				jsonTmp.put("lb", sqzzqd.getlb());
				
				String gzzzTxt = sqzzqd.getgzzz();
				String gzzzShort = gzzzTxt;
				
				if(gzzzShort != null && gzzzShort.length() > 10)
					gzzzShort = gzzzShort.substring(0, 10) + "...";
				
				jsonTmp.put("gzzz", sqzzqd.getgzzz());
				jsonTmp.put("gzzzShort", gzzzShort);
				
				String yjflfgmcTxt = sqzzqd.getyjflfgmc();
				String yjflfgmcShort = yjflfgmcTxt;
				
				if(yjflfgmcShort != null && yjflfgmcShort.length() > 10)
					yjflfgmcShort = yjflfgmcShort.substring(0, 10) + "...";
				
				jsonTmp.put("yjflfgmc", sqzzqd.getyjflfgmc());
				jsonTmp.put("yjflfgmcShort", yjflfgmcShort);
				
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
		logger.debug("get");
    	JSONObject jsonObj = new JSONObject();
    	
		try
		{
			SQZZQD sqzzqd = sqzzqdService.getById(id);
			
			if(sqzzqd != null)
			{
				jsonObj.put("success", true);
				jsonObj.put("id", sqzzqd.getId());
				jsonObj.put("bh", sqzzqd.getbh());
				jsonObj.put("zzly", sqzzqd.getzzly());
				jsonObj.put("zzy", sqzzqd.getzzy());
				jsonObj.put("lb", sqzzqd.getlb());
				jsonObj.put("gzzz", sqzzqd.getgzzz());
				jsonObj.put("yjflfgmc", sqzzqd.getyjflfgmc());
				jsonObj.put("fj", sqzzqd.getFJ());
			}
			else
			{
				jsonObj.put("success", false);
				jsonObj.put("errMsg", "未找到社区职责清单...");
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
