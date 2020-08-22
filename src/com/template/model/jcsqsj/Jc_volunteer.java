package com.template.model.jcsqsj;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import java.util.Date;


@Entity
@Table(name = "JC_VOLUNTEER")
@DynamicInsert(true)
@DynamicUpdate(true)
public class Jc_volunteer implements java.io.Serializable {
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
@Column(name = "of_volunteerteam", nullable = true)
public String of_volunteerteam;
public String getof_volunteerteam()
{
	return of_volunteerteam;
}
public void setof_volunteerteam(String of_volunteerteam)
{
	this.of_volunteerteam = of_volunteerteam;
}
@Column(name = "join_date", nullable = true)
public Date join_date;
public Date getjoin_date()
{
	return join_date;
}
public void setjoin_date(Date join_date)
{
	this.join_date = join_date;
}
@Column(name = "certificate_id", nullable = true)
public String certificate_id;
public String getcertificate_id()
{
	return certificate_id;
}
public void setcertificate_id(String certificate_id)
{
	this.certificate_id = certificate_id;
}
@Column(name = "special_skill", nullable = true)
public String special_skill;
public String getspecial_skill()
{
	return special_skill;
}
public void setspecial_skill(String special_skill)
{
	this.special_skill = special_skill;
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

