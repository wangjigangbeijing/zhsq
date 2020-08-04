package com.template.model.jcsqsj;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import java.util.Date;


@Entity
@Table(name = "JC_TC_DLTCC")
@DynamicInsert(true)
@DynamicUpdate(true)
public class Jc_tc_dltcc implements java.io.Serializable {
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
@Column(name = "berthID", nullable = true)
public String berthID;
public String getberthID()
{
	return berthID;
}
public void setberthID(String berthID)
{
	this.berthID = berthID;
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
@Column(name = "roadname", nullable = true)
public String roadname;
public String getroadname()
{
	return roadname;
}
public void setroadname(String roadname)
{
	this.roadname = roadname;
}
@Column(name = "area", nullable = true)
public String area;
public String getarea()
{
	return area;
}
public void setarea(String area)
{
	this.area = area;
}
@Column(name = "rateinfo", nullable = true)
public String rateinfo;
public String getrateinfo()
{
	return rateinfo;
}
public void setrateinfo(String rateinfo)
{
	this.rateinfo = rateinfo;
}
@Column(name = "parkeTime", nullable = true)
public String parkeTime;
public String getparkeTime()
{
	return parkeTime;
}
public void setparkeTime(String parkeTime)
{
	this.parkeTime = parkeTime;
}
@Column(name = "parknum", nullable = true)
public Integer parknum;
public Integer getparknum()
{
	return parknum;
}
public void setparknum(Integer parknum)
{
	this.parknum = parknum;
}
@Column(name = "picture", nullable = true)
public String picture;
public String getpicture()
{
	return picture;
}
public void setpicture(String picture)
{
	this.picture = picture;
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

