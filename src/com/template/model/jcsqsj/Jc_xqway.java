package com.template.model.jcsqsj;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import java.util.Date;


@Entity
@Table(name = "JC_XQWAY")
@DynamicInsert(true)
@DynamicUpdate(true)
public class Jc_xqway implements java.io.Serializable {
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
@Column(name = "dataid", nullable = true)
public String dataid;
public String getdataid()
{
	return dataid;
}
public void setdataid(String dataid)
{
	this.dataid = dataid;
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
@Column(name = "sfymj", nullable = true)
public String sfymj;
public String getsfymj()
{
	return sfymj;
}
public void setsfymj(String sfymj)
{
	this.sfymj = sfymj;
}
@Column(name = "mjlx", nullable = true)
public String mjlx;
public String getmjlx()
{
	return mjlx;
}
public void setmjlx(String mjlx)
{
	this.mjlx = mjlx;
}
@Column(name = "ssxq", nullable = true)
public String ssxq;
public String getssxq()
{
	return ssxq;
}
public void setssxq(String ssxq)
{
	this.ssxq = ssxq;
}
@Column(name = "pictures", nullable = true)
public String pictures;
public String getpictures()
{
	return pictures;
}
public void setpictures(String pictures)
{
	this.pictures = pictures;
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

