package com.template.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.template.busi.safe.SafeFieldChecker;
import com.template.service.SysUserService;

@Controller
@RequestMapping("idcardController")
public class IdCardController {
	
	private static Logger logger = Logger.getLogger(IdCardController.class);

	@Autowired
	private SysUserService userService;
	
	@RequestMapping(value="queryresident",method = {RequestMethod.POST,RequestMethod.GET},produces="text/html;charset=UTF-8")
	@ResponseBody
	public String queryResident(String idnumber)
	{		
		JSONObject jsonObj = new JSONObject();
		try
		{
			List<Object> params = new ArrayList<Object>();
			String decnum = new SafeFieldChecker().checkField(userService, "jc_resident", "idnumber", idnumber);
			params.add(decnum);
			String sql = "select * from jc_resident where idnumber=?";
			List<HashMap> list = this.userService.findBySql(sql, params);
			if(list != null && list.size() > 0) {
				jsonObj.put("data", list.get(0));
				jsonObj.put("success", true);
			}
			else {
				jsonObj.put("success", false);
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
