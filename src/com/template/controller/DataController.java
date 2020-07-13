package com.template.controller;


import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.w3c.dom.Document;  
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;  
import org.w3c.dom.NodeList;  

import javax.servlet.http.HttpServletRequest;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.google.gson.Gson;
import com.template.model.SysTable;
import com.template.model.SysTableAttribute;
import com.template.model.gis.Gismaplayers;
import com.template.service.TableAttributeService;
import com.template.service.TableService;
import com.template.service.UserService;
import com.template.service.gis.GismaplayersService;
import com.template.util.ConstValue;
import com.template.util.HqlFilter;
import com.template.util.Utility;

@Controller
@RequestMapping(ConstValue.DATA_CONTROLLER)
public class DataController {
	
	private static Logger logger = Logger.getLogger(DataController.class);
	
	private static DecimalFormat m_dataFormat = new DecimalFormat();	
	static
	{
		m_dataFormat.setDecimalSeparatorAlwaysShown(false);
		DecimalFormatSymbols decformat = new DecimalFormatSymbols();
		decformat.setDecimalSeparator('.');
		decformat.setGroupingSeparator(',');
		m_dataFormat.setDecimalFormatSymbols(decformat);	
		m_dataFormat.setMaximumFractionDigits(2);
	}
	
	@Autowired
	private  HttpServletRequest request;
	
	@Autowired
	private TableService tableService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private TableAttributeService layerAttributeService;
	
	@Autowired
	private GismaplayersService gismaplayersService;
	
	@Value("#{propertiesReader['tmpDir']}")
	private String tmpdir = "";
	
	@RequestMapping(value=ConstValue.DATA_CONTROLLER_LOAD_DATA_OF_TABLE,method = {RequestMethod.GET,RequestMethod.POST},produces="text/html;charset=UTF-8")
    @ResponseBody
	public String loadDataOfTable(String tableId,String queryStr)
	{
		logger.info("loadDataOfTable "+tableId+"	"+queryStr);
		
    	JSONObject jsonObj = new JSONObject();
    	
		try
		{	
			if(queryStr != null && queryStr.endsWith("AND"))
				queryStr = queryStr.substring(0, queryStr.length() - 3);
			
			SysTable layer = tableService.getById(tableId);
			jsonObj.put("id", layer.getId());
			jsonObj.put("enName", layer.getTableENName());
			
			HqlFilter hqlFilter = new HqlFilter();
			
			if(tableId != null && tableId.equalsIgnoreCase("") == false && tableId.equalsIgnoreCase("null") == false)
			{
				hqlFilter.addQryCond("tableId", HqlFilter.Operator.EQ, tableId);
			}
			
			List<SysTableAttribute> layerAttrInDB = layerAttributeService.findByFilter(hqlFilter);
			
			HashMap<String,String> hmFiledNameToFieldType =  new HashMap<String,String>();
			String sFieldNames = "id,";
			for(int i=0;i<layerAttrInDB.size();i++)
			{
				SysTableAttribute sla = layerAttrInDB.get(i);
				
				sFieldNames += sla.getENName()+",";
				
				hmFiledNameToFieldType.put(sla.getENName(), sla.getDBType());
			}
			
			if(sFieldNames.endsWith(","))
				sFieldNames = sFieldNames.substring(0,sFieldNames.length() - 1);
			
			String sTableName = layer.getTableENName();			
			
			String sSql = "SELECT "+sFieldNames+" FROM "+sTableName;
			
			if(queryStr != null && queryStr.equalsIgnoreCase("") == false)
				sSql += " WHERE "+queryStr;
			
			logger.debug(sSql);
			
			JSONArray jsonArr = new JSONArray();
			
			List<HashMap> listObj = tableService.findBySql(sSql);
			
			for(int i=0;i<listObj.size();i++)
			{
				HashMap hm = listObj.get(i);
				
				JSONObject jsonRec = new JSONObject();
				
				jsonRec.put("id", hm.get("id"));
				
				for(int j=0;j<layerAttrInDB.size();j++)
				{
					SysTableAttribute sla = layerAttrInDB.get(j);
					
					if(hm.containsKey(sla.getENName()))
					{
						Object objValue = hm.get(sla.getENName().toLowerCase());						
						jsonRec.put(sla.getENName(), objValue);
					}
					else
					{
						//jsonRec.put(sla.getENName(), "");
						logger.error("Not able to find value of field "+sla.getENName());
					}
				}
				
				jsonArr.put(jsonRec);
			}
			
	        jsonObj.put("dataList", jsonArr);
	        
	        jsonObj.put("success", true);
		}
		catch(Exception e)
		{
			logger.error(e.getMessage(),e);
			jsonObj.put("success", false);
			jsonObj.put("errMsg", "获取数据失败");
		}
		finally
		{
			
		}		

        return jsonObj.toString();
    }
	
