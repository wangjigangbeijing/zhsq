package com.template.util;

/*
	1.Execute a GET against the target URL.
	2.Receive 401. Look at location: header.
	3.Do POST to that location. Include the username and password in body of that POST.
	4.Examine location header of the response. This is your session resource.
	5.Do a POST to the session resource location. The body of the POST must contain the URL you want to use.
	6.Take the contents of the response body, and add it as a query string argument to the URL you are trying to get.
	7.Execute the original GET request. Append the query string parameter ?ticket=. Include the cookies you are given.
	8.For additional requests, always resend the cookies received in step 7.
 */
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.codec.binary.Base64;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.apache.http.entity.StringEntity;

public class RestClient 
{
	private static Logger logger = Logger.getLogger(RestClient.class);
	
	private DefaultHttpClient m_Httpclient = new DefaultHttpClient();
	
	public RestClient()
	{
	}
	
	public String getUrlResult(String _sUrl,String _sUser,String _sPassword)
	{
		logger.info("GET :"+_sUrl);
		HttpGet getMethod = new HttpGet(_sUrl);
        
		String sRespMsg = "";
		
		try {

			String auth = _sUser + ":"+ _sPassword;

			byte[] encodedAuth = Base64.encodeBase64(auth.getBytes(Charset.forName("ISO-8859-1")));

			String authHeader = "Basic "  + new
			String(encodedAuth);

			getMethod.setHeader(HttpHeaders.AUTHORIZATION, authHeader);
			
			
			HttpResponse httpResponse = m_Httpclient.execute(getMethod);
			int statusCode = httpResponse.getStatusLine().getStatusCode(); 
			
			HttpEntity entity = httpResponse.getEntity();             //
			
			if (null != entity) { 
	            
	            try {
	            	sRespMsg = EntityUtils.toString(entity, "UTF-8");
	            }
	            catch(Exception e)
	            {
	            	e.printStackTrace();
	            }
	        }
			if(statusCode < 200 || statusCode >= 300)
			{
				logger.error("error code "+statusCode+":"+httpResponse.getStatusLine().getReasonPhrase()+" returned from url:"+_sUrl);
				return "";
			}
		} catch (ClientProtocolException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
        return sRespMsg;
	}
	

	public String getUrlResult(String _sUrl,String token)
	{
		logger.info("GET :"+_sUrl);
		HttpGet getMethod = new HttpGet(_sUrl);
        
		String sRespMsg = "";
		
		try {

			getMethod.setHeader("token", token);
			
			HttpResponse httpResponse = m_Httpclient.execute(getMethod);
			int statusCode = httpResponse.getStatusLine().getStatusCode(); 
			
			HttpEntity entity = httpResponse.getEntity();             //
			
			if (null != entity) { 
	            
	            try {
	            	sRespMsg = EntityUtils.toString(entity, "UTF-8");
	            }
	            catch(Exception e)
	            {
	            	e.printStackTrace();
	            }
	        }
			if(statusCode < 200 || statusCode >= 300)
			{
				logger.error("error code "+statusCode+":"+httpResponse.getStatusLine().getReasonPhrase()+" returned from url:"+_sUrl);
				return "";
			}
		} catch (ClientProtocolException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
        return sRespMsg;
	}
	
	public String post(String _sUrl,Map<String, String> params)
	{
		logger.info("POST:"+_sUrl);
		HttpPost methodPost = new HttpPost(_sUrl);
        
		String sRespMsg = "";
		
		try {
			

			//byte[] encodedAuth = Base64.encodeBase64(auth.getBytes(Charset.forName("ISO-8859-1")));

			//String authHeader = "Basic "  + new
			//String(encodedAuth);

			//methodPost.setHeader(HttpHeaders.AUTHORIZATION, authHeader);
			
			List<NameValuePair> nvps = new ArrayList<NameValuePair>();
            Set<String> keySet = params.keySet();
            for (String key : keySet) {
                nvps.add(new BasicNameValuePair(key, params.get(key)));
            }
            methodPost.setEntity(new UrlEncodedFormEntity(nvps, "utf-8"));
					
			//StringEntity requestEntity = new StringEntity(_sBody ,"UTF-8");  
			
			//methodPost.setEntity(requestEntity);
			
			//methodPost.addHeader("Content-Type", "application/vnd.emc.apollo-v1+xml");
			HttpResponse httpResponse = m_Httpclient.execute(methodPost);
			int statusCode = httpResponse.getStatusLine().getStatusCode(); 
			
			logger.info(statusCode);
			HttpEntity entity = httpResponse.getEntity();             
			
			if (null != entity) { 
	            
	            try {
	            	sRespMsg = EntityUtils.toString(entity, "UTF-8");
	            	
	            	logger.debug(sRespMsg);
	            }
	            catch(Exception e)
	            {
	            	logger.error(e.getMessage(),e);
	            }
	        }
			if(statusCode < 200 || statusCode >= 300)
			{
				logger.error("error code "+statusCode+":"+httpResponse.getStatusLine().getReasonPhrase()+" returned from url:"+_sUrl);
				return "";
			}
			
		} catch (ClientProtocolException e1) {
			e1.printStackTrace();
			logger.error(e1.getMessage(),e1);
		} catch (IOException e1) {
			logger.error(e1.getMessage(),e1);
		}
        return sRespMsg;
	}
	
	public static void main(String[] args) {


		
		RestClient rcLogin = new RestClient();
		
		Map<String, String> params = new HashMap<>();
		params.put("username","nicai");
		params.put("password","046cbc223b97b1f24649958da2cfb311");
		
		String loginResp = rcLogin.post("http://www.weixineasy.com/restful/login",params);
		
		JSONObject jsonLoginResp;
		try {
			jsonLoginResp = new JSONObject(loginResp);
			

			String token = jsonLoginResp.getString("token");

			Date yesterday = TimeUtil.getYesterdayDate(new Date());
			
			Date today = new Date();
			
			long yesterdaySec = yesterday.getTime()/1000-1599517000;
			
			long todaySec = today.getTime()/1000;
			
			String articlesResp = rcLogin.getUrlResult("http://www.weixineasy.com/restful/articles/0/"+yesterdaySec+"/"+todaySec,token);
			
			System.out.println("http://www.weixineasy.com/restful/articles/0/"+yesterdaySec+"/"+todaySec);
			
			System.out.println(articlesResp);
			
			JSONArray jsonArticlesResp = new JSONArray(articlesResp);
			
			for(int i=0;i<jsonArticlesResp.length();i++)
			{
				JSONObject jsonTmp = jsonArticlesResp.getJSONObject(i);
				
				String sendTime = jsonTmp.getString("createtime");
				String link = jsonTmp.getString("link");
				String linkurl = jsonTmp.getString("linkurl");//如果是电话则tel开头
				String pcatename = jsonTmp.getString("pcatename"); //栏目
				
				
				
				
				
				
				
				/*JSONArray jsonSubArticles = jsonTmp.getJSONArray("children");
				
				for(int j=0;j<jsonSubArticles.length();j++)
				{
					JSONObject jsonTmpSubArticle = jsonSubArticles.getJSONObject(j);
					
					String title = jsonTmpSubArticle.getString("title");
					String url = jsonTmpSubArticle.getString("url");
					
					System.out.println(sendTime+title+url);
					
					
				}*/
				
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
}
