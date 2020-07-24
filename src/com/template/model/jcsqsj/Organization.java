package com.template.model.jcsqsj;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import java.util.Date;


@Entity
@Table(name = "JC_ORGANIZATION")
@DynamicInsert(true)
@DynamicUpdate(true)
public class Organization implements java.io.Serializable {
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
@Column(name = "dataid", nullable = true)
public String dataid;
public String getdataid()
{
	return dataid;
}
public void setdataid(String dataid)
{
	this.dataid = dataid;
}
@Column(name = "name", nullable = true)
public String name;
public String getname()
{
	return name;
}
public void setname(String name)
{
	this.name = name;
}
@Column(name = "haslicence", nullable = true)
public String haslicence;
public String gethaslicence()
{
	return haslicence;
}
public void sethaslicence(String haslicence)
{
	this.haslicence = haslicence;
}
@Column(name = "socialcode", nullable = true)
public String socialcode;
public String getsocialcode()
{
	return socialcode;
}
public void setsocialcode(String socialcode)
{
	this.socialcode = socialcode;
}
@Column(name = "socialcodedate", nullable = true)
public Date socialcodedate;
public Date getsocialcodedate()
{
	return socialcodedate;
}
public void setsocialcodedate(Date socialcodedate)
{
	this.socialcodedate = socialcodedate;
}
@Column(name = "orgtype", nullable = true)
public String orgtype;
public String getorgtype()
{
	return orgtype;
}
public void setorgtype(String orgtype)
{
	this.orgtype = orgtype;
}
@Column(name = "economictype", nullable = true)
public String economictype;
public String geteconomictype()
{
	return economictype;
}
public void seteconomictype(String economictype)
{
	this.economictype = economictype;
}
@Column(name = "industry", nullable = true)
public String industry;
public String getindustry()
{
	return industry;
}
public void setindustry(String industry)
{
	this.industry = industry;
}
@Column(name = "subordination", nullable = true)
public String subordination;
public String getsubordination()
{
	return subordination;
}
public void setsubordination(String subordination)
{
	this.subordination = subordination;
}
@Column(name = "establishdate", nullable = true)
public Date establishdate;
public Date getestablishdate()
{
	return establishdate;
}
public void setestablishdate(Date establishdate)
{
	this.establishdate = establishdate;
}
@Column(name = "capitaltype", nullable = true)
public String capitaltype;
public String getcapitaltype()
{
	return capitaltype;
}
public void setcapitaltype(String capitaltype)
{
	this.capitaltype = capitaltype;
}
@Column(name = "capital", nullable = true)
public String capital;
public String getcapital()
{
	return capital;
}
public void setcapital(String capital)
{
	this.capital = capital;
}
@Column(name = "businessscope", nullable = true)
public String businessscope;
public String getbusinessscope()
{
	return businessscope;
}
public void setbusinessscope(String businessscope)
{
	this.businessscope = businessscope;
}
@Column(name = "scale", nullable = true)
public String scale;
public String getscale()
{
	return scale;
}
public void setscale(String scale)
{
	this.scale = scale;
}
@Column(name = "regaddress", nullable = true)
public String regaddress;
public String getregaddress()
{
	return regaddress;
}
public void setregaddress(String regaddress)
{
	this.regaddress = regaddress;
}
@Column(name = "officeaddress", nullable = true)
public String officeaddress;
public String getofficeaddress()
{
	return officeaddress;
}
public void setofficeaddress(String officeaddress)
{
	this.officeaddress = officeaddress;
}
@Column(name = "ofbizbuilding", nullable = true)
public String ofbizbuilding;
public String getofbizbuilding()
{
	return ofbizbuilding;
}
public void setofbizbuilding(String ofbizbuilding)
{
	this.ofbizbuilding = ofbizbuilding;
}
/*
@Column(name = "longitude", nullable = true)
public Double longitude;
public Double getlongitude()
{
	return longitude;
}
public void setlongitude(Double longitude)
{
	this.longitude = longitude;
}
@Column(name = "latitude", nullable = true)
public Double latitude;
public Double getlatitude()
{
	return latitude;
}
public void setlatitude(Double latitude)
{
	this.latitude = latitude;
}*/
@Column(name = "legalname", nullable = true)
public String legalname;
public String getlegalname()
{
	return legalname;
}
public void setlegalname(String legalname)
{
	this.legalname = legalname;
}
@Column(name = "contactname", nullable = true)
public String contactname;
public String getcontactname()
{
	return contactname;
}
public void setcontactname(String contactname)
{
	this.contactname = contactname;
}
@Column(name = "contacttel", nullable = true)
public String contacttel;
public String getcontacttel()
{
	return contacttel;
}
public void setcontacttel(String contacttel)
{
	this.contacttel = contacttel;
}
@Column(name = "moveindate", nullable = true)
public Date moveindate;
public Date getmoveindate()
{
	return moveindate;
}
public void setmoveindate(Date moveindate)
{
	this.moveindate = moveindate;
}
@Column(name = "responsibilityplateno", nullable = true)
public String responsibilityplateno;
public String getresponsibilityplateno()
{
	return responsibilityplateno;
}
public void setresponsibilityplateno(String responsibilityplateno)
{
	this.responsibilityplateno = responsibilityplateno;
}
@Column(name = "hasfirefacilities", nullable = true)
public String hasfirefacilities;
public String gethasfirefacilities()
{
	return hasfirefacilities;
}
public void sethasfirefacilities(String hasfirefacilities)
{
	this.hasfirefacilities = hasfirefacilities;
}
@Column(name = "wastedisposal", nullable = true)
public String wastedisposal;
public String getwastedisposal()
{
	return wastedisposal;
}
public void setwastedisposal(String wastedisposal)
{
	this.wastedisposal = wastedisposal;
}
@Column(name = "status", nullable = true)
public String status;
public String getstatus()
{
	return status;
}
public void setstatus(String status)
{
	this.status = status;
}
@Column(name = "pictures", nullable = true)
public String pictures;
public String getpictures()
{
	return pictures;
}
public void setpictures(String pictures)
{
	this.pictures = pictures;
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

