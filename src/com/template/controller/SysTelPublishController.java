package com.template.controller;
import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import com.template.model.SysTelPublish;
import com.template.service.SysTelPublishService;
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
@RequestMapping("sysTelPublishController")
public class SysTelPublishController {
	private static Logger logger = Logger.getLogger(SysTelPublishController.class);
	@Autowired
	private  HttpServletRequest request;
	@Autowired
	private SysTelPublishService sys_tel_publishService;
@RequestMapping(value="addOrUpdate",method = RequestMethod.POST,produces="text/html;charset=UTF-8")
@ResponseBody
public String addOrUpdate(String id,String title,String category, String telxl, String content,String audio,String target,String publishtime,String status)
{
	JSONObject jsonObj = new JSONObject();
	try
	{
		String sOrgId = Utility.getInstance().getOrganization(request);
		
		SysTelPublish sys_tel_publish;
		if(id==null || id.equalsIgnoreCase(""))
		{
			sys_tel_publish = new SysTelPublish();
			sys_tel_publish.setId(Utility.getUniStr());
		}
		else
		{
			sys_tel_publish = sys_tel_publishService.getById(id);
		}
		sys_tel_publish.settitle(title);
		sys_tel_publish.setcategory(category);
		sys_tel_publish.settelxl(telxl);
		sys_tel_publish.setcontent(content);
		sys_tel_publish.setaudio(audio);
		sys_tel_publish.settarget(target);
		sys_tel_publish.setpublishtime(TimeUtil.formatDate(new Date(),"yyyy-MM-dd HH:mm"));
		sys_tel_publish.setstatus(status);
		sys_tel_publish.setowner(sOrgId);

        sys_tel_publishService.save(sys_tel_publish);
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
		SysTelPublish sys_tel_publish = sys_tel_publishService.getById(id);
		sys_tel_publishService.delete(sys_tel_publish);
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
public String load(String title,String status)
{
	JSONObject jsonObj = new JSONObject();
	try
	{
		HqlFilter hqlFilter = new HqlFilter();
if(title != null && title.equalsIgnoreCase("") == false && title.equalsIgnoreCase("null") == false)
{
	hqlFilter.addQryCond("title", HqlFilter.Operator.LIKE, "%"+title+"%");
}
if(status != null && status.equalsIgnoreCase("") == false && status.equalsIgnoreCase("null") == false)
{
	hqlFilter.addQryCond("status", HqlFilter.Operator.LIKE, "%"+status+"%");
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

        List<SysTelPublish> listObj = sys_tel_publishService.findByFilter(hqlFilter);
        JSONArray jsonArr = new JSONArray();
        int iTotalCnt = 0;
		for(int i=0;i<listObj.size();i++)
		{
			SysTelPublish sys_tel_publish = listObj.get(i);
			JSONObject jsonTmp = new JSONObject();
			jsonTmp.put("id", sys_tel_publish.getId());
			jsonTmp.put("title",sys_tel_publish.gettitle());
			jsonTmp.put("category",sys_tel_publish.getcategory());
			jsonTmp.put("telxl", sys_tel_publish.gettelxl());
			jsonTmp.put("content",sys_tel_publish.getcontent());
			
			String contentShort = sys_tel_publish.getcontent();
			if(contentShort != null && contentShort.length() > 10)
				contentShort = contentShort.substring(0, 10) + "...";
			
			jsonTmp.put("contentShort", contentShort);
			
			jsonTmp.put("audio",sys_tel_publish.getaudio());
			jsonTmp.put("target",sys_tel_publish.gettarget());
			
			String targetShort = sys_tel_publish.gettarget();
			if(targetShort != null && targetShort.length() > 21)
				targetShort = targetShort.substring(0, 21) + "...";
			
			jsonTmp.put("targetShort", targetShort);
			
			jsonTmp.put("publishtime",sys_tel_publish.getpublishtime());
			jsonTmp.put("status",sys_tel_publish.getstatus());

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
		SysTelPublish sys_tel_publish = sys_tel_publishService.getById(id);
		if(sys_tel_publish != null)
		{
			jsonObj.put("title",sys_tel_publish.gettitle());
			jsonObj.put("category",sys_tel_publish.getcategory());
			jsonObj.put("telxl", sys_tel_publish.gettelxl());
			jsonObj.put("content",sys_tel_publish.getcontent());
			jsonObj.put("audio",sys_tel_publish.getaudio());
			jsonObj.put("target",sys_tel_publish.gettarget());
			jsonObj.put("publishtime",sys_tel_publish.getpublishtime());
			jsonObj.put("status",sys_tel_publish.getstatus());

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
