package com.template.model.jcsqsj;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import java.util.Date;


@Entity
@Table(name = "JC_XFTD")
@DynamicInsert(true)
@DynamicUpdate(true)
public class Jc_xftd implements java.io.Serializable {
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
@Column(name = "forbuildings", nullable = true)
public String forbuildings;
public String getforbuildings()
{
	return forbuildings;
}
public void setforbuildings(String forbuildings)
{
	this.forbuildings = forbuildings;
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

