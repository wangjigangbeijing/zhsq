package com.template.busi.controller.jcsqsj;
import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import com.template.model.jcsqsj.Residebuilding;
import com.template.model.jcsqsj.Room;
import com.template.service.jcsqsj.ResidebuildingService;
import com.template.service.jcsqsj.RoomService;
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
@RequestMapping("residebuildingController")
public class ResidebuildingController {
	private static Logger logger = Logger.getLogger(ResidebuildingController.class);
	@Autowired
	private  HttpServletRequest request;
	@Autowired
	private ResidebuildingService residebuildingService;
	@Autowired
	private RoomService roomService;
	
	
@RequestMapping(value="addOrUpdate",method = RequestMethod.POST,produces="text/html;charset=UTF-8")
@ResponseBody
public String addOrUpdate(String id,String dataid,String name,String address,
		String year,String propertyyears,String propertyrights,String heatingsystem,
		String ofcommunity,String buildtype,String buildframework,String constructiontype,String units,
		Integer levels,Integer gralevels,Integer elevators,Integer area,String developer,String propertyowner,String propertyownertel,
		String user,String usertel,String propertymanage,String propertymanagecontact,String propertymanagecontacttel,
		Integer longitude,Integer latitude,String status,String pictures,String note,Integer familiesinbuilding)
{
	JSONObject jsonObj = new JSONObject();
	try
	{
		Residebuilding residebuilding;
		if(id==null || id.equalsIgnoreCase(""))
		{
			residebuilding = new Residebuilding();
			residebuilding.setId(Utility.getUniStr());
		}
		else
		{
			residebuilding = residebuildingService.getById(id);
		}
		residebuilding.setdataid(dataid);
		residebuilding.setname(name);
		residebuilding.setaddress(address);
		residebuilding.setyear(year);
		residebuilding.setpropertyyears(propertyyears);
		residebuilding.setpropertyrights(propertyrights);
		residebuilding.setheatingsystem(heatingsystem);
		residebuilding.setofcommunity(ofcommunity);
		residebuilding.setbuildtype(buildtype);
		residebuilding.setbuildframework(buildframework);
		residebuilding.setconstructiontype(constructiontype);
		residebuilding.setunits(units);
		residebuilding.setlevels(levels);
		residebuilding.setgralevels(gralevels);
		residebuilding.setelevators(elevators);
		residebuilding.setarea(area);
		residebuilding.setdeveloper(developer);
		residebuilding.setpropertyowner(propertyowner);
		residebuilding.setpropertyownertel(propertyownertel);
		residebuilding.setuser(user);
		residebuilding.setusertel(usertel);
		residebuilding.setpropertymanage(propertymanage);
		residebuilding.setpropertymanagecontact(propertymanagecontact);
		residebuilding.setpropertymanagecontacttel(propertymanagecontacttel);
		//residebuilding.setlongitude(longitude);
		//residebuilding.setlatitude(latitude);
		residebuilding.setstatus(status);
		residebuilding.setpictures(pictures);
		residebuilding.setnote(note);
		residebuilding.setfamiliesinbuilding(familiesinbuilding);

        residebuildingService.save(residebuilding);
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
		Residebuilding residebuilding = residebuildingService.getById(id);
		residebuildingService.delete(residebuilding);
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
public String load(String name,String address,String propertyyears,String propertyrights,String heatingsystem,String ofcommunity,String buildtype,String buildframework,String constructiontype,String status)
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

        List<Residebuilding> listObj = residebuildingService.findByFilter(hqlFilter);
        JSONArray jsonArr = new JSONArray();
        int iTotalCnt = 0;
		for(int i=0;i<listObj.size();i++)
		{
			Residebuilding residebuilding = listObj.get(i);
			JSONObject jsonTmp = new JSONObject();
			jsonTmp.put("id", residebuilding.getId());
			jsonTmp.put("dataid",residebuilding.getdataid());
			jsonTmp.put("name",residebuilding.getname());
			jsonTmp.put("address",residebuilding.getaddress());
			jsonTmp.put("year",residebuilding.getyear());
			jsonTmp.put("propertyyears",residebuilding.getpropertyyears());
			jsonTmp.put("propertyrights",residebuilding.getpropertyrights());
			jsonTmp.put("heatingsystem",residebuilding.getheatingsystem());
			
			jsonTmp.put("ofcommunity",residebuilding.getofcommunity());
			
			jsonTmp.put("buildtype",residebuilding.getbuildtype());
			jsonTmp.put("buildframework",residebuilding.getbuildframework());
			jsonTmp.put("constructiontype",residebuilding.getconstructiontype());
			jsonTmp.put("units",residebuilding.getunits());
			jsonTmp.put("levels",residebuilding.getlevels());
			jsonTmp.put("elevators",residebuilding.getelevators());
			jsonTmp.put("area",residebuilding.getarea());
			jsonTmp.put("developer",residebuilding.getdeveloper());
			jsonTmp.put("propertyowner",residebuilding.getpropertyowner());
			jsonTmp.put("propertyownertel",residebuilding.getpropertyownertel());
			jsonTmp.put("user",residebuilding.getuser());
			jsonTmp.put("usertel",residebuilding.getusertel());
			jsonTmp.put("propertymanage",residebuilding.getpropertymanage());
			jsonTmp.put("propertymanagecontact",residebuilding.getpropertymanagecontact());
			jsonTmp.put("propertymanagecontacttel",residebuilding.getpropertymanagecontacttel());
			//jsonTmp.put("longitude",residebuilding.getlongitude());
			//jsonTmp.put("latitude",residebuilding.getlatitude());
			jsonTmp.put("status",residebuilding.getstatus());
			jsonTmp.put("pictures",residebuilding.getpictures());
			jsonTmp.put("note",residebuilding.getnote());
			jsonTmp.put("familiesinbuilding",residebuilding.getfamiliesinbuilding());

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
			Residebuilding residebuilding = residebuildingService.getById(id);
			if(residebuilding != null)
			{
				jsonObj.put("dataid",residebuilding.getdataid());
				jsonObj.put("name",residebuilding.getname());
				jsonObj.put("address",residebuilding.getaddress());
				jsonObj.put("year",residebuilding.getyear());
				jsonObj.put("propertyyears",residebuilding.getpropertyyears());
				jsonObj.put("propertyrights",residebuilding.getpropertyrights());
				jsonObj.put("heatingsystem",residebuilding.getheatingsystem());
				jsonObj.put("ofcommunity",residebuilding.getofcommunity());
				jsonObj.put("buildtype",residebuilding.getbuildtype());
				jsonObj.put("buildframework",residebuilding.getbuildframework());
				jsonObj.put("constructiontype",residebuilding.getconstructiontype());
				jsonObj.put("units",residebuilding.getunits());
				jsonObj.put("levels",residebuilding.getlevels());
				jsonObj.put("gralevels",residebuilding.getgralevels());
				jsonObj.put("elevators",residebuilding.getelevators());
				jsonObj.put("area",residebuilding.getarea());
				jsonObj.put("developer",residebuilding.getdeveloper());
				jsonObj.put("propertyowner",residebuilding.getpropertyowner());
				jsonObj.put("propertyownertel",residebuilding.getpropertyownertel());
				jsonObj.put("user",residebuilding.getuser());
				jsonObj.put("usertel",residebuilding.getusertel());
				jsonObj.put("propertymanage",residebuilding.getpropertymanage());
				jsonObj.put("propertymanagecontact",residebuilding.getpropertymanagecontact());
				jsonObj.put("propertymanagecontacttel",residebuilding.getpropertymanagecontacttel());
				//jsonObj.put("longitude",residebuilding.getlongitude());
				//jsonObj.put("latitude",residebuilding.getlatitude());
				jsonObj.put("status",residebuilding.getstatus());
				jsonObj.put("pictures",residebuilding.getpictures());
				jsonObj.put("note",residebuilding.getnote());
				jsonObj.put("familiesinbuilding",residebuilding.getfamiliesinbuilding());
	
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
	
	@RequestMapping(value="getMinqing",method = {RequestMethod.POST,RequestMethod.GET},produces="text/html;charset=UTF-8")
	@ResponseBody
	public String getMinqing(String id)
	{
		JSONObject jsonObj = new JSONObject();
		try
		{
			Residebuilding residebuilding = residebuildingService.getById(id);
			if(residebuilding != null)
			{
				String units = residebuilding.getunits();
				
				if(units == null)
					units = "";
				
				String [] unitArr = units.split(",");
				
				JSONArray jsonUnitArr = new JSONArray();
				
				int maxRoomNum = 0;
				int curRoomNum = 0;
				
				String curLevel = "1";
				
				for(int i=0;i<unitArr.length;i++)
				{
					JSONObject jsonUnit = new JSONObject();
					
					String unit = unitArr[i];
				
					HqlFilter hqlFilter = new HqlFilter();
					
					hqlFilter.addQryCond("ofunit", HqlFilter.Operator.EQ, unit);
					
					hqlFilter.addQryCond("ofresidebuilding", HqlFilter.Operator.EQ, residebuilding.getId());
					
					hqlFilter.setSort("number");
					
					//hqlFilter.setOrder("desc");
					
					JSONArray jsonRoomArr = new JSONArray();
					
					List<Room> listRoom = roomService.findByFilter(hqlFilter);
					
					for(int j=0;j<listRoom.size();j++)
					{
						Room room = listRoom.get(j);
						
						JSONObject jsonRoom = new JSONObject();

						jsonRoom.put("roomId", room.getId());
						
						jsonRoom.put("number", room.getnumber());
						
						jsonRoom.put("level", room.getlevel());
						
						if(curLevel.equalsIgnoreCase(room.getlevel()))
							curRoomNum ++;
						else
						{
							if(curRoomNum > maxRoomNum)
								maxRoomNum = curRoomNum;
							
							curRoomNum = 0;
						}
						
						jsonRoom.put("peoplecharacteristics", room.getpeoplecharacteristics());
						
						String residentNames = room.getresidentname();
						String residentIds = room.getresidentids();
						
						if(residentNames == null)
							residentNames = "";
						
						if(residentIds == null)
							residentIds = "";
						
						jsonRoom.put("residentNames", residentNames);
						
						jsonRoom.put("residentIds", residentIds);
						
						jsonRoomArr.put(jsonRoom);
					}
					
					jsonUnit.put("rooms", jsonRoomArr);
					jsonUnit.put("name", unit);
					
					jsonUnitArr.put(jsonUnit);
				}
				
				jsonObj.put("units", jsonUnitArr);
				jsonObj.put("name", residebuilding.getofcommunity()+residebuilding.getname());
				jsonObj.put("maxRoomNum", maxRoomNum);
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
