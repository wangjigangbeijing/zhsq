package com.template.filter;

import java.io.IOException;
import java.security.Principal;
import java.util.Collection;

import javax.servlet.Filter;  
import javax.servlet.FilterChain;  
import javax.servlet.FilterConfig;  
import javax.servlet.ServletException;  
import javax.servlet.ServletRequest;  
import javax.servlet.ServletResponse;  
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;  
import org.springframework.security.access.SecurityMetadataSource;  
import org.springframework.security.access.intercept.AbstractSecurityInterceptor;  
import org.springframework.security.access.intercept.InterceptorStatusToken;  
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.FilterInvocation;  
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;  
import org.springframework.web.context.request.RequestContextHolder;

import com.alibaba.fastjson.JSONObject;
import com.template.util.ConstValue;

public class SecurityFilter extends AbstractSecurityInterceptor implements Filter
{
	private static Logger logger = Logger.getLogger(SecurityFilter.class);
	// 与applicationContext-security.xml里的myFilter的属性securityMetadataSource对应，  
    // 其他的两个组件，已经在AbstractSecurityInterceptor定义  
    private FilterInvocationSecurityMetadataSource securityMetadataSource;  
  
    @Override  
    public SecurityMetadataSource obtainSecurityMetadataSource() {  
        return this.securityMetadataSource;  
    }  

    public void doFilter(ServletRequest request, ServletResponse response,  
            FilterChain chain) throws IOException, ServletException {  
        
        
    	SecurityContextHolder.getContext().getAuthentication().getName();
    	
    	HttpServletRequest httprequest    = (HttpServletRequest) request;
        String sUserId = (String)httprequest.getSession().getAttribute(ConstValue.SESSION_USER_ID);
        
        String sSource = httprequest.getHeader("source");//app
        
        if(sSource != null && sSource.equalsIgnoreCase("app"))
        {
        	sUserId  = httprequest.getHeader("userId");
        }
        
        String sToken = httprequest.getHeader("token");
        
        FilterInvocation fi = new FilterInvocation(request, response, chain);
        
        String sURL = fi.getRequestUrl();
        
        if(sSource != null && sSource.equalsIgnoreCase("") == false && sSource.equalsIgnoreCase("app")//APP来的请求
        		&& (sToken == null || sUserId == null))
        {
        	JSONObject jsonObj = new JSONObject();
        	jsonObj.put("success", false);
        	jsonObj.put("errMsg", "无效的token或者userId");
            
            response.getOutputStream().println(jsonObj.toString());
        }
        else if(sUserId == null)
       	{
        	logger.error("will redirect to homepage...");

        	throw new AccessDeniedException("没有权限访问或者session已经过期");    
       	}
        else
        {
        	invoke(fi);
        }
    }
  
    private void invoke(FilterInvocation fi) throws IOException,  
            ServletException {  
    	// object为FilterInvocation对象  
        super.beforeInvocation(fi);//源码  
        // 1.获取请求资源的权限  
        //执行
        Collection<ConfigAttribute> attributes =   
                        securityMetadataSource.getAttributes(fi);  
        // 2.是否拥有权限          
        String sAuth = fi.getHttpRequest().getHeader("Authorization"); 
        
        SecurityContext ctx=SecurityContextHolder.getContext();  
        
        
        /*
        User user = authenticate(fi.getHttpRequest());
        
        SecurityContextHolder.setContext(new Authorizer(user));
        */
        //Authentication authentication = new Authentication();
        //this.accessDecisionManager.decide(authentication, fi, attributes);  
        //this.accessDecisionManager.decide(authenticated, fi, attributes);  
        InterceptorStatusToken token = super.beforeInvocation(fi);  
        try {  
            fi.getChain().doFilter(fi.getRequest(), fi.getResponse());  
        } finally {  
            super.afterInvocation(token, null);  
        }  
    }  
  
    public FilterInvocationSecurityMetadataSource getSecurityMetadataSource() {  
        return securityMetadataSource;  
    }  
  
    public void setSecurityMetadataSource(  
            FilterInvocationSecurityMetadataSource securityMetadataSource) {  
        this.securityMetadataSource = securityMetadataSource;  
    }  
  
    public void init(FilterConfig arg0) throws ServletException {  
        // TODO Auto-generated method stub  
    }  
  
    public void destroy() {  
        // TODO Auto-generated method stub  
  
    }  
  
    @Override  
    public Class<? extends Object> getSecureObjectClass() {  
        //下面的MyAccessDecisionManager的supports方面必须放回true,否则会提醒类型错误    
        return FilterInvocation.class;  
    } 
    
    
    private User authenticate(HttpServletRequest request) {
    	
    	String sUserId = request.getHeader("Authorization"); 
    	
    	
    	//logger.debug(sUserId);
    	
    	/*
        // Extract authentication credentials
        String authentication = ((ContainerRequest) request).getHeaderString(HttpHeaders.AUTHORIZATION);
        if (authentication == null) {
            throw new AuthenticationException("Authentication credentials are required", REALM);
        }
        if (!authentication.startsWith("Basic ")) {
            return null;
            // additional checks should be done here
            // "Only HTTP Basic authentication is supported"
        }
        authentication = authentication.substring("Basic ".length());
        String[] values = Base64.decodeAsString(authentication).split(":");
        if (values.length < 2) {
            throw new WebApplicationException(400);
            // "Invalid syntax for username and password"
        }
        String username = values[0];
        String password = values[1];
        if ((username == null) || (password == null)) {
            throw new WebApplicationException(400);
            // "Missing username or password"
        }
    	 */
        // Validate the extracted credentials
        User user = new User(sUserId, "cargo");
        
        /*if (username.equals("a") && password.equals("a")) {
            user = new User("user", "user");
            System.out.println("USER AUTHENTICATED");
            //        } else if (username.equals("admin") && password.equals("adminadmin")) {
            //            user = new User("admin", "admin");
            //            System.out.println("ADMIN AUTHENTICATED");
        } else {
            System.out.println("USER NOT AUTHENTICATED");
            throw new AuthenticationException("Invalid username or password\r\n", REALM);
        }*/
        return user;
    }
	
    
    public class Authorizer implements SecurityContext {

        private User user;
        private Principal principal;

        public Authorizer(final User user) {
            this.user = user;
            this.principal = new Principal() {

                public String getName() {
                    return user.username;
                }
            };
        }

        public Principal getUserPrincipal() {
            return this.principal;
        }
        
        public String getUserName() {
       	 return user.username;
       }
        
        public String getUserRole() {
        	 return user.role;
        }

        public boolean isUserInRole(String role) {
            return (role.equals(user.role));
        }

		public Authentication getAuthentication() {
			// TODO Auto-generated method stub
			return null;
		}

		public void setAuthentication(Authentication arg0) {
			// TODO Auto-generated method stub
			
		}

        /*public boolean isSecure() {
            return "https".equals(uriInfo.get().getRequestUri().getScheme());
        }

        public String getAuthenticationScheme() {
            return SecurityContext.BASIC_AUTH;
        }*/
    }

    public class User {

        public String username;
        public String role;

        public User(String username, String role) {
            this.username = username;
            this.role = role;
        }
    }
}
