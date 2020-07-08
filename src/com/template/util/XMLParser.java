package com.template.util;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class XMLParser {
	
	
	
	
	
	
	public static void main(String[] args) {
		
		
		String sResp = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>"
				+ "<wfs:WFS_TransactionResponse version=\"1.0.0\" xmlns:wfs=\"http://www.opengis.net/wfs\" "
				+ "xmlns:ogc=\"http://www.opengis.net/ogc\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" "
				+ "xsi:schemaLocation=\"http://www.opengis.net/wfs http://47.104.203.88:8082/gserver/schemas/wfs/1.0.0/WFS-transaction.xsd\">"
				+ "<wfs:InsertResult><ogc:FeatureId fid=\"new_point.2\"/></wfs:InsertResult> <wfs:TransactionResult> <wfs:Status> "
				+ "<wfs:SUCCESS/> </wfs:Status> </wfs:TransactionResult></wfs:WFS_TransactionResponse>";
		
		try
			{
			
			InputStream is = new ByteArrayInputStream(sResp.getBytes());
			
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();//得到创建 DOM 解析器的工厂。  
		    DocumentBuilder builder = factory.newDocumentBuilder();//得到 DOM 解析器对象。  
		    Document document = builder.parse(is);
		    
		    
		    NodeList list = document.getElementsByTagName("ogc:FeatureId");
		    
		        Node node = list.item(0);  
		        
		       NamedNodeMap map = node.getAttributes();
		       
		       Node item = map.getNamedItem("fid");
		       
		       System.out.println(item.getNodeValue());
		       
		       
		       
		       String regEx = ".*\\.([0-9]*)";
		       // 编译正则表达式
		       Pattern pattern = Pattern.compile(regEx);
		       // 忽略大小写的写法
		       // Pattern pat = Pattern.compile(regEx, Pattern.CASE_INSENSITIVE);
		       Matcher matcher = pattern.matcher(item.getNodeValue());
		       // 字符串是否与正则表达式相匹配
		       boolean rs = matcher.matches();
		       System.out.println(rs);
		       
		       System.out.println(matcher.group(1));
		        
			}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}
	
	

}
