package com.template.busi.controller.nfw;
import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;

import com.template.model.nfw.JSJBFW;
import com.template.model.nfw.Xgyqinfo;
import com.template.service.nfw.JSJBFWService;
import com.template.service.nfw.XgyqinfoService;
import com.template.util.HqlFilter;
import com.template.util.ConstValue;
import com.template.util.Utility;
import com.template.util.TimeUtil;
import java.util.List;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
@Controller
@RequestMapping("jsjbfwController")
public class JSJBFWController {
	private static Logger logger = Logger.getLogger(JSJBFWController.class);
	@Autowired
	private  HttpServletRequest request;
	@Autowired
	private JSJBFWService jsjbfwService;
		
	@RequestMapping(value="addOrUpdate",method = RequestMethod.POST,produces="text/html;charset=UTF-8")
	@ResponseBody
	public String addOrUpdate(String id,String sjbt,String sjjjcd,String sjlyjb,String sjly,
			String sjlybh,String sjfl,String wtfl,String fsdz,String dsr,String dsrdh,String sfyqhf,
			String pdsj,String clsx,String cljzsj,String sjnr,String bz,String fj)
	{
		JSONObject jsonObj = new JSONObject();
		try
		{
			JSJBFW jsjbfw;
			
			if(id==null || id.equalsIgnoreCase(""))
			{
				jsjbfw = new JSJBFW();
				jsjbfw.setId(Utility.getUniStr());
			}
			else
			{
				jsjbfw = jsjbfwService.getById(id);
			}
			
			jsjbfw.setbz(bz);
			jsjbfw.setcljzsj(cljzsj);
			jsjbfw.setclsx(clsx);
			jsjbfw.setdsr(dsrdh);
			jsjbfw.setdsrdh(dsrdh);
			jsjbfw.setfj(fj);
			jsjbfw.setfsdz(fsdz);
			jsjbfw.setpdsj(pdsj);
			jsjbfw.setsfyqhf(sfyqhf);
			jsjbfw.setsjbt(sjbt);
			jsjbfw.setsjfl(sjfl);
			jsjbfw.setsjjjcd(sjjjcd);
			jsjbfw.setsjly(sjlybh);
			jsjbfw.setsjlybh(sjlybh);
			jsjbfw.setsjlyjb(sjlyjb);
			jsjbfw.setsjnr(sjnr);
			jsjbfw.setwtfl(wtfl);
			
			/*Xgyqinfo xgyqinfo;
			if(id==null || id.equalsIgnoreCase(""))
			{
				xgyqinfo = new Xgyqinfo();
				xgyqinfo.setId(Utility.getUniStr());
			}
			else
			{
				xgyqinfo = xgyqinfoService.getById(id);
			}
			xgyqinfo.setname(name);
			xgyqinfo.setmobile(mobile);
			xgyqinfo.setaddress(address);
			xgyqinfo.setquezhen(quezhen);
			if(qzdate != null && qzdate.equalsIgnoreCase("") == false)
				xgyqinfo.setqzdate(TimeUtil.parseDate(qzdate, "yyyy-MM-dd"));
			xgyqinfo.setqznote(qznote);
			xgyqinfo.setyisi(yisi);
			xgyqinfo.setmijie(mijie);
			//xgyqinfo.setmjnote(mjnote);
			if(glstartdate != null && glstartdate.equalsIgnoreCase("") == false)
				xgyqinfo.setglstartdate(TimeUtil.parseDate(glstartdate, "yyyy-MM-dd"));// HH:mm
			
			if(glenddate != null && glenddate.equalsIgnoreCase("") == false)
				xgyqinfo.setglenddate(TimeUtil.parseDate(glenddate, "yyyy-MM-dd"));// HH:mm
			xgyqinfo.setnote(note);
			xgyqinfo.sethsjc(hsjc);
			if(hsjcdate != null && hsjcdate.equalsIgnoreCase("") == false)
				xgyqinfo.sethsjcdate(TimeUtil.parseDate(hsjcdate, "yyyy-MM-dd"));// HH:mm
			xgyqinfo.sethsjcjigou(hsjcjigou);
			xgyqinfo.sethsjcjieguo(hsjcjieguo);
	
	        xgyqinfoService.save(xgyqinfo);*/
			
			
			jsjbfwService.save(jsjbfw);
			
			jsonObj.put("dataid", jsjbfw.getId());
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
	
	@RequestMapping(value="delete",method = RequestMethod.POST,produces="text/html;charset=UTF-8")
	@ResponseBody
	public String delete(String id)
	{
		logger.debug("delete "+id);
		JSONObject jsonObj = new JSONObject();
		try
		{
			JSJBFW jsjbfw = jsjbfwService.getById(id);
			jsjbfwService.delete(jsjbfw);
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
	public String load(String name,String mobile,String address,String quezhen,String qzdate,String yisi,String mijie,String hsjc,String hsjcjieguo)
	{
		JSONObject jsonObj = new JSONObject();
		try
		{
			HqlFilter hqlFilter = new HqlFilter();/*
	if(name != null && name.equalsIgnoreCase("") == false && name.equalsIgnoreCase("null") == false)
	{
		hqlFilter.addQryCond("name", HqlFilter.Operator.LIKE, "%"+name+"%");
	}
	if(mobile != null && mobile.equalsIgnoreCase("") == false && mobile.equalsIgnoreCase("null") == false)
	{
		hqlFilter.addQryCond("mobile", HqlFilter.Operator.LIKE, "%"+mobile+"%");
	}
	if(address != null && address.equalsIgnoreCase("") == false && address.equalsIgnoreCase("null") == false)
	{
		hqlFilter.addQryCond("address", HqlFilter.Operator.LIKE, "%"+address+"%");
	}
	if(quezhen != null && quezhen.equalsIgnoreCase("") == false && quezhen.equalsIgnoreCase("null") == false)
	{
		hqlFilter.addQryCond("quezhen", HqlFilter.Operator.LIKE, "%"+quezhen+"%");
	}
	if(qzdate != null && qzdate.equalsIgnoreCase("") == false && qzdate.equalsIgnoreCase("null") == false)
	{
		hqlFilter.addQryCond("qzdate", HqlFilter.Operator.LIKE, "%"+qzdate+"%");
	}
	if(yisi != null && yisi.equalsIgnoreCase("") == false && yisi.equalsIgnoreCase("null") == false)
	{
		hqlFilter.addQryCond("yisi", HqlFilter.Operator.LIKE, "%"+yisi+"%");
	}
	if(mijie != null && mijie.equalsIgnoreCase("") == false && mijie.equalsIgnoreCase("null") == false)
	{
		hqlFilter.addQryCond("mijie", HqlFilter.Operator.LIKE, "%"+mijie+"%");
	}
	if(hsjc != null && hsjc.equalsIgnoreCase("") == false && hsjc.equalsIgnoreCase("null") == false)
	{
		hqlFilter.addQryCond("hsjc", HqlFilter.Operator.LIKE, "%"+hsjc+"%");
	}
	if(hsjcjieguo != null && hsjcjieguo.equalsIgnoreCase("") == false && hsjcjieguo.equalsIgnoreCase("null") == false)
	{
		hqlFilter.addQryCond("hsjcjieguo", HqlFilter.Operator.LIKE, "%"+hsjcjieguo+"%");
	}
		*/
        List<JSJBFW> listObj = jsjbfwService.findByFilter(hqlFilter);
        net.sf.json.JSONArray jsonArr = net.sf.json.JSONArray.fromObject(listObj);
//        JSONArray jsonArr = new JSONArray();
//        int iTotalCnt = 0;
//		for(int i=0;i<listObj.size();i++)
//		{
//			JSJBFW jsjbfw = listObj.get(i);
//			JSONObject jsonTmp = new JSONObject();
//			jsonObj.put("id",jsjbfw.getId());
//			jsonObj.put("bz",jsjbfw.getbz());
//			jsonObj.put("cljzsj",jsjbfw.getcljzsj());
//			jsonObj.put("clsx",jsjbfw.getclsx());
//			jsonObj.put("dsr",jsjbfw.getdsr());
//			jsonObj.put("dsrdh",jsjbfw.getdsrdh());
//			jsonObj.put("fj",jsjbfw.getfj());
//			jsonObj.put("fsdz",jsjbfw.getfsdz());
//			jsonObj.put("pdsj",jsjbfw.getpdsj());
//			jsonObj.put("sfyqhf",jsjbfw.getsfyqhf());
//			jsonObj.put("sjbt",jsjbfw.getsjbt());
//			jsonObj.put("sjfl",jsjbfw.getsjfl());
//			jsonObj.put("sjjjcd",jsjbfw.getsjjjcd());
//			jsonObj.put("sjly",jsjbfw.getsjly());
//			jsonObj.put("sjlybh",jsjbfw.getsjlybh());
//			jsonObj.put("sjlyjb",jsjbfw.getsjlyjb());
//			jsonObj.put("sjnr",jsjbfw.getsjnr());
//			jsonObj.put("wtfl",jsjbfw.getwtfl());
//
//       		jsonArr.put(jsonTmp);
//        	iTotalCnt++;
//		}
        jsonObj.put("totalCount", jsonArr.size());
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
		JSONObject jsonObj = new JSONObject();
		try
		{
			JSJBFW jsjbfw = jsjbfwService.getById(id);
			
			if(jsjbfw != null)
			{
				jsonObj.put("id",jsjbfw.getId());
				jsonObj.put("bz",jsjbfw.getbz());
				jsonObj.put("cljzsj",jsjbfw.getcljzsj());
				jsonObj.put("clsx",jsjbfw.getclsx());
				jsonObj.put("dsr",jsjbfw.getdsr());
				jsonObj.put("dsrdh",jsjbfw.getdsrdh());
				jsonObj.put("fj",jsjbfw.getfj());
				jsonObj.put("fsdz",jsjbfw.getfsdz());
				jsonObj.put("pdsj",jsjbfw.getpdsj());
				jsonObj.put("sfyqhf",jsjbfw.getsfyqhf());
				jsonObj.put("sjbt",jsjbfw.getsjbt());
				jsonObj.put("sjfl",jsjbfw.getsjfl());
				jsonObj.put("sjjjcd",jsjbfw.getsjjjcd());
				jsonObj.put("sjly",jsjbfw.getsjly());
				jsonObj.put("sjlybh",jsjbfw.getsjlybh());
				jsonObj.put("sjlyjb",jsjbfw.getsjlyjb());
				jsonObj.put("sjnr",jsjbfw.getsjnr());
				jsonObj.put("wtfl",jsjbfw.getwtfl());
				jsonObj.put("success", true);
			}
			else
			{
				logger.error("object is not found...");
				jsonObj.put("success", false);
				jsonObj.put("errMsg", "Object can not found...");
			}
				        
			/*Xgyqinfo xgyqinfo = xgyqinfoService.getById(id);
			if(xgyqinfo != null)
			{
				jsonObj.put("name",xgyqinfo.getname());
				jsonObj.put("mobile",xgyqinfo.getmobile());
				jsonObj.put("address",xgyqinfo.getaddress());
				jsonObj.put("quezhen",xgyqinfo.getquezhen());
				jsonObj.put("qzdate",TimeUtil.formatDate(xgyqinfo.getqzdate(),"yyyy-MM-dd"));
				jsonObj.put("qznote",xgyqinfo.getqznote());
				jsonObj.put("yisi",xgyqinfo.getyisi());
				jsonObj.put("mijie",xgyqinfo.getmijie());
				//jsonObj.put("mjnote",xgyqinfo.getmjnote());
				jsonObj.put("glstartdate",TimeUtil.formatDate(xgyqinfo.getglstartdate(),"yyyy-MM-dd HH:mm"));
				jsonObj.put("glenddate",TimeUtil.formatDate(xgyqinfo.getglenddate(),"yyyy-MM-dd HH:mm"));
				jsonObj.put("note",xgyqinfo.getnote());
				jsonObj.put("hsjc",xgyqinfo.gethsjc());
				jsonObj.put("hsjcdate",TimeUtil.formatDate(xgyqinfo.gethsjcdate(),"yyyy-MM-dd HH:mm"));
				jsonObj.put("hsjcjigou",xgyqinfo.gethsjcjigou());
				jsonObj.put("hsjcjieguo",xgyqinfo.gethsjcjieguo());
	
				jsonObj.put("success", true);
			}
			else
			{
				logger.error("object is not found...");
				jsonObj.put("success", false);
				jsonObj.put("errMsg", "Object can not found...");
			}*/
		}
		catch(Exception e)
		{
			logger.error(e.getMessage(),e);
			jsonObj.put("success", false);
		}
	    return jsonObj.toString();
	}
}
