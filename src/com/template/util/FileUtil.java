package com.template.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.log4j.Logger;


public class FileUtil {
	
	private static Logger logger = Logger.getLogger(FileUtil.class);
	
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
	
	
	
	public static boolean saveFileFromStringBuffer(String stream, String dirPath,  
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
		    
		    fs.write(stream.getBytes("utf-8"));  
	        fs.flush();  
		}
		catch(Exception e)
		{
			logger.error(e.getMessage(),e);
		}
		finally
		{
		    if(fs != null)fs.close();  
		}
	    
	    return true;  
	} 
}
