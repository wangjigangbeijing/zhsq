package com.template.model.nfw;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@Table(name = "NFW_SQBSFW_QD")
@DynamicInsert(true)
@DynamicUpdate(true)
public class SQBSFW_QD implements java.io.Serializable {
	
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
	
	@Column(name = "sxmc", nullable = true)
	public String sxmc;
	
	public String getsxmc()
	{
		return sxmc;
	}
	public void setsxmc(String sxmc)
	{
		this.sxmc = sxmc;
	}
	
	@Column(name = "sxlb", nullable = true)
	public String sxlb;
	
	public String getsxlb()
	{
		return sxlb;
	}
	public void setsxlb(String sxlb)
	{
		this.sxlb = sxlb;
	}
	
	@Column(name = "fwdx", nullable = true)
	public String fwdx;
	
	public String getfwdx()
	{
		return fwdx;
	}
	public void setfwdx(String fwdx)
	{
		this.fwdx = fwdx;
	}
	
	
	@Column(name = "ywzgbm", nullable = true)
	public String ywzgbm;
	
	public String getywzgbm()
	{
		return ywzgbm;
	}
	public void setywzgbm(String ywzgbm)
	{
		this.ywzgbm = ywzgbm;
	}
	
	
	@Column(name = "blks", nullable = true)
	public String blks;
	
	public String getblks()
	{
		return blks;
	}
	public void setblks(String blks)
	{
		this.blks = blks;
	}
	
	
	@Column(name = "sfyjjbz", nullable = true)
	public String sfyjjbz;
	
	public String getsfyjjbz()
	{
		return sfyjjbz;
	}
	public void setsfyjjbz(String sfyjjbz)
	{
		this.sfyjjbz = sfyjjbz;
	}
	
	
	@Column(name = "bltj", nullable = true)
	public String bltj;
	
	public String getbltj()
	{
		return bltj;
	}
	public void setbltj(String bltj)
	{
		this.bltj = bltj;
	}
	
	
	@Column(name = "bllc", nullable = true)
	public String bllc;
	
	public String getbllc()
	{
		return bllc;
	}
	public void setbllc(String bllc)
	{
		this.bllc = bllc;
	}
	
	
	@Column(name = "sqcl", nullable = true)
	public String sqcl;
	
	public String getsqcl()
	{
		return sqcl;
	}
	public void setsqcl(String sqcl)
	{
		this.sqcl = sqcl;
	}
	
	
	@Column(name = "sltj", nullable = true)
	public String sltj;
	
	public String getsltj()
	{
		return sltj;
	}
	public void setsltj(String sltj)
	{
		this.sltj = sltj;
	}
	
	
	@Column(name = "pzxs", nullable = true)
	public String pzxs;
	
	public String getpzxs()
	{
		return pzxs;
	}
	public void setpzxs(String pzxs)
	{
		this.pzxs = pzxs;
	}
	
	
	@Column(name = "qysj", nullable = true)
	public String qysj;
	
	public String getqysj()
	{
		return qysj;
	}
	public void setqysj(String qysj)
	{
		this.qysj = qysj;
	}
	
	
	@Column(name = "tysj", nullable = true)
	public String tysj;
	
	public String gettysj()
	{
		return tysj;
	}
	public void settysj(String tysj)
	{
		this.tysj = tysj;
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
	

	@Column(name = "status", nullable = true)
	public String status;
	
	public String getstatus()
	{
		return status;
	}
	public void setstatus(String status)
	{
		this.status = status;
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
