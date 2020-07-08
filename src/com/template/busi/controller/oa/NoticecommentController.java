package com.template.busi.controller.oa;
import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import com.template.model.oa.Noticecomment;
import com.template.service.oa.NoticecommentService;
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
@RequestMapping("noticecommentController")
public class NoticecommentController {
	private static Logger logger = Logger.getLogger(NoticecommentController.class);
	@Autowired
	private  HttpServletRequest request;
	@Autowired
	private NoticecommentService noticecommentService;
	
	@RequestMapping(value="addOrUpdate",method = RequestMethod.POST,produces="text/html;charset=UTF-8")
	@ResponseBody
	public String addOrUpdate(String id,String noticeid,String comments)//,String commenttime),String commentstaffid
	{
		logger.info("noticecomment "+noticeid+"   "+comments);
		JSONObject jsonObj = new JSONObject();
		try
		{
			String userId = request.getHeader(ConstValue.HTTP_HEADER_USERID);
			
			Noticecomment noticecomment;
			if(id==null || id.equalsIgnoreCase(""))
			{
				noticecomment = new Noticecomment();
				noticecomment.setId(Utility.getUniStr());
			}
			else
			{
				noticecomment = noticecommentService.getById(id);
			}
			noticecomment.setnoticeid(noticeid);
			noticecomment.setcomments(comments);
			noticecomment.setcommentstaffid(userId);
			noticecomment.setcommenttime(new Date());
	
	        noticecommentService.save(noticecomment);
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
			Noticecomment noticecomment = noticecommentService.getById(id);
			noticecommentService.delete(noticecomment);
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
	public String load(String comments,String commentstaffid,String noticeid)
	{
		JSONObject jsonObj = new JSONObject();
		try
		{
			HqlFilter hqlFilter = new HqlFilter();
			
			if(comments != null && comments.equalsIgnoreCase("") == false && comments.equalsIgnoreCase("null") == false)
			{
				hqlFilter.addQryCond("comments", HqlFilter.Operator.LIKE, "%"+comments+"%");
			}
			if(commentstaffid != null && commentstaffid.equalsIgnoreCase("") == false && commentstaffid.equalsIgnoreCase("null") == false)
			{
				hqlFilter.addQryCond("commentstaffid", HqlFilter.Operator.LIKE, "%"+commentstaffid+"%");
			}
			if(noticeid != null && noticeid.equalsIgnoreCase("") == false && noticeid.equalsIgnoreCase("null") == false)
			{
				hqlFilter.addQryCond("noticeid", HqlFilter.Operator.EQ, noticeid);
			}
			
	        List<Noticecomment> listObj = noticecommentService.findByFilter(hqlFilter);
	        JSONArray jsonArr = new JSONArray();
	        int iTotalCnt = 0;
			for(int i=0;i<listObj.size();i++)
			{
				Noticecomment noticecomment = listObj.get(i);
				JSONObject jsonTmp = new JSONObject();
				jsonTmp.put("id", noticecomment.getId());
				jsonTmp.put("noticeid",noticecomment.getnoticeid());
				jsonTmp.put("comments",noticecomment.getcomments());
				jsonTmp.put("commentstaffid",noticecomment.getcommentstaffid());
				jsonTmp.put("commenttime",TimeUtil.formatDate(noticecomment.getcommenttime(),"yyyy-MM-dd HH:mm"));
	
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
			Noticecomment noticecomment = noticecommentService.getById(id);
			if(noticecomment != null)
			{
				jsonObj.put("noticeid",noticecomment.getnoticeid());
				jsonObj.put("comments",noticecomment.getcomments());
				jsonObj.put("commentstaffid",noticecomment.getcommentstaffid());
				jsonObj.put("commenttime",TimeUtil.formatDate(noticecomment.getcommenttime(),"yyyy-MM-dd HH:mm"));
	
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
