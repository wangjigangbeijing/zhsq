package com.template.busi.controller.jcsqsj;
import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import com.template.model.jcsqsj.Jc_tc_fjdctcw;
import com.template.service.jcsqsj.Jc_tc_fjdctcwService;
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
@RequestMapping("jc_tc_fjdctcwController")
public class Jc_tc_fjdctcwController {
	private static Logger logger = Logger.getLogger(Jc_tc_fjdctcwController.class);
	@Autowired
	private  HttpServletRequest request;
	@Autowired
	private Jc_tc_fjdctcwService jc_tc_fjdctcwService;
@RequestMapping(value="addOrUpdate",method = RequestMethod.POST,produces="text/html;charset=UTF-8")
@ResponseBody
public String addOrUpdate(String id,String parkID,String parkName,String parkType,String adminDep,String ownerDep,String maintDep,String picture,String note)
{
	JSONObject jsonObj = new JSONObject();
	try
	{
		Jc_tc_fjdctcw jc_tc_fjdctcw;
		if(id==null || id.equalsIgnoreCase(""))
		{
			jc_tc_fjdctcw = new Jc_tc_fjdctcw();
			jc_tc_fjdctcw.setId(Utility.getUniStr());
		}
		else
		{
			jc_tc_fjdctcw = jc_tc_fjdctcwService.getById(id);
		}
		//jc_tc_fjdctcw.setparkID(parkID);
		jc_tc_fjdctcw.setparkName(parkName);
		jc_tc_fjdctcw.setparkType(parkType);
		jc_tc_fjdctcw.setadminDep(adminDep);
		jc_tc_fjdctcw.setownerDep(ownerDep);
		jc_tc_fjdctcw.setmaintDep(maintDep);
		jc_tc_fjdctcw.setpicture(picture);
		jc_tc_fjdctcw.setnote(note);

        jc_tc_fjdctcwService.save(jc_tc_fjdctcw);
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
		Jc_tc_fjdctcw jc_tc_fjdctcw = jc_tc_fjdctcwService.getById(id);
		jc_tc_fjdctcwService.delete(jc_tc_fjdctcw);
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
public String load(String parkName,String parkType,String adminDep,String ownerDep,String maintDep)
{
	JSONObject jsonObj = new JSONObject();
	try
	{
		HqlFilter hqlFilter = new HqlFilter();
if(parkName != null && parkName.equalsIgnoreCase("") == false && parkName.equalsIgnoreCase("null") == false)
{
	hqlFilter.addQryCond("parkName", HqlFilter.Operator.LIKE, "%"+parkName+"%");
}
if(parkType != null && parkType.equalsIgnoreCase("") == false && parkType.equalsIgnoreCase("null") == false)
{
	hqlFilter.addQryCond("parkType", HqlFilter.Operator.LIKE, "%"+parkType+"%");
}
if(adminDep != null && adminDep.equalsIgnoreCase("") == false && adminDep.equalsIgnoreCase("null") == false)
{
	hqlFilter.addQryCond("adminDep", HqlFilter.Operator.LIKE, "%"+adminDep+"%");
}
if(ownerDep != null && ownerDep.equalsIgnoreCase("") == false && ownerDep.equalsIgnoreCase("null") == false)
{
	hqlFilter.addQryCond("ownerDep", HqlFilter.Operator.LIKE, "%"+ownerDep+"%");
}
if(maintDep != null && maintDep.equalsIgnoreCase("") == false && maintDep.equalsIgnoreCase("null") == false)
{
	hqlFilter.addQryCond("maintDep", HqlFilter.Operator.LIKE, "%"+maintDep+"%");
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

        List<Jc_tc_fjdctcw> listObj = jc_tc_fjdctcwService.findByFilter(hqlFilter);
        JSONArray jsonArr = new JSONArray();
        int iTotalCnt = 0;
		for(int i=0;i<listObj.size();i++)
		{
			Jc_tc_fjdctcw jc_tc_fjdctcw = listObj.get(i);
			JSONObject jsonTmp = new JSONObject();
			jsonTmp.put("id", jc_tc_fjdctcw.getId());
			//jsonTmp.put("parkID",jc_tc_fjdctcw.getparkID());
			jsonTmp.put("parkName",jc_tc_fjdctcw.getparkName());
			jsonTmp.put("parkType",jc_tc_fjdctcw.getparkType());
			jsonTmp.put("adminDep",jc_tc_fjdctcw.getadminDep());
			jsonTmp.put("ownerDep",jc_tc_fjdctcw.getownerDep());
			jsonTmp.put("maintDep",jc_tc_fjdctcw.getmaintDep());
			jsonTmp.put("picture",jc_tc_fjdctcw.getpicture());
			jsonTmp.put("note",jc_tc_fjdctcw.getnote());

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
		Jc_tc_fjdctcw jc_tc_fjdctcw = jc_tc_fjdctcwService.getById(id);
		if(jc_tc_fjdctcw != null)
		{
			//jsonObj.put("parkID",jc_tc_fjdctcw.getparkID());
			jsonObj.put("parkName",jc_tc_fjdctcw.getparkName());
			jsonObj.put("parkType",jc_tc_fjdctcw.getparkType());
			jsonObj.put("adminDep",jc_tc_fjdctcw.getadminDep());
			jsonObj.put("ownerDep",jc_tc_fjdctcw.getownerDep());
			jsonObj.put("maintDep",jc_tc_fjdctcw.getmaintDep());
			jsonObj.put("picture",jc_tc_fjdctcw.getpicture());
			jsonObj.put("note",jc_tc_fjdctcw.getnote());

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
