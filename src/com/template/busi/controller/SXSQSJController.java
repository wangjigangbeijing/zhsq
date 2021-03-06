package com.template.busi.controller;

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
import com.mysql.cj.util.StringUtils;
import com.template.model.SXSQSJ;
import com.template.model.SXSQSJ_FL;
import com.template.model.SysSeq;
import com.template.service.SXSQSJFLService;
import com.template.service.SXSQSJService;
import com.template.service.SysSeqService;

@Controller
@RequestMapping("sxsqsjController")
public class SXSQSJController {
	
	private static Logger logger = Logger.getLogger(SXSQSJController.class);
	
	@Autowired
	private  HttpServletRequest request;
	
	@Autowired
	private SXSQSJService sxsqsjService;
	
	@Autowired
	private SXSQSJFLService sxsqsjflService;
	
	@Autowired
	private SysSeqService sysSeqService;
	
	@RequestMapping(value="addOrUpdate",method = RequestMethod.POST,produces="text/html;charset=UTF-8")
    @ResponseBody
	public String addOrUpdate(String id,
			String sxmc,
			String sxdl,
			String sxxl,
			String sxxq,
			String sxdd,
			String sxdsr,
			String sxdsrname,
			String sxjbr,
			String sxjbrname,
			String sxkssj,	
			String sxjssj,
			String sxzj, 
			String sxzp,
			String sxfj, 
			String sxjf, 
			String sxbz, 
			String sxzt,String fj,String point)
	{
		logger.debug("addOrUpdate");
    	JSONObject jsonObj = new JSONObject();
    	
    	String organization = Utility.getInstance().getOrganization(request);
    	
		try
		{
			SXSQSJ sxsqsj = new SXSQSJ();
			
			if(id != null && id.equalsIgnoreCase("") == false)
			{
				sxsqsj = sxsqsjService.getById(id);
			}
			else
			{
				sxsqsj.setId(Utility.getUniStr());
			}
			
			sxsqsj.setSXBM(getSXBM());
			sxsqsj.setSXMC(sxmc);
			sxsqsj.setSXDL(sxdl);
			sxsqsj.setSXXL(sxxl);
			sxsqsj.setSXXQ(sxxq);
			sxsqsj.setSXDD(sxdd);
			sxsqsj.setSXDSR(sxdsr);
			sxsqsj.setSXDSRNAME(sxdsrname);
			sxsqsj.setSXKSSJ(sxkssj);
			sxsqsj.setSXJSSJ(sxjssj);
			sxsqsj.setSXZJ(sxzj);
			sxsqsj.setSXZP(sxzp);
			sxsqsj.setSXFJ(sxfj);
			sxsqsj.setSXKSSJ(sxkssj);
			sxsqsj.setSXJSSJ(sxjssj);
			//sxsqsj.setSXJF(Double.valueOf(sxjf));
			sxsqsj.setSXBZ(sxbz);
			sxsqsj.setSXZT(sxzt);
			sxsqsj.setFJ(fj);
			sxsqsj.setSXJBR(sxjbr);
			sxsqsj.setSXJBRNAME(sxjbrname);
			sxsqsj.setOWNER(organization);
			sxsqsj.setPOINT(point);
			
			sxsqsjService.save(sxsqsj);
			
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
			SXSQSJ sxsqsj = sxsqsjService.getById(id);
			
			sxsqsjService.delete(sxsqsj);
			
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
	public String load(String sxdl, String sxbm, String sxmc, String sxxl)
	{
		logger.info("load sxdl:"+sxdl);
		
		JSONObject jsonObj = new JSONObject();
    	
		try
		{
			HqlFilter hqlFilter = new HqlFilter();
			if(sxdl != null && sxdl.equalsIgnoreCase("") == false)
				hqlFilter.addQryCond("SXDL", HqlFilter.Operator.EQ, sxdl);
			
			if(!StringUtils.isNullOrEmpty(sxbm)) {
				hqlFilter.addQryCond("SXBM", HqlFilter.Operator.EQ, sxbm);
			}
			
			if(!StringUtils.isNullOrEmpty(sxmc)) {
				hqlFilter.addQryCond("SXMC", HqlFilter.Operator.LIKE, "%" + sxmc + "%");
			}
			
			if(!StringUtils.isNullOrEmpty(sxxl)) {
				hqlFilter.addQryCond("SXXL", HqlFilter.Operator.EQ, sxxl);
			}
			
	    	String organization = Utility.getInstance().getOrganization(request);
	    	
			String [] organizationArr = organization.split(",");
			
			ArrayList<String> alStr = new ArrayList<String>();
			
			//String ownerCond = "";
			
			for(int i=0;i<organizationArr.length;i++)
			{
				//ownerCond += " OWNER LIKE '%"+organizationArr[i]+"%' OR ";
				
				//hqlFilter.addOrCondGroup("OWNER", HqlFilter.Operator.LIKE, "%"+organizationArr[i]+"%");
				
				alStr.add("%"+organizationArr[i]+"%");
			}
			
			hqlFilter.setOrder("desc");
			hqlFilter.setSort("SXBM");
			
			hqlFilter.addOrCondGroup("OWNER", HqlFilter.Operator.LIKE, alStr);
			
	        List<SXSQSJ> listObj = sxsqsjService.findByFilter(hqlFilter);
			
	        JSONArray jsonArr = new JSONArray();
	        
	        int iTotalCnt = 0;
			for(int i=0;i<listObj.size();i++)
			{
				SXSQSJ sxsqsj = listObj.get(i);
				
				JSONObject jsonTmp = new JSONObject();
				
				jsonTmp.put("id", sxsqsj.getId());
				jsonTmp.put("sxbm", sxsqsj.getSXBM());
				jsonTmp.put("sxbz", sxsqsj.getSXBZ());
				jsonTmp.put("sxdd", sxsqsj.getSXDD());
				jsonTmp.put("sxdl", sxsqsj.getSXDL());
				jsonTmp.put("sxdsr", sxsqsj.getSXDSR());
				
				/*
				if(ConstValue.residentMap.containsKey(sxsqsj.getSXDSR()))
					jsonTmp.put("sxdsrname", ConstValue.residentMap.get(sxsqsj.getSXDSR()));
				else
					jsonTmp.put("sxdsrname", sxsqsj.getSXDSR());
				*/
				jsonTmp.put("sxdsrname", sxsqsj.getSXDSRNAME());
				jsonTmp.put("sxfj", sxsqsj.getSXFJ());
				jsonTmp.put("sxjf", sxsqsj.getSXJF());
				jsonTmp.put("sxjssj", sxsqsj.getSXJSSJ());
				jsonTmp.put("sxkssj", sxsqsj.getSXKSSJ());
				jsonTmp.put("sxmc", sxsqsj.getSXMC());
				jsonTmp.put("sxxl", sxsqsj.getSXXL());
				jsonTmp.put("sxxq", sxsqsj.getSXXQ());
				jsonTmp.put("sxzj", sxsqsj.getSXZJ());
				jsonTmp.put("sxzp", sxsqsj.getSXZP());
				jsonTmp.put("sxzt", sxsqsj.getSXZT());
				jsonTmp.put("sxjbr", sxsqsj.getSXJBR());
				jsonTmp.put("sxjbrname", sxsqsj.getSXJBRNAME());
				jsonTmp.put("point", sxsqsj.getPOINT());
				
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
			SXSQSJ sxsqsj = sxsqsjService.getById(id);
			
			if(sxsqsj != null)
			{
				jsonObj.put("success", true);
				jsonObj.put("id", sxsqsj.getId());
				jsonObj.put("sxbm", sxsqsj.getSXBM());
				jsonObj.put("sxbz", sxsqsj.getSXBZ());
				jsonObj.put("sxdd", sxsqsj.getSXDD());
				jsonObj.put("sxdl", sxsqsj.getSXDL());
				jsonObj.put("sxdsr", sxsqsj.getSXDSR());
				jsonObj.put("sxdsrname", sxsqsj.getSXDSRNAME());
				jsonObj.put("sxjbr", sxsqsj.getSXJBR());
				jsonObj.put("sxjbrname", sxsqsj.getSXJBRNAME());
				
//				if(ConstValue.residentMap.containsKey(sxsqsj.getSXDSR()))
//					jsonObj.put("sxdsrname", ConstValue.residentMap.get(sxsqsj.getSXDSR()));
//				else
//					jsonObj.put("sxdsrname", sxsqsj.getSXDSR());
//				
//				jsonObj.put("sxjbr", sxsqsj.getSXJBR());
//				if(ConstValue.userMap.containsKey(sxsqsj.getSXJBR()))
//					jsonObj.put("sxjbrname", ConstValue.userMap.get(sxsqsj.getSXJBR()));
//				else
//					jsonObj.put("sxjbrname", sxsqsj.getSXJBR());
				
				jsonObj.put("sxfj", sxsqsj.getSXFJ());
				jsonObj.put("sxjf", sxsqsj.getSXJF());
				jsonObj.put("sxjssj", sxsqsj.getSXJSSJ());
				jsonObj.put("sxkssj", sxsqsj.getSXKSSJ());
				jsonObj.put("sxmc", sxsqsj.getSXMC());
				jsonObj.put("sxxl", sxsqsj.getSXXL());
				jsonObj.put("sxxq", sxsqsj.getSXXQ());
				jsonObj.put("sxzj", sxsqsj.getSXZJ());
				jsonObj.put("sxzp", sxsqsj.getSXZP());
				jsonObj.put("sxzt", sxsqsj.getSXZT());
				jsonObj.put("fj", sxsqsj.getFJ());
				jsonObj.put("point", sxsqsj.getPOINT());
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
	
	
	
	@RequestMapping(value="getSxsqsjBysxsl",method = RequestMethod.GET,produces="text/html;charset=UTF-8")
    @ResponseBody
	public String getSxsqsjBysxsl()
	{
		logger.info("getSxsqsjBysxsl");
		
		JSONObject jsonObj = new JSONObject();
    	
		try
		{
	        JSONArray jsonArr = new JSONArray();
	        
			String sSql = "SELECT SXSQSJ_FL.SXDL,SXSQSJ_FL.SXXL,ICON FROM SXSQSJ_FL ORDER BY SEQ";
					//+ "LEFT JOIN (SELECT COUNT(*) CNT,SXXL,SXDL FROM SXSQSJ GROUP BY SXDL,SXXL) AS DATA ON SXSQSJ_FL.SXDL = DATA.SXDL AND SXSQSJ_FL.SXXL = DATA.SXXL ORDER BY SEQ";
			
			logger.debug(sSql);
			
	        List<HashMap> listObj = sxsqsjService.findBySql(sSql);
			
	        int iTotalCnt = 0;
			for(int i=0;i<listObj.size();i++)
			{
				HashMap hm = listObj.get(i);
				
				JSONObject jsonTmp = new JSONObject();
				
				jsonTmp.put("sxxl", (String)hm.get("SXXL"));
				jsonTmp.put("sxdl", (String)hm.get("SXDL"));
				jsonTmp.put("icon", (String)hm.get("ICON"));
				jsonTmp.put("cntTotal", 0);
				jsonTmp.put("cntThisyear", 0);
				
				/*if(hm.containsKey("CNT") && hm.get("CNT") != null)
					jsonTmp.put("cnt", (BigInteger)hm.get("CNT")+"/"+(BigInteger)hm.get("CNT"));
				else
					jsonTmp.put("cnt", 0+"/"+0);
	        	*/
				jsonArr.put(jsonTmp);
	        	iTotalCnt++;
			}

			HqlFilter hqlFilter = new HqlFilter();
			
			String organization = Utility.getInstance().getOrganization(request);
	    	
			String [] organizationArr = organization.split(",");
			
			ArrayList<String> alStr = new ArrayList<String>();
			
			//String ownerCond = "";
			
			for(int i=0;i<organizationArr.length;i++)
			{
				//ownerCond += " OWNER LIKE '%"+organizationArr[i]+"%' OR ";
				
				//hqlFilter.addOrCondGroup("OWNER", HqlFilter.Operator.LIKE, "%"+organizationArr[i]+"%");
				
				alStr.add("%"+organizationArr[i]+"%");
			}
			
			hqlFilter.addOrCondGroup("OWNER", HqlFilter.Operator.LIKE, alStr);
			
			List<SXSQSJ> listSxsqsj = sxsqsjService.findByFilter(hqlFilter);
			
			String thisYear = TimeUtil.getYear(new Date());
			
			for(int i=0;i<listSxsqsj.size();i++)
			{
				SXSQSJ sxsqsj = listSxsqsj.get(i);
				
				String sxdl = sxsqsj.getSXDL();
				String sxxl = sxsqsj.getSXXL();
				String sxkssj = sxsqsj.getSXKSSJ();
				
				String sxkssjYear = "";
				if(sxkssj != null && sxkssj.length() >= 4)
					sxkssjYear = sxkssj.substring(0, 4);
				
				for(int j=0;j<jsonArr.length();j++)
				{
					JSONObject jsonTmp = jsonArr.getJSONObject(j);
					
					String sxdlInJson = jsonTmp.getString("sxdl");
					String sxxlInJson = jsonTmp.getString("sxxl");
					
					if(sxdlInJson.equalsIgnoreCase(sxdl) && sxxlInJson.equalsIgnoreCase(sxxl))
					{
						if(sxkssjYear.equalsIgnoreCase(thisYear))
						{
							jsonTmp.put("cntThisyear", jsonTmp.getInt("cntThisyear")+1);
						}
						jsonTmp.put("cntTotal", jsonTmp.getInt("cntTotal")+1);
					}
				}
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
	
	
	@RequestMapping(value="count",method = RequestMethod.GET,produces="text/html;charset=UTF-8")
    @ResponseBody
	public String count()
	{
		logger.info("count");
		
		JSONObject jsonObj = new JSONObject();
    	
		try
		{
			String organization = Utility.getInstance().getOrganization(request);
	    	
			String [] organizationArr = organization.split(",");
			
			List<Object> params = new ArrayList<Object>();
			String where = "";
			
			for(int i=0;i<organizationArr.length;i++)
			{				
				if(organizationArr[i].trim().length() == 0) {
					continue;
				}
				if(where.length() == 0) {
					where = "owner like ?";
				}
				else {
					where += " or owner like ?";
				}
				params.add("%" + organizationArr[i] + "%");
			}
			
	        
			String sSql = "SELECT SXDL,COUNT(*) CNT FROM SXSQSJ GROUP BY SXDL";
			if(params.size() > 0) {
				sSql = "SELECT SXDL,COUNT(*) CNT FROM SXSQSJ where (" + where + ") GROUP BY SXDL";
			}
			
			
			logger.debug(sSql);
			
	        List<HashMap> listObj = sxsqsjService.findBySql(sSql, params);
			
	        jsonObj.put("sqdj", 0);
	        jsonObj.put("mzzz", 0);
	        jsonObj.put("sqfw", 0);
	        jsonObj.put("pajs", 0);
	        jsonObj.put("whjy", 0);
	        jsonObj.put("sqhj", 0);
	        jsonObj.put("wsjk", 0);
	        
			for(int i=0;i<listObj.size();i++)
			{
				HashMap hm = listObj.get(i);
				
				BigInteger iCnt = (BigInteger)hm.get("CNT");
				String sxdl = (String)hm.get("SXDL");
				
				if(sxdl != null && sxdl.equalsIgnoreCase("社区党建事项"))
				{
					jsonObj.put("sqdj", iCnt);//社区党建
				}
				else if(sxdl != null && sxdl.equalsIgnoreCase("民主自治事项"))
				{
					jsonObj.put("mzzz", iCnt);//民主自治		
				}
				else if(sxdl != null && sxdl.equalsIgnoreCase("社区服务事项"))
				{
					jsonObj.put("sqfw", iCnt);//社区服务
				}
				else if(sxdl != null && sxdl.equalsIgnoreCase("平安建设事项"))
				{
					jsonObj.put("pajs", iCnt);//平安建设
				}
				else if(sxdl != null && sxdl.equalsIgnoreCase("文化教育事项"))
				{
					jsonObj.put("whjy", iCnt);//文化教育
				}
				else if(sxdl != null && sxdl.equalsIgnoreCase("社区环境事项"))
				{
					jsonObj.put("sqhj", iCnt);//社区环境
				}
				else if(sxdl != null && sxdl.equalsIgnoreCase("卫生健康事项"))
				{
					jsonObj.put("wsjk", iCnt);//卫生健康
				}
				else
				{
					logger.error("Invalid sxdl in table SXSQSJ");
				}
			}
	        
	        jsonObj.put("success", true);
		}
		catch(Exception e)
		{
			logger.error(e.getMessage(),e);
			jsonObj.put("success", false);
		}
        return jsonObj.toString();
    }
	
	
	@RequestMapping(value="getSXBM",method = RequestMethod.GET,produces="text/html;charset=UTF-8")
    @ResponseBody
	public String getSXBM()
	{
		logger.info("getSXBM");
		
		JSONObject jsonObj = new JSONObject();
    	
		String sxbm = "sxsq";
		
		try
		{
			String year = TimeUtil.getYear(new Date());
			
			String organization = Utility.getInstance().getOrganization(request);
			
			SysSeq sysSeq = sysSeqService.getById(organization);
			
			int seq = 1;
			
			if(sysSeq != null)
			{
				seq = sysSeq.getSeq() + 1;
			}
			else
			{
				sysSeq = new SysSeq();
				sysSeq.setId(organization);
			}
			String str = String.format("%05d", seq);      

			sxbm = sxbm + year + str;
			
			sysSeq.setSeq(seq);
			
			sysSeqService.save(sysSeq);
		}
		catch(Exception e)
		{
			logger.error(e.getMessage(),e);
			jsonObj.put("success", false);
		}
        return sxbm;
    }
}
