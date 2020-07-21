package com.template.util;

import java.security.Key;
import java.security.spec.AlgorithmParameterSpec;
 


import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
 


import org.apache.commons.lang3.StringUtils;
 

import org.apache.log4j.Logger;


import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;
 
/**
 * 加密/解密工具
 */
public class EncryptUtil {
	
	private static Logger logger = Logger.getLogger(EncryptUtil.class);
 
	private static String sKey = "9ba45bfd500642328ec03ad8ef1b6e75";
	
	private final byte[] DESIV = new byte[] { 0x12, 0x34, 0x56, 120, (byte) 0x90, (byte) 0xab, (byte) 0xcd, (byte) 0xef };// 向量
 
	private AlgorithmParameterSpec iv = null;// 加密算法的参数接口
	private Key key = null;
	
	private String charset = "utf-8";
	
	private static EncryptUtil encryptUtil = null;
	
	public static String encodeStr(String data)
	{
		if(encryptUtil == null)
		{
			try {
				encryptUtil = new EncryptUtil(sKey,"utf-8");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return encryptUtil.encode(data);
	}
	
	public static String decodeStr(String data)
	{
		if(encryptUtil == null)
		{
			try {
				encryptUtil = new EncryptUtil(sKey,"utf-8");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return encryptUtil.decode(data);
	}
	/**
	 * 初始化
	 * @param deSkey	密钥
	 * @throws Exception
	 */
	public EncryptUtil(String deSkey, String charset){
		if (StringUtils.isNotBlank(charset)) {
			this.charset = charset;
		}
		
		try
		{
			DESKeySpec keySpec = new DESKeySpec(deSkey.getBytes(this.charset));// 设置密钥参数
			iv = new IvParameterSpec(DESIV);// 设置向量
			SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");// 获得密钥工厂
			key = keyFactory.generateSecret(keySpec);// 得到密钥对象
		}
		catch(Exception e)
		{
			
		}
	}
	
	/**
	 * 加密
	 * @date 2017年4月19日 上午9:40:53
	 * @param data
	 * @return
	 * @throws Exception
	 */
	public String encode(String data){
		try
		{
			Cipher enCipher = Cipher.getInstance("DES/CBC/PKCS5Padding");// 得到加密对象Cipher
			enCipher.init(Cipher.ENCRYPT_MODE, key, iv);// 设置工作模式为加密模式，给出密钥和向量
			byte[] pasByte = enCipher.doFinal(data.getBytes("utf-8"));
			BASE64Encoder base64Encoder = new BASE64Encoder();
			return base64Encoder.encode(pasByte);
		}
		catch(Exception e)
		{
			return "";
		}
	}
	
	
	
	/**
	 * 解密
	 * @author ershuai
	 * @date 2017年4月19日 上午9:41:01
	 * @param data
	 * @return
	 * @throws Exception
	 */
	public String decode(String data){
		try
		{
			Cipher deCipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
			deCipher.init(Cipher.DECRYPT_MODE, key, iv);
			BASE64Decoder base64Decoder = new BASE64Decoder();
			byte[] pasByte = deCipher.doFinal(base64Decoder.decodeBuffer(data));
			return new String(pasByte, "UTF-8");
		}
		catch(Exception e)
		{
			return "";
		}
	}
	
	public static void main(String[] args) {
		try {
			
			//System.out.println("加密前的字符：" + test);
			//System.out.println("加密后的字符：" + des.encode(test));
			//System.out.println("解密后的字符：" + des.decode(des.encode(test)));
			
			
			/*
			for(int i=0;i<10;i++)
			{
				String test = "5~"+Utility.getUniStrTime();
				String key = "9ba45bfd500642328ec03ad8ef1b6e75";// 自定义密钥
				EncryptUtil des = new EncryptUtil(key, "utf-8");
				//final byte[] textByte = des.encode(test).getBytes("UTF-8");
				
				String encoded = des.encode(test);
				String decoded = des.decode(encoded);
				
				System.out.println(test +"			"+ encoded+"			"+ decoded);
			}
			*/
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}