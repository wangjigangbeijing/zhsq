package com.template.busi.controller.jcsqsj;
import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import com.template.model.jcsqsj.Jc_xfss;
import com.template.service.jcsqsj.Jc_xfssService;
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
@RequestMapping("jc_xfssController")
public class Jc_xfssController {
	private static Logger logger = Logger.getLogger(Jc_xfssController.class);
	@Autowired
	private  HttpServletRequest request;
	@Autowired
	private Jc_xfssService jc_xfssService;
@RequestMapping(value="addOrUpdate",method = RequestMethod.POST,produces="text/html;charset=UTF-8")
@ResponseBody
public String addOrUpdate(String id,String dateid,String type,String objid,String objname,String deptname1,String deptname2,String deptname3,String isincommunity,String material,String form,String objState,String locatedsc,String pictures,String note)
{
	JSONObject jsonObj = new JSONObject();
	try
	{
		Jc_xfss jc_xfss;
		if(id==null || id.equalsIgnoreCase(""))
		{
			jc_xfss = new Jc_xfss();
			jc_xfss.setId(Utility.getUniStr());
		}
		else
		{
			jc_xfss = jc_xfssService.getById(id);
		}
		jc_xfss.setdateid(dateid);
		jc_xfss.settype(type);
		jc_xfss.setobjid(objid);
		jc_xfss.setobjname(objname);
		jc_xfss.setdeptname1(deptname1);
		jc_xfss.setdeptname2(deptname2);
		jc_xfss.setdeptname3(deptname3);
		jc_xfss.setisincommunity(isincommunity);
		jc_xfss.setmaterial(material);
		jc_xfss.setform(form);
		jc_xfss.setobjState(objState);
		jc_xfss.setlocatedsc(locatedsc);
		jc_xfss.setpictures(pictures);
		jc_xfss.setnote(note);

		String userId = (String)request.getSession().getAttribute(ConstValue.SESSION_USER_ID);
		
		String organization = "";
		if(ConstValue.userToOrgMap.containsKey(userId))
			organization = ConstValue.userToOrgMap.get(userId);
		jc_xfss.setowner(organization);
		
        jc_xfssService.save(jc_xfss);
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
		Jc_xfss jc_xfss = jc_xfssService.getById(id);
		jc_xfssService.delete(jc_xfss);
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
if(type != null && type.equalsIgnoreCase("") == false && type.equalsIgnoreCase("null") == false)
{
	hqlFilter.addQryCond("type", HqlFilter.Operator.LIKE, "%"+type+"%");
}
if(objname != null && objname.equalsIgnoreCase("") == false && objname.equalsIgnoreCase("null") == false)
{
	hqlFilter.addQryCond("objname", HqlFilter.Operator.LIKE, "%"+objname+"%");
}
if(deptname1 != null && deptname1.equalsIgnoreCase("") == false && deptname1.equalsIgnoreCase("null") == false)
{
	hqlFilter.addQryCond("deptname1", HqlFilter.Operator.LIKE, "%"+deptname1+"%");
}
if(isincommunity != null && isincommunity.equalsIgnoreCase("") == false && isincommunity.equalsIgnoreCase("null") == false)
{
	hqlFilter.addQryCond("isincommunity", HqlFilter.Operator.LIKE, "%"+isincommunity+"%");
}
if(material != null && material.equalsIgnoreCase("") == false && material.equalsIgnoreCase("null") == false)
{
	hqlFilter.addQryCond("material", HqlFilter.Operator.EQ, material);
}
if(form != null && form.equalsIgnoreCase("") == false && form.equalsIgnoreCase("null") == false)
{
	hqlFilter.addQryCond("form", HqlFilter.Operator.EQ, form);
}
if(objState != null && objState.equalsIgnoreCase("") == false && objState.equalsIgnoreCase("null") == false)
{
	hqlFilter.addQryCond("objState", HqlFilter.Operator.EQ, objState);
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

        List<Jc_xfss> listObj = jc_xfssService.findByFilter(hqlFilter);
        JSONArray jsonArr = new JSONArray();
        int iTotalCnt = 0;
		for(int i=0;i<listObj.size();i++)
		{
			Jc_xfss jc_xfss = listObj.get(i);
			JSONObject jsonTmp = new JSONObject();
			jsonTmp.put("id", jc_xfss.getId());
			jsonTmp.put("dateid",jc_xfss.getdateid());
			jsonTmp.put("type",jc_xfss.gettype());
			jsonTmp.put("objid",jc_xfss.getobjid());
			jsonTmp.put("objname",jc_xfss.getobjname());
			jsonTmp.put("deptname1",jc_xfss.getdeptname1());
			jsonTmp.put("deptname2",jc_xfss.getdeptname2());
			jsonTmp.put("deptname3",jc_xfss.getdeptname3());
			jsonTmp.put("isincommunity",jc_xfss.getisincommunity());
			jsonTmp.put("material",jc_xfss.getmaterial());
			jsonTmp.put("form",jc_xfss.getform());
			jsonTmp.put("objState",jc_xfss.getobjState());
			jsonTmp.put("locatedsc",jc_xfss.getlocatedsc());
			jsonTmp.put("pictures",jc_xfss.getpictures());
			jsonTmp.put("note",jc_xfss.getnote());

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
		Jc_xfss jc_xfss = jc_xfssService.getById(id);
		if(jc_xfss != null)
		{
			jsonObj.put("dateid",jc_xfss.getdateid());
			jsonObj.put("type",jc_xfss.gettype());
			jsonObj.put("objid",jc_xfss.getobjid());
			jsonObj.put("objname",jc_xfss.getobjname());
			jsonObj.put("deptname1",jc_xfss.getdeptname1());
			jsonObj.put("deptname2",jc_xfss.getdeptname2());
			jsonObj.put("deptname3",jc_xfss.getdeptname3());
			jsonObj.put("isincommunity",jc_xfss.getisincommunity());
			jsonObj.put("material",jc_xfss.getmaterial());
			jsonObj.put("form",jc_xfss.getform());
			jsonObj.put("objState",jc_xfss.getobjState());
			jsonObj.put("locatedsc",jc_xfss.getlocatedsc());
			jsonObj.put("pictures",jc_xfss.getpictures());
			jsonObj.put("note",jc_xfss.getnote());

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
