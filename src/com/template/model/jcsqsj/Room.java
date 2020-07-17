package com.template.model.jcsqsj;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import java.util.Date;


@Entity
@Table(name = "JC_ROOM")
@DynamicInsert(true)
@DynamicUpdate(true)
public class Room implements java.io.Serializable {
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
@Column(name = "dataid", nullable = true)
public String dataid;
public String getdataid()
{
	return dataid;
}
public void setdataid(String dataid)
{
	this.dataid = dataid;
}
@Column(name = "number", nullable = true)
public String number;
public String getnumber()
{
	return number;
}
public void setnumber(String number)
{
	this.number = number;
}
@Column(name = "ofcommunity", nullable = true)
public String ofcommunity;
public String getofcommunity()
{
	return ofcommunity;
}
public void setofcommunity(String ofcommunity)
{
	this.ofcommunity = ofcommunity;
}
@Column(name = "ofresidebuilding", nullable = true)
public String ofresidebuilding;
public String getofresidebuilding()
{
	return ofresidebuilding;
}
public void setofresidebuilding(String ofresidebuilding)
{
	this.ofresidebuilding = ofresidebuilding;
}
@Column(name = "ofunit", nullable = true)
public String ofunit;
public String getofunit()
{
	return ofunit;
}
public void setofunit(String ofunit)
{
	this.ofunit = ofunit;
}
@Column(name = "level", nullable = true)
public String level;
public String getlevel()
{
	return level;
}
public void setlevel(String level)
{
	this.level = level;
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
@Column(name = "isgrouporiented", nullable = true)
public String isgrouporiented;
public String getisgrouporiented()
{
	return isgrouporiented;
}
public void setisgrouporiented(String isgrouporiented)
{
	this.isgrouporiented = isgrouporiented;
}
@Column(name = "ownertype", nullable = true)
public String ownertype;
public String getownertype()
{
	return ownertype;
}
public void setownertype(String ownertype)
{
	this.ownertype = ownertype;
}
@Column(name = "propertypapertype", nullable = true)
public String propertypapertype;
public String getpropertypapertype()
{
	return propertypapertype;
}
public void setpropertypapertype(String propertypapertype)
{
	this.propertypapertype = propertypapertype;
}
@Column(name = "propertypaperid", nullable = true)
public String propertypaperid;
public String getpropertypaperid()
{
	return propertypaperid;
}
public void setpropertypaperid(String propertypaperid)
{
	this.propertypaperid = propertypaperid;
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
@Column(name = "propertymap", nullable = true)
public String propertymap;
public String getpropertymap()
{
	return propertymap;
}
public void setpropertymap(String propertymap)
{
	this.propertymap = propertymap;
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

	@Column(name = "peoplecharacteristics", nullable = true)
	public String peoplecharacteristics;
	public String getpeoplecharacteristics()
	{
		return peoplecharacteristics;
	}
	public void setpeoplecharacteristics(String peoplecharacteristics)
	{
		this.peoplecharacteristics = peoplecharacteristics;
	}
	
	
	@Column(name = "residentname", nullable = true)
	public String residentname;
	public String getresidentname()
	{
		return residentname;
	}
	public void setresidentname(String residentname)
	{
		this.residentname = residentname;
	}
}

