package com.template.model;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import java.util.Date;


@Entity
@Table(name = "SYS_ORGANIZATION")
@DynamicInsert(true)
@DynamicUpdate(true)
public class SysOrganization implements java.io.Serializable {
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
@Column(name = "code", nullable = true)
public String code;
public String getcode()
{
	return code;
}
public void setcode(String code)
{
	this.code = code;
}
@Column(name = "type", nullable = true)
public String type;
public String gettype()
{
	return type;
}
public void settype(String type)
{
	this.type = type;
}
@Column(name = "border", nullable = true)
public String border;
public String getborder()
{
	return border;
}
public void setborder(String border)
{
	this.border = border;
}
@Column(name = "area", nullable = true)
public Integer area;
public Integer getarea()
{
	return area;
}
public void setarea(Integer area)
{
	this.area = area;
}
@Column(name = "address", nullable = true)
public String address;
public String getaddress()
{
	return address;
}
public void setaddress(String address)
{
	this.address = address;
}
@Column(name = "telphone", nullable = true)
public String telphone;
public String gettelphone()
{
	return telphone;
}
public void settelphone(String telphone)
{
	this.telphone = telphone;
}
@Column(name = "secretary", nullable = true)
public String secretary;
public String getsecretary()
{
	return secretary;
}
public void setsecretary(String secretary)
{
	this.secretary = secretary;
}
@Column(name = "secretaryphone", nullable = true)
public String secretaryphone;
public String getsecretaryphone()
{
	return secretaryphone;
}
public void setsecretaryphone(String secretaryphone)
{
	this.secretaryphone = secretaryphone;
}
@Column(name = "directorname", nullable = true)
public String directorname;
public String getdirectorname()
{
	return directorname;
}
public void setdirectorname(String directorname)
{
	this.directorname = directorname;
}
@Column(name = "directorphone", nullable = true)
public String directorphone;
public String getdirectorphone()
{
	return directorphone;
}
public void setdirectorphone(String directorphone)
{
	this.directorphone = directorphone;
}
@Column(name = "note", nullable = true)
public String note;
public String getnote()
{
	return note;
}
public void setnote(String note)
{
	this.note = note;
}
}
