package com.template.model.jcsqsj;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import java.util.Date;


@Entity
@Table(name = "JC_VEHICLE")
@DynamicInsert(true)
@DynamicUpdate(true)
public class Vehicle implements java.io.Serializable {
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
@Column(name = "frameno", nullable = true)
public String frameno;
public String getframeno()
{
	return frameno;
}
public void setframeno(String frameno)
{
	this.frameno = frameno;
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
@Column(name = "ofroom", nullable = true)
public String ofroom;
public String getofroom()
{
	return ofroom;
}
public void setofroom(String ofroom)
{
	this.ofroom = ofroom;
}
@Column(name = "offamily", nullable = true)
public String offamily;
public String getoffamily()
{
	return offamily;
}
public void setoffamily(String offamily)
{
	this.offamily = offamily;
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
@Column(name = "brand", nullable = true)
public String brand;
public String getbrand()
{
	return brand;
}
public void setbrand(String brand)
{
	this.brand = brand;
}
@Column(name = "model", nullable = true)
public String model;
public String getmodel()
{
	return model;
}
public void setmodel(String model)
{
	this.model = model;
}
@Column(name = "color", nullable = true)
public String color;
public String getcolor()
{
	return color;
}
public void setcolor(String color)
{
	this.color = color;
}
@Column(name = "ownername", nullable = true)
public String ownername;
public String getownername()
{
	return ownername;
}
public void setownername(String ownername)
{
	this.ownername = ownername;
}
@Column(name = "ownertel", nullable = true)
public String ownertel;
public String getownertel()
{
	return ownertel;
}
public void setownertel(String ownertel)
{
	this.ownertel = ownertel;
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

