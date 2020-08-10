package com.template.busi.controller.jcsqsj;
import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import com.template.model.jcsqsj.Parking;
import com.template.service.jcsqsj.ParkingService;
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
@RequestMapping("parkingController")
public class ParkingController {
	private static Logger logger = Logger.getLogger(ParkingController.class);
	@Autowired
	private  HttpServletRequest request;
	@Autowired
	private ParkingService parkingService;
@RequestMapping(value="addOrUpdate",method = RequestMethod.POST,produces="text/html;charset=UTF-8")
@ResponseBody
public String addOrUpdate(String id,String dateid,String name,String address,String isofficial,Integer parkinglotnum,Integer area,Integer longitude,Integer latitude,String status,String pictures,String note)
{
	JSONObject jsonObj = new JSONObject();
	try
	{
		Parking parking;
		if(id==null || id.equalsIgnoreCase(""))
		{
			parking = new Parking();
			parking.setId(Utility.getUniStr());
		}
		else
		{
			parking = parkingService.getById(id);
		}
		parking.setdateid(dateid);
		parking.setname(name);
		parking.setaddress(address);
		parking.setisofficial(isofficial);
		parking.setparkinglotnum(parkinglotnum);
		parking.setarea(area);
		//parking.setlongitude(longitude);
		//parking.setlatitude(latitude);
		parking.setstatus(status);
		parking.setpictures(pictures);
		parking.setnote(note);

        parkingService.save(parking);
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
		Parking parking = parkingService.getById(id);
		parkingService.delete(parking);
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
public String load(String name,String address,String isofficial,String status)
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
if(isofficial != null && isofficial.equalsIgnoreCase("") == false && isofficial.equalsIgnoreCase("null") == false)
{
	hqlFilter.addQryCond("isofficial", HqlFilter.Operator.LIKE, "%"+isofficial+"%");
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

        List<Parking> listObj = parkingService.findByFilter(hqlFilter);
        JSONArray jsonArr = new JSONArray();
        int iTotalCnt = 0;
		for(int i=0;i<listObj.size();i++)
		{
			Parking parking = listObj.get(i);
			JSONObject jsonTmp = new JSONObject();
			jsonTmp.put("id", parking.getId());
			jsonTmp.put("dateid",parking.getdateid());
			jsonTmp.put("name",parking.getname());
			jsonTmp.put("address",parking.getaddress());
			jsonTmp.put("isofficial",parking.getisofficial());
			jsonTmp.put("parkinglotnum",parking.getparkinglotnum());
			jsonTmp.put("area",parking.getarea());
			//jsonTmp.put("longitude",parking.getlongitude());
			//jsonTmp.put("latitude",parking.getlatitude());
			jsonTmp.put("status",parking.getstatus());
			jsonTmp.put("pictures",parking.getpictures());
			jsonTmp.put("note",parking.getnote());

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
		Parking parking = parkingService.getById(id);
		if(parking != null)
		{
			jsonObj.put("dateid",parking.getdateid());
			jsonObj.put("name",parking.getname());
			jsonObj.put("address",parking.getaddress());
			jsonObj.put("isofficial",parking.getisofficial());
			jsonObj.put("parkinglotnum",parking.getparkinglotnum());
			jsonObj.put("area",parking.getarea());
			//jsonObj.put("longitude",parking.getlongitude());
			//jsonObj.put("latitude",parking.getlatitude());
			jsonObj.put("status",parking.getstatus());
			jsonObj.put("pictures",parking.getpictures());
			jsonObj.put("note",parking.getnote());

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
