package com.template.busi.controller.jcsqsj;
import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import com.template.model.jcsqsj.Jc_sqorgmember;
import com.template.service.jcsqsj.Jc_sqorgmemberService;
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
@RequestMapping("jc_sqorgmemberController")
public class Jc_sqorgmemberController {
	private static Logger logger = Logger.getLogger(Jc_sqorgmemberController.class);
	@Autowired
	private  HttpServletRequest request;
	@Autowired
	private Jc_sqorgmemberService jc_sqorgmemberService;
@RequestMapping(value="addOrUpdate",method = RequestMethod.POST,produces="text/html;charset=UTF-8")
@ResponseBody
public String addOrUpdate(String id,String name,String idnumber,String sex,String birthday,String age,String mobile,String education,String politicalstatus,String of_sqorganization,String orgjob,String duty,String unit,String status,String pictures,String note)
{
	JSONObject jsonObj = new JSONObject();
	try
	{
		Jc_sqorgmember jc_sqorgmember;
		if(id==null || id.equalsIgnoreCase(""))
		{
			jc_sqorgmember = new Jc_sqorgmember();
			jc_sqorgmember.setId(Utility.getUniStr());
		}
		else
		{
			jc_sqorgmember = jc_sqorgmemberService.getById(id);
		}
		jc_sqorgmember.setname(name);
		jc_sqorgmember.setidnumber(idnumber);
		jc_sqorgmember.setsex(sex);
		jc_sqorgmember.setbirthday(TimeUtil.parseDate(birthday, "yyyy-MM-dd"));
		jc_sqorgmember.setage(age);
		jc_sqorgmember.setmobile(mobile);
		jc_sqorgmember.seteducation(education);
		jc_sqorgmember.setpoliticalstatus(politicalstatus);
		jc_sqorgmember.setof_sqorganization(of_sqorganization);
		jc_sqorgmember.setorgjob(orgjob);
		jc_sqorgmember.setduty(duty);
		jc_sqorgmember.setunit(unit);
		jc_sqorgmember.setstatus(status);
		jc_sqorgmember.setpictures(pictures);
		jc_sqorgmember.setnote(note);

        jc_sqorgmemberService.save(jc_sqorgmember);
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
		Jc_sqorgmember jc_sqorgmember = jc_sqorgmemberService.getById(id);
		jc_sqorgmemberService.delete(jc_sqorgmember);
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
public String load(String name,String idnumber,String sex,String birthday,String age,String mobile,String education,String politicalstatus,String of_sqorganization,String orgjob,String status)
{
	JSONObject jsonObj = new JSONObject();
	try
	{
		HqlFilter hqlFilter = new HqlFilter();
if(name != null && name.equalsIgnoreCase("") == false && name.equalsIgnoreCase("null") == false)
{
	hqlFilter.addQryCond("name", HqlFilter.Operator.LIKE, "%"+name+"%");
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
if(of_sqorganization != null && of_sqorganization.equalsIgnoreCase("") == false && of_sqorganization.equalsIgnoreCase("null") == false)
{
	hqlFilter.addQryCond("of_sqorganization", HqlFilter.Operator.LIKE, "%"+of_sqorganization+"%");
}
if(orgjob != null && orgjob.equalsIgnoreCase("") == false && orgjob.equalsIgnoreCase("null") == false)
{
	hqlFilter.addQryCond("orgjob", HqlFilter.Operator.LIKE, "%"+orgjob+"%");
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

hqlFilter.setSort("created_at");
hqlFilter.setOrder("desc");

        List<Jc_sqorgmember> listObj = jc_sqorgmemberService.findByFilter(hqlFilter);
        JSONArray jsonArr = new JSONArray();
        int iTotalCnt = 0;
		for(int i=0;i<listObj.size();i++)
		{
			Jc_sqorgmember jc_sqorgmember = listObj.get(i);
			JSONObject jsonTmp = new JSONObject();
			jsonTmp.put("id", jc_sqorgmember.getId());
			jsonTmp.put("name",jc_sqorgmember.getname());
			jsonTmp.put("idnumber",jc_sqorgmember.getidnumber());
			jsonTmp.put("sex",jc_sqorgmember.getsex());
			jsonTmp.put("birthday",TimeUtil.formatDate(jc_sqorgmember.getbirthday(),"yyyy-MM-dd"));
			jsonTmp.put("age",jc_sqorgmember.getage());
			jsonTmp.put("mobile",jc_sqorgmember.getmobile());
			jsonTmp.put("education",jc_sqorgmember.geteducation());
			jsonTmp.put("politicalstatus",jc_sqorgmember.getpoliticalstatus());
			jsonTmp.put("of_sqorganization",jc_sqorgmember.getof_sqorganization());
			jsonTmp.put("orgjob",jc_sqorgmember.getorgjob());
			jsonTmp.put("duty",jc_sqorgmember.getduty());
			jsonTmp.put("unit",jc_sqorgmember.getunit());
			jsonTmp.put("status",jc_sqorgmember.getstatus());
			jsonTmp.put("pictures",jc_sqorgmember.getpictures());
			jsonTmp.put("note",jc_sqorgmember.getnote());

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
		Jc_sqorgmember jc_sqorgmember = jc_sqorgmemberService.getById(id);
		if(jc_sqorgmember != null)
		{
			jsonObj.put("name",jc_sqorgmember.getname());
			jsonObj.put("idnumber",jc_sqorgmember.getidnumber());
			jsonObj.put("sex",jc_sqorgmember.getsex());
			jsonObj.put("birthday",TimeUtil.formatDate(jc_sqorgmember.getbirthday(),"yyyy-MM-dd"));
			jsonObj.put("age",jc_sqorgmember.getage());
			jsonObj.put("mobile",jc_sqorgmember.getmobile());
			jsonObj.put("education",jc_sqorgmember.geteducation());
			jsonObj.put("politicalstatus",jc_sqorgmember.getpoliticalstatus());
			jsonObj.put("of_sqorganization",jc_sqorgmember.getof_sqorganization());
			jsonObj.put("orgjob",jc_sqorgmember.getorgjob());
			jsonObj.put("duty",jc_sqorgmember.getduty());
			jsonObj.put("unit",jc_sqorgmember.getunit());
			jsonObj.put("status",jc_sqorgmember.getstatus());
			jsonObj.put("pictures",jc_sqorgmember.getpictures());
			jsonObj.put("note",jc_sqorgmember.getnote());

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
