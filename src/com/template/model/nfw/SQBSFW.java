package com.template.model.nfw;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@Table(name = "NFW_SQBSFW")
@DynamicInsert(true)
@DynamicUpdate(true)
public class SQBSFW implements java.io.Serializable {
	
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
	
	@Column(name = "blr", nullable = false)
	public String blr;
	
	public String getblr()
	{
		return blr;
	}
	public void setblr(String blr)
	{
		this.blr = blr;
	}
	
	@Column(name = "blrname", nullable = false)
	public String blrname;
	
	public String getBlrname() {
		return blrname;
	}
	public void setBlrname(String blrname) {
		this.blrname = blrname;
	}

	@Column(name = "lxdh", nullable = true)
	public String lxdh;
	
	public String getlxdh()
	{
		return lxdh;
	}
	public void setlxdh(String lxdh)
	{
		this.lxdh = lxdh;
	}
	

	@Column(name = "blqd", nullable = true)
	public String blqd;
	
	public String getblqd()
	{
		return blqd;
	}
	public void setblqd(String blqd)
	{
		this.blqd = blqd;
	}
	

	@Column(name = "blsxdl", nullable = true)
	public String blsxdl;
	
	public String getblsxdl()
	{
		return blsxdl;
	}
	public void setblsxdl(String blsxdl)
	{
		this.blsxdl = blsxdl;
	}
	

	@Column(name = "blsxxl", nullable = true)
	public String blsxxl;
	
	public String getblsxxl()
	{
		return blsxxl;
	}
	public void setblsxxl(String blsxxl)
	{
		this.blsxxl = blsxxl;
	}
	
	@Column(name = "blsj", nullable = true)
	public String blsj;
	
	@Column(name = "fj", nullable = true)
	public String fj;

	public String getBlsj() {
		return blsj;
	}
	public void setBlsj(String blsj) {
		this.blsj = blsj;
	}
	public String getFj() {
		return fj;
	}
	public void setFj(String fj) {
		this.fj = fj;
	}

	@Column(name = "xq", nullable = true)
	public String xq;
	
	public String getxq()
	{
		return xq;
	}
	public void setxq(String xq)
	{
		this.xq = xq;
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
	
	@Column(name = "owner", nullable = true)
	public String owner;

	public String getowner() {
		return owner;
	}
	public void setowner(String owner) {
		this.owner = owner;
	}
	
}
