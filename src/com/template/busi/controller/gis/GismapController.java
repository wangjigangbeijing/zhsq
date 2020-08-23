package com.template.busi.controller.gis;
import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import com.template.model.gis.Gismap;
import com.template.model.gis.Gismaplayers;
import com.template.service.gis.GismapService;
import com.template.service.gis.GismaplayersService;
import com.template.util.HqlFilter;
import com.template.util.ConstValue;
import com.template.util.Utility;
import com.template.util.TimeUtil;
import java.util.List;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
@Controller
@RequestMapping("gismapController")
public class GismapController {
	private static Logger logger = Logger.getLogger(GismapController.class);
	@Autowired
	private  HttpServletRequest request;
	

	private static String geoserverURL = "";	
	@Value("#{propertiesReader['geoserver.url']}")
	public void setGEOServerUrl(String param) {
		geoserverURL= param;
	} 
	
	private static String geoserverUserName = "";
	@Value("#{propertiesReader['geoserver.username']}")
	public void setGEOUserName(String param) {
		geoserverUserName= param;
	} 
	
	private static String geoserverPassword = "";
	@Value("#{propertiesReader['geoserver.password']}")
	public void setGEOPassword(String param) {
		geoserverPassword= param;
	} 
	
	@Autowired
	private GismapService gismapService;
	
	@Autowired
	private GismaplayersService gismaplayersService;
	
@RequestMapping(value="addOrUpdate",method = RequestMethod.POST,produces="text/html;charset=UTF-8")
@ResponseBody
public String addOrUpdate(String id,String mapid,String mapname,String maptype,String mapstatus,String mapscope,String mapnote)
{
	JSONObject jsonObj = new JSONObject();
	try
	{
		Gismap gismap;
		if(id==null || id.equalsIgnoreCase(""))
		{
			gismap = new Gismap();
			gismap.setId(Utility.getUniStr());
		}
		else
		{
			gismap = gismapService.getById(id);
		}
		gismap.setmapid(mapid);
		gismap.setmapname(mapname);
		gismap.setmaptype(maptype);
		gismap.setmapstatus(mapstatus);
		gismap.setmapscope(mapscope);
		gismap.setmapnote(mapnote);

        gismapService.save(gismap);
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
		Gismap gismap = gismapService.getById(id);
		gismapService.delete(gismap);
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
public String load(String mapname,String maptype,String mapstatus,String mapscope)
{
	JSONObject jsonObj = new JSONObject();
	try
	{
		HqlFilter hqlFilter = new HqlFilter();
if(mapname != null && mapname.equalsIgnoreCase("") == false && mapname.equalsIgnoreCase("null") == false)
{
	hqlFilter.addQryCond("mapname", HqlFilter.Operator.LIKE, "%"+mapname+"%");
}
if(maptype != null && maptype.equalsIgnoreCase("") == false && maptype.equalsIgnoreCase("null") == false)
{
	hqlFilter.addQryCond("maptype", HqlFilter.Operator.LIKE, "%"+maptype+"%");
}
if(mapstatus != null && mapstatus.equalsIgnoreCase("") == false && mapstatus.equalsIgnoreCase("null") == false)
{
	hqlFilter.addQryCond("mapstatus", HqlFilter.Operator.LIKE, "%"+mapstatus+"%");
}
if(mapscope != null && mapscope.equalsIgnoreCase("") == false && mapscope.equalsIgnoreCase("null") == false)
{
	hqlFilter.addQryCond("mapscope", HqlFilter.Operator.LIKE, "%"+mapscope+"%");
}

        List<Gismap> listObj = gismapService.findByFilter(hqlFilter);
        JSONArray jsonArr = new JSONArray();
        int iTotalCnt = 0;
		for(int i=0;i<listObj.size();i++)
		{
			Gismap gismap = listObj.get(i);
			JSONObject jsonTmp = new JSONObject();
			jsonTmp.put("id", gismap.getId());
			jsonTmp.put("mapid",gismap.getmapid());
			jsonTmp.put("mapname",gismap.getmapname());
			jsonTmp.put("maptype",gismap.getmaptype());
			jsonTmp.put("mapstatus",gismap.getmapstatus());
			jsonTmp.put("mapscope",gismap.getmapscope());
			jsonTmp.put("mapnote",gismap.getmapnote());
			jsonTmp.put("mapicon",gismap.getmapicon());

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
		Gismap gismap = gismapService.getById(id);
		if(gismap != null)
		{
			jsonObj.put("mapid",gismap.getmapid());
			jsonObj.put("mapname",gismap.getmapname());
			jsonObj.put("maptype",gismap.getmaptype());
			jsonObj.put("mapstatus",gismap.getmapstatus());
			jsonObj.put("mapscope",gismap.getmapscope());
			jsonObj.put("mapnote",gismap.getmapnote());
			jsonObj.put("mapicon",gismap.getmapicon());
			jsonObj.put("mapurl",geoserverURL);

			HqlFilter hqlFilter = new HqlFilter();
			hqlFilter.addQryCond("mapid", HqlFilter.Operator.EQ, id);			
			//hqlFilter.addQryCond("defaultchecked", HqlFilter.Operator.EQ, "是");
			hqlFilter.setSort("order");
			

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
				jsonTmp.put("layertype",gismaplayers.getlayertype());
				jsonTmp.put("labelfields",gismaplayers.getlabelfields());
				jsonTmp.put("infofields",gismaplayers.getinfofields());
				jsonTmp.put("queryfields",gismaplayers.getqueryfields());
				jsonTmp.put("note",gismaplayers.getnote());				
				jsonTmp.put("order",gismaplayers.getorder());	
				jsonTmp.put("checked", gismaplayers.getdefaultchecked());
				jsonTmp.put("geoserver",geoserverURL);
				
	       		jsonArr.put(jsonTmp);
	        	iTotalCnt++;
			}
	        jsonObj.put("totalCount", iTotalCnt);
	        jsonObj.put("list", jsonArr);
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


	
	@RequestMapping(value="getMapByName",method = {RequestMethod.POST,RequestMethod.GET},produces="text/html;charset=UTF-8")
	@ResponseBody
	public String getMapByName(String name)
	{
		JSONObject jsonObj = new JSONObject();
		try
		{
			HqlFilter hqlFilter = new HqlFilter();
			
			hqlFilter.addQryCond("mapname", HqlFilter.Operator.EQ, name);
			
			Gismap gismap = gismapService.getByFilter(hqlFilter);
			
			if(gismap != null)
			{
				return get(gismap.getmapid());
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
