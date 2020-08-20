package com.template.busi.controller.jcsqsj;
import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import com.template.model.jcsqsj.Jc_tc_tcccrk;
import com.template.service.jcsqsj.Jc_tc_tcccrkService;
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
@RequestMapping("jc_tc_tcccrkController")
public class Jc_tc_tcccrkController {
	private static Logger logger = Logger.getLogger(Jc_tc_tcccrkController.class);
	@Autowired
	private  HttpServletRequest request;
	@Autowired
	private Jc_tc_tcccrkService jc_tc_tcccrkService;
@RequestMapping(value="addOrUpdate",method = RequestMethod.POST,produces="text/html;charset=UTF-8")
@ResponseBody
public String addOrUpdate(String id,String rkType,String name,String parkName,String picture,String note)
{
	JSONObject jsonObj = new JSONObject();
	try
	{
		Jc_tc_tcccrk jc_tc_tcccrk;
		if(id==null || id.equalsIgnoreCase(""))
		{
			jc_tc_tcccrk = new Jc_tc_tcccrk();
			jc_tc_tcccrk.setId(Utility.getUniStr());
		}
		else
		{
			jc_tc_tcccrk = jc_tc_tcccrkService.getById(id);
		}
		jc_tc_tcccrk.setrkType(rkType);
		jc_tc_tcccrk.setname(name);
		jc_tc_tcccrk.setparkName(parkName);
		jc_tc_tcccrk.setpicture(picture);
		jc_tc_tcccrk.setnote(note);

		String userId = (String)request.getSession().getAttribute(ConstValue.SESSION_USER_ID);
		
		String organization = "";
		if(ConstValue.userToOrgMap.containsKey(userId))
			organization = ConstValue.userToOrgMap.get(userId);
		jc_tc_tcccrk.setowner(organization);
		
        jc_tc_tcccrkService.save(jc_tc_tcccrk);
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
		Jc_tc_tcccrk jc_tc_tcccrk = jc_tc_tcccrkService.getById(id);
		jc_tc_tcccrkService.delete(jc_tc_tcccrk);
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
public String load(String name,String parkName)
{
	JSONObject jsonObj = new JSONObject();
	try
	{
		HqlFilter hqlFilter = new HqlFilter();
if(name != null && name.equalsIgnoreCase("") == false && name.equalsIgnoreCase("null") == false)
{
	hqlFilter.addQryCond("name", HqlFilter.Operator.LIKE, "%"+name+"%");
}
if(parkName != null && parkName.equalsIgnoreCase("") == false && parkName.equalsIgnoreCase("null") == false)
{
	hqlFilter.addQryCond("parkName", HqlFilter.Operator.LIKE, "%"+parkName+"%");
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

        List<Jc_tc_tcccrk> listObj = jc_tc_tcccrkService.findByFilter(hqlFilter);
        JSONArray jsonArr = new JSONArray();
        int iTotalCnt = 0;
		for(int i=0;i<listObj.size();i++)
		{
			Jc_tc_tcccrk jc_tc_tcccrk = listObj.get(i);
			JSONObject jsonTmp = new JSONObject();
			jsonTmp.put("id", jc_tc_tcccrk.getId());
			jsonTmp.put("rkType",jc_tc_tcccrk.getrkType());
			jsonTmp.put("name",jc_tc_tcccrk.getname());
			jsonTmp.put("parkName",jc_tc_tcccrk.getparkName());
			jsonTmp.put("picture",jc_tc_tcccrk.getpicture());
			jsonTmp.put("note",jc_tc_tcccrk.getnote());

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
		Jc_tc_tcccrk jc_tc_tcccrk = jc_tc_tcccrkService.getById(id);
		if(jc_tc_tcccrk != null)
		{
			jsonObj.put("rkType",jc_tc_tcccrk.getrkType());
			jsonObj.put("name",jc_tc_tcccrk.getname());
			jsonObj.put("parkName",jc_tc_tcccrk.getparkName());
			jsonObj.put("picture",jc_tc_tcccrk.getpicture());
			jsonObj.put("note",jc_tc_tcccrk.getnote());

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
