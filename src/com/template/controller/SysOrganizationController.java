package com.template.controller;
import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import com.template.model.SysOrganization;
import com.template.service.SysOrganizationService;
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
@RequestMapping("sysOrganizationController")
public class SysOrganizationController {
	private static Logger logger = Logger.getLogger(SysOrganizationController.class);
	@Autowired
	private  HttpServletRequest request;
	@Autowired
	private SysOrganizationService organizationService;
	@RequestMapping(value="addOrUpdate",method = RequestMethod.POST,produces="text/html;charset=UTF-8")
	@ResponseBody
	public String addOrUpdate(String id,String name,String code,String type,String border,Integer area,String address,
			String telphone,String secretary,String secretaryphone,String directorname,String directorphone,String note,String parent_id)
	{
		JSONObject jsonObj = new JSONObject();
		try
		{
			SysOrganization organization;
			if(id==null || id.equalsIgnoreCase(""))
			{
				organization = new SysOrganization();
				organization.setId(Utility.getUniStr());
			}
			else
			{
				organization = organizationService.getById(id);
			}
			organization.setname(name);
			organization.setcode(code);
			organization.settype(type);
			organization.setborder(border);
			organization.setarea(area);
			organization.setaddress(address);
			organization.settelphone(telphone);
			organization.setsecretary(secretary);
			organization.setsecretaryphone(secretaryphone);
			organization.setdirectorname(directorname);
			organization.setdirectorphone(directorphone);
			organization.setnote(note);
			organization.setparentid(parent_id);
	
	        organizationService.save(organization);
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
			SysOrganization organization = organizationService.getById(id);
			organizationService.delete(organization);
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
	public String load(String name)
	{
		JSONObject jsonObj = new JSONObject();
		try
		{
			HqlFilter hqlFilter = new HqlFilter();
	if(name != null && name.equalsIgnoreCase("") == false && name.equalsIgnoreCase("null") == false)
	{
		hqlFilter.addQryCond("name", HqlFilter.Operator.LIKE, "%"+name+"%");
	}
	
	        List<SysOrganization> listObj = organizationService.findByFilter(hqlFilter);
	        JSONArray jsonArr = new JSONArray();
	        int iTotalCnt = 0;
			for(int i=0;i<listObj.size();i++)
			{
				SysOrganization organization = listObj.get(i);
				JSONObject jsonTmp = new JSONObject();
				jsonTmp.put("id", organization.getId());
				jsonTmp.put("name",organization.getname());
				jsonTmp.put("code",organization.getcode());
				jsonTmp.put("type",organization.gettype());
				jsonTmp.put("border",organization.getborder());
				jsonTmp.put("area",organization.getarea());
				jsonTmp.put("address",organization.getaddress());
				jsonTmp.put("telphone",organization.gettelphone());
				jsonTmp.put("secretary",organization.getsecretary());
				jsonTmp.put("secretaryphone",organization.getsecretaryphone());
				jsonTmp.put("directorname",organization.getdirectorname());
				jsonTmp.put("directorphone",organization.getdirectorphone());
				jsonTmp.put("note",organization.getnote());
				jsonTmp.put("parentid",organization.getparentid());
				
				String parentId = organization.getparentid();
				
				String parentName = "";
				
				if(ConstValue.orgMap.containsKey(parentId))
					parentName = ConstValue.orgMap.get(parentId);
				
				jsonTmp.put("parentName",parentName);
				
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
			SysOrganization organization = organizationService.getById(id);
			if(organization != null)
			{
				jsonObj.put("name",organization.getname());
				jsonObj.put("code",organization.getcode());
				jsonObj.put("type",organization.gettype());
				jsonObj.put("border",organization.getborder());
				jsonObj.put("area",organization.getarea());
				jsonObj.put("address",organization.getaddress());
				jsonObj.put("telphone",organization.gettelphone());
				jsonObj.put("secretary",organization.getsecretary());
				jsonObj.put("secretaryphone",organization.getsecretaryphone());
				jsonObj.put("directorname",organization.getdirectorname());
				jsonObj.put("directorphone",organization.getdirectorphone());
				jsonObj.put("note",organization.getnote());
	
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
