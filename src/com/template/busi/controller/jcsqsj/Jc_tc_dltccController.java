package com.template.busi.controller.jcsqsj;
import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import com.template.model.jcsqsj.Jc_tc_dltcc;
import com.template.service.jcsqsj.Jc_tc_dltccService;
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
@RequestMapping("jc_tc_dltccController")
public class Jc_tc_dltccController {
	private static Logger logger = Logger.getLogger(Jc_tc_dltccController.class);
	@Autowired
	private  HttpServletRequest request;
	@Autowired
	private Jc_tc_dltccService jc_tc_dltccService;
@RequestMapping(value="addOrUpdate",method = RequestMethod.POST,produces="text/html;charset=UTF-8")
@ResponseBody
public String addOrUpdate(String id,String berthID,String name,String roadname,String area,String rateinfo,String parkeTime,Integer parknum,String picture,String note)
{
	JSONObject jsonObj = new JSONObject();
	try
	{
		Jc_tc_dltcc jc_tc_dltcc;
		if(id==null || id.equalsIgnoreCase(""))
		{
			jc_tc_dltcc = new Jc_tc_dltcc();
			jc_tc_dltcc.setId(Utility.getUniStr());
		}
		else
		{
			jc_tc_dltcc = jc_tc_dltccService.getById(id);
		}
		jc_tc_dltcc.setberthID(berthID);
		jc_tc_dltcc.setname(name);
		jc_tc_dltcc.setroadname(roadname);
		jc_tc_dltcc.setarea(area);
		jc_tc_dltcc.setrateinfo(rateinfo);
		jc_tc_dltcc.setparkeTime(parkeTime);
		jc_tc_dltcc.setparknum(parknum);
		jc_tc_dltcc.setpicture(picture);
		jc_tc_dltcc.setnote(note);
		jc_tc_dltcc.setrateinfo(rateinfo);

        jc_tc_dltccService.save(jc_tc_dltcc);
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
		Jc_tc_dltcc jc_tc_dltcc = jc_tc_dltccService.getById(id);
		jc_tc_dltccService.delete(jc_tc_dltcc);
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
public String load(String name,String roadname,String rateinfo)
{
	JSONObject jsonObj = new JSONObject();
	try
	{
		HqlFilter hqlFilter = new HqlFilter();
if(name != null && name.equalsIgnoreCase("") == false && name.equalsIgnoreCase("null") == false)
{
	hqlFilter.addQryCond("name", HqlFilter.Operator.LIKE, "%"+name+"%");
}
if(roadname != null && roadname.equalsIgnoreCase("") == false && roadname.equalsIgnoreCase("null") == false)
{
	hqlFilter.addQryCond("roadname", HqlFilter.Operator.LIKE, "%"+roadname+"%");
}
if(rateinfo != null && rateinfo.equalsIgnoreCase("") == false && rateinfo.equalsIgnoreCase("null") == false)
{
	hqlFilter.addQryCond("rateinfo", HqlFilter.Operator.LIKE, "%"+rateinfo+"%");
}
if(rateinfo != null && rateinfo.equalsIgnoreCase("") == false && rateinfo.equalsIgnoreCase("null") == false)
{
	hqlFilter.addQryCond("rateinfo", HqlFilter.Operator.LIKE, "%"+rateinfo+"%");
}

        List<Jc_tc_dltcc> listObj = jc_tc_dltccService.findByFilter(hqlFilter);
        JSONArray jsonArr = new JSONArray();
        int iTotalCnt = 0;
		for(int i=0;i<listObj.size();i++)
		{
			Jc_tc_dltcc jc_tc_dltcc = listObj.get(i);
			JSONObject jsonTmp = new JSONObject();
			jsonTmp.put("id", jc_tc_dltcc.getId());
			jsonTmp.put("berthID",jc_tc_dltcc.getberthID());
			jsonTmp.put("name",jc_tc_dltcc.getname());
			jsonTmp.put("roadname",jc_tc_dltcc.getroadname());
			jsonTmp.put("area",jc_tc_dltcc.getarea());
			jsonTmp.put("rateinfo",jc_tc_dltcc.getrateinfo());
			jsonTmp.put("parkeTime",jc_tc_dltcc.getparkeTime());
			jsonTmp.put("parknum",jc_tc_dltcc.getparknum());
			jsonTmp.put("picture",jc_tc_dltcc.getpicture());
			jsonTmp.put("note",jc_tc_dltcc.getnote());
			jsonTmp.put("rateinfo",jc_tc_dltcc.getrateinfo());

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
		Jc_tc_dltcc jc_tc_dltcc = jc_tc_dltccService.getById(id);
		if(jc_tc_dltcc != null)
		{
			jsonObj.put("berthID",jc_tc_dltcc.getberthID());
			jsonObj.put("name",jc_tc_dltcc.getname());
			jsonObj.put("roadname",jc_tc_dltcc.getroadname());
			jsonObj.put("area",jc_tc_dltcc.getarea());
			jsonObj.put("rateinfo",jc_tc_dltcc.getrateinfo());
			jsonObj.put("parkeTime",jc_tc_dltcc.getparkeTime());
			jsonObj.put("parknum",jc_tc_dltcc.getparknum());
			jsonObj.put("picture",jc_tc_dltcc.getpicture());
			jsonObj.put("note",jc_tc_dltcc.getnote());
			jsonObj.put("rateinfo",jc_tc_dltcc.getrateinfo());

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
