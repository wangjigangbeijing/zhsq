package com.template.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

//@Entity
//@Table(name = "table_field")
//@DynamicInsert(true)
//@DynamicUpdate(true)
public class TableField implements java.io.Serializable {
	
	/*@Id
	@Column(name = "id", nullable = true)
	public String id;
	
	public String getId()
	{
		return id;
	}
	public void setId(String id)
	{
		this.id = id;
	}
	*/
	public String field_name;
	
	public String getFieldname()
	{
		return field_name;
	}
	public void setFieldname(String field_name)
	{
		this.field_name = field_name;
	}
	
	public String display_name;
	public String getDisplayName()
	{
		return display_name;
	}
	public void setDisplayName(String display_name)
	{
		this.display_name = display_name;
	}
	
	public String table_name;
	
	public String getTableName()
	{
		return table_name;
	}
	public void setTableName(String table_name)
	{
		this.table_name = table_name;
	}
	
	
	public String db_type;
	
	public String getDBType()
	{
		return db_type;
	}
	public void setDBType(String db_type)
	{
		this.db_type = db_type;
	}
	/*
	@Column(name = "countable", nullable = true)
	public Integer countable;
	
	public Integer getCountable()
	{
		return countable;
	}
	public void setCountable(Integer countable)
	{
		this.countable = countable;
	}
	
	@Column(name = "groupable", nullable = true)
	public Integer groupable;
	
	public Integer getGroupable()
	{
		return groupable;
	}
	public void setGroupable(Integer groupable)
	{
		this.groupable = groupable;
	}
	*/
}
