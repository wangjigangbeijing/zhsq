package com.template.model.oa;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import java.util.Date;


@Entity
@Table(name = "CONTACT")
@DynamicInsert(true)
@DynamicUpdate(true)
public class Contact implements java.io.Serializable {
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
@Column(name = "contacttype", nullable = true)
public String contacttype;
public String getcontacttype()
{
	return contacttype;
}
public void setcontacttype(String contacttype)
{
	this.contacttype = contacttype;
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
@Column(name = "tel", nullable = true)
public String tel;
public String gettel()
{
	return tel;
}
public void settel(String tel)
{
	this.tel = tel;
}
@Column(name = "mobile", nullable = true)
public String mobile;
public String getmobile()
{
	return mobile;
}
public void setmobile(String mobile)
{
	this.mobile = mobile;
}
@Column(name = "unitid", nullable = true)
public String unitid;
public String getunitid()
{
	return unitid;
}
public void setunitid(String unitid)
{
	this.unitid = unitid;
}
@Column(name = "unitname", nullable = true)
public String unitname;
public String getunitname()
{
	return unitname;
}
public void setunitname(String unitname)
{
	this.unitname = unitname;
}
@Column(name = "job", nullable = true)
public String job;
public String getjob()
{
	return job;
}
public void setjob(String job)
{
	this.job = job;
}

@Column(name = "seq", nullable = true)
public Integer seq;
public Integer getseq()
{
	return seq;
}
public void setseq(Integer seq)
{
	this.seq = seq;
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

@Column(name = "authoritystaffid", nullable = true)
public String authoritystaffid;
public String getauthoritystaffid()
{
	return authoritystaffid;
}
public void setauthoritystaffid(String authoritystaffid)
{
	this.authoritystaffid = authoritystaffid;
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

