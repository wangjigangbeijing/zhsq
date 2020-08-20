package com.template.model.oa;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import java.util.Date;


@Entity
@Table(name = "NOTICE")
@DynamicInsert(true)
@DynamicUpdate(true)
public class Notice implements java.io.Serializable {
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
@Column(name = "authorityorg", nullable = true)
public String authorityorg;
public String getauthorityorg()
{
	return authorityorg;
}
public void setauthorityorg(String authorityorg)
{
	this.authorityorg = authorityorg;
}
@Column(name = "body", nullable = true)
public String body;
public String getbody()
{
	return body;
}
public void setbody(String body)
{
	this.body = body;
}
@Column(name = "attach", nullable = true)
public String attach;
public String getattach()
{
	return attach;
}
public void setattach(String attach)
{
	this.attach = attach;
}
@Column(name = "time", nullable = true)
public Date time;
public Date gettime()
{
	return time;
}
public void settime(Date time)
{
	this.time = time;
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

