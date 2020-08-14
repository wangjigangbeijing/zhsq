package com.template.busi.controller.jcsqsj;
import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import com.template.model.jcsqsj.Jc_pubfacilities_lh;
import com.template.service.jcsqsj.Jc_pubfacilities_lhService;
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
@RequestMapping("jc_pubfacilities_lhController")
public class Jc_pubfacilities_lhController {
	private static Logger logger = Logger.getLogger(Jc_pubfacilities_lhController.class);
	@Autowired
	private  HttpServletRequest request;
	@Autowired
	private Jc_pubfacilities_lhService jc_pubfacilities_lhService;
@RequestMapping(value="addOrUpdate",method = RequestMethod.POST,produces="text/html;charset=UTF-8")
@ResponseBody
public String addOrUpdate(String id,String dateid,String type,String objid,String objname,String locatedsc,String deptname1,String deptname2,String deptname3,String isincommunity,String material,String form,String objState,String pictures,String note)
{
	JSONObject jsonObj = new JSONObject();
	try
	{
		Jc_pubfacilities_lh jc_pubfacilities_lh;
		if(id==null || id.equalsIgnoreCase(""))
		{
			jc_pubfacilities_lh = new Jc_pubfacilities_lh();
			jc_pubfacilities_lh.setId(Utility.getUniStr());
		}
		else
		{
			jc_pubfacilities_lh = jc_pubfacilities_lhService.getById(id);
		}
		jc_pubfacilities_lh.setdateid(dateid);
		jc_pubfacilities_lh.settype(type);
		jc_pubfacilities_lh.setobjid(objid);
		jc_pubfacilities_lh.setobjname(objname);
		jc_pubfacilities_lh.setlocatedsc(locatedsc);
		jc_pubfacilities_lh.setdeptname1(deptname1);
		jc_pubfacilities_lh.setdeptname2(deptname2);
		jc_pubfacilities_lh.setdeptname3(deptname3);
		jc_pubfacilities_lh.setisincommunity(isincommunity);
		jc_pubfacilities_lh.setmaterial(material);
		jc_pubfacilities_lh.setform(form);
		jc_pubfacilities_lh.setobjState(objState);
		jc_pubfacilities_lh.setpictures(pictures);
		jc_pubfacilities_lh.setnote(note);

		String userId = (String)request.getSession().getAttribute(ConstValue.SESSION_USER_ID);
		
		String organization = "";
		if(ConstValue.userToOrgMap.containsKey(userId))
			organization = ConstValue.userToOrgMap.get(userId);
		jc_pubfacilities_lh.setowner(organization);
		
        jc_pubfacilities_lhService.save(jc_pubfacilities_lh);
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
		Jc_pubfacilities_lh jc_pubfacilities_lh = jc_pubfacilities_lhService.getById(id);
		jc_pubfacilities_lhService.delete(jc_pubfacilities_lh);
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

        List<Jc_pubfacilities_lh> listObj = jc_pubfacilities_lhService.findByFilter(hqlFilter);
        JSONArray jsonArr = new JSONArray();
        int iTotalCnt = 0;
		for(int i=0;i<listObj.size();i++)
		{
			Jc_pubfacilities_lh jc_pubfacilities_lh = listObj.get(i);
			JSONObject jsonTmp = new JSONObject();
			jsonTmp.put("id", jc_pubfacilities_lh.getId());
			jsonTmp.put("dateid",jc_pubfacilities_lh.getdateid());
			jsonTmp.put("type",jc_pubfacilities_lh.gettype());
			jsonTmp.put("objid",jc_pubfacilities_lh.getobjid());
			jsonTmp.put("objname",jc_pubfacilities_lh.getobjname());
			jsonTmp.put("locatedsc",jc_pubfacilities_lh.getlocatedsc());
			jsonTmp.put("deptname1",jc_pubfacilities_lh.getdeptname1());
			jsonTmp.put("deptname2",jc_pubfacilities_lh.getdeptname2());
			jsonTmp.put("deptname3",jc_pubfacilities_lh.getdeptname3());
			jsonTmp.put("isincommunity",jc_pubfacilities_lh.getisincommunity());
			jsonTmp.put("material",jc_pubfacilities_lh.getmaterial());
			jsonTmp.put("form",jc_pubfacilities_lh.getform());
			jsonTmp.put("objState",jc_pubfacilities_lh.getobjState());
			jsonTmp.put("pictures",jc_pubfacilities_lh.getpictures());
			jsonTmp.put("note",jc_pubfacilities_lh.getnote());

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
		Jc_pubfacilities_lh jc_pubfacilities_lh = jc_pubfacilities_lhService.getById(id);
		if(jc_pubfacilities_lh != null)
		{
			jsonObj.put("dateid",jc_pubfacilities_lh.getdateid());
			jsonObj.put("type",jc_pubfacilities_lh.gettype());
			jsonObj.put("objid",jc_pubfacilities_lh.getobjid());
			jsonObj.put("objname",jc_pubfacilities_lh.getobjname());
			jsonObj.put("locatedsc",jc_pubfacilities_lh.getlocatedsc());
			jsonObj.put("deptname1",jc_pubfacilities_lh.getdeptname1());
			jsonObj.put("deptname2",jc_pubfacilities_lh.getdeptname2());
			jsonObj.put("deptname3",jc_pubfacilities_lh.getdeptname3());
			jsonObj.put("isincommunity",jc_pubfacilities_lh.getisincommunity());
			jsonObj.put("material",jc_pubfacilities_lh.getmaterial());
			jsonObj.put("form",jc_pubfacilities_lh.getform());
			jsonObj.put("objState",jc_pubfacilities_lh.getobjState());
			jsonObj.put("pictures",jc_pubfacilities_lh.getpictures());
			jsonObj.put("note",jc_pubfacilities_lh.getnote());

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
