package com.template.busi.controller.jcsqsj;
import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import com.template.model.jcsqsj.Community;
import com.template.service.jcsqsj.CommunityService;
import com.template.util.HqlFilter;
import com.template.util.ConstValue;
import com.template.util.Utility;
import com.template.util.TimeUtil;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
@Controller
@RequestMapping("communityController")
public class CommunityController {
	private static Logger logger = Logger.getLogger(CommunityController.class);
	@Autowired
	private  HttpServletRequest request;
	@Autowired
	private CommunityService communityService;
@RequestMapping(value="addOrUpdate",method = RequestMethod.POST,produces="text/html;charset=UTF-8")
@ResponseBody
public String addOrUpdate(String id,String dataid,String name,String buildtype,String type,String year,Integer buildings,Integer gates,
		Integer groundparking,Integer underparking,String status,String address,String pictures,String note)//,String duoxuan)Integer longitude,Integer latitude,
{
	JSONObject jsonObj = new JSONObject();
	try
	{
		Community community;
		if(id==null || id.equalsIgnoreCase(""))
		{
			community = new Community();
			community.setId(Utility.getUniStr());
		}
		else
		{
			community = communityService.getById(id);
		}
		community.setdataid(dataid);
		community.setname(name);
		community.setbuildtype(buildtype);
		community.settype(type);
		community.setyear(TimeUtil.parseDate(year, "yyyy-MM-dd"));
		community.setbuildings(buildings);
		community.setgates(gates);
		community.setgroundparking(groundparking);
		community.setunderparking(underparking);
		//community.setlongitude(longitude);
		//community.setlatitude(latitude);
		community.setstatus(status);
		community.setaddress(address);
		community.setpictures(pictures);
		community.setnote(note);
		//community.setduoxuan(duoxuan);

        communityService.save(community);
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
		Community community = communityService.getById(id);
		communityService.delete(community);
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
public String load(String name,String buildtype,String type,String year,String status,String address,String note,String duoxuan)
{
	JSONObject jsonObj = new JSONObject();
	try
	{
		HqlFilter hqlFilter = new HqlFilter();
if(name != null && name.equalsIgnoreCase("") == false && name.equalsIgnoreCase("null") == false)
{
	hqlFilter.addQryCond("name", HqlFilter.Operator.LIKE, "%"+name+"%");
}
if(buildtype != null && buildtype.equalsIgnoreCase("") == false && buildtype.equalsIgnoreCase("null") == false)
{
	hqlFilter.addQryCond("buildtype", HqlFilter.Operator.LIKE, "%"+buildtype+"%");
}
if(type != null && type.equalsIgnoreCase("") == false && type.equalsIgnoreCase("null") == false)
{
	hqlFilter.addQryCond("type", HqlFilter.Operator.EQ, type);
}
if(year != null && year.equalsIgnoreCase("") == false && year.equalsIgnoreCase("null") == false)
{
	hqlFilter.addQryCond("year", HqlFilter.Operator.LIKE, "%"+year+"%");
}
if(status != null && status.equalsIgnoreCase("") == false && status.equalsIgnoreCase("null") == false)
{
	hqlFilter.addQryCond("status", HqlFilter.Operator.LIKE, "%"+status+"%");
}
if(address != null && address.equalsIgnoreCase("") == false && address.equalsIgnoreCase("null") == false)
{
	hqlFilter.addQryCond("address", HqlFilter.Operator.LIKE, "%"+address+"%");
}
if(note != null && note.equalsIgnoreCase("") == false && note.equalsIgnoreCase("null") == false)
{
	hqlFilter.addQryCond("note", HqlFilter.Operator.LIKE, "%"+note+"%");
}
if(duoxuan != null && duoxuan.equalsIgnoreCase("") == false && duoxuan.equalsIgnoreCase("null") == false)
{
	hqlFilter.addQryCond("duoxuan", HqlFilter.Operator.LIKE, "%"+duoxuan+"%");
}

String userId = (String)request.getSession().getAttribute(ConstValue.HTTP_HEADER_USERID);

String organization = "";
if(ConstValue.userToOrgMap.containsKey(userId))
	organization = ConstValue.userToOrgMap.get(userId);

ArrayList<String> alOrg = new ArrayList<String>(); 

if(organization != null)
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

        List<Community> listObj = communityService.findByFilter(hqlFilter);
        JSONArray jsonArr = new JSONArray();
        int iTotalCnt = 0;
		for(int i=0;i<listObj.size();i++)
		{
			Community community = listObj.get(i);
			JSONObject jsonTmp = new JSONObject();
			jsonTmp.put("id", community.getId());
			jsonTmp.put("dataid",community.getdataid());
			jsonTmp.put("name",community.getname());
			jsonTmp.put("buildtype",community.getbuildtype());
			jsonTmp.put("type",community.gettype());
			if(community.getyear() != null)
				jsonTmp.put("year",TimeUtil.formatDate(community.getyear(),"yyyy-MM-dd"));
			else
				jsonTmp.put("year","-");
			jsonTmp.put("buildings",community.getbuildings());
			jsonTmp.put("gates",community.getgates());
			jsonTmp.put("groundparking",community.getgroundparking());
			jsonTmp.put("underparking",community.getunderparking());
			//jsonTmp.put("longitude",community.getlongitude());
			//jsonTmp.put("latitude",community.getlatitude());
			jsonTmp.put("status",community.getstatus());
			jsonTmp.put("address",community.getaddress());
			jsonTmp.put("pictures",community.getpictures());
			jsonTmp.put("note",community.getnote());
			//jsonTmp.put("duoxuan",community.getduoxuan());

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
		Community community = communityService.getById(id);
		if(community != null)
		{
			jsonObj.put("dataid",community.getdataid());
			jsonObj.put("name",community.getname());
			jsonObj.put("buildtype",community.getbuildtype());
			jsonObj.put("type",community.gettype());
			jsonObj.put("year",TimeUtil.formatDate(community.getyear(),"yyyy-MM-dd"));
			jsonObj.put("buildings",community.getbuildings());
			jsonObj.put("gates",community.getgates());
			jsonObj.put("groundparking",community.getgroundparking());
			jsonObj.put("underparking",community.getunderparking());
			//jsonObj.put("longitude",community.getlongitude());
			//jsonObj.put("latitude",community.getlatitude());
			jsonObj.put("status",community.getstatus());
			jsonObj.put("address",community.getaddress());
			jsonObj.put("pictures",community.getpictures());
			jsonObj.put("note",community.getnote());
			//jsonObj.put("duoxuan",community.getduoxuan());

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