	/*
	{
	 	tableId:'e828252d-f3ac-4551-a4be-a7f9c841635e',
	 	dataId:'',
	 	point:'',
	 	data:
	 	[	
	 		{
	 			attrId:'707c0256-fecf-4279-8f12-77748574e3cc',
	 			attrValue:'取值'
	 		},
	 		{
	 			attrId:'',
	 			attrValue:''
	 		}
	 	],
	 	geoPackage:''
	} 
	 
	 @Desc
	 		如果dataId为空,则为新增操作
	 */
	@RequestMapping(value="webaddorupdate",method = RequestMethod.POST,produces="text/html;charset=UTF-8")
    @ResponseBody
    
	public String webaddOrUpdateData(MultipartHttpServletRequest multipartRequest)
	{
		logger.debug("addOrUpdateData ");
    	JSONObject jsonObj = new JSONObject();
    	
		try
		{
			Map paraMap = multipartRequest.getParameterMap();
			
			String sDataId = ((String[])paraMap.get("dataId"))[0];
			String sTableId = ((String[])paraMap.get("tableId"))[0];
			
			String sData = ((String[])paraMap.get("data"))[0];
			
			logger.debug(sData);
			
			Gson gson=new Gson();
			java.lang.reflect.Type type=new com.google.gson.reflect.TypeToken<List<DataAttr>>(){}.getType();
			List<DataAttr> dataArr = gson.fromJson(sData,type);
			
			/*for (Iterator it = multipartRequest.getFileNames(); it.hasNext();) 
			{
		        String componentName = (String) it.next();
		        MultipartFile file = multipartRequest.getFile(componentName);
		        if (file.getOriginalFilename().length() > 0) 
		        {
		            String fileName = Utility.getUniStr()+"_"+file.getOriginalFilename();  

		            try
		            {
		                boolean bRet = saveFileFromInputStream(file.getInputStream(), tmpdir, fileName);  
		                
		            } catch (Exception e) {  
		                logger.error(e.getMessage(),e);
		                jsonObj.put("success", false);
		    			return jsonObj.toString();
		            }
		        }
		    }*/

			SysTable sysTable = tableService.getById(sTableId);
			
			if(sysTable == null)
			{
				logger.error("can not find layer by layerId:"+sTableId);
				jsonObj.put("success", false);
				jsonObj.put("errMsg", "can not find layer by layerId:"+sTableId);
				
				return jsonObj.toString();
			}
			
			String sTblName = sysTable.getTableENName();
			
			HqlFilter hqlFilter = new HqlFilter();
			
			if(sTableId != null && sTableId.equalsIgnoreCase("") == false && sTableId.equalsIgnoreCase("null") == false)
			{
				hqlFilter.addQryCond("tableId", HqlFilter.Operator.EQ, sTableId);
			}
			
			List<SysTableAttribute> layerAttr = layerAttributeService.findByFilter(hqlFilter);
			if(layerAttr.size() == 0)
			{
				logger.error("can not find layer attribute by layerId:"+sTableId);
				jsonObj.put("success", false);
				jsonObj.put("errMsg", "can not find layer attribute by layerId:"+sTableId);
				
				return jsonObj.toString();
			}
			
			if(sDataId == null || sDataId.equalsIgnoreCase(""))//插入操作
			{	
				sDataId = Utility.getUniStr();
				
				String sSql = "INSERT INTO "+sTblName+" ";
				
				String sCols = "id,";
				String sVals = "'"+Utility.getUniStr()+"',";
				
				for(int i=0;i<layerAttr.size();i++)
				{
					SysTableAttribute sla = layerAttr.get(i);
					
					String sDBType = sla.getDBType();//数据库类型
					String sAttrName = sla.getENName();//字段名称
					String sAttrId = sla.getId();
					
					for(int j=0;j<dataArr.size();j++)
					{
						String sId = dataArr.get(j).getAttrId();
						
						if(sAttrId.equalsIgnoreCase(sId))
						{
							String sVal = dataArr.get(j).getAttrValue();
							
							if(sVal != null && sVal.equalsIgnoreCase("") == false)
							{
								sCols += sAttrName+",";
								
								if(sDBType.equalsIgnoreCase(ConstValue.DB_TYPE_NUMBER))
								{
									sVals += sVal+",";
								}
								/*else if(sDBType.equalsIgnoreCase(ConstValue.DB_TYPE_STRING))
								{
									sVals += "'"+sVal+"',";
								}*/
								else
								{
									sVals += "'"+sVal+"',";
								}
							}							
							break;
						}
					}
				}
				
				if(sCols != null && sCols.endsWith(","))
					sCols = sCols.substring(0,sCols.length() - 1);
				
				if(sVals != null && sVals.endsWith(","))
					sVals = sVals.substring(0,sVals.length() - 1);
				
				sSql += "("+sCols+") VALUES ("+sVals+")";
				
				logger.debug(sSql);
				
				tableService.executeSql(sSql);
				
				jsonObj.put("success", true);
				jsonObj.put("dataId", sDataId);
			}
			else//更新操作
			{
				String sSql = "UPDATE "+sTblName+" SET ";
				
				for(int i=0;i<layerAttr.size();i++)
				{
					SysTableAttribute sla = layerAttr.get(i);
					
					String sDBType = sla.getDBType();//数据库类型
					String sAttrName = sla.getENName();//字段名称
					String sAttrId = sla.getId();
					
					for(int j=0;j<dataArr.size();j++)
					{
						String sId = dataArr.get(j).getAttrId();
						
						if(sAttrId.equalsIgnoreCase(sId))
						{
							String sVal = dataArr.get(j).getAttrValue();
							
							//if(sVal != null && sVal.equalsIgnoreCase("") == false)
							//{
								sSql += sAttrName+" = ";
								
								if(sDBType.equalsIgnoreCase(ConstValue.DB_TYPE_NUMBER))
								{
									sSql += sVal+",";
								}
								/*else if(sDBType.equalsIgnoreCase(ConstValue.DB_TYPE_STRING))
								{
									sVals += "'"+sVal+"',";
								}*/
								else
								{
									sSql += "'"+sVal+"',";
								}
							//}
							
							break;
						}
					}
				}
				
				if(sSql != null && sSql.endsWith(","))
					sSql = sSql.substring(0,sSql.length() - 1);

				sSql += " WHERE id = '"+sDataId+"'";
				
				logger.debug(sSql);
				
				tableService.executeSql(sSql);
			}
		}
		catch(Exception e)
		{
			logger.error(e.getMessage(),e);
			jsonObj.put("success", false);
			jsonObj.put("errMsg", e.getMessage());
		}
        return jsonObj.toString();
    }	
	
	
	
