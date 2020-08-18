package com.template.busi.controller.jcsqsj;
import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import com.template.model.jcsqsj.Jc_waterpoint;
import com.template.service.jcsqsj.Jc_waterpointService;
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
@RequestMapping("jc_waterpointController")
public class Jc_waterpointController {
	private static Logger logger = Logger.getLogger(Jc_waterpointController.class);
	@Autowired
	private  HttpServletRequest request;
	@Autowired
	private Jc_waterpointService jc_waterpointService;
@RequestMapping(value="addOrUpdate",method = RequestMethod.POST,produces="text/html;charset=UTF-8")
@ResponseBody
public String addOrUpdate(String id,String dataid,String name,String address,String type,String depth,String plan,String note,String picture,String file)
{
	JSONObject jsonObj = new JSONObject();
	try
	{
		Jc_waterpoint jc_waterpoint;
		if(id==null || id.equalsIgnoreCase(""))
		{
			jc_waterpoint = new Jc_waterpoint();
			jc_waterpoint.setId(Utility.getUniStr());
		}
		else
		{
			jc_waterpoint = jc_waterpointService.getById(id);
		}
		jc_waterpoint.setdataid(dataid);
		jc_waterpoint.setname(name);
		jc_waterpoint.setaddress(address);
		jc_waterpoint.settype(type);
		jc_waterpoint.setdepth(depth);
		jc_waterpoint.setplan(plan);
		jc_waterpoint.setnote(note);
		jc_waterpoint.setpicture(picture);
		jc_waterpoint.setfile(file);

        jc_waterpointService.save(jc_waterpoint);
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
		Jc_waterpoint jc_waterpoint = jc_waterpointService.getById(id);
		jc_waterpointService.delete(jc_waterpoint);
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
public String load(String name,String address,String type)
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
if(type != null && type.equalsIgnoreCase("") == false && type.equalsIgnoreCase("null") == false)
{
	hqlFilter.addQryCond("type", HqlFilter.Operator.LIKE, "%"+type+"%");
}

        List<Jc_waterpoint> listObj = jc_waterpointService.findByFilter(hqlFilter);
        JSONArray jsonArr = new JSONArray();
        int iTotalCnt = 0;
		for(int i=0;i<listObj.size();i++)
		{
			Jc_waterpoint jc_waterpoint = listObj.get(i);
			JSONObject jsonTmp = new JSONObject();
			jsonTmp.put("id", jc_waterpoint.getId());
			jsonTmp.put("dataid",jc_waterpoint.getdataid());
			jsonTmp.put("name",jc_waterpoint.getname());
			jsonTmp.put("address",jc_waterpoint.getaddress());
			jsonTmp.put("type",jc_waterpoint.gettype());
			jsonTmp.put("depth",jc_waterpoint.getdepth());
			jsonTmp.put("plan",jc_waterpoint.getplan());
			jsonTmp.put("note",jc_waterpoint.getnote());
			jsonTmp.put("picture",jc_waterpoint.getpicture());
			jsonTmp.put("file",jc_waterpoint.getfile());

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
		Jc_waterpoint jc_waterpoint = jc_waterpointService.getById(id);
		if(jc_waterpoint != null)
		{
			jsonObj.put("dataid",jc_waterpoint.getdataid());
			jsonObj.put("name",jc_waterpoint.getname());
			jsonObj.put("address",jc_waterpoint.getaddress());
			jsonObj.put("type",jc_waterpoint.gettype());
			jsonObj.put("depth",jc_waterpoint.getdepth());
			jsonObj.put("plan",jc_waterpoint.getplan());
			jsonObj.put("note",jc_waterpoint.getnote());
			jsonObj.put("picture",jc_waterpoint.getpicture());
			jsonObj.put("file",jc_waterpoint.getfile());

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
