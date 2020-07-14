package com.template.model.jcsqsj;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import java.util.Date;


@Entity
@Table(name = "JC_RESIDEBUILDING")
@DynamicInsert(true)
@DynamicUpdate(true)
public class Residebuilding implements java.io.Serializable {
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
@Column(name = "year", nullable = true)
public Date year;
public Date getyear()
{
	return year;
}
public void setyear(Date year)
{
	this.year = year;
}
@Column(name = "propertyyears", nullable = true)
public String propertyyears;
public String getpropertyyears()
{
	return propertyyears;
}
public void setpropertyyears(String propertyyears)
{
	this.propertyyears = propertyyears;
}
@Column(name = "propertyrights", nullable = true)
public String propertyrights;
public String getpropertyrights()
{
	return propertyrights;
}
public void setpropertyrights(String propertyrights)
{
	this.propertyrights = propertyrights;
}
@Column(name = "heatingsystem", nullable = true)
public String heatingsystem;
public String getheatingsystem()
{
	return heatingsystem;
}
public void setheatingsystem(String heatingsystem)
{
	this.heatingsystem = heatingsystem;
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
@Column(name = "buildtype", nullable = true)
public String buildtype;
public String getbuildtype()
{
	return buildtype;
}
public void setbuildtype(String buildtype)
{
	this.buildtype = buildtype;
}
@Column(name = "buildframework", nullable = true)
public String buildframework;
public String getbuildframework()
{
	return buildframework;
}
public void setbuildframework(String buildframework)
{
	this.buildframework = buildframework;
}
@Column(name = "constructiontype", nullable = true)
public String constructiontype;
public String getconstructiontype()
{
	return constructiontype;
}
public void setconstructiontype(String constructiontype)
{
	this.constructiontype = constructiontype;
}
@Column(name = "units", nullable = true)
public String units;
public String getunits()
{
	return units;
}
public void setunits(String units)
{
	this.units = units;
}
@Column(name = "levels", nullable = true)
public Integer levels;
public Integer getlevels()
{
	return levels;
}
public void setlevels(Integer levels)
{
	this.levels = levels;
}
@Column(name = "elevators", nullable = true)
public Integer elevators;
public Integer getelevators()
{
	return elevators;
}
public void setelevators(Integer elevators)
{
	this.elevators = elevators;
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
@Column(name = "developer", nullable = true)
public String developer;
public String getdeveloper()
{
	return developer;
}
public void setdeveloper(String developer)
{
	this.developer = developer;
}
@Column(name = "propertyowner", nullable = true)
public String propertyowner;
public String getpropertyowner()
{
	return propertyowner;
}
public void setpropertyowner(String propertyowner)
{
	this.propertyowner = propertyowner;
}
@Column(name = "propertyownertel", nullable = true)
public String propertyownertel;
public String getpropertyownertel()
{
	return propertyownertel;
}
public void setpropertyownertel(String propertyownertel)
{
	this.propertyownertel = propertyownertel;
}
@Column(name = "user", nullable = true)
public String user;
public String getuser()
{
	return user;
}
public void setuser(String user)
{
	this.user = user;
}
@Column(name = "usertel", nullable = true)
public String usertel;
public String getusertel()
{
	return usertel;
}
public void setusertel(String usertel)
{
	this.usertel = usertel;
}
@Column(name = "propertymanage", nullable = true)
public String propertymanage;
public String getpropertymanage()
{
	return propertymanage;
}
public void setpropertymanage(String propertymanage)
{
	this.propertymanage = propertymanage;
}
@Column(name = "propertymanagecontact", nullable = true)
public String propertymanagecontact;
public String getpropertymanagecontact()
{
	return propertymanagecontact;
}
public void setpropertymanagecontact(String propertymanagecontact)
{
	this.propertymanagecontact = propertymanagecontact;
}
@Column(name = "propertymanagecontacttel", nullable = true)
public String propertymanagecontacttel;
public String getpropertymanagecontacttel()
{
	return propertymanagecontacttel;
}
public void setpropertymanagecontacttel(String propertymanagecontacttel)
{
	this.propertymanagecontacttel = propertymanagecontacttel;
}/*
@Column(name = "longitude", nullable = true)
public Integer longitude;
public Integer getlongitude()
{
	return longitude;
}
public void setlongitude(Integer longitude)
{
	this.longitude = longitude;
}
@Column(name = "latitude", nullable = true)
public Integer latitude;
public Integer getlatitude()
{
	return latitude;
}
public void setlatitude(Integer latitude)
{
	this.latitude = latitude;
}*/
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
@Column(name = "pictures", nullable = true)
public String pictures;
public String getpictures()
{
	return pictures;
}
public void setpictures(String pictures)
{
	this.pictures = pictures;
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
@Column(name = "familiesinbuilding", nullable = true)
public Integer familiesinbuilding;
public Integer getfamiliesinbuilding()
{
	return familiesinbuilding;
}
public void setfamiliesinbuilding(Integer familiesinbuilding)
{
	this.familiesinbuilding = familiesinbuilding;
}
}