	@RequestMapping(value=ConstValue.DATA_CONTROLLER_ADD_OR_UPDATE_DATA,method = RequestMethod.POST,produces="text/html;charset=UTF-8")
    @ResponseBody    
	public String addOrUpdateData(@RequestBody String dataPackage)
	{
		logger.debug("addOrUpdateData \n"+dataPackage);
    	JSONObject jsonObj = new JSONObject();
    	
		try
		{
			/*Map paraMap = multipartRequest.getParameterMap();
			
			String sDataId = ((String[])paraMap.get("dataId"))[0];
			String sTableId = ((String[])paraMap.get("tableId"))[0];			
			String sData = ((String[])paraMap.get("data"))[0];
			
			logger.debug(sData);
			*/
			Gson gson=new Gson();
			
			java.lang.reflect.Type type=new com.google.gson.reflect.TypeToken<Data>(){}.getType();
			
			Data data = gson.fromJson(dataPackage,type);
			
			String sDataId = data.getDataId();
			String sTableId = data.getTableId();
			String sPoint = data.getPoint();
			//String sData = ((String[])paraMap.get("data"))[0];
			
			List<DataAttr> dataArr = data.getData();
			
			/*for (Iterator it = multipartRequest.getFileNames(); it.hasNext();) 
			{
		        String componentName = (String) it.next();
		        MultipartFile file = multipartRequest.getFile(componentName);
		        if (file.getOriginalFilename().length() > 0) 
		        {
		            String fileName = Utility.getUniStr()+"_"+file.getOriginalFilename();  

		            try
		            {
		                boolean bRet = saveFileFromInputStream(file.getInputStream(), tmpdir, fileName);  
		                
		            } catch (Exception e) {  
		                logger.error(e.getMessage(),e);
		                jsonObj.put("success", false);
		    			return jsonObj.toString();
		            }
		        }
		    }*/

			SysTable sysTable = tableService.getById(sTableId);
			
			if(sysTable == null)
			{
				logger.error("can not find layer by layerId:"+sTableId);
				jsonObj.put("success", false);
				jsonObj.put("errMsg", "can not find layer by layerId:"+sTableId);
				
				return jsonObj.toString();
			}
			
			String sTblName = sysTable.getTableENName();
			String sGisType = sysTable.getGisType();
			
			HqlFilter hqlFilter = new HqlFilter();
			
			if(sTableId != null && sTableId.equalsIgnoreCase("") == false && sTableId.equalsIgnoreCase("null") == false)
			{
				hqlFilter.addQryCond("tableId", HqlFilter.Operator.EQ, sTableId);
			}
			
			List<SysTableAttribute> layerAttr = layerAttributeService.findByFilter(hqlFilter);
			if(layerAttr.size() == 0)
			{
				logger.error("can not find layer attribute by layerId:"+sTableId);
				jsonObj.put("success", false);
				jsonObj.put("errMsg", "can not find layer attribute by layerId:"+sTableId);
				
				return jsonObj.toString();
			}
			
			if(sDataId == null || sDataId.equalsIgnoreCase(""))//插入操作
			{	
				sDataId = Utility.getUniStr();
				
				String sSql = "INSERT INTO "+sTblName+" ";
				
				String sCols = "id,";
				String sVals = "'"+Utility.getUniStr()+"',";
				
				for(int i=0;i<layerAttr.size();i++)
				{
					SysTableAttribute sla = layerAttr.get(i);
					
					String sDBType = sla.getDBType();//数据库类型
					String sAttrName = sla.getENName();//字段名称
					String sAttrId = sla.getId();
					
					for(int j=0;j<dataArr.size();j++)
					{
						String sId = dataArr.get(j).getAttrId();
						
						if(sAttrId.equalsIgnoreCase(sId))
						{
							String sVal = dataArr.get(j).getAttrValue();
							
							if(sVal != null && sVal.equalsIgnoreCase("") == false)
							{
								sCols += sAttrName+",";
								
								if(sDBType.equalsIgnoreCase(ConstValue.DB_TYPE_NUMBER))
								{
									sVals += sVal+",";
								}
								/*else if(sDBType.equalsIgnoreCase(ConstValue.DB_TYPE_STRING))
								{
									sVals += "'"+sVal+"',";
								}*/
								else
								{
									sVals += "'"+sVal+"',";
								}
								
								/*if(sAttrName.equalsIgnoreCase("point"))
								{
								}*/
							}
							break;
						}
					}
				}
				

				if(sGisType.equalsIgnoreCase("点"))
				{
					sCols += "geom,point,";
					
					sVals += "ST_GeomFromText('POINT("+sPoint+")'),'"+sPoint+"',";
				}
				else if(sGisType.equalsIgnoreCase("线"))
				{
					sCols += "geom,point,";
					
					sVals += "ST_GeomFromText('LINESTRING("+sPoint+")'),'"+sPoint+"',";
				}
				else if(sGisType.equalsIgnoreCase("面"))
				{
					sCols += "geom,point,";
					
					sVals += "ST_GeomFromText('POLYGON(("+sPoint+"))'),'"+sPoint+"',";
				}
				
				if(sCols != null && sCols.endsWith(","))
					sCols = sCols.substring(0,sCols.length() - 1);
				
				if(sVals != null && sVals.endsWith(","))
					sVals = sVals.substring(0,sVals.length() - 1);
				
				sSql += "("+sCols+") VALUES ("+sVals+")";
				
				/*
				ST_GeomFromText('POINT(121.474 31.2329)'),
				ST_GeomFromText('LINESTRING(1 3, 12 5, 12 7)'),
				ST_GeomFromText('POLYGON((121.474 31.2345, 121.472 31.2333, 121.471 31.2315, 121.472 31.2302, 121.473 31.2304, 121.476 31.232, 121.474 31.2345))'), 
				*/
				
				logger.debug(sSql);
				
				tableService.executeSql(sSql);
				
				jsonObj.put("success", true);
				jsonObj.put("dataId", sDataId);
			}
			else//更新操作
			{
				String sSql = "UPDATE "+sTblName+" SET ";
				
				for(int i=0;i<layerAttr.size();i++)
				{
					SysTableAttribute sla = layerAttr.get(i);
					
					String sDBType = sla.getDBType();//数据库类型
					String sAttrName = sla.getENName();//字段名称
					String sAttrId = sla.getId();
					
					for(int j=0;j<dataArr.size();j++)
					{
						String sId = dataArr.get(j).getAttrId();
						
						if(sAttrId.equalsIgnoreCase(sId))
						{
							String sVal = dataArr.get(j).getAttrValue();
							
							//if(sVal != null && sVal.equalsIgnoreCase("") == false)
							//{
								sSql += sAttrName+" = ";
								
								if(sDBType.equalsIgnoreCase(ConstValue.DB_TYPE_NUMBER))
								{
									sSql += sVal+",";
								}
								/*else if(sDBType.equalsIgnoreCase(ConstValue.DB_TYPE_STRING))
								{
									sVals += "'"+sVal+"',";
								}*/
								else
								{
									sSql += "'"+sVal+"',";
								}
							//}
							
							break;
						}
					}
				}
				
				sSql += " POINT = '"+sPoint+"' ";
				
				if(sSql != null && sSql.endsWith(","))
					sSql = sSql.substring(0,sSql.length() - 1);

				sSql += " WHERE id = '"+sDataId+"'";
				
				logger.debug(sSql);
				
				tableService.executeSql(sSql);
			}
		}
		catch(Exception e)
		{
			logger.error(e.getMessage(),e);
			jsonObj.put("success", false);
			jsonObj.put("errMsg", e.getMessage());
		}
        return jsonObj.toString();
    }	
	/*
	public String addOrUpdateData(@RequestBody String dataPackage)
	{
		logger.info("addOrUpdateData ");
		logger.info(dataPackage);
    	JSONObject jsonObj = new JSONObject();
    	
		try
		{
			Gson gson=new Gson();
			java.lang.reflect.Type type=new com.google.gson.reflect.TypeToken<Data>(){}.getType();
			Data data = gson.fromJson(dataPackage,type);
			
			logger.info("data decoded successfully...");
			
			String sDataId = data.getDataId();
			String sTableId = data.getTableId();
			List<DataAttr> dataArr = data.getData();
			
			SysTable sysLayer = tableService.getById(sTableId);
			
			if(sysLayer == null)
			{
				logger.error("can not find layer by tableId:"+sTableId);
				jsonObj.put("success", false);
				jsonObj.put("errMsg", "can not find layer by tableId:"+sTableId);
				
				return jsonObj.toString();
			}
			
			String sTblName = sysLayer.getTableENName();	
			
			sTblName = sTblName.toLowerCase();
			
			
			HqlFilter hqlFilter = new HqlFilter();
			
			if(sTableId != null && sTableId.equalsIgnoreCase("") == false && sTableId.equalsIgnoreCase("null") == false)
			{
				hqlFilter.addQryCond("tableId", HqlFilter.Operator.EQ, sTableId);
			}
			
			List<SysTableAttribute> layerAttr = layerAttributeService.findByFilter(hqlFilter);
			if(layerAttr.size() == 0)
			{
				logger.error("can not find layer attribute by tableId:"+sTableId);
				jsonObj.put("success", false);
				jsonObj.put("errMsg", "can not find layer attribute by tableId:"+sTableId);
				
				return jsonObj.toString();
			}
			
			if(sDataId == null || sDataId.equalsIgnoreCase(""))//插入操作
			{
				String sSql = generateUpdateSql(layerAttr,dataArr,sTblName,sDataId);
				
				tableService.executeSql(sSql);
				logger.info(sSql);
				jsonObj.put("success", true);
				jsonObj.put("dataId", sDataId);
			}
			else//更新操作
			{
				String sSql = generateUpdateSql(layerAttr,dataArr,sTblName,sDataId);
				
				logger.info(sSql);
				
				tableService.executeSql(sSql);
				
				jsonObj.put("success", true);
			}
			
		}
		catch(Exception e)
		{
			logger.error(e.getMessage(),e);
			jsonObj.put("success", false);
			jsonObj.put("errMsg", e.getMessage());
		}
        return jsonObj.toString();
    }
	
	private String generateUpdateSql(List<SysTableAttribute> layerAttr,List<DataAttr> dataArr,String sTblName,String sDataId)
	{
		logger.error("not update created_at and crated_by yet....");
		
		String sSql = "UPDATE "+sTblName+" SET ";

		String sUserId  = request.getHeader("userId");
		
		for(int i=0;i<layerAttr.size();i++)
		{
			SysTableAttribute sla = layerAttr.get(i);
			
			String sDBType = sla.getDBType();//数据库类型
			String sAttrName = sla.getENName();//字段名称
			String sAttrId = sla.getId();
			
			for(int j=0;j<dataArr.size();j++)
			{
				String sId = dataArr.get(j).getAttrId();
				
				if(sAttrId.equalsIgnoreCase(sId))
				{
					String sVal = dataArr.get(j).getAttrValue();
					
					if(sVal != null && sVal.equalsIgnoreCase("null") == false)
					{
						sSql += sAttrName+" = ";
						
						if(sDBType.equalsIgnoreCase(ConstValue.DB_TYPE_NUMBER))
						{
							sSql += sVal+",";
						}
						else if(sDBType.equalsIgnoreCase(ConstValue.DB_TYPE_STRING))
						{
							sVals += "'"+sVal+"',";
						}
						else if((sDBType.equalsIgnoreCase(ConstValue.DB_TYPE_DATE) || sDBType.equalsIgnoreCase(ConstValue.DB_TYPE_TIME))
								&& (sVal == null || sVal.equalsIgnoreCase("")))
						{
							logger.error("time "+sAttrName+" is null or empty");
							continue;
						}
						else
						{
							sSql += "'"+sVal+"',";
						}
					}
					else
					{
						logger.error(sAttrName+" is "+" is null");
					}
					break;
				}
			}
		}
		
		if(sSql != null && sSql.endsWith(","))
			sSql = sSql.substring(0,sSql.length() - 1);

		sSql += " WHERE GID = "+sDataId;
		
		return sSql;
	}
	*/
	@RequestMapping(value=ConstValue.DATA_CONTROLLER_DELETE_DATA,method = RequestMethod.POST,produces="text/html;charset=UTF-8")
    @ResponseBody
	public String deleteData(String tableId,String dataId)
	{
		logger.info("deleteData tableId："+tableId+"	dataId:"+dataId);
    	JSONObject jsonObj = new JSONObject();
    	
		try
		{
			SysTable layer = tableService.getById(tableId);
			
			String sTableName = layer.getTableENName();	
			
			sTableName = sTableName.toLowerCase();
			
			String sSql = "DELETE FROM "+sTableName +" WHERE id = '"+dataId+"'";
			
			tableService.executeSql(sSql);
			
	        jsonObj.put("success", true);
		}
		catch(Exception e)
		{
			logger.error(e.getMessage(),e);
			jsonObj.put("success", false);
		}
        return jsonObj.toString();
    }
	
