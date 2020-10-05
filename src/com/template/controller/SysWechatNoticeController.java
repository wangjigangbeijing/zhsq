package com.template.controller;
import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;

import com.template.busi.safe.AES;
import com.template.model.SysUser;
import com.template.model.SysUserOrganization;
import com.template.model.SysWechatNotice;
import com.template.service.SysUserOrganizationService;
import com.template.service.SysUserService;
import com.template.service.SysWechatNoticeService;
import com.template.util.HqlFilter;
import com.template.util.ConstValue;
import com.template.util.EncryptUtil;
import com.template.util.Utility;
import com.template.util.TimeUtil;
import java.util.List;
import java.util.ArrayList;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
@Controller
@RequestMapping("sysWechatNoticeController")
public class SysWechatNoticeController {
	private static Logger logger = Logger.getLogger(SysWechatNoticeController.class);
	@Autowired
	private  HttpServletRequest request;
	@Autowired
	private SysWechatNoticeService sysWechatNoticeService;
	
	@RequestMapping(value="load",method = RequestMethod.GET,produces="text/html;charset=UTF-8")
	@ResponseBody
	public String load(String title)
	{
		JSONObject jsonObj = new JSONObject();
		try
		{
			HqlFilter hqlFilter = new HqlFilter();
			if(title != null && title.equalsIgnoreCase("") == false && title.equalsIgnoreCase("null") == false)
			{
				hqlFilter.addQryCond("title", HqlFilter.Operator.LIKE, "%"+title+"%");
			}
			
			hqlFilter.setOrder("asc");
			
			String organization = Utility.getInstance().getOrganization(request);
			
			ArrayList<String> alOrg = new ArrayList<String>(); 
			
			if(organization != null && organization.equalsIgnoreCase("") == false)
			{
				String [] organizationArr = organization.split(",");
				
			
				for(int i=0;i<organizationArr.length;i++)
				{
					alOrg.add("%"+organizationArr[i]+"%");
				}
			}
			
			if(alOrg != null && alOrg.size() != 0)
				hqlFilter.addOrCondGroup("owner", HqlFilter.Operator.LIKE, alOrg);
	
			hqlFilter.setSort("createtime");
			
	        List<SysWechatNotice> listObj = sysWechatNoticeService.findByFilter(hqlFilter);
	        JSONArray jsonArr = new JSONArray();
	        int iTotalCnt = 0;
			for(int i=0;i<listObj.size();i++)
			{
				SysWechatNotice sys_wechatnotice = listObj.get(i);
				JSONObject jsonTmp = new JSONObject();
				
				String createtime = sys_wechatnotice.getcreatetime();
				
				try
				{
					Integer createTimeInt = Integer.parseInt(createtime);
					createtime = TimeUtil.getTimeByMillSecond2(createTimeInt*1000);
				}
				catch(Exception e)
				{
					
				}
				
				jsonTmp.put("id", sys_wechatnotice.getId());
				jsonTmp.put("createtime", createtime);
				jsonTmp.put("link", sys_wechatnotice.getlink());
				jsonTmp.put("linkurl", sys_wechatnotice.getlinkurl());
				jsonTmp.put("title", sys_wechatnotice.gettitle());
				jsonTmp.put("pcatename", sys_wechatnotice.getpcatename());
								
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
}
