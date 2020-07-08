package com.template.filter;

import org.apache.log4j.Logger;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;


public class UserDetailServiceImpl implements UserDetailsService 
{
	private static Logger logger = Logger.getLogger(UserDetailServiceImpl.class);
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {
		
		logger.info("loadUserByUsername");
		
		return null;
	}

}
