package com.template.busi.controller.jcsqsj;
import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import com.template.model.jcsqsj.Watersystem;
import com.template.service.jcsqsj.WatersystemService;
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
@RequestMapping("watersystemController")
public class WatersystemController {
	private static Logger logger = Logger.getLogger(WatersystemController.class);
	@Autowired
	private  HttpServletRequest request;
	@Autowired
	private WatersystemService watersystemService;
@RequestMapping(value="addOrUpdate",method = RequestMethod.POST,produces="text/html;charset=UTF-8")
@ResponseBody
public String addOrUpdate(String id,String dateid,String name,String deptname1,String deptname2,String deptname3,
		String leadername,String leadertel,String leaderorg,Integer longitude,Integer latitude,String status,String pictures,String note)
{
	JSONObject jsonObj = new JSONObject();
	try
	{
		Watersystem watersystem;
		if(id==null || id.equalsIgnoreCase(""))
		{
			watersystem = new Watersystem();
			watersystem.setId(Utility.getUniStr());
		}
		else
		{
			watersystem = watersystemService.getById(id);
		}
		watersystem.setdateid(dateid);
		watersystem.setname(name);
		watersystem.setdeptname1(deptname1);
		watersystem.setdeptname2(deptname2);
		watersystem.setdeptname3(deptname3);
		watersystem.setleadername(leadername);
		watersystem.setleadertel(leadertel);
		watersystem.setleaderorg(leaderorg);
		//watersystem.setlongitude(longitude);
		//watersystem.setlatitude(latitude);
		watersystem.setstatus(status);
		watersystem.setpictures(pictures);
		watersystem.setnote(note);

		String userId = (String)request.getSession().getAttribute(ConstValue.SESSION_USER_ID);
		
		String organization = "";
		if(ConstValue.userToOrgMap.containsKey(userId))
			organization = ConstValue.userToOrgMap.get(userId);
		watersystem.setowner(organization);
		
        watersystemService.save(watersystem);
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
		Watersystem watersystem = watersystemService.getById(id);
		watersystemService.delete(watersystem);
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
public String load(String name,String leadername,String status)
{
	JSONObject jsonObj = new JSONObject();
	try
	{
		HqlFilter hqlFilter = new HqlFilter();
if(name != null && name.equalsIgnoreCase("") == false && name.equalsIgnoreCase("null") == false)
{
	hqlFilter.addQryCond("name", HqlFilter.Operator.LIKE, "%"+name+"%");
}
if(leadername != null && leadername.equalsIgnoreCase("") == false && leadername.equalsIgnoreCase("null") == false)
{
	hqlFilter.addQryCond("leadername", HqlFilter.Operator.LIKE, "%"+leadername+"%");
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

        List<Watersystem> listObj = watersystemService.findByFilter(hqlFilter);
        JSONArray jsonArr = new JSONArray();
        int iTotalCnt = 0;
		for(int i=0;i<listObj.size();i++)
		{
			Watersystem watersystem = listObj.get(i);
			JSONObject jsonTmp = new JSONObject();
			jsonTmp.put("id", watersystem.getId());
			jsonTmp.put("dateid",watersystem.getdateid());
			jsonTmp.put("name",watersystem.getname());
			jsonTmp.put("deptname1",watersystem.getdeptname1());
			jsonTmp.put("deptname2",watersystem.getdeptname2());
			jsonTmp.put("deptname3",watersystem.getdeptname3());
			jsonTmp.put("leadername",watersystem.getleadername());
			jsonTmp.put("leadertel",watersystem.getleadertel());
			jsonTmp.put("leaderorg",watersystem.getleaderorg());
			//jsonTmp.put("longitude",watersystem.getlongitude());
			//jsonTmp.put("latitude",watersystem.getlatitude());
			jsonTmp.put("status",watersystem.getstatus());
			jsonTmp.put("pictures",watersystem.getpictures());
			jsonTmp.put("note",watersystem.getnote());

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
		Watersystem watersystem = watersystemService.getById(id);
		if(watersystem != null)
		{
			jsonObj.put("dateid",watersystem.getdateid());
			jsonObj.put("name",watersystem.getname());
			jsonObj.put("deptname1",watersystem.getdeptname1());
			jsonObj.put("deptname2",watersystem.getdeptname2());
			jsonObj.put("deptname3",watersystem.getdeptname3());
			jsonObj.put("leadername",watersystem.getleadername());
			jsonObj.put("leadertel",watersystem.getleadertel());
			jsonObj.put("leaderorg",watersystem.getleaderorg());
			//jsonObj.put("longitude",watersystem.getlongitude());
			//jsonObj.put("latitude",watersystem.getlatitude());
			jsonObj.put("status",watersystem.getstatus());
			jsonObj.put("pictures",watersystem.getpictures());
			jsonObj.put("note",watersystem.getnote());

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
