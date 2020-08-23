package com.template.controller;

import it.geosolutions.geoserver.rest.GeoServerRESTManager;
import it.geosolutions.geoserver.rest.GeoServerRESTPublisher;
import it.geosolutions.geoserver.rest.GeoServerRESTReader;
import it.geosolutions.geoserver.rest.GeoServerRESTPublisher.UploadMethod;
import it.geosolutions.geoserver.rest.decoder.RESTDataStore;
import it.geosolutions.geoserver.rest.encoder.GSAbstractStoreEncoder;
import it.geosolutions.geoserver.rest.encoder.GSLayerEncoder;
import it.geosolutions.geoserver.rest.encoder.GSResourceEncoder.ProjectionPolicy;
import it.geosolutions.geoserver.rest.encoder.coverage.GSCoverageEncoderTest;
import it.geosolutions.geoserver.rest.encoder.datastore.GSOracleNGDatastoreEncoder;
import it.geosolutions.geoserver.rest.encoder.datastore.GSPostGISDatastoreEncoder;
import it.geosolutions.geoserver.rest.encoder.feature.GSFeatureTypeEncoder;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.httpclient.NameValuePair;
import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;












import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.Producer;
import com.google.gson.Gson;
import com.template.model.SysTable;
import com.template.model.SysTableAttribute;
import com.template.model.TableField;
import com.template.service.TableAttributeService;
import com.template.service.TableService;
import com.template.util.ConstValue;
import com.template.util.FileUtil;
import com.template.util.HqlFilter;
import com.template.util.Utility;

@Controller
@RequestMapping(ConstValue.TABLE_CONTROLLER)
@Transactional
@Service
public class TableController {
	
	private static Logger logger = Logger.getLogger(TableController.class);
	
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
	private TableAttributeService tableAttributeService;
	
	@Value("#{propertiesReader['tmpDir']}")
	private String tmpdir = "";
	
	@Value("#{propertiesReader['database']}")
	private String database = "";
	
	@Value("#{propertiesReader['geoserver.workspace']}")
	private String workspace = "";
	
	@Value("#{propertiesReader['geoserver.dbstoreName']}")
	private String dbStoreName = "";
	

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
	
