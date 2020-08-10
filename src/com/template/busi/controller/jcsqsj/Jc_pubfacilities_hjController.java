package com.template.busi.controller.jcsqsj;
import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import com.template.model.jcsqsj.Jc_pubfacilities_hj;
import com.template.service.jcsqsj.Jc_pubfacilities_hjService;
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
@RequestMapping("jc_pubfacilities_hjController")
public class Jc_pubfacilities_hjController {
	private static Logger logger = Logger.getLogger(Jc_pubfacilities_hjController.class);
	@Autowired
	private  HttpServletRequest request;
	@Autowired
	private Jc_pubfacilities_hjService jc_pubfacilities_hjService;
@RequestMapping(value="addOrUpdate",method = RequestMethod.POST,produces="text/html;charset=UTF-8")
@ResponseBody
public String addOrUpdate(String id,String dateid,String type,String objid,String objname,String locatedsc,String deptname1,String deptname2,String deptname3,String isincommunity,String material,String form,String objState,String pictures,String note)
{
	JSONObject jsonObj = new JSONObject();
	try
	{
		Jc_pubfacilities_hj jc_pubfacilities_hj;
		if(id==null || id.equalsIgnoreCase(""))
		{
			jc_pubfacilities_hj = new Jc_pubfacilities_hj();
			jc_pubfacilities_hj.setId(Utility.getUniStr());
		}
		else
		{
			jc_pubfacilities_hj = jc_pubfacilities_hjService.getById(id);
		}
		jc_pubfacilities_hj.setdateid(dateid);
		jc_pubfacilities_hj.settype(type);
		jc_pubfacilities_hj.setobjid(objid);
		jc_pubfacilities_hj.setobjname(objname);
		jc_pubfacilities_hj.setlocatedsc(locatedsc);
		jc_pubfacilities_hj.setdeptname1(deptname1);
		jc_pubfacilities_hj.setdeptname2(deptname2);
		jc_pubfacilities_hj.setdeptname3(deptname3);
		jc_pubfacilities_hj.setisincommunity(isincommunity);
		jc_pubfacilities_hj.setmaterial(material);
		jc_pubfacilities_hj.setform(form);
		jc_pubfacilities_hj.setobjState(objState);
		jc_pubfacilities_hj.setpictures(pictures);
		jc_pubfacilities_hj.setnote(note);

        jc_pubfacilities_hjService.save(jc_pubfacilities_hj);
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
		Jc_pubfacilities_hj jc_pubfacilities_hj = jc_pubfacilities_hjService.getById(id);
		jc_pubfacilities_hjService.delete(jc_pubfacilities_hj);
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
	hqlFilter.addQryCond("type", HqlFilter.Operator.EQ, type);
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

        List<Jc_pubfacilities_hj> listObj = jc_pubfacilities_hjService.findByFilter(hqlFilter);
        JSONArray jsonArr = new JSONArray();
        int iTotalCnt = 0;
		for(int i=0;i<listObj.size();i++)
		{
			Jc_pubfacilities_hj jc_pubfacilities_hj = listObj.get(i);
			JSONObject jsonTmp = new JSONObject();
			jsonTmp.put("id", jc_pubfacilities_hj.getId());
			jsonTmp.put("dateid",jc_pubfacilities_hj.getdateid());
			jsonTmp.put("type",jc_pubfacilities_hj.gettype());
			jsonTmp.put("objid",jc_pubfacilities_hj.getobjid());
			jsonTmp.put("objname",jc_pubfacilities_hj.getobjname());
			jsonTmp.put("locatedsc",jc_pubfacilities_hj.getlocatedsc());
			jsonTmp.put("deptname1",jc_pubfacilities_hj.getdeptname1());
			jsonTmp.put("deptname2",jc_pubfacilities_hj.getdeptname2());
			jsonTmp.put("deptname3",jc_pubfacilities_hj.getdeptname3());
			jsonTmp.put("isincommunity",jc_pubfacilities_hj.getisincommunity());
			jsonTmp.put("material",jc_pubfacilities_hj.getmaterial());
			jsonTmp.put("form",jc_pubfacilities_hj.getform());
			jsonTmp.put("objState",jc_pubfacilities_hj.getobjState());
			jsonTmp.put("pictures",jc_pubfacilities_hj.getpictures());
			jsonTmp.put("note",jc_pubfacilities_hj.getnote());

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
		Jc_pubfacilities_hj jc_pubfacilities_hj = jc_pubfacilities_hjService.getById(id);
		if(jc_pubfacilities_hj != null)
		{
			jsonObj.put("dateid",jc_pubfacilities_hj.getdateid());
			jsonObj.put("type",jc_pubfacilities_hj.gettype());
			jsonObj.put("objid",jc_pubfacilities_hj.getobjid());
			jsonObj.put("objname",jc_pubfacilities_hj.getobjname());
			jsonObj.put("locatedsc",jc_pubfacilities_hj.getlocatedsc());
			jsonObj.put("deptname1",jc_pubfacilities_hj.getdeptname1());
			jsonObj.put("deptname2",jc_pubfacilities_hj.getdeptname2());
			jsonObj.put("deptname3",jc_pubfacilities_hj.getdeptname3());
			jsonObj.put("isincommunity",jc_pubfacilities_hj.getisincommunity());
			jsonObj.put("material",jc_pubfacilities_hj.getmaterial());
			jsonObj.put("form",jc_pubfacilities_hj.getform());
			jsonObj.put("objState",jc_pubfacilities_hj.getobjState());
			jsonObj.put("pictures",jc_pubfacilities_hj.getpictures());
			jsonObj.put("note",jc_pubfacilities_hj.getnote());

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
