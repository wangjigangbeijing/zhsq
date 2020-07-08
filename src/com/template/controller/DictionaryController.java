package com.template.controller;
import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;

import com.google.gson.Gson;
import com.template.controller.DataController.DataAttr;
import com.template.model.SysDictionary;
import com.template.model.SysMenu;
import com.template.service.DictionaryService;
import com.template.service.MenuService;
import com.template.util.HqlFilter;
import com.template.util.ConstValue;
import com.template.util.Utility;
import com.template.util.TimeUtil;

import java.util.List;
import java.util.Map;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.xml.bind.annotation.XmlRootElement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
@Controller
@RequestMapping("dictionaryController")
public class DictionaryController {
	private static Logger logger = Logger.getLogger(DictionaryController.class);
	@Autowired
	private  HttpServletRequest request;
	@Autowired
	private DictionaryService dictionaryService;
	
	@RequestMapping(value="addOrUpdate",method = RequestMethod.POST)
	@ResponseBody
	public String addOrUpdate(String dic_enname,String dic_zhname,String dic_type,String dic_value)
	{
		JSONObject jsonObj = new JSONObject();
		try
		{
			SysDictionary sysDictionary;
			
			sysDictionary = dictionaryService.getById(dic_enname);
			
			if(sysDictionary == null)
			{
				sysDictionary = new SysDictionary();
			}
			
			sysDictionary.setdic_enname(dic_enname);
			sysDictionary.setdic_zhname(dic_zhname);
			sysDictionary.setdic_type(dic_type);
			sysDictionary.setdic_value(dic_value);
			
			dictionaryService.saveOrUpdate(sysDictionary);
			
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
	@RequestMapping(value="delete",method = RequestMethod.POST)
	@ResponseBody
	public String delete(String id)
	{
		logger.debug("delete "+id);
		JSONObject jsonObj = new JSONObject();
		try
		{
			SysDictionary first = dictionaryService.getById(id);
			dictionaryService.delete(first);
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
	public String load(String dic_zhname)
	{
		JSONObject jsonObj = new JSONObject();
		try
		{
			HqlFilter hqlFilter = new HqlFilter();
			
			if(dic_zhname != null && dic_zhname.equalsIgnoreCase("") == false)
			{
				hqlFilter.addQryCond("dic_zhname", HqlFilter.Operator.LIKE, "%"+dic_zhname+"%");
			}
			
	        List<SysDictionary> listObj = dictionaryService.findByFilter(hqlFilter);
	        JSONArray jsonArr = new JSONArray();
	        int iTotalCnt = 0;
			for(int i=0;i<listObj.size();i++)
			{
				SysDictionary menu = listObj.get(i);
				JSONObject jsonTmp = new JSONObject();
				jsonTmp.put("dic_enname",menu.getdic_enname());
				jsonTmp.put("dic_zhname",menu.getdic_zhname());
				jsonTmp.put("dic_type",menu.getdic_type());	
				jsonTmp.put("dic_value",menu.getdic_value());
				
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
			SysDictionary menu = dictionaryService.getById(id);
			if(menu != null)
			{
				jsonObj.put("dic_enname",menu.getdic_enname());
				jsonObj.put("dic_zhname",menu.getdic_zhname());
				jsonObj.put("dic_type",menu.getdic_type());	
				jsonObj.put("dic_value",menu.getdic_value());
	
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
	
	
	/*
	 	id:"",
		params:[
			{
				id:"ancd-asdfjk2-19389-1nklaf",
				enname:"ancd",
				value:"23asfdd"
			},
			{
				id:"ancd-asdfjk2-deqed-ddca",
				enname:"cbd",
				value:"caxee"
			}
		]	
	*/
	
	@RequestMapping(value="getDataOfDic",method = {RequestMethod.POST,RequestMethod.GET},produces="text/html;charset=UTF-8")
	@ResponseBody
	public String getDataOfDic(@RequestBody String dataPackage)
	{
		logger.info("getDataOfDic	"+dataPackage);
		JSONObject jsonObj = new JSONObject();
		try
		{
			Gson gson=new Gson();
			
			java.lang.reflect.Type type=new com.google.gson.reflect.TypeToken<Dic>(){}.getType();
			
			Dic dic = gson.fromJson(dataPackage,type);
			
			String id = dic.getId();
			
			List<Param> paramArr = dic.getParams();
			
			SysDictionary dictionary = dictionaryService.getById(id);
			
			JSONArray jsonArr = new JSONArray();
			
			if(dictionary != null)
			{
				String sql = dictionary.getdic_value();
				
				if(dictionary.getdic_type().equalsIgnoreCase("SQL"))
				{
					logger.debug(sql);
					for(int i=0;paramArr != null && i<paramArr.size();i++)
					{
						Param param = paramArr.get(i);
						
						String enName = param.getEnName();
						String paramid = param.getId();
						String value = param.getValue();
						
						sql = sql.replaceAll("\\{"+enName+"\\}", value);
						
						sql = sql.replaceAll("\\{"+paramid+"\\}", value);
						
						sql = sql.replaceAll("\\{"+(i+1)+"\\}", value);
					}
					
					logger.debug(sql);

					List<HashMap> listObj = dictionaryService.findBySql(sql);
					
					if(id.equalsIgnoreCase("ofunit") == false)
					{
						for(int i=0;i<listObj.size();i++)
						{
							HashMap hm = listObj.get(i);//这里的hashmap  第一个field是key   第二个field是value
							
							JSONObject jsonTmp = new JSONObject();
							
							if(hm.containsKey("id"))
							{
								jsonTmp.put("key", hm.get("id"));
								jsonTmp.put("value", hm.get("id"));
							}
							
							if(hm.containsKey("value"))
							{
								jsonTmp.put("value", hm.get("value"));
							}
							
							Iterator iter = hm.entrySet().iterator();
							for (int j=0;iter.hasNext() && j <=2;j++) {
								
								Map.Entry entry = (Map.Entry) iter.next();
								
								if(entry.getKey().equals("id") == false && entry.getKey().equals("value") == false)
								{
									jsonTmp.put((String)entry.getKey(), (String)entry.getValue());
								}
							}
							
							jsonArr.put(jsonTmp);
						}
					}
					else
					{
						if(listObj != null && listObj.size() != 0)
						{
							HashMap hm = listObj.get(0);
							
							Iterator iter = hm.entrySet().iterator();
							for (int j=0;iter.hasNext() && j <=2;j++) {
								
								Map.Entry entry = (Map.Entry) iter.next();
								
								String units = (String)entry.getValue();
								
								String [] unitArr = units.split(",");
								
								for(int k=0;k<unitArr.length;k++)
								{
									JSONObject jsonTmp = new JSONObject();
									jsonTmp.put("key", unitArr[k]);
									jsonTmp.put("value", unitArr[k]);
									
									jsonArr.put(jsonTmp);
								}
							}
						}
					}
				}
				
				jsonObj.put("success", true);				
				jsonObj.put("value", jsonArr);
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
	
	
	@XmlRootElement
	class Dic implements java.io.Serializable {
	
		public String id;
		
		public String getId()
		{
			return id;
		}
		public void setId(String id)
		{
			this.id = id;
		}
		
		public List<Param> params;
		
		public List<Param> getParams()
		{
			return params;
		}
		public void setParams(List<Param> params)
		{
			this.params = params;
		}
	}
	
	
	@XmlRootElement
	class Param implements java.io.Serializable {
	
		public String id;
		
		public String getId()
		{
			return id;
		}
		public void setId(String id)
		{
			this.id = id;
		}
		
		public String enname;
		
		public String getEnName()
		{
			return enname;
		}
		public void setEnName(String enname)
		{
			this.enname = enname;
		}
		
		public String value;
		
		public String getValue()
		{
			return value;
		}
		public void setValue(String value)
		{
			this.value = value;
		}
	}
	
	
	
	
	
	
	
}
