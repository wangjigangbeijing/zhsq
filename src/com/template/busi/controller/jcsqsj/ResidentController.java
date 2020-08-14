package com.template.busi.controller.jcsqsj;
import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import com.template.model.jcsqsj.Resident;
import com.template.service.jcsqsj.ResidentService;
import com.template.util.HqlFilter;
import com.template.util.ConstValue;
import com.template.util.Utility;
import com.template.util.TimeUtil;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
@Controller
@RequestMapping("residentController")
public class ResidentController {
	private static Logger logger = Logger.getLogger(ResidentController.class);
	@Autowired
	private  HttpServletRequest request;
	@Autowired
	private ResidentService residentService;
@RequestMapping(value="addOrUpdate",method = RequestMethod.POST,produces="text/html;charset=UTF-8")
@ResponseBody
public String addOrUpdate(String id,String dataid,String name,String identitytype,String idnumber,String characteristics,String ofcommunity,String ofresidebuilding,String ofunit,String ofroom,String offamily,String sex,String residencestatus,String ishouseholder,String relationshiphouseholder,String registrationcategory,String nationality,String homeplace,String birthday,String age,String nation,String politicalstatus,String usedname,String residenceaddress,String residentialaddress,String education,String professionstatus,String professiontype,String workunit,String job,String issocialsecurity,String email,String tel,String fax,String mobile,String eligiousbelief,String marriage,String isforeignmarriage,String health,String military,String stature,String blood,String hobby,String reasonnotresidence,String custodian,String custodiantel,String custodianrelationship,String custodianincommunity,String pictures,String note,String dy_partymembertype,String dy_of_partyorganization,String dy_joinpartydate,String dy_inpartydate,String dy_membership,String dy_islost,String dy_lostdate,String dy_movemember,String dy_moveto,String zdr_type,String zdr_reason,String zdr_custodian,String zdr_custodiantel,String jzr_correctioncontent,String jzr_correctiondate,String jzr_correctionaddress,String sy_unemployedreason,String sy_unemployedreemployment,String sy_reemploymentunit,String ylfn_firstmarriagedate,Integer ylfn_children,Integer ylfn_bornchildren,String ylfn_pregnancy,String ylfn_lastmenstruation,String ylfn_terminationpregnancy,String lnr_oldid,String lnr_economicsources,String lnr_livingconditions,String lnr_selfcare,String lnr_specialsubsidies,String lnr_emergencycontact,String lnr_emergencycontactrelated,String lnr_emergencycontacttel,String lnr_physicalcondition,String lnr_medicationcondition,String cj_disabilitytype,String cj_disabilitylevel,String cj_disabilityreason,String cj_disabilityemployment,String jsb_type,String jsb_medicalhistory,String jhr_name,String hjr_tel,String zyz_certificate_id,String zyz_special_skill,String jmdb_representative_level,String jmdb_startofterm,String jmdb_endofterm,String wtgg_skill_type,String wtgg_special_skill)
{
	JSONObject jsonObj = new JSONObject();
	try
	{
		Resident resident;
		if(id==null || id.equalsIgnoreCase(""))
		{
			resident = new Resident();
			resident.setId(Utility.getUniStr());
		}
		else
		{
			resident = residentService.getById(id);
		}
		resident.setdataid(dataid);
		resident.setname(name);
		resident.setidentitytype(identitytype);
		resident.setidnumber(idnumber);
		resident.setcharacteristics(characteristics);
		resident.setofcommunity(ofcommunity);
		resident.setofresidebuilding(ofresidebuilding);
		resident.setofunit(ofunit);
		resident.setofroom(ofroom);
		resident.setoffamily(offamily);
		resident.setsex(sex);
		resident.setresidencestatus(residencestatus);
		resident.setishouseholder(ishouseholder);
		resident.setrelationshiphouseholder(relationshiphouseholder);
		resident.setregistrationcategory(registrationcategory);
		resident.setnationality(nationality);
		resident.sethomeplace(homeplace);
		resident.setbirthday(TimeUtil.parseDate(birthday, "yyyy-MM-dd"));
		resident.setage(age);
		resident.setnation(nation);
		resident.setpoliticalstatus(politicalstatus);
		resident.setusedname(usedname);
		resident.setresidenceaddress(residenceaddress);
		resident.setresidentialaddress(residentialaddress);
		resident.seteducation(education);
		resident.setprofessionstatus(professionstatus);
		resident.setprofessiontype(professiontype);
		resident.setworkunit(workunit);
		resident.setjob(job);
		resident.setissocialsecurity(issocialsecurity);
		resident.setemail(email);
		resident.settel(tel);
		resident.setfax(fax);
		resident.setmobile(mobile);
		resident.seteligiousbelief(eligiousbelief);
		resident.setmarriage(marriage);
		resident.setisforeignmarriage(isforeignmarriage);
		resident.sethealth(health);
		resident.setmilitary(military);
		resident.setstature(stature);
		resident.setblood(blood);
		resident.sethobby(hobby);
		resident.setreasonnotresidence(reasonnotresidence);
		resident.setcustodian(custodian);
		resident.setcustodiantel(custodiantel);
		resident.setcustodianrelationship(custodianrelationship);
		resident.setcustodianincommunity(custodianincommunity);
		resident.setpictures(pictures);
		resident.setnote(note);
		resident.setdy_partymembertype(dy_partymembertype);
		resident.setdy_of_partyorganization(dy_of_partyorganization);
		resident.setdy_joinpartydate(TimeUtil.parseDate(dy_joinpartydate, "yyyy-MM-dd"));
		resident.setdy_inpartydate(TimeUtil.parseDate(dy_inpartydate, "yyyy-MM-dd"));
		resident.setdy_membership(dy_membership);
		resident.setdy_islost(dy_islost);
		resident.setdy_lostdate(TimeUtil.parseDate(dy_lostdate, "yyyy-MM-dd"));
		resident.setdy_movemember(dy_movemember);
		resident.setdy_moveto(dy_moveto);
		resident.setzdr_type(zdr_type);
		resident.setzdr_reason(zdr_reason);
		resident.setzdr_custodian(zdr_custodian);
		resident.setzdr_custodiantel(zdr_custodiantel);
		resident.setjzr_correctioncontent(jzr_correctioncontent);
		resident.setjzr_correctiondate(TimeUtil.parseDate(jzr_correctiondate, "yyyy-MM-dd"));
		resident.setjzr_correctionaddress(jzr_correctionaddress);
		resident.setsy_unemployedreason(sy_unemployedreason);
		resident.setsy_unemployedreemployment(sy_unemployedreemployment);
		resident.setsy_reemploymentunit(sy_reemploymentunit);
		resident.setylfn_firstmarriagedate(TimeUtil.parseDate(ylfn_firstmarriagedate, "yyyy-MM-dd"));
		resident.setylfn_children(ylfn_children);
		resident.setylfn_bornchildren(ylfn_bornchildren);
		resident.setylfn_pregnancy(ylfn_pregnancy);
		resident.setylfn_lastmenstruation(TimeUtil.parseDate(ylfn_lastmenstruation, "yyyy-MM-dd"));
		resident.setylfn_terminationpregnancy(TimeUtil.parseDate(ylfn_terminationpregnancy, "yyyy-MM-dd"));
		resident.setlnr_oldid(lnr_oldid);
		resident.setlnr_economicsources(lnr_economicsources);
		resident.setlnr_livingconditions(lnr_livingconditions);
		resident.setlnr_selfcare(lnr_selfcare);
		resident.setlnr_specialsubsidies(lnr_specialsubsidies);
		resident.setlnr_emergencycontact(lnr_emergencycontact);
		resident.setlnr_emergencycontactrelated(lnr_emergencycontactrelated);
		resident.setlnr_emergencycontacttel(lnr_emergencycontacttel);
		resident.setlnr_physicalcondition(lnr_physicalcondition);
		resident.setlnr_medicationcondition(lnr_medicationcondition);
		resident.setcj_disabilitytype(cj_disabilitytype);
		resident.setcj_disabilitylevel(cj_disabilitylevel);
		resident.setcj_disabilityreason(cj_disabilityreason);
		resident.setcj_disabilityemployment(cj_disabilityemployment);
		resident.setjsb_type(jsb_type);
		resident.setjsb_medicalhistory(jsb_medicalhistory);
		//resident.setjhr_name(jhr_name);
		//resident.sethjr_tel(hjr_tel);
		resident.setzyz_certificate_id(zyz_certificate_id);
		resident.setzyz_special_skill(zyz_special_skill);
		resident.setjmdb_representative_level(jmdb_representative_level);
		resident.setjmdb_startofterm(TimeUtil.parseDate(jmdb_startofterm, "yyyy-MM-dd"));
		resident.setjmdb_endofterm(TimeUtil.parseDate(jmdb_endofterm, "yyyy-MM-dd"));
		resident.setwtgg_skill_type(wtgg_skill_type);
		resident.setwtgg_special_skill(wtgg_special_skill);

		String userId = (String)request.getSession().getAttribute(ConstValue.SESSION_USER_ID);
		
		String organization = "";
		if(ConstValue.userToOrgMap.containsKey(userId))
			organization = ConstValue.userToOrgMap.get(userId);
		resident.setowner(organization);
		
        residentService.save(resident);
        jsonObj.put("success", true);
	}
	catch(Exception e)
	{
		logger.error(e.getMessage(),e);
		jsonObj.put("success", false);
		jsonObj.put("errMsg", e.getMessage());
	}
    return jsonObj.toString();
}
@RequestMapping(value="delete",method = RequestMethod.POST,produces="text/html;charset=UTF-8")
@ResponseBody
public String delete(String id)
{
	logger.debug("delete "+id);
	JSONObject jsonObj = new JSONObject();
	try
	{
		Resident resident = residentService.getById(id);
		residentService.delete(resident);
        jsonObj.put("success", true);
	}
	catch(Exception e)
	{
		logger.error(e.getMessage(),e);
		jsonObj.put("success", false);
		jsonObj.put("errMsg", e.getMessage());
	}
    return jsonObj.toString();
}
@RequestMapping(value="load",method = RequestMethod.GET,produces="text/html;charset=UTF-8")
@ResponseBody
public String load(String name,String identitytype,String idnumber,String characteristics,String ofcommunity,String ofresidebuilding,
		String ofunit,String ofroom,String offamily,String sex,String residencestatus,String ishouseholder,String relationshiphouseholder,
		String registrationcategory,String birthday,String age,String nation,String politicalstatus,String education,String professionstatus,
		String professiontype,String issocialsecurity,String tel,String mobile,String marriage,String isforeignmarriage,String health,String blood,
		String custodianincommunity,String dy_partymembertype,String dy_of_partyorganization,String dy_joinpartydate,String dy_membership,String dy_islost,
		String dy_movemember,String zdr_type,String jzr_correctioncontent,String sy_unemployedreemployment,String lnr_economicsources,String lnr_livingconditions,
		String cj_disabilitytype,String cj_disabilitylevel,String jsb_type)
{
	JSONObject jsonObj = new JSONObject();
	try
	{
		HqlFilter hqlFilter = new HqlFilter();
		if(name != null && name.equalsIgnoreCase("") == false && name.equalsIgnoreCase("null") == false)
		{
			hqlFilter.addQryCond("name", HqlFilter.Operator.LIKE, "%"+name+"%");
		}
		if(identitytype != null && identitytype.equalsIgnoreCase("") == false && identitytype.equalsIgnoreCase("null") == false)
		{
			hqlFilter.addQryCond("identitytype", HqlFilter.Operator.EQ, identitytype);
		}
		if(idnumber != null && idnumber.equalsIgnoreCase("") == false && idnumber.equalsIgnoreCase("null") == false)
		{
			hqlFilter.addQryCond("idnumber", HqlFilter.Operator.LIKE, "%"+idnumber+"%");
		}
		if(characteristics != null && characteristics.equalsIgnoreCase("") == false && characteristics.equalsIgnoreCase("null") == false)
		{
			//hqlFilter.addQryCond("characteristics", HqlFilter.Operator.EQ, characteristics);
			
			String [] charArr = characteristics.split(",");
			
			//ArrayList<String> charArrList = (ArrayList<String>) Arrays.asList(charArr);
			
			ArrayList<String> charArrList = new ArrayList<String>();
			
			for(int i=0;i<charArr.length;i++)
			{
				if(charArr[i].equalsIgnoreCase("") == false)				
					charArrList.add("%"+charArr[i]+"%");
			}
			
			hqlFilter.addOrCondGroup("characteristics", HqlFilter.Operator.LIKE, charArrList);
		}
		if(ofcommunity != null && ofcommunity.equalsIgnoreCase("") == false && ofcommunity.equalsIgnoreCase("null") == false)
		{
			hqlFilter.addQryCond("ofcommunity", HqlFilter.Operator.LIKE, "%"+ofcommunity+"%");
		}
		if(ofresidebuilding != null && ofresidebuilding.equalsIgnoreCase("") == false && ofresidebuilding.equalsIgnoreCase("null") == false)
		{
			hqlFilter.addQryCond("ofresidebuilding", HqlFilter.Operator.LIKE, "%"+ofresidebuilding+"%");
		}
		if(ofunit != null && ofunit.equalsIgnoreCase("") == false && ofunit.equalsIgnoreCase("null") == false)
		{
			hqlFilter.addQryCond("ofunit", HqlFilter.Operator.LIKE, "%"+ofunit+"%");
		}
		if(ofroom != null && ofroom.equalsIgnoreCase("") == false && ofroom.equalsIgnoreCase("null") == false)
		{
			hqlFilter.addQryCond("ofroom", HqlFilter.Operator.LIKE, "%"+ofroom+"%");
		}
		if(offamily != null && offamily.equalsIgnoreCase("") == false && offamily.equalsIgnoreCase("null") == false)
		{
			hqlFilter.addQryCond("offamily", HqlFilter.Operator.LIKE, "%"+offamily+"%");
		}
		if(sex != null && sex.equalsIgnoreCase("") == false && sex.equalsIgnoreCase("null") == false)
		{
			hqlFilter.addQryCond("sex", HqlFilter.Operator.LIKE, "%"+sex+"%");
		}
		if(residencestatus != null && residencestatus.equalsIgnoreCase("") == false && residencestatus.equalsIgnoreCase("null") == false)
		{
			hqlFilter.addQryCond("residencestatus", HqlFilter.Operator.EQ, residencestatus);
		}
		if(ishouseholder != null && ishouseholder.equalsIgnoreCase("") == false && ishouseholder.equalsIgnoreCase("null") == false)
		{
			hqlFilter.addQryCond("ishouseholder", HqlFilter.Operator.LIKE, "%"+ishouseholder+"%");
		}
		if(relationshiphouseholder != null && relationshiphouseholder.equalsIgnoreCase("") == false && relationshiphouseholder.equalsIgnoreCase("null") == false)
		{
			hqlFilter.addQryCond("relationshiphouseholder", HqlFilter.Operator.EQ, relationshiphouseholder);
		}
		if(registrationcategory != null && registrationcategory.equalsIgnoreCase("") == false && registrationcategory.equalsIgnoreCase("null") == false)
		{
			hqlFilter.addQryCond("registrationcategory", HqlFilter.Operator.EQ, registrationcategory);
		}
		if(birthday != null && birthday.equalsIgnoreCase("") == false && birthday.equalsIgnoreCase("null") == false)
		{
			hqlFilter.addQryCond("birthday", HqlFilter.Operator.LIKE, "%"+birthday+"%");
		}
		if(age != null && age.equalsIgnoreCase("") == false && age.equalsIgnoreCase("null") == false)
		{
			hqlFilter.addQryCond("age", HqlFilter.Operator.LIKE, "%"+age+"%");
		}
		if(nation != null && nation.equalsIgnoreCase("") == false && nation.equalsIgnoreCase("null") == false)
		{
			hqlFilter.addQryCond("nation", HqlFilter.Operator.LIKE, "%"+nation+"%");
		}
		if(politicalstatus != null && politicalstatus.equalsIgnoreCase("") == false && politicalstatus.equalsIgnoreCase("null") == false)
		{
			hqlFilter.addQryCond("politicalstatus", HqlFilter.Operator.LIKE, "%"+politicalstatus+"%");
		}
		if(education != null && education.equalsIgnoreCase("") == false && education.equalsIgnoreCase("null") == false)
		{
			hqlFilter.addQryCond("education", HqlFilter.Operator.EQ, education);
		}
		if(professionstatus != null && professionstatus.equalsIgnoreCase("") == false && professionstatus.equalsIgnoreCase("null") == false)
		{
			hqlFilter.addQryCond("professionstatus", HqlFilter.Operator.EQ, professionstatus);
		}
		if(professiontype != null && professiontype.equalsIgnoreCase("") == false && professiontype.equalsIgnoreCase("null") == false)
		{
			hqlFilter.addQryCond("professiontype", HqlFilter.Operator.EQ, professiontype);
		}
		if(issocialsecurity != null && issocialsecurity.equalsIgnoreCase("") == false && issocialsecurity.equalsIgnoreCase("null") == false)
		{
			hqlFilter.addQryCond("issocialsecurity", HqlFilter.Operator.LIKE, "%"+issocialsecurity+"%");
		}
		if(tel != null && tel.equalsIgnoreCase("") == false && tel.equalsIgnoreCase("null") == false)
		{
			hqlFilter.addQryCond("tel", HqlFilter.Operator.LIKE, "%"+tel+"%");
		}
		if(mobile != null && mobile.equalsIgnoreCase("") == false && mobile.equalsIgnoreCase("null") == false)
		{
			hqlFilter.addQryCond("mobile", HqlFilter.Operator.LIKE, "%"+mobile+"%");
		}
		if(marriage != null && marriage.equalsIgnoreCase("") == false && marriage.equalsIgnoreCase("null") == false)
		{
			hqlFilter.addQryCond("marriage", HqlFilter.Operator.LIKE, "%"+marriage+"%");
		}
		if(isforeignmarriage != null && isforeignmarriage.equalsIgnoreCase("") == false && isforeignmarriage.equalsIgnoreCase("null") == false)
		{
			hqlFilter.addQryCond("isforeignmarriage", HqlFilter.Operator.LIKE, "%"+isforeignmarriage+"%");
		}
		if(health != null && health.equalsIgnoreCase("") == false && health.equalsIgnoreCase("null") == false)
		{
			hqlFilter.addQryCond("health", HqlFilter.Operator.LIKE, "%"+health+"%");
		}
		if(blood != null && blood.equalsIgnoreCase("") == false && blood.equalsIgnoreCase("null") == false)
		{
			hqlFilter.addQryCond("blood", HqlFilter.Operator.LIKE, "%"+blood+"%");
		}
		if(custodianincommunity != null && custodianincommunity.equalsIgnoreCase("") == false && custodianincommunity.equalsIgnoreCase("null") == false)
		{
			hqlFilter.addQryCond("custodianincommunity", HqlFilter.Operator.LIKE, "%"+custodianincommunity+"%");
		}
		if(dy_partymembertype != null && dy_partymembertype.equalsIgnoreCase("") == false && dy_partymembertype.equalsIgnoreCase("null") == false)
		{
			hqlFilter.addQryCond("dy_partymembertype", HqlFilter.Operator.LIKE, "%"+dy_partymembertype+"%");
		}
		if(dy_of_partyorganization != null && dy_of_partyorganization.equalsIgnoreCase("") == false && dy_of_partyorganization.equalsIgnoreCase("null") == false)
		{
			hqlFilter.addQryCond("dy_of_partyorganization", HqlFilter.Operator.LIKE, "%"+dy_of_partyorganization+"%");
		}
		if(dy_joinpartydate != null && dy_joinpartydate.equalsIgnoreCase("") == false && dy_joinpartydate.equalsIgnoreCase("null") == false)
		{
			hqlFilter.addQryCond("dy_joinpartydate", HqlFilter.Operator.LIKE, "%"+dy_joinpartydate+"%");
		}
		if(dy_membership != null && dy_membership.equalsIgnoreCase("") == false && dy_membership.equalsIgnoreCase("null") == false)
		{
			hqlFilter.addQryCond("dy_membership", HqlFilter.Operator.EQ, dy_membership);
		}
		if(dy_islost != null && dy_islost.equalsIgnoreCase("") == false && dy_islost.equalsIgnoreCase("null") == false)
		{
			hqlFilter.addQryCond("dy_islost", HqlFilter.Operator.LIKE, "%"+dy_islost+"%");
		}
		if(dy_movemember != null && dy_movemember.equalsIgnoreCase("") == false && dy_movemember.equalsIgnoreCase("null") == false)
		{
			hqlFilter.addQryCond("dy_movemember", HqlFilter.Operator.LIKE, "%"+dy_movemember+"%");
		}
		if(zdr_type != null && zdr_type.equalsIgnoreCase("") == false && zdr_type.equalsIgnoreCase("null") == false)
		{
			hqlFilter.addQryCond("zdr_type", HqlFilter.Operator.EQ, zdr_type);
		}
		if(jzr_correctioncontent != null && jzr_correctioncontent.equalsIgnoreCase("") == false && jzr_correctioncontent.equalsIgnoreCase("null") == false)
		{
			hqlFilter.addQryCond("jzr_correctioncontent", HqlFilter.Operator.LIKE, "%"+jzr_correctioncontent+"%");
		}
		if(sy_unemployedreemployment != null && sy_unemployedreemployment.equalsIgnoreCase("") == false && sy_unemployedreemployment.equalsIgnoreCase("null") == false)
		{
			hqlFilter.addQryCond("sy_unemployedreemployment", HqlFilter.Operator.LIKE, "%"+sy_unemployedreemployment+"%");
		}
		if(lnr_economicsources != null && lnr_economicsources.equalsIgnoreCase("") == false && lnr_economicsources.equalsIgnoreCase("null") == false)
		{
			hqlFilter.addQryCond("lnr_economicsources", HqlFilter.Operator.EQ, lnr_economicsources);
		}
		if(lnr_livingconditions != null && lnr_livingconditions.equalsIgnoreCase("") == false && lnr_livingconditions.equalsIgnoreCase("null") == false)
		{
			hqlFilter.addQryCond("lnr_livingconditions", HqlFilter.Operator.EQ, lnr_livingconditions);
		}
		if(cj_disabilitytype != null && cj_disabilitytype.equalsIgnoreCase("") == false && cj_disabilitytype.equalsIgnoreCase("null") == false)
		{
			hqlFilter.addQryCond("cj_disabilitytype", HqlFilter.Operator.EQ, cj_disabilitytype);
		}
		if(cj_disabilitylevel != null && cj_disabilitylevel.equalsIgnoreCase("") == false && cj_disabilitylevel.equalsIgnoreCase("null") == false)
		{
			hqlFilter.addQryCond("cj_disabilitylevel", HqlFilter.Operator.EQ, cj_disabilitylevel);
		}
		if(jsb_type != null && jsb_type.equalsIgnoreCase("") == false && jsb_type.equalsIgnoreCase("null") == false)
		{
			hqlFilter.addQryCond("jsb_type", HqlFilter.Operator.EQ, jsb_type);
		}


String userId = (String)request.getSession().getAttribute(ConstValue.HTTP_HEADER_USERID);

String organization = "";
if(ConstValue.userToOrgMap.containsKey(userId))
	organization = ConstValue.userToOrgMap.get(userId);

ArrayList<String> alOrg = new ArrayList<String>(); 

if(organization != null && organization.equalsIgnoreCase("") == false)
{
	String [] organizationArr = organization.split(",");
	

	for(int i=0;i<organizationArr.length;i++)
	{
		alOrg.add("%"+organizationArr[i]+"%");
	}
}

if(alOrg != null && alOrg.size() != 0)
	hqlFilter.addOrCondGroup("owner", HqlFilter.Operator.LIKE, alOrg);

hqlFilter.setSort("created_at");
hqlFilter.setOrder("desc");

        List<Resident> listObj = residentService.findByFilter(hqlFilter);
        JSONArray jsonArr = new JSONArray();
        int iTotalCnt = 0;
		for(int i=0;i<listObj.size();i++)
		{
			Resident resident = listObj.get(i);
			JSONObject jsonTmp = new JSONObject();
			jsonTmp.put("id", resident.getId());
			jsonTmp.put("dataid",resident.getdataid());
			jsonTmp.put("name",resident.getname());
			jsonTmp.put("identitytype",resident.getidentitytype());
			jsonTmp.put("idnumber",resident.getidnumber());
			jsonTmp.put("characteristics",resident.getcharacteristics());
			jsonTmp.put("ofcommunity",resident.getofcommunity());
			jsonTmp.put("ofresidebuilding",resident.getofresidebuilding());
			jsonTmp.put("ofunit",resident.getofunit());
			jsonTmp.put("ofroom",resident.getofroom());
			
			if(ConstValue.hmDicMap.containsKey(resident.getofresidebuilding()))
			{
				jsonTmp.put("ofresidebuildingname",ConstValue.hmDicMap.get(resident.getofresidebuilding()));
			}
			
			jsonTmp.put("ofunit",resident.getofunit());
			jsonTmp.put("ofroom",resident.getofroom());

			if(ConstValue.hmDicMap.containsKey(resident.getofroom()))
			{
				jsonTmp.put("roomname",ConstValue.hmDicMap.get(resident.getofroom()));
			}
			
			jsonTmp.put("offamily",resident.getoffamily());
			if(ConstValue.hmDicMap.containsKey(resident.getoffamily()))
			{
				jsonTmp.put("offamilyname",ConstValue.hmDicMap.get(resident.getoffamily()));
			}
			
			jsonTmp.put("address",resident.getofcommunity() + resident.getofresidebuilding() + resident.getofunit() + resident.getofroom());
			
			jsonTmp.put("sex",resident.getsex());
			jsonTmp.put("residencestatus",resident.getresidencestatus());
			jsonTmp.put("ishouseholder",resident.getishouseholder());
			jsonTmp.put("relationshiphouseholder",resident.getrelationshiphouseholder());
			jsonTmp.put("registrationcategory",resident.getregistrationcategory());
			jsonTmp.put("nationality",resident.getnationality());
			jsonTmp.put("homeplace",resident.gethomeplace());
			if(resident.getbirthday() != null)
				jsonTmp.put("birthday",TimeUtil.formatDate(resident.getbirthday(),"yyyy-MM-dd"));
			else
				jsonTmp.put("birthday","-");
			jsonTmp.put("age",resident.getage());
			jsonTmp.put("nation",resident.getnation());
			jsonTmp.put("politicalstatus",resident.getpoliticalstatus());
			jsonTmp.put("usedname",resident.getusedname());
			jsonTmp.put("residenceaddress",resident.getresidenceaddress());
			jsonTmp.put("residentialaddress",resident.getresidentialaddress());
			jsonTmp.put("education",resident.geteducation());
			jsonTmp.put("professionstatus",resident.getprofessionstatus());
			jsonTmp.put("professiontype",resident.getprofessiontype());
			jsonTmp.put("workunit",resident.getworkunit());
			jsonTmp.put("job",resident.getjob());
			jsonTmp.put("issocialsecurity",resident.getissocialsecurity());
			jsonTmp.put("email",resident.getemail());
			jsonTmp.put("tel",resident.gettel());
			jsonTmp.put("fax",resident.getfax());
			jsonTmp.put("mobile",resident.getmobile());
			jsonTmp.put("eligiousbelief",resident.geteligiousbelief());
			jsonTmp.put("marriage",resident.getmarriage());
			jsonTmp.put("isforeignmarriage",resident.getisforeignmarriage());
			jsonTmp.put("health",resident.gethealth());
			jsonTmp.put("military",resident.getmilitary());
			jsonTmp.put("stature",resident.getstature());
			jsonTmp.put("blood",resident.getblood());
			jsonTmp.put("hobby",resident.gethobby());
			jsonTmp.put("reasonnotresidence",resident.getreasonnotresidence());
			jsonTmp.put("custodian",resident.getcustodian());
			jsonTmp.put("custodiantel",resident.getcustodiantel());
			jsonTmp.put("custodianrelationship",resident.getcustodianrelationship());
			jsonTmp.put("custodianincommunity",resident.getcustodianincommunity());
			jsonTmp.put("pictures",resident.getpictures());
			jsonTmp.put("note",resident.getnote());
			jsonTmp.put("dy_partymembertype",resident.getdy_partymembertype());
			jsonTmp.put("dy_of_partyorganization",resident.getdy_of_partyorganization());
			
			if(resident.getbirthday() != null)
				jsonTmp.put("dy_joinpartydate",TimeUtil.formatDate(resident.getdy_joinpartydate(),"yyyy-MM-dd"));
			else
				jsonTmp.put("dy_joinpartydate","-");
			
			if(resident.getbirthday() != null)
				jsonTmp.put("dy_inpartydate",TimeUtil.formatDate(resident.getdy_inpartydate(),"yyyy-MM-dd"));
			else
				jsonTmp.put("dy_inpartydate","-");
			
			jsonTmp.put("dy_membership",resident.getdy_membership());
			jsonTmp.put("dy_islost",resident.getdy_islost());
			
			if(resident.getbirthday() != null)
				jsonTmp.put("dy_lostdate",TimeUtil.formatDate(resident.getdy_lostdate(),"yyyy-MM-dd"));
			else
				jsonTmp.put("dy_lostdate","-");
			
			jsonTmp.put("dy_movemember",resident.getdy_movemember());
			jsonTmp.put("dy_moveto",resident.getdy_moveto());
			jsonTmp.put("zdr_type",resident.getzdr_type());
			jsonTmp.put("zdr_reason",resident.getzdr_reason());
			jsonTmp.put("zdr_custodian",resident.getzdr_custodian());
			jsonTmp.put("zdr_custodiantel",resident.getzdr_custodiantel());
			jsonTmp.put("jzr_correctioncontent",resident.getjzr_correctioncontent());
			
			if(resident.getbirthday() != null)
				jsonTmp.put("jzr_correctiondate",TimeUtil.formatDate(resident.getjzr_correctiondate(),"yyyy-MM-dd"));
			else
				jsonTmp.put("jzr_correctiondate","-");
			
			jsonTmp.put("jzr_correctionaddress",resident.getjzr_correctionaddress());
			jsonTmp.put("sy_unemployedreason",resident.getsy_unemployedreason());
			jsonTmp.put("sy_unemployedreemployment",resident.getsy_unemployedreemployment());
			jsonTmp.put("sy_reemploymentunit",resident.getsy_reemploymentunit());
			
			if(resident.getbirthday() != null)
				jsonTmp.put("ylfn_firstmarriagedate",TimeUtil.formatDate(resident.getylfn_firstmarriagedate(),"yyyy-MM-dd"));
			else
				jsonTmp.put("ylfn_firstmarriagedate","-");
			
			jsonTmp.put("ylfn_children",resident.getylfn_children());
			jsonTmp.put("ylfn_bornchildren",resident.getylfn_bornchildren());
			jsonTmp.put("ylfn_pregnancy",resident.getylfn_pregnancy());
			
			if(resident.getbirthday() != null)
				jsonTmp.put("ylfn_lastmenstruation",TimeUtil.formatDate(resident.getylfn_lastmenstruation(),"yyyy-MM-dd"));
			else
				jsonTmp.put("ylfn_lastmenstruation","-");
			
			if(resident.getbirthday() != null)
				jsonTmp.put("ylfn_terminationpregnancy",TimeUtil.formatDate(resident.getylfn_terminationpregnancy(),"yyyy-MM-dd"));
			else
				jsonTmp.put("ylfn_terminationpregnancy","-");
			
			jsonTmp.put("lnr_oldid",resident.getlnr_oldid());
			jsonTmp.put("lnr_economicsources",resident.getlnr_economicsources());
			jsonTmp.put("lnr_livingconditions",resident.getlnr_livingconditions());
			jsonTmp.put("lnr_selfcare",resident.getlnr_selfcare());
			jsonTmp.put("lnr_specialsubsidies",resident.getlnr_specialsubsidies());
			jsonTmp.put("lnr_emergencycontact",resident.getlnr_emergencycontact());
			jsonTmp.put("lnr_emergencycontactrelated",resident.getlnr_emergencycontactrelated());
			jsonTmp.put("lnr_emergencycontacttel",resident.getlnr_emergencycontacttel());
			jsonTmp.put("lnr_physicalcondition",resident.getlnr_physicalcondition());
			jsonTmp.put("lnr_medicationcondition",resident.getlnr_medicationcondition());
			jsonTmp.put("cj_disabilitytype",resident.getcj_disabilitytype());
			jsonTmp.put("cj_disabilitylevel",resident.getcj_disabilitylevel());
			jsonTmp.put("cj_disabilityreason",resident.getcj_disabilityreason());
			jsonTmp.put("cj_disabilityemployment",resident.getcj_disabilityemployment());
			jsonTmp.put("jsb_type",resident.getjsb_type());
			jsonTmp.put("jsb_medicalhistory",resident.getjsb_medicalhistory());
			//jsonTmp.put("jhr_name",resident.getjhr_name());
			//jsonTmp.put("hjr_tel",resident.gethjr_tel());
			jsonTmp.put("zyz_certificate_id",resident.getzyz_certificate_id());
			jsonTmp.put("zyz_special_skill",resident.getzyz_special_skill());
			jsonTmp.put("jmdb_representative_level",resident.getjmdb_representative_level());
			
			if(resident.getbirthday() != null)
				jsonTmp.put("jmdb_startofterm",TimeUtil.formatDate(resident.getjmdb_startofterm(),"yyyy-MM-dd"));
			else
				jsonTmp.put("jmdb_startofterm","-");
			
			if(resident.getbirthday() != null)
				jsonTmp.put("jmdb_endofterm",TimeUtil.formatDate(resident.getjmdb_endofterm(),"yyyy-MM-dd"));
			else
				jsonTmp.put("jmdb_endofterm","-");
			
			jsonTmp.put("wtgg_skill_type",resident.getwtgg_skill_type());
			jsonTmp.put("wtgg_special_skill",resident.getwtgg_special_skill());

       		jsonArr.put(jsonTmp);
        	iTotalCnt++;
		}
        jsonObj.put("totalCount", iTotalCnt);
        jsonObj.put("list", jsonArr);
        jsonObj.put("success", true);
	}
	catch(Exception e)
	{
		logger.error(e.getMessage(),e);
		jsonObj.put("success", false);
	}
    return jsonObj.toString();
}
@RequestMapping(value="get",method = {RequestMethod.POST,RequestMethod.GET},produces="text/html;charset=UTF-8")
@ResponseBody
public String get(String id)
{
	JSONObject jsonObj = new JSONObject();
	try
	{
		Resident resident = residentService.getById(id);
		if(resident != null)
		{
			jsonObj.put("dataid",resident.getdataid());
			jsonObj.put("name",resident.getname());
			jsonObj.put("identitytype",resident.getidentitytype());
			jsonObj.put("idnumber",resident.getidnumber());
			jsonObj.put("characteristics",resident.getcharacteristics());
			jsonObj.put("ofcommunity",resident.getofcommunity());
			jsonObj.put("ofresidebuilding",resident.getofresidebuilding());
			jsonObj.put("ofunit",resident.getofunit());
			jsonObj.put("ofroom",resident.getofroom());
			jsonObj.put("offamily",resident.getoffamily());
			jsonObj.put("sex",resident.getsex());
			jsonObj.put("residencestatus",resident.getresidencestatus());
			jsonObj.put("ishouseholder",resident.getishouseholder());
			jsonObj.put("relationshiphouseholder",resident.getrelationshiphouseholder());
			jsonObj.put("registrationcategory",resident.getregistrationcategory());
			jsonObj.put("nationality",resident.getnationality());
			jsonObj.put("homeplace",resident.gethomeplace());
			if(resident.getbirthday() != null)
				jsonObj.put("birthday",TimeUtil.formatDate(resident.getbirthday(),"yyyy-MM-dd"));
			else
				jsonObj.put("birthday","-");
			jsonObj.put("age",resident.getage());
			jsonObj.put("nation",resident.getnation());
			jsonObj.put("politicalstatus",resident.getpoliticalstatus());
			jsonObj.put("usedname",resident.getusedname());
			jsonObj.put("residenceaddress",resident.getresidenceaddress());
			jsonObj.put("residentialaddress",resident.getresidentialaddress());
			jsonObj.put("education",resident.geteducation());
			jsonObj.put("professionstatus",resident.getprofessionstatus());
			jsonObj.put("professiontype",resident.getprofessiontype());
			jsonObj.put("workunit",resident.getworkunit());
			jsonObj.put("job",resident.getjob());
			jsonObj.put("issocialsecurity",resident.getissocialsecurity());
			jsonObj.put("email",resident.getemail());
			jsonObj.put("tel",resident.gettel());
			jsonObj.put("fax",resident.getfax());
			jsonObj.put("mobile",resident.getmobile());
			jsonObj.put("eligiousbelief",resident.geteligiousbelief());
			jsonObj.put("marriage",resident.getmarriage());
			jsonObj.put("isforeignmarriage",resident.getisforeignmarriage());
			jsonObj.put("health",resident.gethealth());
			jsonObj.put("military",resident.getmilitary());
			jsonObj.put("stature",resident.getstature());
			jsonObj.put("blood",resident.getblood());
			jsonObj.put("hobby",resident.gethobby());
			jsonObj.put("reasonnotresidence",resident.getreasonnotresidence());
			jsonObj.put("custodian",resident.getcustodian());
			jsonObj.put("custodiantel",resident.getcustodiantel());
			jsonObj.put("custodianrelationship",resident.getcustodianrelationship());
			jsonObj.put("custodianincommunity",resident.getcustodianincommunity());
			jsonObj.put("pictures",resident.getpictures());
			jsonObj.put("note",resident.getnote());
			jsonObj.put("dy_partymembertype",resident.getdy_partymembertype());
			jsonObj.put("dy_of_partyorganization",resident.getdy_of_partyorganization());
			
			if(resident.getbirthday() != null)
				jsonObj.put("dy_joinpartydate",TimeUtil.formatDate(resident.getdy_joinpartydate(),"yyyy-MM-dd"));
			else
				jsonObj.put("dy_joinpartydate","-");
			
			if(resident.getdy_inpartydate() != null)
				jsonObj.put("dy_inpartydate",TimeUtil.formatDate(resident.getdy_inpartydate(),"yyyy-MM-dd"));
			else
				jsonObj.put("dy_inpartydate","-");
			
			jsonObj.put("dy_membership",resident.getdy_membership());
			jsonObj.put("dy_islost",resident.getdy_islost());
			
			if(resident.getdy_lostdate() != null)
				jsonObj.put("dy_lostdate",TimeUtil.formatDate(resident.getdy_lostdate(),"yyyy-MM-dd"));
			else
				jsonObj.put("dy_lostdate","-");
			
			jsonObj.put("dy_movemember",resident.getdy_movemember());
			jsonObj.put("dy_moveto",resident.getdy_moveto());
			jsonObj.put("zdr_type",resident.getzdr_type());
			jsonObj.put("zdr_reason",resident.getzdr_reason());
			jsonObj.put("zdr_custodian",resident.getzdr_custodian());
			jsonObj.put("zdr_custodiantel",resident.getzdr_custodiantel());
			jsonObj.put("jzr_correctioncontent",resident.getjzr_correctioncontent());
			
			if(resident.getjzr_correctiondate() != null)
				jsonObj.put("jzr_correctiondate",TimeUtil.formatDate(resident.getjzr_correctiondate(),"yyyy-MM-dd"));
			else
				jsonObj.put("jzr_correctiondate","-");
			
			jsonObj.put("jzr_correctionaddress",resident.getjzr_correctionaddress());
			jsonObj.put("sy_unemployedreason",resident.getsy_unemployedreason());
			jsonObj.put("sy_unemployedreemployment",resident.getsy_unemployedreemployment());
			jsonObj.put("sy_reemploymentunit",resident.getsy_reemploymentunit());
			
			if(resident.getylfn_firstmarriagedate() != null)
				jsonObj.put("ylfn_firstmarriagedate",TimeUtil.formatDate(resident.getylfn_firstmarriagedate(),"yyyy-MM-dd"));
			else
				jsonObj.put("ylfn_firstmarriagedate","-");
			
			jsonObj.put("ylfn_children",resident.getylfn_children());
			jsonObj.put("ylfn_bornchildren",resident.getylfn_bornchildren());
			jsonObj.put("ylfn_pregnancy",resident.getylfn_pregnancy());
			
			if(resident.getylfn_lastmenstruation() != null)
				jsonObj.put("ylfn_lastmenstruation",TimeUtil.formatDate(resident.getylfn_lastmenstruation(),"yyyy-MM-dd"));
			else
				jsonObj.put("ylfn_lastmenstruation","-");
			
			if(resident.getylfn_terminationpregnancy() != null)
				jsonObj.put("ylfn_terminationpregnancy",TimeUtil.formatDate(resident.getylfn_terminationpregnancy(),"yyyy-MM-dd"));
			else
				jsonObj.put("ylfn_terminationpregnancy","-");
			
			jsonObj.put("lnr_oldid",resident.getlnr_oldid());
			jsonObj.put("lnr_economicsources",resident.getlnr_economicsources());
			jsonObj.put("lnr_livingconditions",resident.getlnr_livingconditions());
			jsonObj.put("lnr_selfcare",resident.getlnr_selfcare());
			jsonObj.put("lnr_specialsubsidies",resident.getlnr_specialsubsidies());
			jsonObj.put("lnr_emergencycontact",resident.getlnr_emergencycontact());
			jsonObj.put("lnr_emergencycontactrelated",resident.getlnr_emergencycontactrelated());
			jsonObj.put("lnr_emergencycontacttel",resident.getlnr_emergencycontacttel());
			jsonObj.put("lnr_physicalcondition",resident.getlnr_physicalcondition());
			jsonObj.put("lnr_medicationcondition",resident.getlnr_medicationcondition());
			jsonObj.put("cj_disabilitytype",resident.getcj_disabilitytype());
			jsonObj.put("cj_disabilitylevel",resident.getcj_disabilitylevel());
			jsonObj.put("cj_disabilityreason",resident.getcj_disabilityreason());
			jsonObj.put("cj_disabilityemployment",resident.getcj_disabilityemployment());
			jsonObj.put("jsb_type",resident.getjsb_type());
			jsonObj.put("jsb_medicalhistory",resident.getjsb_medicalhistory());
			//jsonObj.put("jhr_name",resident.getjhr_name());
			//jsonObj.put("hjr_tel",resident.gethjr_tel());
			jsonObj.put("zyz_certificate_id",resident.getzyz_certificate_id());
			jsonObj.put("zyz_special_skill",resident.getzyz_special_skill());
			jsonObj.put("jmdb_representative_level",resident.getjmdb_representative_level());

			if(resident.getjmdb_startofterm() != null)
				jsonObj.put("jmdb_startofterm",TimeUtil.formatDate(resident.getjmdb_startofterm(),"yyyy-MM-dd"));
			else
				jsonObj.put("jmdb_startofterm","-");

			if(resident.getjmdb_endofterm() != null)
				jsonObj.put("jmdb_endofterm",TimeUtil.formatDate(resident.getjmdb_endofterm(),"yyyy-MM-dd"));
			else
				jsonObj.put("jmdb_endofterm","-");
			
			jsonObj.put("wtgg_skill_type",resident.getwtgg_skill_type());
			jsonObj.put("wtgg_special_skill",resident.getwtgg_special_skill());

			jsonObj.put("success", true);
		}
		else
		{
			logger.error("object is not found...");
			jsonObj.put("success", false);
			jsonObj.put("errMsg", "Object can not found...");
		}
	}
	catch(Exception e)
	{
		logger.error(e.getMessage(),e);
		jsonObj.put("success", false);
	}
    return jsonObj.toString();
}
}
