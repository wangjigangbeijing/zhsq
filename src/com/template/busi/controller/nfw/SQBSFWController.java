package com.template.busi.controller.nfw;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigInteger;
import java.text.NumberFormat;
import java.util.ArrayList;
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
import com.template.service.SysUserService;

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
	public String addOrUpdate(String id,String blr, String blrname,
			String lxdh,
			String blqd,
			String blsxdl,
			String blsxxl,
			String xq,
			String bz)
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
			
			sqbsfw.setblr(blr);
			sqbsfw.setBlrname(blrname);
			sqbsfw.setlxdh(lxdh);
			sqbsfw.setblqd(blqd);
			sqbsfw.setblsxdl(blsxdl);
			sqbsfw.setblsxxl(blsxxl);
			sqbsfw.setxq(xq);
			sqbsfw.setbz(bz);
			
			sqbsfwService.saveOrUpdate(sqbsfw);
			
			jsonObj.put("success", true);
			jsonObj.put("dataid", sqbsfw.getId());
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
			String sql = "select a.*, (select status from fw_flowdatainfo where dataid=a.id order by inserttime desc LIMIT 0, 1) as status from nfw_sqbsfw a";
			List<HashMap> list = this.sqbsfwService.findBySql(sql);
//			HqlFilter hqlFilter = new HqlFilter();
//        List<JSJBFW> listObj = jsjbfwService.findByFilter(hqlFilter);
        //net.sf.json.JSONArray jsonArr = net.sf.json.JSONArray.fromObject(list);
        jsonObj.put("totalCount", list.size());
        jsonObj.put("list", list);
        jsonObj.put("success", true);
	}
	catch(Exception e)
	{
		logger.error(e.getMessage(),e);
		jsonObj.put("success", false);
	}
    return jsonObj.toString();
    }
	
	@RequestMapping(value="loadsxxl",method = RequestMethod.GET,produces="text/html;charset=UTF-8")
    @ResponseBody
	public String loadSxxl()
	{
		logger.info("load sxxl");
		
		JSONObject jsonObj = new JSONObject();
		try
		{
			String sql = "select a.* from nfw_sqbsfw_qd a";
			List<HashMap> list = this.sqbsfwService.findBySql(sql);
//			HqlFilter hqlFilter = new HqlFilter();
//        List<JSJBFW> listObj = jsjbfwService.findByFilter(hqlFilter);
        //net.sf.json.JSONArray jsonArr = net.sf.json.JSONArray.fromObject(list);
        jsonObj.put("totalCount", list.size());
        jsonObj.put("list", list);
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
			String sql = "select a.*, (select status from fw_flowdatainfo where dataid=a.id order by inserttime desc LIMIT 0, 1) as status from nfw_sqbsfw a where id=?";
			List<Object> params = new ArrayList<Object>();
			params.add(id);
			List<HashMap> list = this.sqbsfwService.findBySql(sql, params);
			
			if(list != null && list.size() > 0)
			{
				jsonObj.put("data",list.get(0));
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
