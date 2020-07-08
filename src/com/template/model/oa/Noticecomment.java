package com.template.model.oa;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import java.util.Date;


@Entity
@Table(name = "NOTICECOMMENT")
@DynamicInsert(true)
@DynamicUpdate(true)
public class Noticecomment implements java.io.Serializable {
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
@Column(name = "comments", nullable = true)
public String comments;
public String getcomments()
{
	return comments;
}
public void setcomments(String comments)
{
	this.comments = comments;
}
@Column(name = "commentstaffid", nullable = true)
public String commentstaffid;
public String getcommentstaffid()
{
	return commentstaffid;
}
public void setcommentstaffid(String commentstaffid)
{
	this.commentstaffid = commentstaffid;
}
@Column(name = "commenttime", nullable = true)
public Date commenttime;
public Date getcommenttime()
{
	return commenttime;
}
public void setcommenttime(Date commenttime)
{
	this.commenttime = commenttime;
}
}

