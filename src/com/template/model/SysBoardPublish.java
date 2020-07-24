package com.template.model;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import java.util.Date;


@Entity
@Table(name = "SYS_BOARD_PUBLISH")
@DynamicInsert(true)
@DynamicUpdate(true)
public class SysBoardPublish implements java.io.Serializable {
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
@Column(name = "title", nullable = true)
public String title;
public String gettitle()
{
	return title;
}
public void settitle(String title)
{
	this.title = title;
}
@Column(name = "category", nullable = true)
public String category;
public String getcategory()
{
	return category;
}
public void setcategory(String category)
{
	this.category = category;
}
@Column(name = "facilities", nullable = true)
public String facilities;
public String getfacilities()
{
	return facilities;
}
public void setfacilities(String facilities)
{
	this.facilities = facilities;
}
@Column(name = "content", nullable = true)
public String content;
public String getcontent()
{
	return content;
}
public void setcontent(String content)
{
	this.content = content;
}
@Column(name = "attachment", nullable = true)
public String attachment;
public String getattachment()
{
	return attachment;
}
public void setattachment(String attachment)
{
	this.attachment = attachment;
}
@Column(name = "starttime", nullable = true)
public String starttime;
public String getstarttime()
{
	return starttime;
}
public void setstarttime(String starttime)
{
	this.starttime = starttime;
}
@Column(name = "endtime", nullable = true)
public String endtime;
public String getendtime()
{
	return endtime;
}
public void setendtime(String endtime)
{
	this.endtime = endtime;
}
}

