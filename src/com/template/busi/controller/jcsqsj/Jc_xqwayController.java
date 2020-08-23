package com.template.busi.controller.jcsqsj;
import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import com.template.model.jcsqsj.Jc_xqway;
import com.template.service.jcsqsj.Jc_xqwayService;
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
@RequestMapping("jc_xqwayController")
public class Jc_xqwayController {
	private static Logger logger = Logger.getLogger(Jc_xqwayController.class);
	@Autowired
	private  HttpServletRequest request;
	@Autowired
	private Jc_xqwayService jc_xqwayService;
@RequestMapping(value="addOrUpdate",method = RequestMethod.POST,produces="text/html;charset=UTF-8")
@ResponseBody
public String addOrUpdate(String id,String dataid,String name,String type,String sfymj,String mjlx,String ssxq,String pictures,String note)
{
	JSONObject jsonObj = new JSONObject();
	try
	{
		Jc_xqway jc_xqway;
		if(id==null || id.equalsIgnoreCase(""))
		{
			jc_xqway = new Jc_xqway();
			jc_xqway.setId(Utility.getUniStr());
		}
		else
		{
			jc_xqway = jc_xqwayService.getById(id);
		}
		jc_xqway.setdataid(dataid);
		jc_xqway.setname(name);
		jc_xqway.settype(type);
		jc_xqway.setsfymj(sfymj);
		jc_xqway.setmjlx(mjlx);
		jc_xqway.setssxq(ssxq);
		jc_xqway.setpictures(pictures);
		jc_xqway.setnote(note);

		String userId = (String)request.getSession().getAttribute(ConstValue.SESSION_USER_ID);
		
		String organization = "";
		if(ConstValue.userToOrgMap.containsKey(userId))
			organization = ConstValue.userToOrgMap.get(userId);
		jc_xqway.setowner(organization);
		
        jc_xqwayService.save(jc_xqway);
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
		Jc_xqway jc_xqway = jc_xqwayService.getById(id);
		jc_xqwayService.delete(jc_xqway);
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
public String load(String name,String type,String sfymj,String mjlx,String ssxq)
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
if(sfymj != null && sfymj.equalsIgnoreCase("") == false && sfymj.equalsIgnoreCase("null") == false)
{
	hqlFilter.addQryCond("sfymj", HqlFilter.Operator.LIKE, "%"+sfymj+"%");
}
if(mjlx != null && mjlx.equalsIgnoreCase("") == false && mjlx.equalsIgnoreCase("null") == false)
{
	hqlFilter.addQryCond("mjlx", HqlFilter.Operator.LIKE, "%"+mjlx+"%");
}
if(ssxq != null && ssxq.equalsIgnoreCase("") == false && ssxq.equalsIgnoreCase("null") == false)
{
	hqlFilter.addQryCond("ssxq", HqlFilter.Operator.LIKE, "%"+ssxq+"%");
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

        List<Jc_xqway> listObj = jc_xqwayService.findByFilter(hqlFilter);
        JSONArray jsonArr = new JSONArray();
        int iTotalCnt = 0;
		for(int i=0;i<listObj.size();i++)
		{
			Jc_xqway jc_xqway = listObj.get(i);
			JSONObject jsonTmp = new JSONObject();
			jsonTmp.put("id", jc_xqway.getId());
			jsonTmp.put("dataid",jc_xqway.getdataid());
			jsonTmp.put("name",jc_xqway.getname());
			jsonTmp.put("type",jc_xqway.gettype());
			jsonTmp.put("sfymj",jc_xqway.getsfymj());
			jsonTmp.put("mjlx",jc_xqway.getmjlx());
			jsonTmp.put("ssxq",jc_xqway.getssxq());
			
			if(ConstValue.hmDicMap.containsKey(jc_xqway.getssxq()))
			{
				jsonTmp.put("ssxqname",ConstValue.hmDicMap.get(jc_xqway.getssxq()));
			}
			
			jsonTmp.put("pictures",jc_xqway.getpictures());
			jsonTmp.put("note",jc_xqway.getnote());

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
		Jc_xqway jc_xqway = jc_xqwayService.getById(id);
		if(jc_xqway != null)
		{
			jsonObj.put("dataid",jc_xqway.getdataid());
			jsonObj.put("name",jc_xqway.getname());
			jsonObj.put("type",jc_xqway.gettype());
			jsonObj.put("sfymj",jc_xqway.getsfymj());
			jsonObj.put("mjlx",jc_xqway.getmjlx());
			jsonObj.put("ssxq",jc_xqway.getssxq());
			jsonObj.put("pictures",jc_xqway.getpictures());
			jsonObj.put("note",jc_xqway.getnote());

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
