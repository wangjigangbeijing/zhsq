package com.template.busi.controller.jcsqsj;
import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import com.template.model.jcsqsj.Organization;
import com.template.service.jcsqsj.OrganizationService;
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
@RequestMapping("organizationController")
public class OrganizationController {
	private static Logger logger = Logger.getLogger(OrganizationController.class);
	@Autowired
	private  HttpServletRequest request;
	@Autowired
	private OrganizationService organizationService;
@RequestMapping(value="addOrUpdate",method = RequestMethod.POST,produces="text/html;charset=UTF-8")
@ResponseBody
public String addOrUpdate(String id,String dataid,String name,String haslicence,String socialcode,
		String socialcodedate,String orgtype,String economictype,String industry,String subordination,
		String establishdate,String capitaltype,String capital,String businessscope,String scale,String regaddress,
		String officeaddress,String ofbizbuilding,Integer longitude,Integer latitude,String legalname,String contactname,
		String contacttel,String moveindate,String responsibilityplateno,String hasfirefacilities,String wastedisposal,String status,String pictures,String note)
{
	JSONObject jsonObj = new JSONObject();
	try
	{
		Organization organization;
		if(id==null || id.equalsIgnoreCase(""))
		{
			organization = new Organization();
			organization.setId(Utility.getUniStr());
		}
		else
		{
			organization = organizationService.getById(id);
		}
		organization.setdataid(dataid);
		organization.setname(name);
		organization.sethaslicence(haslicence);
		organization.setsocialcode(socialcode);
		organization.setsocialcodedate(TimeUtil.parseDate(socialcodedate, "yyyy-MM-dd"));
		organization.setorgtype(orgtype);
		organization.seteconomictype(economictype);
		organization.setindustry(industry);
		organization.setsubordination(subordination);
		organization.setestablishdate(TimeUtil.parseDate(establishdate, "yyyy-MM-dd"));
		organization.setcapitaltype(capitaltype);
		organization.setcapital(capital);
		organization.setbusinessscope(businessscope);
		organization.setscale(scale);
		organization.setregaddress(regaddress);
		organization.setofficeaddress(officeaddress);
		organization.setofbizbuilding(ofbizbuilding);
		//organization.setlongitude(longitude);
		//organization.setlatitude(latitude);
		organization.setlegalname(legalname);
		organization.setcontactname(contactname);
		organization.setcontacttel(contacttel);
		organization.setmoveindate(TimeUtil.parseDate(moveindate, "yyyy-MM-dd"));
		organization.setresponsibilityplateno(responsibilityplateno);
		organization.sethasfirefacilities(hasfirefacilities);
		organization.setwastedisposal(wastedisposal);
		//organization.setlongitude(longitude);
		//organization.setlatitude(latitude);
		organization.setstatus(status);
		organization.setpictures(pictures);
		organization.setnote(note);

        organizationService.save(organization);
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
		Organization organization = organizationService.getById(id);
		organizationService.delete(organization);
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
public String load(String name,String haslicence,String socialcode,String orgtype,String economictype,String industry,String subordination,String scale,String officeaddress,String ofbizbuilding,String hasfirefacilities,String status)
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
if(hasfirefacilities != null && hasfirefacilities.equalsIgnoreCase("") == false && hasfirefacilities.equalsIgnoreCase("null") == false)
{
	hqlFilter.addQryCond("hasfirefacilities", HqlFilter.Operator.LIKE, "%"+hasfirefacilities+"%");
}
if(status != null && status.equalsIgnoreCase("") == false && status.equalsIgnoreCase("null") == false)
{
	hqlFilter.addQryCond("status", HqlFilter.Operator.LIKE, "%"+status+"%");
}

        List<Organization> listObj = organizationService.findByFilter(hqlFilter);
        JSONArray jsonArr = new JSONArray();
        int iTotalCnt = 0;
		for(int i=0;i<listObj.size();i++)
		{
			Organization organization = listObj.get(i);
			JSONObject jsonTmp = new JSONObject();
			jsonTmp.put("id", organization.getId());
			jsonTmp.put("dataid",organization.getdataid());
			jsonTmp.put("name",organization.getname());
			jsonTmp.put("haslicence",organization.gethaslicence());
			jsonTmp.put("socialcode",organization.getsocialcode());
			jsonTmp.put("socialcodedate",TimeUtil.formatDate(organization.getsocialcodedate(),"yyyy-MM-dd"));
			jsonTmp.put("orgtype",organization.getorgtype());
			jsonTmp.put("economictype",organization.geteconomictype());
			jsonTmp.put("industry",organization.getindustry());
			jsonTmp.put("subordination",organization.getsubordination());
			jsonTmp.put("establishdate",TimeUtil.formatDate(organization.getestablishdate(),"yyyy-MM-dd"));
			jsonTmp.put("capitaltype",organization.getcapitaltype());
			jsonTmp.put("capital",organization.getcapital());
			jsonTmp.put("businessscope",organization.getbusinessscope());
			jsonTmp.put("scale",organization.getscale());
			jsonTmp.put("regaddress",organization.getregaddress());
			jsonTmp.put("officeaddress",organization.getofficeaddress());
			jsonTmp.put("ofbizbuilding",organization.getofbizbuilding());
			//jsonTmp.put("longitude",organization.getlongitude());
			//jsonTmp.put("latitude",organization.getlatitude());
			jsonTmp.put("legalname",organization.getlegalname());
			jsonTmp.put("contactname",organization.getcontactname());
			jsonTmp.put("contacttel",organization.getcontacttel());
			
			if(organization.getmoveindate() != null)
				jsonTmp.put("moveindate",TimeUtil.formatDate(organization.getmoveindate(),"yyyy-MM-dd"));
			else
				jsonTmp.put("moveindate","-");
			
			jsonTmp.put("responsibilityplateno",organization.getresponsibilityplateno());
			jsonTmp.put("hasfirefacilities",organization.gethasfirefacilities());
			jsonTmp.put("wastedisposal",organization.getwastedisposal());
			//jsonTmp.put("longitude",organization.getlongitude());
			//jsonTmp.put("latitude",organization.getlatitude());
			jsonTmp.put("status",organization.getstatus());
			jsonTmp.put("pictures",organization.getpictures());
			jsonTmp.put("note",organization.getnote());

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
		Organization organization = organizationService.getById(id);
		if(organization != null)
		{
			jsonObj.put("dataid",organization.getdataid());
			jsonObj.put("name",organization.getname());
			jsonObj.put("haslicence",organization.gethaslicence());
			jsonObj.put("socialcode",organization.getsocialcode());
			jsonObj.put("socialcodedate",TimeUtil.formatDate(organization.getsocialcodedate(),"yyyy-MM-dd"));
			jsonObj.put("orgtype",organization.getorgtype());
			jsonObj.put("economictype",organization.geteconomictype());
			jsonObj.put("industry",organization.getindustry());
			jsonObj.put("subordination",organization.getsubordination());
			jsonObj.put("establishdate",TimeUtil.formatDate(organization.getestablishdate(),"yyyy-MM-dd"));
			jsonObj.put("capitaltype",organization.getcapitaltype());
			jsonObj.put("capital",organization.getcapital());
			jsonObj.put("businessscope",organization.getbusinessscope());
			jsonObj.put("scale",organization.getscale());
			jsonObj.put("regaddress",organization.getregaddress());
			jsonObj.put("officeaddress",organization.getofficeaddress());
			jsonObj.put("ofbizbuilding",organization.getofbizbuilding());
			//jsonObj.put("longitude",organization.getlongitude());
			//jsonObj.put("latitude",organization.getlatitude());
			jsonObj.put("legalname",organization.getlegalname());
			jsonObj.put("contactname",organization.getcontactname());
			jsonObj.put("contacttel",organization.getcontacttel());
			jsonObj.put("moveindate",TimeUtil.formatDate(organization.getmoveindate(),"yyyy-MM-dd"));
			jsonObj.put("responsibilityplateno",organization.getresponsibilityplateno());
			jsonObj.put("hasfirefacilities",organization.gethasfirefacilities());
			jsonObj.put("wastedisposal",organization.getwastedisposal());
			//jsonObj.put("longitude",organization.getlongitude());
			//jsonObj.put("latitude",organization.getlatitude());
			jsonObj.put("status",organization.getstatus());
			jsonObj.put("pictures",organization.getpictures());
			jsonObj.put("note",organization.getnote());

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