	@RequestMapping(value=ConstValue.DATA_CONTROLLER_GET_DATA,method = {RequestMethod.POST,RequestMethod.GET},produces="text/html;charset=UTF-8")
    @ResponseBody
	public String getData(String tableId,String dataId)
	{
		logger.info("getData tableId:"+tableId+"	dataId:"+dataId);
		
    	JSONObject jsonObj = new JSONObject();
    	
		try
		{
			SysTable layer = tableService.getById(tableId);
			
			HqlFilter hqlFilter = new HqlFilter();
			
			if(tableId != null && tableId.equalsIgnoreCase("") == false && tableId.equalsIgnoreCase("null") == false)
			{
				hqlFilter.addQryCond("tableId", HqlFilter.Operator.EQ, tableId);
			}
			
			
			List<SysTableAttribute> layerAttrInDB = layerAttributeService.findByFilter(hqlFilter);
			
			String sFields = "";
			
			for(int i=0;i<layerAttrInDB.size();i++)
			{
				SysTableAttribute sla = layerAttrInDB.get(i);
				
				sFields += sla.getENName()+",";
			}
			
			if(sFields.endsWith(","))
				sFields = sFields.substring(0,sFields.length() - 1);
			
			String sSql = "SELECT "+sFields+" FROM "+layer.getTableENName()+" WHERE ID = '"+dataId+"'";
			
			logger.info(sSql);
			
			List<HashMap> listObj = tableService.findBySql(sSql);
			
			jsonObj.put("dataId", dataId);
			
			JSONArray jsonArrDic = new JSONArray();
			
			for(int i=0;i<listObj.size();i++)
			{
				HashMap hm = listObj.get(i);
				
				for(int j=0;j<layerAttrInDB.size();j++)
				{
					SysTableAttribute sla = layerAttrInDB.get(j);
					
					if(hm.containsKey(sla.getENName()))
					{
						String enName = sla.getENName();
						
						Object objValue = hm.get(sla.getENName().toLowerCase());	
						
						jsonObj.put(sla.getENName(), objValue);
						
						if(ConstValue.dicList.contains(enName))
						{
							JSONObject jsonDict = new JSONObject();
							String sVal = objValue.toString();
							
							if(ConstValue.hmDicMap.containsKey(sVal))
							{
								//jsonObj.put(sla.getENName(), ConstValue.hmDicMap.get(sVal));
								jsonDict.put("key",sVal);
								jsonDict.put("value",ConstValue.hmDicMap.get(sVal));
								
								jsonArrDic.put(jsonDict);
							}
						}
					}
					else
					{
						//jsonRec.put(sla.getENName(), "");
						logger.error("Not able to find value of field "+sla.getENName());
					}
				}
				
				//jsonArr.put(jsonRec);
				jsonObj.put("success", true);
				jsonObj.put("dictionvalue", jsonArrDic);
			}
		}
		catch(Exception e)
		{
			logger.error(e.getMessage(),e);
			jsonObj.put("success", false);
			jsonObj.put("errMsg", e.getMessage());
		}
        return jsonObj.toString();
	}
	

