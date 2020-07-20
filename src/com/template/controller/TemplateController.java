package com.template.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.bcel.util.ClassPath;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.template.model.SysTable;
import com.template.model.SysTableAttribute;
import com.template.service.TableAttributeService;
import com.template.service.TableService;
import com.template.util.ConstValue;
import com.template.util.FileUtil;
import com.template.util.HqlFilter;
import com.template.util.TimeUtil;
import com.template.util.Utility;


@Controller
@RequestMapping(ConstValue.TEMPLATE_CONTROLLER)
public class TemplateController {
	
	private static Logger logger = Logger.getLogger(TemplateController.class);
	
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

	
	@RequestMapping(value=ConstValue.TEMPLATE_CONTROLLER_GENERATE_CODE,method = RequestMethod.POST,produces="text/html;charset=UTF-8")
    @ResponseBody
	public String generateCode(String tableId,String packageName)
	{
		logger.debug("generateCode "+tableId);
    	JSONObject jsonObj = new JSONObject();
    	
		try
		{
			packageName = "com.template";
			
			SysTable sysTable = tableService.getById(tableId);
			
			String sErrMsg = "";
			
			if(sysTable == null)
			{
				logger.error("can not find layer by layerId:"+tableId);
				jsonObj.put("success", false);
				jsonObj.put("errMsg", "can not find layer by layerId:"+tableId);
				
				return jsonObj.toString();
			}
			
			sErrMsg = copyGenerateModel(tableId,packageName);
			if(sErrMsg != null && sErrMsg.equalsIgnoreCase("") == false)
			{
				logger.error(sErrMsg);
				jsonObj.put("success", false);
				jsonObj.put("errMsg", sErrMsg);
				
				return jsonObj.toString();
			}
			

			sErrMsg = copyGenerateService(tableId,packageName);
			if(sErrMsg != null && sErrMsg.equalsIgnoreCase("") == false)
			{
				logger.error(sErrMsg);
				jsonObj.put("success", false);
				jsonObj.put("errMsg", sErrMsg);
				
				return jsonObj.toString();
			}
			
			sErrMsg = copyGenerateController(tableId,packageName);
			if(sErrMsg != null && sErrMsg.equalsIgnoreCase("") == false)
			{
				logger.error(sErrMsg);
				jsonObj.put("success", false);
				jsonObj.put("errMsg", sErrMsg);
				
				return jsonObj.toString();
			}
			
			sErrMsg = copyGenerateJavaScript(tableId);
			if(sErrMsg != null && sErrMsg.equalsIgnoreCase("") == false)
			{
				logger.error(sErrMsg);
				jsonObj.put("success", false);
				jsonObj.put("errMsg", sErrMsg);
				
				return jsonObj.toString();
			}
			
			sErrMsg = copyGenerateHtml(tableId);
			if(sErrMsg != null && sErrMsg.equalsIgnoreCase("") == false)
			{
				logger.error(sErrMsg);
				jsonObj.put("success", false);
				jsonObj.put("errMsg", sErrMsg);
				
				return jsonObj.toString();
			}
			
			
			sErrMsg = copyGenerateJavaScriptDetail(tableId);
			if(sErrMsg != null && sErrMsg.equalsIgnoreCase("") == false)
			{
				logger.error(sErrMsg);
				jsonObj.put("success", false);
				jsonObj.put("errMsg", sErrMsg);
				
				return jsonObj.toString();
			}
			
			
			sErrMsg = copyGenerateHtmlDetail(tableId);
			if(sErrMsg != null && sErrMsg.equalsIgnoreCase("") == false)
			{
				logger.error(sErrMsg);
				jsonObj.put("success", false);
				jsonObj.put("errMsg", sErrMsg);
				
				return jsonObj.toString();
			}
			
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
	
	
	private String copyGenerateModel(String tableId,String packageName)
	{
		try
		{
			SysTable sysLayer = tableService.getById(tableId);
			
			String sTableName = sysLayer.getTableENName();
			
			MyStringBuffer modelStringBuffer = new MyStringBuffer();
			
			modelStringBuffer.append("package "+packageName+".model;");
			modelStringBuffer.append("import javax.persistence.Column;");
			modelStringBuffer.append("import javax.persistence.Entity;");
			modelStringBuffer.append("import javax.persistence.Id;");
			modelStringBuffer.append("import javax.persistence.Table;");
			modelStringBuffer.append("import org.hibernate.annotations.DynamicInsert;");
			modelStringBuffer.append("import org.hibernate.annotations.DynamicUpdate;");
			modelStringBuffer.append("import java.util.Date;");
			modelStringBuffer.append("\r\n");
			modelStringBuffer.append("@Entity");
			modelStringBuffer.append("@Table(name = \""+sTableName.toUpperCase()+"\")");
			modelStringBuffer.append("@DynamicInsert(true)");
			modelStringBuffer.append("@DynamicUpdate(true)");
			
			modelStringBuffer.append("public class "+toUpperCaseFirstOne(sTableName)+" implements java.io.Serializable {");
			
			modelStringBuffer.append("@Id");
			modelStringBuffer.append("@Column(name = \"id\", nullable = false)");
			modelStringBuffer.append("public String id;");
			modelStringBuffer.append("public String getId()");
			modelStringBuffer.append("{");
			modelStringBuffer.append("	return id;");
			modelStringBuffer.append("}");
			modelStringBuffer.append("public void setId(String id)");
			modelStringBuffer.append("{");
			modelStringBuffer.append("	this.id = id;");
			modelStringBuffer.append("}");
			

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
				
				String dbType = sla.getDBType();//ConstValue.DB_TYPE_STRING
				//String fieldName = toLowerCaseFirstOne(sla.getENName());
				
				String objectType = "";
				
				if(dbType.equalsIgnoreCase(ConstValue.DB_TYPE_STRING))
					objectType = "String";
				else if(dbType.equalsIgnoreCase(ConstValue.DB_TYPE_NUMBER))
					objectType = "Integer";
				else if(dbType.equalsIgnoreCase(ConstValue.DB_TYPE_TIME) || dbType.equalsIgnoreCase(ConstValue.DB_TYPE_DATE))
					objectType = "Date";
				
				modelStringBuffer.append("@Column(name = \""+sla.getENName()+"\", nullable = true)");
				modelStringBuffer.append("public "+objectType+" "+sla.getENName()+";");
				
				modelStringBuffer.append("public "+objectType+" get"+sla.getENName()+"()");
				modelStringBuffer.append("{");
				modelStringBuffer.append("	return "+sla.getENName()+";");
				modelStringBuffer.append("}");
				modelStringBuffer.append("public void set"+sla.getENName()+"("+objectType+" "+sla.getENName()+")");
				modelStringBuffer.append("{");
				modelStringBuffer.append("	this."+sla.getENName()+" = "+sla.getENName()+";");
				modelStringBuffer.append("}");
			}
			
			modelStringBuffer.append("}\r\n");
			
			FileUtil.saveFileFromStringBuffer(modelStringBuffer.toString(), "c://temp//model", toUpperCaseFirstOne(sTableName)+".java");
		}
		catch(Exception e)
		{
			logger.error(e.getMessage(),e);
			return e.getMessage();
		}
		
		return "";
	}
	
	private String copyGenerateService(String layerId,String packageName)
	{
		try
		{
			SysTable sysLayer = tableService.getById(layerId);
			
			String sTableName = sysLayer.getTableENName();
			
			MyStringBuffer serviceStringBuffer = new MyStringBuffer();
			serviceStringBuffer.append("package "+packageName+".service;");
			serviceStringBuffer.append("import org.springframework.stereotype.Service;");
			serviceStringBuffer.append("import org.springframework.transaction.annotation.Transactional;");
			
			serviceStringBuffer.append("import "+packageName+".model."+toUpperCaseFirstOne(sTableName)+";");
			serviceStringBuffer.append("import com.template.service.base.BaseServiceImpl;");
			
			serviceStringBuffer.append("@Transactional");
			serviceStringBuffer.append("@Service");
			serviceStringBuffer.append("public class "+toUpperCaseFirstOne(sTableName)+"Service extends BaseServiceImpl<"+toUpperCaseFirstOne(sTableName)+">");
			serviceStringBuffer.append("{");
				
			serviceStringBuffer.append("}");
			
			FileUtil.saveFileFromStringBuffer(serviceStringBuffer.toString(), "c://temp//service", toUpperCaseFirstOne(sTableName)+"Service.java");
		}
		catch(Exception e)
		{
			logger.error(e.getMessage(),e);
			return e.getMessage();
		}
		
		return "";
	}
	
	private String copyGenerateController(String tableId,String packageName)
	{
		try
		{
			SysTable sysTable = tableService.getById(tableId);
			
			String sTableName = sysTable.getTableENName();
			
			String sFirstUpper = toUpperCaseFirstOne(sTableName);
			String sFirstLower = toLowerCaseFirstOne(sTableName);
			
			MyStringBuffer serviceStringBuffer = new MyStringBuffer();
			
			serviceStringBuffer.append("package "+packageName+".controller;");
			
			serviceStringBuffer.append("import org.apache.log4j.Logger;");
			serviceStringBuffer.append("import org.json.JSONArray;");
			serviceStringBuffer.append("import org.json.JSONObject;");
			
			serviceStringBuffer.append("import "+packageName+".model."+sFirstUpper+";");
			serviceStringBuffer.append("import "+packageName+".service."+sFirstUpper+"Service;");
			serviceStringBuffer.append("import com.template.util.HqlFilter;");
			serviceStringBuffer.append("import com.template.util.ConstValue;");
			serviceStringBuffer.append("import com.template.util.Utility;");
			serviceStringBuffer.append("import com.template.util.TimeUtil;");
			
			serviceStringBuffer.append("import java.util.List;");
			serviceStringBuffer.append("import java.util.Date;");
			serviceStringBuffer.append("import javax.servlet.http.HttpServletRequest;");
			serviceStringBuffer.append("import org.springframework.beans.factory.annotation.Autowired;");
			serviceStringBuffer.append("import org.springframework.stereotype.Controller;");
			serviceStringBuffer.append("import org.springframework.web.bind.annotation.RequestMapping;");
			serviceStringBuffer.append("import org.springframework.web.bind.annotation.RequestMethod;");
			serviceStringBuffer.append("import org.springframework.web.bind.annotation.ResponseBody;");
			
			serviceStringBuffer.append("@Controller");
			serviceStringBuffer.append("@RequestMapping(\""+sFirstLower+"Controller\")");
			serviceStringBuffer.append("public class "+sFirstUpper+"Controller {");
			
			serviceStringBuffer.append("	private static Logger logger = Logger.getLogger("+sFirstUpper+"Controller.class);");
				
			serviceStringBuffer.append("	@Autowired");
			serviceStringBuffer.append("	private  HttpServletRequest request;");
				
			serviceStringBuffer.append("	@Autowired");
			
			serviceStringBuffer.append("	private "+sFirstUpper+"Service "+sFirstLower+"Service;");
			
			HqlFilter hqlFilter = new HqlFilter();
			
			if(tableId != null && tableId.equalsIgnoreCase("") == false && tableId.equalsIgnoreCase("null") == false)
			{
				hqlFilter.addQryCond("tableId", HqlFilter.Operator.EQ, tableId);
			}
			
			List<SysTableAttribute> tableAttrInDB = tableAttributeService.findByFilter(hqlFilter);
			
			String sAddPara= "";
			String sQryPara= "";
			MyStringBuffer qryFilter = new MyStringBuffer();
			MyStringBuffer setVal = new MyStringBuffer();
			MyStringBuffer loadJson = new MyStringBuffer();
			MyStringBuffer getJson = new MyStringBuffer();
			//数据库中保存的映射信息
			for(int i=0;i<tableAttrInDB.size();i++)
			{
				SysTableAttribute sla = tableAttrInDB.get(i);
				
				String dbType = sla.getDBType();
				
				String objectType = "";
				String paraType = "";//时间的paraType为String
				
				if(dbType.equalsIgnoreCase(ConstValue.DB_TYPE_STRING))
				{
					objectType = "String";
					paraType = "String";
				}
				else if(dbType.equalsIgnoreCase(ConstValue.DB_TYPE_NUMBER))
				{
					objectType = "Integer";
					paraType = "Integer";
				}
				else if(dbType.equalsIgnoreCase(ConstValue.DB_TYPE_TIME) || dbType.equalsIgnoreCase(ConstValue.DB_TYPE_DATE))
				{
					objectType = "Date";
					paraType = "String"; 
				}
				
				sAddPara += paraType+" "+sla.getENName()+",";
				
				if(sla.getSupportQuery() != null && sla.getSupportQuery().equalsIgnoreCase("是"))
				{
					sQryPara += "String "+sla.getENName()+",";
					
					qryFilter.append("if("+sla.getENName()+" != null && "+sla.getENName()+".equalsIgnoreCase(\"\") == false && "+sla.getENName()+".equalsIgnoreCase(\"null\") == false)");
					qryFilter.append("{");
					
					if(sla.getComponentsType().equalsIgnoreCase(ConstValue.COMPONENT_TYPE_DROPDOWN))
					{
						qryFilter.append("	hqlFilter.addQryCond(\""+sla.getENName()+"\", HqlFilter.Operator.EQ, "+sla.getENName()+");");
					}
					else
					{
						qryFilter.append("	hqlFilter.addQryCond(\""+sla.getENName()+"\", HqlFilter.Operator.LIKE, \"%\"+"+sla.getENName()+"+\"%\");");
					}	
					
					qryFilter.append("}");
				}
				
				if(dbType.equalsIgnoreCase(ConstValue.DB_TYPE_DATE))
				{
					
					setVal.append("		"+sFirstLower+".set"+sla.getENName()+"(TimeUtil.parseDate("+sla.getENName()+", \"yyyy-MM-dd\"));");	
					loadJson.append("			jsonTmp.put(\""+sla.getENName()+"\",TimeUtil.formatDate("+sFirstLower+".get"+sla.getENName()+"(),\"yyyy-MM-dd\"));");
					getJson.append("			jsonObj.put(\""+sla.getENName()+"\",TimeUtil.formatDate("+sFirstLower+".get"+sla.getENName()+"(),\"yyyy-MM-dd\"));");	
				}
				else if(dbType.equalsIgnoreCase(ConstValue.DB_TYPE_TIME))
				{
					setVal.append("		"+sFirstLower+".set"+sla.getENName()+"(TimeUtil.parseDate("+sla.getENName()+", \"yyyy-MM-dd HH:mm\"));");
					
					loadJson.append("			jsonTmp.put(\""+sla.getENName()+"\",TimeUtil.formatDate("+sFirstLower+".get"+sla.getENName()+"(),\"yyyy-MM-dd HH:mm\"));");
					getJson.append("			jsonObj.put(\""+sla.getENName()+"\",TimeUtil.formatDate("+sFirstLower+".get"+sla.getENName()+"(),\"yyyy-MM-dd HH:mm\"));");	
				}
				else
				{
					setVal.append("		"+sFirstLower+".set"+sla.getENName()+"("+sla.getENName()+");");	
					loadJson.append("			jsonTmp.put(\""+sla.getENName()+"\","+sFirstLower+".get"+sla.getENName()+"());");
					getJson.append("			jsonObj.put(\""+sla.getENName()+"\","+sFirstLower+".get"+sla.getENName()+"());");		
				}	
			}
			
			if(sAddPara.endsWith(","))
				sAddPara = sAddPara.substring(0,sAddPara.length() - 1);
			
			if(sQryPara.endsWith(","))
				sQryPara = sQryPara.substring(0,sQryPara.length() - 1);
			
			serviceStringBuffer.append("@RequestMapping(value=\"addOrUpdate\",method = RequestMethod.POST,produces=\"text/html;charset=UTF-8\")");
			serviceStringBuffer.append("@ResponseBody");
			
			serviceStringBuffer.append("public String addOrUpdate(String id," + sAddPara + ")");
			serviceStringBuffer.append("{");
			serviceStringBuffer.append("	JSONObject jsonObj = new JSONObject();");
		    	
			serviceStringBuffer.append("	try");
			serviceStringBuffer.append("	{");
			
			serviceStringBuffer.append("		"+sFirstUpper+" "+sFirstLower+";");
			
			serviceStringBuffer.append("		if(id==null || id.equalsIgnoreCase(\"\"))");
			serviceStringBuffer.append("		{");
			serviceStringBuffer.append("			"+sFirstLower+" = new "+sFirstUpper+"();");		
			serviceStringBuffer.append("			"+sFirstLower+".setId(Utility.getUniStr());");
			serviceStringBuffer.append("		}");
			serviceStringBuffer.append("		else");
			serviceStringBuffer.append("		{");
			serviceStringBuffer.append("			"+sFirstLower+" = "+sFirstLower+"Service.getById(id);");
			serviceStringBuffer.append("		}");
				
			serviceStringBuffer.append(setVal.toString());
			
			serviceStringBuffer.append("        "+sFirstLower+"Service.save("+sFirstLower+");");
			
			serviceStringBuffer.append("        jsonObj.put(\"success\", true);");
			serviceStringBuffer.append("	}");
			serviceStringBuffer.append("	catch(Exception e)");
			serviceStringBuffer.append("	{");
			serviceStringBuffer.append("		logger.error(e.getMessage(),e);");
			serviceStringBuffer.append("		jsonObj.put(\"success\", false);");
			serviceStringBuffer.append("		jsonObj.put(\"errMsg\", e.getMessage());");
			serviceStringBuffer.append("	}");
			serviceStringBuffer.append("    return jsonObj.toString();");
			serviceStringBuffer.append("}");
			
			
			serviceStringBuffer.append("@RequestMapping(value=\"delete\",method = RequestMethod.POST,produces=\"text/html;charset=UTF-8\")");
			serviceStringBuffer.append("@ResponseBody");
			serviceStringBuffer.append("public String delete(String id)");
			serviceStringBuffer.append("{");
			serviceStringBuffer.append("	logger.debug(\"delete \"+id);");
			serviceStringBuffer.append("	JSONObject jsonObj = new JSONObject();");
		    	
			serviceStringBuffer.append("	try");
			serviceStringBuffer.append("	{");
			serviceStringBuffer.append("		"+sFirstUpper+" "+sFirstLower+" = "+sFirstLower+"Service.getById(id);");
					
			serviceStringBuffer.append("		"+sFirstLower+"Service.delete("+sFirstLower+");");
					
			serviceStringBuffer.append("        jsonObj.put(\"success\", true);");
			serviceStringBuffer.append("	}");
			serviceStringBuffer.append("	catch(Exception e)");
			serviceStringBuffer.append("	{");
			serviceStringBuffer.append("		logger.error(e.getMessage(),e);");
			serviceStringBuffer.append("		jsonObj.put(\"success\", false);");
			serviceStringBuffer.append("		jsonObj.put(\"errMsg\", e.getMessage());");
			serviceStringBuffer.append("	}");
			serviceStringBuffer.append("    return jsonObj.toString();");
			serviceStringBuffer.append("}");
			
			
			
			serviceStringBuffer.append("@RequestMapping(value=\"load\",method = RequestMethod.GET,produces=\"text/html;charset=UTF-8\")");
			serviceStringBuffer.append("@ResponseBody");
			serviceStringBuffer.append("public String load("+sQryPara+")");
			serviceStringBuffer.append("{");
			serviceStringBuffer.append("	JSONObject jsonObj = new JSONObject();");
		    	
			serviceStringBuffer.append("	try");
			serviceStringBuffer.append("	{");
			serviceStringBuffer.append("		HqlFilter hqlFilter = new HqlFilter();");
			
			serviceStringBuffer.append(qryFilter.toString());
					
			serviceStringBuffer.append("        List<"+sFirstUpper+"> listObj = "+sFirstLower+"Service.findByFilter(hqlFilter);");
					
			serviceStringBuffer.append("        JSONArray jsonArr = new JSONArray();");
			        
			serviceStringBuffer.append("        int iTotalCnt = 0;");
			serviceStringBuffer.append("		for(int i=0;i<listObj.size();i++)");
			serviceStringBuffer.append("		{");
			serviceStringBuffer.append("			"+sFirstUpper+" "+sFirstLower+" = listObj.get(i);");
						
			serviceStringBuffer.append("			JSONObject jsonTmp = new JSONObject();");
						
			serviceStringBuffer.append("			jsonTmp.put(\"id\", "+sFirstLower+".getId());");
			
			serviceStringBuffer.append(loadJson.toString());
						
			serviceStringBuffer.append("       		jsonArr.put(jsonTmp);");
			        	
			serviceStringBuffer.append("        	iTotalCnt++;");
			serviceStringBuffer.append("		}");
			        
			serviceStringBuffer.append("        jsonObj.put(\"totalCount\", iTotalCnt);");
			serviceStringBuffer.append("        jsonObj.put(\"list\", jsonArr);");
			        
			serviceStringBuffer.append("        jsonObj.put(\"success\", true);");
			serviceStringBuffer.append("	}");
			serviceStringBuffer.append("	catch(Exception e)");
			serviceStringBuffer.append("	{");
			serviceStringBuffer.append("		logger.error(e.getMessage(),e);");
			serviceStringBuffer.append("		jsonObj.put(\"success\", false);");
			serviceStringBuffer.append("	}");
			serviceStringBuffer.append("    return jsonObj.toString();");
			serviceStringBuffer.append("}");
			
			
			
			serviceStringBuffer.append("@RequestMapping(value=\"get\",method = {RequestMethod.POST,RequestMethod.GET},produces=\"text/html;charset=UTF-8\")");
			serviceStringBuffer.append("@ResponseBody");
			serviceStringBuffer.append("public String get(String id)");
			serviceStringBuffer.append("{");
			serviceStringBuffer.append("	JSONObject jsonObj = new JSONObject();");
		    	
			serviceStringBuffer.append("	try");
			serviceStringBuffer.append("	{");
			serviceStringBuffer.append("		"+sFirstUpper+" "+sFirstLower+" = "+sFirstLower+"Service.getById(id);");
					
			serviceStringBuffer.append("		if("+sFirstLower+" != null)");
			serviceStringBuffer.append("		{");
			
			serviceStringBuffer.append(getJson.toString());
			serviceStringBuffer.append("			jsonObj.put(\"success\", true);");
						
			serviceStringBuffer.append("		}");
			serviceStringBuffer.append("		else");
			serviceStringBuffer.append("		{");
			serviceStringBuffer.append("			logger.error(\"object is not found...\");");
						
			serviceStringBuffer.append("			jsonObj.put(\"success\", false);");
			serviceStringBuffer.append("			jsonObj.put(\"errMsg\", \"Object can not found...\");");
			serviceStringBuffer.append("		}");
			serviceStringBuffer.append("	}");
			serviceStringBuffer.append("	catch(Exception e)");
			serviceStringBuffer.append("	{");
			serviceStringBuffer.append("		logger.error(e.getMessage(),e);");
			serviceStringBuffer.append("		jsonObj.put(\"success\", false);");
			serviceStringBuffer.append("	}");
			serviceStringBuffer.append("    return jsonObj.toString();");
			serviceStringBuffer.append("}");
			
			serviceStringBuffer.append("}");
		
			FileUtil.saveFileFromStringBuffer(serviceStringBuffer.toString(), "c://temp/controller", toUpperCaseFirstOne(sTableName)+"Controller.java");
		}
		catch(Exception e)
		{
			logger.error(e.getMessage(),e);
			return e.getMessage();
		}
		
		return "";
	}
	

	private String copyGenerateJavaScript(String tableId)
	{
		String sReturn = "";
		
		try
		{
			SysTable sysTable = tableService.getById(tableId);
			
			String sTableName = sysTable.getTableENName();
			
			String sFirstUpper = toUpperCaseFirstOne(sTableName);
			String sFirstLower = toLowerCaseFirstOne(sTableName);
			
			MyStringBuffer serviceStringBuffer = new MyStringBuffer();
			
			serviceStringBuffer.append("");
			
			HqlFilter hqlFilter = new HqlFilter();
			
			if(tableId != null && tableId.equalsIgnoreCase("") == false && tableId.equalsIgnoreCase("null") == false)
			{
				hqlFilter.addQryCond("tableId", HqlFilter.Operator.EQ, tableId);
			}
			
			List<SysTableAttribute> tableAttrInDB = tableAttributeService.findByFilter(hqlFilter);
			
			String sTableColumn = "";
			String sViewDetail = "";
			String sResetDetail = "";
			String sAddOrUpdateSubmit = "";
			int iColumnNum = 0;
			String sSupportQuery = "";
			String sQueryStr = "";
			
			MyStringBuffer AddOrUpdateVal = new MyStringBuffer();
			
			//数据库中保存的映射信息
			for(int i=0;i<tableAttrInDB.size();i++)
			{
				SysTableAttribute sla = tableAttrInDB.get(i);
				
				String componentsType = sla.getComponentsType();
				
				if(componentsType.equalsIgnoreCase(ConstValue.COMPONENT_TYPE_FILE) != true && componentsType.equalsIgnoreCase(ConstValue.COMPONENT_TYPE_IMAGE) != true)	
				{
					sTableColumn += ("					{ 'data': '"+sla.getENName()+"' ,'sClass':'text-center'},\r\n");
					iColumnNum ++;
				}
				
				sResetDetail += "	\\$('#"+sla.getENName()+"').val('');\r\n";
				
				if(sla.getSupportQuery() != null && sla.getSupportQuery().equalsIgnoreCase("是"))
				{
					sSupportQuery += " var "+sla.getENName()+" = \\$('#"+sla.getENName()+"Query').val();\r\n";
					sQueryStr += sla.getENName()+"='+"+sla.getENName()+"+'&";
				}
				
				if(sla.getComponentsType().equalsIgnoreCase(ConstValue.COMPONENT_TYPE_CHECK))
				{
					AddOrUpdateVal.append("var val"+sla.getENName()+"Arr=new Array();");  
					
					AddOrUpdateVal.append("\\$('input[name=\""+sla.getENName()+"\"]:checked').each(function()");  
					AddOrUpdateVal.append("{      ");  
					AddOrUpdateVal.append("	val"+sla.getENName()+"Arr.push(\\$(this).val());//向数组中添加元素  		");  	
					AddOrUpdateVal.append("});");  
	
					AddOrUpdateVal.append("var val"+sla.getENName()+" = val"+sla.getENName()+"Arr.join(VALUE_SPLITTER);//将数组元素连接起来以构建一个字符串");  
					
					sAddOrUpdateSubmit += "		"+sla.getENName()+":val"+sla.getENName()+",\r\n";
					
					sViewDetail += "if(obj."+sla.getENName()+" != null){\r\n";
					sViewDetail += "	var "+sla.getENName()+"Arr = obj."+sla.getENName()+".split(VALUE_SPLITTER);\r\n";
					
					sViewDetail += "	for(var j=0;j<"+sla.getENName()+"Arr.length;j++)\r\n";
					sViewDetail += "	{\r\n";
					sViewDetail += "		if("+sla.getENName()+"Arr[j] != '')\r\n";
					sViewDetail += "		{\r\n";
					sViewDetail += "			\\$(\"input[name='"+sla.getENName()+"'][value='\"+"+sla.getENName()+"Arr[j]+\"']\").attr('checked','true');\r\n";
					sViewDetail += "		}\r\n";
					sViewDetail += "	}	\r\n";
					sViewDetail += "}	\r\n";
				}
				else
				{
					sAddOrUpdateSubmit += "		"+sla.getENName()+":\\$('#"+sla.getENName()+"').val(),\r\n";
					sViewDetail += "				\\$('#"+sla.getENName()+"').val(obj."+sla.getENName()+");\r\n";
				}
			}
			
			sTableColumn += ("					{ 'data': '' ,'sClass':'text-center'}\r\n");	
			
			sAddOrUpdateSubmit = sAddOrUpdateSubmit.substring(0,sAddOrUpdateSubmit.length() - 3);
			
			try
			{  
				ClassPathResource r = new ClassPathResource("template.js");
				
				String sTpl = FileUtils.readFileToString(r.getFile(),"utf-8");
				
				sTpl = sTpl.replaceAll("\\{FirstLower\\}", sFirstLower)
							.replaceAll("\\{FirstUpper\\}", sFirstUpper)
							.replaceAll("\\{TableColumn\\}", sTableColumn)
							.replaceAll("\\{ColumnNum\\}", (""+iColumnNum))
							.replaceAll("\\{ViewDetail\\}", sViewDetail)
							.replaceAll("\\{ResetDetail\\}", sResetDetail)
							.replaceAll("\\{SupportQuery\\}", sSupportQuery)
							.replaceAll("\\{QueryStr\\}", sQueryStr)
							
							.replaceAll("\\{AddOrUpdateVal\\}", AddOrUpdateVal.toString())
							
							.replaceAll("\\{addOrUpdateSubmit\\}", sAddOrUpdateSubmit);
				
				serviceStringBuffer.append(sTpl);
			}
			catch(Exception e)
            {
            	logger.error(e.getMessage(),e);
            }
			
			
			FileUtil.saveFileFromStringBuffer(serviceStringBuffer.toString(), "c://temp/"+sFirstLower, sFirstLower+".js");
		}
		catch(Exception e)
		{
			logger.error(e.getMessage(),e);
			return e.getMessage();
		}
		
		return "";
	}
	
	private String copyGenerateHtml(String tableId)
	{
		String sReturn = "";
		
		try
		{
			SysTable sysTable = tableService.getById(tableId);
			
			String sTableName = sysTable.getTableENName();
			
			String sFirstUpper = toUpperCaseFirstOne(sTableName);
			String sFirstLower = toLowerCaseFirstOne(sTableName);
			
			MyStringBuffer serviceStringBuffer = new MyStringBuffer();
			
			serviceStringBuffer.append("");
			
			HqlFilter hqlFilter = new HqlFilter();
			
			if(tableId != null && tableId.equalsIgnoreCase("") == false && tableId.equalsIgnoreCase("null") == false)
			{
				hqlFilter.addQryCond("tableId", HqlFilter.Operator.EQ, tableId);
			}
			
			List<SysTableAttribute> tableAttrInDB = tableAttributeService.findByFilter(hqlFilter);
			
			String sTableColumn = "";
			//MyStringBuffer FormDetail = new MyStringBuffer();			
			MyStringBuffer QueryForm = new MyStringBuffer();

			MyStringBuffer InitScript = new MyStringBuffer();
			
			//数据库中保存的映射信息
			for(int i=0;i<tableAttrInDB.size();i++)
			{
				SysTableAttribute sla = tableAttrInDB.get(i);
				
				/*FormDetail.append("					<div class='form-group'>");
				FormDetail.append("						<label class='col-md-2 control-label'>"+sla.getZHName()+"</label>");
				FormDetail.append("  				<div class='col-md-9'>");
				*/
				String componentsType = sla.getComponentsType();
				String values = sla.getValues();
				
				//文件或者图片在列表中不显示
				if(componentsType.equalsIgnoreCase(ConstValue.COMPONENT_TYPE_FILE) != true && componentsType.equalsIgnoreCase(ConstValue.COMPONENT_TYPE_IMAGE) != true)
					sTableColumn += ("<th style='text-align:center;'>"+sla.getZHName()+"</th>\r\n");
				
				/*if(componentsType.equalsIgnoreCase(ConstValue.COMPONENT_TYPE_INPUT))
				{
					FormDetail.append(" 					<input type='text' class='form-control' id='"+sla.getENName()+"'>");
				}
				else if(componentsType.equalsIgnoreCase(ConstValue.COMPONENT_TYPE_TEXTAREA))
				{
					FormDetail.append("					<textarea class='form-control ckeditor field' name='" +sla.getENName() + "' id='" +sla.getENName() + "' rows='6'></textarea>");
				}
				else if(componentsType.equalsIgnoreCase(ConstValue.COMPONENT_TYPE_DROPDOWN))
				{
					FormDetail.append("						<select class='form-control field' name='" +sla.getENName() + "' id='" +sla.getENName() + "'>");
					
					String [] valArr = values.split(ConstValue.VALUE_SPLITTER);
					
					for(int j=0;j<valArr.length ;j++ ) 
					{ 
						FormDetail.append(" 						<option value='"+valArr[j]+"'>"+valArr[j]+"</option>");
					}
					FormDetail.append("						</select>");
				}
				else if(componentsType.equalsIgnoreCase(ConstValue.COMPONENT_TYPE_DATE))
				{
					FormDetail.append("					<div data-date-viewmode='years' data-date-format='yyyy-mm-dd' class='input-append date dpYears'>");
					FormDetail.append("						<input type='text' size='16' class='form-control' id='" +sla.getENName() + "'>");					
					FormDetail.append("						<span class='input-group-btn add-on'>");
					FormDetail.append("							<button class='btn btn-danger' type='button'><i class='fa fa-calendar'></i></button>");
					FormDetail.append("						</span>");
					FormDetail.append("					</div>");
				}
				else if(componentsType.equalsIgnoreCase(ConstValue.COMPONENT_TYPE_RADIO))
				{
					FormDetail.append("					<label class='radio-inline'>");
					
					String [] valArr = values.split(ConstValue.VALUE_SPLITTER);
					
					for(int j=0;j<valArr.length ;j++ ) 
					{ 
						FormDetail.append("						<input type='radio' name='" +sla.getENName() + "' value='"+valArr[j]+"'> "+valArr[j]);
					}
					FormDetail.append("					</label>");
				}
				else if(componentsType.equalsIgnoreCase(ConstValue.COMPONENT_TYPE_CHECK))
				{
					FormDetail.append("					<label class='radio-inline'>");
					
					String [] valArr = values.split(ConstValue.VALUE_SPLITTER);
					
					for(int j=0;j<valArr.length ;j++ ) 
					{ 
						FormDetail.append("					<input type='checkbox' name='" +sla.getENName() + "' value='"+valArr[j]+"'> "+valArr[j]);
					}
					FormDetail.append(" 				</label>");
				}
				else if(componentsType.equalsIgnoreCase(ConstValue.COMPONENT_TYPE_FILE) || componentsType.equalsIgnoreCase(ConstValue.COMPONENT_TYPE_IMAGE))
				{
					FormDetail.append("					<div id='"+sla.getENName() +"div' class='wu-example'>");
					FormDetail.append("						<input type='text' class='form-control field' name='" +sla.getENName() + "' id='" +sla.getENName() + "' style='display:none;'>");
					FormDetail.append("						<table class='table' id='"+sla.getENName() +"picktable'><thead><tr><th>名称</th><th>状态</th><th>操作</th></tr></thead><tbody></tbody></table>");
					FormDetail.append("						<div class='btns'>");
					FormDetail.append("						<div id='" + sla.getENName() + "pick'>选择文件</div>");
					FormDetail.append("						</div>");
					FormDetail.append("					</div>");
				}
				*/
				if(sla.getSupportQuery() != null && sla.getSupportQuery().equalsIgnoreCase("是"))
				{
					QueryForm.append("						<label class='col-md-1 control-label' style='text-align:right;'>"+sla.getZHName()+"</label>");
					QueryForm.append("<div class='col-sm-2'>");
					if(componentsType.equalsIgnoreCase(ConstValue.COMPONENT_TYPE_DROPDOWN))
					{
						QueryForm.append("						<select class='form-control field' name='" +sla.getENName() + "Query' id='" +sla.getENName() + "Query'>");
						
						QueryForm.append(" 						<option value=''>全部</option>");
						
						String [] valArr = values.split(ConstValue.VALUE_SPLITTER);
						
						for(int j=0;j<valArr.length ;j++ ) 
						{ 
							QueryForm.append(" 						<option value='"+valArr[j]+"'>"+valArr[j]+"</option>");
						}
						QueryForm.append("						</select>");
						
					}
					else// if(componentsType.equalsIgnoreCase(ConstValue.COMPONENT_TYPE_DROPDOWN))
					{
						
						QueryForm.append(" 					<input type='text' class='form-control' id='"+sla.getENName()+"Query'>");
					}	
					QueryForm.append("</div>");
				}
				
				//FormDetail.append(" 						</div>");
				//FormDetail.append(" 					</div>");
				
				

				if(componentsType.equalsIgnoreCase(ConstValue.COMPONENT_TYPE_FILE) || componentsType.equalsIgnoreCase(ConstValue.COMPONENT_TYPE_IMAGE))
				{
					InitScript.append("var uploader = WebUploader.create({");
					InitScript.append("	auto: true,");
					 
					InitScript.append("// swf文件路径");
					InitScript.append("//swf: BASE_URL + '/js/Uploader.swf',");
						
					InitScript.append("// 文件接收服务端");
					InitScript.append("server: getContextPath()+'/fileController/upload',");
					 
					InitScript.append("// 选择文件的按钮。可选。");
					InitScript.append("// 内部根据当前运行是创建，可能是input元素，也可能是flash.");
					InitScript.append("pick: '#"+sla.getENName() + "pick',");
					 
					//InitScript.append("// 只允许选择图片文件。");
					//InitScript.append("/*accept: {");
					//InitScript.append("							title: 'Images',");
					//InitScript.append("extensions: 'gif,jpg,jpeg,bmp,png',");
					//InitScript.append("mimeTypes: 'image/*'");
					//InitScript.append("},*/");
					InitScript.append("formData:{");
					InitScript.append("attributeName:'"+sla.getENName()+"'");
					InitScript.append("}");
					InitScript.append("});");
					
					InitScript.append("uploader.on( 'fileQueued', function( file ) {");
						
					InitScript.append("	\\$('#'+file.source._refer[0].id+'table').append('<tr><td>'+file.name+'</td><td id='+file.id+'>等待上传...</td>'+");
					InitScript.append("						\"<td><button type='button' class='btn btn-success btn-xs' onclick='javascript:downloadAttach();return false;'><i class='fa fa-check'></i></button></td>\"+");
					InitScript.append("						'</tr>');");
					InitScript.append("});");
	
					InitScript.append("uploader.on( 'uploadSuccess', function( file,response ) {");
						
					InitScript.append("	\\$( '#'+response.attributeName ).val(response.fileName+VALUE_SPLITTER+\\$('#'+response.attributeName ).val());");
						
					InitScript.append("	console.log(\\$( '#'+response.attributeName ).val());");
						
					InitScript.append("	\\$( '#'+file.id ).text('已上传');");
						
					InitScript.append("});");
	
					InitScript.append("uploader.on( 'uploadError', function( file,response ) {");
												
					InitScript.append("});	");
				}
			}
			
			sTableColumn += "<th style='text-align:center;'>操作</th>";
			
			try
			{  
				ClassPathResource r = new ClassPathResource("template.html");
				
				String sTpl = FileUtils.readFileToString(r.getFile(),"utf-8");
				
				sTpl = sTpl.replaceAll("\\{FirstLower\\}", sFirstLower)
							.replaceAll("\\{FirstUpper\\}", sFirstUpper)
							.replaceAll("\\{QueryForm\\}", QueryForm.toString())
							.replaceAll("\\{TableColumn\\}", sTableColumn)
							.replaceAll("\\{InitScript\\}", InitScript.toString());
							//.replaceAll("\\{FormDetail\\}",FormDetail.toString());
				
				serviceStringBuffer.append(sTpl);
			}
			catch(Exception e)
            {
            	logger.error(e.getMessage(),e);
            }
			
			
			FileUtil.saveFileFromStringBuffer(serviceStringBuffer.toString(), "c://temp/"+sFirstLower, sFirstLower+".html");
		}
		catch(Exception e)
		{
			logger.error(e.getMessage(),e);
			return e.getMessage();
		}
		
		return "";
	}
	
	private String copyGenerateJavaScriptDetail(String tableId)
	{
		String sReturn = "";
		
		try
		{
			SysTable sysTable = tableService.getById(tableId);
			
			String sTableName = sysTable.getTableENName();
			
			String sFirstUpper = toUpperCaseFirstOne(sTableName);
			String sFirstLower = toLowerCaseFirstOne(sTableName);
			
			MyStringBuffer serviceStringBuffer = new MyStringBuffer();
			
			serviceStringBuffer.append("");
			
			HqlFilter hqlFilter = new HqlFilter();
			
			if(tableId != null && tableId.equalsIgnoreCase("") == false && tableId.equalsIgnoreCase("null") == false)
			{
				hqlFilter.addQryCond("tableId", HqlFilter.Operator.EQ, tableId);
			}
			
			List<SysTableAttribute> tableAttrInDB = tableAttributeService.findByFilter(hqlFilter);
			
			String sTableColumn = "";
			String sViewDetail = "";
			String sResetDetail = "";
			String sAddOrUpdateSubmit = "";
			int iColumnNum = 0;
			String sSupportQuery = "";
			String sQueryStr = "";
			
			MyStringBuffer AddOrUpdateVal = new MyStringBuffer();
			
			//数据库中保存的映射信息
			for(int i=0;i<tableAttrInDB.size();i++)
			{
				SysTableAttribute sla = tableAttrInDB.get(i);
				
				String componentsType = sla.getComponentsType();
				
				sTableColumn += ("					{ 'data': '"+sla.getENName()+"' ,'sClass':'text-center'},\r\n");	
				
				sResetDetail += "	\\$('#"+sla.getENName()+"').val('');\r\n";
				
				
				
				if(sla.getSupportQuery() != null && sla.getSupportQuery().equalsIgnoreCase("是"))
				{
					sSupportQuery += " var "+sla.getENName()+" = \\$('#"+sla.getENName()+"Query').val();\r\n";
					sQueryStr += sla.getENName()+"='+"+sla.getENName()+"+'&";
				}
				
				if(sla.getComponentsType().equalsIgnoreCase(ConstValue.COMPONENT_TYPE_CHECK))
				{
					AddOrUpdateVal.append("var val"+sla.getENName()+"Arr=new Array();");  
					
					AddOrUpdateVal.append("\\$('input[name=\""+sla.getENName()+"\"]:checked').each(function()");  
					AddOrUpdateVal.append("{      ");  
					AddOrUpdateVal.append("	val"+sla.getENName()+"Arr.push(\\$(this).val());//向数组中添加元素  		");  	
					AddOrUpdateVal.append("});");  
	
					AddOrUpdateVal.append("var val"+sla.getENName()+" = val"+sla.getENName()+"Arr.join(VALUE_SPLITTER);//将数组元素连接起来以构建一个字符串");  
					
					sAddOrUpdateSubmit += "		"+sla.getENName()+":val"+sla.getENName()+",\r\n";
					
					sViewDetail += "if(obj."+sla.getENName()+" != null){\r\n";
					sViewDetail += "	var "+sla.getENName()+"Arr = obj."+sla.getENName()+".split(VALUE_SPLITTER);\r\n";
					
					sViewDetail += "	for(var j=0;j<"+sla.getENName()+"Arr.length;j++)\r\n";
					sViewDetail += "	{\r\n";
					sViewDetail += "		if("+sla.getENName()+"Arr[j] != '')\r\n";
					sViewDetail += "		{\r\n";
					sViewDetail += "			\\$(\"input[name='"+sla.getENName()+"'][value='\"+"+sla.getENName()+"Arr[j]+\"']\").attr('checked','true');\r\n";
					sViewDetail += "		}\r\n";
					sViewDetail += "	}	\r\n";
					sViewDetail += "}	\r\n";
				}
				else if(sla.getComponentsType().equalsIgnoreCase(ConstValue.COMPONENT_TYPE_IMAGE) || sla.getComponentsType().equalsIgnoreCase(ConstValue.COMPONENT_TYPE_FILE))
				{
					sAddOrUpdateSubmit += "		"+sla.getENName()+":\\$('#"+sla.getENName()+"').val(),\r\n";
					
					sViewDetail += "				var "+sla.getENName()+"Arr = obj."+sla.getENName()+".split(VALUE_SPLITTER);";
					
					sViewDetail += "				for(var j=0;j<"+sla.getENName()+"Arr.length;j++)";
					sViewDetail += "				{";
					sViewDetail += "					if("+sla.getENName()+"Arr[j] != '')";
					sViewDetail += "					{";
					sViewDetail += "						\\$('#"+sla.getENName()+"picktable').append('<tr><td>'+"+sla.getENName()+"Arr[j]+'</td><td>上传成功</td>'+";
					sViewDetail += "							'<td><button type=\"button\" class=\"btn btn-success btn-xs\" onclick=\"javascript:downloadAttach(\\\\''+"+sla.getENName()+"Arr[j]+'\\\\');return false;\"><i class=\"fa fa-check\"></i></button></td>'+";
					sViewDetail += "							'</tr>');";
					sViewDetail += "					}";
					sViewDetail += "				}";
				}
				else
				{
					sAddOrUpdateSubmit += "		"+sla.getENName()+":\\$('#"+sla.getENName()+"').val(),\r\n";
					sViewDetail += "				\\$('#"+sla.getENName()+"').val(obj."+sla.getENName()+");\r\n";
				}
			}
			
			iColumnNum = tableAttrInDB.size();
			
			sTableColumn += ("					{ 'data': '' ,'sClass':'text-center'}\r\n");	
			
			sAddOrUpdateSubmit = sAddOrUpdateSubmit.substring(0,sAddOrUpdateSubmit.length() - 3);
			
			try
			{  
				ClassPathResource r = new ClassPathResource("templateDetail.js");
				
				String sTpl = FileUtils.readFileToString(r.getFile(),"utf-8");
				
				sTpl = sTpl.replaceAll("\\{FirstLower\\}", sFirstLower)
							.replaceAll("\\{FirstUpper\\}", sFirstUpper)
							.replaceAll("\\{TableColumn\\}", sTableColumn)
							.replaceAll("\\{ColumnNum\\}", (""+iColumnNum))
							.replaceAll("\\{ViewDetail\\}", sViewDetail)
							.replaceAll("\\{ResetDetail\\}", sResetDetail)
							.replaceAll("\\{SupportQuery\\}", sSupportQuery)
							.replaceAll("\\{QueryStr\\}", sQueryStr)
							
							.replaceAll("\\{AddOrUpdateVal\\}", AddOrUpdateVal.toString())
							
							.replaceAll("\\{addOrUpdateSubmit\\}", sAddOrUpdateSubmit);
				
				serviceStringBuffer.append(sTpl);
			}
			catch(Exception e)
            {
            	logger.error(e.getMessage(),e);
            }
			
			
			FileUtil.saveFileFromStringBuffer(serviceStringBuffer.toString(), "c://temp/"+sFirstLower, sFirstLower+"Detail.js");
		}
		catch(Exception e)
		{
			logger.error(e.getMessage(),e);
			return e.getMessage();
		}
		
		return "";
	}
	
	private String copyGenerateHtmlDetail(String tableId)
	{
		String sReturn = "";
		
		try
		{
			SysTable sysTable = tableService.getById(tableId);
			
			String sTableName = sysTable.getTableENName();
			
			String sFirstUpper = toUpperCaseFirstOne(sTableName);
			String sFirstLower = toLowerCaseFirstOne(sTableName);
			
			MyStringBuffer serviceStringBuffer = new MyStringBuffer();
			
			serviceStringBuffer.append("");
			
			HqlFilter hqlFilter = new HqlFilter();
			
			if(tableId != null && tableId.equalsIgnoreCase("") == false && tableId.equalsIgnoreCase("null") == false)
			{
				hqlFilter.addQryCond("tableId", HqlFilter.Operator.EQ, tableId);
			}
			
			List<SysTableAttribute> tableAttrInDB = tableAttributeService.findByFilter(hqlFilter);
			
			//String sTableColumn = "";
			MyStringBuffer FormDetail = new MyStringBuffer();			
			//MyStringBuffer QueryForm = new MyStringBuffer();

			MyStringBuffer InitScript = new MyStringBuffer();
			
			//数据库中保存的映射信息
			for(int i=0;i<tableAttrInDB.size();i++)
			{
				SysTableAttribute sla = tableAttrInDB.get(i);
				
				//sTableColumn += ("<th style='text-align:center;'>"+sla.getZHName()+"</th>\r\n");	
				
				FormDetail.append("					<div class='form-group row'>");
				FormDetail.append("						<label class='col-md-2 control-label'>"+sla.getZHName()+"</label>");
				FormDetail.append("  				<div class='col-md-9'>");
				
				String componentsType = sla.getComponentsType();
				String values = sla.getValues();
				
				if(componentsType.equalsIgnoreCase(ConstValue.COMPONENT_TYPE_INPUT))
				{
					FormDetail.append(" 					<input type='text' class='form-control' id='"+sla.getENName()+"'>");
				}
				if(componentsType.equalsIgnoreCase(ConstValue.COMPONENT_TYPE_NUMBER_INTEGER))
				{
					FormDetail.append(" 					<input type='number' class='form-control' id='"+sla.getENName()+"'>");
				}
				else if(componentsType.equalsIgnoreCase(ConstValue.COMPONENT_TYPE_TEXTAREA))
				{
					FormDetail.append("					<textarea class='form-control ckeditor field' name='" +sla.getENName() + "' id='" +sla.getENName() + "' rows='6'></textarea>");
				}
				else if(componentsType.equalsIgnoreCase(ConstValue.COMPONENT_TYPE_DROPDOWN))
				{
					FormDetail.append("						<select class='form-control field' name='" +sla.getENName() + "' id='" +sla.getENName() + "'>");
					
					String [] valArr = values.split(ConstValue.VALUE_SPLITTER);
					
					for(int j=0;j<valArr.length ;j++ ) 
					{ 
						FormDetail.append(" 						<option value='"+valArr[j]+"'>"+valArr[j]+"</option>");
					}
					FormDetail.append("						</select>");
				}
				else if(componentsType.equalsIgnoreCase(ConstValue.COMPONENT_TYPE_DATE))
				{
					FormDetail.append("					<div data-date-viewmode='years' data-date-format='yyyy-mm-dd' class='input-append date dpYears'>");
					FormDetail.append("						<input type='text' size='16' class='form-control' id='" +sla.getENName() + "'>");					
					FormDetail.append("						<span class='input-group-btn add-on'>");
					FormDetail.append("							<button class='btn btn-danger' type='button'><i class='fa fa-calendar'></i></button>");
					FormDetail.append("						</span>");
					FormDetail.append("					</div>");
				}
				else if(componentsType.equalsIgnoreCase(ConstValue.COMPONENT_TYPE_RADIO))
				{
					FormDetail.append("					<label class='radio-inline'>");
					
					String [] valArr = values.split(ConstValue.VALUE_SPLITTER);
					
					for(int j=0;j<valArr.length ;j++ ) 
					{ 
						FormDetail.append("						<input type='radio' name='" +sla.getENName() + "' value='"+valArr[j]+"'> "+valArr[j]);
					}
					FormDetail.append("					</label>");
				}
				else if(componentsType.equalsIgnoreCase(ConstValue.COMPONENT_TYPE_CHECK))
				{
					FormDetail.append("					<label class='radio-inline'>");
					
					String [] valArr = values.split(ConstValue.VALUE_SPLITTER);
					
					for(int j=0;j<valArr.length ;j++ ) 
					{ 
						FormDetail.append("					<input type='checkbox' name='" +sla.getENName() + "' value='"+valArr[j]+"'> "+valArr[j]);
					}
					FormDetail.append(" 				</label>");
				}
				else if(componentsType.equalsIgnoreCase(ConstValue.COMPONENT_TYPE_FILE) || componentsType.equalsIgnoreCase(ConstValue.COMPONENT_TYPE_IMAGE))
				{
					FormDetail.append("<div id='"+sla.getENName() +"div' class='wu-example'>");
					FormDetail.append("<input type='text' class='form-control field' name='" +sla.getENName() + "' id='" +sla.getENName() + "' style='display:none;'>");
					FormDetail.append("<table class='table' id='"+sla.getENName() +"picktable'><thead><tr><th>名称</th><th>状态</th><th>操作</th></tr></thead><tbody></tbody></table>");
					FormDetail.append("<div class='btns'>");
					FormDetail.append("<div id='" + sla.getENName() + "pick'>选择文件</div>");
					FormDetail.append("</div>");
					FormDetail.append("</div>");
				}
				
				/*if(sla.getSupportQuery() != null && sla.getSupportQuery().equalsIgnoreCase("是"))
				{
					QueryForm.append("						<label class='col-md-1 control-label'>"+sla.getZHName()+"</label>");
					QueryForm.append("<div class='col-sm-2'>");
					if(componentsType.equalsIgnoreCase(ConstValue.COMPONENT_TYPE_DROPDOWN))
					{
						QueryForm.append("						<select class='form-control field' name='" +sla.getENName() + "Query' id='" +sla.getENName() + "Query'>");
						
						QueryForm.append(" 						<option value=''>全部</option>");
						
						String [] valArr = values.split(ConstValue.VALUE_SPLITTER);
						
						for(int j=0;j<valArr.length ;j++ ) 
						{ 
							QueryForm.append(" 						<option value='"+valArr[j]+"'>"+valArr[j]+"</option>");
						}
						QueryForm.append("						</select>");
						
					}
					else// if(componentsType.equalsIgnoreCase(ConstValue.COMPONENT_TYPE_DROPDOWN))
					{
						
						QueryForm.append(" 					<input type='text' class='form-control' id='"+sla.getENName()+"Query'>");
					}	
					QueryForm.append("</div>");
				}*/
				
				FormDetail.append(" 						</div>");
				FormDetail.append(" 					</div>");
				
				

				if(componentsType.equalsIgnoreCase(ConstValue.COMPONENT_TYPE_FILE) || componentsType.equalsIgnoreCase(ConstValue.COMPONENT_TYPE_IMAGE))
				{
					InitScript.append("var uploader"+sla.getENName() + " = WebUploader.create({");
					InitScript.append("	auto: true,");
					 
					InitScript.append("// swf文件路径");
					InitScript.append("//swf: BASE_URL + '/js/Uploader.swf',");
						
					InitScript.append("// 文件接收服务端");
					InitScript.append("server: getContextPath()+'/fileController/upload',");
					 
					InitScript.append("// 选择文件的按钮。可选。");
					InitScript.append("// 内部根据当前运行是创建，可能是input元素，也可能是flash.");
					InitScript.append("pick: '#"+sla.getENName() + "pick',");
					 
					//InitScript.append("// 只允许选择图片文件。");
					//InitScript.append("/*accept: {");
					//InitScript.append("							title: 'Images',");
					//InitScript.append("extensions: 'gif,jpg,jpeg,bmp,png',");
					//InitScript.append("mimeTypes: 'image/*'");
					//InitScript.append("},*/");
					InitScript.append("formData:{");
					InitScript.append("attributeName:'"+sla.getENName()+"'");
					InitScript.append("}");
					InitScript.append("});");
					
					InitScript.append("uploader"+sla.getENName() + ".on( 'fileQueued', function( file ) {");
						
					InitScript.append("	\\$('#'+file.source._refer[0].id+'table').append('<tr><td>'+file.name+'</td><td id='+file.id+'>等待上传...</td>'+");
					InitScript.append("						\"<td><button type='button' id='\"+file.id+\"Button' class='btn btn-success btn-xs' onclick='javascript:downloadAttach();return false;'><i class='fa fa-check'></i></button></td>\"+");
					InitScript.append("						'</tr>');");
					InitScript.append("});");
	
					InitScript.append("uploader"+sla.getENName() + ".on( 'uploadSuccess', function( file,response ) {");
						
					InitScript.append("	\\$( '#'+response.attributeName ).val(response.fileName+VALUE_SPLITTER+\\$('#'+response.attributeName ).val());");
						
					InitScript.append("	console.log(\\$( '#'+response.attributeName ).val());");
						
					InitScript.append("	\\$( '#'+file.id ).text('已上传');");
					InitScript.append("	\\$('#'+file.id+'Button').attr('onclick', onclick='javascript:downloadAttach(\"'+response.fileName+'\");return false;');"); 
						
					InitScript.append("});");
	
					InitScript.append("uploader"+sla.getENName() + ".on( 'uploadError', function( file,response ) {");
												
					InitScript.append("});	");
				}
			}
			
			//sTableColumn += "<th style='text-align:center;'>操作</th>";
			
			try
			{  
				ClassPathResource r = new ClassPathResource("templateDetail.html");
				
				String sTpl = FileUtils.readFileToString(r.getFile(),"utf-8");
				
				sTpl = sTpl.replaceAll("\\{FirstLower\\}", sFirstLower)
							.replaceAll("\\{FirstUpper\\}", sFirstUpper)
							//.replaceAll("\\{QueryForm\\}", QueryForm.toString())
							//.replaceAll("\\{TableColumn\\}", sTableColumn)
							.replaceAll("\\{InitScript\\}", InitScript.toString())
							.replaceAll("\\{FormDetail\\}",FormDetail.toString());
				
				serviceStringBuffer.append(sTpl);
			}
			catch(Exception e)
            {
            	logger.error(e.getMessage(),e);
            }
			
			FileUtil.saveFileFromStringBuffer(serviceStringBuffer.toString(), "c://temp/"+sFirstLower, sFirstLower+"Detail.html");
		}
		catch(Exception e)
		{
			logger.error(e.getMessage(),e);
			return e.getMessage();
		}
		
		return "";
	}
	
	private static String toUpperCaseFirstOne(String s){
		  if(Character.isUpperCase(s.charAt(0)))
		    return s;
		  else
		    return (new StringBuilder()).append(Character.toUpperCase(s.charAt(0))).append(s.substring(1)).toString();
		}
	
	
	private static String toLowerCaseFirstOne(String s){
		  if(Character.isLowerCase(s.charAt(0)))
		    return s;
		  else
		    return (new StringBuilder()).append(Character.toLowerCase(s.charAt(0))).append(s.substring(1)).toString();
		}
	
	
	
	class MyStringBuffer
	{
		 public StringBuffer strBuf = new StringBuffer();
		 
		 public MyStringBuffer append(String val)
		 {
			 strBuf.append(val).append("\r\n");
			 
			 return this;
		 }
		 
		 public String toString()
		 {
			return strBuf.toString();
		 }
		 
	}
}

