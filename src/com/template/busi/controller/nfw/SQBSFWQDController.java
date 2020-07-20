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
import com.template.model.nfw.SQBSFW_QD;
import com.template.model.SysUser;
import com.template.service.nfw.SQBSFWQDService;
import com.template.service.nfw.SQBSFWService;
import com.template.service.SysUserService;

@Controller
@RequestMapping("sqbsfwqdController")
public class SQBSFWQDController {
	
	private static Logger logger = Logger.getLogger(SQBSFWQDController.class);
	
	@Autowired
	private  HttpServletRequest request;
	
	@Autowired
	private SQBSFWQDService sqbsfwqdService;
	
	@RequestMapping(value="addOrUpdate",method = RequestMethod.POST,produces="text/html;charset=UTF-8")
    @ResponseBody
	public String addOrUpdate(
			String id,
			String sxmc,
			String sxlb,
			String fwdx,
			String ywzgbm,
			String blks,
			String sfyjjbz,
			String bltj,
			String bllc,
			String sqcl,
			String sltj,
			String pzxs,
			String qysj,
			String tysj,
			String bz,
			String fj)
	{
		logger.debug("addOrUpdate");
    	JSONObject jsonObj = new JSONObject();
    	
		try
		{
			SQBSFW_QD sqbsfw_qd = new SQBSFW_QD();
			
			if(id != null && id.equalsIgnoreCase("") == false)
			{
				sqbsfw_qd = sqbsfwqdService.getById(id);
			}
			else
			{
				sqbsfw_qd.setId(Utility.getUniStr());
				sqbsfw_qd.setstatus("正常");
			}
			
			sqbsfw_qd.setblks(blks);
			sqbsfw_qd.setbllc(bllc);
			sqbsfw_qd.setbltj(bltj);
			sqbsfw_qd.setbz(bz);
			sqbsfw_qd.setfj(fj);
			sqbsfw_qd.setfwdx(fwdx);
			sqbsfw_qd.setpzxs(pzxs);
			sqbsfw_qd.setqysj(qysj);
			sqbsfw_qd.setsfyjjbz(sfyjjbz);
			sqbsfw_qd.setsltj(sltj);
			sqbsfw_qd.setsqcl(sqcl);
			sqbsfw_qd.setsxlb(sxlb);
			sqbsfw_qd.setsxmc(sxmc);
			sqbsfw_qd.settysj(tysj);
			sqbsfw_qd.setywzgbm(ywzgbm);
			
			sqbsfwqdService.saveOrUpdate(sqbsfw_qd);
			
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
			SQBSFW_QD sqbsfw_qd = sqbsfwqdService.getById(id);
			
			sqbsfwqdService.delete(sqbsfw_qd);
			
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
	        List<SQBSFW_QD> listObj = sqbsfwqdService.findByFilter(new HqlFilter());
			
	        JSONArray jsonArr = new JSONArray();
	        
	        int iTotalCnt = 0;
			for(int i=0;i<listObj.size();i++)
			{
				SQBSFW_QD sqbsfw_qd = listObj.get(i);
				
				JSONObject jsonTmp = new JSONObject();
				
				jsonTmp.put("id", sqbsfw_qd.getId());
				jsonTmp.put("blks", sqbsfw_qd.getblks());
				jsonTmp.put("bllc", sqbsfw_qd.getbllc());
				jsonTmp.put("bltj", sqbsfw_qd.getbltj());
				jsonTmp.put("bz", sqbsfw_qd.getbz());
				jsonTmp.put("fj", sqbsfw_qd.getfj());
				jsonTmp.put("fwdx", sqbsfw_qd.getfwdx());
				jsonTmp.put("pzxs", sqbsfw_qd.getpzxs());
				jsonTmp.put("qysj", sqbsfw_qd.getqysj());
				jsonTmp.put("sfyjjbz", sqbsfw_qd.getsfyjjbz());
				jsonTmp.put("sltj", sqbsfw_qd.getsltj());
				jsonTmp.put("sqcl", sqbsfw_qd.getsqcl());
				jsonTmp.put("sxlb", sqbsfw_qd.getsxlb());
				jsonTmp.put("sxmc", sqbsfw_qd.getsxmc());
				jsonTmp.put("tysj", sqbsfw_qd.gettysj());
				jsonTmp.put("status", sqbsfw_qd.getstatus());
				jsonTmp.put("ywzgbm", sqbsfw_qd.getywzgbm());
				
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
			SQBSFW_QD sqbsfw_qd = sqbsfwqdService.getById(id);
			
			if(sqbsfw_qd != null)
			{
				jsonObj.put("success", true);
				jsonObj.put("id", sqbsfw_qd.getId());
				jsonObj.put("blks", sqbsfw_qd.getblks());
				jsonObj.put("bllc", sqbsfw_qd.getbllc());
				jsonObj.put("bltj", sqbsfw_qd.getbltj());
				jsonObj.put("bz", sqbsfw_qd.getbz());
				jsonObj.put("fj", sqbsfw_qd.getfj());
				jsonObj.put("fwdx", sqbsfw_qd.getfwdx());
				jsonObj.put("pzxs", sqbsfw_qd.getpzxs());
				jsonObj.put("qysj", sqbsfw_qd.getqysj());
				jsonObj.put("sfyjjbz", sqbsfw_qd.getsfyjjbz());
				jsonObj.put("sltj", sqbsfw_qd.getsltj());
				jsonObj.put("sqcl", sqbsfw_qd.getsqcl());
				jsonObj.put("sxlb", sqbsfw_qd.getsxlb());
				jsonObj.put("sxmc", sqbsfw_qd.getsxmc());
				jsonObj.put("tysj", sqbsfw_qd.gettysj());
				jsonObj.put("ywzgbm", sqbsfw_qd.getywzgbm());
				jsonObj.put("status", sqbsfw_qd.getstatus());
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
	
	

	@RequestMapping(value="changestatus",method = {RequestMethod.POST,RequestMethod.GET},produces="text/html;charset=UTF-8")
    @ResponseBody
	public String changestatus(String id,String status)
	{
		logger.debug("get");
    	JSONObject jsonObj = new JSONObject();
    	
		try
		{
			SQBSFW_QD sqbsfw_qd = sqbsfwqdService.getById(id);
			
			if(sqbsfw_qd != null)
			{
				jsonObj.put("success", true);
				sqbsfw_qd.setstatus(status);
				sqbsfwqdService.save(sqbsfw_qd);
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
	
}
