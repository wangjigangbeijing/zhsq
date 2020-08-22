package com.template.model.oa;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import java.util.Date;


@Entity
@Table(name = "WORKLOG")
@DynamicInsert(true)
@DynamicUpdate(true)
public class Worklog implements java.io.Serializable {
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
@Column(name = "date", nullable = true)
public String date;
public String getdate()
{
	return date;
}
public void setdate(String date)
{
	this.date = date;
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
@Column(name = "hours", nullable = true)
public String hours;
public String gethours()
{
	return hours;
}
public void sethours(String hours)
{
	this.hours = hours;
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
@Column(name = "files", nullable = true)
public String files;
public String getfiles()
{
	return files;
}
public void setfiles(String files)
{
	this.files = files;
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


@Column(name = "created_by", nullable = true)
public String created_by;
public String getcreated_by()
{
	return created_by;
}
public void setcreated_by(String created_by)
{
	this.created_by = created_by;
}

}