	@RequestMapping(value="webwfs",method = {RequestMethod.GET,RequestMethod.POST},produces="text/html;charset=UTF-8")
    @ResponseBody
	public String webwfs(String layerId)
	{
		logger.info("begin webwfs "+layerId);

		String sResp = "";
		
		JSONObject jsonObj = new JSONObject();
		
		try
		{
			Gismaplayers mapLayer = gismaplayersService.getById(layerId);
			
			String infoFields = mapLayer.getinfofields();
			
			String sTableName = mapLayer.getlayersource();
			
			String [] infoFieldArr = infoFields.split(",");
			
			String sSql = "select id,st_astext(geom) as geom,"+infoFields+" from "+sTableName+" where 1 = 1 AND ";
			
			String sCond = "";
			
			/*for(int i=0;i<dataArr.size();i++)
			{
				String attrName = dataArr.get(i).getAttrName();
				String attrOperator = dataArr.get(i).getAttrOperator();
				String attrVal = dataArr.get(i).getAttrValue();
				
				logger.debug("'"+attrName + "' " +attrOperator +" '" +  attrVal+"'");
				
				if(attrVal == null || attrVal.equalsIgnoreCase("null") || attrVal.equalsIgnoreCase(""))
					continue;
				
				if(attrOperator.trim().equalsIgnoreCase("like"))
					attrVal = "%"+attrVal+"%";
				
				sCond += "\""+attrName + "\" " +attrOperator +" '" +  attrVal + "' AND ";
			}
			
			sSql += sCond;
			*/
			sSql = sSql.substring(0, sSql.length() - 5);
			
			logger.debug(sSql);
			
			List<HashMap> listObj = gismaplayersService.findBySql(sSql);
		
			String sLayerType = mapLayer.getlayertype();
			
			if(sLayerType.equalsIgnoreCase("point"))
			{
				sLayerType = "Point";
			}
			else if(sLayerType.equalsIgnoreCase("line"))
			{
				sLayerType = "MultiLineString";
			}
			else if(sLayerType.equalsIgnoreCase("polygon"))
			{
				sLayerType = "MultiPolygon";
			}
			
			JSONArray jsonFeatures = new JSONArray();
			
			for(int i=0;i<listObj.size();i++)
			{
				HashMap hm = listObj.get(i);
				
				String sGeom = (String)hm.get("geom");
				String gid = (String)hm.get("id");
				
				String info = "";
				for(int j=0;j<infoFieldArr.length;j++)
				{
					String field = infoFieldArr[j];
					
					String val = hm.get(field).toString();
					
					info += val + "\r\n";
				}
				
				sGeom = sGeom.replaceAll("\\ ", ",").replaceAll("POINT", "").replaceAll("POLYGON", "").replaceAll("MULTIPOLYGON", "").replaceAll("MULTILINESTRING", "").replaceAll(",,", " ").trim();
				
				JSONObject jsonTmp = new JSONObject();
				
				jsonTmp.put("info", info);
				jsonTmp.put("type", sLayerType);
				jsonTmp.put("id", sTableName+"."+gid);
				if(sLayerType.equalsIgnoreCase("Point"))
				{
					sGeom = sGeom.replaceAll("\\(", "").replaceAll("\\)", "");
					
					String [] coorArr = sGeom.split(",");
					
					jsonTmp.put("coordinates", coorArr);
				}
				else if(sLayerType.equalsIgnoreCase("MultiLineString"))
				{
					sGeom = sGeom.replaceAll("\\(", "").replaceAll("\\)", "");
					
					String [] coorArr = sGeom.split(",");
					
					String [][] aArr = new String[coorArr.length/2][2];
					
					for(int j=0;j<coorArr.length;j = j+2)
					{
						aArr[j/2][0] =  coorArr[j];
						aArr[j/2][1] =  coorArr[j+1];
					}
					
					//String [][][] lineArr = {{{"642702.61666556", "3506608.6968755"}, {"642579.60833186", "3506549.31354199"}, {"642592.3333319", "3506704.13437578"}}};
					
					String [][][] lineArr = {aArr};
					
					jsonTmp.put("coordinates", lineArr);
				}
				else if(sLayerType.equalsIgnoreCase("MultiPolygon"))
				{
					sGeom = sGeom.replaceAll("\\(", "").replaceAll("\\)", "");
					
					//sGeom = "116.30454,39.859639,116.20654,39.859239,116.30954,39.559339,116.30454,39.859639";//可以显示的例子，不能删除
					
					//sGeom = "116.31424437203337,39.88458832721425,116.31639416252204,39.88455745597841,116.31637404595477,39.88335655411623,"
					//		+ "116.31522471941156,39.88327628743202,116.31529579794925,39.8829161152562,116.31421084442128,39.882887301400395,"
					//		+ "116.31424437203337,39.88458832721425";
					
					String [] coorArr = sGeom.split(",");

					String [][] aArr = new String[coorArr.length/2][2];
					
					for(int j=0;j<coorArr.length;j = j+2)
					{
						aArr[j/2][0] =  coorArr[j];
						aArr[j/2][1] =  coorArr[j+1];
					}
					
					String [][][] lineArr = {aArr};
					
					jsonTmp.put("coordinates", lineArr);
				}
				
				jsonFeatures.put(jsonTmp);
				
			}
			jsonObj.put("features",jsonFeatures);
		}
		catch(Exception e)
		{
			logger.error(e.getMessage(),e);
			
			jsonObj.put("success", false);
			
			jsonObj.put("errMsg", "加载编辑数据失败");
			
			return jsonObj.toString();
		}
        return jsonObj.toString();
    }
	
	
	@XmlRootElement
	class Data implements java.io.Serializable {
		
