package com.template.busi.controller.jcsqsj;
import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import com.template.model.jcsqsj.Service_store;
import com.template.service.jcsqsj.Service_storeService;
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
@RequestMapping("service_storeController")
public class Service_storeController {
	private static Logger logger = Logger.getLogger(Service_storeController.class);
	@Autowired
	private  HttpServletRequest request;
	@Autowired
	private Service_storeService service_storeService;
@RequestMapping(value="addOrUpdate",method = RequestMethod.POST,produces="text/html;charset=UTF-8")
@ResponseBody
public String addOrUpdate(String id,String dateid,String name,String type,String address,
		String socialcode,String businessscope,Integer businessarea,String ischain,String otherbusiness,
		String contact,String contacttel, String opentime, String closetime,String is24hours,Integer longitude,Integer latitude,String status,String pictures,String note)
{
	JSONObject jsonObj = new JSONObject();
	try
	{
		Service_store service_store;
		if(id==null || id.equalsIgnoreCase(""))
		{
			service_store = new Service_store();
			service_store.setId(Utility.getUniStr());
		}
		else
		{
			service_store = service_storeService.getById(id);
		}
		service_store.setdateid(dateid);
		service_store.setname(name);
		service_store.settype(type);
		service_store.setaddress(address);
		service_store.setsocialcode(socialcode);
		service_store.setbusinessscope(businessscope);
		service_store.setbusinessarea(businessarea);
		service_store.setischain(ischain);
		service_store.setotherbusiness(otherbusiness);
		service_store.setcontact(contact);
		service_store.setcontacttel(contacttel);
		//service_store.setopentime(opentime);
		//service_store.setclosetime(closetime);
		service_store.setis24hours(is24hours);
		//service_store.setlongitude(longitude);
		//service_store.setlatitude(latitude);
		service_store.setstatus(status);
		service_store.setpictures(pictures);
		service_store.setnote(note);

		String userId = (String)request.getSession().getAttribute(ConstValue.SESSION_USER_ID);
		
		String organization = "";
		if(ConstValue.userToOrgMap.containsKey(userId))
			organization = ConstValue.userToOrgMap.get(userId);
		service_store.setowner(organization);
		
        service_storeService.save(service_store);
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
		Service_store service_store = service_storeService.getById(id);
		service_storeService.delete(service_store);
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
public String load(String name,String type,String address,String socialcode,String ischain,String contact,String is24hours,String status)
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
if(socialcode != null && socialcode.equalsIgnoreCase("") == false && socialcode.equalsIgnoreCase("null") == false)
{
	hqlFilter.addQryCond("socialcode", HqlFilter.Operator.LIKE, "%"+socialcode+"%");
}
if(ischain != null && ischain.equalsIgnoreCase("") == false && ischain.equalsIgnoreCase("null") == false)
{
	hqlFilter.addQryCond("ischain", HqlFilter.Operator.LIKE, "%"+ischain+"%");
}
if(contact != null && contact.equalsIgnoreCase("") == false && contact.equalsIgnoreCase("null") == false)
{
	hqlFilter.addQryCond("contact", HqlFilter.Operator.LIKE, "%"+contact+"%");
}
if(is24hours != null && is24hours.equalsIgnoreCase("") == false && is24hours.equalsIgnoreCase("null") == false)
{
	hqlFilter.addQryCond("is24hours", HqlFilter.Operator.LIKE, "%"+is24hours+"%");
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

        List<Service_store> listObj = service_storeService.findByFilter(hqlFilter);
        JSONArray jsonArr = new JSONArray();
        int iTotalCnt = 0;
		for(int i=0;i<listObj.size();i++)
		{
			Service_store service_store = listObj.get(i);
			JSONObject jsonTmp = new JSONObject();
			jsonTmp.put("id", service_store.getId());
			jsonTmp.put("dateid",service_store.getdateid());
			jsonTmp.put("name",service_store.getname());
			jsonTmp.put("type",service_store.gettype());
			jsonTmp.put("address",service_store.getaddress());
			jsonTmp.put("socialcode",service_store.getsocialcode());
			jsonTmp.put("businessscope",service_store.getbusinessscope());
			jsonTmp.put("businessarea",service_store.getbusinessarea());
			jsonTmp.put("ischain",service_store.getischain());
			jsonTmp.put("otherbusiness",service_store.getotherbusiness());
			jsonTmp.put("contact",service_store.getcontact());
			jsonTmp.put("contacttel",service_store.getcontacttel());
			//jsonTmp.put("opentime",service_store.getopentime());
			//jsonTmp.put("closetime",service_store.getclosetime());
			jsonTmp.put("is24hours",service_store.getis24hours());
			//jsonTmp.put("longitude",service_store.getlongitude());
			//jsonTmp.put("latitude",service_store.getlatitude());
			jsonTmp.put("status",service_store.getstatus());
			jsonTmp.put("pictures",service_store.getpictures());
			jsonTmp.put("note",service_store.getnote());

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
		Service_store service_store = service_storeService.getById(id);
		if(service_store != null)
		{
			jsonObj.put("dateid",service_store.getdateid());
			jsonObj.put("name",service_store.getname());
			jsonObj.put("type",service_store.gettype());
			jsonObj.put("address",service_store.getaddress());
			jsonObj.put("socialcode",service_store.getsocialcode());
			jsonObj.put("businessscope",service_store.getbusinessscope());
			jsonObj.put("businessarea",service_store.getbusinessarea());
			jsonObj.put("ischain",service_store.getischain());
			jsonObj.put("otherbusiness",service_store.getotherbusiness());
			jsonObj.put("contact",service_store.getcontact());
			jsonObj.put("contacttel",service_store.getcontacttel());
			//jsonObj.put("opentime",service_store.getopentime());
			//jsonObj.put("closetime",service_store.getclosetime());
			jsonObj.put("is24hours",service_store.getis24hours());
			//jsonObj.put("longitude",service_store.getlongitude());
			//jsonObj.put("latitude",service_store.getlatitude());
			jsonObj.put("status",service_store.getstatus());
			jsonObj.put("pictures",service_store.getpictures());
			jsonObj.put("note",service_store.getnote());

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
