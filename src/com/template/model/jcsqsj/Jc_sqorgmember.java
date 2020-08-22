package com.template.model.jcsqsj;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import java.util.Date;


@Entity
@Table(name = "JC_SQORGMEMBER")
@DynamicInsert(true)
@DynamicUpdate(true)
public class Jc_sqorgmember implements java.io.Serializable {
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
@Column(name = "idnumber", nullable = true)
public String idnumber;
public String getidnumber()
{
	return idnumber;
}
public void setidnumber(String idnumber)
{
	this.idnumber = idnumber;
}
@Column(name = "sex", nullable = true)
public String sex;
public String getsex()
{
	return sex;
}
public void setsex(String sex)
{
	this.sex = sex;
}
@Column(name = "birthday", nullable = true)
public Date birthday;
public Date getbirthday()
{
	return birthday;
}
public void setbirthday(Date birthday)
{
	this.birthday = birthday;
}
@Column(name = "age", nullable = true)
public String age;
public String getage()
{
	return age;
}
public void setage(String age)
{
	this.age = age;
}
@Column(name = "mobile", nullable = true)
public String mobile;
public String getmobile()
{
	return mobile;
}
public void setmobile(String mobile)
{
	this.mobile = mobile;
}
@Column(name = "education", nullable = true)
public String education;
public String geteducation()
{
	return education;
}
public void seteducation(String education)
{
	this.education = education;
}
@Column(name = "politicalstatus", nullable = true)
public String politicalstatus;
public String getpoliticalstatus()
{
	return politicalstatus;
}
public void setpoliticalstatus(String politicalstatus)
{
	this.politicalstatus = politicalstatus;
}
@Column(name = "of_sqorganization", nullable = true)
public String of_sqorganization;
public String getof_sqorganization()
{
	return of_sqorganization;
}
public void setof_sqorganization(String of_sqorganization)
{
	this.of_sqorganization = of_sqorganization;
}
@Column(name = "orgjob", nullable = true)
public String orgjob;
public String getorgjob()
{
	return orgjob;
}
public void setorgjob(String orgjob)
{
	this.orgjob = orgjob;
}
@Column(name = "duty", nullable = true)
public String duty;
public String getduty()
{
	return duty;
}
public void setduty(String duty)
{
	this.duty = duty;
}
@Column(name = "unit", nullable = true)
public String unit;
public String getunit()
{
	return unit;
}
public void setunit(String unit)
{
	this.unit = unit;
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
}

