package com.template.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@Table(name = "sys_table_attribute")
@DynamicInsert(true)
@DynamicUpdate(true)
@XmlRootElement
public class SysTableAttribute implements java.io.Serializable {
	
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
	
	@Column(name = "table_id", nullable = true)
	public String tableId;
	
	public String getTableId()
	{
		return tableId;
	}
	public void setTableId(String tableId)
	{
		this.tableId = tableId;
	}
	
	@Column(name = "created_at", nullable = true)
	public Date created_at;
	
	public Date getCreatedAt()
	{
		return created_at;
	}
	public void setCreatedAt(Date created_at)
	{
		this.created_at = created_at;
	}
	
	@Column(name = "ATTRIBUTE_ENNAME", nullable = true)
	public String attrENName;//字段名称需要和页面上对应
	
	public String getENName()
	{
		return attrENName;
	}
	public void setENName(String attrENName)
	{
		this.attrENName = attrENName;
	}
	
	@Column(name = "ATTRIBUTE_ZHNAME", nullable = true)
	public String attrZHName;
	
	public String getZHName()
	{
		return attrZHName;
	}
	public void setZHName(String attrZHName)
	{
		this.attrZHName = attrZHName;
	}
	
	@Column(name = "ATTRIBUTE_COMPONENTSTYPE", nullable = true)
	public String attrComponentType;
	
	public String getComponentsType()
	{
		return attrComponentType;
	}
	public void setComponentsType(String attrComponentType)
	{
		this.attrComponentType = attrComponentType;
	}
	
	@Column(name = "ATTRIBUTE_DBTYPE", nullable = true)
	public String attrDBType;
	
	public String getDBType()
	{
		return attrDBType;
	}
	public void setDBType(String attrDBType)
	{
		this.attrDBType = attrDBType;
	}

	@Column(name = "ATTRIBUTE_DBLENGTH", nullable = true)
	public Integer attrDBLength;
	
	public Integer getDBLength()
	{
		return attrDBLength;
	}
	public void setDBLength(Integer attrDBLength)
	{
		this.attrDBLength = attrDBLength;
	}
	
	@Column(name = "ATTRIBUTE_VALUES", nullable = true)
	public String attrValue;
	
	public String getValues()
	{
		return attrValue;
	}
	public void setValues(String attrValue)
	{
		this.attrValue = attrValue;
	}
	
	/*
	 * @Desc	映射到SysAttribute表,如果该字段是从SysAttribute表映射过来的
	 */
	@Column(name = "attribute_id", nullable = true)
	public String attributeId;
	
	public String attributeId()
	{
		return attributeId;
	}
	public void setAttributeId(String attributeId)
	{
		this.attributeId = attributeId;
	}
	
	@Column(name = "SEQ", nullable = true)
	public Integer seq;
	
	public Integer getSeq()
	{
		return seq;
	}
	public void setSeq(Integer seq)
	{
		this.seq = seq;
	}
	
	
	@Column(name = "app_list_display", nullable = true)
	public String appListDisplay;
	
	public String getAppListDisplay()
	{
		return appListDisplay==null?"":appListDisplay;
	}
	public void setAppListDisplay(String appListDisplay)
	{
		this.appListDisplay = appListDisplay;
	}
	
	
	@Column(name = "app_display", nullable = true)
	public String appDisplay;
	
	public String getAppDisplay()
	{
		return appDisplay==null?"":appDisplay;
	}
	public void setAppDisplay(String appDisplay)
	{
		this.appDisplay = appDisplay;
	}
	
	@Column(name = "support_query", nullable = true)
	public String supportQuery;
	
	public String getSupportQuery()
	{
		return supportQuery==null?"":supportQuery;
	}
	public void setSupportQuery(String supportQuery)
	{
		this.supportQuery = supportQuery;
	}
	
	@Column(name = "parent_field", nullable = true)
	public String parentField;
	
	public String getparentField()
	{
		return parentField;
	}
	public void setparentField(String parentField)
	{
		this.parentField = parentField;
	}
	

	@Column(name = "parent_value", nullable = true)
	public String parentValue;
	
	public String getparentValue()
	{
		return parentValue;
	}
	public void setparentValue(String parentValue)
	{
		this.parentValue = parentValue;
	}
	
	@Column(name = "encryption", nullable = true)
	public String encryption;
	
	public String getencryption()
	{
		return encryption;
	}
	public void setencryption(String encryption)
	{
		this.encryption = encryption;
	}
	
	@Column(name = "showalgo", nullable = true)
	public String showalgo;
	
	public String getshowalgo()
	{
		return showalgo;
	}
	public void setshowalgo(String showalgo)
	{
		this.showalgo = showalgo;
	}
	
	@Column(name = "starrule", nullable = true)
	public String starrule;
	
	public String getstarrule()
	{
		return starrule;
	}
	public void setstarrule(String starrule)
	{
		this.starrule = starrule;
	}
	
}
