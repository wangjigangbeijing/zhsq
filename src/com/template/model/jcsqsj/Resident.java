package com.template.model.jcsqsj;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import java.util.Date;


@Entity
@Table(name = "JC_RESIDENT")
@DynamicInsert(true)
@DynamicUpdate(true)
public class Resident implements java.io.Serializable {
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
@Column(name = "identitytype", nullable = true)
public String identitytype;
public String getidentitytype()
{
	return identitytype;
}
public void setidentitytype(String identitytype)
{
	this.identitytype = identitytype;
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
@Column(name = "characteristics", nullable = true)
public String characteristics;
public String getcharacteristics()
{
	return characteristics;
}
public void setcharacteristics(String characteristics)
{
	this.characteristics = characteristics;
}
@Column(name = "ofcommunity", nullable = true)
public String ofcommunity;
public String getofcommunity()
{
	return ofcommunity;
}
public void setofcommunity(String ofcommunity)
{
	this.ofcommunity = ofcommunity;
}
@Column(name = "ofresidebuilding", nullable = true)
public String ofresidebuilding;
public String getofresidebuilding()
{
	return ofresidebuilding;
}
public void setofresidebuilding(String ofresidebuilding)
{
	this.ofresidebuilding = ofresidebuilding;
}
@Column(name = "ofunit", nullable = true)
public String ofunit;
public String getofunit()
{
	return ofunit;
}
public void setofunit(String ofunit)
{
	this.ofunit = ofunit;
}
@Column(name = "ofroom", nullable = true)
public String ofroom;
public String getofroom()
{
	return ofroom;
}
public void setofroom(String ofroom)
{
	this.ofroom = ofroom;
}
@Column(name = "offamily", nullable = true)
public String offamily;
public String getoffamily()
{
	return offamily;
}
public void setoffamily(String offamily)
{
	this.offamily = offamily;
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
@Column(name = "residencestatus", nullable = true)
public String residencestatus;
public String getresidencestatus()
{
	return residencestatus;
}
public void setresidencestatus(String residencestatus)
{
	this.residencestatus = residencestatus;
}
@Column(name = "ishouseholder", nullable = true)
public String ishouseholder;
public String getishouseholder()
{
	return ishouseholder;
}
public void setishouseholder(String ishouseholder)
{
	this.ishouseholder = ishouseholder;
}
@Column(name = "relationshiphouseholder", nullable = true)
public String relationshiphouseholder;
public String getrelationshiphouseholder()
{
	return relationshiphouseholder;
}
public void setrelationshiphouseholder(String relationshiphouseholder)
{
	this.relationshiphouseholder = relationshiphouseholder;
}
@Column(name = "registrationcategory", nullable = true)
public String registrationcategory;
public String getregistrationcategory()
{
	return registrationcategory;
}
public void setregistrationcategory(String registrationcategory)
{
	this.registrationcategory = registrationcategory;
}
@Column(name = "nationality", nullable = true)
public String nationality;
public String getnationality()
{
	return nationality;
}
public void setnationality(String nationality)
{
	this.nationality = nationality;
}
@Column(name = "homeplace", nullable = true)
public String homeplace;
public String gethomeplace()
{
	return homeplace;
}
public void sethomeplace(String homeplace)
{
	this.homeplace = homeplace;
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
@Column(name = "nation", nullable = true)
public String nation;
public String getnation()
{
	return nation;
}
public void setnation(String nation)
{
	this.nation = nation;
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
@Column(name = "usedname", nullable = true)
public String usedname;
public String getusedname()
{
	return usedname;
}
public void setusedname(String usedname)
{
	this.usedname = usedname;
}
@Column(name = "residenceaddress", nullable = true)
public String residenceaddress;
public String getresidenceaddress()
{
	return residenceaddress;
}
public void setresidenceaddress(String residenceaddress)
{
	this.residenceaddress = residenceaddress;
}
@Column(name = "residentialaddress", nullable = true)
public String residentialaddress;
public String getresidentialaddress()
{
	return residentialaddress;
}
public void setresidentialaddress(String residentialaddress)
{
	this.residentialaddress = residentialaddress;
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
@Column(name = "professionstatus", nullable = true)
public String professionstatus;
public String getprofessionstatus()
{
	return professionstatus;
}
public void setprofessionstatus(String professionstatus)
{
	this.professionstatus = professionstatus;
}
@Column(name = "professiontype", nullable = true)
public String professiontype;
public String getprofessiontype()
{
	return professiontype;
}
public void setprofessiontype(String professiontype)
{
	this.professiontype = professiontype;
}
@Column(name = "workunit", nullable = true)
public String workunit;
public String getworkunit()
{
	return workunit;
}
public void setworkunit(String workunit)
{
	this.workunit = workunit;
}
@Column(name = "job", nullable = true)
public String job;
public String getjob()
{
	return job;
}
public void setjob(String job)
{
	this.job = job;
}
@Column(name = "issocialsecurity", nullable = true)
public String issocialsecurity;
public String getissocialsecurity()
{
	return issocialsecurity;
}
public void setissocialsecurity(String issocialsecurity)
{
	this.issocialsecurity = issocialsecurity;
}
@Column(name = "email", nullable = true)
public String email;
public String getemail()
{
	return email;
}
public void setemail(String email)
{
	this.email = email;
}
@Column(name = "tel", nullable = true)
public String tel;
public String gettel()
{
	return tel;
}
public void settel(String tel)
{
	this.tel = tel;
}
@Column(name = "fax", nullable = true)
public String fax;
public String getfax()
{
	return fax;
}
public void setfax(String fax)
{
	this.fax = fax;
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
@Column(name = "eligiousbelief", nullable = true)
public String eligiousbelief;
public String geteligiousbelief()
{
	return eligiousbelief;
}
public void seteligiousbelief(String eligiousbelief)
{
	this.eligiousbelief = eligiousbelief;
}
@Column(name = "marriage", nullable = true)
public String marriage;
public String getmarriage()
{
	return marriage;
}
public void setmarriage(String marriage)
{
	this.marriage = marriage;
}
@Column(name = "isforeignmarriage", nullable = true)
public String isforeignmarriage;
public String getisforeignmarriage()
{
	return isforeignmarriage;
}
public void setisforeignmarriage(String isforeignmarriage)
{
	this.isforeignmarriage = isforeignmarriage;
}
@Column(name = "health", nullable = true)
public String health;
public String gethealth()
{
	return health;
}
public void sethealth(String health)
{
	this.health = health;
}
@Column(name = "military", nullable = true)
public String military;
public String getmilitary()
{
	return military;
}
public void setmilitary(String military)
{
	this.military = military;
}
@Column(name = "stature", nullable = true)
public String stature;
public String getstature()
{
	return stature;
}
public void setstature(String stature)
{
	this.stature = stature;
}
@Column(name = "blood", nullable = true)
public String blood;
public String getblood()
{
	return blood;
}
public void setblood(String blood)
{
	this.blood = blood;
}
@Column(name = "hobby", nullable = true)
public String hobby;
public String gethobby()
{
	return hobby;
}
public void sethobby(String hobby)
{
	this.hobby = hobby;
}
@Column(name = "reasonnotresidence", nullable = true)
public String reasonnotresidence;
public String getreasonnotresidence()
{
	return reasonnotresidence;
}
public void setreasonnotresidence(String reasonnotresidence)
{
	this.reasonnotresidence = reasonnotresidence;
}
@Column(name = "custodian", nullable = true)
public String custodian;
public String getcustodian()
{
	return custodian;
}
public void setcustodian(String custodian)
{
	this.custodian = custodian;
}
@Column(name = "custodiantel", nullable = true)
public String custodiantel;
public String getcustodiantel()
{
	return custodiantel;
}
public void setcustodiantel(String custodiantel)
{
	this.custodiantel = custodiantel;
}
@Column(name = "custodianrelationship", nullable = true)
public String custodianrelationship;
public String getcustodianrelationship()
{
	return custodianrelationship;
}
public void setcustodianrelationship(String custodianrelationship)
{
	this.custodianrelationship = custodianrelationship;
}
@Column(name = "custodianincommunity", nullable = true)
public String custodianincommunity;
public String getcustodianincommunity()
{
	return custodianincommunity;
}
public void setcustodianincommunity(String custodianincommunity)
{
	this.custodianincommunity = custodianincommunity;
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
@Column(name = "dy_partymembertype", nullable = true)
public String dy_partymembertype;
public String getdy_partymembertype()
{
	return dy_partymembertype;
}
public void setdy_partymembertype(String dy_partymembertype)
{
	this.dy_partymembertype = dy_partymembertype;
}
@Column(name = "dy_of_partyorganization", nullable = true)
public String dy_of_partyorganization;
public String getdy_of_partyorganization()
{
	return dy_of_partyorganization;
}
public void setdy_of_partyorganization(String dy_of_partyorganization)
{
	this.dy_of_partyorganization = dy_of_partyorganization;
}
@Column(name = "dy_joinpartydate", nullable = true)
public Date dy_joinpartydate;
public Date getdy_joinpartydate()
{
	return dy_joinpartydate;
}
public void setdy_joinpartydate(Date dy_joinpartydate)
{
	this.dy_joinpartydate = dy_joinpartydate;
}
@Column(name = "dy_inpartydate", nullable = true)
public Date dy_inpartydate;
public Date getdy_inpartydate()
{
	return dy_inpartydate;
}
public void setdy_inpartydate(Date dy_inpartydate)
{
	this.dy_inpartydate = dy_inpartydate;
}
@Column(name = "dy_membership", nullable = true)
public String dy_membership;
public String getdy_membership()
{
	return dy_membership;
}
public void setdy_membership(String dy_membership)
{
	this.dy_membership = dy_membership;
}
@Column(name = "dy_islost", nullable = true)
public String dy_islost;
public String getdy_islost()
{
	return dy_islost;
}
public void setdy_islost(String dy_islost)
{
	this.dy_islost = dy_islost;
}
@Column(name = "dy_lostdate", nullable = true)
public Date dy_lostdate;
public Date getdy_lostdate()
{
	return dy_lostdate;
}
public void setdy_lostdate(Date dy_lostdate)
{
	this.dy_lostdate = dy_lostdate;
}
@Column(name = "dy_movemember", nullable = true)
public String dy_movemember;
public String getdy_movemember()
{
	return dy_movemember;
}
public void setdy_movemember(String dy_movemember)
{
	this.dy_movemember = dy_movemember;
}
@Column(name = "dy_moveto", nullable = true)
public String dy_moveto;
public String getdy_moveto()
{
	return dy_moveto;
}
public void setdy_moveto(String dy_moveto)
{
	this.dy_moveto = dy_moveto;
}
@Column(name = "zdr_type", nullable = true)
public String zdr_type;
public String getzdr_type()
{
	return zdr_type;
}
public void setzdr_type(String zdr_type)
{
	this.zdr_type = zdr_type;
}
@Column(name = "zdr_reason", nullable = true)
public String zdr_reason;
public String getzdr_reason()
{
	return zdr_reason;
}
public void setzdr_reason(String zdr_reason)
{
	this.zdr_reason = zdr_reason;
}
@Column(name = "zdr_custodian", nullable = true)
public String zdr_custodian;
public String getzdr_custodian()
{
	return zdr_custodian;
}
public void setzdr_custodian(String zdr_custodian)
{
	this.zdr_custodian = zdr_custodian;
}
@Column(name = "zdr_custodiantel", nullable = true)
public String zdr_custodiantel;
public String getzdr_custodiantel()
{
	return zdr_custodiantel;
}
public void setzdr_custodiantel(String zdr_custodiantel)
{
	this.zdr_custodiantel = zdr_custodiantel;
}
@Column(name = "jzr_correctioncontent", nullable = true)
public String jzr_correctioncontent;
public String getjzr_correctioncontent()
{
	return jzr_correctioncontent;
}
public void setjzr_correctioncontent(String jzr_correctioncontent)
{
	this.jzr_correctioncontent = jzr_correctioncontent;
}
@Column(name = "jzr_correctiondate", nullable = true)
public Date jzr_correctiondate;
public Date getjzr_correctiondate()
{
	return jzr_correctiondate;
}
public void setjzr_correctiondate(Date jzr_correctiondate)
{
	this.jzr_correctiondate = jzr_correctiondate;
}
@Column(name = "jzr_correctionaddress", nullable = true)
public String jzr_correctionaddress;
public String getjzr_correctionaddress()
{
	return jzr_correctionaddress;
}
public void setjzr_correctionaddress(String jzr_correctionaddress)
{
	this.jzr_correctionaddress = jzr_correctionaddress;
}
@Column(name = "sy_unemployedreason", nullable = true)
public String sy_unemployedreason;
public String getsy_unemployedreason()
{
	return sy_unemployedreason;
}
public void setsy_unemployedreason(String sy_unemployedreason)
{
	this.sy_unemployedreason = sy_unemployedreason;
}
@Column(name = "sy_unemployedreemployment", nullable = true)
public String sy_unemployedreemployment;
public String getsy_unemployedreemployment()
{
	return sy_unemployedreemployment;
}
public void setsy_unemployedreemployment(String sy_unemployedreemployment)
{
	this.sy_unemployedreemployment = sy_unemployedreemployment;
}
@Column(name = "sy_reemploymentunit", nullable = true)
public String sy_reemploymentunit;
public String getsy_reemploymentunit()
{
	return sy_reemploymentunit;
}
public void setsy_reemploymentunit(String sy_reemploymentunit)
{
	this.sy_reemploymentunit = sy_reemploymentunit;
}
@Column(name = "ylfn_firstmarriagedate", nullable = true)
public Date ylfn_firstmarriagedate;
public Date getylfn_firstmarriagedate()
{
	return ylfn_firstmarriagedate;
}
public void setylfn_firstmarriagedate(Date ylfn_firstmarriagedate)
{
	this.ylfn_firstmarriagedate = ylfn_firstmarriagedate;
}
@Column(name = "ylfn_children", nullable = true)
public Integer ylfn_children;
public Integer getylfn_children()
{
	return ylfn_children;
}
public void setylfn_children(Integer ylfn_children)
{
	this.ylfn_children = ylfn_children;
}
@Column(name = "ylfn_bornchildren", nullable = true)
public Integer ylfn_bornchildren;
public Integer getylfn_bornchildren()
{
	return ylfn_bornchildren;
}
public void setylfn_bornchildren(Integer ylfn_bornchildren)
{
	this.ylfn_bornchildren = ylfn_bornchildren;
}
@Column(name = "ylfn_pregnancy", nullable = true)
public String ylfn_pregnancy;
public String getylfn_pregnancy()
{
	return ylfn_pregnancy;
}
public void setylfn_pregnancy(String ylfn_pregnancy)
{
	this.ylfn_pregnancy = ylfn_pregnancy;
}
@Column(name = "ylfn_lastmenstruation", nullable = true)
public Date ylfn_lastmenstruation;
public Date getylfn_lastmenstruation()
{
	return ylfn_lastmenstruation;
}
public void setylfn_lastmenstruation(Date ylfn_lastmenstruation)
{
	this.ylfn_lastmenstruation = ylfn_lastmenstruation;
}
@Column(name = "ylfn_terminationpregnancy", nullable = true)
public Date ylfn_terminationpregnancy;
public Date getylfn_terminationpregnancy()
{
	return ylfn_terminationpregnancy;
}
public void setylfn_terminationpregnancy(Date ylfn_terminationpregnancy)
{
	this.ylfn_terminationpregnancy = ylfn_terminationpregnancy;
}
@Column(name = "lnr_oldid", nullable = true)
public String lnr_oldid;
public String getlnr_oldid()
{
	return lnr_oldid;
}
public void setlnr_oldid(String lnr_oldid)
{
	this.lnr_oldid = lnr_oldid;
}
@Column(name = "lnr_economicsources", nullable = true)
public String lnr_economicsources;
public String getlnr_economicsources()
{
	return lnr_economicsources;
}
public void setlnr_economicsources(String lnr_economicsources)
{
	this.lnr_economicsources = lnr_economicsources;
}
@Column(name = "lnr_livingconditions", nullable = true)
public String lnr_livingconditions;
public String getlnr_livingconditions()
{
	return lnr_livingconditions;
}
public void setlnr_livingconditions(String lnr_livingconditions)
{
	this.lnr_livingconditions = lnr_livingconditions;
}
@Column(name = "lnr_selfcare", nullable = true)
public String lnr_selfcare;
public String getlnr_selfcare()
{
	return lnr_selfcare;
}
public void setlnr_selfcare(String lnr_selfcare)
{
	this.lnr_selfcare = lnr_selfcare;
}
@Column(name = "lnr_specialsubsidies", nullable = true)
public String lnr_specialsubsidies;
public String getlnr_specialsubsidies()
{
	return lnr_specialsubsidies;
}
public void setlnr_specialsubsidies(String lnr_specialsubsidies)
{
	this.lnr_specialsubsidies = lnr_specialsubsidies;
}
@Column(name = "lnr_emergencycontact", nullable = true)
public String lnr_emergencycontact;
public String getlnr_emergencycontact()
{
	return lnr_emergencycontact;
}
public void setlnr_emergencycontact(String lnr_emergencycontact)
{
	this.lnr_emergencycontact = lnr_emergencycontact;
}
@Column(name = "lnr_emergencycontactrelated", nullable = true)
public String lnr_emergencycontactrelated;
public String getlnr_emergencycontactrelated()
{
	return lnr_emergencycontactrelated;
}
public void setlnr_emergencycontactrelated(String lnr_emergencycontactrelated)
{
	this.lnr_emergencycontactrelated = lnr_emergencycontactrelated;
}
@Column(name = "lnr_emergencycontacttel", nullable = true)
public String lnr_emergencycontacttel;
public String getlnr_emergencycontacttel()
{
	return lnr_emergencycontacttel;
}
public void setlnr_emergencycontacttel(String lnr_emergencycontacttel)
{
	this.lnr_emergencycontacttel = lnr_emergencycontacttel;
}
@Column(name = "lnr_physicalcondition", nullable = true)
public String lnr_physicalcondition;
public String getlnr_physicalcondition()
{
	return lnr_physicalcondition;
}
public void setlnr_physicalcondition(String lnr_physicalcondition)
{
	this.lnr_physicalcondition = lnr_physicalcondition;
}
@Column(name = "lnr_medicationcondition", nullable = true)
public String lnr_medicationcondition;
public String getlnr_medicationcondition()
{
	return lnr_medicationcondition;
}
public void setlnr_medicationcondition(String lnr_medicationcondition)
{
	this.lnr_medicationcondition = lnr_medicationcondition;
}
@Column(name = "cj_disabilitytype", nullable = true)
public String cj_disabilitytype;
public String getcj_disabilitytype()
{
	return cj_disabilitytype;
}
public void setcj_disabilitytype(String cj_disabilitytype)
{
	this.cj_disabilitytype = cj_disabilitytype;
}
@Column(name = "cj_disabilitylevel", nullable = true)
public String cj_disabilitylevel;
public String getcj_disabilitylevel()
{
	return cj_disabilitylevel;
}
public void setcj_disabilitylevel(String cj_disabilitylevel)
{
	this.cj_disabilitylevel = cj_disabilitylevel;
}
@Column(name = "cj_disabilityreason", nullable = true)
public String cj_disabilityreason;
public String getcj_disabilityreason()
{
	return cj_disabilityreason;
}
public void setcj_disabilityreason(String cj_disabilityreason)
{
	this.cj_disabilityreason = cj_disabilityreason;
}
@Column(name = "cj_disabilityemployment", nullable = true)
public String cj_disabilityemployment;
public String getcj_disabilityemployment()
{
	return cj_disabilityemployment;
}
public void setcj_disabilityemployment(String cj_disabilityemployment)
{
	this.cj_disabilityemployment = cj_disabilityemployment;
}
@Column(name = "jsb_type", nullable = true)
public String jsb_type;
public String getjsb_type()
{
	return jsb_type;
}
public void setjsb_type(String jsb_type)
{
	this.jsb_type = jsb_type;
}
@Column(name = "jsb_medicalhistory", nullable = true)
public String jsb_medicalhistory;
public String getjsb_medicalhistory()
{
	return jsb_medicalhistory;
}
public void setjsb_medicalhistory(String jsb_medicalhistory)
{
	this.jsb_medicalhistory = jsb_medicalhistory;
}/*
@Column(name = "jhr_name", nullable = true)
public String jhr_name;
public String getjhr_name()
{
	return jhr_name;
}
public void setjhr_name(String jhr_name)
{
	this.jhr_name = jhr_name;
}
@Column(name = "hjr_tel", nullable = true)
public String hjr_tel;
public String gethjr_tel()
{
	return hjr_tel;
}
public void sethjr_tel(String hjr_tel)
{
	this.hjr_tel = hjr_tel;
}*/
@Column(name = "zyz_certificate_id", nullable = true)
public String zyz_certificate_id;
public String getzyz_certificate_id()
{
	return zyz_certificate_id;
}
public void setzyz_certificate_id(String zyz_certificate_id)
{
	this.zyz_certificate_id = zyz_certificate_id;
}
@Column(name = "zyz_special_skill", nullable = true)
public String zyz_special_skill;
public String getzyz_special_skill()
{
	return zyz_special_skill;
}
public void setzyz_special_skill(String zyz_special_skill)
{
	this.zyz_special_skill = zyz_special_skill;
}
@Column(name = "jmdb_representative_level", nullable = true)
public String jmdb_representative_level;
public String getjmdb_representative_level()
{
	return jmdb_representative_level;
}
public void setjmdb_representative_level(String jmdb_representative_level)
{
	this.jmdb_representative_level = jmdb_representative_level;
}
@Column(name = "jmdb_startofterm", nullable = true)
public Date jmdb_startofterm;
public Date getjmdb_startofterm()
{
	return jmdb_startofterm;
}
public void setjmdb_startofterm(Date jmdb_startofterm)
{
	this.jmdb_startofterm = jmdb_startofterm;
}
@Column(name = "jmdb_endofterm", nullable = true)
public Date jmdb_endofterm;
public Date getjmdb_endofterm()
{
	return jmdb_endofterm;
}
public void setjmdb_endofterm(Date jmdb_endofterm)
{
	this.jmdb_endofterm = jmdb_endofterm;
}
@Column(name = "wtgg_skill_type", nullable = true)
public String wtgg_skill_type;
public String getwtgg_skill_type()
{
	return wtgg_skill_type;
}
public void setwtgg_skill_type(String wtgg_skill_type)
{
	this.wtgg_skill_type = wtgg_skill_type;
}
@Column(name = "wtgg_special_skill", nullable = true)
public String wtgg_special_skill;
public String getwtgg_special_skill()
{
	return wtgg_special_skill;
}
public void setwtgg_special_skill(String wtgg_special_skill)
{
	this.wtgg_special_skill = wtgg_special_skill;
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

