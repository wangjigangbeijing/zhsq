package com.template.busi.controller.jcsqsj;
import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import com.template.model.jcsqsj.Populationgroup;
import com.template.service.jcsqsj.PopulationgroupService;
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
@RequestMapping("populationgroupController")
public class PopulationgroupController {
	private static Logger logger = Logger.getLogger(PopulationgroupController.class);
	@Autowired
	private  HttpServletRequest request;
	@Autowired
	private PopulationgroupService populationgroupService;
@RequestMapping(value="addOrUpdate",method = RequestMethod.POST,produces="text/html;charset=UTF-8")
@ResponseBody
public String addOrUpdate(String id,String dateid,String name,String contactorg,String regstatus,String regunit,String competentunit,String address,String establishdate,Integer persionsize,String introduction,String leadername,String leaderid,String leadermobile,String contact,String contactmobile,String pictures,String note)
{
	JSONObject jsonObj = new JSONObject();
	try
	{
		Populationgroup populationgroup;
		if(id==null || id.equalsIgnoreCase(""))
		{
			populationgroup = new Populationgroup();
			populationgroup.setId(Utility.getUniStr());
		}
		else
		{
			populationgroup = populationgroupService.getById(id);
		}
		populationgroup.setdateid(dateid);
		populationgroup.setname(name);
		populationgroup.setcontactorg(contactorg);
		populationgroup.setregstatus(regstatus);
		populationgroup.setregunit(regunit);
		populationgroup.setcompetentunit(competentunit);
		populationgroup.setaddress(address);
		populationgroup.setestablishdate(TimeUtil.parseDate(establishdate, "yyyy-MM-dd"));
		populationgroup.setpersionsize(persionsize);
		populationgroup.setintroduction(introduction);
		populationgroup.setleadername(leadername);
		populationgroup.setleaderid(leaderid);
		populationgroup.setleadermobile(leadermobile);
		populationgroup.setcontact(contact);
		populationgroup.setcontactmobile(contactmobile);
		populationgroup.setpictures(pictures);
		populationgroup.setnote(note);

		String userId = (String)request.getSession().getAttribute(ConstValue.SESSION_USER_ID);
		
		String organization = "";
		if(ConstValue.userToOrgMap.containsKey(userId))
			organization = ConstValue.userToOrgMap.get(userId);
		populationgroup.setowner(organization);
		
        populationgroupService.save(populationgroup);
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
		Populationgroup populationgroup = populationgroupService.getById(id);
		populationgroupService.delete(populationgroup);
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
public String load(String name,String regstatus,String address,String persionsize,String leadername)
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

        List<Populationgroup> listObj = populationgroupService.findByFilter(hqlFilter);
        JSONArray jsonArr = new JSONArray();
        int iTotalCnt = 0;
		for(int i=0;i<listObj.size();i++)
		{
			Populationgroup populationgroup = listObj.get(i);
			JSONObject jsonTmp = new JSONObject();
			jsonTmp.put("id", populationgroup.getId());
			jsonTmp.put("dateid",populationgroup.getdateid());
			jsonTmp.put("name",populationgroup.getname());
			jsonTmp.put("contactorg",populationgroup.getcontactorg());
			jsonTmp.put("regstatus",populationgroup.getregstatus());
			jsonTmp.put("regunit",populationgroup.getregunit());
			jsonTmp.put("competentunit",populationgroup.getcompetentunit());
			jsonTmp.put("address",populationgroup.getaddress());
			jsonTmp.put("establishdate",TimeUtil.formatDate(populationgroup.getestablishdate(),"yyyy-MM-dd"));
			jsonTmp.put("persionsize",populationgroup.getpersionsize());
			jsonTmp.put("introduction",populationgroup.getintroduction());
			jsonTmp.put("leadername",populationgroup.getleadername());
			jsonTmp.put("leaderid",populationgroup.getleaderid());
			jsonTmp.put("leadermobile",populationgroup.getleadermobile());
			jsonTmp.put("contact",populationgroup.getcontact());
			jsonTmp.put("contactmobile",populationgroup.getcontactmobile());
			jsonTmp.put("pictures",populationgroup.getpictures());
			jsonTmp.put("note",populationgroup.getnote());

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
		Populationgroup populationgroup = populationgroupService.getById(id);
		if(populationgroup != null)
		{
			jsonObj.put("dateid",populationgroup.getdateid());
			jsonObj.put("name",populationgroup.getname());
			jsonObj.put("contactorg",populationgroup.getcontactorg());
			jsonObj.put("regstatus",populationgroup.getregstatus());
			jsonObj.put("regunit",populationgroup.getregunit());
			jsonObj.put("competentunit",populationgroup.getcompetentunit());
			jsonObj.put("address",populationgroup.getaddress());
			jsonObj.put("establishdate",TimeUtil.formatDate(populationgroup.getestablishdate(),"yyyy-MM-dd"));
			jsonObj.put("persionsize",populationgroup.getpersionsize());
			jsonObj.put("introduction",populationgroup.getintroduction());
			jsonObj.put("leadername",populationgroup.getleadername());
			jsonObj.put("leaderid",populationgroup.getleaderid());
			jsonObj.put("leadermobile",populationgroup.getleadermobile());
			jsonObj.put("contact",populationgroup.getcontact());
			jsonObj.put("contactmobile",populationgroup.getcontactmobile());
			jsonObj.put("pictures",populationgroup.getpictures());
			jsonObj.put("note",populationgroup.getnote());

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
