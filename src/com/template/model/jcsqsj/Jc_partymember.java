package com.template.model.jcsqsj;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import java.util.Date;


@Entity
@Table(name = "JC_PARTYMEMBER")
@DynamicInsert(true)
@DynamicUpdate(true)
public class Jc_partymember implements java.io.Serializable {
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
@Column(name = "partymembertype", nullable = true)
public String partymembertype;
public String getpartymembertype()
{
	return partymembertype;
}
public void setpartymembertype(String partymembertype)
{
	this.partymembertype = partymembertype;
}
@Column(name = "of_partyorganization", nullable = true)
public String of_partyorganization;
public String getof_partyorganization()
{
	return of_partyorganization;
}
public void setof_partyorganization(String of_partyorganization)
{
	this.of_partyorganization = of_partyorganization;
}
@Column(name = "isincommunity", nullable = true)
public String isincommunity;
public String getisincommunity()
{
	return isincommunity;
}
public void setisincommunity(String isincommunity)
{
	this.isincommunity = isincommunity;
}
@Column(name = "homeaddress", nullable = true)
public String homeaddress;
public String gethomeaddress()
{
	return homeaddress;
}
public void sethomeaddress(String homeaddress)
{
	this.homeaddress = homeaddress;
}
@Column(name = "zhiwu", nullable = true)
public String zhiwu;
public String getzhiwu()
{
	return zhiwu;
}
public void setzhiwu(String zhiwu)
{
	this.zhiwu = zhiwu;
}
@Column(name = "joinpartydate", nullable = true)
public Date joinpartydate;
public Date getjoinpartydate()
{
	return joinpartydate;
}
public void setjoinpartydate(Date joinpartydate)
{
	this.joinpartydate = joinpartydate;
}
@Column(name = "inpartydate", nullable = true)
public Date inpartydate;
public Date getinpartydate()
{
	return inpartydate;
}
public void setinpartydate(Date inpartydate)
{
	this.inpartydate = inpartydate;
}
@Column(name = "dyage", nullable = true)
public String dyage;
public String getdyage()
{
	return dyage;
}
public void setdyage(String dyage)
{
	this.dyage = dyage;
}
@Column(name = "membership", nullable = true)
public String membership;
public String getmembership()
{
	return membership;
}
public void setmembership(String membership)
{
	this.membership = membership;
}
@Column(name = "islost", nullable = true)
public String islost;
public String getislost()
{
	return islost;
}
public void setislost(String islost)
{
	this.islost = islost;
}
@Column(name = "lostdate", nullable = true)
public Date lostdate;
public Date getlostdate()
{
	return lostdate;
}
public void setlostdate(Date lostdate)
{
	this.lostdate = lostdate;
}
@Column(name = "movemember", nullable = true)
public String movemember;
public String getmovemember()
{
	return movemember;
}
public void setmovemember(String movemember)
{
	this.movemember = movemember;
}
@Column(name = "moveto", nullable = true)
public String moveto;
public String getmoveto()
{
	return moveto;
}
public void setmoveto(String moveto)
{
	this.moveto = moveto;
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

