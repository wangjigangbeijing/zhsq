package com.template.busi.controller.jcsqsj;
import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import com.template.model.jcsqsj.Family;
import com.template.service.jcsqsj.FamilyService;
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
@RequestMapping("familyController")
public class FamilyController {
	private static Logger logger = Logger.getLogger(FamilyController.class);
	@Autowired
	private  HttpServletRequest request;
	@Autowired
	private FamilyService familyService;
@RequestMapping(value="addOrUpdate",method = RequestMethod.POST,produces="text/html;charset=UTF-8")
@ResponseBody
public String addOrUpdate(String id,String name,String registrationcategory,String registrationaddress,String ofcommunity,String ofresidebuilding,String ofunit,String ofroom,String status)
{
	JSONObject jsonObj = new JSONObject();
	try
	{
		Family family;
		if(id==null || id.equalsIgnoreCase(""))
		{
			family = new Family();
			family.setId(Utility.getUniStr());
		}
		else
		{
			family = familyService.getById(id);
		}
		family.setname(name);
		family.setregistrationcategory(registrationcategory);
		family.setregistrationaddress(registrationaddress);
		family.setofcommunity(ofcommunity);
		family.setofresidebuilding(ofresidebuilding);
		family.setofunit(ofunit);
		family.setofroom(ofroom);
		family.setstatus(status);

		String userId = (String)request.getSession().getAttribute(ConstValue.SESSION_USER_ID);
		
		String organization = "";
		if(ConstValue.userToOrgMap.containsKey(userId))
			organization = ConstValue.userToOrgMap.get(userId);
		family.setowner(organization);
		
        familyService.save(family);
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
		Family family = familyService.getById(id);
		familyService.delete(family);
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
public String load(String name,String registrationcategory,String registrationaddress,String ofcommunity,String ofresidebuilding,String ofunit,String ofroom,String status)
{
	JSONObject jsonObj = new JSONObject();
	try
	{
		int iPageSize = 10;
		int iDisplayStart = 0;
		
		String aoData = request.getParameter("aoData");
		
		if(aoData != null && aoData.equalsIgnoreCase("") == false)
		{
			JSONArray jsonArrParam = new JSONArray(aoData);
			for(int i=0;i<jsonArrParam.length();i++)
			{
				if("iDisplayLength".equalsIgnoreCase(jsonArrParam.getJSONObject(i).get("name").toString()))
				{
					iPageSize = jsonArrParam.getJSONObject(i).getInt("value");
				}
				else if("iDisplayStart".equalsIgnoreCase(jsonArrParam.getJSONObject(i).get("name").toString()))
				{
					iDisplayStart = jsonArrParam.getJSONObject(i).getInt("value");
				}
			}
		}
		
		HqlFilter hqlFilter = new HqlFilter(iDisplayStart/iPageSize+1,iPageSize);
if(name != null && name.equalsIgnoreCase("") == false && name.equalsIgnoreCase("null") == false)
{
	hqlFilter.addQryCond("name", HqlFilter.Operator.LIKE, "%"+name+"%");
}
if(registrationcategory != null && registrationcategory.equalsIgnoreCase("") == false && registrationcategory.equalsIgnoreCase("null") == false)
{
	hqlFilter.addQryCond("registrationcategory", HqlFilter.Operator.EQ, registrationcategory);
}
if(registrationaddress != null && registrationaddress.equalsIgnoreCase("") == false && registrationaddress.equalsIgnoreCase("null") == false)
{
	hqlFilter.addQryCond("registrationaddress", HqlFilter.Operator.LIKE, "%"+registrationaddress+"%");
}
if(ofcommunity != null && ofcommunity.equalsIgnoreCase("") == false && ofcommunity.equalsIgnoreCase("null") == false)
{
	hqlFilter.addQryCond("ofcommunity", HqlFilter.Operator.LIKE, "%"+ofcommunity+"%");
}
if(ofresidebuilding != null && ofresidebuilding.equalsIgnoreCase("") == false && ofresidebuilding.equalsIgnoreCase("null") == false)
{
	hqlFilter.addQryCond("ofresidebuilding", HqlFilter.Operator.LIKE, "%"+ofresidebuilding+"%");
}
if(ofunit != null && ofunit.equalsIgnoreCase("") == false && ofunit.equalsIgnoreCase("null") == false)
{
	hqlFilter.addQryCond("ofunit", HqlFilter.Operator.LIKE, "%"+ofunit+"%");
}
if(ofroom != null && ofroom.equalsIgnoreCase("") == false && ofroom.equalsIgnoreCase("null") == false)
{
	hqlFilter.addQryCond("ofroom", HqlFilter.Operator.LIKE, "%"+ofroom+"%");
}
if(status != null && status.equalsIgnoreCase("") == false && status.equalsIgnoreCase("null") == false)
{
	hqlFilter.addQryCond("status", HqlFilter.Operator.LIKE, "%"+status+"%");
}


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

hqlFilter.setSort("created_at");
hqlFilter.setOrder("desc");


        List<Family> listObj = familyService.findByFilter(hqlFilter);
        JSONArray jsonArr = new JSONArray();

		long iTotalCnt = familyService.countByFilter(hqlFilter);
		
		for(int i=0;i<listObj.size();i++)
		{
			Family family = listObj.get(i);
			JSONObject jsonTmp = new JSONObject();
			jsonTmp.put("id", family.getId());
			jsonTmp.put("name",family.getname());
			jsonTmp.put("registrationcategory",family.getregistrationcategory());
			jsonTmp.put("registrationaddress",family.getregistrationaddress());
			jsonTmp.put("ofcommunity",family.getofcommunity());
			jsonTmp.put("ofresidebuilding",family.getofresidebuilding());

			if(ConstValue.hmDicMap.containsKey(family.getofresidebuilding()))
			{
				jsonTmp.put("ofresidebuildingname",ConstValue.hmDicMap.get(family.getofresidebuilding()));
			}
			
			jsonTmp.put("ofunit",family.getofunit());
			jsonTmp.put("ofroom",family.getofroom());

			if(ConstValue.hmDicMap.containsKey(family.getofroom()))
			{
				jsonTmp.put("roomname",ConstValue.hmDicMap.get(family.getofroom()));
			}
			
			jsonTmp.put("status",family.getstatus());

       		jsonArr.put(jsonTmp);
		}
        jsonObj.put("totalCount", iTotalCnt);
        jsonObj.put("list", jsonArr);
        jsonObj.put("success", true);
        
        jsonObj.put("aaData", jsonArr);
		jsonObj.put("iTotalRecords", iTotalCnt);
		jsonObj.put("iTotalDisplayRecords", iTotalCnt);
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
		Family family = familyService.getById(id);
		if(family != null)
		{
			jsonObj.put("name",family.getname());
			jsonObj.put("registrationcategory",family.getregistrationcategory());
			jsonObj.put("registrationaddress",family.getregistrationaddress());
			jsonObj.put("ofcommunity",family.getofcommunity());
			jsonObj.put("ofresidebuilding",family.getofresidebuilding());
			jsonObj.put("ofresidebuildingname",ConstValue.hmDicMap.get(family.getofresidebuilding()));
			jsonObj.put("ofunit",family.getofunit());
			jsonObj.put("ofroom",family.getofroom());
			jsonObj.put("ofroomname",ConstValue.hmDicMap.get(family.getofroom()));
			jsonObj.put("status",family.getstatus());

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
