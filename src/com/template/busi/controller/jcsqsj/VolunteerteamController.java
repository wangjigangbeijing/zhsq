package com.template.busi.controller.jcsqsj;
import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import com.template.model.jcsqsj.Volunteerteam;
import com.template.service.jcsqsj.VolunteerteamService;
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
@RequestMapping("volunteerteamController")
public class VolunteerteamController {
	private static Logger logger = Logger.getLogger(VolunteerteamController.class);
	@Autowired
	private  HttpServletRequest request;
	@Autowired
	private VolunteerteamService volunteerteamService;
@RequestMapping(value="addOrUpdate",method = RequestMethod.POST,produces="text/html;charset=UTF-8")
@ResponseBody
public String addOrUpdate(String id,String dateid,String name,String contactorg,String regstatus,String regunit,String competentunit,String address,String establishdate,Integer persionsize,String introduction,String leadername,String leaderid,String leadermobile,String contact,String contactmobile,String pictures,String note)
{
	JSONObject jsonObj = new JSONObject();
	try
	{
		Volunteerteam volunteerteam;
		if(id==null || id.equalsIgnoreCase(""))
		{
			volunteerteam = new Volunteerteam();
			volunteerteam.setId(Utility.getUniStr());
		}
		else
		{
			volunteerteam = volunteerteamService.getById(id);
		}
		volunteerteam.setdateid(dateid);
		volunteerteam.setname(name);
		volunteerteam.setcontactorg(contactorg);
		volunteerteam.setregstatus(regstatus);
		volunteerteam.setregunit(regunit);
		volunteerteam.setcompetentunit(competentunit);
		volunteerteam.setaddress(address);
		volunteerteam.setestablishdate(TimeUtil.parseDate(establishdate, "yyyy-MM-dd"));
		volunteerteam.setpersionsize(persionsize);
		volunteerteam.setintroduction(introduction);
		volunteerteam.setleadername(leadername);
		volunteerteam.setleaderid(leaderid);
		volunteerteam.setleadermobile(leadermobile);
		volunteerteam.setcontact(contact);
		volunteerteam.setcontactmobile(contactmobile);
		volunteerteam.setpictures(pictures);
		volunteerteam.setnote(note);

        volunteerteamService.save(volunteerteam);
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
		Volunteerteam volunteerteam = volunteerteamService.getById(id);
		volunteerteamService.delete(volunteerteam);
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

        List<Volunteerteam> listObj = volunteerteamService.findByFilter(hqlFilter);
        JSONArray jsonArr = new JSONArray();
        int iTotalCnt = 0;
		for(int i=0;i<listObj.size();i++)
		{
			Volunteerteam volunteerteam = listObj.get(i);
			JSONObject jsonTmp = new JSONObject();
			jsonTmp.put("id", volunteerteam.getId());
			jsonTmp.put("dateid",volunteerteam.getdateid());
			jsonTmp.put("name",volunteerteam.getname());
			jsonTmp.put("contactorg",volunteerteam.getcontactorg());
			jsonTmp.put("regstatus",volunteerteam.getregstatus());
			jsonTmp.put("regunit",volunteerteam.getregunit());
			jsonTmp.put("competentunit",volunteerteam.getcompetentunit());
			jsonTmp.put("address",volunteerteam.getaddress());
			
			if(volunteerteam.getestablishdate() != null)
				jsonTmp.put("establishdate",TimeUtil.formatDate(volunteerteam.getestablishdate(),"yyyy-MM-dd"));
			else
				jsonTmp.put("establishdate","-");
			
			jsonTmp.put("persionsize",volunteerteam.getpersionsize());
			jsonTmp.put("introduction",volunteerteam.getintroduction());
			jsonTmp.put("leadername",volunteerteam.getleadername());
			jsonTmp.put("leaderid",volunteerteam.getleaderid());
			jsonTmp.put("leadermobile",volunteerteam.getleadermobile());
			jsonTmp.put("contact",volunteerteam.getcontact());
			jsonTmp.put("contactmobile",volunteerteam.getcontactmobile());
			jsonTmp.put("pictures",volunteerteam.getpictures());
			jsonTmp.put("note",volunteerteam.getnote());

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
		Volunteerteam volunteerteam = volunteerteamService.getById(id);
		if(volunteerteam != null)
		{
			jsonObj.put("dateid",volunteerteam.getdateid());
			jsonObj.put("name",volunteerteam.getname());
			jsonObj.put("contactorg",volunteerteam.getcontactorg());
			jsonObj.put("regstatus",volunteerteam.getregstatus());
			jsonObj.put("regunit",volunteerteam.getregunit());
			jsonObj.put("competentunit",volunteerteam.getcompetentunit());
			jsonObj.put("address",volunteerteam.getaddress());
			jsonObj.put("establishdate",TimeUtil.formatDate(volunteerteam.getestablishdate(),"yyyy-MM-dd"));
			jsonObj.put("persionsize",volunteerteam.getpersionsize());
			jsonObj.put("introduction",volunteerteam.getintroduction());
			jsonObj.put("leadername",volunteerteam.getleadername());
			jsonObj.put("leaderid",volunteerteam.getleaderid());
			jsonObj.put("leadermobile",volunteerteam.getleadermobile());
			jsonObj.put("contact",volunteerteam.getcontact());
			jsonObj.put("contactmobile",volunteerteam.getcontactmobile());
			jsonObj.put("pictures",volunteerteam.getpictures());
			jsonObj.put("note",volunteerteam.getnote());

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
