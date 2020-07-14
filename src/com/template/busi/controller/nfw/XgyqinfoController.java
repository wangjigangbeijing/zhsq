package com.template.busi.controller.nfw;
import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import com.template.model.nfw.Xgyqinfo;
import com.template.service.nfw.XgyqinfoService;
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
@RequestMapping("xgyqinfoController")
public class XgyqinfoController {
	private static Logger logger = Logger.getLogger(XgyqinfoController.class);
	@Autowired
	private  HttpServletRequest request;
	@Autowired
	private XgyqinfoService xgyqinfoService;
@RequestMapping(value="addOrUpdate",method = RequestMethod.POST,produces="text/html;charset=UTF-8")
@ResponseBody
public String addOrUpdate(String id,String name,String mobile,String address,String quezhen,String qzdate,String qznote,String yisi,String mijie,String mjnote,String glstartdate,String glenddate,String note,String hsjc,String hsjcdate,String hsjcjigou,String hsjcjieguo)
{
	JSONObject jsonObj = new JSONObject();
	try
	{
		Xgyqinfo xgyqinfo;
		if(id==null || id.equalsIgnoreCase(""))
		{
			xgyqinfo = new Xgyqinfo();
			xgyqinfo.setId(Utility.getUniStr());
		}
		else
		{
			xgyqinfo = xgyqinfoService.getById(id);
		}
		xgyqinfo.setname(name);
		xgyqinfo.setmobile(mobile);
		xgyqinfo.setaddress(address);
		xgyqinfo.setquezhen(quezhen);
		if(qzdate != null && qzdate.equalsIgnoreCase("") == false)
			xgyqinfo.setqzdate(TimeUtil.parseDate(qzdate, "yyyy-MM-dd"));
		//xgyqinfo.setqznote(qznote);
		xgyqinfo.setyisi(yisi);
		xgyqinfo.setmijie(mijie);
		//xgyqinfo.setmjnote(mjnote);
		if(glstartdate != null && glstartdate.equalsIgnoreCase("") == false)
			xgyqinfo.setglstartdate(TimeUtil.parseDate(glstartdate, "yyyy-MM-dd"));// HH:mm
		
		if(glenddate != null && glenddate.equalsIgnoreCase("") == false)
			xgyqinfo.setglenddate(TimeUtil.parseDate(glenddate, "yyyy-MM-dd"));// HH:mm
		//xgyqinfo.setnote(note);
		xgyqinfo.sethsjc(hsjc);
		if(hsjcdate != null && hsjcdate.equalsIgnoreCase("") == false)
			xgyqinfo.sethsjcdate(TimeUtil.parseDate(hsjcdate, "yyyy-MM-dd"));// HH:mm
		xgyqinfo.sethsjcjigou(hsjcjigou);
		xgyqinfo.sethsjcjieguo(hsjcjieguo);

        xgyqinfoService.save(xgyqinfo);
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
		Xgyqinfo xgyqinfo = xgyqinfoService.getById(id);
		xgyqinfoService.delete(xgyqinfo);
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
public String load(String name,String mobile,String address,String quezhen,String qzdate,String yisi,String mijie,String hsjc,String hsjcjieguo)
{
	JSONObject jsonObj = new JSONObject();
	try
	{
		HqlFilter hqlFilter = new HqlFilter();
if(name != null && name.equalsIgnoreCase("") == false && name.equalsIgnoreCase("null") == false)
{
	hqlFilter.addQryCond("name", HqlFilter.Operator.LIKE, "%"+name+"%");
}
if(mobile != null && mobile.equalsIgnoreCase("") == false && mobile.equalsIgnoreCase("null") == false)
{
	hqlFilter.addQryCond("mobile", HqlFilter.Operator.LIKE, "%"+mobile+"%");
}
if(address != null && address.equalsIgnoreCase("") == false && address.equalsIgnoreCase("null") == false)
{
	hqlFilter.addQryCond("address", HqlFilter.Operator.LIKE, "%"+address+"%");
}
if(quezhen != null && quezhen.equalsIgnoreCase("") == false && quezhen.equalsIgnoreCase("null") == false)
{
	hqlFilter.addQryCond("quezhen", HqlFilter.Operator.LIKE, "%"+quezhen+"%");
}
if(qzdate != null && qzdate.equalsIgnoreCase("") == false && qzdate.equalsIgnoreCase("null") == false)
{
	hqlFilter.addQryCond("qzdate", HqlFilter.Operator.LIKE, "%"+qzdate+"%");
}
if(yisi != null && yisi.equalsIgnoreCase("") == false && yisi.equalsIgnoreCase("null") == false)
{
	hqlFilter.addQryCond("yisi", HqlFilter.Operator.LIKE, "%"+yisi+"%");
}
if(mijie != null && mijie.equalsIgnoreCase("") == false && mijie.equalsIgnoreCase("null") == false)
{
	hqlFilter.addQryCond("mijie", HqlFilter.Operator.LIKE, "%"+mijie+"%");
}
if(hsjc != null && hsjc.equalsIgnoreCase("") == false && hsjc.equalsIgnoreCase("null") == false)
{
	hqlFilter.addQryCond("hsjc", HqlFilter.Operator.LIKE, "%"+hsjc+"%");
}
if(hsjcjieguo != null && hsjcjieguo.equalsIgnoreCase("") == false && hsjcjieguo.equalsIgnoreCase("null") == false)
{
	hqlFilter.addQryCond("hsjcjieguo", HqlFilter.Operator.LIKE, "%"+hsjcjieguo+"%");
}

        List<Xgyqinfo> listObj = xgyqinfoService.findByFilter(hqlFilter);
        JSONArray jsonArr = new JSONArray();
        int iTotalCnt = 0;
		for(int i=0;i<listObj.size();i++)
		{
			Xgyqinfo xgyqinfo = listObj.get(i);
			JSONObject jsonTmp = new JSONObject();
			jsonTmp.put("id", xgyqinfo.getId());
			jsonTmp.put("name",xgyqinfo.getname());
			jsonTmp.put("mobile",xgyqinfo.getmobile());
			jsonTmp.put("address",xgyqinfo.getaddress());
			jsonTmp.put("quezhen",xgyqinfo.getquezhen());
			if(xgyqinfo.getqzdate() != null)
				jsonTmp.put("qzdate",TimeUtil.formatDate(xgyqinfo.getqzdate(),"yyyy-MM-dd"));
			else
				jsonTmp.put("qzdate","-");
			//jsonTmp.put("qznote",xgyqinfo.getqznote());
			jsonTmp.put("yisi",xgyqinfo.getyisi());
			jsonTmp.put("mijie",xgyqinfo.getmijie());
			//jsonTmp.put("mjnote",xgyqinfo.getmjnote());
			if(xgyqinfo.getglstartdate() != null)
				jsonTmp.put("glstartdate",TimeUtil.formatDate(xgyqinfo.getglstartdate(),"yyyy-MM-dd HH:mm"));
			else
				jsonTmp.put("glstartdate","");
			if(xgyqinfo.getglenddate() != null)
				jsonTmp.put("glenddate",TimeUtil.formatDate(xgyqinfo.getglenddate(),"yyyy-MM-dd HH:mm"));
			else
				jsonTmp.put("glenddate","-");
			//jsonTmp.put("note",xgyqinfo.getnote());
			jsonTmp.put("hsjc",xgyqinfo.gethsjc());
			if(xgyqinfo.gethsjcdate() != null)
				jsonTmp.put("hsjcdate",TimeUtil.formatDate(xgyqinfo.gethsjcdate(),"yyyy-MM-dd HH:mm"));
			else
				jsonTmp.put("hsjcdate","-");
			jsonTmp.put("hsjcjigou",xgyqinfo.gethsjcjigou());
			jsonTmp.put("hsjcjieguo",xgyqinfo.gethsjcjieguo());

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
		Xgyqinfo xgyqinfo = xgyqinfoService.getById(id);
		if(xgyqinfo != null)
		{
			jsonObj.put("name",xgyqinfo.getname());
			jsonObj.put("mobile",xgyqinfo.getmobile());
			jsonObj.put("address",xgyqinfo.getaddress());
			jsonObj.put("quezhen",xgyqinfo.getquezhen());
			jsonObj.put("qzdate",TimeUtil.formatDate(xgyqinfo.getqzdate(),"yyyy-MM-dd"));
			//jsonObj.put("qznote",xgyqinfo.getqznote());
			jsonObj.put("yisi",xgyqinfo.getyisi());
			jsonObj.put("mijie",xgyqinfo.getmijie());
			//jsonObj.put("mjnote",xgyqinfo.getmjnote());
			jsonObj.put("glstartdate",TimeUtil.formatDate(xgyqinfo.getglstartdate(),"yyyy-MM-dd HH:mm"));
			jsonObj.put("glenddate",TimeUtil.formatDate(xgyqinfo.getglenddate(),"yyyy-MM-dd HH:mm"));
			//jsonObj.put("note",xgyqinfo.getnote());
			jsonObj.put("hsjc",xgyqinfo.gethsjc());
			jsonObj.put("hsjcdate",TimeUtil.formatDate(xgyqinfo.gethsjcdate(),"yyyy-MM-dd HH:mm"));
			jsonObj.put("hsjcjigou",xgyqinfo.gethsjcjigou());
			jsonObj.put("hsjcjieguo",xgyqinfo.gethsjcjieguo());

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
