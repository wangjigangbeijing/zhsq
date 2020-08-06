package com.template.controller;
import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import com.template.model.SysBoardPublish;
import com.template.service.SysBoardPublishService;
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
@RequestMapping("sysBoardPublishController")
public class SysBoardPublishController {
	private static Logger logger = Logger.getLogger(SysBoardPublishController.class);
	@Autowired
	private  HttpServletRequest request;
	@Autowired
	private SysBoardPublishService sys_board_publishService;
@RequestMapping(value="addOrUpdate",method = RequestMethod.POST,produces="text/html;charset=UTF-8")
@ResponseBody
public String addOrUpdate(String id,String title,String category,String facilities,String content,
		String attachment,String starttime,String endtime,String status)
{
	JSONObject jsonObj = new JSONObject();
	try
	{
		SysBoardPublish sys_board_publish;
		if(id==null || id.equalsIgnoreCase(""))
		{
			sys_board_publish = new SysBoardPublish();
			sys_board_publish.setId(Utility.getUniStr());
		}
		else
		{
			sys_board_publish = sys_board_publishService.getById(id);
		}
		sys_board_publish.settitle(title);
		sys_board_publish.setcategory(category);
		sys_board_publish.setfacilities(facilities);
		sys_board_publish.setcontent(content);
		sys_board_publish.setattachment(attachment);
		sys_board_publish.setstarttime(starttime);
		sys_board_publish.setendtime(endtime);
		sys_board_publish.setstatus(status);

        sys_board_publishService.save(sys_board_publish);
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
		SysBoardPublish sys_board_publish = sys_board_publishService.getById(id);
		sys_board_publishService.delete(sys_board_publish);
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
public String load(String title,String category)
{
	JSONObject jsonObj = new JSONObject();
	try
	{
		HqlFilter hqlFilter = new HqlFilter();
if(title != null && title.equalsIgnoreCase("") == false && title.equalsIgnoreCase("null") == false)
{
	hqlFilter.addQryCond("title", HqlFilter.Operator.LIKE, "%"+title+"%");
}
if(category != null && category.equalsIgnoreCase("") == false && category.equalsIgnoreCase("null") == false)
{
	hqlFilter.addQryCond("category", HqlFilter.Operator.EQ, category);
}

        List<SysBoardPublish> listObj = sys_board_publishService.findByFilter(hqlFilter);
        JSONArray jsonArr = new JSONArray();
        int iTotalCnt = 0;
		for(int i=0;i<listObj.size();i++)
		{
			SysBoardPublish sys_board_publish = listObj.get(i);
			JSONObject jsonTmp = new JSONObject();
			jsonTmp.put("id", sys_board_publish.getId());
			jsonTmp.put("title",sys_board_publish.gettitle());
			jsonTmp.put("category",sys_board_publish.getcategory());
			
			String facilities = sys_board_publish.getfacilities();
			String [] facilityArr = facilities.split(",");
			
			String facilitiesNames = "";
			
			for(int j=0;j<facilityArr.length;j++)
			{
				String facility = facilityArr[j];
				
				if(ConstValue.advertisementMap.containsKey(facility))
				{
					facilitiesNames += ConstValue.advertisementMap.get(facility) + ",";
				}
			}
			
			jsonTmp.put("facilities",facilitiesNames);
			
			String facilitiesNamesShort = facilitiesNames;
			
			if(facilitiesNames.length() > 50)
				facilitiesNamesShort = facilitiesNames.substring(0,50) +"...";

			jsonTmp.put("facilitiesNamesShort",facilitiesNamesShort);
			
			jsonTmp.put("content",sys_board_publish.getcontent());
			
			String contentShort = sys_board_publish.getcontent();
			if(contentShort.length() > 20)
				contentShort = contentShort.substring(0, 20)+"...";
			
			jsonTmp.put("contentShort", contentShort);
			
			jsonTmp.put("attachment",sys_board_publish.getattachment());
			jsonTmp.put("starttime",sys_board_publish.getstarttime());
			jsonTmp.put("endtime",sys_board_publish.getendtime());
			jsonTmp.put("status",sys_board_publish.getstatus());

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
		SysBoardPublish sys_board_publish = sys_board_publishService.getById(id);
		if(sys_board_publish != null)
		{
			jsonObj.put("title",sys_board_publish.gettitle());
			jsonObj.put("category",sys_board_publish.getcategory());
			jsonObj.put("facilities",sys_board_publish.getfacilities());
			jsonObj.put("content",sys_board_publish.getcontent());
			jsonObj.put("attachment",sys_board_publish.getattachment());
			jsonObj.put("starttime",sys_board_publish.getstarttime());
			jsonObj.put("endtime",sys_board_publish.getendtime());
			jsonObj.put("status",sys_board_publish.getstatus());
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
