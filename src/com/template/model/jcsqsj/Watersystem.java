package com.template.model.jcsqsj;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import java.util.Date;


@Entity
@Table(name = "JC_WATERSYSTEM")
@DynamicInsert(true)
@DynamicUpdate(true)
public class Watersystem implements java.io.Serializable {
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
@Column(name = "leadertel", nullable = true)
public String leadertel;
public String getleadertel()
{
	return leadertel;
}
public void setleadertel(String leadertel)
{
	this.leadertel = leadertel;
}
@Column(name = "leaderorg", nullable = true)
public String leaderorg;
public String getleaderorg()
{
	return leaderorg;
}
public void setleaderorg(String leaderorg)
{
	this.leaderorg = leaderorg;
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

