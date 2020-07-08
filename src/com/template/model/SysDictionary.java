package com.template.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@Table(name = "sys_dictionary")
@DynamicInsert(true)
@DynamicUpdate(true)
public class SysDictionary implements java.io.Serializable {
	
	@Id
	@Column(name = "dic_enname", nullable = true)
	public String dic_enname;
	
	public String getdic_enname()
	{
		return dic_enname;
	}
	public void setdic_enname(String dic_enname)
	{
		this.dic_enname = dic_enname;
	}
	
	
	@Column(name = "dic_zhname", nullable = true)
	public String dic_zhname;
	
	public String getdic_zhname()
	{
		return dic_zhname;
	}
	public void setdic_zhname(String dic_zhname)
	{
		this.dic_zhname = dic_zhname;
	}
	
	
	@Column(name = "dic_type", nullable = true)
	public String dic_type;
	
	public String getdic_type()
	{
		return dic_type;
	}
	public void setdic_type(String dic_type)
	{
		this.dic_type = dic_type;
	}
	
	
	@Column(name = "dic_value", nullable = true)
	public String dic_value;
	
	public String getdic_value()
	{
		return dic_value;
	}
	public void setdic_value(String dic_value)
	{
		this.dic_value = dic_value;
	}
	
}
