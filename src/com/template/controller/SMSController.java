package com.template.controller;
import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;

import com.template.model.SMSMessage;
import com.template.model.SMSMessageStatus;
import com.template.model.SysMenu;
import com.template.service.MenuService;
import com.template.service.SMSService;
import com.template.service.SMSStatusService;
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
@RequestMapping("smsController")
public class SMSController {
	private static Logger logger = Logger.getLogger(SMSController.class);
	@Autowired
	private  HttpServletRequest request;
	
	@Autowired
	private SMSService smsService;
	
	@Autowired
	private SMSStatusService smsStatusService;
	
	@RequestMapping(value="addOrUpdate",method = RequestMethod.POST)
	@ResponseBody
	public String addOrUpdate(String id,String smsContent,String mobileList)
	{
		JSONObject jsonObj = new JSONObject();
		try
		{
			String userName = (String)request.getSession().getAttribute(ConstValue.SESSION_USER_NAME);
			
			SMSMessage smsMessage = new SMSMessage();
			
			String mobileArr [] = mobileList.split(",");
			
			int iTargetCnt = mobileArr.length;
			
			smsMessage.setCreateDate(new Date());
			smsMessage.setSuccessCnt(0);
			smsMessage.setMessageContent(smsContent);
			smsMessage.setFailCnt(0);
			smsMessage.setSendStatus(ConstValue.SMS_STATUS_INITIAL);
			smsMessage.setTargetCnt(iTargetCnt);
			smsMessage.setSender(userName);
			smsMessage.setTimerSend(new Date());
			
			String msgId = Utility.getUniStr();
			smsMessage.setId(msgId);
			
			for(int i=0;i<mobileArr.length;i++)
			{
				SMSMessageStatus smsMessageStatus = new SMSMessageStatus();

				smsMessageStatus.setId(Utility.getUniStr());				
				smsMessageStatus.setMsgId(msgId);
				smsMessageStatus.setMobile(mobileArr[i]);
				smsMessageStatus.setStatus(ConstValue.SMS_STATUS_INITIAL);
				
				smsStatusService.save(smsMessageStatus);
			}
			
			smsService.save(smsMessage);
			
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
	
	@RequestMapping(value="delete",method = RequestMethod.POST)
	@ResponseBody
	public String delete(String id)
	{
		logger.debug("delete "+id);
		JSONObject jsonObj = new JSONObject();
		try
		{
			HqlFilter hqlFilter = new HqlFilter();
			
	        List<SMSMessageStatus> listObj = smsStatusService.findByFilter(hqlFilter);
	        
	        for(int i=0;i<listObj.size();i++)
	        {
	        	SMSMessageStatus smsMessageStatus = listObj.get(i);
	        	
	        	smsStatusService.delete(smsMessageStatus);
	        }
	        
			SMSMessage smsMessage = smsService.getById(id);
			smsService.delete(smsMessage);
			
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
	public String load(String msgContent)
	{
		JSONObject jsonObj = new JSONObject();
		try
		{
			HqlFilter hqlFilter = new HqlFilter();
			
			/*if(menu_zhname != null && menu_zhname.equalsIgnoreCase("") == false)
			{
				hqlFilter.addQryCond("menu_zhname", HqlFilter.Operator.LIKE, "%"+menu_zhname+"%");
			}*/
			
	        List<SMSMessage> listObj = smsService.findByFilter(hqlFilter);
	        
	        JSONArray jsonArr = new JSONArray();
	        int iTotalCnt = 0;
			for(int i=0;i<listObj.size();i++)
			{
				SMSMessage message = listObj.get(i);
				
				JSONObject jsonTmp = new JSONObject();
				jsonTmp.put("id", message.getId());
				
				jsonTmp.put("createDate", TimeUtil.formatDate(message.getCreateDate(),"yyyy-MM-dd HH:mm:ss"));
				
				String messageContent = message.getMessageContent();
				
				if(messageContent.length() > 50)
					messageContent += messageContent.substring(0, 50)+"...";
				
				jsonTmp.put("messageContent", messageContent);
				jsonTmp.put("successCnt", message.getSuccessCnt());
				jsonTmp.put("failCnt", message.getFailCnt());
				jsonTmp.put("sendStatus", message.getSendStatus());
	
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
	public String get(String msgId)
	{
		JSONObject jsonObj = new JSONObject();
		try
		{
			HqlFilter hqlFilter = new HqlFilter();
			
			if(msgId != null && msgId.equalsIgnoreCase("") == false)
			{
				hqlFilter.addQryCond("msgid", HqlFilter.Operator.EQ, msgId);
			}
			
	        List<SMSMessageStatus> listObj = smsStatusService.findByFilter(hqlFilter);
	        
	        JSONArray jsonArr = new JSONArray();
	        int iTotalCnt = 0;
			for(int i=0;i<listObj.size();i++)
			{
				SMSMessageStatus messageStatus = listObj.get(i);
				
				JSONObject jsonTmp = new JSONObject();
				
				jsonTmp.put("id", messageStatus.getId());
				
				jsonTmp.put("mobile", messageStatus.getMobile());
				
				if(messageStatus.getSendTime() != null)
					jsonTmp.put("sendTime", TimeUtil.formatDate(messageStatus.getSendTime(),"yyyy-MM-dd HH:mm:ss"));
				else
					jsonTmp.put("sendTime", "-");
				
				jsonTmp.put("status", messageStatus.getStatus());
	
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
