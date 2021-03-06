package com.template.busi.controller.oa;
import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;

import com.mysql.cj.util.StringUtils;
import com.template.model.SysUserOrganization;
import com.template.model.oa.Notice;
import com.template.service.SysUserOrganizationService;
import com.template.service.oa.NoticeService;
import com.template.util.HqlFilter;
import com.template.util.ConstValue;
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
@RequestMapping("noticeController")
public class NoticeController {
	private static Logger logger = Logger.getLogger(NoticeController.class);
	@Autowired
	private  HttpServletRequest request;
	@Autowired
	private NoticeService noticeService;
	
	@Autowired
	private SysUserOrganizationService userOrganizationService;
	
	@RequestMapping(value="addOrUpdate",method = RequestMethod.POST,produces="text/html;charset=UTF-8")
	@ResponseBody
	public String addOrUpdate(String id,String title,String type,String authorityorg,String body,String attach,String time)
	{
		JSONObject jsonObj = new JSONObject();
		try
		{
			String sOrgId = Utility.getInstance().getOrganization(request);
			
			Notice notice;
			if(id==null || id.equalsIgnoreCase(""))
			{
				notice = new Notice();
				notice.setId(Utility.getUniStr());
			}
			else
			{
				notice = noticeService.getById(id);
			}
			notice.settitle(title);
			notice.settype(type);
			notice.setauthorityorg(authorityorg);
			notice.setbody(body);
			notice.setattach(attach);
			notice.setowner(sOrgId);
			
			if(time != null)
				notice.settime(TimeUtil.parseDate(time + " 00:00:00", "yyyy-MM-dd HH:mm:ss"));
			else
				notice.settime(new Date());
	
	        noticeService.save(notice);
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
		Notice notice = noticeService.getById(id);
		noticeService.delete(notice);
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
	public String load(String title,String type,String body)
	{
		JSONObject jsonObj = new JSONObject();
		try
		{
			HqlFilter hqlFilter = new HqlFilter();
			
			if(title != null && title.equalsIgnoreCase("") == false && title.equalsIgnoreCase("null") == false)
			{
				hqlFilter.addQryCond("title", HqlFilter.Operator.LIKE, "%"+title+"%");
			}
			if(type != null && type.equalsIgnoreCase("") == false && type.equalsIgnoreCase("null") == false)
			{
				hqlFilter.addQryCond("type", HqlFilter.Operator.LIKE, "%"+type+"%");
			}
			if(body != null && body.equalsIgnoreCase("") == false && body.equalsIgnoreCase("null") == false)
			{
				hqlFilter.addQryCond("body", HqlFilter.Operator.LIKE, "%"+body+"%");
			}
	
			//用户映射的具体组织 
			String userId = request.getHeader(ConstValue.HTTP_HEADER_USERID);//该接口只从APP上调用
			
			ArrayList<String> alOrg = new ArrayList<String>(); 
			
			//社区组织
			String organization = Utility.getInstance().getOrganization(request);
			
			if(organization != null && organization.equalsIgnoreCase("") == false)
			{
				String [] organizationArr = organization.split(",");
				

				for(int i=0;i<organizationArr.length;i++)
				{
					if(!"d216599c-7b85-48ab-8e49-049178f5a285".equals(organizationArr[i])) {
						alOrg.add("%"+organizationArr[i]+"%");
					}
				}
			}
			
			
			
//			HqlFilter hqlFilterOrganzation = new HqlFilter();
//			hqlFilterOrganzation.addQryCond("user", HqlFilter.Operator.EQ, userId);
//			
//			List<SysUserOrganization> userOrgList = userOrganizationService.findByFilter(hqlFilterOrganzation);
//
//			for(int j=0;j<userOrgList.size();j++)
//			{
//				alOrg.add(userOrgList.get(j).getorganization());
//			}
			
			
			if(alOrg != null && alOrg.size() != 0)
				hqlFilter.addOrCondGroup("authorityorg", HqlFilter.Operator.LIKE, alOrg);
			
	        List<Notice> listObj = noticeService.findByFilter(hqlFilter);
	        JSONArray jsonArr = new JSONArray();
	        int iTotalCnt = 0;
			for(int i=0;i<listObj.size();i++)
			{
				Notice notice = listObj.get(i);
				JSONObject jsonTmp = new JSONObject();
				jsonTmp.put("id", notice.getId());
				jsonTmp.put("title",notice.gettitle());
				jsonTmp.put("type",notice.gettype());
				jsonTmp.put("authorityorg",notice.getauthorityorg());
				
				if(ConstValue.orgMap.containsKey(notice.getauthorityorg()))
					jsonTmp.put("authorityorgname",ConstValue.orgMap.get(notice.getauthorityorg()));
				
				jsonTmp.put("body",notice.getbody());
				
				String bodyTxt = notice.getbody();
				String bodyShort = bodyTxt;
				if(bodyShort != null && bodyShort.length() > 10)
					bodyShort = bodyShort.substring(0, 10) + "...";
				
				jsonTmp.put("bodyShort", bodyShort);
				
				jsonTmp.put("attach",notice.getattach());
				jsonTmp.put("time",TimeUtil.formatDate(notice.gettime(),"yyyy-MM-dd HH:mm"));
	
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
	

	@RequestMapping(value="loadFromWeb",method = RequestMethod.GET,produces="text/html;charset=UTF-8")
	@ResponseBody
	public String loadFromWeb(String title,String type,String body)
	{
		JSONObject jsonObj = new JSONObject();
		try
		{
			HqlFilter hqlFilter = new HqlFilter();
			
			if(title != null && title.equalsIgnoreCase("") == false && title.equalsIgnoreCase("null") == false)
			{
				hqlFilter.addQryCond("title", HqlFilter.Operator.LIKE, "%"+title+"%");
			}
			if(type != null && type.equalsIgnoreCase("") == false && type.equalsIgnoreCase("null") == false)
			{
				hqlFilter.addQryCond("type", HqlFilter.Operator.LIKE, "%"+type+"%");
			}
			if(body != null && body.equalsIgnoreCase("") == false && body.equalsIgnoreCase("null") == false)
			{
				hqlFilter.addQryCond("body", HqlFilter.Operator.LIKE, "%"+body+"%");
			}
	
			ArrayList<String> alOrg = new ArrayList<String>(); 
			
			String organization = Utility.getInstance().getOrganization(request);
			
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
			
	        List<Notice> listObj = noticeService.findByFilter(hqlFilter);
	        JSONArray jsonArr = new JSONArray();
	        int iTotalCnt = 0;
			for(int i=0;i<listObj.size();i++)
			{
				Notice notice = listObj.get(i);
				JSONObject jsonTmp = new JSONObject();
				jsonTmp.put("id", notice.getId());
				jsonTmp.put("title",notice.gettitle());
				jsonTmp.put("type",notice.gettype());
				jsonTmp.put("authorityorg",notice.getauthorityorg());
				
				if(ConstValue.orgMap.containsKey(notice.getauthorityorg()))
					jsonTmp.put("authorityorgname",ConstValue.orgMap.get(notice.getauthorityorg()));
				
				jsonTmp.put("body",notice.getbody());
				
				String bodyTxt = notice.getbody();
				String bodyShort = bodyTxt;
				if(bodyShort != null && bodyShort.length() > 10)
					bodyShort = bodyShort.substring(0, 10) + "...";
				
				jsonTmp.put("bodyShort", bodyShort);
				
				jsonTmp.put("attach",notice.getattach());
				jsonTmp.put("time",TimeUtil.formatDate(notice.gettime(),"yyyy-MM-dd HH:mm"));
	
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
	JSONObject jsonObj = new JSONObject();
	try
	{
		Notice notice = noticeService.getById(id);
		if(notice != null)
		{
			jsonObj.put("title",notice.gettitle());
			jsonObj.put("type",notice.gettype());
			jsonObj.put("authorityorg",notice.getauthorityorg());
			jsonObj.put("body",notice.getbody());
			jsonObj.put("attach",notice.getattach());
			jsonObj.put("time",TimeUtil.formatDate(notice.gettime(),"yyyy-MM-dd HH:mm"));

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
}
