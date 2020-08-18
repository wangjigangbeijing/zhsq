package com.template.busi.safe;

public class DataProcess {

	private static String showName(String s) {
		StringBuilder sb = new StringBuilder(s);
		sb.replace(1, 2, "*");
		return sb.toString();
	}
	
	private static String showPhone(String s) {
		StringBuilder sb = new StringBuilder(s);
		sb.replace(3, 7, "****");
		return sb.toString();
	}
	
	private static String showEmail(String s) {
		StringBuilder sb = new StringBuilder(s);
		sb.replace(1, 4, "***");
		return sb.toString();
	}
	
	public static String processData(String s, int type) {
		String result = s;
		switch(type) {
		case 1:
			result = showName(s);
			break;
		case 2:
			result = showPhone(s);
			break;
		case 3:
			result = showEmail(s);
			break;
		}
		return result;
	}
	
	public static void main(String[] args) {
		String s = "xiyoucjp0209@163.com";
		System.out.println(DataProcess.processData(s, 3));
	}
}
