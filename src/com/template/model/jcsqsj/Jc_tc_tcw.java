package com.template.model.jcsqsj;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import java.util.Date;


@Entity
@Table(name = "JC_TC_TCW")
@DynamicInsert(true)
@DynamicUpdate(true)
public class Jc_tc_tcw implements java.io.Serializable {
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
@Column(name = "created_at", nullable = true)
public Date created_at;
public Date getcreated_at()
{
	return created_at;
}
public void setcreated_at(Date created_at)
{
	this.created_at = created_at;
}

@Column(name = "inparkname", nullable = true)
public String inparkname;
public String getinparkname()
{
	return inparkname;
}
public void setinparkname(String inparkname)
{
	this.inparkname = inparkname;
}
@Column(name = "cwtype", nullable = true)
public String cwtype;
public String getcwtype()
{
	return cwtype;
}
public void setcwtype(String cwtype)
{
	this.cwtype = cwtype;
}
@Column(name = "location", nullable = true)
public String location;
public String getlocation()
{
	return location;
}
public void setlocation(String location)
{
	this.location = location;
}
@Column(name = "numbers", nullable = true)
public Integer numbers;
public Integer getnumbers()
{
	return numbers;
}
public void setnumbers(Integer numbers)
{
	this.numbers = numbers;
}
@Column(name = "usetype", nullable = true)
public String UseType;
public String getUseType()
{
	return UseType;
}
public void setUseType(String UseType)
{
	this.UseType = UseType;
}
@Column(name = "sizetype", nullable = true)
public String sizeType;
public String getsizeType()
{
	return sizeType;
}
public void setsizeType(String sizeType)
{
	this.sizeType = sizeType;
}
@Column(name = "heighttype", nullable = true)
public String heightType;
public String getheightType()
{
	return heightType;
}
public void setheightType(String heightType)
{
	this.heightType = heightType;
}
@Column(name = "arrange", nullable = true)
public String arrange;
public String getarrange()
{
	return arrange;
}
public void setarrange(String arrange)
{
	this.arrange = arrange;
}
@Column(name = "hascharge", nullable = true)
public String hascharge;
public String gethascharge()
{
	return hascharge;
}
public void sethascharge(String hascharge)
{
	this.hascharge = hascharge;
}
@Column(name = "chargenum", nullable = true)
public Integer chargenum;
public Integer getchargenum()
{
	return chargenum;
}
public void setchargenum(Integer chargenum)
{
	this.chargenum = chargenum;
}
@Column(name = "cwcode", nullable = true)
public String cwcode;
public String getcwcode()
{
	return cwcode;
}
public void setcwcode(String cwcode)
{
	this.cwcode = cwcode;
}
@Column(name = "pciture", nullable = true)
public String pciture;
public String getpciture()
{
	return pciture;
}
public void setpciture(String pciture)
{
	this.pciture = pciture;
}
@Column(name = "note", nullable = true)
public String note;
public String getnote()
{
	return note;
}
public void setnote(String note)
{
	this.note = note;
}

@Column(name = "owner", nullable = true)
public String owner;
public String getowner()
{
	return owner;
}
public void setowner(String owner)
{
	this.owner = owner;
}
}

