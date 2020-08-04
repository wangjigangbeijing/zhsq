package com.template.model.jcsqsj;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import java.util.Date;


@Entity
@Table(name = "JC_TC_TCCCRK")
@DynamicInsert(true)
@DynamicUpdate(true)
public class Jc_tc_tcccrk implements java.io.Serializable {
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
@Column(name = "rkType", nullable = true)
public String rkType;
public String getrkType()
{
	return rkType;
}
public void setrkType(String rkType)
{
	this.rkType = rkType;
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

