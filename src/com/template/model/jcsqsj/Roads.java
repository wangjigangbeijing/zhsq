package com.template.model.jcsqsj;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import java.util.Date;


@Entity
@Table(name = "JC_ROADS")
@DynamicInsert(true)
@DynamicUpdate(true)
public class Roads implements java.io.Serializable {
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
@Column(name = "length", nullable = true)
public Integer length;
public Integer getlength()
{
	return length;
}
public void setlength(Integer length)
{
	this.length = length;
}
@Column(name = "width", nullable = true)
public Integer width;
public Integer getwidth()
{
	return width;
}
public void setwidth(Integer width)
{
	this.width = width;
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
@Column(name = "direction", nullable = true)
public String direction;
public String getdirection()
{
	return direction;
}
public void setdirection(String direction)
{
	this.direction = direction;
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


@Column(name = "owner", nullable = true)
public String owner;
public String getowner()
{
	return owner;
}
public void setowner(String owner)
{
	this.owner = owner;
}
}

