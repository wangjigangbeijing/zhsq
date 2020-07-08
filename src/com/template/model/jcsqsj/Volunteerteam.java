package com.template.model.jcsqsj;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import java.util.Date;


@Entity
@Table(name = "JC_VOLUNTEERTEAM")
@DynamicInsert(true)
@DynamicUpdate(true)
public class Volunteerteam implements java.io.Serializable {
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
@Column(name = "contactorg", nullable = true)
public String contactorg;
public String getcontactorg()
{
	return contactorg;
}
public void setcontactorg(String contactorg)
{
	this.contactorg = contactorg;
}
@Column(name = "regstatus", nullable = true)
public String regstatus;
public String getregstatus()
{
	return regstatus;
}
public void setregstatus(String regstatus)
{
	this.regstatus = regstatus;
}
@Column(name = "regunit", nullable = true)
public String regunit;
public String getregunit()
{
	return regunit;
}
public void setregunit(String regunit)
{
	this.regunit = regunit;
}
@Column(name = "competentunit", nullable = true)
public String competentunit;
public String getcompetentunit()
{
	return competentunit;
}
public void setcompetentunit(String competentunit)
{
	this.competentunit = competentunit;
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
@Column(name = "establishdate", nullable = true)
public Date establishdate;
public Date getestablishdate()
{
	return establishdate;
}
public void setestablishdate(Date establishdate)
{
	this.establishdate = establishdate;
}
@Column(name = "persionsize", nullable = true)
public Integer persionsize;
public Integer getpersionsize()
{
	return persionsize;
}
public void setpersionsize(Integer persionsize)
{
	this.persionsize = persionsize;
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
@Column(name = "leadername", nullable = true)
public String leadername;
public String getleadername()
{
	return leadername;
}
public void setleadername(String leadername)
{
	this.leadername = leadername;
}
@Column(name = "leaderid", nullable = true)
public String leaderid;
public String getleaderid()
{
	return leaderid;
}
public void setleaderid(String leaderid)
{
	this.leaderid = leaderid;
}
@Column(name = "leadermobile", nullable = true)
public String leadermobile;
public String getleadermobile()
{
	return leadermobile;
}
public void setleadermobile(String leadermobile)
{
	this.leadermobile = leadermobile;
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
@Column(name = "contactmobile", nullable = true)
public String contactmobile;
public String getcontactmobile()
{
	return contactmobile;
}
public void setcontactmobile(String contactmobile)
{
	this.contactmobile = contactmobile;
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

