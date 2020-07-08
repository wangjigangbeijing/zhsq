package com.template.init;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;

import com.template.util.ConstValue;

public class Initializer extends HttpServlet
{
	public static Logger logger = Logger.getLogger(Initializer.class);
	
	static
	{
		//String sFile = "./conf/portal.properties";
		//ConfigManager confMng = ConfigManager.getInstance(sFile);
	}
	
	public void init() throws ServletException 
	{
		DOMConfigurator.configure(Initializer.class.getClassLoader().getResource("log4j.xml"));
			
		logger.info("taskScheduler startup Successfully...");
	}
	
	public void destroy(){
		logger.info("Destroying portal...");
	}
}
