package com.template.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@Table(name = "SXSQSJ")
@DynamicInsert(true)
@DynamicUpdate(true)
/*
 * 数据编码	事项名称	事项大类	事项小类	事项详情	事项地点	事项当事人	事项开始时间	事项结束时间	事项总结	事项照片	事项附件	事项经费	事项备注	事项状态(计划、进行中、结束、取消)
 */
public class SXSQSJ implements java.io.Serializable {
	
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
	
	
	@Column(name = "SXBM", nullable = true)
	public String SXBM;
	
	public String getSXBM()
	{
		return SXBM;
	}
	public void setSXBM(String SXBM)
	{
		this.SXBM = SXBM;
	}
	
	
	@Column(name = "SXMC", nullable = true)
	public String SXMC;
	
	public String getSXMC()
	{
		return SXMC;
	}
	public void setSXMC(String SXMC)
	{
		this.SXMC = SXMC;
	}
	
	
	@Column(name = "SXDL", nullable = true)
	public String SXDL;
	
	public String getSXDL()
	{
		return SXDL;
	}
	public void setSXDL(String SXDL)
	{
		this.SXDL = SXDL;
	}
	
	
	@Column(name = "SXXL", nullable = true)
	public String SXXL;
	
	public String getSXXL()
	{
		return SXXL;
	}
	public void setSXXL(String SXXL)
	{
		this.SXXL = SXXL;
	}
	
	
	@Column(name = "SXXQ", nullable = true)
	public String SXXQ;
	
	public String getSXXQ()
	{
		return SXXQ;
	}
	public void setSXXQ(String SXXQ)
	{
		this.SXXQ = SXXQ;
	}
	
	
	@Column(name = "SXDD", nullable = true)
	public String SXDD;
	
	public String getSXDD()
	{
		return SXDD;
	}
	public void setSXDD(String SXDD)
	{
		this.SXDD = SXDD;
	}
	
	
	@Column(name = "SXDSR", nullable = true)
	public String SXDSR;
	
	public String getSXDSR()
	{
		return SXDSR;
	}
	public void setSXDSR(String SXDSR)
	{
		this.SXDSR = SXDSR;
	}
	
	@Column(name = "SXDSRNAME", nullable = true)
	public String SXDSRNAME;
	
	public String getSXDSRNAME()
	{
		return SXDSRNAME;
	}
	public void setSXDSRNAME(String SXDSRNAME)
	{
		this.SXDSRNAME = SXDSRNAME;
	}
	
	
	@Column(name = "SXKSSJ", nullable = true)
	public String SXKSSJ;
	
	public String getSXKSSJ()
	{
		return SXKSSJ;
	}
	public void setSXKSSJ(String SXKSSJ)
	{
		this.SXKSSJ = SXKSSJ;
	}
	
	
	@Column(name = "SXJSSJ", nullable = true)
	public String SXJSSJ;
	
	public String getSXJSSJ()
	{
		return SXJSSJ;
	}
	public void setSXJSSJ(String SXJSSJ)
	{
		this.SXJSSJ = SXJSSJ;
	}
	
	
	@Column(name = "SXZJ", nullable = true)
	public String SXZJ;
	
	public String getSXZJ()
	{
		return SXZJ;
	}
	public void setSXZJ(String SXZJ)
	{
		this.SXZJ = SXZJ;
	}
	
	
	@Column(name = "SXZP", nullable = true)
	public String SXZP;
	
	public String getSXZP()
	{
		return SXZP;
	}
	public void setSXZP(String SXZP)
	{
		this.SXZP = SXZP;
	}
	
	
	@Column(name = "SXFJ", nullable = true)
	public String SXFJ;
	
	public String getSXFJ()
	{
		return SXFJ;
	}
	public void setSXFJ(String SXFJ)
	{
		this.SXFJ = SXFJ;
	}
	
	@Column(name = "SXJF", nullable = true)
	public Double SXJF;
	
	public Double getSXJF()
	{
		return SXJF;
	}
	public void setSXJF(Double SXJF)
	{
		this.SXJF = SXJF;
	}
	
	
	@Column(name = "SXBZ", nullable = true)
	public String SXBZ;
	
	public String getSXBZ()
	{
		return SXBZ;
	}
	public void setSXBZ(String SXBZ)
	{
		this.SXBZ = SXBZ;
	}
	
	
	@Column(name = "SXZT", nullable = true)
	public String SXZT;
	
	public String getSXZT()
	{
		return SXZT;
	}
	public void setSXZT(String SXZT)
	{
		this.SXZT = SXZT;
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
	

	
	@Column(name = "SXJBR", nullable = true)
	public String SXJBR;
	
	public String getSXJBR()
	{
		return SXJBR;
	}
	public void setSXJBR(String SXJBR)
	{
		this.SXJBR = SXJBR;
	}
	
	@Column(name = "SXJBRNAME", nullable = true)
	public String SXJBRNAME;
	
	public String getSXJBRNAME()
	{
		return SXJBRNAME;
	}
	public void setSXJBRNAME(String SXJBRNAME)
	{
		this.SXJBRNAME = SXJBRNAME;
	}
	

	@Column(name = "OWNER", nullable = true)
	public String OWNER;
	
	public String getOWNER()
	{
		return OWNER;
	}
	public void setOWNER(String OWNER)
	{
		this.OWNER = OWNER;
	}
	

	@Column(name = "POINT", nullable = true)
	public String POINT;
	
	public String getPOINT()
	{
		return POINT;
	}
	public void setPOINT(String POINT)
	{
		this.POINT = POINT;
	}
}
