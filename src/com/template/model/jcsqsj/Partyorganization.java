package com.template.model.jcsqsj;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import java.util.Date;


@Entity
@Table(name = "JC_PARTYORGANIZATION")
@DynamicInsert(true)
@DynamicUpdate(true)
public class Partyorganization implements java.io.Serializable {
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
@Column(name = "secretary", nullable = true)
public String secretary;
public String getsecretary()
{
	return secretary;
}
public void setsecretary(String secretary)
{
	this.secretary = secretary;
}
@Column(name = "secretarytel", nullable = true)
public String secretarytel;
public String getsecretarytel()
{
	return secretarytel;
}
public void setsecretarytel(String secretarytel)
{
	this.secretarytel = secretarytel;
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
@Column(name = "company", nullable = true)
public String company;
public String getcompany()
{
	return company;
}
public void setcompany(String company)
{
	this.company = company;
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

