package com.template.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;


public class Utility 
{
	public static Logger logger = Logger.getLogger(Utility.class);
	
	public static Utility utility;
	
	public synchronized static Utility getInstance()
	{
		if(utility == null)
			utility = new Utility();
		
		return utility;
	}
	
	/*
	@desc	获得当前系统时间
	@return	返回的时间格式为：20050105090212	
	*/
	public synchronized static String getSystemTime()
	{
		Date measureDate = new Date();
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String sResult = format.format(measureDate);
		return sResult;
	}
	
	public synchronized static String getSystemTimeByMillSeconds(long lMill)
	{
		Calendar c = Calendar.getInstance();
		c.setTimeInMillis(lMill);
		Date measureDate = c.getTime();
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String sResult = format.format(measureDate);
		return sResult;
	}
	
	public synchronized static long getCurTimeInMills()
	{
		Date m_date = new Date();
		Calendar c = Calendar.getInstance();
		c.setTime(m_date);
		
		long lTimeInMillis = c.getTimeInMillis();
		
		return lTimeInMillis;
	}
	
	public static long lLastOne = 0;
	public static int iTail = 0;
	
	
	public synchronized static String getUniStr()
	{
		/*Date m_date = new Date();
		Calendar c = Calendar.getInstance();
		c.setTime(m_date);
		DateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
		String sResult = format.format(m_date);
		
		long curValue = c.getTimeInMillis()/10;
		
		if(lLastOne != curValue)
		{
			iTail = 0;
			lLastOne = curValue;
		}
		else if(iTail == 10)
		{
			try {
				Thread.currentThread().sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			lLastOne++;
			curValue++;
			iTail=0;
		}
		iTail++;
		String sTail = String.format("%02d", iTail);
		sResult += sTail;
		return sResult;*/
		return UUID.randomUUID().toString();
	}
	/*
	public synchronized static String getUniStr()
	{
		Date m_date = new Date();
		Calendar c = Calendar.getInstance();
		c.setTime(m_date);
		DateFormat format = new SimpleDateFormat("yyyyMMddHHmmssSS");
		String sResult = format.format(m_date);
		try {
			Thread.currentThread().sleep(2);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		return sResult;
	}*/
	public static double round(double value, int scale) {

		BigDecimal bd = new BigDecimal(value);

		bd = bd.setScale(scale,BigDecimal.ROUND_HALF_UP);

		double d = bd.doubleValue();

		bd = null;

		return d;

	}
	
	public synchronized static double mul(double v1, int v2) {
		BigDecimal b1 = new BigDecimal(Double.toString(v1));
		BigDecimal b2 = new BigDecimal(Integer.toString(v2));
		return b1.multiply(b2).doubleValue();
	}
	public synchronized static double mul(double v1, double v2) {
		BigDecimal b1 = new BigDecimal(Double.toString(v1));
		BigDecimal b2 = new BigDecimal(Double.toString(v2));
		return b1.multiply(b2).doubleValue();
	}
	public static boolean isNullOrEmpty(Object obj) {
		if (obj instanceof Object[]) {
			Object[] o = (Object[]) obj;
			for (int i = 0; i < o.length; i++) {
				Object object = o[i];
				if(object instanceof Date){
					if(object.equals(new Date(0))) return true;
				}else  if ((object == null) || (("").equals(object))) {
					return true;
				}
			}
		} else {
			if(obj instanceof Date){
				if(obj.equals(new Date(0))){
					return true;
				}
			}else if ((obj == null) || (("").equals(obj))) {
				return true;
			}
		}

		return false;
	}
	
	
	public static ArrayList<Integer> getRandomNumber(int iMax,int iCount)
	{
		ArrayList<Integer> alRandom = new ArrayList<Integer>();
		
		while(alRandom.size()<iCount)
		{
			Random iRandom = new java.util.Random();
			
			int iNumber = Math.abs(iRandom.nextInt() % iMax);;
			
			if(alRandom.contains(iNumber) == false)
				alRandom.add(iNumber);
		}
		return alRandom;
	}
	

	public static boolean saveFileFromInputStream(InputStream stream, String dirPath,  
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
	
	

	@Autowired
	private HttpServletRequest request;
	
	public String getOrganization()
	{
		String organization = "";
		
		try
		{
			//String sSource = request.getHeader(ConstValue.HTTP_HEADER_SOURCE);//app
			
			String userId = request.getHeader(ConstValue.HTTP_HEADER_USERID);
			
			if(userId == null || userId.equalsIgnoreCase(""))
				userId = (String)request.getSession().getAttribute(ConstValue.SESSION_USER_ID);
			
			//String organization = "";
			if(ConstValue.userToOrgMap.containsKey(userId))
				organization = ConstValue.userToOrgMap.get(userId);
		}
		catch(Exception e)
		{
			logger.error(e.getMessage(),e);
		}
		
		try
		{
			if(organization.equalsIgnoreCase("") && request.getSession().getAttribute(ConstValue.SESSION_USER_ORG) != null)
			{	
				organization = (String)request.getSession().getAttribute(ConstValue.SESSION_USER_ORG);
			}
		}
		catch(Exception e)
		{
			logger.error(e.getMessage(),e);
		}
		
		return organization;
	}
	
	
	
}