	private GeoServerRESTPublisher publisher;
	
	
	public TableController()
	{
        try {
        	if(geoserverURL != null && geoserverURL.equalsIgnoreCase("") == false)
        	{        		
	        	URL URL = new URL(geoserverURL);
	        	GeoServerRESTManager manager = new GeoServerRESTManager(URL, geoserverUserName, geoserverPassword);
	        	publisher = manager.getPublisher();
	        	logger.info("GeoServer connection initialize successfully...");
        	}
        	else
        	{
        		logger.error("failed to get geoserver.url...");
        	}
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
	}
	
	@RequestMapping(value=ConstValue.TABLE_CONTROLLER_LOAD_TABLE,method = {RequestMethod.GET,RequestMethod.POST},produces="text/html;charset=UTF-8")
    @ResponseBody
	public String loadTable(String dataType,String tableName)
	{
		logger.info("loadTable ");
		
    	JSONObject jsonObj = new JSONObject();
    	
		try
		{
			HqlFilter hqlFilter = new HqlFilter();
			
			if(dataType != null && dataType.equalsIgnoreCase("") == false)
			{
				hqlFilter.addQryCond("data_type", HqlFilter.Operator.EQ, dataType);
			}
			
			if(tableName != null && tableName.equalsIgnoreCase("") == false)
			{
				hqlFilter.addQryCond("table_zhname", HqlFilter.Operator.LIKE, "%"+tableName+"%");
			}
			
	        List<SysTable> listObj = tableService.findByFilter(hqlFilter);
	        
	        JSONArray jsonArr = new JSONArray();
	        
	        int iTotalCnt = 0;
			for(int i=0;i<listObj.size();i++)
			{
				SysTable sysTable = listObj.get(i);
				
	        	JSONObject jsonTmp = new JSONObject();
	        	jsonTmp.put("id", sysTable.getId());
	        	jsonTmp.put("tableZHName", sysTable.getTableZHName());
	        	jsonTmp.put("status", sysTable.getId());
	        	jsonTmp.put("gisType", sysTable.getGisType());
	        	jsonTmp.put("dataType", sysTable.getDataType());
	        	jsonTmp.put("dataSubType", sysTable.getDataSubType());
	        	jsonTmp.put("seq", sysTable.getseq());
	        	jsonTmp.put("icon", sysTable.getIcon());
	        	
	        	String sSql = "SELECT COUNT(*) CNT FROM "+sysTable.getTableENName()+" WHERE 1 = 1 ";

				String organization = Utility.getInstance().getOrganization(request);
				if(organization != null && organization.equalsIgnoreCase("") == false)
				{
					String [] organizationArr = organization.split(",");
					
					String ownerCond = "";
					
					for(int j=0;j<organizationArr.length;j++)
					{
						if(ownerCond.length() == 0) {
							ownerCond = " OWNER LIKE '%"+organizationArr[j]+"%'";
						}
						else {
							ownerCond += " OR OWNER LIKE '%"+organizationArr[j]+"%'";
						}
					}
					
					if(ownerCond.length() > 0) {
						sSql += " AND ("+ownerCond+") ";
					}	
				}
				
	        	List<HashMap> listCnt = tableService.findBySql(sSql);
				
	        	if(listCnt.size() != 0)	        		
	        	{
	        		HashMap hm = listCnt.get(0);
	        		jsonTmp.put("cnt", hm.get("CNT"));	
	        	}
	        	else
	        	{
	        		jsonTmp.put("cnt", 0);
	        	}
	        	
	        	jsonArr.put(jsonTmp);
	        	
	        	iTotalCnt++;
			}
	        
	        jsonObj.put("totalCount", iTotalCnt);
	        jsonObj.put("tableList", jsonArr);
	        
	        jsonObj.put("success", true);
		}
		catch(Exception e)
		{
			logger.error(e.getMessage(),e);
			jsonObj.put("success", false);
			jsonObj.put("errMsg", "获取图层列表失败");
		}
		finally
		{
			
		}		

        return jsonObj.toString();
    }
	
	@RequestMapping(value=ConstValue.TABLE_CONTROLLER_ADD_OR_UPDATE_TABLE,method = RequestMethod.POST,produces="text/html;charset=UTF-8")
    @ResponseBody
	public String addOrUpdateTable(String tableId,String tableZHName,String tableType,String tableENName,String tableDescription,
			String seq,String tableAttribute,String dataType,String gisType)//MultipartHttpServletRequest multipartRequest)
	{
		logger.info("addOrUpdateTable");
		
		logger.info("tableId:"+tableId);
		logger.info("tableZHName:"+tableZHName);
		logger.info("tableType:"+tableType);
		logger.info("tableENName:"+tableENName);
		logger.info("tableDescription:"+tableDescription);
		logger.info("seq:"+seq);
		logger.info("tableAttribute:"+tableAttribute);
		logger.info("dataType:"+dataType);
		
    	JSONObject jsonObj = new JSONObject();
    	
		try
		{
			jsonObj.put("success", true);
			
			Gson gson=new Gson();
			
			java.lang.reflect.Type type=new com.google.gson.reflect.TypeToken<List<SysTableAttribute>>(){}.getType();
			List<SysTableAttribute> layerAttribute = gson.fromJson("["+tableAttribute+"]",type);
			
			//字段重复性判断			
			ArrayList<String> dedupList = new ArrayList<String>();
			for(int i=0;i<layerAttribute.size();i++)
			{
				SysTableAttribute stl = layerAttribute.get(i);
				
				if(dedupList.contains(stl.getENName()))
				{
					jsonObj.put("errMsg", "重复字段名称"+stl.getENName()+"存在");
					jsonObj.put("success", false);
					return jsonObj.toString();
				}
				
				dedupList.add(stl.getENName());				
			}
			
			SysTable table = new SysTable();
			
			if(tableId == null || tableId.equalsIgnoreCase(""))//如果是新建,需要判断图层英文名称是否已经存在
			{
				tableId = Utility.getUniStr();
				
				table.setId(tableId);
			}
			else
			{
				table = tableService.getById(tableId);
			}

			table.setTableDescription(tableDescription);
			table.setTableENName(tableENName);
			table.setTableZHName(tableZHName);
        	jsonObj.put("tableId", tableId);
        	
			tableService.saveOrUpdate(table);
			
			ArrayList<TableField> alTblField = getTableFieldsByTableName(tableENName,database);
			
			if(alTblField.size() == 0)
			{
				String sCreateTable = "CREATE TABLE "+tableENName + "(";//页面上保证至少有一个采集属性
				
				sCreateTable += "id varchar(64),";
				sCreateTable += "created_by varchar(64),";
				sCreateTable += "owner varchar(64),";
				sCreateTable += "point text,";
				sCreateTable += "created_at timestamp,";
				
				if(gisType != null && gisType.equalsIgnoreCase("点"))
				{
					sCreateTable += "geom geometry(PointZ),";
				}
				else if(gisType != null && gisType.equalsIgnoreCase("线"))
				{
					sCreateTable += "geom geometry(MultiLineString),";
				}
				else if(gisType != null && gisType.equalsIgnoreCase("面"))
				{
					sCreateTable += "geom geometry(MultiPolygon),";
				}
				
				//需要处理数据库表创建失败的情况
				HqlFilter hqlFilter = new HqlFilter();
				
				if(tableId != null && tableId.equalsIgnoreCase("") == false && tableId.equalsIgnoreCase("null") == false)
				{
					hqlFilter.addQryCond("tableId", HqlFilter.Operator.EQ, tableId);
				}
				
				List<SysTableAttribute> layerAttrInDB = tableAttributeService.findByFilter(hqlFilter);				
				
				for(int i=0;i<layerAttribute.size();i++)
				{
					SysTableAttribute layerAttr = layerAttribute.get(i);
					
					String sENName = layerAttr.getENName().trim();
					String sDBType = layerAttr.getDBType();//字符串  日期   时间  整数
					int iLength = layerAttr.getDBLength();
					
					if(iLength == 0)
						iLength = 16;
					
					if(sDBType != null && sDBType.equalsIgnoreCase(ConstValue.DB_TYPE_STRING))
					{
						sCreateTable += sENName + " varchar("+iLength+"),";
					}
					else if(sDBType != null && sDBType.equalsIgnoreCase(ConstValue.DB_TYPE_NUMBER))
					{
						sCreateTable += sENName + " integer,";
					}
					else if(sDBType != null && sDBType.equalsIgnoreCase(ConstValue.DB_TYPE_DATE))
					{
						sCreateTable += sENName + " date,";
					}
					else if(sDBType != null && sDBType.equalsIgnoreCase(ConstValue.DB_TYPE_TIME))
					{
						sCreateTable += sENName + " datetime,";
					}
					
					int j=0;
					SysTableAttribute slaInDB = null;
					for(;j<layerAttrInDB.size();j++)//目前只支持字段名称的比较
					{
						slaInDB = layerAttrInDB.get(j);
						
						if(slaInDB.getENName().equalsIgnoreCase(layerAttr.getENName()))
						{
							break;
						}
					}
					
					if(j == layerAttrInDB.size())
					{
						layerAttr.setId(Utility.getUniStr());
						layerAttr.setTableId(tableId);
						tableAttributeService.save(layerAttr);
					}
					else
					{
						//slaInDB.setAppDisplay(layerAttr.getAppDisplay());
						slaInDB.setDBType(layerAttr.getDBType());
						slaInDB.setDBLength(layerAttr.getDBLength());
						slaInDB.setSeq(layerAttr.getSeq());
						slaInDB.setSupportQuery(layerAttr.getSupportQuery());
						slaInDB.setValues(layerAttr.getValues());
						slaInDB.setENName(layerAttr.getENName());
						slaInDB.setZHName(layerAttr.getZHName());
						tableAttributeService.save(slaInDB);
					}
					//tableAttributeService.save(layerAttr);
				}
				
				sCreateTable = sCreateTable.substring(0,sCreateTable.length() - 1);
				
				sCreateTable += ")";
				
				logger.debug(sCreateTable);
				
				tableAttributeService.executeSql(sCreateTable);
				logger.info(sCreateTable);
				tableService.saveOrUpdate(table);
	        	
			}
			else
			{
				HqlFilter hqlFilter = new HqlFilter();
				
				if(tableId != null && tableId.equalsIgnoreCase("") == false && tableId.equalsIgnoreCase("null") == false)
				{
					hqlFilter.addQryCond("tableId", HqlFilter.Operator.EQ, tableId);
				}
				
				List<SysTableAttribute> layerAttrInDB = tableAttributeService.findByFilter(hqlFilter);
				
				String sDropColumn = "ALTER TABLE "+tableENName + "";//JS端保证至少有一个采集属性
				String sColNames = "";
				//反向比较,需要删除字段映射,同时生成drop column的语句
				for(int i=0;i<layerAttrInDB.size();i++)//目前只支持字段名称的比较
				{
					SysTableAttribute slaInDB = layerAttrInDB.get(i);
					
					int j = 0;
					//数据库中保存的映射信息
					for(;j<layerAttribute.size();j++)
					{
						SysTableAttribute sla = layerAttribute.get(j);
						
						if(slaInDB.getENName().equalsIgnoreCase(sla.getENName()) &&  
								//slaInDB.getValues().equalsIgnoreCase(sla.getValues()) && 
								slaInDB.getDBLength() == sla.getDBLength() && 
								//slaInDB.getZHName().equalsIgnoreCase(sla.getZHName())  &&
								slaInDB.getDBType().equalsIgnoreCase(sla.getDBType()) )
						{
							break;
						}
					}
					
					if(j == layerAttribute.size())
					{
						sColNames += " DROP " + slaInDB.getENName() + ",";
						tableAttributeService.delete(slaInDB);
					}
				}
				
				if(sColNames.endsWith(","))
				{
					sDropColumn += sColNames.substring(0, sColNames.length() - 1);
					
					tableAttributeService.executeSql(sDropColumn);
				}
				
				//======================新的字段  更新数据库中的映射表=================================
				String sAddColumn = "ALTER TABLE "+tableENName + "";//JS端保证至少有一个采集属性
				sColNames = "";
				for(int i=0;i<layerAttribute.size();i++)//目前只支持字段名称的比较
				{
					SysTableAttribute sla = layerAttribute.get(i);
					
					int j = 0;
					SysTableAttribute slaInDB = null;
					//数据库中保存的映射信息
					for(;j<layerAttrInDB.size();j++)
					{
						slaInDB = layerAttrInDB.get(j);
						
						if(slaInDB.getENName().equalsIgnoreCase(sla.getENName()) &&  
								//slaInDB.getValues().equalsIgnoreCase(sla.getValues()) && 
								slaInDB.getDBLength() == sla.getDBLength() && 
								//slaInDB.getZHName().equalsIgnoreCase(sla.getZHName())  &&
								slaInDB.getDBType().equalsIgnoreCase(sla.getDBType()) )
						{
							break;
						}
					}
					
					if(j == layerAttrInDB.size())//当前从界面上过来的字段在数据库中没有找到
					{
						String sDBType = sla.getDBType();
						if(sDBType != null && sDBType.equalsIgnoreCase(ConstValue.DB_TYPE_STRING))
						{
							sColNames += " ADD "+sla.getENName() + " varchar("+sla.getDBLength()+"),";
						}
						else if(sDBType != null && sDBType.equalsIgnoreCase(ConstValue.DB_TYPE_NUMBER))
						{
							sColNames += " ADD "+sla.getENName() + " integer,";
						}
						else if(sDBType != null && sDBType.equalsIgnoreCase(ConstValue.DB_TYPE_DATE))
						{
							sColNames += " ADD "+sla.getENName() + " date,";
						}
						else if(sDBType != null && sDBType.equalsIgnoreCase(ConstValue.DB_TYPE_TIME))
						{
							sColNames += " ADD "+sla.getENName() + " datetime,";
						}
						
						sla.setId(Utility.getUniStr());
						sla.setTableId(tableId);
						tableAttributeService.save(sla);
					}
					else//当前从页面上过来的字段在数据库中找到了,但是也要更新seq
					{
						slaInDB.setSeq(sla.getSeq());
						slaInDB.setValues(sla.getValues());
						slaInDB.setComponentsType(sla.getComponentsType());
						//slaInDB.setAppDisplay(sla.getAppDisplay());
						slaInDB.setSupportQuery(sla.getSupportQuery());
						tableAttributeService.save(slaInDB);
					}
				}
				if(sColNames.endsWith(","))
				{
					sAddColumn += sColNames.substring(0, sColNames.length() - 1);
					
					tableAttributeService.executeSql(sAddColumn);
				}
			}
		}
		catch(Exception e)
		{
			logger.error(e.getMessage(),e);
			jsonObj.put("success", false);
			jsonObj.put("errMsg", "创建或者修改表单失败");
		}
        return jsonObj.toString();
    }
	private ArrayList<TableField> getTableFieldsByTableName(String sTableName,String database)
	{
		ArrayList<TableField> alTblField = new ArrayList<TableField>();
		
		//String sSql = "SELECT col_description(a.attrelid,a.attnum) as comment,format_type(a.atttypid,a.atttypmod) as type,a.attname as name, a.attnotnull as notnull "
		//		+ "FROM pg_class as c,pg_attribute as a where c.relname = '"+sTableName.toLowerCase()+"' and a.attrelid = c.oid and a.attnum>0";
		
		String sSql = "SELECT COLUMN_NAME,DATA_TYPE FROM information_schema.COLUMNS WHERE table_name = '"+sTableName.toLowerCase()+"' and table_schema = '"+database.toLowerCase()+"'";
		
		List<HashMap> listPie = tableAttributeService.findBySql(sSql);
		
		for(int i=0;i<listPie.size();i++)
		{
			HashMap hm = listPie.get(i);
			
			String sFieldType = (String)hm.get("DATA_TYPE");
			String sFieldName = (String)hm.get("COLUMN_NAME");
			
			TableField tf = new TableField();
			
			tf.setDBType(sFieldType);
			tf.setFieldname(sFieldName);
			alTblField.add(tf);
		}
		
		return alTblField;
	}
	
	
	@RequestMapping(value=ConstValue.TABLE_CONTROLLER_DELETE_TABLE,method = RequestMethod.POST,produces="text/html;charset=UTF-8")
    @ResponseBody
	public String deleteTable(String tableId)
	{
		logger.debug("deleteTable tableId:"+tableId);
    	JSONObject jsonObj = new JSONObject();
    	
		try
		{
			SysTable table = tableService.getById(tableId);
			
			String sTableName = table.getTableENName();
			
			sTableName = sTableName.toLowerCase();
			
			String sSql = "DROP TABLE "+sTableName;
			
			HqlFilter hqlFilter = new HqlFilter();
			
			if(tableId != null && tableId.equalsIgnoreCase("") == false && tableId.equalsIgnoreCase("null") == false)
			{
				hqlFilter.addQryCond("tableId", HqlFilter.Operator.EQ, tableId);
			}
			
			List<SysTableAttribute> layerAttrInDB = tableAttributeService.findByFilter(hqlFilter);
			
			//数据库中保存的映射信息
			for(int i=0;i<layerAttrInDB.size();i++)
			{
				SysTableAttribute sla = layerAttrInDB.get(i);
				tableAttributeService.delete(sla);
			}
			
			try
			{
				tableService.executeSql(sSql);
			}
			catch(Exception e)
			{
				logger.error(e.getMessage(),e);
			}
			tableService.delete(table);
			
	        jsonObj.put("success", true);
		}
		catch(Exception e)
		{
			logger.error(e.getMessage(),e);
			jsonObj.put("success", false);
		}
        return jsonObj.toString();
    }
	
	
	@RequestMapping(value=ConstValue.TABLE_CONTROLLER_GET_TABLE,method = {RequestMethod.POST,RequestMethod.GET},produces="text/html;charset=UTF-8")
    @ResponseBody
	public String getTable(String tableId)
	{
		logger.debug("getLayer "+tableId);
    	JSONObject jsonObj = new JSONObject();
    	
		try
		{
			String sSource = request.getHeader("source");//app
			
			SysTable table = tableService.getById(tableId);
			
			if(table == null)
			{
				jsonObj.put("success", false);
				jsonObj.put("errMsg", "未找到表单信息");
				return jsonObj.toString();
			}
			
			jsonObj.put("id", table.getId());
			jsonObj.put("gisType", table.getGisType());
			jsonObj.put("tableENName", table.getTableENName());
			jsonObj.put("tableZHName", table.getTableZHName());
			jsonObj.put("tableDescription", table.getTableDescription());
			
			HqlFilter hqlFilter = new HqlFilter();
			
			if(tableId != null && tableId.equalsIgnoreCase("") == false && tableId.equalsIgnoreCase("null") == false)
			{
				hqlFilter.addQryCond("tableId", HqlFilter.Operator.EQ, tableId);
			}
			
			
			JSONArray jsonArr = new JSONArray();
			List<SysTableAttribute> layerAttrInDB = tableAttributeService.findByFilter(hqlFilter);
			
			//数据库中保存的映射信息
			for(int i=0;i<layerAttrInDB.size();i++)
			{
				SysTableAttribute sla = layerAttrInDB.get(i);
				
				JSONObject jsonAttr = new JSONObject();
				
				jsonAttr.put("id", sla.getId());
				jsonAttr.put("componentsType", sla.getComponentsType());
				jsonAttr.put("dbType", sla.getDBType());
				jsonAttr.put("dbLength", sla.getDBLength());
				jsonAttr.put("values", sla.getValues());
				jsonAttr.put("zhName", sla.getZHName());
				jsonAttr.put("enName", sla.getENName());
				jsonAttr.put("parentField", sla.getparentField());
				jsonAttr.put("parentValue", sla.getparentValue());
				jsonAttr.put("appListDisplay", sla.getAppListDisplay());
				jsonAttr.put("appDisplay", sla.getAppDisplay());
				jsonAttr.put("showalgo", sla.getshowalgo());
				jsonAttr.put("encryption", sla.getencryption());
				/*if(sSource != null && sSource.equalsIgnoreCase("app") && sla.getAppDisplay().equalsIgnoreCase("否"))
				{
					continue;
				}
				else
				{*/
					/*String sAppDisplay = sla.getAppDisplay();//兼容历史数据,如果未设置,则在app上显示
					if(sAppDisplay == null || sAppDisplay.equalsIgnoreCase(""))
						sAppDisplay = "是";
					
					jsonAttr.put("appDisplay", sAppDisplay);
					*/
					String sSupportQuery = sla.getSupportQuery();//兼容历史数据,如果未设置,则在app上显示
					if(sSupportQuery == null || sSupportQuery.equalsIgnoreCase(""))
						sSupportQuery = "否";
					
					jsonAttr.put("supportQuery", sSupportQuery);
				//}
				jsonAttr.put("seq", sla.getSeq() == null?"":sla.getSeq());
				jsonArr.put(jsonAttr);
			}
			
			jsonObj.put("attributeList", jsonArr);
			jsonObj.put("totalCnt", layerAttrInDB.size());
	        jsonObj.put("success", true);
		}
		catch(Exception e)
		{
			logger.error(e.getMessage(),e);
			jsonObj.put("success", false);
		}
        return jsonObj.toString();
	}
	
	
	

	@RequestMapping(value="unpublishLayer",method = RequestMethod.POST,produces="text/html;charset=UTF-8")
    @ResponseBody
	public String unpublishLayer(String sTableName)
	{
		logger.debug("unpublishLayer :"+sTableName);
    	JSONObject jsonObj = new JSONObject();
    	
		try
		{
			/*SysProject sysProject = projectService.getById(projectId);
			
			SysTenant tenant = ConstValue.projectId2Tenant.get(projectId);			
			String sTenantCode = tenant.getTenantCode();
			
			SysLayer layer = layerService.getById(layerId);
			
			String sLayerCode = layer.getLayerCode();
			String sLayerENName = layer.getLayerENName();
			String sLayerType = layer.getLayerType();
			
			String sTableName = sTenantCode+"_"+sysProject.getProjectCode()+"_"+sLayerCode+"_"+sLayerENName;
			*/
			sTableName = sTableName.toLowerCase();
			
			//String sSql = "DROP TABLE "+sTableName;
			/*
			HqlFilter hqlFilter = new HqlFilter();
			
			if(layerId != null && layerId.equalsIgnoreCase("") == false && layerId.equalsIgnoreCase("null") == false)
			{
				hqlFilter.addQryCond("layerId", HqlFilter.Operator.EQ, layerId);
			}
			
			List<SysLayerAttribute> layerAttrInDB = layerAttributeService.findByFilter(hqlFilter);
			
			//数据库中保存的映射信息
			for(int i=0;i<layerAttrInDB.size();i++)
			{
				SysLayerAttribute sla = layerAttrInDB.get(i);
				layerAttributeService.delete(sla);
			}
			
			HqlFilter hqlFilterProject = new HqlFilter();
			
			if(layerId != null && layerId.equalsIgnoreCase("") == false && layerId.equalsIgnoreCase("null") == false)
			{
				hqlFilterProject.addQryCond("layer_id", HqlFilter.Operator.EQ, layerId);
			}
			
			List<SysProjectLayer> projectLayer = projectLayerService.findByFilter(hqlFilterProject);
			
			for(int i=0;i<projectLayer.size();i++)
			{
				SysProjectLayer sp = projectLayer.get(i);
				projectLayerService.delete(sp);
			}
			*/
			//publisher.removeLayer(null,sLayerENName);			
			//publisher.unpublishCoverage(workspace, storeName, sLayerENName);
			
			publisher.unpublishFeatureType(workspace, dbStoreName, sTableName);
			
	        jsonObj.put("success", true);
		}
		catch(Exception e)
		{
			logger.error(e.getMessage(),e);
			jsonObj.put("success", false);
		}
        return jsonObj.toString();
    }
	
	public boolean publishLayer(String tableName,String gisType)
	{
		boolean bPublished = true;
		
		tableName = tableName.toLowerCase();
		
        GSFeatureTypeEncoder fte = new GSFeatureTypeEncoder();
        fte.setName(tableName);
        fte.setNativeName(tableName);
        fte.setTitle(tableName);
        fte.setNativeCRS("EPSG:4326");
        fte.setLatLonBoundingBox(Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, "EPSG:4326");
        fte.setDescription(tableName);
        fte.setEnabled(true);
        
        GSLayerEncoder layerEncoder = new GSLayerEncoder();
        layerEncoder.setEnabled(true);
        layerEncoder.setQueryable(true);
        if(gisType.equalsIgnoreCase("点"))
        	layerEncoder.setDefaultStyle("point");
        else if(gisType.equalsIgnoreCase("线"))
        	layerEncoder.setDefaultStyle("line");
        else if(gisType.equalsIgnoreCase("面"))
        	layerEncoder.setDefaultStyle("polygon");
        
        bPublished = publisher.publishDBLayer(workspace, dbStoreName, fte, layerEncoder);
		
		return bPublished;
	}
}
