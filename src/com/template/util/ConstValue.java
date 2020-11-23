package com.template.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.template.model.SysOrganization;

public class ConstValue {
	
	public final static String TABLE_CONTROLLER = "tableController";
	public final static String TABLE_CONTROLLER_ADD_OR_UPDATE_TABLE = "addOrUpdateTable";
	public final static String TABLE_CONTROLLER_DELETE_TABLE = "deleteTable";
	public final static String TABLE_CONTROLLER_GET_TABLE = "getTable";
	public final static String TABLE_CONTROLLER_LOAD_TABLE = "loadTable";
	
	public final static String USER_CONTROLLER = "userController";
	public final static String USER_CONTROLLER_ADD_OR_UPDATE_USER = "addOrUpdateUser";
	public final static String USER_CONTROLLER_DELETE_USER = "deleteUser";
	public final static String USER_CONTROLLER_GET_USER = "getUser";
	public final static String USER_CONTROLLER_LOAD_USER = "loadUser";
	
	public final static String ATTRIBUTE_CONTROLLER = "attributeController";
	public final static String ATTRIBUTE_CONTROLLER_LOAD_ATTRIBUTE = "loadAttribute";
	public final static String ATTRIBUTE_CONTROLLER_GET_ATTRIBUTE = "getAttribute";
	public final static String ATTRIBUTE_CONTROLLER_ADD_OR_UPDATE_ATTRIBUTE = "addOrUpdateAttribute";
	public final static String ATTRIBUTE_CONTROLLER_DELETE_ATTRIBUTE = "deleteAttribute";
	
	public final static String DATA_CONTROLLER = "dataController";
	public final static String DATA_CONTROLLER_LOAD_DATA_OF_TABLE = "loadDataOfTable";
	public final static String DATA_CONTROLLER_GET_DATA = "getData";
	public final static String DATA_CONTROLLER_ADD_OR_UPDATE_DATA = "addOrUpdateData";
	public final static String DATA_CONTROLLER_DELETE_DATA = "deleteData";
	
	public final static String TEMPLATE_CONTROLLER = "templateController";
	public final static String TEMPLATE_CONTROLLER_LOAD_DATA_OF_LAYER = "loadData";
	public final static String TEMPLATE_CONTROLLER_GET_DATA = "getData";
	public final static String TEMPLATE_CONTROLLER_ADD_OR_UPDATE_DATA = "addOrUpdateData";
	public final static String TEMPLATE_CONTROLLER_GENERATE_CODE = "generateCode";
	
	public final static String FILE_CONTROLLER = "fileController";
	public final static String FILE_UPLOAD = "upload";
	public final static String FILE_DOWNLOAD = "download";	
	
	public final static String LOGIN_CONTROLLER = "loginController";
	public final static String LOGIN_CONTROLLER_LOGIN = "login";
	public final static String LOGIN_CONTROLLER_LOGOUT = "logout";
	public final static String LOGIN_CONTROLLER_GET_CURRENT_LOGIN_USER_INFO = "getCurrentLoginUserInfo";
	
	public final static String ORGANIZATION_CONTROLLER = "organizationController";
	public final static String ORGANIZATION_CONTROLLER_LOAD_ORGANIZATION = "loadOrganization";
	public final static String ORGANIZATION_CONTROLLER_GET_ORGANIZATION = "getOrganization";
	public final static String ORGANIZATION_CONTROLLER_ADD_OR_UPDATE_ORGANIZATION = "addOrUpdateOrganization";
	public final static String ORGANIZATION_CONTROLLER_DELETE_ORGANIZATION = "deleteOrganization";
	
	//public final static String DIRECTORY_CONTROLLER = "directoryController";
	//public final static String DIRECTORY_CONTROLLER_LOAD_DIRECTORIES = "loadDirectories";
	public final static String DICTIONARY_CONTROLLER_LOAD_COMMUNITY_BY_STREET = "loadCommunityByStreet";
	
	public final static String DASHBOARD_CONTROLLER = "dashboardController";
	public final static String DASHBOARD_CONTROLLER_LOAD_ENT_DATA = "loadEntData";
	public final static String DASHBOARD_CONTROLLER_GET_ENT_DATA = "getEntData";
	public final static String DASHBOARD_CONTROLLER_GET_ENT_IMAGE = "getEntImage";
	public final static String DASHBOARD_CONTROLLER_LOAD_STREET_LIST = "loadStreetList";
	
