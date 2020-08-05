package com.template.busi.controller.jcsqsj;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigInteger;
import java.text.NumberFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.template.util.ConstValue;
import com.template.util.HqlFilter;
import com.template.util.TimeUtil;
import com.template.util.Utility;
import com.template.model.SXSQSJ;
import com.template.model.SXSQSJ_FL;
import com.template.model.SysUser;
import com.template.service.SXSQSJFLService;
import com.template.service.SXSQSJService;
import com.template.service.SysUserService;
//import com.template.service.jcsqsj.CommercialbuildingService;
import com.template.service.jcsqsj.*;

@Controller
@RequestMapping("jcsqsjController")
public class JCSQSJController {
	
	private static Logger logger = Logger.getLogger(JCSQSJController.class);
	
	@Autowired
	private  HttpServletRequest request;
	
	@Autowired
	private Jc_bizbuildingService jc_bizbuildingService;
	
	@Autowired
	private CommunityService communityService;
	
	@Autowired
	private ResidebuildingService residebuildingService;
	
	@Autowired
	private RoomService roomService;
	
	@Autowired
	private FamilyService familyService;
	
	@Autowired
	private ResidentService residentService;
	
	@Autowired
	private VehicleService vehicleService;
	
	@Autowired
	private Jc_organizationService organizationService;
	
	@Autowired
	private UndergroundspaceService undergroundspaceService;
	
	@Autowired
	private PartyorganizationService partyorganizationService;
	
	@Autowired
	private VolunteerteamService volunteerteamService;
	
	@Autowired
	private RoadsService roadsService;
	
	@Autowired
	private PopulationgroupService populationgroupService;
	
	@Autowired
	private CulturefacilitiesService culturefacilitiesService;
	
	@Autowired
	private WatersystemService watersystemService;
	
	@Autowired
	private ShelterService shelterService;
	
	@Autowired
	private Service_storeService service_storeService;
	
	@Autowired
	private Jc_rubbishService jc_rubbishService;
	
	//@Autowired
	//private PublicfacilitiesService publicfacilitiesService;
	
	@Autowired
	private Jc_partymemberService jc_partymemberService; 
	
	@Autowired
	private Jc_advertisementService jc_advertisementService;
	
	@Autowired
	private Jc_pubfacilities_gyService jc_pubfacilities_gyService;
	
	@Autowired
	private Jc_pubfacilities_jtService jc_pubfacilities_jtService;
	
	@Autowired
	private Jc_pubfacilities_hjService jc_pubfacilities_hjService;
	
	@Autowired
	private Jc_pubfacilities_lhService jc_pubfacilities_lhService;
	
	@Autowired
	private Jc_pubfacilities_qtService jc_pubfacilities_qtService;
	
	@Autowired
	private Jc_xftdService jc_xftdService;
	
	@Autowired
	private Jc_xfssService jc_xfssService;
	
	@Autowired
	private Jc_tc_tcwService jc_tc_tcwService;
	
	@Autowired
	private Jc_tc_tcccrkService jc_tc_tccrkService;
	
	@Autowired
	private Jc_tc_ybtccService jc_tc_ybtccService;
	
	@Autowired
	private Jc_tc_fjdctcwService jc_tc_fjdctcwService;
	
	@Autowired
	private Jc_tc_dltccService jc_tc_dltccService;
	
	//@Autowired
	//private ParkingService parkingService;
	
