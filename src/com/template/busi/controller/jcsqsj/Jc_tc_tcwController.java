package com.template.busi.controller.jcsqsj;
import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import com.template.model.jcsqsj.Jc_tc_tcw;
import com.template.service.jcsqsj.Jc_tc_tcwService;
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
@RequestMapping("jc_tc_tcwController")
public class Jc_tc_tcwController {
	private static Logger logger = Logger.getLogger(Jc_tc_tcwController.class);
	@Autowired
	private  HttpServletRequest request;
	@Autowired
	private Jc_tc_tcwService jc_tc_tcwService;
@RequestMapping(value="addOrUpdate",method = RequestMethod.POST,produces="text/html;charset=UTF-8")
@ResponseBody
public String addOrUpdate(String id,String inparkname,String cwtype,String location,Integer numbers,String UseType,String sizeType,String heightType,String arrange,String hascharge,Integer chargenum,String cwcode,String pciture,String note)
{
	JSONObject jsonObj = new JSONObject();
	try
	{
		Jc_tc_tcw jc_tc_tcw;
		if(id==null || id.equalsIgnoreCase(""))
		{
			jc_tc_tcw = new Jc_tc_tcw();
			jc_tc_tcw.setId(Utility.getUniStr());
		}
		else
		{
			jc_tc_tcw = jc_tc_tcwService.getById(id);
		}
		jc_tc_tcw.setinparkname(inparkname);
		jc_tc_tcw.setcwtype(cwtype);
		jc_tc_tcw.setlocation(location);
		jc_tc_tcw.setnumbers(numbers);
		jc_tc_tcw.setUseType(UseType);
		jc_tc_tcw.setsizeType(sizeType);
		jc_tc_tcw.setheightType(heightType);
		jc_tc_tcw.setarrange(arrange);
		jc_tc_tcw.sethascharge(hascharge);
		jc_tc_tcw.setchargenum(chargenum);
		jc_tc_tcw.setcwcode(cwcode);
		jc_tc_tcw.setpciture(pciture);
		jc_tc_tcw.setnote(note);

		String userId = (String)request.getSession().getAttribute(ConstValue.SESSION_USER_ID);
		
		String organization = "";
		if(ConstValue.userToOrgMap.containsKey(userId))
			organization = ConstValue.userToOrgMap.get(userId);
		jc_tc_tcw.setowner(organization);
		
        jc_tc_tcwService.save(jc_tc_tcw);
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
		Jc_tc_tcw jc_tc_tcw = jc_tc_tcwService.getById(id);
		jc_tc_tcwService.delete(jc_tc_tcw);
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
public String load(String cwtype,String UseType,String sizeType,String heightType,String arrange,String hascharge)
{
	JSONObject jsonObj = new JSONObject();
	try
	{
		HqlFilter hqlFilter = new HqlFilter();
if(cwtype != null && cwtype.equalsIgnoreCase("") == false && cwtype.equalsIgnoreCase("null") == false)
{
	hqlFilter.addQryCond("cwtype", HqlFilter.Operator.LIKE, "%"+cwtype+"%");
}
if(UseType != null && UseType.equalsIgnoreCase("") == false && UseType.equalsIgnoreCase("null") == false)
{
	hqlFilter.addQryCond("UseType", HqlFilter.Operator.LIKE, "%"+UseType+"%");
}
if(sizeType != null && sizeType.equalsIgnoreCase("") == false && sizeType.equalsIgnoreCase("null") == false)
{
	hqlFilter.addQryCond("sizeType", HqlFilter.Operator.LIKE, "%"+sizeType+"%");
}
if(heightType != null && heightType.equalsIgnoreCase("") == false && heightType.equalsIgnoreCase("null") == false)
{
	hqlFilter.addQryCond("heightType", HqlFilter.Operator.LIKE, "%"+heightType+"%");
}
if(arrange != null && arrange.equalsIgnoreCase("") == false && arrange.equalsIgnoreCase("null") == false)
{
	hqlFilter.addQryCond("arrange", HqlFilter.Operator.EQ, arrange);
}
if(hascharge != null && hascharge.equalsIgnoreCase("") == false && hascharge.equalsIgnoreCase("null") == false)
{
	hqlFilter.addQryCond("hascharge", HqlFilter.Operator.LIKE, "%"+hascharge+"%");
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

        List<Jc_tc_tcw> listObj = jc_tc_tcwService.findByFilter(hqlFilter);
        JSONArray jsonArr = new JSONArray();
        int iTotalCnt = 0;
		for(int i=0;i<listObj.size();i++)
		{
			Jc_tc_tcw jc_tc_tcw = listObj.get(i);
			JSONObject jsonTmp = new JSONObject();
			jsonTmp.put("id", jc_tc_tcw.getId());
			
			String inparknameDisplay = jc_tc_tcw.getinparkname();
			
			jsonTmp.put("inparkname",jc_tc_tcw.getinparkname());
			
			if(ConstValue.hmDicMap.containsKey(inparknameDisplay))
				inparknameDisplay = ConstValue.hmDicMap.get(inparknameDisplay);
			
			jsonTmp.put("inparknameDisplay",inparknameDisplay);
			
			jsonTmp.put("cwtype",jc_tc_tcw.getcwtype());
			jsonTmp.put("location",jc_tc_tcw.getlocation());
			jsonTmp.put("numbers",jc_tc_tcw.getnumbers());
			jsonTmp.put("UseType",jc_tc_tcw.getUseType());
			jsonTmp.put("sizeType",jc_tc_tcw.getsizeType());
			jsonTmp.put("heightType",jc_tc_tcw.getheightType());
			jsonTmp.put("arrange",jc_tc_tcw.getarrange());
			jsonTmp.put("hascharge",jc_tc_tcw.gethascharge());
			jsonTmp.put("chargenum",jc_tc_tcw.getchargenum());
			jsonTmp.put("cwcode",jc_tc_tcw.getcwcode());
			jsonTmp.put("pciture",jc_tc_tcw.getpciture());
			jsonTmp.put("note",jc_tc_tcw.getnote());

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
		Jc_tc_tcw jc_tc_tcw = jc_tc_tcwService.getById(id);
		if(jc_tc_tcw != null)
		{
			jsonObj.put("inparkname",jc_tc_tcw.getinparkname());
			jsonObj.put("cwtype",jc_tc_tcw.getcwtype());
			jsonObj.put("location",jc_tc_tcw.getlocation());
			jsonObj.put("numbers",jc_tc_tcw.getnumbers());
			jsonObj.put("UseType",jc_tc_tcw.getUseType());
			jsonObj.put("sizeType",jc_tc_tcw.getsizeType());
			jsonObj.put("heightType",jc_tc_tcw.getheightType());
			jsonObj.put("arrange",jc_tc_tcw.getarrange());
			jsonObj.put("hascharge",jc_tc_tcw.gethascharge());
			jsonObj.put("chargenum",jc_tc_tcw.getchargenum());
			jsonObj.put("cwcode",jc_tc_tcw.getcwcode());
			jsonObj.put("pciture",jc_tc_tcw.getpciture());
			jsonObj.put("note",jc_tc_tcw.getnote());

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
