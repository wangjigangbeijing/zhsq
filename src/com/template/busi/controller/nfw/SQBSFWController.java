package com.template.busi.controller.nfw;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigInteger;
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
import com.template.model.nfw.SQBSFW;
import com.template.model.SysUser;
import com.template.service.nfw.SQBSFWService;
import com.template.service.UserService;

@Controller
@RequestMapping("sqbsfwController")
public class SQBSFWController {
	
	private static Logger logger = Logger.getLogger(SQBSFWController.class);
	
	@Autowired
	private  HttpServletRequest request;
	
	@Autowired
	private SQBSFWService sqbsfwService;
	
	@RequestMapping(value="addOrUpdate",method = RequestMethod.POST,produces="text/html;charset=UTF-8")
    @ResponseBody
	public String addOrUpdate(String id,String sxbm,
			String sxmc,
			String sxdl,
			String sxxl,
			String sxxq,
			String sxdd,
			String sxdsr, 
			String sxkssj,	
			String sxjssj,
			String sxzj, 
			String sxzp,
			String sxfj, 
			String sxjf, 
			String sxbz, 
			String sxzt)
	{
		logger.debug("addOrUpdate");
    	JSONObject jsonObj = new JSONObject();
    	
		try
		{
			SQBSFW sqbsfw = new SQBSFW();
			
			if(id != null && id.equalsIgnoreCase("") == false)
			{
				sqbsfw = sqbsfwService.getById(id);
			}
			else
			{
				sqbsfw.setId(Utility.getUniStr());
			}
			
			/*sqbsfw.setSXBM(sxbm);
			sqbsfw.setSXMC(sxmc);
			sqbsfw.setSXDL(sxdl);
			sqbsfw.setSXXL(sxxl);
			sqbsfw.setSXXQ(sxxq);
			sqbsfw.setSXDD(sxdd);
			sqbsfw.setSXDSR(sxdsr);
			sqbsfw.setSXKSSJ(sxkssj);
			sqbsfw.setSXJSSJ(sxjssj);
			sqbsfw.setSXZJ(sxzj);
			sqbsfw.setSXZP(sxzp);
			sqbsfw.setSXFJ(sxfj);
			sqbsfw.setSXKSSJ(sxkssj);
			sqbsfw.setSXJSSJ(sxjssj);
			//sqbsfw.setSXJF(Double.valueOf(sxjf));
			sqbsfw.setSXBZ(sxbz);
			sqbsfw.setSXZT(sxzt);*/
			
			sqbsfwService.saveOrUpdate(sqbsfw);
			
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
			SQBSFW sqbsfw = sqbsfwService.getById(id);
			
			sqbsfwService.delete(sqbsfw);
			
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
	public String load(String sxdl)
	{
		logger.info("load sxdl:"+sxdl);
		
		JSONObject jsonObj = new JSONObject();
    	
		try
		{
			HqlFilter hqlFilter = new HqlFilter();
			if(sxdl != null && sxdl.equalsIgnoreCase("") == false)
				hqlFilter.addQryCond("SXDL", HqlFilter.Operator.EQ, sxdl);
			
	        List<SQBSFW> listObj = sqbsfwService.findByFilter(hqlFilter);
			
	        JSONArray jsonArr = new JSONArray();
	        
	        int iTotalCnt = 0;
			for(int i=0;i<listObj.size();i++)
			{
				SQBSFW sqbsfw = listObj.get(i);
				
				JSONObject jsonTmp = new JSONObject();
				
				jsonTmp.put("id", sqbsfw.getId());
				/*jsonTmp.put("sxbm", sqbsfw.getSXBM());
				jsonTmp.put("sxbz", sqbsfw.getSXBZ());
				jsonTmp.put("sxdd", sqbsfw.getSXDD());
				jsonTmp.put("sxdl", sqbsfw.getSXDL());
				jsonTmp.put("sxdsr", sqbsfw.getSXDSR());
				jsonTmp.put("sxfj", sqbsfw.getSXFJ());
				jsonTmp.put("sxjf", sqbsfw.getSXJF());
				jsonTmp.put("sxjssj", sqbsfw.getSXJSSJ());
				jsonTmp.put("sxkssj", sqbsfw.getSXKSSJ());
				jsonTmp.put("sxmc", sqbsfw.getSXMC());
				jsonTmp.put("sxxl", sqbsfw.getSXXL());
				jsonTmp.put("sxxq", sqbsfw.getSXXQ());
				jsonTmp.put("sxzj", sqbsfw.getSXZJ());
				jsonTmp.put("sxzp", sqbsfw.getSXZP());
				jsonTmp.put("sxzt", sqbsfw.getSXZT());*/
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
			SQBSFW sqbsfw = sqbsfwService.getById(id);
			
			if(sqbsfw != null)
			{
				jsonObj.put("success", true);
				jsonObj.put("id", sqbsfw.getId());
				/*jsonObj.put("sxbm", sqbsfw.getSXBM());
				jsonObj.put("sxbz", sqbsfw.getSXBZ());
				jsonObj.put("sxdd", sqbsfw.getSXDD());
				jsonObj.put("sxdl", sqbsfw.getSXDL());
				jsonObj.put("sxdsr", sqbsfw.getSXDSR());
				jsonObj.put("sxfj", sqbsfw.getSXFJ());
				jsonObj.put("sxjf", sqbsfw.getSXJF());
				jsonObj.put("sxjssj", sqbsfw.getSXJSSJ());
				jsonObj.put("sxkssj", sqbsfw.getSXKSSJ());
				jsonObj.put("sxmc", sqbsfw.getSXMC());
				jsonObj.put("sxxl", sqbsfw.getSXXL());
				jsonObj.put("sxxq", sqbsfw.getSXXQ());
				jsonObj.put("sxzj", sqbsfw.getSXZJ());
				jsonObj.put("sxzp", sqbsfw.getSXZP());
				jsonObj.put("sxzt", sqbsfw.getSXZT());*/
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
	@RequestMapping(value="getSxsqsjBysxsl",method = RequestMethod.GET,produces="text/html;charset=UTF-8")
    @ResponseBody
	public String getSxsqsjBysxsl()
	{
		logger.info("getSxsqsjBysxsl");
		
		JSONObject jsonObj = new JSONObject();
    	
		try
		{
			String sSql = "SELECT COUNT(*) CNT,SXXL FROM SQBSFW GROUP BY SXXL";
			
	        List<HashMap> listObj = sqbsfwService.findBySql(sSql);
			
	        JSONArray jsonArr = new JSONArray();
	        
	        int iTotalCnt = 0;
			for(int i=0;i<listObj.size();i++)
			{
				HashMap hm = listObj.get(i);
				
				JSONObject jsonTmp = new JSONObject();
				
				jsonTmp.put("sxxl", (String)hm.get("SXXL"));
				jsonTmp.put("cnt", (BigInteger)hm.get("CNT"));
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
    }*/
}
