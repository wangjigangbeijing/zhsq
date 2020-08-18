package com.template.model.nfw;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import java.util.Date;


@Entity
@Table(name = "KZ_JSJBFW")
@DynamicInsert(true)
@DynamicUpdate(true)
public class JSJBFW implements java.io.Serializable 
{
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
	
	@Column(name = "sjbt", nullable = true)
	public String sjbt;
	public String getsjbt()
	{
		return sjbt;
	}
	public void setsjbt(String sjbt)
	{
		this.sjbt = sjbt;
	}

	@Column(name = "sjjjcd", nullable = true)
	public String sjjjcd;
	public String getsjjjcd()
	{
		return sjjjcd;
	}
	public void setsjjjcd(String sjjjcd)
	{
		this.sjjjcd = sjjjcd;
	}

	
	@Column(name = "sjlyjb", nullable = true)
	public String sjlyjb;
	public String getsjlyjb()
	{
		return sjlyjb;
	}
	public void setsjlyjb(String sjlyjb)
	{
		this.sjlyjb = sjlyjb;
	}
	
	@Column(name = "sjly", nullable = true)
	public String sjly;
	public String getsjly()
	{
		return sjly;
	}
	public void setsjly(String sjly)
	{
		this.sjly = sjly;
	}
	
	@Column(name = "sjlybh", nullable = true)
	public String sjlybh;
	public String getsjlybh()
	{
		return sjlybh;
	}
	public void setsjlybh(String sjlybh)
	{
		this.sjlybh = sjlybh;
	}

	@Column(name = "sjfl", nullable = true)
	public String sjfl;
	public String getsjfl()
	{
		return sjfl;
	}
	public void setsjfl(String sjfl)
	{
		this.sjfl = sjfl;
	}
	
	@Column(name = "wtfl", nullable = true)
	public String wtfl;
	public String getwtfl()
	{
		return wtfl;
	}
	public void setwtfl(String wtfl)
	{
		this.wtfl = wtfl;
	}
	
	@Column(name = "fsdz", nullable = true)
	public String fsdz;
	public String getfsdz()
	{
		return fsdz;
	}
	public void setfsdz(String fsdz)
	{
		this.fsdz = fsdz;
	}


	@Column(name = "dsr", nullable = true)
	public String dsr;
	public String getdsr()
	{
		return dsr;
	}
	public void setdsr(String dsr)
	{
		this.dsr = dsr;
	}


	@Column(name = "dsrdh", nullable = true)
	public String dsrdh;
	public String getdsrdh()
	{
		return dsrdh;
	}
	public void setdsrdh(String dsrdh)
	{
		this.dsrdh = dsrdh;
	}
	
	@Column(name = "sfyqhf", nullable = true)
	public String sfyqhf;
	public String getsfyqhf()
	{
		return sfyqhf;
	}
	public void setsfyqhf(String sfyqhf)
	{
		this.sfyqhf = sfyqhf;
	}

	@Column(name = "pdsj", nullable = true)
	public String pdsj;
	public String getpdsj()
	{
		return pdsj;
	}
	public void setpdsj(String pdsj)
	{
		this.pdsj = pdsj;
	}


	@Column(name = "clsx", nullable = true)
	public String clsx;
	public String getclsx()
	{
		return clsx;
	}
	public void setclsx(String clsx)
	{
		this.clsx = clsx;
	}
	
	@Column(name = "cljzsj", nullable = true)
	public String cljzsj;
	public String getcljzsj()
	{
		return cljzsj;
	}
	public void setcljzsj(String cljzsj)
	{
		this.cljzsj = cljzsj;
	}


	@Column(name = "sjnr", nullable = true)
	public String sjnr;
	public String getsjnr()
	{
		return sjnr;
	}
	public void setsjnr(String sjnr)
	{
		this.sjnr = sjnr;
	}


	@Column(name = "bz", nullable = true)
	public String bz;
	public String getbz()
	{
		return bz;
	}
	public void setbz(String bz)
	{
		this.bz = bz;
	}


	@Column(name = "fj", nullable = true)
	public String fj;
	public String getfj()
	{
		return fj;
	}
	public void setfj(String fj)
	{
		this.fj = fj;
	}
	
	@Column(name = "owner", nullable = true)
	public String owner;
	public String getowner() {
		return owner;
	}
	public void setowner(String owner) {
		this.owner = owner;
	}
	
}

