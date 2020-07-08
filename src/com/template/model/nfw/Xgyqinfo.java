package com.template.model.nfw;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import java.util.Date;


@Entity
@Table(name = "KZ_XGYQINFO")
@DynamicInsert(true)
@DynamicUpdate(true)
public class Xgyqinfo implements java.io.Serializable {
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
@Column(name = "address", nullable = true)
public String address;
public String getaddress()
{
	return address;
}
public void setaddress(String address)
{
	this.address = address;
}
@Column(name = "quezhen", nullable = true)
public String quezhen;
public String getquezhen()
{
	return quezhen;
}
public void setquezhen(String quezhen)
{
	this.quezhen = quezhen;
}
@Column(name = "qzdate", nullable = true)
public Date qzdate;
public Date getqzdate()
{
	return qzdate;
}
public void setqzdate(Date qzdate)
{
	this.qzdate = qzdate;
}
@Column(name = "qznote", nullable = true)
public String qznote;
public String getqznote()
{
	return qznote;
}
public void setqznote(String qznote)
{
	this.qznote = qznote;
}
@Column(name = "yisi", nullable = true)
public String yisi;
public String getyisi()
{
	return yisi;
}
public void setyisi(String yisi)
{
	this.yisi = yisi;
}
@Column(name = "mijie", nullable = true)
public String mijie;
public String getmijie()
{
	return mijie;
}
public void setmijie(String mijie)
{
	this.mijie = mijie;
}
@Column(name = "mjnote", nullable = true)
public String mjnote;
public String getmjnote()
{
	return mjnote;
}
public void setmjnote(String mjnote)
{
	this.mjnote = mjnote;
}
@Column(name = "glstartdate", nullable = true)
public Date glstartdate;
public Date getglstartdate()
{
	return glstartdate;
}
public void setglstartdate(Date glstartdate)
{
	this.glstartdate = glstartdate;
}
@Column(name = "glenddate", nullable = true)
public Date glenddate;
public Date getglenddate()
{
	return glenddate;
}
public void setglenddate(Date glenddate)
{
	this.glenddate = glenddate;
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
@Column(name = "hsjc", nullable = true)
public String hsjc;
public String gethsjc()
{
	return hsjc;
}
public void sethsjc(String hsjc)
{
	this.hsjc = hsjc;
}
@Column(name = "hsjcdate", nullable = true)
public Date hsjcdate;
public Date gethsjcdate()
{
	return hsjcdate;
}
public void sethsjcdate(Date hsjcdate)
{
	this.hsjcdate = hsjcdate;
}
@Column(name = "hsjcjigou", nullable = true)
public String hsjcjigou;
public String gethsjcjigou()
{
	return hsjcjigou;
}
public void sethsjcjigou(String hsjcjigou)
{
	this.hsjcjigou = hsjcjigou;
}
@Column(name = "hsjcjieguo", nullable = true)
public String hsjcjieguo;
public String gethsjcjieguo()
{
	return hsjcjieguo;
}
public void sethsjcjieguo(String hsjcjieguo)
{
	this.hsjcjieguo = hsjcjieguo;
}
}

