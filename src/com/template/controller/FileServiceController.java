package com.template.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;



import java.util.Date;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.template.util.ConstValue;
import com.template.util.TimeUtil;
import com.template.util.Utility;

@Controller
@RequestMapping(ConstValue.FILE_CONTROLLER)
public class FileServiceController {

	@Value("#{propertiesReader['tmpDir']}")
	private String tmpDir = "";
	
	@Value("#{propertiesReader['sysDir']}")
	private String sysDir = "";
	
	@Autowired
	private  HttpServletRequest request;
	
	private static Logger logger = Logger.getLogger(FileServiceController.class);
	
	@RequestMapping(value=ConstValue.FILE_UPLOAD,method=RequestMethod.POST,produces="text/html;charset=UTF-8")
    @ResponseBody
	public String uploadFile(MultipartHttpServletRequest multipartRequest)
	{
		logger.debug("uploadFile");
		JSONObject jsonRet = new JSONObject();
		
		String fileName = "";
		try
		{
			Map paraMap = multipartRequest.getParameterMap();
			
			if(paraMap.containsKey("attributeName") == true)
			{
				/*logger.error("Request does not have attribtueName");
				jsonRet.put("success", false);
				jsonRet.put("errMsg", "Request does not have attribtueName");
				
				return jsonRet.toString();*/
				
				jsonRet.put("attributeName",paraMap.get("attributeName"));
			}
			
			for (Iterator it = multipartRequest.getFileNames(); it.hasNext();) //页面限制只上传一个文件
			{
				String id = (String) it.next();
		        MultipartFile file = multipartRequest.getFile(id);
		        if (file.getOriginalFilename().length() > 0) 
		        {
		        	String originalFileName = file.getOriginalFilename();  
		        	
		        	//fileName = Utility.getUniStr()+"~"+originalFileName;
		        	fileName = TimeUtil.formatDate(new Date(), "yyyyMMddHHmmss")+"~"+originalFileName;
		        	
	                boolean bRet = Utility.saveFileFromInputStream(file.getInputStream(), tmpDir, fileName);
		        }	
			}
			jsonRet.put("success", true);
			jsonRet.put("fileName", fileName);
		}
		catch (Exception e) 
		{
			logger.error(e.getMessage(),e);
			jsonRet.put("success", false);
			jsonRet.put("errMsg", e.getMessage());
		}
		return jsonRet.toString();
	}
	

	@RequestMapping(value=ConstValue.FILE_DOWNLOAD,produces="text/html;charset=UTF-8") 
	@ResponseBody
	public void download(String fileName,HttpServletResponse response)
	{  
		logger.debug("download 	"+fileName);
		OutputStream os = null;
		try
		{	
			os = response.getOutputStream();
            response.reset();  
            response.setHeader("Content-Disposition", "attachment; filename=" + fileName);  
            
            if(tmpDir.endsWith("/") == false)
            	tmpDir += "/";
            
            String sFileName = tmpDir+fileName;
            
            os.write(FileUtils.readFileToByteArray(new File(sFileName)));  
            os.flush();
        } catch (IOException e) {
			e.printStackTrace();
		} 
        finally {
			try {
            	if (os != null) os.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
        }  
    }  
	
	

	@RequestMapping(value="downloadIcon",produces="text/html;charset=UTF-8") 
	@ResponseBody
	public void downloadIcon(String fileName,HttpServletResponse response)
	{  
		logger.debug("download 	"+fileName);
		OutputStream os = null;
		try
		{	
			os = response.getOutputStream();
            response.reset();  
            response.setHeader("Content-Disposition", "attachment; filename=" + fileName);  
            
            String pjtPath = request.getSession().getServletContext().getRealPath("");
            
            if(sysDir.endsWith("/") == false)
            	sysDir += "/";
            
            String sFileName = sysDir+fileName;
            
            os.write(FileUtils.readFileToByteArray(new File(sFileName)));  
            os.flush();
        } catch (IOException e) {
			e.printStackTrace();
		} 
        finally {
			try {
            	if (os != null) os.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
        }  
    }  
}
