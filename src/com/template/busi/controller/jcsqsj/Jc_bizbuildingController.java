package com.template.busi.controller.jcsqsj;
import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import com.template.model.jcsqsj.Jc_bizbuilding;
import com.template.service.jcsqsj.Jc_bizbuildingService;
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
@RequestMapping("jc_bizbuildingController")
public class Jc_bizbuildingController {
	private static Logger logger = Logger.getLogger(Jc_bizbuildingController.class);
	@Autowired
	private  HttpServletRequest request;
	@Autowired
	private Jc_bizbuildingService jc_bizbuildingService;
@RequestMapping(value="addOrUpdate",method = RequestMethod.POST,produces="text/html;charset=UTF-8")
@ResponseBody
public String addOrUpdate(String id,String dataid,String name,String address,String year,String purpose,String propertyyears,String propertyrights,String heatingsystem,String ofcommunity,String buildtype,String buildframework,String constructiontype,String units,Integer levels,Integer elevators,Integer area,String developer,String propertyowner,String propertyownertel,String user,String usertel,String propertymanage,String propertymanagecontact,String propertymanagecontacttel,String status,String pictures,String note,Integer orginbuilding)
{
	JSONObject jsonObj = new JSONObject();
	try
	{
		Jc_bizbuilding jc_bizbuilding;
		if(id==null || id.equalsIgnoreCase(""))
		{
			jc_bizbuilding = new Jc_bizbuilding();
			jc_bizbuilding.setId(Utility.getUniStr());
		}
		else
		{
			jc_bizbuilding = jc_bizbuildingService.getById(id);
		}
		jc_bizbuilding.setdataid(dataid);
		jc_bizbuilding.setname(name);
		jc_bizbuilding.setaddress(address);
		jc_bizbuilding.setyear(TimeUtil.parseDate(year, "yyyy-MM-dd"));
		jc_bizbuilding.setpurpose(purpose);
		jc_bizbuilding.setpropertyyears(propertyyears);
		jc_bizbuilding.setpropertyrights(propertyrights);
		jc_bizbuilding.setheatingsystem(heatingsystem);
		jc_bizbuilding.setofcommunity(ofcommunity);
		jc_bizbuilding.setbuildtype(buildtype);
		jc_bizbuilding.setbuildframework(buildframework);
		jc_bizbuilding.setconstructiontype(constructiontype);
		jc_bizbuilding.setunits(units);
		jc_bizbuilding.setlevels(levels);
		jc_bizbuilding.setelevators(elevators);
		jc_bizbuilding.setarea(area);
		jc_bizbuilding.setdeveloper(developer);
		jc_bizbuilding.setpropertyowner(propertyowner);
		jc_bizbuilding.setpropertyownertel(propertyownertel);
		jc_bizbuilding.setuser(user);
		jc_bizbuilding.setusertel(usertel);
		jc_bizbuilding.setpropertymanage(propertymanage);
		jc_bizbuilding.setpropertymanagecontact(propertymanagecontact);
		jc_bizbuilding.setpropertymanagecontacttel(propertymanagecontacttel);
		jc_bizbuilding.setstatus(status);
		jc_bizbuilding.setpictures(pictures);
		jc_bizbuilding.setnote(note);
		jc_bizbuilding.setorginbuilding(orginbuilding);

        jc_bizbuildingService.save(jc_bizbuilding);
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
		Jc_bizbuilding jc_bizbuilding = jc_bizbuildingService.getById(id);
		jc_bizbuildingService.delete(jc_bizbuilding);
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
public String load(String name,String address,String purpose,String propertyyears,String propertyrights,String heatingsystem,String ofcommunity,String buildtype,String buildframework,String constructiontype,String propertymanagecontact,String status)
{
	JSONObject jsonObj = new JSONObject();
	try
	{
		HqlFilter hqlFilter = new HqlFilter();
if(name != null && name.equalsIgnoreCase("") == false && name.equalsIgnoreCase("null") == false)
{
	hqlFilter.addQryCond("name", HqlFilter.Operator.LIKE, "%"+name+"%");
}
if(address != null && address.equalsIgnoreCase("") == false && address.equalsIgnoreCase("null") == false)
{
	hqlFilter.addQryCond("address", HqlFilter.Operator.LIKE, "%"+address+"%");
}
if(purpose != null && purpose.equalsIgnoreCase("") == false && purpose.equalsIgnoreCase("null") == false)
{
	hqlFilter.addQryCond("purpose", HqlFilter.Operator.EQ, purpose);
}
if(propertyyears != null && propertyyears.equalsIgnoreCase("") == false && propertyyears.equalsIgnoreCase("null") == false)
{
	hqlFilter.addQryCond("propertyyears", HqlFilter.Operator.LIKE, "%"+propertyyears+"%");
}
if(propertyrights != null && propertyrights.equalsIgnoreCase("") == false && propertyrights.equalsIgnoreCase("null") == false)
{
	hqlFilter.addQryCond("propertyrights", HqlFilter.Operator.EQ, propertyrights);
}
if(heatingsystem != null && heatingsystem.equalsIgnoreCase("") == false && heatingsystem.equalsIgnoreCase("null") == false)
{
	hqlFilter.addQryCond("heatingsystem", HqlFilter.Operator.LIKE, "%"+heatingsystem+"%");
}
if(ofcommunity != null && ofcommunity.equalsIgnoreCase("") == false && ofcommunity.equalsIgnoreCase("null") == false)
{
	hqlFilter.addQryCond("ofcommunity", HqlFilter.Operator.LIKE, "%"+ofcommunity+"%");
}
if(buildtype != null && buildtype.equalsIgnoreCase("") == false && buildtype.equalsIgnoreCase("null") == false)
{
	hqlFilter.addQryCond("buildtype", HqlFilter.Operator.LIKE, "%"+buildtype+"%");
}
if(buildframework != null && buildframework.equalsIgnoreCase("") == false && buildframework.equalsIgnoreCase("null") == false)
{
	hqlFilter.addQryCond("buildframework", HqlFilter.Operator.EQ, buildframework);
}
if(constructiontype != null && constructiontype.equalsIgnoreCase("") == false && constructiontype.equalsIgnoreCase("null") == false)
{
	hqlFilter.addQryCond("constructiontype", HqlFilter.Operator.EQ, constructiontype);
}
if(propertymanagecontact != null && propertymanagecontact.equalsIgnoreCase("") == false && propertymanagecontact.equalsIgnoreCase("null") == false)
{
	hqlFilter.addQryCond("propertymanagecontact", HqlFilter.Operator.LIKE, "%"+propertymanagecontact+"%");
}
if(status != null && status.equalsIgnoreCase("") == false && status.equalsIgnoreCase("null") == false)
{
	hqlFilter.addQryCond("status", HqlFilter.Operator.LIKE, "%"+status+"%");
}

String userId = (String)request.getSession().getAttribute(ConstValue.SESSION_USER_ID);

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

        List<Jc_bizbuilding> listObj = jc_bizbuildingService.findByFilter(hqlFilter);
        JSONArray jsonArr = new JSONArray();
        int iTotalCnt = 0;
		for(int i=0;i<listObj.size();i++)
		{
			Jc_bizbuilding jc_bizbuilding = listObj.get(i);
			JSONObject jsonTmp = new JSONObject();
			jsonTmp.put("id", jc_bizbuilding.getId());
			jsonTmp.put("dataid",jc_bizbuilding.getdataid());
			jsonTmp.put("name",jc_bizbuilding.getname());
			jsonTmp.put("address",jc_bizbuilding.getaddress());
			if(jc_bizbuilding.getyear() != null)
				jsonTmp.put("year",TimeUtil.formatDate(jc_bizbuilding.getyear(),"yyyy-MM-dd"));
			else
				jsonTmp.put("year","-");
			jsonTmp.put("purpose",jc_bizbuilding.getpurpose());
			jsonTmp.put("propertyyears",jc_bizbuilding.getpropertyyears());
			jsonTmp.put("propertyrights",jc_bizbuilding.getpropertyrights());
			jsonTmp.put("heatingsystem",jc_bizbuilding.getheatingsystem());
			jsonTmp.put("ofcommunity",jc_bizbuilding.getofcommunity());
			jsonTmp.put("buildtype",jc_bizbuilding.getbuildtype());
			jsonTmp.put("buildframework",jc_bizbuilding.getbuildframework());
			jsonTmp.put("constructiontype",jc_bizbuilding.getconstructiontype());
			jsonTmp.put("units",jc_bizbuilding.getunits());
			jsonTmp.put("levels",jc_bizbuilding.getlevels());
			jsonTmp.put("elevators",jc_bizbuilding.getelevators());
			jsonTmp.put("area",jc_bizbuilding.getarea());
			jsonTmp.put("developer",jc_bizbuilding.getdeveloper());
			jsonTmp.put("propertyowner",jc_bizbuilding.getpropertyowner());
			jsonTmp.put("propertyownertel",jc_bizbuilding.getpropertyownertel());
			jsonTmp.put("user",jc_bizbuilding.getuser());
			jsonTmp.put("usertel",jc_bizbuilding.getusertel());
			jsonTmp.put("propertymanage",jc_bizbuilding.getpropertymanage());
			jsonTmp.put("propertymanagecontact",jc_bizbuilding.getpropertymanagecontact());
			jsonTmp.put("propertymanagecontacttel",jc_bizbuilding.getpropertymanagecontacttel());
			jsonTmp.put("status",jc_bizbuilding.getstatus());
			jsonTmp.put("pictures",jc_bizbuilding.getpictures());
			jsonTmp.put("note",jc_bizbuilding.getnote());
			jsonTmp.put("orginbuilding",jc_bizbuilding.getorginbuilding());

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
		Jc_bizbuilding jc_bizbuilding = jc_bizbuildingService.getById(id);
		if(jc_bizbuilding != null)
		{
			jsonObj.put("dataid",jc_bizbuilding.getdataid());
			jsonObj.put("name",jc_bizbuilding.getname());
			jsonObj.put("address",jc_bizbuilding.getaddress());
			jsonObj.put("year",TimeUtil.formatDate(jc_bizbuilding.getyear(),"yyyy-MM-dd"));
			jsonObj.put("purpose",jc_bizbuilding.getpurpose());
			jsonObj.put("propertyyears",jc_bizbuilding.getpropertyyears());
			jsonObj.put("propertyrights",jc_bizbuilding.getpropertyrights());
			jsonObj.put("heatingsystem",jc_bizbuilding.getheatingsystem());
			jsonObj.put("ofcommunity",jc_bizbuilding.getofcommunity());
			jsonObj.put("buildtype",jc_bizbuilding.getbuildtype());
			jsonObj.put("buildframework",jc_bizbuilding.getbuildframework());
			jsonObj.put("constructiontype",jc_bizbuilding.getconstructiontype());
			jsonObj.put("units",jc_bizbuilding.getunits());
			jsonObj.put("levels",jc_bizbuilding.getlevels());
			jsonObj.put("elevators",jc_bizbuilding.getelevators());
			jsonObj.put("area",jc_bizbuilding.getarea());
			jsonObj.put("developer",jc_bizbuilding.getdeveloper());
			jsonObj.put("propertyowner",jc_bizbuilding.getpropertyowner());
			jsonObj.put("propertyownertel",jc_bizbuilding.getpropertyownertel());
			jsonObj.put("user",jc_bizbuilding.getuser());
			jsonObj.put("usertel",jc_bizbuilding.getusertel());
			jsonObj.put("propertymanage",jc_bizbuilding.getpropertymanage());
			jsonObj.put("propertymanagecontact",jc_bizbuilding.getpropertymanagecontact());
			jsonObj.put("propertymanagecontacttel",jc_bizbuilding.getpropertymanagecontacttel());
			jsonObj.put("status",jc_bizbuilding.getstatus());
			jsonObj.put("pictures",jc_bizbuilding.getpictures());
			jsonObj.put("note",jc_bizbuilding.getnote());
			jsonObj.put("orginbuilding",jc_bizbuilding.getorginbuilding());

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
