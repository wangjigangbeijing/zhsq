package com.template.busi.controller.jcsqsj;
import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import com.template.model.jcsqsj.Jc_advertisement;
import com.template.service.jcsqsj.Jc_advertisementService;
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
@RequestMapping("jc_advertisementController")
public class Jc_advertisementController {
	private static Logger logger = Logger.getLogger(Jc_advertisementController.class);
	@Autowired
	private  HttpServletRequest request;
	@Autowired
	private Jc_advertisementService jc_advertisementService;
@RequestMapping(value="addOrUpdate",method = RequestMethod.POST,produces="text/html;charset=UTF-8")
@ResponseBody
public String addOrUpdate(String id,String name,String type,String address,String level,String unit,String tel,String picture,String note)
{
	JSONObject jsonObj = new JSONObject();
	try
	{
		Jc_advertisement jc_advertisement;
		if(id==null || id.equalsIgnoreCase(""))
		{
			jc_advertisement = new Jc_advertisement();
			jc_advertisement.setId(Utility.getUniStr());
		}
		else
		{
			jc_advertisement = jc_advertisementService.getById(id);
		}
		jc_advertisement.setname(name);
		jc_advertisement.settype(type);
		jc_advertisement.setaddress(address);
		jc_advertisement.setlevel(level);
		jc_advertisement.setunit(unit);
		jc_advertisement.settel(tel);
		jc_advertisement.setpicture(picture);
		jc_advertisement.setnote(note);

        jc_advertisementService.save(jc_advertisement);
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
		Jc_advertisement jc_advertisement = jc_advertisementService.getById(id);
		jc_advertisementService.delete(jc_advertisement);
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
public String load(String name,String type,String level,String unit)
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
if(level != null && level.equalsIgnoreCase("") == false && level.equalsIgnoreCase("null") == false)
{
	hqlFilter.addQryCond("level", HqlFilter.Operator.EQ, level);
}
if(unit != null && unit.equalsIgnoreCase("") == false && unit.equalsIgnoreCase("null") == false)
{
	hqlFilter.addQryCond("unit", HqlFilter.Operator.LIKE, "%"+unit+"%");
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

        List<Jc_advertisement> listObj = jc_advertisementService.findByFilter(hqlFilter);
        JSONArray jsonArr = new JSONArray();
        int iTotalCnt = 0;
		for(int i=0;i<listObj.size();i++)
		{
			Jc_advertisement jc_advertisement = listObj.get(i);
			JSONObject jsonTmp = new JSONObject();
			jsonTmp.put("id", jc_advertisement.getId());
			jsonTmp.put("name",jc_advertisement.getname());
			jsonTmp.put("type",jc_advertisement.gettype());
			jsonTmp.put("address",jc_advertisement.getaddress());
			jsonTmp.put("level",jc_advertisement.getlevel());
			jsonTmp.put("unit",jc_advertisement.getunit());
			jsonTmp.put("tel",jc_advertisement.gettel());
			jsonTmp.put("picture",jc_advertisement.getpicture());
			jsonTmp.put("note",jc_advertisement.getnote());

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
		Jc_advertisement jc_advertisement = jc_advertisementService.getById(id);
		if(jc_advertisement != null)
		{
			jsonObj.put("name",jc_advertisement.getname());
			jsonObj.put("type",jc_advertisement.gettype());
			jsonObj.put("address",jc_advertisement.getaddress());
			jsonObj.put("level",jc_advertisement.getlevel());
			jsonObj.put("unit",jc_advertisement.getunit());
			jsonObj.put("tel",jc_advertisement.gettel());
			jsonObj.put("picture",jc_advertisement.getpicture());
			jsonObj.put("note",jc_advertisement.getnote());

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
