package com.template.busi.controller.jcsqsj;
import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import com.template.model.jcsqsj.Jc_sqorganization;
import com.template.service.jcsqsj.Jc_sqorganizationService;
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
@RequestMapping("jc_sqorganizationController")
public class Jc_sqorganizationController {
	private static Logger logger = Logger.getLogger(Jc_sqorganizationController.class);
	@Autowired
	private  HttpServletRequest request;
	@Autowired
	private Jc_sqorganizationService jc_sqorganizationService;
@RequestMapping(value="addOrUpdate",method = RequestMethod.POST,produces="text/html;charset=UTF-8")
@ResponseBody
public String addOrUpdate(String id,String dateid,String name,String regstatus,String regunit,String competentunit,String address,String establishdate,Integer persionsize,String introduction,String leadername,String leaderid,String leadermobile,String contact,String contactmobile,String pictures,String note)
{
	JSONObject jsonObj = new JSONObject();
	try
	{
		Jc_sqorganization jc_sqorganization;
		if(id==null || id.equalsIgnoreCase(""))
		{
			jc_sqorganization = new Jc_sqorganization();
			jc_sqorganization.setId(Utility.getUniStr());
		}
		else
		{
			jc_sqorganization = jc_sqorganizationService.getById(id);
		}
		jc_sqorganization.setdateid(dateid);
		jc_sqorganization.setname(name);
		jc_sqorganization.setregstatus(regstatus);
		jc_sqorganization.setregunit(regunit);
		jc_sqorganization.setcompetentunit(competentunit);
		jc_sqorganization.setaddress(address);
		jc_sqorganization.setestablishdate(TimeUtil.parseDate(establishdate, "yyyy-MM-dd"));
		jc_sqorganization.setpersionsize(persionsize);
		jc_sqorganization.setintroduction(introduction);
		jc_sqorganization.setleadername(leadername);
		jc_sqorganization.setleaderid(leaderid);
		jc_sqorganization.setleadermobile(leadermobile);
		jc_sqorganization.setcontact(contact);
		jc_sqorganization.setcontactmobile(contactmobile);
		jc_sqorganization.setpictures(pictures);
		jc_sqorganization.setnote(note);

        jc_sqorganizationService.save(jc_sqorganization);
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
		Jc_sqorganization jc_sqorganization = jc_sqorganizationService.getById(id);
		jc_sqorganizationService.delete(jc_sqorganization);
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
public String load(String name,String regstatus,String address,String persionsize,String leadername,String leadermobile)
{
	JSONObject jsonObj = new JSONObject();
	try
	{
		HqlFilter hqlFilter = new HqlFilter();
if(name != null && name.equalsIgnoreCase("") == false && name.equalsIgnoreCase("null") == false)
{
	hqlFilter.addQryCond("name", HqlFilter.Operator.LIKE, "%"+name+"%");
}
if(regstatus != null && regstatus.equalsIgnoreCase("") == false && regstatus.equalsIgnoreCase("null") == false)
{
	hqlFilter.addQryCond("regstatus", HqlFilter.Operator.EQ, regstatus);
}
if(address != null && address.equalsIgnoreCase("") == false && address.equalsIgnoreCase("null") == false)
{
	hqlFilter.addQryCond("address", HqlFilter.Operator.LIKE, "%"+address+"%");
}
if(persionsize != null && persionsize.equalsIgnoreCase("") == false && persionsize.equalsIgnoreCase("null") == false)
{
	hqlFilter.addQryCond("persionsize", HqlFilter.Operator.LIKE, "%"+persionsize+"%");
}
if(leadername != null && leadername.equalsIgnoreCase("") == false && leadername.equalsIgnoreCase("null") == false)
{
	hqlFilter.addQryCond("leadername", HqlFilter.Operator.LIKE, "%"+leadername+"%");
}
if(leadermobile != null && leadermobile.equalsIgnoreCase("") == false && leadermobile.equalsIgnoreCase("null") == false)
{
	hqlFilter.addQryCond("leadermobile", HqlFilter.Operator.LIKE, "%"+leadermobile+"%");
}

        List<Jc_sqorganization> listObj = jc_sqorganizationService.findByFilter(hqlFilter);
        JSONArray jsonArr = new JSONArray();
        int iTotalCnt = 0;
		for(int i=0;i<listObj.size();i++)
		{
			Jc_sqorganization jc_sqorganization = listObj.get(i);
			JSONObject jsonTmp = new JSONObject();
			jsonTmp.put("id", jc_sqorganization.getId());
			jsonTmp.put("dateid",jc_sqorganization.getdateid());
			jsonTmp.put("name",jc_sqorganization.getname());
			jsonTmp.put("regstatus",jc_sqorganization.getregstatus());
			jsonTmp.put("regunit",jc_sqorganization.getregunit());
			jsonTmp.put("competentunit",jc_sqorganization.getcompetentunit());
			jsonTmp.put("address",jc_sqorganization.getaddress());
			jsonTmp.put("establishdate",TimeUtil.formatDate(jc_sqorganization.getestablishdate(),"yyyy-MM-dd"));
			jsonTmp.put("persionsize",jc_sqorganization.getpersionsize());
			jsonTmp.put("introduction",jc_sqorganization.getintroduction());
			jsonTmp.put("leadername",jc_sqorganization.getleadername());
			jsonTmp.put("leaderid",jc_sqorganization.getleaderid());
			jsonTmp.put("leadermobile",jc_sqorganization.getleadermobile());
			jsonTmp.put("contact",jc_sqorganization.getcontact());
			jsonTmp.put("contactmobile",jc_sqorganization.getcontactmobile());
			jsonTmp.put("pictures",jc_sqorganization.getpictures());
			jsonTmp.put("note",jc_sqorganization.getnote());

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
		Jc_sqorganization jc_sqorganization = jc_sqorganizationService.getById(id);
		if(jc_sqorganization != null)
		{
			jsonObj.put("dateid",jc_sqorganization.getdateid());
			jsonObj.put("name",jc_sqorganization.getname());
			jsonObj.put("regstatus",jc_sqorganization.getregstatus());
			jsonObj.put("regunit",jc_sqorganization.getregunit());
			jsonObj.put("competentunit",jc_sqorganization.getcompetentunit());
			jsonObj.put("address",jc_sqorganization.getaddress());
			jsonObj.put("establishdate",TimeUtil.formatDate(jc_sqorganization.getestablishdate(),"yyyy-MM-dd"));
			jsonObj.put("persionsize",jc_sqorganization.getpersionsize());
			jsonObj.put("introduction",jc_sqorganization.getintroduction());
			jsonObj.put("leadername",jc_sqorganization.getleadername());
			jsonObj.put("leaderid",jc_sqorganization.getleaderid());
			jsonObj.put("leadermobile",jc_sqorganization.getleadermobile());
			jsonObj.put("contact",jc_sqorganization.getcontact());
			jsonObj.put("contactmobile",jc_sqorganization.getcontactmobile());
			jsonObj.put("pictures",jc_sqorganization.getpictures());
			jsonObj.put("note",jc_sqorganization.getnote());

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
