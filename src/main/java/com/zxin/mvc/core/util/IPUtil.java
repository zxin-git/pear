/**
 * Copyright: 版权所有 ( c ) 北京启明星辰信息技术股份有限公司 2011。保留所有权利。
 * Author: Administrator
 * Created: 2011-5-4
 */
package com.zxin.mvc.core.util;

import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

/**
 * IP工具类
 * 
 * @author liunan
 * 
 */
public abstract class IPUtil {

	private static final Pattern ip_pattern = Pattern
			.compile("^(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$");
	
	private static final Pattern ipv6_pattern = Pattern
			.compile("^((([0-9A-Fa-f]{1,4}:){7}[0-9A-Fa-f]{1,4})|(([0-9A-Fa-f]{1,4}:){6}:[0-9A-Fa-f]{1,4})|(([0-9A-Fa-f]{1,4}:){5}:([0-9A-Fa-f]{1,4}:)?[0-9A-Fa-f]{1,4})|(([0-9A-Fa-f]{1,4}:){4}:([0-9A-Fa-f]{1,4}:){0,2}[0-9A-Fa-f]{1,4})|(([0-9A-Fa-f]{1,4}:){3}:([0-9A-Fa-f]{1,4}:){0,3}[0-9A-Fa-f]{1,4})|(([0-9A-Fa-f]{1,4}:){2}:([0-9A-Fa-f]{1,4}:){0,4}[0-9A-Fa-f]{1,4})|(([0-9A-Fa-f]{1,4}:){6}((\\b((25[0-5])|(1\\d{2})|(2[0-4]\\d)|(\\d{1,2}))\\b)\\.){3}(\\b((25[0-5])|(1\\d{2})|(2[0-4]\\d)|(\\d{1,2}))\\b))|(([0-9A-Fa-f]{1,4}:){0,5}:((\\b((25[0-5])|(1\\d{2})|(2[0-4]\\d)|(\\d{1,2}))\\b)\\.){3}(\\b((25[0-5])|(1\\d{2})|(2[0-4]\\d)|(\\d{1,2}))\\b))|(::([0-9A-Fa-f]{1,4}:){0,5}((\\b((25[0-5])|(1\\d{2})|(2[0-4]\\d)|(\\d{1,2}))\\b)\\.){3}(\\b((25[0-5])|(1\\d{2})|(2[0-4]\\d)|(\\d{1,2}))\\b))|([0-9A-Fa-f]{1,4}::([0-9A-Fa-f]{1,4}:){0,5}[0-9A-Fa-f]{1,4})|(::([0-9A-Fa-f]{1,4}:){0,6}[0-9A-Fa-f]{1,4})|(([0-9A-Fa-f]{1,4}:){1,7}:))$");

	/**
	 * 是否为合法的IP地址格式
	 * 
	 * @param ip
	 * @return
	 */
	public static final boolean isValid(String ip) {
		return ip_pattern.matcher(ip).matches();
	}
	
	/**
	 * 是否为合法的IPv6地址格式
	 * @param ipv6
	 * @return
	 */
	public static final boolean isValidv6(String ipv6) {
		return ipv6_pattern.matcher(ipv6).matches();
	}

	/**
	 * 将IP地址转换为整数表示
	 * 
	 * @param ip
	 * @return
	 */
	public static final long ip2int(String ip) {

		if (isValid(ip)) {
			String[] segs = ip.split("\\.");
			long ipint = 0;
			for (int i = 0; i < 4; i++) {
				long n = Integer.parseInt(segs[i], 10);
				if (n < 0 || n > 255) {
					return -1;
				}
				ipint += (n << ((3 - i) * 8)) >>> 0;
			}
			return ipint;
		}

		return -1;
	}

	/**
	 * 将整数表示的IP地址转换为可识别的字符串格式
	 * 
	 * @param ip
	 * @return
	 */
	public static final String int2ip(long ip) {
		
		if (ip < 0)
			throw new IllegalArgumentException("IP address in int format, must be positive.");

		return ((ip >> 24) & 0xFF) + "." + ((ip >> 16) & 0xFF) + "."
				+ ((ip >> 8) & 0xFF) + "." + (ip & 0xFF);
	}

	/**
	 * 根据IP与子网掩码获得起始IP的长整数表示
	 * 
	 * @param sourceIP
	 * @param mask
	 *            子网掩码的位数
	 * @return
	 */
	public static final long getStartIP(String sourceIP, int mask) {
		long xx = ip2int(sourceIP);
		long yy = 0x00000000FFFFFFFF << (32 - mask);
		long startIP = xx & yy;
		return startIP;

	}

