package com.template.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@Table(name = "sys_table")
@DynamicInsert(true)
@DynamicUpdate(true)
public class SysTable implements java.io.Serializable {
	
	@Id
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

	@Column(name = "gis_type", nullable = true)
	public String gis_type;
	
	public String getGisType()
	{
		return gis_type;
	}
	public void setGisType(String gis_type)
	{
		this.gis_type = gis_type;
	}
	
	@Column(name = "table_enname", nullable = true)
	public String table_enname;
	
	public String getTableENName()
	{
		return table_enname;
	}
	public void setTableENName(String table_enname)
	{
		this.table_enname = table_enname;
	}
	
	@Column(name = "table_zhname", nullable = true)
	public String table_zhname;
	
	public String getTableZHName()
	{
		return table_zhname;
	}
	public void setTableZHName(String table_zhname)
	{
		this.table_zhname = table_zhname;
	}
	
	@Column(name = "object_number", nullable = true)
	public Integer object_number;
	
	public Integer getObjectNumber()
	{
		return object_number;
	}
	public void setObjectNumber(Integer object_number)
	{
		this.object_number = object_number;
	}
	
	@Column(name = "table_description", nullable = true)
	public String table_description;
	
	public String getTableDescription()
	{
		return table_description;
	}
	public void setTableDescription(String table_description)
	{
		this.table_description = table_description;
	}

	@Column(name = "icon", nullable = true)
	public String icon;
	
	public String getIcon()
	{
		return icon;
	}
	public void setIcon(String icon)
	{
		this.icon = icon;
	}
	
	@Column(name = "data_type", nullable = true)
	public String data_type;
	
	public String getDataType()
	{
		return data_type;
	}
	public void setDataType(String data_type)
	{
		this.data_type = data_type;
	}
}
