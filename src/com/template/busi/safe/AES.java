package com.template.busi.safe;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Arrays;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;


public class AES {

	private static final String KEY_ALGORITHM = "AES";
    private static final String DEFAULT_CIPHER_ALGORITHM = "AES/ECB/PKCS5Padding";//默认的加密算法
 
    /**
     * AES 加密操作
     *
     * @param content 待加密内容
     * @param key 加密密码
     * @return 返回Base64转码后的加密数据
     */
    public static String encrypt(String content) {
        try {
            Cipher cipher = Cipher.getInstance(DEFAULT_CIPHER_ALGORITHM);// 创建密码器
 
            byte[] byteContent = content.getBytes("utf-8");
 
            cipher.init(Cipher.ENCRYPT_MODE, getSecretKey(KeyConstants.aes_key));// 初始化为加密模式的密码器
 
            byte[] result = cipher.doFinal(byteContent);// 加密
 
            return org.apache.commons.codec.binary.Base64.encodeBase64String(result);//通过Base64转码返回
        } catch (Exception ex) {
            ex.printStackTrace();
        }
 
        return null;
    }
 
    /**
     * AES 解密操作
     *
     * @param content
     * @param key
     * @return
     */
    public static String decrypt(String content) {
 
        try {
            //实例化
            Cipher cipher = Cipher.getInstance(DEFAULT_CIPHER_ALGORITHM);
 
            //使用密钥初始化，设置为解密模式
            cipher.init(Cipher.DECRYPT_MODE, getSecretKey(KeyConstants.aes_key));
 
            //执行操作
            byte[] result = cipher.doFinal(org.apache.commons.codec.binary.Base64.decodeBase64(content));
 
            return new String(result, "utf-8");
        } catch (Exception ex) {
            //ex.printStackTrace();
        	System.out.println("解密异常");
        }
 
        return null;
    }
 
    /**
     * 生成加密秘钥
     *
     * @return
     * @throws UnsupportedEncodingException
     */
    private static SecretKeySpec getSecretKey(final String key) throws UnsupportedEncodingException {
        //返回生成指定算法密钥生成器的 KeyGenerator 对象
        KeyGenerator kg = null;
 
        try {
            kg = KeyGenerator.getInstance(KEY_ALGORITHM);
 
            //AES 要求密钥长度为 128
            kg.init(128, new SecureRandom(key.getBytes()));
 
            //生成一个密钥
            SecretKey secretKey = kg.generateKey();
 
            return new SecretKeySpec(Arrays.copyOf(key.getBytes("utf-8"), 16), KEY_ALGORITHM);// 转换为AES专用密钥
        } catch (NoSuchAlgorithmException ex) {
            ex.printStackTrace();
        }
 
        return null;
    }
 
    public static void main(String[] args) throws Exception{
       String encryptionText = "8wFaF4lWiMYit/bL83pnlA==";
 
        System.out.println("解密(decrypt):"+AES.decrypt(encryptionText));
 
    }

}
