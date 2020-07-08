package com.template.busi.controller.jcsqsj;
import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import com.template.model.jcsqsj.Culturefacilities;
import com.template.service.jcsqsj.CulturefacilitiesService;
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
@RequestMapping("culturefacilitiesController")
public class CulturefacilitiesController {
	private static Logger logger = Logger.getLogger(CulturefacilitiesController.class);
	@Autowired
	private  HttpServletRequest request;
	@Autowired
	private CulturefacilitiesService culturefacilitiesService;
@RequestMapping(value="addOrUpdate",method = RequestMethod.POST,produces="text/html;charset=UTF-8")
@ResponseBody
public String addOrUpdate(String id,String dateid,String name,String tpye,String category,String address,String purpose,String introduction,String managedepart,String contact,String contacttel,Integer longitude,Integer latitude,String status,String pictures,String note)
{
	JSONObject jsonObj = new JSONObject();
	try
	{
		Culturefacilities culturefacilities;
		if(id==null || id.equalsIgnoreCase(""))
		{
			culturefacilities = new Culturefacilities();
			culturefacilities.setId(Utility.getUniStr());
		}
		else
		{
			culturefacilities = culturefacilitiesService.getById(id);
		}
		culturefacilities.setdateid(dateid);
		culturefacilities.setname(name);
		culturefacilities.settpye(tpye);
		culturefacilities.setcategory(category);
		culturefacilities.setaddress(address);
		culturefacilities.setpurpose(purpose);
		culturefacilities.setintroduction(introduction);
		culturefacilities.setmanagedepart(managedepart);
		culturefacilities.setcontact(contact);
		culturefacilities.setcontacttel(contacttel);
		//culturefacilities.setlongitude(longitude);
		//culturefacilities.setlatitude(latitude);
		culturefacilities.setstatus(status);
		culturefacilities.setpictures(pictures);
		culturefacilities.setnote(note);

        culturefacilitiesService.save(culturefacilities);
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
		Culturefacilities culturefacilities = culturefacilitiesService.getById(id);
		culturefacilitiesService.delete(culturefacilities);
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
public String load(String name,String tpye,String category,String contact,String status)
{
	JSONObject jsonObj = new JSONObject();
	try
	{
		HqlFilter hqlFilter = new HqlFilter();
if(name != null && name.equalsIgnoreCase("") == false && name.equalsIgnoreCase("null") == false)
{
	hqlFilter.addQryCond("name", HqlFilter.Operator.LIKE, "%"+name+"%");
}
if(tpye != null && tpye.equalsIgnoreCase("") == false && tpye.equalsIgnoreCase("null") == false)
{
	hqlFilter.addQryCond("tpye", HqlFilter.Operator.LIKE, "%"+tpye+"%");
}
if(category != null && category.equalsIgnoreCase("") == false && category.equalsIgnoreCase("null") == false)
{
	hqlFilter.addQryCond("category", HqlFilter.Operator.EQ, category);
}
if(contact != null && contact.equalsIgnoreCase("") == false && contact.equalsIgnoreCase("null") == false)
{
	hqlFilter.addQryCond("contact", HqlFilter.Operator.LIKE, "%"+contact+"%");
}
if(status != null && status.equalsIgnoreCase("") == false && status.equalsIgnoreCase("null") == false)
{
	hqlFilter.addQryCond("status", HqlFilter.Operator.LIKE, "%"+status+"%");
}

        List<Culturefacilities> listObj = culturefacilitiesService.findByFilter(hqlFilter);
        JSONArray jsonArr = new JSONArray();
        int iTotalCnt = 0;
		for(int i=0;i<listObj.size();i++)
		{
			Culturefacilities culturefacilities = listObj.get(i);
			JSONObject jsonTmp = new JSONObject();
			jsonTmp.put("id", culturefacilities.getId());
			jsonTmp.put("dateid",culturefacilities.getdateid());
			jsonTmp.put("name",culturefacilities.getname());
			jsonTmp.put("tpye",culturefacilities.gettpye());
			jsonTmp.put("category",culturefacilities.getcategory());
			jsonTmp.put("address",culturefacilities.getaddress());
			jsonTmp.put("purpose",culturefacilities.getpurpose());
			jsonTmp.put("introduction",culturefacilities.getintroduction());
			jsonTmp.put("managedepart",culturefacilities.getmanagedepart());
			jsonTmp.put("contact",culturefacilities.getcontact());
			jsonTmp.put("contacttel",culturefacilities.getcontacttel());
			//jsonTmp.put("longitude",culturefacilities.getlongitude());
			//jsonTmp.put("latitude",culturefacilities.getlatitude());
			jsonTmp.put("status",culturefacilities.getstatus());
			jsonTmp.put("pictures",culturefacilities.getpictures());
			jsonTmp.put("note",culturefacilities.getnote());

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
		Culturefacilities culturefacilities = culturefacilitiesService.getById(id);
		if(culturefacilities != null)
		{
			jsonObj.put("dateid",culturefacilities.getdateid());
			jsonObj.put("name",culturefacilities.getname());
			jsonObj.put("tpye",culturefacilities.gettpye());
			jsonObj.put("category",culturefacilities.getcategory());
			jsonObj.put("address",culturefacilities.getaddress());
			jsonObj.put("purpose",culturefacilities.getpurpose());
			jsonObj.put("introduction",culturefacilities.getintroduction());
			jsonObj.put("managedepart",culturefacilities.getmanagedepart());
			jsonObj.put("contact",culturefacilities.getcontact());
			jsonObj.put("contacttel",culturefacilities.getcontacttel());
			//jsonObj.put("longitude",culturefacilities.getlongitude());
			//jsonObj.put("latitude",culturefacilities.getlatitude());
			jsonObj.put("status",culturefacilities.getstatus());
			jsonObj.put("pictures",culturefacilities.getpictures());
			jsonObj.put("note",culturefacilities.getnote());

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
