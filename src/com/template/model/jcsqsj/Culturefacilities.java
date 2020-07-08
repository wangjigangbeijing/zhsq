package com.template.model.jcsqsj;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import java.util.Date;


@Entity
@Table(name = "JC_CULTUREFACILITIES")
@DynamicInsert(true)
@DynamicUpdate(true)
public class Culturefacilities implements java.io.Serializable {
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
@Column(name = "tpye", nullable = true)
public String tpye;
public String gettpye()
{
	return tpye;
}
public void settpye(String tpye)
{
	this.tpye = tpye;
}
@Column(name = "category", nullable = true)
public String category;
public String getcategory()
{
	return category;
}
public void setcategory(String category)
{
	this.category = category;
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
@Column(name = "purpose", nullable = true)
public String purpose;
public String getpurpose()
{
	return purpose;
}
public void setpurpose(String purpose)
{
	this.purpose = purpose;
}
@Column(name = "introduction", nullable = true)
public String introduction;
public String getintroduction()
{
	return introduction;
}
public void setintroduction(String introduction)
{
	this.introduction = introduction;
}
@Column(name = "managedepart", nullable = true)
public String managedepart;
public String getmanagedepart()
{
	return managedepart;
}
public void setmanagedepart(String managedepart)
{
	this.managedepart = managedepart;
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

