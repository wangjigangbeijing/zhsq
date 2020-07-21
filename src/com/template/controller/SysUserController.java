package com.template.controller;
import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import com.template.model.SysUser;
import com.template.service.SysUserService;
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
@RequestMapping("sysUserController")
public class SysUserController {
	private static Logger logger = Logger.getLogger(SysUserController.class);
	@Autowired
	private  HttpServletRequest request;
	@Autowired
	private SysUserService sys_userService;
@RequestMapping(value="addOrUpdate",method = RequestMethod.POST,produces="text/html;charset=UTF-8")
@ResponseBody
public String addOrUpdate(String id,String name,String loginid,String password,String gender,String birthday,String joinday,String mobile,String department,String job,String role,String status)
{
	JSONObject jsonObj = new JSONObject();
	try
	{
		SysUser sys_user;
		if(id==null || id.equalsIgnoreCase(""))
		{
			sys_user = new SysUser();
			sys_user.setId(Utility.getUniStr());
		}
		else
		{
			sys_user = sys_userService.getById(id);
		}
		sys_user.setname(name);
		sys_user.setloginid(loginid);
		sys_user.setpassword(password);
		sys_user.setgender(gender);
		sys_user.setbirthday(TimeUtil.parseDate(birthday, "yyyy-MM-dd"));
		sys_user.setjoinday(TimeUtil.parseDate(joinday, "yyyy-MM-dd"));
		sys_user.setmobile(mobile);
		//sys_user.setdepartment(department);
		sys_user.setjob(job);
		sys_user.setrole(role);
		sys_user.setstatus(status);

        sys_userService.save(sys_user);
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
		SysUser sys_user = sys_userService.getById(id);
		sys_userService.delete(sys_user);
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
public String load(String name,String status)
{
	JSONObject jsonObj = new JSONObject();
	try
	{
		HqlFilter hqlFilter = new HqlFilter();
if(name != null && name.equalsIgnoreCase("") == false && name.equalsIgnoreCase("null") == false)
{
	hqlFilter.addQryCond("name", HqlFilter.Operator.LIKE, "%"+name+"%");
}
if(status != null && status.equalsIgnoreCase("") == false && status.equalsIgnoreCase("null") == false)
{
	hqlFilter.addQryCond("status", HqlFilter.Operator.LIKE, "%"+status+"%");
}

        List<SysUser> listObj = sys_userService.findByFilter(hqlFilter);
        JSONArray jsonArr = new JSONArray();
        int iTotalCnt = 0;
		for(int i=0;i<listObj.size();i++)
		{
			SysUser sys_user = listObj.get(i);
			JSONObject jsonTmp = new JSONObject();
			jsonTmp.put("id", sys_user.getId());
			jsonTmp.put("name",sys_user.getname());
			jsonTmp.put("loginid",sys_user.getloginid());
			jsonTmp.put("password",sys_user.getpassword());
			jsonTmp.put("gender",sys_user.getgender());
			if(sys_user.getbirthday() != null)
				jsonTmp.put("birthday",TimeUtil.formatDate(sys_user.getbirthday(),"yyyy-MM-dd"));
			else
				jsonTmp.put("birthday","-");
			
			if(sys_user.getjoinday() != null)
				jsonTmp.put("joinday",TimeUtil.formatDate(sys_user.getjoinday(),"yyyy-MM-dd"));
			else
				jsonTmp.put("joinday","-");
			
			jsonTmp.put("mobile",sys_user.getmobile());
			//jsonTmp.put("department",sys_user.getdepartment());
			jsonTmp.put("job",sys_user.getjob());
			jsonTmp.put("role",sys_user.getrole());
			jsonTmp.put("status",sys_user.getstatus());

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
		SysUser sys_user = sys_userService.getById(id);
		if(sys_user != null)
		{
			jsonObj.put("name",sys_user.getname());
			jsonObj.put("loginid",sys_user.getloginid());
			jsonObj.put("password",sys_user.getpassword());
			jsonObj.put("gender",sys_user.getgender());
			jsonObj.put("birthday",TimeUtil.formatDate(sys_user.getbirthday(),"yyyy-MM-dd"));
			jsonObj.put("joinday",TimeUtil.formatDate(sys_user.getjoinday(),"yyyy-MM-dd"));
			jsonObj.put("mobile",sys_user.getmobile());
			//jsonObj.put("department",sys_user.getdepartment());
			jsonObj.put("job",sys_user.getjob());
			jsonObj.put("role",sys_user.getrole());
			jsonObj.put("status",sys_user.getstatus());

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
