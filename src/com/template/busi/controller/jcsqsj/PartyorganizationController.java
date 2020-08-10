package com.template.busi.controller.jcsqsj;
import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import com.template.model.jcsqsj.Partyorganization;
import com.template.service.jcsqsj.PartyorganizationService;
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
@RequestMapping("partyorganizationController")
public class PartyorganizationController {
	private static Logger logger = Logger.getLogger(PartyorganizationController.class);
	@Autowired
	private  HttpServletRequest request;
	@Autowired
	private PartyorganizationService partyorganizationService;
@RequestMapping(value="addOrUpdate",method = RequestMethod.POST,produces="text/html;charset=UTF-8")
@ResponseBody
public String addOrUpdate(String id,String dateid,String name,String tpye,String secretary,String secretarytel,String contact,String contacttel,String company,String pictures,String note)
{
	JSONObject jsonObj = new JSONObject();
	try
	{
		Partyorganization partyorganization;
		if(id==null || id.equalsIgnoreCase(""))
		{
			partyorganization = new Partyorganization();
			partyorganization.setId(Utility.getUniStr());
		}
		else
		{
			partyorganization = partyorganizationService.getById(id);
		}
		partyorganization.setdateid(dateid);
		partyorganization.setname(name);
		partyorganization.settpye(tpye);
		partyorganization.setsecretary(secretary);
		partyorganization.setsecretarytel(secretarytel);
		partyorganization.setcontact(contact);
		partyorganization.setcontacttel(contacttel);
		partyorganization.setcompany(company);
		partyorganization.setpictures(pictures);
		partyorganization.setnote(note);

        partyorganizationService.save(partyorganization);
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
		Partyorganization partyorganization = partyorganizationService.getById(id);
		partyorganizationService.delete(partyorganization);
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
public String load(String name,String tpye,String secretary)
{
	JSONObject jsonObj = new JSONObject();
	try
	{
		HqlFilter hqlFilter = new HqlFilter();
if(name != null && name.equalsIgnoreCase("") == false && name.equalsIgnoreCase("null") == false)
{
	hqlFilter.addQryCond("name", HqlFilter.Operator.LIKE, "%"+name+"%");
}
if(tpye != null && tpye.equalsIgnoreCase("") == false && tpye.equalsIgnoreCase("null") == false)
{
	hqlFilter.addQryCond("tpye", HqlFilter.Operator.EQ, tpye);
}
if(secretary != null && secretary.equalsIgnoreCase("") == false && secretary.equalsIgnoreCase("null") == false)
{
	hqlFilter.addQryCond("secretary", HqlFilter.Operator.LIKE, "%"+secretary+"%");
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

        List<Partyorganization> listObj = partyorganizationService.findByFilter(hqlFilter);
        JSONArray jsonArr = new JSONArray();
        int iTotalCnt = 0;
		for(int i=0;i<listObj.size();i++)
		{
			Partyorganization partyorganization = listObj.get(i);
			JSONObject jsonTmp = new JSONObject();
			jsonTmp.put("id", partyorganization.getId());
			jsonTmp.put("dateid",partyorganization.getdateid());
			jsonTmp.put("name",partyorganization.getname());
			jsonTmp.put("tpye",partyorganization.gettpye());
			jsonTmp.put("secretary",partyorganization.getsecretary());
			jsonTmp.put("secretarytel",partyorganization.getsecretarytel());
			jsonTmp.put("contact",partyorganization.getcontact());
			jsonTmp.put("contacttel",partyorganization.getcontacttel());
			jsonTmp.put("company",partyorganization.getcompany());
			jsonTmp.put("pictures",partyorganization.getpictures());
			jsonTmp.put("note",partyorganization.getnote());

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
		Partyorganization partyorganization = partyorganizationService.getById(id);
		if(partyorganization != null)
		{
			jsonObj.put("dateid",partyorganization.getdateid());
			jsonObj.put("name",partyorganization.getname());
			jsonObj.put("tpye",partyorganization.gettpye());
			jsonObj.put("secretary",partyorganization.getsecretary());
			jsonObj.put("secretarytel",partyorganization.getsecretarytel());
			jsonObj.put("contact",partyorganization.getcontact());
			jsonObj.put("contacttel",partyorganization.getcontacttel());
			jsonObj.put("company",partyorganization.getcompany());
			jsonObj.put("pictures",partyorganization.getpictures());
			jsonObj.put("note",partyorganization.getnote());

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
