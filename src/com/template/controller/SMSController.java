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
import java.util.ArrayList;
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
	public String addOrUpdate(String id,String smsContent,String smsType, String smsXl, String mobileList)
	{
		JSONObject jsonObj = new JSONObject();
		try
		{
			String sOrgId = Utility.getInstance().getOrganization(request);
			
			String userId = (String)request.getSession().getAttribute(ConstValue.SESSION_USER_ID);
			
			SMSMessage smsMessage = new SMSMessage();
			
			String mobileArr [] = mobileList.split(",");
			
			int iTargetCnt = mobileArr.length;
			
			smsMessage.setCreateDate(new Date());
			smsMessage.setSuccessCnt(0);
			smsMessage.setMessageContent(smsContent);
			smsMessage.setFailCnt(0);
			smsMessage.setSendStatus(ConstValue.SMS_STATUS_INITIAL);
			smsMessage.setTargetCnt(iTargetCnt);
			smsMessage.setSender(userId);
			smsMessage.setTimerSend(new Date());
			smsMessage.setTarget(mobileList);
			smsMessage.setSMSType(smsType);
			smsMessage.setSMSXl(smsXl);
			smsMessage.setowner(sOrgId);
			
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
	public String load(String smsContent)
	{
		JSONObject jsonObj = new JSONObject();
		try
		{
			HqlFilter hqlFilter = new HqlFilter();
			
			if(smsContent != null && smsContent.equalsIgnoreCase("") == false)
			{
				hqlFilter.addQryCond("messageContent", HqlFilter.Operator.LIKE, "%"+smsContent+"%");
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
				
				String messageContentShort = messageContent;
				
				if(messageContent.length() > 50)
					messageContentShort =messageContentShort.substring(0, 50)+"...";
				
				jsonTmp.put("messageContent", messageContent);
				jsonTmp.put("messageContentShort", messageContentShort);
				jsonTmp.put("successCnt", message.getSuccessCnt());
				jsonTmp.put("failCnt", message.getFailCnt());
				jsonTmp.put("mobileList", message.getTarget());
				jsonTmp.put("messageType", message.getSMSType());
				jsonTmp.put("messageXl", message.getSMSXl());
				
				String mobileListShort = message.getTarget();
				if(mobileListShort != null && mobileListShort.length() > 20)
					mobileListShort = mobileListShort.substring(0, 20) + "...";
				
				jsonTmp.put("mobileListShort", mobileListShort);
				
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
		logger.debug("get:"+msgId);
		
		JSONObject jsonObj = new JSONObject();
		try
		{
			SMSMessage smsMessage = smsService.getById(msgId);
			
			jsonObj.put("messageContent", smsMessage.getMessageContent());
			jsonObj.put("mobileList", smsMessage.getTarget());
			
			String sender = smsMessage.getSender();
			if(ConstValue.userMap.containsKey(smsMessage.getSender()))
				sender = ConstValue.userMap.get(smsMessage.getSender());
			jsonObj.put("sender", sender);
			
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
				
				String mobile = messageStatus.getMobile();
				
				String name = "";
				
				if(mobile.indexOf("-") != -1)
				{
					name = mobile.substring(0,mobile.indexOf("-"));
					mobile = mobile.substring(mobile.indexOf("-") + 1);
				}
				jsonTmp.put("name", name);
				jsonTmp.put("mobile", mobile);
				
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
	
	@RequestMapping(value="resend",method = RequestMethod.POST)
	@ResponseBody
	public String resend(String id)
	{
		JSONObject jsonObj = new JSONObject();
		try
		{
			String userName = (String)request.getSession().getAttribute(ConstValue.SESSION_USER_NAME);
			
			SMSMessageStatus msgStatus = smsStatusService.getById(id);
			
			if(msgStatus != null)
			{
				msgStatus.setStatus(ConstValue.SMS_STATUS_INITIAL);
				
				smsStatusService.save(msgStatus);
			}
			
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
	
}
