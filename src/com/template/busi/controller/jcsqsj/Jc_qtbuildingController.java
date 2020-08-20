package com.template.busi.controller.jcsqsj;
import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import com.template.model.jcsqsj.Jc_qtbuilding;
import com.template.service.jcsqsj.Jc_qtbuildingService;
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
@RequestMapping("jc_qtbuildingController")
public class Jc_qtbuildingController {
	private static Logger logger = Logger.getLogger(Jc_qtbuildingController.class);
	@Autowired
	private  HttpServletRequest request;
	@Autowired
	private Jc_qtbuildingService jc_qtbuildingService;
@RequestMapping(value="addOrUpdate",method = RequestMethod.POST,produces="text/html;charset=UTF-8")
@ResponseBody
public String addOrUpdate(String id,String dataid,String name,String address,String year,String purpose,String propertyyears,String propertyrights,String heatingsystem,String ofcommunity,String buildtype,String buildframework,String constructiontype,String units,Integer levels,Integer gralevels,Integer elevators,Integer area,String developer,String propertyowner,String propertyownertel,String user,String usertel,String propertymanage,String propertymanagecontact,String propertymanagecontacttel,String status,String pictures,String note,Integer orginbuilding)
{
	JSONObject jsonObj = new JSONObject();
	try
	{
		Jc_qtbuilding jc_qtbuilding;
		if(id==null || id.equalsIgnoreCase(""))
		{
			jc_qtbuilding = new Jc_qtbuilding();
			jc_qtbuilding.setId(Utility.getUniStr());
		}
		else
		{
			jc_qtbuilding = jc_qtbuildingService.getById(id);
		}
		jc_qtbuilding.setdataid(dataid);
		jc_qtbuilding.setname(name);
		jc_qtbuilding.setaddress(address);
		jc_qtbuilding.setyear(year);
		jc_qtbuilding.setpurpose(purpose);
		jc_qtbuilding.setpropertyyears(propertyyears);
		jc_qtbuilding.setpropertyrights(propertyrights);
		jc_qtbuilding.setheatingsystem(heatingsystem);
		jc_qtbuilding.setofcommunity(ofcommunity);
		jc_qtbuilding.setbuildtype(buildtype);
		jc_qtbuilding.setbuildframework(buildframework);
		jc_qtbuilding.setconstructiontype(constructiontype);
		jc_qtbuilding.setunits(units);
		jc_qtbuilding.setlevels(levels);
		jc_qtbuilding.setgralevels(gralevels);
		jc_qtbuilding.setelevators(elevators);
		jc_qtbuilding.setarea(area);
		jc_qtbuilding.setdeveloper(developer);
		jc_qtbuilding.setpropertyowner(propertyowner);
		jc_qtbuilding.setpropertyownertel(propertyownertel);
		jc_qtbuilding.setuser(user);
		jc_qtbuilding.setusertel(usertel);
		jc_qtbuilding.setpropertymanage(propertymanage);
		jc_qtbuilding.setpropertymanagecontact(propertymanagecontact);
		jc_qtbuilding.setpropertymanagecontacttel(propertymanagecontacttel);
		jc_qtbuilding.setstatus(status);
		jc_qtbuilding.setpictures(pictures);
		jc_qtbuilding.setnote(note);
		jc_qtbuilding.setorginbuilding(orginbuilding);

		String userId = (String)request.getSession().getAttribute(ConstValue.SESSION_USER_ID);
		
		String organization = "";
		if(ConstValue.userToOrgMap.containsKey(userId))
			organization = ConstValue.userToOrgMap.get(userId);
		jc_qtbuilding.setowner(organization);
		
        jc_qtbuildingService.save(jc_qtbuilding);
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
		Jc_qtbuilding jc_qtbuilding = jc_qtbuildingService.getById(id);
		jc_qtbuildingService.delete(jc_qtbuilding);
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
        List<Jc_qtbuilding> listObj = jc_qtbuildingService.findByFilter(hqlFilter);
        JSONArray jsonArr = new JSONArray();
        int iTotalCnt = 0;
		for(int i=0;i<listObj.size();i++)
		{
			Jc_qtbuilding jc_qtbuilding = listObj.get(i);
			JSONObject jsonTmp = new JSONObject();
			jsonTmp.put("id", jc_qtbuilding.getId());
			jsonTmp.put("dataid",jc_qtbuilding.getdataid());
			jsonTmp.put("name",jc_qtbuilding.getname());
			jsonTmp.put("address",jc_qtbuilding.getaddress());
			jsonTmp.put("year",jc_qtbuilding.getyear());
			jsonTmp.put("purpose",jc_qtbuilding.getpurpose());
			jsonTmp.put("propertyyears",jc_qtbuilding.getpropertyyears());
			jsonTmp.put("propertyrights",jc_qtbuilding.getpropertyrights());
			jsonTmp.put("heatingsystem",jc_qtbuilding.getheatingsystem());
			jsonTmp.put("ofcommunity",jc_qtbuilding.getofcommunity());
			jsonTmp.put("buildtype",jc_qtbuilding.getbuildtype());
			jsonTmp.put("buildframework",jc_qtbuilding.getbuildframework());
			jsonTmp.put("constructiontype",jc_qtbuilding.getconstructiontype());
			jsonTmp.put("units",jc_qtbuilding.getunits());
			jsonTmp.put("levels",jc_qtbuilding.getlevels());
			jsonTmp.put("gralevels",jc_qtbuilding.getgralevels());
			jsonTmp.put("elevators",jc_qtbuilding.getelevators());
			jsonTmp.put("area",jc_qtbuilding.getarea());
			jsonTmp.put("developer",jc_qtbuilding.getdeveloper());
			jsonTmp.put("propertyowner",jc_qtbuilding.getpropertyowner());
			jsonTmp.put("propertyownertel",jc_qtbuilding.getpropertyownertel());
			jsonTmp.put("user",jc_qtbuilding.getuser());
			jsonTmp.put("usertel",jc_qtbuilding.getusertel());
			jsonTmp.put("propertymanage",jc_qtbuilding.getpropertymanage());
			jsonTmp.put("propertymanagecontact",jc_qtbuilding.getpropertymanagecontact());
			jsonTmp.put("propertymanagecontacttel",jc_qtbuilding.getpropertymanagecontacttel());
			jsonTmp.put("status",jc_qtbuilding.getstatus());
			jsonTmp.put("pictures",jc_qtbuilding.getpictures());
			jsonTmp.put("note",jc_qtbuilding.getnote());
			jsonTmp.put("orginbuilding",jc_qtbuilding.getorginbuilding());

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
		Jc_qtbuilding jc_qtbuilding = jc_qtbuildingService.getById(id);
		if(jc_qtbuilding != null)
		{
			jsonObj.put("dataid",jc_qtbuilding.getdataid());
			jsonObj.put("name",jc_qtbuilding.getname());
			jsonObj.put("address",jc_qtbuilding.getaddress());
			jsonObj.put("year",jc_qtbuilding.getyear());
			jsonObj.put("purpose",jc_qtbuilding.getpurpose());
			jsonObj.put("propertyyears",jc_qtbuilding.getpropertyyears());
			jsonObj.put("propertyrights",jc_qtbuilding.getpropertyrights());
			jsonObj.put("heatingsystem",jc_qtbuilding.getheatingsystem());
			jsonObj.put("ofcommunity",jc_qtbuilding.getofcommunity());
			jsonObj.put("buildtype",jc_qtbuilding.getbuildtype());
			jsonObj.put("buildframework",jc_qtbuilding.getbuildframework());
			jsonObj.put("constructiontype",jc_qtbuilding.getconstructiontype());
			jsonObj.put("units",jc_qtbuilding.getunits());
			jsonObj.put("levels",jc_qtbuilding.getlevels());
			jsonObj.put("gralevels",jc_qtbuilding.getgralevels());
			jsonObj.put("elevators",jc_qtbuilding.getelevators());
			jsonObj.put("area",jc_qtbuilding.getarea());
			jsonObj.put("developer",jc_qtbuilding.getdeveloper());
			jsonObj.put("propertyowner",jc_qtbuilding.getpropertyowner());
			jsonObj.put("propertyownertel",jc_qtbuilding.getpropertyownertel());
			jsonObj.put("user",jc_qtbuilding.getuser());
			jsonObj.put("usertel",jc_qtbuilding.getusertel());
			jsonObj.put("propertymanage",jc_qtbuilding.getpropertymanage());
			jsonObj.put("propertymanagecontact",jc_qtbuilding.getpropertymanagecontact());
			jsonObj.put("propertymanagecontacttel",jc_qtbuilding.getpropertymanagecontacttel());
			jsonObj.put("status",jc_qtbuilding.getstatus());
			jsonObj.put("pictures",jc_qtbuilding.getpictures());
			jsonObj.put("note",jc_qtbuilding.getnote());
			jsonObj.put("orginbuilding",jc_qtbuilding.getorginbuilding());

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
