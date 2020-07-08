package com.template.model.jcsqsj;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import java.util.Date;


@Entity
@Table(name = "JC_PUBLICFACILITIES")
@DynamicInsert(true)
@DynamicUpdate(true)
public class Publicfacilities implements java.io.Serializable {
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
@Column(name = "objid", nullable = true)
public String objid;
public String getobjid()
{
	return objid;
}
public void setobjid(String objid)
{
	this.objid = objid;
}
@Column(name = "objname", nullable = true)
public String objname;
public String getobjname()
{
	return objname;
}
public void setobjname(String objname)
{
	this.objname = objname;
}
@Column(name = "locatedsc", nullable = true)
public String locatedsc;
public String getlocatedsc()
{
	return locatedsc;
}
public void setlocatedsc(String locatedsc)
{
	this.locatedsc = locatedsc;
}
@Column(name = "deptname1", nullable = true)
public String deptname1;
public String getdeptname1()
{
	return deptname1;
}
public void setdeptname1(String deptname1)
{
	this.deptname1 = deptname1;
}
@Column(name = "deptname2", nullable = true)
public String deptname2;
public String getdeptname2()
{
	return deptname2;
}
public void setdeptname2(String deptname2)
{
	this.deptname2 = deptname2;
}
@Column(name = "deptname3", nullable = true)
public String deptname3;
public String getdeptname3()
{
	return deptname3;
}
public void setdeptname3(String deptname3)
{
	this.deptname3 = deptname3;
}
/*
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
@Column(name = "isincommunity", nullable = true)
public String isincommunity;
public String getisincommunity()
{
	return isincommunity;
}
public void setisincommunity(String isincommunity)
{
	this.isincommunity = isincommunity;
}
@Column(name = "material", nullable = true)
public String material;
public String getmaterial()
{
	return material;
}
public void setmaterial(String material)
{
	this.material = material;
}
@Column(name = "form", nullable = true)
public String form;
public String getform()
{
	return form;
}
public void setform(String form)
{
	this.form = form;
}
@Column(name = "objState", nullable = true)
public String objState;
public String getobjState()
{
	return objState;
}
public void setobjState(String objState)
{
	this.objState = objState;
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

