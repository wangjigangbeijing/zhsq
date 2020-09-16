package com.template.controller;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.mysql.cj.util.StringUtils;
import com.template.busi.controller.flow.FlowTemplateController;
import com.template.model.SysOrganization;
import com.template.service.SysOrganizationService;
import com.template.service.SysUserService;
import com.template.service.jcsqsj.FamilyService;
import com.template.service.jcsqsj.Jc_advertisementService;
import com.template.service.jcsqsj.Jc_partymemberService;
import com.template.service.jcsqsj.Jc_pubfacilities_gyService;
import com.template.service.jcsqsj.Jc_pubfacilities_hjService;
import com.template.service.jcsqsj.Jc_pubfacilities_jtService;
import com.template.service.jcsqsj.Jc_pubfacilities_lhService;
import com.template.service.jcsqsj.Jc_pubfacilities_qtService;
import com.template.service.jcsqsj.Jc_tc_tcwService;
import com.template.service.jcsqsj.Jc_volunteerService;
import com.template.service.jcsqsj.ResidentService;
import com.template.service.jcsqsj.Service_storeService;
import com.template.util.ConstValue;
import com.template.util.HqlFilter;
import com.template.util.Utility;

@Controller
@RequestMapping("homeChartController")
public class HomeChartController {

private static Logger logger = Logger.getLogger(FlowTemplateController.class);
	
	@Autowired
	private  HttpServletRequest request;
	
	@Autowired
	private SysOrganizationService organizationService;
	
	@Autowired
	private ResidentService residentService;
	
	@Autowired
	private Jc_pubfacilities_gyService gyService;
	
	@Autowired
	private Jc_pubfacilities_hjService hjService;
	
	@Autowired
	private Jc_pubfacilities_jtService jtService;
	
	@Autowired
	private Jc_pubfacilities_lhService lhService;
	
	@Autowired
	private Jc_pubfacilities_qtService qtService;

	@Autowired
	private Jc_advertisementService advertisementService;
	
	@Autowired
	private Service_storeService service_storeService;
	
	@Autowired
	private FamilyService familyService;
	
	@Autowired
	private Jc_tc_tcwService tcwService;

	@Autowired
	private SysUserService userService;

	@Autowired
	private Jc_partymemberService partyMemberService;

	@Autowired
	private Jc_volunteerService volunteerService;
	
	private static DecimalFormat percdf = new DecimalFormat("##.00%");    //##.00%   百分比格式，后面不足2位的用0补齐
	private static DecimalFormat df = new DecimalFormat("##.00");    //##.00%   百分比格式，后面不足2位的用0补齐

