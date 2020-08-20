package com.template.model;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import java.util.Date;


@Entity
@Table(name = "SYS_TEL_PUBLISH")
@DynamicInsert(true)
@DynamicUpdate(true)
public class SysTelPublish implements java.io.Serializable {
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
@Column(name = "audio", nullable = true)
public String audio;
public String getaudio()
{
	return audio;
}
public void setaudio(String audio)
{
	this.audio = audio;
}
@Column(name = "target", nullable = true)
public String target;
public String gettarget()
{
	return target;
}
public void settarget(String target)
{
	this.target = target;
}
@Column(name = "publishtime", nullable = true)
public String publishtime;
public String getpublishtime()
{
	return publishtime;
}
public void setpublishtime(String publishtime)
{
	this.publishtime = publishtime;
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

