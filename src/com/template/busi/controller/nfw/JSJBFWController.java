package com.template.busi.controller.nfw;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mysql.cj.util.StringUtils;
import com.template.model.nfw.JSJBFW;
import com.template.service.nfw.JSJBFWService;
import com.template.util.HqlFilter;
import com.template.util.Utility;
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
			jsjbfw.setsjly(sjly);
			jsjbfw.setsjlybh(sjlybh);
			jsjbfw.setsjlyjb(sjlyjb);
			jsjbfw.setsjnr(sjnr);
			jsjbfw.setwtfl(wtfl);
			
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
	public String load(String sjbt,String sjly,String sjlybh,String dsr, String status)
	{
		JSONObject jsonObj = new JSONObject();
		try
		{
			List<Object> params = new ArrayList<Object>();
			String sql = "select a.*, (select status from fw_flowdatainfo where dataid=a.id order by inserttime desc LIMIT 0, 1) as status from kz_jsjbfw a where 1=1";
			
			if(!StringUtils.isNullOrEmpty(sjbt)) {
				sql += " and a.sjbt like ?";
				params.add("%" + sjbt + "%");
			}
			if(!StringUtils.isNullOrEmpty(sjly) && !"null".equals(sjly)) {
				sql += " and a.sjly=?";
				params.add(sjly);
			}
			if(!StringUtils.isNullOrEmpty(sjlybh)) {
				sql += " and a.sjlybh=?";
				params.add(sjlybh);
			}
			if(!StringUtils.isNullOrEmpty(dsr)) {
				sql += " and a.dsr=?";
				params.add(dsr);
			}
			sql += " order by a.pdsj desc";
			if(!StringUtils.isNullOrEmpty(status)) {
				sql = "select b.* from (" + sql + ") as b where b.status=?";
				params.add(status);
			}
			List<HashMap> list = this.jsjbfwService.findBySql(sql, params);
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
		JSONObject jsonObj = new JSONObject();
		try
		{
			String sql = "select a.*, (select status from fw_flowdatainfo where dataid=a.id order by inserttime desc LIMIT 0, 1) as status from kz_jsjbfw a where id=?";
			List<Object> params = new ArrayList<Object>();
			params.add(id);
			List<HashMap> list = this.jsjbfwService.findBySql(sql, params);
			
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
