package com.template.model.jcsqsj;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import java.util.Date;


@Entity
@Table(name = "JC_FAMILY")
@DynamicInsert(true)
@DynamicUpdate(true)
public class Family implements java.io.Serializable {
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
@Column(name = "registrationcategory", nullable = true)
public String registrationcategory;
public String getregistrationcategory()
{
	return registrationcategory;
}
public void setregistrationcategory(String registrationcategory)
{
	this.registrationcategory = registrationcategory;
}
@Column(name = "registrationaddress", nullable = true)
public String registrationaddress;
public String getregistrationaddress()
{
	return registrationaddress;
}
public void setregistrationaddress(String registrationaddress)
{
	this.registrationaddress = registrationaddress;
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
}

