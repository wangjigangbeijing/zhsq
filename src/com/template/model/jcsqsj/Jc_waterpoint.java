package com.template.model.jcsqsj;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import java.util.Date;


@Entity
@Table(name = "JC_WATERPOINT")
@DynamicInsert(true)
@DynamicUpdate(true)
public class Jc_waterpoint implements java.io.Serializable {
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
@Column(name = "depth", nullable = true)
public String depth;
public String getdepth()
{
	return depth;
}
public void setdepth(String depth)
{
	this.depth = depth;
}
@Column(name = "plan", nullable = true)
public String plan;
public String getplan()
{
	return plan;
}
public void setplan(String plan)
{
	this.plan = plan;
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
@Column(name = "file", nullable = true)
public String file;
public String getfile()
{
	return file;
}
public void setfile(String file)
{
	this.file = file;
}
}

