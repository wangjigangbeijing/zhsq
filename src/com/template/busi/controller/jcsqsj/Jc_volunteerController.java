package com.template.busi.controller.jcsqsj;
import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;

import com.template.busi.safe.SafeFieldChecker;
import com.template.model.jcsqsj.Jc_volunteer;
import com.template.service.jcsqsj.Jc_volunteerService;
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
@RequestMapping("jc_volunteerController")
public class Jc_volunteerController {
	private static Logger logger = Logger.getLogger(Jc_volunteerController.class);
	@Autowired
	private  HttpServletRequest request;
	@Autowired
	private Jc_volunteerService jc_volunteerService;
@RequestMapping(value="addOrUpdate",method = RequestMethod.POST,produces="text/html;charset=UTF-8")
@ResponseBody
public String addOrUpdate(String id,String name,String idnumber,String sex,String birthday,String age,String mobile,String education,String politicalstatus,String of_volunteerteam,String join_date,String certificate_id,String special_skill,String status,String pictures,String note)
{
	JSONObject jsonObj = new JSONObject();
	try
	{
		Jc_volunteer jc_volunteer;
		if(id==null || id.equalsIgnoreCase(""))
		{
			jc_volunteer = new Jc_volunteer();
			jc_volunteer.setId(Utility.getUniStr());
		}
		else
		{
			jc_volunteer = jc_volunteerService.getById(id);
		}
		jc_volunteer.setname(name);
		jc_volunteer.setidnumber(idnumber);
		jc_volunteer.setsex(sex);
		jc_volunteer.setbirthday(TimeUtil.parseDate(birthday, "yyyy-MM-dd"));
		jc_volunteer.setage(age);
		jc_volunteer.setmobile(mobile);
		jc_volunteer.seteducation(education);
		jc_volunteer.setpoliticalstatus(politicalstatus);
		jc_volunteer.setof_volunteerteam(of_volunteerteam);
		jc_volunteer.setjoin_date(TimeUtil.parseDate(join_date, "yyyy-MM-dd"));
		jc_volunteer.setcertificate_id(certificate_id);
		jc_volunteer.setspecial_skill(special_skill);
		jc_volunteer.setstatus(status);
		jc_volunteer.setpictures(pictures);
		jc_volunteer.setnote(note);

        jc_volunteerService.save(jc_volunteer);
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
		Jc_volunteer jc_volunteer = jc_volunteerService.getById(id);
		jc_volunteerService.delete(jc_volunteer);
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
@RequestMapping(value="load",method = {RequestMethod.GET,RequestMethod.POST},produces="text/html;charset=UTF-8")
@ResponseBody
public String load(String name,String idnumber,String sex,String birthday,String age,String mobile,String education,String politicalstatus,String of_volunteerteam,String join_date,String status)
{
	JSONObject jsonObj = new JSONObject();
	try
	{
		HqlFilter hqlFilter = new HqlFilter();
if(name != null && name.equalsIgnoreCase("") == false && name.equalsIgnoreCase("null") == false)
{
	//hqlFilter.addQryCond("name", HqlFilter.Operator.LIKE, "%"+name+"%");
	hqlFilter.addQryCond("name", HqlFilter.Operator.EQ, new SafeFieldChecker().checkField(jc_volunteerService, "jc_volunteer", "name", name));
}
if(idnumber != null && idnumber.equalsIgnoreCase("") == false && idnumber.equalsIgnoreCase("null") == false)
{
	hqlFilter.addQryCond("idnumber", HqlFilter.Operator.LIKE, "%"+idnumber+"%");
}
if(sex != null && sex.equalsIgnoreCase("") == false && sex.equalsIgnoreCase("null") == false)
{
	hqlFilter.addQryCond("sex", HqlFilter.Operator.LIKE, "%"+sex+"%");
}
if(birthday != null && birthday.equalsIgnoreCase("") == false && birthday.equalsIgnoreCase("null") == false)
{
	hqlFilter.addQryCond("birthday", HqlFilter.Operator.LIKE, "%"+birthday+"%");
}
if(age != null && age.equalsIgnoreCase("") == false && age.equalsIgnoreCase("null") == false)
{
	hqlFilter.addQryCond("age", HqlFilter.Operator.LIKE, "%"+age+"%");
}
if(mobile != null && mobile.equalsIgnoreCase("") == false && mobile.equalsIgnoreCase("null") == false)
{
	hqlFilter.addQryCond("mobile", HqlFilter.Operator.LIKE, "%"+mobile+"%");
}
if(education != null && education.equalsIgnoreCase("") == false && education.equalsIgnoreCase("null") == false)
{
	hqlFilter.addQryCond("education", HqlFilter.Operator.EQ, education);
}
if(politicalstatus != null && politicalstatus.equalsIgnoreCase("") == false && politicalstatus.equalsIgnoreCase("null") == false)
{
	hqlFilter.addQryCond("politicalstatus", HqlFilter.Operator.EQ, politicalstatus);
}
if(of_volunteerteam != null && of_volunteerteam.equalsIgnoreCase("") == false && of_volunteerteam.equalsIgnoreCase("null") == false)
{
	hqlFilter.addQryCond("of_volunteerteam", HqlFilter.Operator.LIKE, "%"+of_volunteerteam+"%");
}
if(join_date != null && join_date.equalsIgnoreCase("") == false && join_date.equalsIgnoreCase("null") == false)
{
	hqlFilter.addQryCond("join_date", HqlFilter.Operator.LIKE, "%"+join_date+"%");
}
if(status != null && status.equalsIgnoreCase("") == false && status.equalsIgnoreCase("null") == false)
{
	hqlFilter.addQryCond("status", HqlFilter.Operator.LIKE, "%"+status+"%");
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

//hqlFilter.setSort("created_at");
hqlFilter.setOrder("desc");

        List<Jc_volunteer> listObj = jc_volunteerService.findByFilter(hqlFilter);
        JSONArray jsonArr = new JSONArray();
        int iTotalCnt = 0;
		for(int i=0;i<listObj.size();i++)
		{
			Jc_volunteer jc_volunteer = listObj.get(i);
			JSONObject jsonTmp = new JSONObject();
			jsonTmp.put("id", jc_volunteer.getId());
			jsonTmp.put("name",jc_volunteer.getname());
			jsonTmp.put("idnumber",jc_volunteer.getidnumber());
			jsonTmp.put("sex",jc_volunteer.getsex());
			jsonTmp.put("birthday",TimeUtil.formatDate(jc_volunteer.getbirthday(),"yyyy-MM-dd"));
			jsonTmp.put("age",jc_volunteer.getage());
			jsonTmp.put("mobile",jc_volunteer.getmobile());
			jsonTmp.put("education",jc_volunteer.geteducation());
			jsonTmp.put("politicalstatus",jc_volunteer.getpoliticalstatus());
			
			jsonTmp.put("of_volunteerteam",ConstValue.hmDicMap.get(jc_volunteer.getof_volunteerteam()));
			
			jsonTmp.put("join_date",TimeUtil.formatDate(jc_volunteer.getjoin_date(),"yyyy-MM-dd"));
			jsonTmp.put("certificate_id",jc_volunteer.getcertificate_id());
			jsonTmp.put("special_skill",jc_volunteer.getspecial_skill());
			jsonTmp.put("status",jc_volunteer.getstatus());
			jsonTmp.put("pictures",jc_volunteer.getpictures());
			jsonTmp.put("note",jc_volunteer.getnote());

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
		Jc_volunteer jc_volunteer = jc_volunteerService.getById(id);
		if(jc_volunteer != null)
		{
			jsonObj.put("name",jc_volunteer.getname());
			jsonObj.put("idnumber",jc_volunteer.getidnumber());
			jsonObj.put("sex",jc_volunteer.getsex());
			jsonObj.put("birthday",TimeUtil.formatDate(jc_volunteer.getbirthday(),"yyyy-MM-dd"));
			jsonObj.put("age",jc_volunteer.getage());
			jsonObj.put("mobile",jc_volunteer.getmobile());
			jsonObj.put("education",jc_volunteer.geteducation());
			jsonObj.put("politicalstatus",jc_volunteer.getpoliticalstatus());
			jsonObj.put("of_volunteerteam",jc_volunteer.getof_volunteerteam());
			jsonObj.put("join_date",TimeUtil.formatDate(jc_volunteer.getjoin_date(),"yyyy-MM-dd"));
			jsonObj.put("certificate_id",jc_volunteer.getcertificate_id());
			jsonObj.put("special_skill",jc_volunteer.getspecial_skill());
			jsonObj.put("status",jc_volunteer.getstatus());
			jsonObj.put("pictures",jc_volunteer.getpictures());
			jsonObj.put("note",jc_volunteer.getnote());

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
