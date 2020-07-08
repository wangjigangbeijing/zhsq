package com.template.model.jcsqsj;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import java.util.Date;


@Entity
@Table(name = "JC_SERVICE_STORE")
@DynamicInsert(true)
@DynamicUpdate(true)
public class Service_store implements java.io.Serializable {
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
@Column(name = "socialcode", nullable = true)
public String socialcode;
public String getsocialcode()
{
	return socialcode;
}
public void setsocialcode(String socialcode)
{
	this.socialcode = socialcode;
}
@Column(name = "businessscope", nullable = true)
public String businessscope;
public String getbusinessscope()
{
	return businessscope;
}
public void setbusinessscope(String businessscope)
{
	this.businessscope = businessscope;
}
@Column(name = "businessarea", nullable = true)
public Integer businessarea;
public Integer getbusinessarea()
{
	return businessarea;
}
public void setbusinessarea(Integer businessarea)
{
	this.businessarea = businessarea;
}
@Column(name = "ischain", nullable = true)
public String ischain;
public String getischain()
{
	return ischain;
}
public void setischain(String ischain)
{
	this.ischain = ischain;
}
@Column(name = "otherbusiness", nullable = true)
public String otherbusiness;
public String getotherbusiness()
{
	return otherbusiness;
}
public void setotherbusiness(String otherbusiness)
{
	this.otherbusiness = otherbusiness;
}
@Column(name = "contact", nullable = true)
public String contact;
public String getcontact()
{
	return contact;
}
public void setcontact(String contact)
{
	this.contact = contact;
}
@Column(name = "contacttel", nullable = true)
public String contacttel;
public String getcontacttel()
{
	return contacttel;
}
public void setcontacttel(String contacttel)
{
	this.contacttel = contacttel;
}
@Column(name = "opentime", nullable = true)
public Date opentime;
public Date getopentime()
{
	return opentime;
}
public void setopentime(Date opentime)
{
	this.opentime = opentime;
}
@Column(name = "closetime", nullable = true)
public Date closetime;
public Date getclosetime()
{
	return closetime;
}
public void setclosetime(Date closetime)
{
	this.closetime = closetime;
}
@Column(name = "is24hours", nullable = true)
public String is24hours;
public String getis24hours()
{
	return is24hours;
}
public void setis24hours(String is24hours)
{
	this.is24hours = is24hours;
}
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

