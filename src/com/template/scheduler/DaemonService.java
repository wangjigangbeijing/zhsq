package com.template.scheduler;

import java.util.ArrayList;
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
import com.template.model.SysUser;
import com.template.model.jcsqsj.Resident;
import com.template.model.jcsqsj.Room;
import com.template.service.DictionaryService;
import com.template.service.SysUserService;
import com.template.service.jcsqsj.ResidentService;
import com.template.service.jcsqsj.RoomService;
import com.template.util.ConstValue;
import com.template.util.HqlFilter;

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
	
	/*
	 * @Desc	
	 */
	public void job() 
	{
		try
		{
			logger.info("Periodic Job...");
			
			loadDictionaryInfo();
			
			loadResidentInfo();
			
			loadUserInfo();
			
			refreshResident();//定期刷民情图数据
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

	public void loadUserInfo()
	{
		try
		{
			List<SysUser> userList = userService.findByFilter(new HqlFilter());
			
			for(int i=0;i<userList.size();i++)
			{
				SysUser sysUser = userList.get(i);
				
				String id = sysUser.getId();
				String name = sysUser.getname();
				
				ConstValue.userMap.put(id,name);
			}
		}
		catch(Exception e)
		{
			logger.error(e.getMessage(),e);
		}
	}
	
	
	public void refreshResident()
	{
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
				
				for(int i=0;i<residentList.size();i++)
				{
					try
					{
						Resident resident = residentList.get(i);
						
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
	
						if(name == null || name.equalsIgnoreCase(""))
							continue;
						
						if(residentnames.indexOf(name) == -1)
						{
							residentnames = residentnames + name + ",";
						}
					}
					catch(Exception e)
					{
						logger.error(e.getMessage(),e);
					}
				}
				
				room.setpeoplecharacteristics(characteristics);

				room.setresidentname(residentnames);
				
				roomService.save(room);
			}
		}
		catch(Exception e)
		{
			logger.error(e.getMessage(),e);
		}		
	}
}