package com.template.model;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import java.util.Date;


@Entity
@Table(name = "SYS_ROLE_RIGHT")
@DynamicInsert(true)
@DynamicUpdate(true)
public class SysRoleRight implements java.io.Serializable {
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
	@Column(name = "roleid", nullable = true)
	public String roleid;
	public String getroleid()
	{
		return roleid;
	}
	public void setroleid(String roleid)
	{
		this.roleid = roleid;
	}
	@Column(name = "rightid", nullable = true)
	public String rightid;
	public String getrightid()
	{
		return rightid;
	}
	public void setrightid(String rightid)
	{
		this.rightid = rightid;
	}
}

