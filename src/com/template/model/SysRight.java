package com.template.model;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import java.util.Date;


@Entity
@Table(name = "SYS_RIGHT")
@DynamicInsert(true)
@DynamicUpdate(true)
public class SysRight implements java.io.Serializable {
	@Id
	@Column(name = "rightid", nullable = false)
	public String rightid;
	public String getId()
	{
		return rightid;
	}
	public void setId(String rightid)
	{
		this.rightid = rightid;
	}
	@Column(name = "rightname", nullable = true)
	public String rightname;
	public String getrightname()
	{
		return rightname;
	}
	public void setrightname(String rightname)
	{
		this.rightname = rightname;
	}
	@Column(name = "tablename", nullable = true)
	public String tablename;
	public String gettablename()
	{
		return tablename;
	}
	public void settablename(String tablename)
	{
		this.tablename = tablename;
	}
	@Column(name = "action", nullable = true)
	public String action;
	public String getaction()
	{
		return action;
	}
	public void setaction(String action)
	{
		this.action = action;
	}
}

