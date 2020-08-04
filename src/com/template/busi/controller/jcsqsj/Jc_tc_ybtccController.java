package com.template.busi.controller.jcsqsj;
import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import com.template.model.jcsqsj.Jc_tc_ybtcc;
import com.template.service.jcsqsj.Jc_tc_ybtccService;
import com.template.util.HqlFilter;
import com.template.util.ConstValue;
import com.template.util.Utility;
import com.template.util.TimeUtil;
import java.util.List;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
@Controller
@RequestMapping("jc_tc_ybtccController")
public class Jc_tc_ybtccController {
	private static Logger logger = Logger.getLogger(Jc_tc_ybtccController.class);
	@Autowired
	private  HttpServletRequest request;
	@Autowired
	private Jc_tc_ybtccService jc_tc_ybtccService;
@RequestMapping(value="addOrUpdate",method = RequestMethod.POST,produces="text/html;charset=UTF-8")
@ResponseBody
public String addOrUpdate(String id,String parkID,String parkName,String tradeName,String jztype,String unitName,String unitAddres,String area,String adminDep,String ownerDep,String maintDep,Integer berthNum,Integer UnberthNum,Integer GrberthNum,Integer ParkingNum,Integer openNum,String parkTime,Integer chpileNum,Integer BaFreeNum,Integer MecNum,String Chargetype,String LoLeChtype,String ShLeChtype,String DyData,String picture,String note)
{
	JSONObject jsonObj = new JSONObject();
	try
	{
		Jc_tc_ybtcc jc_tc_ybtcc;
		if(id==null || id.equalsIgnoreCase(""))
		{
			jc_tc_ybtcc = new Jc_tc_ybtcc();
			jc_tc_ybtcc.setId(Utility.getUniStr());
		}
		else
		{
			jc_tc_ybtcc = jc_tc_ybtccService.getById(id);
		}
		jc_tc_ybtcc.setparkID(parkID);
		jc_tc_ybtcc.setparkName(parkName);
		jc_tc_ybtcc.settradeName(tradeName);
		jc_tc_ybtcc.setjztype(jztype);
		jc_tc_ybtcc.setunitName(unitName);
		jc_tc_ybtcc.setunitAddres(unitAddres);
		jc_tc_ybtcc.setarea(area);
		jc_tc_ybtcc.setadminDep(adminDep);
		jc_tc_ybtcc.setownerDep(ownerDep);
		jc_tc_ybtcc.setmaintDep(maintDep);
		jc_tc_ybtcc.setberthNum(berthNum);
		jc_tc_ybtcc.setUnberthNum(UnberthNum);
		jc_tc_ybtcc.setGrberthNum(GrberthNum);
		jc_tc_ybtcc.setParkingNum(ParkingNum);
		jc_tc_ybtcc.setopenNum(openNum);
		jc_tc_ybtcc.setparkTime(parkTime);
		jc_tc_ybtcc.setchpileNum(chpileNum);
		jc_tc_ybtcc.setBaFreeNum(BaFreeNum);
		jc_tc_ybtcc.setMecNum(MecNum);
		jc_tc_ybtcc.setChargetype(Chargetype);
		jc_tc_ybtcc.setLoLeChtype(LoLeChtype);
		jc_tc_ybtcc.setShLeChtype(ShLeChtype);
		jc_tc_ybtcc.setDyData(DyData);
		jc_tc_ybtcc.setpicture(picture);
		jc_tc_ybtcc.setnote(note);

        jc_tc_ybtccService.save(jc_tc_ybtcc);
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
		Jc_tc_ybtcc jc_tc_ybtcc = jc_tc_ybtccService.getById(id);
		jc_tc_ybtccService.delete(jc_tc_ybtcc);
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
public String load(String tradeName,String jztype,String unitAddres,String adminDep,String ownerDep,String maintDep,String Chargetype,String DyData)
{
	JSONObject jsonObj = new JSONObject();
	try
	{
		HqlFilter hqlFilter = new HqlFilter();
if(tradeName != null && tradeName.equalsIgnoreCase("") == false && tradeName.equalsIgnoreCase("null") == false)
{
	hqlFilter.addQryCond("tradeName", HqlFilter.Operator.EQ, tradeName);
}
if(jztype != null && jztype.equalsIgnoreCase("") == false && jztype.equalsIgnoreCase("null") == false)
{
	hqlFilter.addQryCond("jztype", HqlFilter.Operator.EQ, jztype);
}
if(unitAddres != null && unitAddres.equalsIgnoreCase("") == false && unitAddres.equalsIgnoreCase("null") == false)
{
	hqlFilter.addQryCond("unitAddres", HqlFilter.Operator.LIKE, "%"+unitAddres+"%");
}
if(adminDep != null && adminDep.equalsIgnoreCase("") == false && adminDep.equalsIgnoreCase("null") == false)
{
	hqlFilter.addQryCond("adminDep", HqlFilter.Operator.LIKE, "%"+adminDep+"%");
}
if(ownerDep != null && ownerDep.equalsIgnoreCase("") == false && ownerDep.equalsIgnoreCase("null") == false)
{
	hqlFilter.addQryCond("ownerDep", HqlFilter.Operator.LIKE, "%"+ownerDep+"%");
}
if(maintDep != null && maintDep.equalsIgnoreCase("") == false && maintDep.equalsIgnoreCase("null") == false)
{
	hqlFilter.addQryCond("maintDep", HqlFilter.Operator.LIKE, "%"+maintDep+"%");
}
if(Chargetype != null && Chargetype.equalsIgnoreCase("") == false && Chargetype.equalsIgnoreCase("null") == false)
{
	hqlFilter.addQryCond("Chargetype", HqlFilter.Operator.LIKE, "%"+Chargetype+"%");
}
if(DyData != null && DyData.equalsIgnoreCase("") == false && DyData.equalsIgnoreCase("null") == false)
{
	hqlFilter.addQryCond("DyData", HqlFilter.Operator.LIKE, "%"+DyData+"%");
}

        List<Jc_tc_ybtcc> listObj = jc_tc_ybtccService.findByFilter(hqlFilter);
        JSONArray jsonArr = new JSONArray();
        int iTotalCnt = 0;
		for(int i=0;i<listObj.size();i++)
		{
			Jc_tc_ybtcc jc_tc_ybtcc = listObj.get(i);
			JSONObject jsonTmp = new JSONObject();
			jsonTmp.put("id", jc_tc_ybtcc.getId());
			jsonTmp.put("parkID",jc_tc_ybtcc.getparkID());
			jsonTmp.put("parkName",jc_tc_ybtcc.getparkName());
			jsonTmp.put("tradeName",jc_tc_ybtcc.gettradeName());
			jsonTmp.put("jztype",jc_tc_ybtcc.getjztype());
			jsonTmp.put("unitName",jc_tc_ybtcc.getunitName());
			jsonTmp.put("unitAddres",jc_tc_ybtcc.getunitAddres());
			jsonTmp.put("area",jc_tc_ybtcc.getarea());
			jsonTmp.put("adminDep",jc_tc_ybtcc.getadminDep());
			jsonTmp.put("ownerDep",jc_tc_ybtcc.getownerDep());
			jsonTmp.put("maintDep",jc_tc_ybtcc.getmaintDep());
			jsonTmp.put("berthNum",jc_tc_ybtcc.getberthNum());
			jsonTmp.put("UnberthNum",jc_tc_ybtcc.getUnberthNum());
			jsonTmp.put("GrberthNum",jc_tc_ybtcc.getGrberthNum());
			jsonTmp.put("ParkingNum",jc_tc_ybtcc.getParkingNum());
			jsonTmp.put("openNum",jc_tc_ybtcc.getopenNum());
			jsonTmp.put("parkTime",jc_tc_ybtcc.getparkTime());
			jsonTmp.put("chpileNum",jc_tc_ybtcc.getchpileNum());
			jsonTmp.put("BaFreeNum",jc_tc_ybtcc.getBaFreeNum());
			jsonTmp.put("MecNum",jc_tc_ybtcc.getMecNum());
			jsonTmp.put("Chargetype",jc_tc_ybtcc.getChargetype());
			jsonTmp.put("LoLeChtype",jc_tc_ybtcc.getLoLeChtype());
			jsonTmp.put("ShLeChtype",jc_tc_ybtcc.getShLeChtype());
			jsonTmp.put("DyData",jc_tc_ybtcc.getDyData());
			jsonTmp.put("picture",jc_tc_ybtcc.getpicture());
			jsonTmp.put("note",jc_tc_ybtcc.getnote());

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
		Jc_tc_ybtcc jc_tc_ybtcc = jc_tc_ybtccService.getById(id);
		if(jc_tc_ybtcc != null)
		{
			jsonObj.put("parkID",jc_tc_ybtcc.getparkID());
			jsonObj.put("parkName",jc_tc_ybtcc.getparkName());
			jsonObj.put("tradeName",jc_tc_ybtcc.gettradeName());
			jsonObj.put("jztype",jc_tc_ybtcc.getjztype());
			jsonObj.put("unitName",jc_tc_ybtcc.getunitName());
			jsonObj.put("unitAddres",jc_tc_ybtcc.getunitAddres());
			jsonObj.put("area",jc_tc_ybtcc.getarea());
			jsonObj.put("adminDep",jc_tc_ybtcc.getadminDep());
			jsonObj.put("ownerDep",jc_tc_ybtcc.getownerDep());
			jsonObj.put("maintDep",jc_tc_ybtcc.getmaintDep());
			jsonObj.put("berthNum",jc_tc_ybtcc.getberthNum());
			jsonObj.put("UnberthNum",jc_tc_ybtcc.getUnberthNum());
			jsonObj.put("GrberthNum",jc_tc_ybtcc.getGrberthNum());
			jsonObj.put("ParkingNum",jc_tc_ybtcc.getParkingNum());
			jsonObj.put("openNum",jc_tc_ybtcc.getopenNum());
			jsonObj.put("parkTime",jc_tc_ybtcc.getparkTime());
			jsonObj.put("chpileNum",jc_tc_ybtcc.getchpileNum());
			jsonObj.put("BaFreeNum",jc_tc_ybtcc.getBaFreeNum());
			jsonObj.put("MecNum",jc_tc_ybtcc.getMecNum());
			jsonObj.put("Chargetype",jc_tc_ybtcc.getChargetype());
			jsonObj.put("LoLeChtype",jc_tc_ybtcc.getLoLeChtype());
			jsonObj.put("ShLeChtype",jc_tc_ybtcc.getShLeChtype());
			jsonObj.put("DyData",jc_tc_ybtcc.getDyData());
			jsonObj.put("picture",jc_tc_ybtcc.getpicture());
			jsonObj.put("note",jc_tc_ybtcc.getnote());

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
