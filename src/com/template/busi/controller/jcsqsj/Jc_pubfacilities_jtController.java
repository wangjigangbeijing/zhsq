package com.template.busi.controller.jcsqsj;
import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import com.template.model.jcsqsj.Jc_pubfacilities_jt;
import com.template.service.jcsqsj.Jc_pubfacilities_jtService;
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
@RequestMapping("jc_pubfacilities_jtController")
public class Jc_pubfacilities_jtController {
	private static Logger logger = Logger.getLogger(Jc_pubfacilities_jtController.class);
	@Autowired
	private  HttpServletRequest request;
	@Autowired
	private Jc_pubfacilities_jtService jc_pubfacilities_jtService;
@RequestMapping(value="addOrUpdate",method = RequestMethod.POST,produces="text/html;charset=UTF-8")
@ResponseBody
public String addOrUpdate(String id,String dateid,String type,String objid,String objname,String locatedsc,String deptname1,String deptname2,String deptname3,String isincommunity,String material,String form,String objState,String pictures,String note)
{
	JSONObject jsonObj = new JSONObject();
	try
	{
		Jc_pubfacilities_jt jc_pubfacilities_jt;
		if(id==null || id.equalsIgnoreCase(""))
		{
			jc_pubfacilities_jt = new Jc_pubfacilities_jt();
			jc_pubfacilities_jt.setId(Utility.getUniStr());
		}
		else
		{
			jc_pubfacilities_jt = jc_pubfacilities_jtService.getById(id);
		}
		jc_pubfacilities_jt.setdateid(dateid);
		jc_pubfacilities_jt.settype(type);
		jc_pubfacilities_jt.setobjid(objid);
		jc_pubfacilities_jt.setobjname(objname);
		jc_pubfacilities_jt.setlocatedsc(locatedsc);
		jc_pubfacilities_jt.setdeptname1(deptname1);
		jc_pubfacilities_jt.setdeptname2(deptname2);
		jc_pubfacilities_jt.setdeptname3(deptname3);
		jc_pubfacilities_jt.setisincommunity(isincommunity);
		jc_pubfacilities_jt.setmaterial(material);
		jc_pubfacilities_jt.setform(form);
		jc_pubfacilities_jt.setobjState(objState);
		jc_pubfacilities_jt.setpictures(pictures);
		jc_pubfacilities_jt.setnote(note);

		String userId = (String)request.getSession().getAttribute(ConstValue.SESSION_USER_ID);
		
		String organization = "";
		if(ConstValue.userToOrgMap.containsKey(userId))
			organization = ConstValue.userToOrgMap.get(userId);
		jc_pubfacilities_jt.setowner(organization);
		
        jc_pubfacilities_jtService.save(jc_pubfacilities_jt);
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
		Jc_pubfacilities_jt jc_pubfacilities_jt = jc_pubfacilities_jtService.getById(id);
		jc_pubfacilities_jtService.delete(jc_pubfacilities_jt);
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
		HqlFilter hqlFilter = new HqlFilter();
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

        List<Jc_pubfacilities_jt> listObj = jc_pubfacilities_jtService.findByFilter(hqlFilter);
        JSONArray jsonArr = new JSONArray();
        int iTotalCnt = 0;
		for(int i=0;i<listObj.size();i++)
		{
			Jc_pubfacilities_jt jc_pubfacilities_jt = listObj.get(i);
			JSONObject jsonTmp = new JSONObject();
			jsonTmp.put("id", jc_pubfacilities_jt.getId());
			jsonTmp.put("dateid",jc_pubfacilities_jt.getdateid());
			jsonTmp.put("type",jc_pubfacilities_jt.gettype());
			jsonTmp.put("objid",jc_pubfacilities_jt.getobjid());
			jsonTmp.put("objname",jc_pubfacilities_jt.getobjname());
			jsonTmp.put("locatedsc",jc_pubfacilities_jt.getlocatedsc());
			jsonTmp.put("deptname1",jc_pubfacilities_jt.getdeptname1());
			jsonTmp.put("deptname2",jc_pubfacilities_jt.getdeptname2());
			jsonTmp.put("deptname3",jc_pubfacilities_jt.getdeptname3());
			jsonTmp.put("isincommunity",jc_pubfacilities_jt.getisincommunity());
			jsonTmp.put("material",jc_pubfacilities_jt.getmaterial());
			jsonTmp.put("form",jc_pubfacilities_jt.getform());
			jsonTmp.put("objState",jc_pubfacilities_jt.getobjState());
			jsonTmp.put("pictures",jc_pubfacilities_jt.getpictures());
			jsonTmp.put("note",jc_pubfacilities_jt.getnote());

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
		Jc_pubfacilities_jt jc_pubfacilities_jt = jc_pubfacilities_jtService.getById(id);
		if(jc_pubfacilities_jt != null)
		{
			jsonObj.put("dateid",jc_pubfacilities_jt.getdateid());
			jsonObj.put("type",jc_pubfacilities_jt.gettype());
			jsonObj.put("objid",jc_pubfacilities_jt.getobjid());
			jsonObj.put("objname",jc_pubfacilities_jt.getobjname());
			jsonObj.put("locatedsc",jc_pubfacilities_jt.getlocatedsc());
			jsonObj.put("deptname1",jc_pubfacilities_jt.getdeptname1());
			jsonObj.put("deptname2",jc_pubfacilities_jt.getdeptname2());
			jsonObj.put("deptname3",jc_pubfacilities_jt.getdeptname3());
			jsonObj.put("isincommunity",jc_pubfacilities_jt.getisincommunity());
			jsonObj.put("material",jc_pubfacilities_jt.getmaterial());
			jsonObj.put("form",jc_pubfacilities_jt.getform());
			jsonObj.put("objState",jc_pubfacilities_jt.getobjState());
			jsonObj.put("pictures",jc_pubfacilities_jt.getpictures());
			jsonObj.put("note",jc_pubfacilities_jt.getnote());

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
