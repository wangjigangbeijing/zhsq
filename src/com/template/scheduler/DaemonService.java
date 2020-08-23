package com.template.scheduler;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.template.model.SysDictionary;
import com.template.model.SysOrganization;
import com.template.model.SysRole;
import com.template.model.SysUser;
import com.template.model.SysUserOrganization;
import com.template.model.jcsqsj.Jc_advertisement;
import com.template.model.jcsqsj.Residebuilding;
import com.template.model.jcsqsj.Resident;
import com.template.model.jcsqsj.Room;
import com.template.service.DictionaryService;
import com.template.service.SysOrganizationService;
import com.template.service.SysRoleService;
import com.template.service.SysUserOrganizationService;
import com.template.service.SysUserService;
import com.template.service.jcsqsj.FamilyService;
import com.template.service.jcsqsj.Jc_advertisementService;
import com.template.service.jcsqsj.ResidebuildingService;
import com.template.service.jcsqsj.ResidentService;
import com.template.service.jcsqsj.RoomService;
import com.template.util.ConstValue;
import com.template.util.HqlFilter;
import com.template.util.TimeUtil;

/*

 */
@Component("DaemonService")
public class DaemonService 
{
	public static Logger logger = Logger.getLogger(DaemonService.class);
	
	@Autowired
	private DictionaryService dictionaryService;
	
	@Autowired
	private ResidentService residentService;
	
	@Autowired
	private RoomService roomService;

	@Autowired
	private SysUserService userService;
	
	@Autowired
	private SysRoleService roleService;

	@Autowired
	private SysOrganizationService organizationService;
	
	@Autowired
	private SysUserOrganizationService userOrganizationService;
	
	@Autowired
	private Jc_advertisementService jc_advertisementService;
	
	@Autowired
	private ResidebuildingService residebuildingService;
	
	@Autowired
	private FamilyService familyService;
	
	/*
	 * @Desc	
	 */
	public void twoMinsJob() 
	{
		try
		{
			logger.info("Periodic Job...");
			
			loadDictionaryInfo();
			
			loadResidentInfo();
			
			loadRoleInfo();
			
			loadUserInfo();
			
			loadOrganizationInfo();
			
			loadAdvertisementInfo();
			
			//refreshResident();//定期刷民情图数据
			
			//refreshResidentBuilding();//定期刷民情图数据
		}
		catch(Exception e)
		{
			logger.error(e.getMessage(),e);
		}
	}
	
	public void dailyJob() 
	{
		try
		{
			logger.info("Periodic Job...");
			
			refreshResident();//定期刷民情图数据
			
			
			refreshResidentBuilding();//定期刷民情图数据
			
		}
		catch(Exception e)
		{
			logger.error(e.getMessage(),e);
		}
	}
	
	
	public void loadDictionaryInfo()
	{
		HqlFilter hqlFilter = new HqlFilter();
		
        List<SysDictionary> listDic = dictionaryService.findByFilter(hqlFilter);
        
		for(int i=0;i<listDic.size();i++)
		{
			SysDictionary dictionary = listDic.get(i);
			
			String dicName = dictionary.getdic_enname();
			
			ConstValue.dicList.add(dicName);
			
			String dicVal = dictionary.getdic_value();
			
			String sql = dicVal;
			
			if(sql.indexOf("where") != -1)
				sql = sql.substring(0,sql.indexOf("where"));
			
			String id = dictionary.getdic_enname();
			
			logger.debug(sql);
			
			if(dictionary.getdic_type().equalsIgnoreCase("SQL"))
			{
				try
				{
					logger.debug(sql);
					
					List<HashMap> listObj = dictionaryService.findBySql(sql);
					
					if(id.equalsIgnoreCase("ofunit") == false)
					{
						for(int j=0;j<listObj.size();j++)
						{
							HashMap hm = listObj.get(j);//这里的hashmap  第一个field是key   第二个field是value
							
							String key = "";
							String val = "";
							
							//JSONObject jsonTmp = new JSONObject();
							
							if(hm.containsKey("id"))
							{
								key = (String)hm.get("id");
								val = (String)hm.get("id");
							}
							
							if(hm.containsKey("value"))
							{
								val = (String)hm.get("value");
							}
							
							ConstValue.hmDicMap.put(key, val);
						}
					}
				}
				catch(Exception e)
				{
					logger.error(e.getMessage(),e);
				}
			}
		}
	}
	
