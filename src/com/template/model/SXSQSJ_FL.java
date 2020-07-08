package com.template.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@Table(name = "SXSQSJ_FL")
@DynamicInsert(true)
@DynamicUpdate(true)
/*
 * 
 */
public class SXSQSJ_FL implements java.io.Serializable {
	
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
	
	
	@Column(name = "ICON", nullable = true)
	public String ICON;
	
	public String getICON()
	{
		return ICON;
	}
	public void setICON(String ICON)
	{
		this.ICON = ICON;
	}
	
}
