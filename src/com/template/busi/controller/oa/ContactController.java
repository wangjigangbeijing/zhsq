package com.template.busi.controller.oa;
import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import com.template.model.oa.Contact;
import com.template.service.oa.ContactService;
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
@RequestMapping("contactController")
public class ContactController {
	private static Logger logger = Logger.getLogger(ContactController.class);
	@Autowired
	private  HttpServletRequest request;
	@Autowired
	private ContactService contactService;
@RequestMapping(value="addOrUpdate",method = RequestMethod.POST,produces="text/html;charset=UTF-8")
@ResponseBody
public String addOrUpdate(String id,String contacttype,String name,String tel,String mobile,String unitid,String unitname,String job,
		Integer seq,String note,String authoritystaffid)
{
	JSONObject jsonObj = new JSONObject();
	try
	{
		String sOrgId = Utility.getInstance().getOrganization(request);
		
		Contact contact;
		if(id==null || id.equalsIgnoreCase(""))
		{
			contact = new Contact();
			contact.setId(Utility.getUniStr());
		}
		else
		{
			contact = contactService.getById(id);
		}
		contact.setcontacttype(contacttype);
		contact.setname(name);
		contact.settel(tel);
		contact.setmobile(mobile);
		contact.setunitid(unitid);
		contact.setunitname(unitname);
		contact.setjob(job);
		contact.setseq(seq);
		contact.setnote(note);
		contact.setauthoritystaffid(authoritystaffid);
		contact.setowner(sOrgId);

        contactService.save(contact);
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
		Contact contact = contactService.getById(id);
		contactService.delete(contact);
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
public String load(String contacttype,String name,String mobile,String unitname,String note)
{
	JSONObject jsonObj = new JSONObject();
	try
	{
		HqlFilter hqlFilter = new HqlFilter();
if(contacttype != null && contacttype.equalsIgnoreCase("") == false && contacttype.equalsIgnoreCase("null") == false)
{
	hqlFilter.addQryCond("contacttype", HqlFilter.Operator.LIKE, "%"+contacttype+"%");
}
if(name != null && name.equalsIgnoreCase("") == false && name.equalsIgnoreCase("null") == false)
{
	hqlFilter.addQryCond("name", HqlFilter.Operator.LIKE, "%"+name+"%");
}
if(mobile != null && mobile.equalsIgnoreCase("") == false && mobile.equalsIgnoreCase("null") == false)
{
	hqlFilter.addQryCond("mobile", HqlFilter.Operator.LIKE, "%"+mobile+"%");
}
if(unitname != null && unitname.equalsIgnoreCase("") == false && unitname.equalsIgnoreCase("null") == false)
{
	hqlFilter.addQryCond("unitname", HqlFilter.Operator.LIKE, "%"+unitname+"%");
}
if(note != null && note.equalsIgnoreCase("") == false && note.equalsIgnoreCase("null") == false)
{
	hqlFilter.addQryCond("note", HqlFilter.Operator.LIKE, "%"+note+"%");
}

ArrayList<String> alOrg = new ArrayList<String>(); 

String organization = Utility.getInstance().getOrganization(request);

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

        List<Contact> listObj = contactService.findByFilter(hqlFilter);
        JSONArray jsonArr = new JSONArray();
        int iTotalCnt = 0;
		for(int i=0;i<listObj.size();i++)
		{
			Contact contact = listObj.get(i);
			JSONObject jsonTmp = new JSONObject();
			jsonTmp.put("id", contact.getId());
			jsonTmp.put("contacttype",contact.getcontacttype());
			jsonTmp.put("name",contact.getname());
			jsonTmp.put("tel",contact.gettel());
			jsonTmp.put("mobile",contact.getmobile());
			jsonTmp.put("unitid",contact.getunitid());
			jsonTmp.put("unitname",contact.getunitname());
			jsonTmp.put("job",contact.getjob());
			jsonTmp.put("seq",contact.getseq());
			jsonTmp.put("note",contact.getnote());
			jsonTmp.put("authoritystaffid",contact.getauthoritystaffid());

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
		Contact contact = contactService.getById(id);
		if(contact != null)
		{
			jsonObj.put("contacttype",contact.getcontacttype());
			jsonObj.put("name",contact.getname());
			jsonObj.put("tel",contact.gettel());
			jsonObj.put("mobile",contact.getmobile());
			jsonObj.put("unitid",contact.getunitid());
			jsonObj.put("unitname",contact.getunitname());
			jsonObj.put("job",contact.getjob());
			jsonObj.put("seq",contact.getseq());
			jsonObj.put("note",contact.getnote());
			jsonObj.put("authoritystaffid",contact.getauthoritystaffid());

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
