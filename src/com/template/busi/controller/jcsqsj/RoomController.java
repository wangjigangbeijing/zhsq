package com.template.busi.controller.jcsqsj;
import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import com.template.model.jcsqsj.Room;
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
@RequestMapping("roomController")
public class RoomController {
	private static Logger logger = Logger.getLogger(RoomController.class);
	@Autowired
	private  HttpServletRequest request;
	@Autowired
	private RoomService roomService;
@RequestMapping(value="addOrUpdate",method = RequestMethod.POST,produces="text/html;charset=UTF-8")
@ResponseBody
public String addOrUpdate(String id,String dataid,String number,String ofcommunity,
		String ofresidebuilding,String ofunit,Integer level,String status,String isgrouporiented,
		String ownertype,String propertypapertype,String propertypaperid,String address,String propertymap,String note)
{
	JSONObject jsonObj = new JSONObject();
	try
	{
		Room room;
		if(id==null || id.equalsIgnoreCase(""))
		{
			room = new Room();
			room.setId(Utility.getUniStr());
		}
		else
		{
			room = roomService.getById(id);
		}
		room.setdataid(dataid);
		room.setnumber(number);
		room.setofcommunity(ofcommunity);
		room.setofresidebuilding(ofresidebuilding);
		room.setofunit(ofunit);
		room.setlevel(level);
		room.setstatus(status);
		room.setisgrouporiented(isgrouporiented);
		room.setownertype(ownertype);
		room.setpropertypapertype(propertypapertype);
		room.setpropertypaperid(propertypaperid);
		room.setaddress(address);
		room.setpropertymap(propertymap);
		room.setnote(note);

		String userId = (String)request.getSession().getAttribute(ConstValue.SESSION_USER_ID);
		
		String organization = "";
		if(ConstValue.userToOrgMap.containsKey(userId))
			organization = ConstValue.userToOrgMap.get(userId);
		room.setowner(organization);
		
        roomService.save(room);
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
		Room room = roomService.getById(id);
		roomService.delete(room);
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
public String load(String number,String ofcommunity,String ofresidebuilding,String ofunit,String status,String isgrouporiented,String ownertype,String propertypapertype)
{
	JSONObject jsonObj = new JSONObject();
	try
	{
		int iPageSize = 10;
		int iDisplayStart = 0;
		
		String aoData = request.getParameter("aoData");
		
		if(aoData != null && aoData.equalsIgnoreCase("") == false)
		{
			JSONArray jsonArrParam = new JSONArray(aoData);
			for(int i=0;i<jsonArrParam.length();i++)
			{
				if("iDisplayLength".equalsIgnoreCase(jsonArrParam.getJSONObject(i).get("name").toString()))
				{
					iPageSize = jsonArrParam.getJSONObject(i).getInt("value");
				}
				else if("iDisplayStart".equalsIgnoreCase(jsonArrParam.getJSONObject(i).get("name").toString()))
				{
					iDisplayStart = jsonArrParam.getJSONObject(i).getInt("value");
				}
			}
		}
		
		HqlFilter hqlFilter = new HqlFilter(iDisplayStart/iPageSize+1,iPageSize);
		
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
if(status != null && status.equalsIgnoreCase("") == false && status.equalsIgnoreCase("null") == false)
{
	hqlFilter.addQryCond("status", HqlFilter.Operator.LIKE, "%"+status+"%");
}
if(isgrouporiented != null && isgrouporiented.equalsIgnoreCase("") == false && isgrouporiented.equalsIgnoreCase("null") == false)
{
	hqlFilter.addQryCond("isgrouporiented", HqlFilter.Operator.LIKE, "%"+isgrouporiented+"%");
}
if(ownertype != null && ownertype.equalsIgnoreCase("") == false && ownertype.equalsIgnoreCase("null") == false)
{
	hqlFilter.addQryCond("ownertype", HqlFilter.Operator.LIKE, "%"+ownertype+"%");
}
if(propertypapertype != null && propertypapertype.equalsIgnoreCase("") == false && propertypapertype.equalsIgnoreCase("null") == false)
{
	hqlFilter.addQryCond("propertypapertype", HqlFilter.Operator.EQ, propertypapertype);
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

        List<Room> listObj = roomService.findByFilter(hqlFilter);
        JSONArray jsonArr = new JSONArray();
        
        long iTotalCnt = roomService.countByFilter(hqlFilter);
        
		for(int i=0;i<listObj.size();i++)
		{
			Room room = listObj.get(i);
			JSONObject jsonTmp = new JSONObject();
			jsonTmp.put("id", room.getId());
			jsonTmp.put("dataid",room.getdataid());
			jsonTmp.put("number",room.getnumber());
			jsonTmp.put("ofcommunity",room.getofcommunity());
			
			jsonTmp.put("ofresidebuilding",room.getofresidebuilding());
			
			if(ConstValue.hmDicMap.containsKey(room.getofresidebuilding()))
			{
				jsonTmp.put("ofresidebuildingname",ConstValue.hmDicMap.get(room.getofresidebuilding()));
			}
			
			jsonTmp.put("ofunit",room.getofunit());
			jsonTmp.put("level",room.getlevel());
			jsonTmp.put("status",room.getstatus());
			jsonTmp.put("isgrouporiented",room.getisgrouporiented());
			jsonTmp.put("ownertype",room.getownertype());
			jsonTmp.put("propertypapertype",room.getpropertypapertype());
			jsonTmp.put("propertypaperid",room.getpropertypaperid());
			jsonTmp.put("address",room.getaddress());
			jsonTmp.put("propertymap",room.getpropertymap());
			jsonTmp.put("note",room.getnote());
			jsonTmp.put("peoplecharacteristics",room.getpeoplecharacteristics());

       		jsonArr.put(jsonTmp);
		}
        jsonObj.put("totalCount", iTotalCnt);
        jsonObj.put("list", jsonArr);
        jsonObj.put("success", true);
        
        jsonObj.put("aaData", jsonArr);
		jsonObj.put("iTotalRecords", iTotalCnt);
		jsonObj.put("iTotalDisplayRecords", iTotalCnt);
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
		Room room = roomService.getById(id);
		if(room != null)
		{
			jsonObj.put("dataid",room.getdataid());
			jsonObj.put("number",room.getnumber());
			jsonObj.put("ofcommunity",room.getofcommunity());
			jsonObj.put("ofresidebuilding",room.getofresidebuilding());
			jsonObj.put("ofresidebuildingname",ConstValue.hmDicMap.get(room.getofresidebuilding()));
			jsonObj.put("ofunit",room.getofunit());
			jsonObj.put("level",room.getlevel());
			jsonObj.put("status",room.getstatus());
			jsonObj.put("isgrouporiented",room.getisgrouporiented());
			jsonObj.put("ownertype",room.getownertype());
			jsonObj.put("propertypapertype",room.getpropertypapertype());
			jsonObj.put("propertypaperid",room.getpropertypaperid());
			jsonObj.put("address",room.getaddress());
			jsonObj.put("propertymap",room.getpropertymap());
			jsonObj.put("note",room.getnote());

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


@RequestMapping(value="getRoomIdByBuildingIdAndRoomNum",method = {RequestMethod.POST,RequestMethod.GET},produces="text/html;charset=UTF-8")
@ResponseBody
public String getRoomIdByBuildingIdAndRoomNum(String residentBuildingId,String roomNum)
{
	JSONObject jsonObj = new JSONObject();
	try
	{
		HqlFilter hqlFilter = new HqlFilter();
		
		hqlFilter.addQryCond("ofresidebuilding", HqlFilter.Operator.EQ, residentBuildingId);
		
		hqlFilter.addQryCond("number", HqlFilter.Operator.EQ, roomNum);
		
		Room room = roomService.getByFilter(hqlFilter);
		if(room != null)
		{
			jsonObj.put("id", room.getId());
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
