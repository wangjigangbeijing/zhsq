package com.template.model.jcsqsj;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import java.util.Date;


@Entity
@Table(name = "JC_COMMUNITY")
@DynamicInsert(true)
@DynamicUpdate(true)
public class Community implements java.io.Serializable {
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
@Column(name = "buildings", nullable = true)
public Integer buildings;
public Integer getbuildings()
{
	return buildings;
}
public void setbuildings(Integer buildings)
{
	this.buildings = buildings;
}
@Column(name = "gates", nullable = true)
public Integer gates;
public Integer getgates()
{
	return gates;
}
public void setgates(Integer gates)
{
	this.gates = gates;
}
@Column(name = "groundparking", nullable = true)
public Integer groundparking;
public Integer getgroundparking()
{
	return groundparking;
}
public void setgroundparking(Integer groundparking)
{
	this.groundparking = groundparking;
}
@Column(name = "underparking", nullable = true)
public Integer underparking;
public Integer getunderparking()
{
	return underparking;
}
public void setunderparking(Integer underparking)
{
	this.underparking = underparking;
}
/*@Column(name = "longitude", nullable = true)
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
}/*
@Column(name = "duoxuan", nullable = true)
public String duoxuan;
public String getduoxuan()
{
	return duoxuan;
}
public void setduoxuan(String duoxuan)
{
	this.duoxuan = duoxuan;
}*/
}

