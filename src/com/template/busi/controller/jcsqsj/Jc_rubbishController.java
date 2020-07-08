package com.template.busi.controller.jcsqsj;
import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import com.template.model.Jc_rubbish;
import com.template.service.jcsqsj.Jc_rubbishService;
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
@RequestMapping("jc_rubbishController")
public class Jc_rubbishController {
	private static Logger logger = Logger.getLogger(Jc_rubbishController.class);
	@Autowired
	private  HttpServletRequest request;
	@Autowired
	private Jc_rubbishService jc_rubbishService;
@RequestMapping(value="addOrUpdate",method = RequestMethod.POST,produces="text/html;charset=UTF-8")
@ResponseBody
public String addOrUpdate(String id,String name,String type,String kind,String catagory,String address,String department,String departtel,String cleartime,String picture,String note)
{
	JSONObject jsonObj = new JSONObject();
	try
	{
		Jc_rubbish jc_rubbish;
		if(id==null || id.equalsIgnoreCase(""))
		{
			jc_rubbish = new Jc_rubbish();
			jc_rubbish.setId(Utility.getUniStr());
		}
		else
		{
			jc_rubbish = jc_rubbishService.getById(id);
		}
		jc_rubbish.setname(name);
		jc_rubbish.settype(type);
		jc_rubbish.setkind(kind);
		jc_rubbish.setcatagory(catagory);
		jc_rubbish.setaddress(address);
		jc_rubbish.setdepartment(department);
		jc_rubbish.setdeparttel(departtel);
		jc_rubbish.setcleartime(cleartime);
		jc_rubbish.setpicture(picture);
		jc_rubbish.setnote(note);

        jc_rubbishService.save(jc_rubbish);
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
		Jc_rubbish jc_rubbish = jc_rubbishService.getById(id);
		jc_rubbishService.delete(jc_rubbish);
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
public String load(String name,String type,String kind,String catagory,String address,String department,String departtel)
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
	hqlFilter.addQryCond("type", HqlFilter.Operator.LIKE, "%"+type+"%");
}
if(kind != null && kind.equalsIgnoreCase("") == false && kind.equalsIgnoreCase("null") == false)
{
	hqlFilter.addQryCond("kind", HqlFilter.Operator.LIKE, "%"+kind+"%");
}
if(catagory != null && catagory.equalsIgnoreCase("") == false && catagory.equalsIgnoreCase("null") == false)
{
	hqlFilter.addQryCond("catagory", HqlFilter.Operator.LIKE, "%"+catagory+"%");
}
if(address != null && address.equalsIgnoreCase("") == false && address.equalsIgnoreCase("null") == false)
{
	hqlFilter.addQryCond("address", HqlFilter.Operator.LIKE, "%"+address+"%");
}
if(department != null && department.equalsIgnoreCase("") == false && department.equalsIgnoreCase("null") == false)
{
	hqlFilter.addQryCond("department", HqlFilter.Operator.LIKE, "%"+department+"%");
}
if(departtel != null && departtel.equalsIgnoreCase("") == false && departtel.equalsIgnoreCase("null") == false)
{
	hqlFilter.addQryCond("departtel", HqlFilter.Operator.LIKE, "%"+departtel+"%");
}

        List<Jc_rubbish> listObj = jc_rubbishService.findByFilter(hqlFilter);
        JSONArray jsonArr = new JSONArray();
        int iTotalCnt = 0;
		for(int i=0;i<listObj.size();i++)
		{
			Jc_rubbish jc_rubbish = listObj.get(i);
			JSONObject jsonTmp = new JSONObject();
			jsonTmp.put("id", jc_rubbish.getId());
			jsonTmp.put("name",jc_rubbish.getname());
			jsonTmp.put("type",jc_rubbish.gettype());
			jsonTmp.put("kind",jc_rubbish.getkind());
			jsonTmp.put("catagory",jc_rubbish.getcatagory());
			jsonTmp.put("address",jc_rubbish.getaddress());
			jsonTmp.put("department",jc_rubbish.getdepartment());
			jsonTmp.put("departtel",jc_rubbish.getdeparttel());
			jsonTmp.put("cleartime",jc_rubbish.getcleartime());
			jsonTmp.put("picture",jc_rubbish.getpicture());
			jsonTmp.put("note",jc_rubbish.getnote());

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
		Jc_rubbish jc_rubbish = jc_rubbishService.getById(id);
		if(jc_rubbish != null)
		{
			jsonObj.put("name",jc_rubbish.getname());
			jsonObj.put("type",jc_rubbish.gettype());
			jsonObj.put("kind",jc_rubbish.getkind());
			jsonObj.put("catagory",jc_rubbish.getcatagory());
			jsonObj.put("address",jc_rubbish.getaddress());
			jsonObj.put("department",jc_rubbish.getdepartment());
			jsonObj.put("departtel",jc_rubbish.getdeparttel());
			jsonObj.put("cleartime",jc_rubbish.getcleartime());
			jsonObj.put("picture",jc_rubbish.getpicture());
			jsonObj.put("note",jc_rubbish.getnote());

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
