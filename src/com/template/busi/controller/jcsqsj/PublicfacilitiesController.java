package com.template.busi.controller.jcsqsj;
import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import com.template.model.jcsqsj.Publicfacilities;
import com.template.service.jcsqsj.PublicfacilitiesService;
import com.template.util.HqlFilter;
import com.template.util.ConstValue;
import com.template.util.Utility;
import com.template.util.TimeUtil;
import java.util.List;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
@Controller
@RequestMapping("publicfacilitiesController")
public class PublicfacilitiesController {
	private static Logger logger = Logger.getLogger(PublicfacilitiesController.class);
	@Autowired
	private  HttpServletRequest request;
	@Autowired
	private PublicfacilitiesService publicfacilitiesService;
@RequestMapping(value="addOrUpdate",method = RequestMethod.POST,produces="text/html;charset=UTF-8")
@ResponseBody
public String addOrUpdate(String id,String dateid,String type,String objid,String objname,String locatedsc,
		String deptname1,String deptname2,String deptname3,String isincommunity,String material,String form,String objState,String pictures,String note)//Integer longitude,Integer latitude,
{
	JSONObject jsonObj = new JSONObject();
	try
	{
		Publicfacilities publicfacilities;
		if(id==null || id.equalsIgnoreCase(""))
		{
			publicfacilities = new Publicfacilities();
			publicfacilities.setId(Utility.getUniStr());
		}
		else
		{
			publicfacilities = publicfacilitiesService.getById(id);
		}
		publicfacilities.setdateid(dateid);
		publicfacilities.settype(type);
		publicfacilities.setobjid(objid);
		publicfacilities.setobjname(objname);
		publicfacilities.setlocatedsc(locatedsc);
		publicfacilities.setdeptname1(deptname1);
		publicfacilities.setdeptname2(deptname2);
		publicfacilities.setdeptname3(deptname3);
		//publicfacilities.setlongitude(longitude);
		//publicfacilities.setlatitude(latitude);
		publicfacilities.setisincommunity(isincommunity);
		publicfacilities.setmaterial(material);
		publicfacilities.setform(form);
		publicfacilities.setobjState(objState);
		publicfacilities.setpictures(pictures);
		publicfacilities.setnote(note);

        publicfacilitiesService.save(publicfacilities);
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
		Publicfacilities publicfacilities = publicfacilitiesService.getById(id);
		publicfacilitiesService.delete(publicfacilities);
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
	hqlFilter.addQryCond("objstate", HqlFilter.Operator.EQ, objState);
}

        List<Publicfacilities> listObj = publicfacilitiesService.findByFilter(hqlFilter);
        JSONArray jsonArr = new JSONArray();
        int iTotalCnt = 0;
		for(int i=0;i<listObj.size();i++)
		{
			Publicfacilities publicfacilities = listObj.get(i);
			JSONObject jsonTmp = new JSONObject();
			jsonTmp.put("id", publicfacilities.getId());
			jsonTmp.put("dateid",publicfacilities.getdateid());
			jsonTmp.put("type",publicfacilities.gettype());
			jsonTmp.put("objid",publicfacilities.getobjid());
			jsonTmp.put("objname",publicfacilities.getobjname());
			jsonTmp.put("locatedsc",publicfacilities.getlocatedsc());
			jsonTmp.put("deptname1",publicfacilities.getdeptname1());
			jsonTmp.put("deptname2",publicfacilities.getdeptname2());
			jsonTmp.put("deptname3",publicfacilities.getdeptname3());
			//jsonTmp.put("longitude",publicfacilities.getlongitude());
			//jsonTmp.put("latitude",publicfacilities.getlatitude());
			jsonTmp.put("isincommunity",publicfacilities.getisincommunity());
			jsonTmp.put("material",publicfacilities.getmaterial());
			jsonTmp.put("form",publicfacilities.getform());
			jsonTmp.put("objState",publicfacilities.getobjState());
			jsonTmp.put("pictures",publicfacilities.getpictures());
			jsonTmp.put("note",publicfacilities.getnote());

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
		Publicfacilities publicfacilities = publicfacilitiesService.getById(id);
		if(publicfacilities != null)
		{
			jsonObj.put("dateid",publicfacilities.getdateid());
			jsonObj.put("type",publicfacilities.gettype());
			jsonObj.put("objid",publicfacilities.getobjid());
			jsonObj.put("objname",publicfacilities.getobjname());
			jsonObj.put("locatedsc",publicfacilities.getlocatedsc());
			jsonObj.put("deptname1",publicfacilities.getdeptname1());
			jsonObj.put("deptname2",publicfacilities.getdeptname2());
			jsonObj.put("deptname3",publicfacilities.getdeptname3());
			//jsonObj.put("longitude",publicfacilities.getlongitude());
			//jsonObj.put("latitude",publicfacilities.getlatitude());
			jsonObj.put("isincommunity",publicfacilities.getisincommunity());
			jsonObj.put("material",publicfacilities.getmaterial());
			jsonObj.put("form",publicfacilities.getform());
			jsonObj.put("objState",publicfacilities.getobjState());
			jsonObj.put("pictures",publicfacilities.getpictures());
			jsonObj.put("note",publicfacilities.getnote());

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
