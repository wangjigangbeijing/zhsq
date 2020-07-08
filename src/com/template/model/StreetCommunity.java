package com.template.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@Table(name = "street_community")
@DynamicInsert(true)
@DynamicUpdate(true)
public class StreetCommunity implements java.io.Serializable {
	
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
	
	@Column(name = "street", nullable = false)
	public String street;
	
	public String getStreet()
	{
		return street;
	}
	public void setStreet(String street)
	{
		this.street = street;
	}
	
	@Column(name = "community", nullable = false)
	public String community;
	
	public String getCommunity()
	{
		return community;
	}
	public void setCommunity(String community)
	{
		this.community = community;
	}
}