	@RequestMapping(value="get",method = {RequestMethod.POST,RequestMethod.GET},produces="text/html;charset=UTF-8")
    @ResponseBody
	public String get(String id)
	{
		logger.debug("get");
    	JSONObject jsonObj = new JSONObject();
    	
		try
		{
			HqlFilter hqlFilter = new HqlFilter();
			
			long commercialbuilding = jc_bizbuildingService.countByFilter(hqlFilter);//商务楼宇
			
			long community = communityService.countByFilter(hqlFilter);//小区信息
			
			long residebuilding = residebuildingService.countByFilter(hqlFilter);//住宅楼宇
			
			long room = roomService.countByFilter(hqlFilter); //房屋信息
			
			long family = familyService.countByFilter(hqlFilter); //家庭信息
			
			long resident = residentService.countByFilter(hqlFilter); //居民信息
			
			long vehicle = vehicleService.countByFilter(hqlFilter); //车辆信息
			
			long organization = organizationService.countByFilter(hqlFilter); //法人组织
			
			long undergroundspace = undergroundspaceService.countByFilter(hqlFilter); //地下空间
			
			long partyorganization = partyorganizationService.countByFilter(hqlFilter);//党组织信息
			
			long populationgroup = populationgroupService.countByFilter(hqlFilter);//居民团体
			
			long volunteerteam = volunteerteamService.countByFilter(hqlFilter);//志愿者队伍
			
			long roads = roadsService.countByFilter(hqlFilter); //周边道路
			
			long shelter = shelterService.countByFilter(hqlFilter);//避难场所
			
			long watersystem = watersystemService.countByFilter(hqlFilter);//河湖水系
			
			//long publicfacilities = publicfacilitiesService.countByFilter(hqlFilter);//市政设施
			
			long culturefacilities = culturefacilitiesService.countByFilter(hqlFilter); //文体设施
			
			//long parking = parkingService.countByFilter(hqlFilter); //停车资源
			
			long service_store = service_storeService.countByFilter(hqlFilter); //服务网点
			
			long ljz = jc_rubbishService.countByFilter(hqlFilter); //垃圾站
			
			long dyxx = jc_partymemberService.countByFilter(hqlFilter); //
			
			long xcss = jc_advertisementService.countByFilter(hqlFilter); //	
			
			long gyszss = jc_pubfacilities_gyService.countByFilter(hqlFilter); //	
			
			long jtszss = jc_pubfacilities_jtService.countByFilter(hqlFilter); //	
			
			long srhjszss = jc_pubfacilities_hjService.countByFilter(hqlFilter); //
			
			long yllhszss = jc_pubfacilities_lhService.countByFilter(hqlFilter); //
			
			long qtszss = jc_pubfacilities_qtService.countByFilter(hqlFilter); //	
			
			long xftd = jc_xftdService.countByFilter(hqlFilter); //	
			
			long xfss = jc_xfssService.countByFilter(hqlFilter); //	
			
			long tcwqy = jc_tc_tcwService.countByFilter(hqlFilter); //	
			
			long ybtcccrk = jc_tc_tccrkService.countByFilter(hqlFilter); //	
			
			long ybtcc = jc_tc_ybtccService.countByFilter(hqlFilter); //	
			
			long fjdctcw = jc_tc_fjdctcwService.countByFilter(hqlFilter); //	
			
			long dltcc = jc_tc_dltccService.countByFilter(hqlFilter); //	
			
			jsonObj.put("success", true);
			jsonObj.put("community", community);
			jsonObj.put("residebuilding", residebuilding);
			jsonObj.put("room", room);
			jsonObj.put("family", family);
			jsonObj.put("resident", resident);
			jsonObj.put("vehicle", vehicle);
			jsonObj.put("organization", organization);
			jsonObj.put("undergroundspace", undergroundspace);
			jsonObj.put("partyorganization", partyorganization);
			jsonObj.put("populationgroup", populationgroup);
			jsonObj.put("volunteerteam", volunteerteam);
			jsonObj.put("roads", roads);
			jsonObj.put("shelter", shelter);
			jsonObj.put("watersystem", watersystem);
			jsonObj.put("culturefacilities", culturefacilities);
			jsonObj.put("service_store", service_store);
			//jsonObj.put("publicfacilities", publicfacilities);
			jsonObj.put("commercialbuilding", commercialbuilding);
			jsonObj.put("ljz", ljz);
			//jsonObj.put("parking", parking);
			
			jsonObj.put("dyxx", dyxx);			
			jsonObj.put("xcss", xcss);
			jsonObj.put("gyszss", gyszss);
			jsonObj.put("jtszss", jtszss);
			jsonObj.put("srhjszss", srhjszss);
			jsonObj.put("yllhszss", yllhszss);
			jsonObj.put("qtszss", qtszss);
			jsonObj.put("xftd", xftd);
			jsonObj.put("xfss", xfss);
			jsonObj.put("tcwqy", tcwqy);
			jsonObj.put("ybtcccrk", ybtcccrk);
			jsonObj.put("ybtcc", ybtcc);
			jsonObj.put("fjdctcw", fjdctcw);
			jsonObj.put("dltcc", dltcc);
			
			/*dyxx //党员信息 	 	Jc_partymemberService 
			xcss	//宣传设施					Jc_advertisementService
			gyszss	//公用市政设施				Jc_pubfacilities_gyService
			jtszss	//交通市政设施				Jc_pubfacilities_jtService
			srhjszss	//市容环境市政设施		Jc_pubfacilities_hjService
			yllhszss	//园林绿化市政设施		Jc_pubfacilities_lhService
			qtszss		//其他市政设施			Jc_pubfacilities_qtService
			xftd		//消防通道				Jc_xftdService
			xfss		//消防设施				Jc_xfssService
			tcwqy		//停车位区域			Jc_tc_tcwService
			ybtcccr		//一般停车场出入口		Jc_tc_ybtccrkService
			ybtcc		//一般停车场			Jc_tc_ybtccService
			fjdctcw		//非机动车停车位			Jc_tc_fjdctcwService
			dltcc		//道路停车场			Jc_tc_dltccService
			*/
		}
		catch(Exception e)
		{
			logger.error(e.getMessage(),e);
			jsonObj.put("success", false);
		}
        return jsonObj.toString();
    }
	
	
}