	@RequestMapping(value="getOrganizationInfo",method = {RequestMethod.POST,RequestMethod.GET},produces="text/html;charset=UTF-8")
	@ResponseBody
	public String getOrganizationInfo() 
	{
		String owner = Utility.getInstance().getOrganization(request);//社区用户返回的是社区ID
		
		if(owner == null || owner.equalsIgnoreCase(""))
			return "大红门街道办事处";
		
    	JSONObject jsonObj = new JSONObject();
    	
		try
		{
			SysOrganization sysOrganization = organizationService.getById(owner);
			
			String note = sysOrganization.getnote();//社区介绍
			jsonObj.put("note", note);
			String noteShort = note;
			if(note != null && note.length() > 300)
				noteShort = note.substring(0, 300);
			jsonObj.put("noteShort", noteShort+"...");
			
			String year = sysOrganization.getyear();//社区建成时间
			jsonObj.put("year", year);
			String type = sysOrganization.gettype();//社区类型
			jsonObj.put("type", type);
			Double area = sysOrganization.getarea();//社区面积
			jsonObj.put("area", area);
			String pictures = sysOrganization.getpictures();//社区美景
			jsonObj.put("pictures", pictures);
			
			String sRoomArea = "SELECT SUM(area) as area FROM jc_residebuilding where owner = '"+owner+"'";
			List<HashMap> listRoomObj = organizationService.findBySql(sRoomArea);
			
			double roomArea = 0.0;
			if(listRoomObj.size() != 0 && listRoomObj.get(0).get("area") != null)
				roomArea = (Double)listRoomObj.get(0).get("area");
			jsonObj.put("roomArea", roomArea);
			
			String sUnderGroundArea = "SELECT SUM(area) as area FROM jc_undergroundspace where owner = '"+owner+"'";
			List<HashMap> listUnderGroundObj = organizationService.findBySql(sUnderGroundArea);
			
			double underGroundArea = 0.0;
			if(listUnderGroundObj.size() != 0 && listUnderGroundObj.get(0).get("area") != null)
				underGroundArea = (Double)listUnderGroundObj.get(0).get("area");
			jsonObj.put("underGroundArea", underGroundArea);
			HqlFilter cntResident = new HqlFilter();
			
			cntResident.addQryCond("owner", HqlFilter.Operator.EQ, owner);
			long residentCnt = residentService.countByFilter(cntResident);
			jsonObj.put("residentCnt", residentCnt);
			
			double residentDensity = residentCnt*1.0/area;//人口密度
			jsonObj.put("residentDensity", df.format(residentDensity));
			
			double averageRoomArea = roomArea/residentCnt;//人均住房面积
			jsonObj.put("averageRoomArea", df.format(averageRoomArea));
			
			double averageUnderGroundArea = underGroundArea/residentCnt;//人均地下空间面积
			jsonObj.put("averageUnderGroundArea", df.format(averageUnderGroundArea));
			
			long gyCnt = gyService.countByFilter(cntResident);
			long hjCnt = hjService.countByFilter(cntResident);
			long jtCnt = jtService.countByFilter(cntResident);
			long lhCnt = lhService.countByFilter(cntResident);
			long qtCnt = qtService.countByFilter(cntResident);
			long publcFacility = gyCnt + hjCnt + jtCnt + lhCnt + qtCnt;
			
			double publcFacilityDensity = publcFacility*1.0/area;//市政设施密度
			jsonObj.put("publcFacilityDensity", df.format(publcFacilityDensity));
			
			String sRubbishNum = "SELECT SUM(num) as num FROM jc_rubbish where owner = '"+owner+"'";
			List<HashMap> listRubbishNum = organizationService.findBySql(sRubbishNum);
			
			Integer rubbishNum = 0;
			if(listRubbishNum.size() != 0 && listRubbishNum.get(0).get("num") != null)
				rubbishNum = ((BigDecimal)listRubbishNum.get(0).get("num")).intValue();
			jsonObj.put("roomArea", roomArea);
			double averageRubbishNum = rubbishNum*1.0/(residentCnt/100);//每百人垃圾设施数量
			jsonObj.put("averageRubbishNum", df.format(averageRubbishNum));
			
			long advertisementCnt = advertisementService.countByFilter(cntResident);			
			jsonObj.put("advertisementCnt", advertisementCnt);
			
			double averageAdvertisementNum = advertisementCnt*1.0/(residentCnt/100);//每百人宣传设施数量（宣传设施总数/人口数量）
			jsonObj.put("averageAdvertisementNum", df.format(averageAdvertisementNum));
			
			String sCultureFacilities = "SELECT SUM(num) as num,SUM(area) as area FROM jc_culturefacilities where owner = '"+owner+"' and tpye = '健身设施'";
			List<HashMap> listCultureFacilities = organizationService.findBySql(sCultureFacilities);
			
			Integer cultureFacilitiesNum = 0;
			if(listCultureFacilities.size() != 0 && listCultureFacilities.get(0).get("num") != null)
				cultureFacilitiesNum = ((BigDecimal)listCultureFacilities.get(0).get("num")).intValue();
			jsonObj.put("cultureFacilitiesNum",cultureFacilitiesNum);
			
			Double cultureFacilitiesArea = 0.0;
			if(listCultureFacilities.size() != 0 && listCultureFacilities.get(0).get("area") != null)
				cultureFacilitiesArea = (Double)listCultureFacilities.get(0).get("area");
			jsonObj.put("cultureFacilitiesArea",cultureFacilitiesArea);
			
			double averageCultureFacNum = cultureFacilitiesNum*1.0/residentCnt;//人均健身设施数量
			jsonObj.put("averageCultureFacNum", df.format(averageCultureFacNum));
			
			double averageCultureFacArea = cultureFacilitiesArea*1.0/residentCnt;//人均健身场地面积
			jsonObj.put("averageCultureFacArea", df.format(averageCultureFacArea));
			
			long serviceStoreCnt = service_storeService.countByFilter(cntResident);			
			double averageServiceStoreNum = serviceStoreCnt*1.0/residentCnt;//人均服务网点数量（服务网点总数/人口数量）
			jsonObj.put("averageServiceStoreNum", df.format(averageServiceStoreNum));
			
			long famliyCnt = familyService.countByFilter(cntResident);
			jsonObj.put("famliyCnt", famliyCnt);
			
			long tcwCnt = tcwService.countByFilter(cntResident);
			jsonObj.put("tcwCnt", tcwCnt);
			
			HqlFilter tcwResident = new HqlFilter();
			tcwResident.addQryCond("owner", HqlFilter.Operator.EQ, owner);
			tcwResident.addQryCond("cwtype", HqlFilter.Operator.EQ, "居住区停车位");
			
			long jzqtcwCnt = tcwService.countByFilter(tcwResident);
			jsonObj.put("jzqtcwCnt", jzqtcwCnt);
			
			double averageResidentJZQTcw = jzqtcwCnt*1.0/(residentCnt/100);//人均居住区停车位数（居住区停车位总数/人口数量）
			jsonObj.put("averageResidentJZQTcw", df.format(averageResidentJZQTcw));
			
			double averageResidentTcw = tcwCnt*1.0/(residentCnt/100);//人均总车位数（停车位总数/人口数量）
			jsonObj.put("averageResidentTcw", df.format(averageResidentTcw));
			
			double averageFamilyJZQTcw = jzqtcwCnt*1.0/(famliyCnt/100);//户均居住区停车位数（居住区停车位总数/人口数量）
			jsonObj.put("averageFamilyJZQTcw", df.format(averageFamilyJZQTcw));
			
			double averageFamilyTcw = tcwCnt*1.0/(famliyCnt/100);//户均总车位数（停车位总数/人口数量）
			jsonObj.put("averageFamilyTcw", df.format(averageFamilyTcw));
			
			String sDZY = "SELECT SUM(num) as num FROM jc_pubfacilities_gy__v_jldzy where owner = '"+owner+"'";
			List<HashMap> listDZY = organizationService.findBySql(sDZY);
			
			Integer dzyNum = 0;
			if(listDZY.size() != 0 && listDZY.get(0).get("num") != null)
				dzyNum = ((BigDecimal)listDZY.get(0).get("num")).intValue();
			
			double averageResidentDZY = dzyNum*1.0/residentCnt;//人均监控探头数量
			jsonObj.put("averageResidentDZY", df.format(averageResidentDZY));
			
			long sqCnt = userService.countByFilter(cntResident);//社区工作者
			Double sqPercent = sqCnt*1.0/(residentCnt/100);///每百人社区工作者占比情况
			jsonObj.put("sqCnt", sqCnt);
			jsonObj.put("sqPercent", df.format(sqPercent*100));
			
			long partyMemberCnt = partyMemberService.countByFilter(cntResident);//党员
			Double partyMemberPercent = partyMemberCnt*1.0/(residentCnt);///党员占比情况
			jsonObj.put("partyMemberCnt", partyMemberCnt);
			jsonObj.put("partyMemberPercent", df.format(partyMemberPercent*100));
			
			long volunteerCnt = volunteerService.countByFilter(cntResident);//志愿者
			Double volunteerPercent = volunteerCnt*1.0/(residentCnt);///志愿者占比情况
			jsonObj.put("volunteerCnt", volunteerCnt);
			jsonObj.put("volunteerPercent", df.format(volunteerPercent*100));
			
			String sTree = "SELECT COUNT(*) AS cnt FROM jc_pubfacilities_lh where owner = '"+owner+"' and (type = '行树' or type = '独立树')";
			List<HashMap> listTree = organizationService.findBySql(sTree);
			
			Integer treeNum = 0;
			if(listTree.size() != 0 && listTree.get(0).get("cnt") != null)
				treeNum = ((BigInteger)listTree.get(0).get("cnt")).intValue();;

			double averageTreeNum = treeNum*1.0/(residentCnt/100);//每百人绿植数量
			jsonObj.put("averageTreeNum", df.format(averageTreeNum));
			
			String sCamera = "SELECT SUM(NUM) AS cnt FROM jc_pubfacilities_gy where owner = '"+owner+"' and type = '监控电子眼'";
			List<HashMap> listCamera = organizationService.findBySql(sCamera);
			
			Integer cameraNum = 0;
			if(listCamera.size() != 0 && listCamera.get(0).get("cnt") != null)
				cameraNum = ((BigDecimal)listCamera.get(0).get("cnt")).intValue();;
			jsonObj.put("cameraNum", cameraNum);		
			
			
			String sSQOrgMember = "SELECT COUNT(*) AS cnt,politicalstatus FROM jc_sqorgmember where owner = '"+owner+"' group by politicalstatus";
			List<HashMap> listSQOrgMember = organizationService.findBySql(sSQOrgMember);
			
			Integer sqOrgMemberNum = 1;
			Integer sqOrgMemberPartyMemberNum = 0;
			for(int i=0;i<listSQOrgMember.size();i++)
			{
				if(listSQOrgMember.get(0).get("politicalstatus") != null && ((String)listSQOrgMember.get(0).get("politicalstatus")).equalsIgnoreCase("中共党员"))
				{
					sqOrgMemberPartyMemberNum = ((BigInteger)listSQOrgMember.get(i).get("cnt")).intValue();
				}
				sqOrgMemberNum += ((BigInteger)listSQOrgMember.get(i).get("cnt")).intValue();
			}
			
			double sqOfPartyMember = sqOrgMemberPartyMemberNum*1.0/sqOrgMemberNum;//社区工作者党员占比情况
			jsonObj.put("sqOrgMemberNum",sqOrgMemberNum);		
			jsonObj.put("sqOrgMemberPartyMemberNum",sqOrgMemberPartyMemberNum);	
			jsonObj.put("sqOfPartyMember", df.format(sqOfPartyMember*100));
			
			
			String sVolunteerPartyMember = "SELECT COUNT(*) AS cnt,politicalstatus FROM jc_volunteer where owner = '"+owner+"' group by politicalstatus";
			List<HashMap> listVolunteerPartyMember = organizationService.findBySql(sVolunteerPartyMember);
			
			Integer volunteerNum = 1;
			Integer volunteerPartyMemberNum = 0;
			for(int i=0;i<listVolunteerPartyMember.size();i++)
			{
				if(listVolunteerPartyMember.get(0).get("politicalstatus") != null && ((String)listVolunteerPartyMember.get(0).get("politicalstatus")).equalsIgnoreCase("中共党员"))
				{
					volunteerPartyMemberNum = ((BigInteger)listVolunteerPartyMember.get(i).get("cnt")).intValue();
				}
				volunteerNum += ((BigInteger)listVolunteerPartyMember.get(i).get("cnt")).intValue();
			}
			
			double volunteerOfPartyMember = volunteerPartyMemberNum*1.0/volunteerNum;//社区工作者党员占比情况
			jsonObj.put("volunteerNum",volunteerNum);		
			jsonObj.put("volunteerPartyMemberNum",volunteerPartyMemberNum);		
			jsonObj.put("volunteerOfPartyMember", df.format(volunteerOfPartyMember*100));
			
			

			//服务网点			
			String sServiceStoreSql = "SELECT TYPE,COUNT(*) CNT FROM jc_service_store where owner = '"+owner+"' GROUP BY TYPE";
			List<HashMap> listServiceStore = organizationService.findBySql(sServiceStoreSql);
			
			JSONArray jsonServiceStore = new JSONArray();
			for(int i=0;i<listServiceStore.size();i++)
			{
				JSONObject jsonTmp = new JSONObject();
				
				HashMap hm = listServiceStore.get(i);
				String serviceStore = "";
				
				if(hm.get("TYPE") == null || hm.get("TYPE").equals(""))
					serviceStore = "不详";
				else
					serviceStore = (String)hm.get("TYPE");

				BigInteger serviceStoreNum = (BigInteger)hm.get("CNT");
				
				jsonTmp.put("serviceStore", serviceStore);
				jsonTmp.put("serviceStoreNum", serviceStoreNum);
				jsonTmp.put("serviceStoreNum100", df.format(serviceStoreNum.intValue()*1.0/(residentCnt/100)));
				
				jsonServiceStore.add(jsonTmp);	
			}
			jsonObj.put("serviceStoreArr", jsonServiceStore);
			
			
			
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
	
	
	
	@RequestMapping(value="getMinQing",method = {RequestMethod.POST,RequestMethod.GET},produces="text/html;charset=UTF-8")
	@ResponseBody
	public String getMinQing() 
	{
		String owner = Utility.getInstance().getOrganization(request);//社区用户返回的是社区ID
		
		if(owner == null || owner.equalsIgnoreCase(""))
			return "大红门街道办事处";
		
    	JSONObject jsonObj = new JSONObject();
    	
		try
		{
			//性别分布图
			String sGenderSql = "SELECT SEX,COUNT(*) CNT FROM JC_RESIDENT GROUP BY SEX";
			List<HashMap> listGender = organizationService.findBySql(sGenderSql);
			
			JSONArray jsonGender = new JSONArray();
			for(int i=0;i<listGender.size();i++)
			{
				JSONObject jsonTmp = new JSONObject();
				
				HashMap hm = listGender.get(i);
				String gender = "";
				
				if(hm.get("SEX") == null || hm.get("SEX").equals(""))
					gender = "不详";
				else
					gender = (String)hm.get("SEX");
				
				BigInteger genderNum = (BigInteger)hm.get("CNT");
				
				jsonTmp.put("gender", gender);
				jsonTmp.put("genderNum", genderNum);
				
				jsonGender.add(jsonTmp);
			}
			jsonObj.put("genderArr", jsonGender);
			
			//年龄分布图
			String sAgeSql = "SELECT AGECHARACTERISTIC,COUNT(*) CNT FROM JC_RESIDENT GROUP BY AGECHARACTERISTIC";
			List<HashMap> listAge = organizationService.findBySql(sAgeSql);
			
			JSONArray jsonAge = new JSONArray();
			for(int i=0;i<listAge.size();i++)
			{
				JSONObject jsonTmp = new JSONObject();
				
				HashMap hm = listAge.get(i);
				String gender = "";
				
				if(hm.get("AGECHARACTERISTIC") == null || hm.get("AGECHARACTERISTIC").equals(""))
					gender = "不详";
				else
					gender = (String)hm.get("AGECHARACTERISTIC");
				
				BigInteger genderNum = (BigInteger)hm.get("CNT");
				
				jsonTmp.put("age", gender);
				jsonTmp.put("ageNum", genderNum);
				
				jsonAge.add(jsonTmp);
			}
			jsonObj.put("ageArr", jsonAge);
			
			
			//户籍类别分布			
			String sResidentStatusSql = "SELECT RESIDENCESTATUS,COUNT(*) CNT FROM JC_RESIDENT GROUP BY RESIDENCESTATUS";
			List<HashMap> listResidentStatus = organizationService.findBySql(sResidentStatusSql);
			
			JSONArray jsonResidentStatus = new JSONArray();
			for(int i=0;i<listResidentStatus.size();i++)
			{
				JSONObject jsonTmp = new JSONObject();
				
				HashMap hm = listResidentStatus.get(i);
				String residencestatus = "";
				
				if(hm.get("RESIDENCESTATUS") == null || hm.get("RESIDENCESTATUS").equals(""))
					residencestatus = "不详";
				else
					residencestatus = (String)hm.get("RESIDENCESTATUS");
				
				BigInteger residenceStatusNum = (BigInteger)hm.get("CNT");
				
				jsonTmp.put("residenceStatus", residencestatus);
				jsonTmp.put("residenceStatusNum", residenceStatusNum);
				
				jsonResidentStatus.add(jsonTmp);
			}
			jsonObj.put("residenceStatusArr", jsonResidentStatus);
			
			//民族			
			String sNationSql = "SELECT NATION,COUNT(*) CNT FROM JC_RESIDENT GROUP BY NATION ORDER BY CNT DESC";
			List<HashMap> listNation = organizationService.findBySql(sNationSql);

			JSONArray jsonNation = new JSONArray();
			for(int i=0;i<listNation.size() && i <= 5;i++)
			{
				JSONObject jsonTmp = new JSONObject();
				
				HashMap hm = listNation.get(i);
				String nation = "";
				
				if(hm.get("NATION") == null || hm.get("NATION").equals(""))
					nation = "不详";
				else
					nation = (String)hm.get("NATION");
				
				BigInteger nationNum = (BigInteger)hm.get("CNT");
				
				jsonTmp.put("nation", nation);
				jsonTmp.put("nationNum", nationNum);
				
				jsonNation.add(jsonTmp);
			}
			jsonObj.put("nationArr", jsonNation);
			
			//政治面貌			
			String sPoliticalSql = "SELECT POLITICALSTATUS,COUNT(*) CNT FROM JC_RESIDENT GROUP BY POLITICALSTATUS";
			List<HashMap> listPolitical = organizationService.findBySql(sPoliticalSql);

			JSONArray jsonPoliticalStatus = new JSONArray();
			for(int i=0;i<listPolitical.size();i++)
			{
				JSONObject jsonTmp = new JSONObject();
				
				HashMap hm = listPolitical.get(i);
				String political = "";
				
				if(hm.get("POLITICALSTATUS") == null || hm.get("POLITICALSTATUS").equals(""))
					political = "不详";
				else
					political = (String)hm.get("POLITICALSTATUS");

				BigInteger politicalNum = (BigInteger)hm.get("CNT");
				
				jsonTmp.put("politicalstatus", political);
				jsonTmp.put("politicalStatusNum", politicalNum);
				
				jsonPoliticalStatus.add(jsonTmp);	
			}
			jsonObj.put("politicalStatusArr", jsonPoliticalStatus);
			
			//职业状态			
			String sProfessionSql = "SELECT PROFESSIONSTATUS,COUNT(*) CNT FROM JC_RESIDENT GROUP BY PROFESSIONSTATUS";
			List<HashMap> listProfession = organizationService.findBySql(sProfessionSql);
			
			JSONArray jsonProfession = new JSONArray();
			for(int i=0;i<listProfession.size();i++)
			{
				JSONObject jsonTmp = new JSONObject();
				
				HashMap hm = listProfession.get(i);
				String profession = "";
				
				if(hm.get("PROFESSIONSTATUS") == null || hm.get("PROFESSIONSTATUS").equals(""))
					profession = "不详";
				else
					profession = (String)hm.get("PROFESSIONSTATUS");

				BigInteger professionNum = (BigInteger)hm.get("CNT");
				
				jsonTmp.put("profession", profession);
				jsonTmp.put("professionNum", professionNum);
				
				jsonProfession.add(jsonTmp);	
			}
			jsonObj.put("professionArr", jsonProfession);
			
			
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
	
	
	@RequestMapping(value="getOther",method = {RequestMethod.POST,RequestMethod.GET},produces="text/html;charset=UTF-8")
	@ResponseBody
	public String getOther() 
	{
		String owner = Utility.getInstance().getOrganization(request);//社区用户返回的是社区ID
		
		if(owner == null || owner.equalsIgnoreCase(""))
			return "大红门街道办事处";
		
    	JSONObject jsonObj = new JSONObject();
    	
		try
		{
			//教育指数			
			String sEducationSql = "SELECT EDUCATION,COUNT(*) CNT FROM JC_RESIDENT GROUP BY EDUCATION";
			List<HashMap> listEducation = organizationService.findBySql(sEducationSql);

			JSONArray jsonEducation = new JSONArray();
			for(int i=0;i<listEducation.size();i++)
			{
				JSONObject jsonTmp = new JSONObject();
				
				HashMap hm = listEducation.get(i);
				String political = "";
				
				if(hm.get("EDUCATION") == null || hm.get("EDUCATION").equals(""))
					political = "不详";
				else
					political = (String)hm.get("EDUCATION");

				BigInteger politicalNum = (BigInteger)hm.get("CNT");
				
				jsonTmp.put("education", political);
				jsonTmp.put("educationNum", politicalNum);
				
				jsonEducation.add(jsonTmp);	
			}
			jsonObj.put("educationArr", jsonEducation);
			
			
			
			
			//婚姻指数			
			String sMarriageSql = "SELECT MARRIAGE,COUNT(*) CNT FROM JC_RESIDENT GROUP BY MARRIAGE";
			List<HashMap> listMarriage = organizationService.findBySql(sMarriageSql);

			JSONArray jsonMarriage = new JSONArray();
			for(int i=0;i<listMarriage.size();i++)
			{
				JSONObject jsonTmp = new JSONObject();
				
				HashMap hm = listMarriage.get(i);
				String political = "";
				
				if(hm.get("MARRIAGE") == null || hm.get("MARRIAGE").equals(""))
					political = "不详";
				else
					political = (String)hm.get("MARRIAGE");

				BigInteger politicalNum = (BigInteger)hm.get("CNT");
				
				jsonTmp.put("marriage", political);
				jsonTmp.put("marriageNum", politicalNum);
				
				jsonMarriage.add(jsonTmp);	
			}
			jsonObj.put("marriageArr", jsonMarriage);
			
			
			
			
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
}
