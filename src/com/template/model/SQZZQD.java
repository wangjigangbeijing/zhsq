package com.template.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@Table(name = "SQZZQD")
@DynamicInsert(true)
@DynamicUpdate(true)
public class SQZZQD implements java.io.Serializable {
	
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
	
	@Column(name = "bh", nullable = true)
	public String bh;
	
	public String getbh()
	{
		return bh;
	}
	public void setbh(String bh)
	{
		this.bh = bh;
	}
	
	@Column(name = "gzzz", nullable = true)
	public String gzzz;
	
	public String getgzzz()
	{
		return gzzz;
	}
	public void setgzzz(String gzzz)
	{
		this.gzzz = gzzz;
	}
	

	@Column(name = "zzly", nullable = true)
	public String zzly;
	
	public String getzzly()
	{
		return zzly;
	}
	public void setzzly(String zzly)
	{
		this.zzly = zzly;
	}
	

	@Column(name = "zzy", nullable = true)
	public String zzy;
	
	public String getzzy()
	{
		return zzy;
	}
	public void setzzy(String zzy)
	{
		this.zzy = zzy;
	}
	

	@Column(name = "lb", nullable = true)
	public String lb;
	
	public String getlb()
	{
		return lb;
	}
	public void setlb(String lb)
	{
		this.lb = lb;
	}
	

	@Column(name = "yjflfgmc", nullable = true)//依据法律法规名称
	public String yjflfgmc;
	
	public String getyjflfgmc()
	{
		return yjflfgmc;
	}
	public void setyjflfgmc(String yjflfgmc)
	{
		this.yjflfgmc = yjflfgmc;
	}
	

	@Column(name = "FJ", nullable = true)
	public String FJ;
	
	public String getFJ()
	{
		return FJ;
	}
	public void setFJ(String FJ)
	{
		this.FJ = FJ;
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
