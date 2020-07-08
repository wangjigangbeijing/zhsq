package com.template.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@Table(name = "sys_user")
@DynamicInsert(true)
@DynamicUpdate(true)
public class SysUser implements java.io.Serializable {
	
	@Id
	@Column(name = "id", nullable = false)
	public String id;
	
	public String getId()
	{
		return id;
	}
	public void setId(String id)
	{
		this.id = id;
	}
	
	@Column(name = "LOGIN_ID", nullable = false)
	public String loginId;
	
	public String getLoginId()
	{
		return loginId;
	}
	public void setLoginId(String loginId)
	{
		this.loginId = loginId;
	}
	
	@Column(name = "USER_NAME", nullable = false)
	public String username;
	
	public String getUsername()
	{
		return username;
	}
	public void setUsername(String username)
	{
		this.username = username;
	}
	
	@Column(name = "password", nullable = true)
	public String password;
	
	public String getPassword()
	{
		return password;
	}
	public void setPassword(String password)
	{
		this.password = password;
	}
	
	@Column(name = "version", nullable = true)
	public String version;
	
	public String getVersion()
	{
		return version;
	}
	public void setVersion(String version)
	{
		this.version = version;
	}
	
	@Column(name = "mobile", nullable = true)
	public String mobile;
	
	public String getMobile()
	{
		return mobile;
	}
	public void setMobile(String mobile)
	{
		this.mobile = mobile;
	}
	
	@Column(name = "status", nullable = true)
	public String status;
	
	public String getStatus()
	{
		return status;
	}
	public void setStatus(String status)
	{
		this.status = status;
	}

	/*@Column(name = "usertype", nullable = true)
	public Integer usertype;
	
	public Integer getUsertype()
	{
		return usertype;
	}
	public void setUsertype(Integer usertype)
	{
		this.usertype = usertype;
	}*/
}
