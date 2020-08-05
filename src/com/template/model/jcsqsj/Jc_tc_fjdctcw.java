package com.template.model.jcsqsj;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import java.util.Date;


@Entity
@Table(name = "JC_TC_FJDCTCW")
@DynamicInsert(true)
@DynamicUpdate(true)
public class Jc_tc_fjdctcw implements java.io.Serializable {
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
@Column(name = "parkName", nullable = true)
public String parkName;
public String getparkName()
{
	return parkName;
}
public void setparkName(String parkName)
{
	this.parkName = parkName;
}
@Column(name = "parkType", nullable = true)
public String parkType;
public String getparkType()
{
	return parkType;
}
public void setparkType(String parkType)
{
	this.parkType = parkType;
}
@Column(name = "adminDep", nullable = true)
public String adminDep;
public String getadminDep()
{
	return adminDep;
}
public void setadminDep(String adminDep)
{
	this.adminDep = adminDep;
}
@Column(name = "ownerDep", nullable = true)
public String ownerDep;
public String getownerDep()
{
	return ownerDep;
}
public void setownerDep(String ownerDep)
{
	this.ownerDep = ownerDep;
}
@Column(name = "maintDep", nullable = true)
public String maintDep;
public String getmaintDep()
{
	return maintDep;
}
public void setmaintDep(String maintDep)
{
	this.maintDep = maintDep;
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
