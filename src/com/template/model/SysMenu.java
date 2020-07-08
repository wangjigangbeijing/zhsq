package com.template.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@Table(name = "sys_menu")
@DynamicInsert(true)
@DynamicUpdate(true)
public class SysMenu implements java.io.Serializable {
	
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
	
	@Column(name = "menu_zhname", nullable = true)
	public String menu_zhname;
	
	public String getMenuZHName()
	{
		return menu_zhname;
	}
	public void setMenuZHName(String menu_zhname)
	{
		this.menu_zhname = menu_zhname;
	}
	
	@Column(name = "menu_enname", nullable = true)
	public String menu_enname;
	
	public String getMenuENName()
	{
		return menu_enname;
	}
	public void setMenuENName(String menu_enname)
	{
		this.menu_enname = menu_enname;
	}
	
	
	@Column(name = "menu_type", nullable = true)
	public String menu_type;
	
	public String getMenuType()
	{
		return menu_type;
	}
	public void setMenuType(String menu_type)
	{
		this.menu_type = menu_type;
	}
	
	
	@Column(name = "table_id", nullable = true)
	public String table_id;
	
	public String getTableId()
	{
		return table_id;
	}
	public void setTableId(String table_id)
	{
		this.table_id = table_id;
	}
	
	@Column(name = "file_name", nullable = true)
	public String file_name;
	
	public String getFileName()
	{
		return file_name;
	}
	public void setFileName(String file_name)
	{
		this.file_name = file_name;
	}
	
	@Column(name = "external_url", nullable = true)
	public String external_url;
	
	public String getExternalUrl()
	{
		return file_name;
	}
	public void setExternalUrl(String external_url)
	{
		this.external_url = external_url;
	}
}
