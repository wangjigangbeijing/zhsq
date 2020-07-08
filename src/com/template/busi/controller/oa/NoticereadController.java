package com.template.busi.controller.oa;
import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import com.template.model.oa.Noticeread;
import com.template.service.oa.NoticereadService;
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
@RequestMapping("noticereadController")
public class NoticereadController {
	private static Logger logger = Logger.getLogger(NoticereadController.class);
	@Autowired
	private  HttpServletRequest request;
	@Autowired
	private NoticereadService noticereadService;
	
	@RequestMapping(value="addOrUpdate",method = RequestMethod.POST,produces="text/html;charset=UTF-8")
	@ResponseBody
	public String addOrUpdate(String id,String noticeid)//,String readerid)
	{
		JSONObject jsonObj = new JSONObject();
		try
		{
			String userId = request.getHeader(ConstValue.HTTP_HEADER_USERID);
			
			Noticeread noticeread;
			if(id==null || id.equalsIgnoreCase(""))
			{
				noticeread = new Noticeread();
				noticeread.setId(Utility.getUniStr());
			}
			else
			{
				noticeread = noticereadService.getById(id);
			}
			noticeread.setnoticeid(noticeid);
			noticeread.setreaderid(userId);
			noticeread.setreadtime(new Date());
	
	        noticereadService.save(noticeread);
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
		Noticeread noticeread = noticereadService.getById(id);
		noticereadService.delete(noticeread);
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
public String load()
{
	JSONObject jsonObj = new JSONObject();
	try
	{
		HqlFilter hqlFilter = new HqlFilter();

        List<Noticeread> listObj = noticereadService.findByFilter(hqlFilter);
        JSONArray jsonArr = new JSONArray();
        int iTotalCnt = 0;
		for(int i=0;i<listObj.size();i++)
		{
			Noticeread noticeread = listObj.get(i);
			JSONObject jsonTmp = new JSONObject();
			jsonTmp.put("id", noticeread.getId());
			jsonTmp.put("noticeid",noticeread.getnoticeid());
			jsonTmp.put("readerid",noticeread.getreaderid());
			jsonTmp.put("readtime",TimeUtil.formatDate(noticeread.getreadtime(),"yyyy-MM-dd HH:mm"));

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
		Noticeread noticeread = noticereadService.getById(id);
		if(noticeread != null)
		{
			jsonObj.put("noticeid",noticeread.getnoticeid());
			jsonObj.put("readerid",noticeread.getreaderid());
			jsonObj.put("readtime",TimeUtil.formatDate(noticeread.getreadtime(),"yyyy-MM-dd HH:mm"));

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
