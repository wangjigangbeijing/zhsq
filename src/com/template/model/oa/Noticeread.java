package com.template.model.oa;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import java.util.Date;


@Entity
@Table(name = "NOTICEREAD")
@DynamicInsert(true)
@DynamicUpdate(true)
public class Noticeread implements java.io.Serializable {
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
@Column(name = "noticeid", nullable = true)
public String noticeid;
public String getnoticeid()
{
	return noticeid;
}
public void setnoticeid(String noticeid)
{
	this.noticeid = noticeid;
}
@Column(name = "readerid", nullable = true)
public String readerid;
public String getreaderid()
{
	return readerid;
}
public void setreaderid(String readerid)
{
	this.readerid = readerid;
}
@Column(name = "readtime", nullable = true)
public Date readtime;
public Date getreadtime()
{
	return readtime;
}
public void setreadtime(Date readtime)
{
	this.readtime = readtime;
}
}

