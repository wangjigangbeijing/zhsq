package com.template.busi.controller.jcsqsj;
import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import com.template.model.jcsqsj.Jc_pubfacilities_gy;
import com.template.service.jcsqsj.Jc_pubfacilities_gyService;
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
@RequestMapping("jc_pubfacilities_gyController")
public class Jc_pubfacilities_gyController {
	private static Logger logger = Logger.getLogger(Jc_pubfacilities_gyController.class);
	@Autowired
	private  HttpServletRequest request;
	@Autowired
	private Jc_pubfacilities_gyService jc_pubfacilities_gyService;
@RequestMapping(value="addOrUpdate",method = RequestMethod.POST,produces="text/html;charset=UTF-8")
@ResponseBody
public String addOrUpdate(String id,String dateid,String type,String objid,String objname,String locatedsc,String deptname1,String deptname2,String deptname3,String isincommunity,String material,String form,String objState,String pictures,String note)
{
	JSONObject jsonObj = new JSONObject();
	try
	{
		Jc_pubfacilities_gy jc_pubfacilities_gy;
		if(id==null || id.equalsIgnoreCase(""))
		{
			jc_pubfacilities_gy = new Jc_pubfacilities_gy();
			jc_pubfacilities_gy.setId(Utility.getUniStr());
		}
		else
		{
			jc_pubfacilities_gy = jc_pubfacilities_gyService.getById(id);
		}
		jc_pubfacilities_gy.setdateid(dateid);
		jc_pubfacilities_gy.settype(type);
		jc_pubfacilities_gy.setobjid(objid);
		jc_pubfacilities_gy.setobjname(objname);
		jc_pubfacilities_gy.setlocatedsc(locatedsc);
		jc_pubfacilities_gy.setdeptname1(deptname1);
		jc_pubfacilities_gy.setdeptname2(deptname2);
		jc_pubfacilities_gy.setdeptname3(deptname3);
		jc_pubfacilities_gy.setisincommunity(isincommunity);
		jc_pubfacilities_gy.setmaterial(material);
		jc_pubfacilities_gy.setform(form);
		jc_pubfacilities_gy.setobjState(objState);
		jc_pubfacilities_gy.setpictures(pictures);
		jc_pubfacilities_gy.setnote(note);

		String userId = (String)request.getSession().getAttribute(ConstValue.SESSION_USER_ID);
		
		String organization = "";
		if(ConstValue.userToOrgMap.containsKey(userId))
			organization = ConstValue.userToOrgMap.get(userId);
		jc_pubfacilities_gy.setowner(organization);
		
        jc_pubfacilities_gyService.save(jc_pubfacilities_gy);
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
		Jc_pubfacilities_gy jc_pubfacilities_gy = jc_pubfacilities_gyService.getById(id);
		jc_pubfacilities_gyService.delete(jc_pubfacilities_gy);
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
public String load(String type,String objname,String deptname1,String isincommunity,String material,String form,String objState)
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
if(type != null && type.equalsIgnoreCase("") == false && type.equalsIgnoreCase("undefined") == false)
{
	hqlFilter.addQryCond("type", HqlFilter.Operator.EQ, type);
}
if(objname != null && objname.equalsIgnoreCase("") == false && objname.equalsIgnoreCase("undefined") == false)
{
	hqlFilter.addQryCond("objname", HqlFilter.Operator.LIKE, "%"+objname+"%");
}
if(deptname1 != null && deptname1.equalsIgnoreCase("") == false && deptname1.equalsIgnoreCase("undefined") == false)
{
	hqlFilter.addQryCond("deptname1", HqlFilter.Operator.LIKE, "%"+deptname1+"%");
}
if(isincommunity != null && isincommunity.equalsIgnoreCase("") == false && isincommunity.equalsIgnoreCase("undefined") == false)
{
	hqlFilter.addQryCond("isincommunity", HqlFilter.Operator.LIKE, "%"+isincommunity+"%");
}
if(material != null && material.equalsIgnoreCase("") == false && material.equalsIgnoreCase("undefined") == false)
{
	hqlFilter.addQryCond("material", HqlFilter.Operator.EQ, material);
}
if(form != null && form.equalsIgnoreCase("") == false && form.equalsIgnoreCase("undefined") == false)
{
	hqlFilter.addQryCond("form", HqlFilter.Operator.EQ, form);
}
if(objState != null && objState.equalsIgnoreCase("") == false && objState.equalsIgnoreCase("undefined") == false)
{
	hqlFilter.addQryCond("objState", HqlFilter.Operator.EQ, objState);
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

        List<Jc_pubfacilities_gy> listObj = jc_pubfacilities_gyService.findByFilter(hqlFilter);
        JSONArray jsonArr = new JSONArray();

		long iTotalCnt = jc_pubfacilities_gyService.countByFilter(hqlFilter);
		
		for(int i=0;i<listObj.size();i++)
		{
			Jc_pubfacilities_gy jc_pubfacilities_gy = listObj.get(i);
			JSONObject jsonTmp = new JSONObject();
			jsonTmp.put("id", jc_pubfacilities_gy.getId());
			jsonTmp.put("dateid",jc_pubfacilities_gy.getdateid());
			jsonTmp.put("type",jc_pubfacilities_gy.gettype());
			jsonTmp.put("objid",jc_pubfacilities_gy.getobjid());
			jsonTmp.put("objname",jc_pubfacilities_gy.getobjname());
			jsonTmp.put("locatedsc",jc_pubfacilities_gy.getlocatedsc());
			jsonTmp.put("deptname1",jc_pubfacilities_gy.getdeptname1());
			jsonTmp.put("deptname2",jc_pubfacilities_gy.getdeptname2());
			jsonTmp.put("deptname3",jc_pubfacilities_gy.getdeptname3());
			jsonTmp.put("isincommunity",jc_pubfacilities_gy.getisincommunity());
			jsonTmp.put("material",jc_pubfacilities_gy.getmaterial());
			jsonTmp.put("form",jc_pubfacilities_gy.getform());
			jsonTmp.put("objState",jc_pubfacilities_gy.getobjState());
			jsonTmp.put("pictures",jc_pubfacilities_gy.getpictures());
			jsonTmp.put("note",jc_pubfacilities_gy.getnote());

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
		Jc_pubfacilities_gy jc_pubfacilities_gy = jc_pubfacilities_gyService.getById(id);
		if(jc_pubfacilities_gy != null)
		{
			jsonObj.put("dateid",jc_pubfacilities_gy.getdateid());
			jsonObj.put("type",jc_pubfacilities_gy.gettype());
			jsonObj.put("objid",jc_pubfacilities_gy.getobjid());
			jsonObj.put("objname",jc_pubfacilities_gy.getobjname());
			jsonObj.put("locatedsc",jc_pubfacilities_gy.getlocatedsc());
			jsonObj.put("deptname1",jc_pubfacilities_gy.getdeptname1());
			jsonObj.put("deptname2",jc_pubfacilities_gy.getdeptname2());
			jsonObj.put("deptname3",jc_pubfacilities_gy.getdeptname3());
			jsonObj.put("isincommunity",jc_pubfacilities_gy.getisincommunity());
			jsonObj.put("material",jc_pubfacilities_gy.getmaterial());
			jsonObj.put("form",jc_pubfacilities_gy.getform());
			jsonObj.put("objState",jc_pubfacilities_gy.getobjState());
			jsonObj.put("pictures",jc_pubfacilities_gy.getpictures());
			jsonObj.put("note",jc_pubfacilities_gy.getnote());

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
