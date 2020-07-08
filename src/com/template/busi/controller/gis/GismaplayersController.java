package com.template.busi.controller.gis;
import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import com.template.model.gis.Gismaplayers;
import com.template.service.gis.GismaplayersService;
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
@RequestMapping("gismaplayersController")
public class GismaplayersController {
	private static Logger logger = Logger.getLogger(GismaplayersController.class);
	@Autowired
	private  HttpServletRequest request;
	@Autowired
	private GismaplayersService gismaplayersService;
@RequestMapping(value="addOrUpdate",method = RequestMethod.POST,produces="text/html;charset=UTF-8")
@ResponseBody
public String addOrUpdate(String id,String layerid,String mapid,String layersource,String layerstyle,String labelfields,String infofields,String queryfields,String note)
{
	JSONObject jsonObj = new JSONObject();
	try
	{
		Gismaplayers gismaplayers;
		if(id==null || id.equalsIgnoreCase(""))
		{
			gismaplayers = new Gismaplayers();
			gismaplayers.setId(Utility.getUniStr());
		}
		else
		{
			gismaplayers = gismaplayersService.getById(id);
		}
		gismaplayers.setlayerid(layerid);
		gismaplayers.setmapid(mapid);
		gismaplayers.setlayersource(layersource);
		gismaplayers.setlayerstyle(layerstyle);
		gismaplayers.setlabelfields(labelfields);
		gismaplayers.setinfofields(infofields);
		gismaplayers.setqueryfields(queryfields);
		gismaplayers.setnote(note);

        gismaplayersService.save(gismaplayers);
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
		Gismaplayers gismaplayers = gismaplayersService.getById(id);
		gismaplayersService.delete(gismaplayers);
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
public String load(String layersource)
{
	JSONObject jsonObj = new JSONObject();
	try
	{
		HqlFilter hqlFilter = new HqlFilter();
if(layersource != null && layersource.equalsIgnoreCase("") == false && layersource.equalsIgnoreCase("null") == false)
{
	hqlFilter.addQryCond("layersource", HqlFilter.Operator.LIKE, "%"+layersource+"%");
}

        List<Gismaplayers> listObj = gismaplayersService.findByFilter(hqlFilter);
        JSONArray jsonArr = new JSONArray();
        int iTotalCnt = 0;
		for(int i=0;i<listObj.size();i++)
		{
			Gismaplayers gismaplayers = listObj.get(i);
			JSONObject jsonTmp = new JSONObject();
			jsonTmp.put("id", gismaplayers.getId());
			jsonTmp.put("layerid",gismaplayers.getlayerid());
			jsonTmp.put("mapid",gismaplayers.getmapid());
			jsonTmp.put("layersource",gismaplayers.getlayersource());
			jsonTmp.put("layerstyle",gismaplayers.getlayerstyle());
			jsonTmp.put("labelfields",gismaplayers.getlabelfields());
			jsonTmp.put("infofields",gismaplayers.getinfofields());
			jsonTmp.put("queryfields",gismaplayers.getqueryfields());
			jsonTmp.put("note",gismaplayers.getnote());

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
		Gismaplayers gismaplayers = gismaplayersService.getById(id);
		if(gismaplayers != null)
		{
			jsonObj.put("layerid",gismaplayers.getlayerid());
			jsonObj.put("mapid",gismaplayers.getmapid());
			jsonObj.put("layersource",gismaplayers.getlayersource());
			jsonObj.put("layerstyle",gismaplayers.getlayerstyle());
			jsonObj.put("labelfields",gismaplayers.getlabelfields());
			jsonObj.put("infofields",gismaplayers.getinfofields());
			jsonObj.put("queryfields",gismaplayers.getqueryfields());
			jsonObj.put("note",gismaplayers.getnote());

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
