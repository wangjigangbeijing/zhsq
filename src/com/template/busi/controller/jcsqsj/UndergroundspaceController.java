package com.template.busi.controller.jcsqsj;
import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import com.template.model.jcsqsj.Undergroundspace;
import com.template.service.jcsqsj.UndergroundspaceService;
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
@RequestMapping("undergroundspaceController")
public class UndergroundspaceController {
	private static Logger logger = Logger.getLogger(UndergroundspaceController.class);
	@Autowired
	private  HttpServletRequest request;
	@Autowired
	private UndergroundspaceService undergroundspaceService;
@RequestMapping(value="addOrUpdate",method = RequestMethod.POST,produces="text/html;charset=UTF-8")
@ResponseBody
public String addOrUpdate(String id,String dateid,String name,String type,String address,String ofresidebuilding,String ofbizbuilding,String level,Integer area,String purpose,Integer longitude,Integer latitude,String status,String pictures,String note)
{
	JSONObject jsonObj = new JSONObject();
	try
	{
		Undergroundspace undergroundspace;
		if(id==null || id.equalsIgnoreCase(""))
		{
			undergroundspace = new Undergroundspace();
			undergroundspace.setId(Utility.getUniStr());
		}
		else
		{
			undergroundspace = undergroundspaceService.getById(id);
		}
		undergroundspace.setdateid(dateid);
		undergroundspace.setname(name);
		undergroundspace.settype(type);
		undergroundspace.setaddress(address);
		undergroundspace.setofresidebuilding(ofresidebuilding);
		undergroundspace.setofbizbuilding(ofbizbuilding);
		undergroundspace.setlevel(level);
		undergroundspace.setarea(area);
		undergroundspace.setpurpose(purpose);
		//undergroundspace.setlongitude(longitude);
		//undergroundspace.setlatitude(latitude);
		undergroundspace.setstatus(status);
		undergroundspace.setpictures(pictures);
		undergroundspace.setnote(note);

        undergroundspaceService.save(undergroundspace);
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
		Undergroundspace undergroundspace = undergroundspaceService.getById(id);
		undergroundspaceService.delete(undergroundspace);
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
public String load(String name,String type,String address,String ofresidebuilding,String ofbizbuilding,String status)
{
	JSONObject jsonObj = new JSONObject();
	try
	{
		HqlFilter hqlFilter = new HqlFilter();
if(name != null && name.equalsIgnoreCase("") == false && name.equalsIgnoreCase("null") == false)
{
	hqlFilter.addQryCond("name", HqlFilter.Operator.LIKE, "%"+name+"%");
}
if(type != null && type.equalsIgnoreCase("") == false && type.equalsIgnoreCase("null") == false)
{
	hqlFilter.addQryCond("type", HqlFilter.Operator.EQ, type);
}
if(address != null && address.equalsIgnoreCase("") == false && address.equalsIgnoreCase("null") == false)
{
	hqlFilter.addQryCond("address", HqlFilter.Operator.LIKE, "%"+address+"%");
}
if(ofresidebuilding != null && ofresidebuilding.equalsIgnoreCase("") == false && ofresidebuilding.equalsIgnoreCase("null") == false)
{
	hqlFilter.addQryCond("ofresidebuilding", HqlFilter.Operator.LIKE, "%"+ofresidebuilding+"%");
}
if(ofbizbuilding != null && ofbizbuilding.equalsIgnoreCase("") == false && ofbizbuilding.equalsIgnoreCase("null") == false)
{
	hqlFilter.addQryCond("ofbizbuilding", HqlFilter.Operator.LIKE, "%"+ofbizbuilding+"%");
}
if(status != null && status.equalsIgnoreCase("") == false && status.equalsIgnoreCase("null") == false)
{
	hqlFilter.addQryCond("status", HqlFilter.Operator.LIKE, "%"+status+"%");
}


String userId = (String)request.getSession().getAttribute(ConstValue.SESSION_USER_ID);

String organization = "";
if(ConstValue.userToOrgMap.containsKey(userId))
	organization = ConstValue.userToOrgMap.get(userId);

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

        List<Undergroundspace> listObj = undergroundspaceService.findByFilter(hqlFilter);
        JSONArray jsonArr = new JSONArray();
        int iTotalCnt = 0;
		for(int i=0;i<listObj.size();i++)
		{
			Undergroundspace undergroundspace = listObj.get(i);
			JSONObject jsonTmp = new JSONObject();
			jsonTmp.put("id", undergroundspace.getId());
			jsonTmp.put("dateid",undergroundspace.getdateid());
			jsonTmp.put("name",undergroundspace.getname());
			jsonTmp.put("type",undergroundspace.gettype());
			jsonTmp.put("address",undergroundspace.getaddress());
			jsonTmp.put("ofresidebuilding",undergroundspace.getofresidebuilding());
			jsonTmp.put("ofbizbuilding",undergroundspace.getofbizbuilding());
			jsonTmp.put("level",undergroundspace.getlevel());
			jsonTmp.put("area",undergroundspace.getarea());
			jsonTmp.put("purpose",undergroundspace.getpurpose());
			//jsonTmp.put("longitude",undergroundspace.getlongitude());
			//jsonTmp.put("latitude",undergroundspace.getlatitude());
			jsonTmp.put("status",undergroundspace.getstatus());
			jsonTmp.put("pictures",undergroundspace.getpictures());
			jsonTmp.put("note",undergroundspace.getnote());

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
		Undergroundspace undergroundspace = undergroundspaceService.getById(id);
		if(undergroundspace != null)
		{
			jsonObj.put("dateid",undergroundspace.getdateid());
			jsonObj.put("name",undergroundspace.getname());
			jsonObj.put("type",undergroundspace.gettype());
			jsonObj.put("address",undergroundspace.getaddress());
			jsonObj.put("ofresidebuilding",undergroundspace.getofresidebuilding());
			jsonObj.put("ofbizbuilding",undergroundspace.getofbizbuilding());
			jsonObj.put("level",undergroundspace.getlevel());
			jsonObj.put("area",undergroundspace.getarea());
			jsonObj.put("purpose",undergroundspace.getpurpose());
			//jsonObj.put("longitude",undergroundspace.getlongitude());
			//jsonObj.put("latitude",undergroundspace.getlatitude());
			jsonObj.put("status",undergroundspace.getstatus());
			jsonObj.put("pictures",undergroundspace.getpictures());
			jsonObj.put("note",undergroundspace.getnote());

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
