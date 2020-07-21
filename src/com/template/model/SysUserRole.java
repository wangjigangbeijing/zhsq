package com.template.model;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import java.util.Date;


@Entity
@Table(name = "SYS_USER_ROLE")
@DynamicInsert(true)
@DynamicUpdate(true)
public class SysUserRole implements java.io.Serializable {
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
@Column(name = "user", nullable = true)
public String user;
public String getuser()
{
	return user;
}
public void setuser(String user)
{
	this.user = user;
}
@Column(name = "role", nullable = true)
public String role;
public String getrole()
{
	return role;
}
public void setrole(String role)
{
	this.role = role;
}
}