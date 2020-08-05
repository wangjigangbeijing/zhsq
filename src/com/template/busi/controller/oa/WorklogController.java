package com.template.busi.controller.oa;
import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import com.template.model.oa.Worklog;
import com.template.service.oa.WorklogService;
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
@RequestMapping("worklogController")
public class WorklogController {
	private static Logger logger = Logger.getLogger(WorklogController.class);
	@Autowired
	private  HttpServletRequest request;
	@Autowired
	private WorklogService worklogService;
@RequestMapping(value="addOrUpdate",method = RequestMethod.POST,produces="text/html;charset=UTF-8")
@ResponseBody
public String addOrUpdate(String id,String title,String date,String type,String hours,String content,String files)
{
	JSONObject jsonObj = new JSONObject();
	try
	{
		Worklog worklog;
		if(id==null || id.equalsIgnoreCase(""))
		{
			worklog = new Worklog();
			worklog.setId(Utility.getUniStr());
		}
		else
		{
			worklog = worklogService.getById(id);
		}
		
		String userId = request.getHeader(ConstValue.HTTP_HEADER_USERID);
		
		worklog.settitle(title);
		worklog.setdate(TimeUtil.parseDate(date, "yyyy-MM-dd"));
		worklog.settype(type);
		worklog.sethours(hours);
		worklog.setcontent(content);
		worklog.setfiles(files);
		worklog.setowner(userId);

        worklogService.save(worklog);
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
		Worklog worklog = worklogService.getById(id);
		worklogService.delete(worklog);
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
public String load(String title,String date,String type,String hours,String content,String files,String owner)
{
	JSONObject jsonObj = new JSONObject();
	try
	{
		HqlFilter hqlFilter = new HqlFilter();
		if(title != null && title.equalsIgnoreCase("") == false && title.equalsIgnoreCase("null") == false)
		{
			hqlFilter.addQryCond("title", HqlFilter.Operator.LIKE, "%"+title+"%");
		}
		if(date != null && date.equalsIgnoreCase("") == false && date.equalsIgnoreCase("null") == false)
		{
			hqlFilter.addQryCond("date", HqlFilter.Operator.LIKE, "%"+date+"%");
		}
		if(type != null && type.equalsIgnoreCase("") == false && type.equalsIgnoreCase("null") == false)
		{
			hqlFilter.addQryCond("type", HqlFilter.Operator.LIKE, "%"+type+"%");
		}
		if(hours != null && hours.equalsIgnoreCase("") == false && hours.equalsIgnoreCase("null") == false)
		{
			hqlFilter.addQryCond("hours", HqlFilter.Operator.LIKE, "%"+hours+"%");
		}
		if(content != null && content.equalsIgnoreCase("") == false && content.equalsIgnoreCase("null") == false)
		{
			hqlFilter.addQryCond("content", HqlFilter.Operator.LIKE, "%"+content+"%");
		}
		if(files != null && files.equalsIgnoreCase("") == false && files.equalsIgnoreCase("null") == false)
		{
			hqlFilter.addQryCond("files", HqlFilter.Operator.LIKE, "%"+files+"%");
		}
		if(owner != null && owner.equalsIgnoreCase("") == false && owner.equalsIgnoreCase("null") == false)
		{
			hqlFilter.addQryCond("owner", HqlFilter.Operator.LIKE, "%"+owner+"%");
		}

		hqlFilter.setSort("date");
		hqlFilter.setOrder("desc");

        List<Worklog> listObj = worklogService.findByFilter(hqlFilter);
        JSONArray jsonArr = new JSONArray();
        int iTotalCnt = 0;
		for(int i=0;i<listObj.size();i++)
		{
			Worklog worklog = listObj.get(i);
			JSONObject jsonTmp = new JSONObject();
			jsonTmp.put("id", worklog.getId());
			jsonTmp.put("title",worklog.gettitle());
			jsonTmp.put("date",TimeUtil.formatDate(worklog.getdate(),"yyyy-MM-dd"));
			jsonTmp.put("type",worklog.gettype());
			jsonTmp.put("hours",worklog.gethours());
			jsonTmp.put("content",worklog.getcontent());
			jsonTmp.put("files",worklog.getfiles());
			jsonTmp.put("owner",worklog.getowner());

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
		Worklog worklog = worklogService.getById(id);
		if(worklog != null)
		{
			jsonObj.put("title",worklog.gettitle());
			jsonObj.put("date",TimeUtil.formatDate(worklog.getdate(),"yyyy-MM-dd"));
			jsonObj.put("type",worklog.gettype());
			jsonObj.put("hours",worklog.gethours());
			jsonObj.put("content",worklog.getcontent());
			jsonObj.put("files",worklog.getfiles());
			jsonObj.put("owner",worklog.getowner());

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
