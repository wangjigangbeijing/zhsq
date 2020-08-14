package com.template.busi.controller.jcsqsj;
import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import com.template.model.jcsqsj.Jc_partymember;
import com.template.service.jcsqsj.Jc_partymemberService;
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
@RequestMapping("jc_partymemberController")
public class Jc_partymemberController {
	private static Logger logger = Logger.getLogger(Jc_partymemberController.class);
	@Autowired
	private  HttpServletRequest request;
	@Autowired
	private Jc_partymemberService jc_partymemberService;
@RequestMapping(value="addOrUpdate",method = RequestMethod.POST,produces="text/html;charset=UTF-8")
@ResponseBody
public String addOrUpdate(String id,String name,String idnumber,String sex,String birthday,String age,String mobile,String education,String partymembertype,String of_partyorganization,String isincommunity,String homeaddress,String zhiwu,String joinpartydate,String inpartydate,String dyage,String membership,String islost,String lostdate,String movemember,String moveto,String pictures,String note)
{
	JSONObject jsonObj = new JSONObject();
	try
	{
		Jc_partymember jc_partymember;
		if(id==null || id.equalsIgnoreCase(""))
		{
			jc_partymember = new Jc_partymember();
			jc_partymember.setId(Utility.getUniStr());
		}
		else
		{
			jc_partymember = jc_partymemberService.getById(id);
		}
		jc_partymember.setname(name);
		jc_partymember.setidnumber(idnumber);
		jc_partymember.setsex(sex);
		jc_partymember.setbirthday(TimeUtil.parseDate(birthday, "yyyy-MM-dd"));
		jc_partymember.setage(age);
		jc_partymember.setmobile(mobile);
		jc_partymember.seteducation(education);
		jc_partymember.setpartymembertype(partymembertype);
		jc_partymember.setof_partyorganization(of_partyorganization);
		jc_partymember.setisincommunity(isincommunity);
		jc_partymember.sethomeaddress(homeaddress);
		jc_partymember.setzhiwu(zhiwu);
		jc_partymember.setjoinpartydate(TimeUtil.parseDate(joinpartydate, "yyyy-MM-dd"));
		jc_partymember.setinpartydate(TimeUtil.parseDate(inpartydate, "yyyy-MM-dd"));
		jc_partymember.setdyage(dyage);
		jc_partymember.setmembership(membership);
		jc_partymember.setislost(islost);
		jc_partymember.setlostdate(TimeUtil.parseDate(lostdate, "yyyy-MM-dd"));
		jc_partymember.setmovemember(movemember);
		jc_partymember.setmoveto(moveto);
		jc_partymember.setpictures(pictures);
		jc_partymember.setnote(note);

		String userId = (String)request.getSession().getAttribute(ConstValue.SESSION_USER_ID);
		
		String organization = "";
		if(ConstValue.userToOrgMap.containsKey(userId))
			organization = ConstValue.userToOrgMap.get(userId);
		jc_partymember.setowner(organization);
		
        jc_partymemberService.save(jc_partymember);
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
		Jc_partymember jc_partymember = jc_partymemberService.getById(id);
		jc_partymemberService.delete(jc_partymember);
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
public String load(String name,String idnumber,String sex,String birthday,String age,String mobile,String education,String partymembertype,String of_partyorganization,String isincommunity,String joinpartydate,String dyage,String membership,String islost,String movemember)
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
if(partymembertype != null && partymembertype.equalsIgnoreCase("") == false && partymembertype.equalsIgnoreCase("null") == false)
{
	hqlFilter.addQryCond("partymembertype", HqlFilter.Operator.LIKE, "%"+partymembertype+"%");
}
if(of_partyorganization != null && of_partyorganization.equalsIgnoreCase("") == false && of_partyorganization.equalsIgnoreCase("null") == false)
{
	hqlFilter.addQryCond("of_partyorganization", HqlFilter.Operator.LIKE, "%"+of_partyorganization+"%");
}
if(isincommunity != null && isincommunity.equalsIgnoreCase("") == false && isincommunity.equalsIgnoreCase("null") == false)
{
	hqlFilter.addQryCond("isincommunity", HqlFilter.Operator.LIKE, "%"+isincommunity+"%");
}
if(joinpartydate != null && joinpartydate.equalsIgnoreCase("") == false && joinpartydate.equalsIgnoreCase("null") == false)
{
	hqlFilter.addQryCond("joinpartydate", HqlFilter.Operator.LIKE, "%"+joinpartydate+"%");
}
if(dyage != null && dyage.equalsIgnoreCase("") == false && dyage.equalsIgnoreCase("null") == false)
{
	hqlFilter.addQryCond("dyage", HqlFilter.Operator.LIKE, "%"+dyage+"%");
}
if(membership != null && membership.equalsIgnoreCase("") == false && membership.equalsIgnoreCase("null") == false)
{
	hqlFilter.addQryCond("membership", HqlFilter.Operator.EQ, membership);
}
if(islost != null && islost.equalsIgnoreCase("") == false && islost.equalsIgnoreCase("null") == false)
{
	hqlFilter.addQryCond("islost", HqlFilter.Operator.LIKE, "%"+islost+"%");
}
if(movemember != null && movemember.equalsIgnoreCase("") == false && movemember.equalsIgnoreCase("null") == false)
{
	hqlFilter.addQryCond("movemember", HqlFilter.Operator.LIKE, "%"+movemember+"%");
}


String userId = (String)request.getSession().getAttribute(ConstValue.HTTP_HEADER_USERID);

String organization = "";
if(ConstValue.userToOrgMap.containsKey(userId))
	organization = ConstValue.userToOrgMap.get(userId);

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

        List<Jc_partymember> listObj = jc_partymemberService.findByFilter(hqlFilter);
        JSONArray jsonArr = new JSONArray();
        int iTotalCnt = 0;
		for(int i=0;i<listObj.size();i++)
		{
			Jc_partymember jc_partymember = listObj.get(i);
			JSONObject jsonTmp = new JSONObject();
			jsonTmp.put("id", jc_partymember.getId());
			jsonTmp.put("name",jc_partymember.getname());
			jsonTmp.put("idnumber",jc_partymember.getidnumber());
			jsonTmp.put("sex",jc_partymember.getsex());
			
			if(jc_partymember.getbirthday() != null)
				jsonTmp.put("birthday",TimeUtil.formatDate(jc_partymember.getbirthday(),"yyyy-MM-dd"));
			else
				jsonTmp.put("birthday","-");
			
			jsonTmp.put("age",jc_partymember.getage());
			jsonTmp.put("mobile",jc_partymember.getmobile());
			jsonTmp.put("education",jc_partymember.geteducation());
			jsonTmp.put("partymembertype",jc_partymember.getpartymembertype());
			jsonTmp.put("of_partyorganization",jc_partymember.getof_partyorganization());
			jsonTmp.put("isincommunity",jc_partymember.getisincommunity());
			jsonTmp.put("homeaddress",jc_partymember.gethomeaddress());
			jsonTmp.put("zhiwu",jc_partymember.getzhiwu());
			
			if(jc_partymember.getjoinpartydate() != null)
				jsonTmp.put("joinpartydate",TimeUtil.formatDate(jc_partymember.getjoinpartydate(),"yyyy-MM-dd"));
			else
				jsonTmp.put("joinpartydate","-");
			
			
			if(jc_partymember.getinpartydate() != null)
				jsonTmp.put("inpartydate",TimeUtil.formatDate(jc_partymember.getinpartydate(),"yyyy-MM-dd"));
			else
				jsonTmp.put("inpartydate","-");
			
			jsonTmp.put("dyage",jc_partymember.getdyage());
			jsonTmp.put("membership",jc_partymember.getmembership());
			jsonTmp.put("islost",jc_partymember.getislost());
			
			

			if(jc_partymember.getlostdate() != null)
				jsonTmp.put("lostdate",TimeUtil.formatDate(jc_partymember.getlostdate(),"yyyy-MM-dd"));
			else
				jsonTmp.put("lostdate","-");
			
			jsonTmp.put("movemember",jc_partymember.getmovemember());
			jsonTmp.put("moveto",jc_partymember.getmoveto());
			jsonTmp.put("pictures",jc_partymember.getpictures());
			jsonTmp.put("note",jc_partymember.getnote());

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
		Jc_partymember jc_partymember = jc_partymemberService.getById(id);
		if(jc_partymember != null)
		{
			jsonObj.put("name",jc_partymember.getname());
			jsonObj.put("idnumber",jc_partymember.getidnumber());
			jsonObj.put("sex",jc_partymember.getsex());
			jsonObj.put("birthday",TimeUtil.formatDate(jc_partymember.getbirthday(),"yyyy-MM-dd"));
			jsonObj.put("age",jc_partymember.getage());
			jsonObj.put("mobile",jc_partymember.getmobile());
			jsonObj.put("education",jc_partymember.geteducation());
			jsonObj.put("partymembertype",jc_partymember.getpartymembertype());
			jsonObj.put("of_partyorganization",jc_partymember.getof_partyorganization());
			jsonObj.put("isincommunity",jc_partymember.getisincommunity());
			jsonObj.put("homeaddress",jc_partymember.gethomeaddress());
			jsonObj.put("zhiwu",jc_partymember.getzhiwu());
			jsonObj.put("joinpartydate",TimeUtil.formatDate(jc_partymember.getjoinpartydate(),"yyyy-MM-dd"));
			jsonObj.put("inpartydate",TimeUtil.formatDate(jc_partymember.getinpartydate(),"yyyy-MM-dd"));
			jsonObj.put("dyage",jc_partymember.getdyage());
			jsonObj.put("membership",jc_partymember.getmembership());
			jsonObj.put("islost",jc_partymember.getislost());
			jsonObj.put("lostdate",TimeUtil.formatDate(jc_partymember.getlostdate(),"yyyy-MM-dd"));
			jsonObj.put("movemember",jc_partymember.getmovemember());
			jsonObj.put("moveto",jc_partymember.getmoveto());
			jsonObj.put("pictures",jc_partymember.getpictures());
			jsonObj.put("note",jc_partymember.getnote());

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