	public void loadResidentInfo()
	{
		logger.debug("loadResidentInfo");
		try
		{
			List<Resident> residentList = residentService.findByFilter(new HqlFilter());
			
			for(int i=0;i<residentList.size();i++)
			{
				Resident resident = residentList.get(i);
				
				String id = resident.getId();
				String name = resident.getname();
				
				ConstValue.residentMap.put(id,name);
			}
		}
		catch(Exception e)
		{
			logger.error(e.getMessage(),e);
		}
	}


	public void loadRoleInfo()
	{
		logger.debug("loadRoleInfo");
		try
		{
			List<SysRole> roleList = roleService.findByFilter(new HqlFilter());
			
			for(int i=0;i<roleList.size();i++)
			{
				SysRole sysRole = roleList.get(i);
				
				String id = sysRole.getId();
				String name = sysRole.getname();
				
				ConstValue.roleMap.put(id,name);
			}
		}
		catch(Exception e)
		{
			logger.error(e.getMessage(),e);
		}
	}
	
	public void loadUserInfo()
	{
		logger.debug("loadUserInfo");
		try
		{
			List<SysUser> userList = userService.findByFilter(new HqlFilter());
			
			for(int i=0;i<userList.size();i++)
			{
				SysUser sysUser = userList.get(i);
				
				String id = sysUser.getId();
				String name = sysUser.getname();
				
				ConstValue.userMap.put(id,name);
				
				HqlFilter hqlFilterOrganzation = new HqlFilter();
				hqlFilterOrganzation.addQryCond("user", HqlFilter.Operator.EQ, sysUser.getId());
				
				List<SysUserOrganization> userOrgList = userOrganizationService.findByFilter(hqlFilterOrganzation);

				String organizations = "";
				
				for(int j=0;j<userOrgList.size();j++)
				{
					organizations += userOrgList.get(j).getorganization()+",";
				}
				
				ConstValue.userToOrgMap.put(id,organizations);
			}
		}
		catch(Exception e)
		{
			logger.error(e.getMessage(),e);
		}
	}
	
	public void loadOrganizationInfo()
	{
		logger.debug("loadOrganizationInfo");
		try
		{
			List<SysOrganization> orgList = organizationService.findByFilter(new HqlFilter());
			
			for(int i=0;i<orgList.size();i++)
			{
				SysOrganization sysOrganization = orgList.get(i);
				
				String id = sysOrganization.getId();
				String name = sysOrganization.getname();
				
				ConstValue.orgMap.put(id,name);
				
				ConstValue.orgIdToOrganization.put(id,sysOrganization);
			}
		}
		catch(Exception e)
		{
			logger.error(e.getMessage(),e);
		}
	}
	
	public void loadAdvertisementInfo()
	{
		logger.debug("loadAdvertisementInfo");
		try
		{
			List<Jc_advertisement> orgList = jc_advertisementService.findByFilter(new HqlFilter());
			
			for(int i=0;i<orgList.size();i++)
			{
				Jc_advertisement sysOrganization = orgList.get(i);
				
				String id = sysOrganization.getId();
				String name = sysOrganization.getname();
				
				ConstValue.advertisementMap.put(id,name);
			}
		}
		catch(Exception e)
		{
			logger.error(e.getMessage(),e);
		}
	}
	
