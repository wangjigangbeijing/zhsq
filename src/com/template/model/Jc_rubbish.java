package com.template.model;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import java.util.Date;


@Entity
@Table(name = "JC_RUBBISH")
@DynamicInsert(true)
@DynamicUpdate(true)
public class Jc_rubbish implements java.io.Serializable {
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
@Column(name = "kind", nullable = true)
public String kind;
public String getkind()
{
	return kind;
}
public void setkind(String kind)
{
	this.kind = kind;
}
@Column(name = "catagory", nullable = true)
public String catagory;
public String getcatagory()
{
	return catagory;
}
public void setcatagory(String catagory)
{
	this.catagory = catagory;
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
@Column(name = "department", nullable = true)
public String department;
public String getdepartment()
{
	return department;
}
public void setdepartment(String department)
{
	this.department = department;
}
@Column(name = "departtel", nullable = true)
public String departtel;
public String getdeparttel()
{
	return departtel;
}
public void setdeparttel(String departtel)
{
	this.departtel = departtel;
}
@Column(name = "cleartime", nullable = true)
public String cleartime;
public String getcleartime()
{
	return cleartime;
}
public void setcleartime(String cleartime)
{
	this.cleartime = cleartime;
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

