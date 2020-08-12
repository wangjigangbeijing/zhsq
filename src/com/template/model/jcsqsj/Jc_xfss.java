package com.template.model.jcsqsj;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import java.util.Date;


@Entity
@Table(name = "JC_XFSS")
@DynamicInsert(true)
@DynamicUpdate(true)
public class Jc_xfss implements java.io.Serializable {
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
@Column(name = "created_at", nullable = true)
public Date created_at;
public Date getcreated_at()
{
	return created_at;
}
public void setcreated_at(Date created_at)
{
	this.created_at = created_at;
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
@Column(name = "objid", nullable = true)
public String objid;
public String getobjid()
{
	return objid;
}
public void setobjid(String objid)
{
	this.objid = objid;
}
@Column(name = "objname", nullable = true)
public String objname;
public String getobjname()
{
	return objname;
}
public void setobjname(String objname)
{
	this.objname = objname;
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
@Column(name = "isincommunity", nullable = true)
public String isincommunity;
public String getisincommunity()
{
	return isincommunity;
}
public void setisincommunity(String isincommunity)
{
	this.isincommunity = isincommunity;
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
@Column(name = "form", nullable = true)
public String form;
public String getform()
{
	return form;
}
public void setform(String form)
{
	this.form = form;
}
@Column(name = "objstate", nullable = true)
public String objState;
public String getobjState()
{
	return objState;
}
public void setobjState(String objState)
{
	this.objState = objState;
}
@Column(name = "locatedsc", nullable = true)
public String locatedsc;
public String getlocatedsc()
{
	return locatedsc;
}
public void setlocatedsc(String locatedsc)
{
	this.locatedsc = locatedsc;
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