	/**
	 * 根据IP与子网掩码获得终点IP的长整数表示
	 * 
	 * @param sourceIP
	 * @param mask
	 *            子网掩码的位数
	 * @return
	 */
	public static long getEndIP(String sourceIP, int mask) {
		long xx = ip2int(sourceIP);
		long yy2 = 0x00000000FFFFFFFF >>> mask;
		long endIP = xx | yy2;
		return endIP;
	}

	/**
	 * 针对使用代理的方式获取真实IP
	 * 来源:http://www.blogjava.net/Werther/archive/2009/04/24/267420.html
	 * 
	 * @param request
	 * @return
	 */
	public static String getRealRemoteAddr(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}

	/**
	 * 将ip型子网掩码转化为十进制型子网掩码
	 * @param mask
	 * @return
	 */
	public static int mask2int(String mask) {
		if (mask.equals("128.0.0.0"))
			return 1;
		else if (mask.equals("192.0.0.0"))
			return 2;
		else if (mask.equals("224.0.0.0"))
			return 3;
		else if (mask.equals("240.0.0.0"))
			return 4;
		else if (mask.equals("248.0.0.0"))
			return 5;
		else if (mask.equals("252.0.0.0"))
			return 6;
		else if (mask.equals("254.0.0.0"))
			return 7;
		else if (mask.equals("255.0.0.0"))
			return 8;
		else if (mask.equals("255.128.0.0"))
			return 9;
		else if (mask.equals("255.192.0.0"))
			return 10;
		else if (mask.equals("255.224.0.0"))
			return 11;
		else if (mask.equals("255.240.0.0"))
			return 12;
		else if (mask.equals("255.248.0.0"))
			return 13;
		else if (mask.equals("255.252.0.0"))
			return 14;
		else if (mask.equals("255.254.0.0"))
			return 15;
		else if (mask.equals("255.255.0.0"))
			return 16;
		else if (mask.equals("255.255.128.0"))
			return 17;
		else if (mask.equals("255.255.192.0"))
			return 18;
		else if (mask.equals("255.255.224.0"))
			return 19;
		else if (mask.equals("255.255.240.0"))
			return 20;
		else if (mask.equals("255.255.248.0"))
			return 21;
		else if (mask.equals("255.255.252.0"))
			return 22;
		else if (mask.equals("255.255.254.0"))
			return 23;
		else if (mask.equals("255.255.255.0"))
			return 24;
		else if (mask.equals("255.255.255.128"))
			return 25;
		else if (mask.equals("255.255.255.192"))
			return 26;
		else if (mask.equals("255.255.255.224"))
			return 27;
		else if (mask.equals("255.255.255.240"))
			return 28;
		else if (mask.equals("255.255.255.248"))
			return 29;
		else if (mask.equals("255.255.255.252"))
			return 30;
		else if (mask.equals("255.255.255.254"))
			return 31;
		else if (mask.equals("255.255.255.255"))
			return 32;
		return 0;

	}

	/**
	 * 将十进制子网掩码转化为ip型子网掩码
	 * 
	 * @param mask
	 * @return
	 */
	public static String mask2ip(int mask) {
		if (mask == 1)
			return "128.0.0.0";
		else if (mask == 2)
			return "192.0.0.0";
		else if (mask == 3)
			return "224.0.0.0";
		else if (mask == 4)
			return "240.0.0.0";
		else if (mask == 5)
			return "248.0.0.0";
		else if (mask == 6)
			return "252.0.0.0";
		else if (mask == 7)
			return "254.0.0.0";
		else if (mask == 8)
			return "255.0.0.0";
		else if (mask == 9)
			return "255.128.0.0";
		else if (mask == 10)
			return "255.192.0.0";
		else if (mask == 11)
			return "255.224.0.0";
		else if (mask == 12)
			return "255.240.0.0";
		else if (mask == 13)
			return "255.248.0.0";
		else if (mask == 14)
			return "255.252.0.0";
		else if (mask == 15)
			return "255.254.0.0";
		else if (mask == 16)
			return "255.255.0.0";
		else if (mask == 17)
			return "255.255.128.0";
		else if (mask == 18)
			return "255.255.192.0";
		else if (mask == 19)
			return "255.255.224.0";
		else if (mask == 20)
			return "255.255.240.0";
		else if (mask == 21)
			return "255.255.248.0";
		else if (mask == 22)
			return "255.255.252.0";
		else if (mask == 23)
			return "255.255.254.0";
		else if (mask == 24)
			return "255.255.255.0";
		else if (mask == 25)
			return "255.255.255.128";
		else if (mask == 26)
			return "255.255.255.192";
		else if (mask == 27)
			return "255.255.255.224";
		else if (mask == 28)
			return "255.255.255.240";
		else if (mask == 29)
			return "255.255.255.248";
		else if (mask == 30)
			return "255.255.255.252";
		else if (mask == 31)
			return "255.255.255.254";
		else if (mask == 32)
			return "255.255.255.255";
		return "";

	}
}
