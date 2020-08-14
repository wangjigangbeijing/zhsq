package com.template.busi.controller.jcsqsj;
import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import com.template.model.jcsqsj.Shelter;
import com.template.service.jcsqsj.ShelterService;
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
@RequestMapping("shelterController")
public class ShelterController {
	private static Logger logger = Logger.getLogger(ShelterController.class);
	@Autowired
	private  HttpServletRequest request;
	@Autowired
	private ShelterService shelterService;
@RequestMapping(value="addOrUpdate",method = RequestMethod.POST,produces="text/html;charset=UTF-8")
@ResponseBody
public String addOrUpdate(String id,String dateid,String name,String address,Integer area,Integer longitude,Integer latitude,String status,String pictures,String note)
{
	JSONObject jsonObj = new JSONObject();
	try
	{
		Shelter shelter;
		if(id==null || id.equalsIgnoreCase(""))
		{
			shelter = new Shelter();
			shelter.setId(Utility.getUniStr());
		}
		else
		{
			shelter = shelterService.getById(id);
		}
		shelter.setdateid(dateid);
		shelter.setname(name);
		shelter.setaddress(address);
		shelter.setarea(area);
		//shelter.setlongitude(longitude);
		//shelter.setlatitude(latitude);
		shelter.setstatus(status);
		shelter.setpictures(pictures);
		shelter.setnote(note);

		String userId = (String)request.getSession().getAttribute(ConstValue.SESSION_USER_ID);
		
		String organization = "";
		if(ConstValue.userToOrgMap.containsKey(userId))
			organization = ConstValue.userToOrgMap.get(userId);
		shelter.setowner(organization);

        shelterService.save(shelter);
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
		Shelter shelter = shelterService.getById(id);
		shelterService.delete(shelter);
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
public String load(String name,String address,String status)
{
	JSONObject jsonObj = new JSONObject();
	try
	{
		HqlFilter hqlFilter = new HqlFilter();
if(name != null && name.equalsIgnoreCase("") == false && name.equalsIgnoreCase("null") == false)
{
	hqlFilter.addQryCond("name", HqlFilter.Operator.LIKE, "%"+name+"%");
}
if(address != null && address.equalsIgnoreCase("") == false && address.equalsIgnoreCase("null") == false)
{
	hqlFilter.addQryCond("address", HqlFilter.Operator.LIKE, "%"+address+"%");
}
if(status != null && status.equalsIgnoreCase("") == false && status.equalsIgnoreCase("null") == false)
{
	hqlFilter.addQryCond("status", HqlFilter.Operator.LIKE, "%"+status+"%");
}

String userId = (String)request.getSession().getAttribute(ConstValue.HTTP_HEADER_USERID);

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


        List<Shelter> listObj = shelterService.findByFilter(hqlFilter);
        JSONArray jsonArr = new JSONArray();
        int iTotalCnt = 0;
		for(int i=0;i<listObj.size();i++)
		{
			Shelter shelter = listObj.get(i);
			JSONObject jsonTmp = new JSONObject();
			jsonTmp.put("id", shelter.getId());
			jsonTmp.put("dateid",shelter.getdateid());
			jsonTmp.put("name",shelter.getname());
			jsonTmp.put("address",shelter.getaddress());
			jsonTmp.put("area",shelter.getarea());
			//jsonTmp.put("longitude",shelter.getlongitude());
			//jsonTmp.put("latitude",shelter.getlatitude());
			jsonTmp.put("status",shelter.getstatus());
			jsonTmp.put("pictures",shelter.getpictures());
			jsonTmp.put("note",shelter.getnote());

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
		Shelter shelter = shelterService.getById(id);
		if(shelter != null)
		{
			jsonObj.put("dateid",shelter.getdateid());
			jsonObj.put("name",shelter.getname());
			jsonObj.put("address",shelter.getaddress());
			jsonObj.put("area",shelter.getarea());
			//jsonObj.put("longitude",shelter.getlongitude());
			//jsonObj.put("latitude",shelter.getlatitude());
			jsonObj.put("status",shelter.getstatus());
			jsonObj.put("pictures",shelter.getpictures());
			jsonObj.put("note",shelter.getnote());

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
