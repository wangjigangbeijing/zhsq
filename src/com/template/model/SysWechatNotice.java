package com.template.model;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import java.util.Date;


@Entity
@Table(name = "SYS_WECHAT_NOTICE")
@DynamicInsert(true)
@DynamicUpdate(true)
public class SysWechatNotice implements java.io.Serializable {
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
@Column(name = "createtime", nullable = true)
public String createtime;
public String getcreatetime()
{
	return createtime;
}
public void setcreatetime(String createtime)
{
	this.createtime = createtime;
}
@Column(name = "link", nullable = true)
public String link;
public String getlink()
{
	return link;
}
public void setlink(String link)
{
	this.link = link;
}
@Column(name = "linkurl", nullable = true)
public String linkurl;
public String getlinkurl()
{
	return linkurl;
}
public void setlinkurl(String linkurl)
{
	this.linkurl = linkurl;
}
@Column(name = "pcatename", nullable = true)
public String pcatename;
public String getpcatename()
{
	return pcatename;
}
public void setpcatename(String pcatename)
{
	this.pcatename = pcatename;
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