	public void refreshResident()
	{
		logger.debug("refreshResident");
		try
		{
			List<Room> roomList = roomService.findByFilter(new HqlFilter());
			
			for(int r=0;r<roomList.size();r++)	
			{
				Room room = roomList.get(r);
				
				String roomId = room.getId();
				
				HqlFilter hqlFilter = new HqlFilter();
				
				hqlFilter.addQryCond("ofroom", HqlFilter.Operator.EQ, roomId);
				
				List<Resident> residentList = residentService.findByFilter(hqlFilter);
				
				String characteristics = "";
				
				String residentnames = "";
				
				String residentids = "";
				
				for(int i=0;i<residentList.size();i++)
				{
					try
					{
						Resident resident = residentList.get(i);
						
						Date birthday = resident.getbirthday();//刷新居民年龄
						
						if(birthday != null)
						{
							long years = TimeUtil.getTimeSpanYear(birthday, new Date());
							
							years += 1;
							
							resident.setage(String.valueOf(years));
							
							String yearChar = "";
							if(years <= 6)
								yearChar = "0-6岁儿童";
							else if(years <= 13 && years >6)
								yearChar = "7-13岁青少年"; 
							else if(years <= 18 && years >13)
								yearChar = "13-18岁青少年"; 
							else if(years <= 80 && years >60)
								yearChar = "老年人"; 
							else if(years <= 90 && years >80)
								yearChar = "80岁以上老人"; 
							else if(years >90)
								yearChar = "90岁以上老人"; 
							
							String curChar = resident.getcharacteristics();
							
							curChar.replaceAll("0-6岁儿童", "");
							curChar.replaceAll("7-13岁青少年", "");
							curChar.replaceAll("13-18岁青少年", "");
							curChar.replaceAll("老年人", "");
							curChar.replaceAll("80岁以上老人", "");
							curChar.replaceAll("90岁以上老人", "");
							
							curChar += ","+yearChar;
							
							resident.setcharacteristics(curChar);
						}
						residentService.save(resident);
						
						String residentcharacteristic = resident.characteristics;
						
						if(residentcharacteristic != null && residentcharacteristic.equalsIgnoreCase("") == false)
						{
							//==========================  人员角色刷新
							
							String [] charArr = residentcharacteristic.split(",");
							
							for(int j=0;j<charArr.length;j++)
							{
								if(characteristics.indexOf(charArr[j]) == -1)
								{
									characteristics = characteristics + charArr[j] + ",";
								}
							}
						}
						
						//==========================  人员名称刷新
						String name = resident.getname();
						String id = resident.getId();
	
						if(name == null || name.equalsIgnoreCase(""))
							continue;
						
						if(residentids.indexOf(id) == -1)
						{
							residentnames = residentnames + name + ",";
							residentids = residentids + id + ",";
						}
					}
					catch(Exception e)
					{
						logger.error(e.getMessage(),e);
					}
				}
				
				room.setpeoplecharacteristics(characteristics);
				
				int nameLength = 63;
				if(residentnames.length() < nameLength)
					nameLength = residentnames.length();
				
				if(residentnames.endsWith(","))
					residentnames = residentnames.substring(0,nameLength - 1);
				
				if(residentids.endsWith(","))
					residentids = residentids.substring(0,residentids.length() - 1);

				room.setresidentname(residentnames);
				room.setresidentids(residentids);				
				roomService.save(room);
			}
		}
		catch(Exception e)
		{
			logger.error(e.getMessage(),e);
		}		
	}
	
	public void refreshResidentBuilding()
	{
		logger.debug("refreshResidentBuilding");
		try
		{
			List<Residebuilding> resideBuildingList = residebuildingService.findByFilter(new HqlFilter());
			
			for(int i=0;i<resideBuildingList.size();i++)	
			{
				Residebuilding building = resideBuildingList.get(i);
				
				String buildingId = building.getId();
				
				HqlFilter hqlFilter = new HqlFilter();
				
				hqlFilter.addQryCond("ofresidebuilding", HqlFilter.Operator.EQ, buildingId);
				
				Integer familyCount = familyService.countByFilter(hqlFilter).intValue();
				
				building.setfamiliesinbuilding(familyCount);
				
				residebuildingService.save(building);
			}
		}
		catch(Exception e)
		{
			logger.error(e.getMessage(),e);
		}
	}
	
}
