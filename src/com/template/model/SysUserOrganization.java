package com.template.model;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import java.util.Date;


@Entity
@Table(name = "SYS_USER_ORGANIZATION")
@DynamicInsert(true)
@DynamicUpdate(true)
public class SysUserOrganization implements java.io.Serializable {
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
@Column(name = "organization", nullable = true)
public String organization;
public String getorganization()
{
	return organization;
}
public void setorganization(String organization)
{
	this.organization = organization;
}
}