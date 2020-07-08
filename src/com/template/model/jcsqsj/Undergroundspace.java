package com.template.model.jcsqsj;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import java.util.Date;


@Entity
@Table(name = "JC_UNDERGROUNDSPACE")
@DynamicInsert(true)
@DynamicUpdate(true)
public class Undergroundspace implements java.io.Serializable {
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
@Column(name = "dateid", nullable = true)
public String dateid;
public String getdateid()
{
	return dateid;
}
public void setdateid(String dateid)
{
	this.dateid = dateid;
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
@Column(name = "ofbizbuilding", nullable = true)
public String ofbizbuilding;
public String getofbizbuilding()
{
	return ofbizbuilding;
}
public void setofbizbuilding(String ofbizbuilding)
{
	this.ofbizbuilding = ofbizbuilding;
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
@Column(name = "purpose", nullable = true)
public String purpose;
public String getpurpose()
{
	return purpose;
}
public void setpurpose(String purpose)
{
	this.purpose = purpose;
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
}

