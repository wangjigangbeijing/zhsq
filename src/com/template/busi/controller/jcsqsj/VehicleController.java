package com.template.busi.controller.jcsqsj;
import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import com.template.model.jcsqsj.Vehicle;
import com.template.service.jcsqsj.VehicleService;
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
@RequestMapping("vehicleController")
public class VehicleController {
	private static Logger logger = Logger.getLogger(VehicleController.class);
	@Autowired
	private  HttpServletRequest request;
	@Autowired
	private VehicleService vehicleService;
@RequestMapping(value="addOrUpdate",method = RequestMethod.POST,produces="text/html;charset=UTF-8")
@ResponseBody
public String addOrUpdate(String id,String dataid,String number,String frameno,String ofcommunity,String ofresidebuilding,String ofunit,String ofroom,String offamily,String type,String brand,String model,String color,String ownername,String ownertel,String status,String pictures,String note)
{
	JSONObject jsonObj = new JSONObject();
	try
	{
		Vehicle vehicle;
		if(id==null || id.equalsIgnoreCase(""))
		{
			vehicle = new Vehicle();
			vehicle.setId(Utility.getUniStr());
		}
		else
		{
			vehicle = vehicleService.getById(id);
		}
		vehicle.setdataid(dataid);
		vehicle.setnumber(number);
		vehicle.setframeno(frameno);
		vehicle.setofcommunity(ofcommunity);
		vehicle.setofresidebuilding(ofresidebuilding);
		vehicle.setofunit(ofunit);
		vehicle.setofroom(ofroom);
		vehicle.setoffamily(offamily);
		vehicle.settype(type);
		vehicle.setbrand(brand);
		vehicle.setmodel(model);
		vehicle.setcolor(color);
		vehicle.setownername(ownername);
		vehicle.setownertel(ownertel);
		vehicle.setstatus(status);
		vehicle.setpictures(pictures);
		vehicle.setnote(note);

        vehicleService.save(vehicle);
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
		Vehicle vehicle = vehicleService.getById(id);
		vehicleService.delete(vehicle);
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
public String load(String number,String ofcommunity,String ofresidebuilding,String ofunit,String ofroom,String offamily,String type,String ownername,String status)
{
	JSONObject jsonObj = new JSONObject();
	try
	{
		HqlFilter hqlFilter = new HqlFilter();
if(number != null && number.equalsIgnoreCase("") == false && number.equalsIgnoreCase("null") == false)
{
	hqlFilter.addQryCond("number", HqlFilter.Operator.LIKE, "%"+number+"%");
}
if(ofcommunity != null && ofcommunity.equalsIgnoreCase("") == false && ofcommunity.equalsIgnoreCase("null") == false)
{
	hqlFilter.addQryCond("ofcommunity", HqlFilter.Operator.LIKE, "%"+ofcommunity+"%");
}
if(ofresidebuilding != null && ofresidebuilding.equalsIgnoreCase("") == false && ofresidebuilding.equalsIgnoreCase("null") == false)
{
	hqlFilter.addQryCond("ofresidebuilding", HqlFilter.Operator.LIKE, "%"+ofresidebuilding+"%");
}
if(ofunit != null && ofunit.equalsIgnoreCase("") == false && ofunit.equalsIgnoreCase("null") == false)
{
	hqlFilter.addQryCond("ofunit", HqlFilter.Operator.LIKE, "%"+ofunit+"%");
}
if(ofroom != null && ofroom.equalsIgnoreCase("") == false && ofroom.equalsIgnoreCase("null") == false)
{
	hqlFilter.addQryCond("ofroom", HqlFilter.Operator.LIKE, "%"+ofroom+"%");
}
if(offamily != null && offamily.equalsIgnoreCase("") == false && offamily.equalsIgnoreCase("null") == false)
{
	hqlFilter.addQryCond("offamily", HqlFilter.Operator.LIKE, "%"+offamily+"%");
}
if(type != null && type.equalsIgnoreCase("") == false && type.equalsIgnoreCase("null") == false)
{
	hqlFilter.addQryCond("type", HqlFilter.Operator.EQ, type);
}
if(ownername != null && ownername.equalsIgnoreCase("") == false && ownername.equalsIgnoreCase("null") == false)
{
	hqlFilter.addQryCond("ownername", HqlFilter.Operator.LIKE, "%"+ownername+"%");
}
if(status != null && status.equalsIgnoreCase("") == false && status.equalsIgnoreCase("null") == false)
{
	hqlFilter.addQryCond("status", HqlFilter.Operator.LIKE, "%"+status+"%");
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

        List<Vehicle> listObj = vehicleService.findByFilter(hqlFilter);
        JSONArray jsonArr = new JSONArray();
        int iTotalCnt = 0;
		for(int i=0;i<listObj.size();i++)
		{
			Vehicle vehicle = listObj.get(i);
			JSONObject jsonTmp = new JSONObject();
			jsonTmp.put("id", vehicle.getId());
			jsonTmp.put("dataid",vehicle.getdataid());
			jsonTmp.put("number",vehicle.getnumber());
			jsonTmp.put("frameno",vehicle.getframeno());
			jsonTmp.put("ofcommunity",vehicle.getofcommunity());
			jsonTmp.put("ofresidebuilding",vehicle.getofresidebuilding());
			jsonTmp.put("ofunit",vehicle.getofunit());
			jsonTmp.put("ofroom",vehicle.getofroom());
			jsonTmp.put("offamily",vehicle.getoffamily());
			jsonTmp.put("type",vehicle.gettype());
			jsonTmp.put("brand",vehicle.getbrand());
			jsonTmp.put("model",vehicle.getmodel());
			jsonTmp.put("color",vehicle.getcolor());
			jsonTmp.put("ownername",vehicle.getownername());
			jsonTmp.put("ownertel",vehicle.getownertel());
			jsonTmp.put("status",vehicle.getstatus());
			jsonTmp.put("pictures",vehicle.getpictures());
			jsonTmp.put("note",vehicle.getnote());

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
		Vehicle vehicle = vehicleService.getById(id);
		if(vehicle != null)
		{
			jsonObj.put("dataid",vehicle.getdataid());
			jsonObj.put("number",vehicle.getnumber());
			jsonObj.put("frameno",vehicle.getframeno());
			jsonObj.put("ofcommunity",vehicle.getofcommunity());
			jsonObj.put("ofresidebuilding",vehicle.getofresidebuilding());
			jsonObj.put("ofunit",vehicle.getofunit());
			jsonObj.put("ofroom",vehicle.getofroom());
			jsonObj.put("offamily",vehicle.getoffamily());
			jsonObj.put("type",vehicle.gettype());
			jsonObj.put("brand",vehicle.getbrand());
			jsonObj.put("model",vehicle.getmodel());
			jsonObj.put("color",vehicle.getcolor());
			jsonObj.put("ownername",vehicle.getownername());
			jsonObj.put("ownertel",vehicle.getownertel());
			jsonObj.put("status",vehicle.getstatus());
			jsonObj.put("pictures",vehicle.getpictures());
			jsonObj.put("note",vehicle.getnote());

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