	public final static String DATAMANAGEMENT_CONTROLLER = "dataManagementController";
	public final static String DATAQRYEXP_CONTROLLER = "dataQryExpController";
	public final static String DATAMANAGEMENT_CONTROLLER_UPDATE_STATUS = "updateStatus";
	public final static String DATAMANAGEMENT_CONTROLLER_UPDATE_CHAIN_STORE = "updateChainStore";
	public final static String DATAMANAGEMENT_CONTROLLER_EXPORT_ENT_DATA = "exportEntData";
	
	public final static String DICTIONARY_CONTROLLER = "dictionaryController";
	public final static String DICTIONARY_CONTROLLER_LOAD_BUSI_TYPE = "loadBusiType";
	
	public final static String DATASTAT_CONTROLLER = "dataStatController";
	public final static String DATASTAT_CONTROLLER_LOAD_STAT_CHART = "loadStatChart";
	
	public final static String DYNSTAT_CONTROLLER = "dynStatController";
	public final static String DYNSTAT_CONTROLLER_LOAD_DYN_STAT = "loadDynStat";
	
	public final static String SESSION_USER_ID = "USER_ID";
	public final static String SESSION_USER_TYPE = "USER_TYPE";
	public final static String SESSION_USER_STREET = "USER_STREET";
	public final static String SESSION_USER_NAME = "USER_NAME";
	public final static String SESSION_USER_ORG = "USER_ORG";
	//0 采集人员 1 web街道，具备数据审核功能   2 web客户 3 web系统管理员  4 采集管理员,可以补采
	public final static int USER_TYPE_ADMIN = 0;
	public final static int USER_TYPE_COMMUNITY = 1;
	
	public final static String DB_TYPE_STRING = "字符串";
	public final static String DB_TYPE_NUMBER = "数字";
	public final static String DB_TYPE_TIME = "日期时间";
	public final static String DB_TYPE_DATE = "日期";
	
	public final static String COMPONENT_TYPE_RADIO = "单选";
	public final static String COMPONENT_TYPE_DROPDOWN = "下拉";
	public final static String COMPONENT_TYPE_CHECK = "多选";
	public final static String COMPONENT_TYPE_FILE = "文件";
	public final static String COMPONENT_TYPE_IMAGE = "图片";
	public final static String COMPONENT_TYPE_NUMBER_INTEGER = "数字-整型";
	public final static String COMPONENT_TYPE_NUMBER_FLOAT = "数字-浮点";
	public final static String COMPONENT_TYPE_INPUT = "单行文本";
	public final static String COMPONENT_TYPE_TEXTAREA = "多行文本";
	public final static String COMPONENT_TYPE_DATE = "日期";
	public final static String COMPONENT_TYPE_DATETIME = "日期时间";
	public final static String COMPONENT_TYPE_TIME = "时间";
	
	public final static String HTTP_HEADER_SOURCE = "source";
	public final static String HTTP_HEADER_USERID = "userId";
	public final static String HTTP_HEADER_TOKEN = "token";
	public final static String HTTP_HEADER_SOURCE_APP = "app";
	
	//public static HashMap<String,String> hmOrgId2OrgName = new HashMap<String,String>();
	
	public static String MASTER_SECRET = "d0125ef24923e68a39ab59c9";
	public static String APP_KEY = "92c1c083b7fcea14a5cf79e4";
	
	public final static String VALUE_SPLITTER = ",";
	
	public final static String SMS_STATUS_INITIAL = "等待发送";
	public final static String SMS_STATUS_SUBMIT_SUCCESS = "提交成功";
	public final static String SMS_STATUS_SEND_SUCCESS = "发送成功";
	public final static String SMS_STATUS_PARTIAL_SUCCESS = "部分成功";
	public final static String SMS_STATUS_FAILED = "失败";
	
	public static ArrayList<String> dicList = new ArrayList<String>();
	
	public static HashMap<String,String> hmDicMap = new HashMap<String,String>();
	
	public static HashMap<String,String> residentMap = new HashMap<String,String>();
	
	public static HashMap<String,String> userMap = new HashMap<String,String>();
	
	public static HashMap<String,String> roleMap = new HashMap<String,String>();
	
	public static HashMap<String,String> userToOrgMap = new HashMap<String,String>();//用户的社区组织ID
	
	public static HashMap<String,List<String>> userToRoleMap = new HashMap<String,List<String>>();
	
	public static HashMap<String,String> orgMap = new HashMap<String,String>();

	public static HashMap<String,SysOrganization> orgIdToOrganization = new HashMap<String,SysOrganization>();
	
	public static HashMap<String,String> advertisementMap = new HashMap<String,String>();
}
