package com.template.model.gis;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import java.util.Date;


@Entity
@Table(name = "GISMAP")
@DynamicInsert(true)
@DynamicUpdate(true)
public class Gismap implements java.io.Serializable {
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
@Column(name = "mapid", nullable = true)
public String mapid;
public String getmapid()
{
	return mapid;
}
public void setmapid(String mapid)
{
	this.mapid = mapid;
}
@Column(name = "mapname", nullable = true)
public String mapname;
public String getmapname()
{
	return mapname;
}
public void setmapname(String mapname)
{
	this.mapname = mapname;
}
@Column(name = "maptype", nullable = true)
public String maptype;
public String getmaptype()
{
	return maptype;
}
public void setmaptype(String maptype)
{
	this.maptype = maptype;
}
@Column(name = "mapstatus", nullable = true)
public String mapstatus;
public String getmapstatus()
{
	return mapstatus;
}
public void setmapstatus(String mapstatus)
{
	this.mapstatus = mapstatus;
}
@Column(name = "mapscope", nullable = true)
public String mapscope;
public String getmapscope()
{
	return mapscope;
}
public void setmapscope(String mapscope)
{
	this.mapscope = mapscope;
}
@Column(name = "mapnote", nullable = true)
public String mapnote;
public String getmapnote()
{
	return mapnote;
}
public void setmapnote(String mapnote)
{
	this.mapnote = mapnote;
}
@Column(name = "mapicon", nullable = true)
public String mapicon;
public String getmapicon()
{
	return mapicon;
}
public void setmapicon(String mapicon)
{
	this.mapicon = mapicon;
}
}

