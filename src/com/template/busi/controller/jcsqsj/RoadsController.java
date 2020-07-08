package com.template.busi.controller.jcsqsj;
import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import com.template.model.jcsqsj.Roads;
import com.template.service.jcsqsj.RoadsService;
import com.template.util.HqlFilter;
import com.template.util.ConstValue;
import com.template.util.Utility;
import com.template.util.TimeUtil;
import java.util.List;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
@Controller
@RequestMapping("roadsController")
public class RoadsController {
	private static Logger logger = Logger.getLogger(RoadsController.class);
	@Autowired
	private  HttpServletRequest request;
	@Autowired
	private RoadsService roadsService;
@RequestMapping(value="addOrUpdate",method = RequestMethod.POST,produces="text/html;charset=UTF-8")
@ResponseBody
public String addOrUpdate(String id,String dateid,String name,String type,Integer length,Integer width,String material,String direction,
		String leadername,String leadertel,String leaderorg,String status,String pictures,String note)//Integer longitude,Integer latitude,
{
	JSONObject jsonObj = new JSONObject();
	try
	{
		Roads roads;
		if(id==null || id.equalsIgnoreCase(""))
		{
			roads = new Roads();
			roads.setId(Utility.getUniStr());
		}
		else
		{
			roads = roadsService.getById(id);
		}
		roads.setdateid(dateid);
		roads.setname(name);
		roads.settype(type);
		roads.setlength(length);
		roads.setwidth(width);
		roads.setmaterial(material);
		roads.setdirection(direction);
		roads.setleadername(leadername);
		roads.setleadertel(leadertel);
		roads.setleaderorg(leaderorg);
		//roads.setlongitude(longitude);
		//roads.setlatitude(latitude);
		roads.setstatus(status);
		roads.setpictures(pictures);
		roads.setnote(note);

        roadsService.save(roads);
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
		Roads roads = roadsService.getById(id);
		roadsService.delete(roads);
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
public String load(String name,String type,String direction,String leadername,String status)
{
	JSONObject jsonObj = new JSONObject();
	try
	{
		HqlFilter hqlFilter = new HqlFilter();
if(name != null && name.equalsIgnoreCase("") == false && name.equalsIgnoreCase("null") == false)
{
	hqlFilter.addQryCond("name", HqlFilter.Operator.LIKE, "%"+name+"%");
}
if(type != null && type.equalsIgnoreCase("") == false && type.equalsIgnoreCase("null") == false)
{
	hqlFilter.addQryCond("type", HqlFilter.Operator.EQ, type);
}
if(direction != null && direction.equalsIgnoreCase("") == false && direction.equalsIgnoreCase("null") == false)
{
	hqlFilter.addQryCond("direction", HqlFilter.Operator.EQ, direction);
}
if(leadername != null && leadername.equalsIgnoreCase("") == false && leadername.equalsIgnoreCase("null") == false)
{
	hqlFilter.addQryCond("leadername", HqlFilter.Operator.LIKE, "%"+leadername+"%");
}
if(status != null && status.equalsIgnoreCase("") == false && status.equalsIgnoreCase("null") == false)
{
	hqlFilter.addQryCond("status", HqlFilter.Operator.LIKE, "%"+status+"%");
}

        List<Roads> listObj = roadsService.findByFilter(hqlFilter);
        JSONArray jsonArr = new JSONArray();
        int iTotalCnt = 0;
		for(int i=0;i<listObj.size();i++)
		{
			Roads roads = listObj.get(i);
			JSONObject jsonTmp = new JSONObject();
			jsonTmp.put("id", roads.getId());
			jsonTmp.put("dateid",roads.getdateid());
			jsonTmp.put("name",roads.getname());
			jsonTmp.put("type",roads.gettype());
			jsonTmp.put("length",roads.getlength());
			jsonTmp.put("width",roads.getwidth());
			jsonTmp.put("material",roads.getmaterial());
			jsonTmp.put("direction",roads.getdirection());
			jsonTmp.put("leadername",roads.getleadername());
			jsonTmp.put("leadertel",roads.getleadertel());
			jsonTmp.put("leaderorg",roads.getleaderorg());
			//jsonTmp.put("longitude",roads.getlongitude());
			//jsonTmp.put("latitude",roads.getlatitude());
			jsonTmp.put("status",roads.getstatus());
			jsonTmp.put("pictures",roads.getpictures());
			jsonTmp.put("note",roads.getnote());

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
		Roads roads = roadsService.getById(id);
		if(roads != null)
		{
			jsonObj.put("dateid",roads.getdateid());
			jsonObj.put("name",roads.getname());
			jsonObj.put("type",roads.gettype());
			jsonObj.put("length",roads.getlength());
			jsonObj.put("width",roads.getwidth());
			jsonObj.put("material",roads.getmaterial());
			jsonObj.put("direction",roads.getdirection());
			jsonObj.put("leadername",roads.getleadername());
			jsonObj.put("leadertel",roads.getleadertel());
			jsonObj.put("leaderorg",roads.getleaderorg());
			//jsonObj.put("longitude",roads.getlongitude());
			//jsonObj.put("latitude",roads.getlatitude());
			jsonObj.put("status",roads.getstatus());
			jsonObj.put("pictures",roads.getpictures());
			jsonObj.put("note",roads.getnote());

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
