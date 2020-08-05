package com.template.busi.controller.jcsqsj;
import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import com.template.model.jcsqsj.Jc_organization;
import com.template.service.jcsqsj.Jc_organizationService;
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
@RequestMapping("jc_organizationController")
public class Jc_organizationController {
	private static Logger logger = Logger.getLogger(Jc_organizationController.class);
	@Autowired
	private  HttpServletRequest request;
	@Autowired
	private Jc_organizationService jc_organizationService;
@RequestMapping(value="addOrUpdate",method = RequestMethod.POST,produces="text/html;charset=UTF-8")
@ResponseBody
public String addOrUpdate(String id,String dataid,String name,String haslicence,String socialcode,String socialcodedate,String orgtype,String economictype,String industry,String subordination,String establishdate,String capitaltype,String capital,String businessscope,String scale,String regaddress,String officeaddress,String ofbizbuilding,String legalname,String contactname,String contacttel,String moveindate,String responsibilityplateno,String hasfirefacilities,String wastedisposal,String status,String pictures,String note)
{
	JSONObject jsonObj = new JSONObject();
	try
	{
		Jc_organization jc_organization;
		if(id==null || id.equalsIgnoreCase(""))
		{
			jc_organization = new Jc_organization();
			jc_organization.setId(Utility.getUniStr());
		}
		else
		{
			jc_organization = jc_organizationService.getById(id);
		}
		jc_organization.setdataid(dataid);
		jc_organization.setname(name);
		jc_organization.sethaslicence(haslicence);
		jc_organization.setsocialcode(socialcode);
		jc_organization.setsocialcodedate(TimeUtil.parseDate(socialcodedate, "yyyy-MM-dd"));
		jc_organization.setorgtype(orgtype);
		jc_organization.seteconomictype(economictype);
		jc_organization.setindustry(industry);
		jc_organization.setsubordination(subordination);
		jc_organization.setestablishdate(TimeUtil.parseDate(establishdate, "yyyy-MM-dd"));
		jc_organization.setcapitaltype(capitaltype);
		jc_organization.setcapital(capital);
		jc_organization.setbusinessscope(businessscope);
		jc_organization.setscale(scale);
		jc_organization.setregaddress(regaddress);
		jc_organization.setofficeaddress(officeaddress);
		jc_organization.setofbizbuilding(ofbizbuilding);
		jc_organization.setlegalname(legalname);
		jc_organization.setcontactname(contactname);
		jc_organization.setcontacttel(contacttel);
		jc_organization.setmoveindate(TimeUtil.parseDate(moveindate, "yyyy-MM-dd"));
		jc_organization.setresponsibilityplateno(responsibilityplateno);
		jc_organization.sethasfirefacilities(hasfirefacilities);
		jc_organization.setwastedisposal(wastedisposal);
		jc_organization.setstatus(status);
		jc_organization.setpictures(pictures);
		jc_organization.setnote(note);

        jc_organizationService.save(jc_organization);
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
		Jc_organization jc_organization = jc_organizationService.getById(id);
		jc_organizationService.delete(jc_organization);
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
public String load(String name,String haslicence,String socialcode,String orgtype,String economictype,String industry,String subordination,String scale,String officeaddress,String ofbizbuilding,String contacttel,String hasfirefacilities,String status)
{
	JSONObject jsonObj = new JSONObject();
	try
	{
		HqlFilter hqlFilter = new HqlFilter();
if(name != null && name.equalsIgnoreCase("") == false && name.equalsIgnoreCase("null") == false)
{
	hqlFilter.addQryCond("name", HqlFilter.Operator.LIKE, "%"+name+"%");
}
if(haslicence != null && haslicence.equalsIgnoreCase("") == false && haslicence.equalsIgnoreCase("null") == false)
{
	hqlFilter.addQryCond("haslicence", HqlFilter.Operator.LIKE, "%"+haslicence+"%");
}
if(socialcode != null && socialcode.equalsIgnoreCase("") == false && socialcode.equalsIgnoreCase("null") == false)
{
	hqlFilter.addQryCond("socialcode", HqlFilter.Operator.LIKE, "%"+socialcode+"%");
}
if(orgtype != null && orgtype.equalsIgnoreCase("") == false && orgtype.equalsIgnoreCase("null") == false)
{
	hqlFilter.addQryCond("orgtype", HqlFilter.Operator.EQ, orgtype);
}
if(economictype != null && economictype.equalsIgnoreCase("") == false && economictype.equalsIgnoreCase("null") == false)
{
	hqlFilter.addQryCond("economictype", HqlFilter.Operator.EQ, economictype);
}
if(industry != null && industry.equalsIgnoreCase("") == false && industry.equalsIgnoreCase("null") == false)
{
	hqlFilter.addQryCond("industry", HqlFilter.Operator.EQ, industry);
}
if(subordination != null && subordination.equalsIgnoreCase("") == false && subordination.equalsIgnoreCase("null") == false)
{
	hqlFilter.addQryCond("subordination", HqlFilter.Operator.EQ, subordination);
}
if(scale != null && scale.equalsIgnoreCase("") == false && scale.equalsIgnoreCase("null") == false)
{
	hqlFilter.addQryCond("scale", HqlFilter.Operator.EQ, scale);
}
if(officeaddress != null && officeaddress.equalsIgnoreCase("") == false && officeaddress.equalsIgnoreCase("null") == false)
{
	hqlFilter.addQryCond("officeaddress", HqlFilter.Operator.LIKE, "%"+officeaddress+"%");
}
if(ofbizbuilding != null && ofbizbuilding.equalsIgnoreCase("") == false && ofbizbuilding.equalsIgnoreCase("null") == false)
{
	hqlFilter.addQryCond("ofbizbuilding", HqlFilter.Operator.LIKE, "%"+ofbizbuilding+"%");
}
if(contacttel != null && contacttel.equalsIgnoreCase("") == false && contacttel.equalsIgnoreCase("null") == false)
{
	hqlFilter.addQryCond("contacttel", HqlFilter.Operator.LIKE, "%"+contacttel+"%");
}
if(hasfirefacilities != null && hasfirefacilities.equalsIgnoreCase("") == false && hasfirefacilities.equalsIgnoreCase("null") == false)
{
	hqlFilter.addQryCond("hasfirefacilities", HqlFilter.Operator.LIKE, "%"+hasfirefacilities+"%");
}
if(status != null && status.equalsIgnoreCase("") == false && status.equalsIgnoreCase("null") == false)
{
	hqlFilter.addQryCond("status", HqlFilter.Operator.LIKE, "%"+status+"%");
}

        List<Jc_organization> listObj = jc_organizationService.findByFilter(hqlFilter);
        JSONArray jsonArr = new JSONArray();
        int iTotalCnt = 0;
		for(int i=0;i<listObj.size();i++)
		{
			Jc_organization jc_organization = listObj.get(i);
			JSONObject jsonTmp = new JSONObject();
			jsonTmp.put("id", jc_organization.getId());
			jsonTmp.put("dataid",jc_organization.getdataid());
			jsonTmp.put("name",jc_organization.getname());
			jsonTmp.put("haslicence",jc_organization.gethaslicence());
			jsonTmp.put("socialcode",jc_organization.getsocialcode());
			jsonTmp.put("socialcodedate",TimeUtil.formatDate(jc_organization.getsocialcodedate(),"yyyy-MM-dd"));
			jsonTmp.put("orgtype",jc_organization.getorgtype());
			jsonTmp.put("economictype",jc_organization.geteconomictype());
			jsonTmp.put("industry",jc_organization.getindustry());
			jsonTmp.put("subordination",jc_organization.getsubordination());
			jsonTmp.put("establishdate",TimeUtil.formatDate(jc_organization.getestablishdate(),"yyyy-MM-dd"));
			jsonTmp.put("capitaltype",jc_organization.getcapitaltype());
			jsonTmp.put("capital",jc_organization.getcapital());
			jsonTmp.put("businessscope",jc_organization.getbusinessscope());
			jsonTmp.put("scale",jc_organization.getscale());
			jsonTmp.put("regaddress",jc_organization.getregaddress());
			jsonTmp.put("officeaddress",jc_organization.getofficeaddress());
			jsonTmp.put("ofbizbuilding",jc_organization.getofbizbuilding());
			jsonTmp.put("legalname",jc_organization.getlegalname());
			jsonTmp.put("contactname",jc_organization.getcontactname());
			jsonTmp.put("contacttel",jc_organization.getcontacttel());
			jsonTmp.put("moveindate",TimeUtil.formatDate(jc_organization.getmoveindate(),"yyyy-MM-dd"));
			jsonTmp.put("responsibilityplateno",jc_organization.getresponsibilityplateno());
			jsonTmp.put("hasfirefacilities",jc_organization.gethasfirefacilities());
			jsonTmp.put("wastedisposal",jc_organization.getwastedisposal());
			jsonTmp.put("status",jc_organization.getstatus());
			jsonTmp.put("pictures",jc_organization.getpictures());
			jsonTmp.put("note",jc_organization.getnote());

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
		Jc_organization jc_organization = jc_organizationService.getById(id);
		if(jc_organization != null)
		{
			jsonObj.put("dataid",jc_organization.getdataid());
			jsonObj.put("name",jc_organization.getname());
			jsonObj.put("haslicence",jc_organization.gethaslicence());
			jsonObj.put("socialcode",jc_organization.getsocialcode());
			jsonObj.put("socialcodedate",TimeUtil.formatDate(jc_organization.getsocialcodedate(),"yyyy-MM-dd"));
			jsonObj.put("orgtype",jc_organization.getorgtype());
			jsonObj.put("economictype",jc_organization.geteconomictype());
			jsonObj.put("industry",jc_organization.getindustry());
			jsonObj.put("subordination",jc_organization.getsubordination());
			jsonObj.put("establishdate",TimeUtil.formatDate(jc_organization.getestablishdate(),"yyyy-MM-dd"));
			jsonObj.put("capitaltype",jc_organization.getcapitaltype());
			jsonObj.put("capital",jc_organization.getcapital());
			jsonObj.put("businessscope",jc_organization.getbusinessscope());
			jsonObj.put("scale",jc_organization.getscale());
			jsonObj.put("regaddress",jc_organization.getregaddress());
			jsonObj.put("officeaddress",jc_organization.getofficeaddress());
			jsonObj.put("ofbizbuilding",jc_organization.getofbizbuilding());
			jsonObj.put("legalname",jc_organization.getlegalname());
			jsonObj.put("contactname",jc_organization.getcontactname());
			jsonObj.put("contacttel",jc_organization.getcontacttel());
			jsonObj.put("moveindate",TimeUtil.formatDate(jc_organization.getmoveindate(),"yyyy-MM-dd"));
			jsonObj.put("responsibilityplateno",jc_organization.getresponsibilityplateno());
			jsonObj.put("hasfirefacilities",jc_organization.gethasfirefacilities());
			jsonObj.put("wastedisposal",jc_organization.getwastedisposal());
			jsonObj.put("status",jc_organization.getstatus());
			jsonObj.put("pictures",jc_organization.getpictures());
			jsonObj.put("note",jc_organization.getnote());

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