		public String tableId;
		
		public String getTableId()
		{
			return tableId;
		}
		public void setTableId(String tableId)
		{
			this.tableId = tableId;
		}
		
		public String dataId;
		
		public String getDataId()
		{
			return dataId;
		}
		public void setDataId(String dataId)
		{
			this.dataId = dataId;
		}
		
		
		public String point;
		
		public String getPoint()
		{
			return point;
		}
		public void setPoint(String point)
		{
			this.point = point;
		}
		
		
		public List<DataAttr> data;
		
		public List<DataAttr> getData()
		{
			return data;
		}
		public void setData(List<DataAttr> data)
		{
			this.data = data;
		}
	}
	
	@XmlRootElement
	class DataAttr implements java.io.Serializable {
	
		public String attrId;
		
		public String getAttrId()
		{
			return attrId;
		}
		public void setAttrId(String attrId)
		{
			this.attrId = attrId;
		}
		
		public String attrName;
		
		public String getAttrName()
		{
			return attrName;
		}
		public void setAttrName(String attrName)
		{
			this.attrName = attrName;
		}
		
		public String attrValue;
		
		public String getAttrValue()
		{
			return attrValue;
		}
		public void setAttrValue(String attrValue)
		{
			this.attrValue = attrValue;
		}
	}
	
	
	
	

	private boolean saveFileFromInputStream(InputStream stream, String dirPath,  
	        String filename) throws IOException 
	{	
		File file = null;
		FileOutputStream fs = null;
		try
		{
			File dir = new File(dirPath);
			if(dir.exists() == false)
				dir.mkdir();
			
			file = new File(dirPath + "/" + filename);  
		    fs = new FileOutputStream(file);  
		    byte[] buffer = new byte[1024 * 1024];  
		    int bytesum = 0;  
		    int byteread = 0;  
		    while ((byteread = stream.read(buffer)) != -1) {  
		        bytesum += byteread;  
		        fs.write(buffer, 0, byteread);  
		        fs.flush();  
		    }  
		}
		catch(Exception e)
		{
			logger.error(e.getMessage(),e);
		}
		finally
		{
		    if(fs != null)fs.close();  
		    if(stream != null)stream.close();  
		}
	    
	    return true;  
	} 
}
