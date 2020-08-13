package com.template.util;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.mysql.cj.util.StringUtils;

import sun.misc.BASE64Decoder;

public class Base64ToJPG{
	
	private static Log log = LogFactory.getLog(Base64ToJPG.class);
	
	public static String base642Jpg(String base64, String filePath) {
		if(StringUtils.isNullOrEmpty(base64)) {
			return null;
		}
		base64 = base64.replace(" ", "+");
		ByteArrayInputStream in = null;
		FileOutputStream out = null;
		try {
			
			byte[] buf = new BASE64Decoder().decodeBuffer(base64);
			in = new ByteArrayInputStream(buf);
			out = new FileOutputStream(new File(filePath));
			int bytes = 0;
			byte[] bufferOut = new byte[1024];
			while ((bytes = in.read(bufferOut)) != -1) {
				out.write(bufferOut, 0, bytes);
			}
			out.flush();
			out.close();
			
			return filePath;
			
		} catch (Exception e) {
			//logger.error("上传base64图片保存本地异常",e);
			e.printStackTrace();
			log.error("base64toJpg", e.getCause());
			
			return null;
		}
	}
}
