package com.template.model;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import java.util.Date;


@Entity
@Table(name = "SYS_USER")
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
@Column(name = "name", nullable = true)
public String name;
public String getname()
{
	return name;
}
public void setname(String name)
{
	this.name = name;
}
@Column(name = "loginid", nullable = true)
public String loginid;
public String getloginid()
{
	return loginid;
}
public void setloginid(String loginid)
{
	this.loginid = loginid;
}
@Column(name = "password", nullable = true)
public String password;
public String getpassword()
{
	return password;
}
public void setpassword(String password)
{
	this.password = password;
}
@Column(name = "gender", nullable = true)
public String gender;
public String getgender()
{
	return gender;
}
public void setgender(String gender)
{
	this.gender = gender;
}
@Column(name = "birthday", nullable = true)
public Date birthday;
public Date getbirthday()
{
	return birthday;
}
public void setbirthday(Date birthday)
{
	this.birthday = birthday;
}
@Column(name = "joinday", nullable = true)
public Date joinday;
public Date getjoinday()
{
	return joinday;
}
public void setjoinday(Date joinday)
{
	this.joinday = joinday;
}
@Column(name = "mobile", nullable = true)
public String mobile;
public String getmobile()
{
	return mobile;
}
public void setmobile(String mobile)
{
	this.mobile = mobile;
}/*
@Column(name = "department", nullable = true)
public String department;
public String getdepartment()
{
	return department;
}
public void setdepartment(String department)
{
	this.department = department;
}*/
@Column(name = "job", nullable = true)
public String job;
public String getjob()
{
	return job;
}
public void setjob(String job)
{
	this.job = job;
}
@Column(name = "role", nullable = true)
public String role;
public String getrole()
{
	return role;
}
public void setrole(String role)
{
	this.role = role;
}
@Column(name = "status", nullable = true)
public String status;
public String getstatus()
{
	return status;
}
public void setstatus(String status)
{
	this.status = status;
}

@Column(name = "uorder", nullable = true)
public String uorder;
public String getuorder()
{
	return uorder;
}
public void setuorder(String uorder)
{
	this.uorder = uorder;
}

@Column(name = "owner", nullable = true)
public String owner;
public String getowner()
{
	return owner;
}
public void setowner(String owner)
{
	this.owner = owner;
}
}

