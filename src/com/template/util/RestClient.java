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
import org.apache.commons.codec.binary.Base64;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;
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
	
	public String postToRunReport(String _sUrl, String _sBody,String _sUser,String _sPassword)
	{
		logger.info("POST:"+_sUrl);
		HttpPost methodPost = new HttpPost(_sUrl);
        
		String sRespMsg = "";
		
		try {
			
			String auth = _sUser + ":"+ _sPassword;

			byte[] encodedAuth = Base64.encodeBase64(auth.getBytes(Charset.forName("ISO-8859-1")));

			String authHeader = "Basic "  + new
			String(encodedAuth);

			methodPost.setHeader(HttpHeaders.AUTHORIZATION, authHeader);
					
					
			StringEntity requestEntity = new StringEntity(_sBody ,"UTF-8");  
			
			methodPost.setEntity(requestEntity);
			
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
		//DOMConfigurator.configure("./config/log4j.xml");
		
		RestClient rc = new RestClient();
		
		String sPayload = "<wfs:Transaction service=\"WFS\" version=\"1.0.0\" "+
		  "xmlns:cdf=\"http://www.opengis.net/cite/data\" "+
		  "xmlns:ogc=\"http://www.opengis.net/ogc\" "+
		  "xmlns:wfs=\"http://www.opengis.net/wfs\" "+
		  "xmlns:topp=\"http://www.openplans.org/topp\"> "+
		  "<wfs:Delete typeName=\"geosolutions:cy_point\"> "+
		    "<ogc:Filter> "+
		     " <ogc:PropertyIsEqualTo> "+
		      "  <ogc:PropertyName>name</ogc:PropertyName> "+
		       " <ogc:Literal>ddddddd</ogc:Literal> "+
		      "</ogc:PropertyIsEqualTo> "+
		    "</ogc:Filter> "+
		  "</wfs:Delete> "+
		"</wfs:Transaction>";
		
		System.out.println(sPayload);
		
		rc.postToRunReport("http://localhost:8090/geoserver/wfs",sPayload,"","");
		
		
		
		
	}
}
