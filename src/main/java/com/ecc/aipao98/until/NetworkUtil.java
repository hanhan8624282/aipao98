package com.ecc.aipao98.until;

import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class NetworkUtil {
	/**
	 * Logger for this class
	 */
	private static Logger logger = Logger.getLogger(NetworkUtil.class);

	/**
	 * 获取请求主机IP地址,如果通过代理进来，则透过防火墙获取真实IP地址;
	 * 
	 * @param request
	 * @return
	 * @throws IOException
	 */
	public final static String getIpAddress(HttpServletRequest request) {
		// 获取请求主机IP地址,如果通过代理进来，则透过防火墙获取真实IP地址
		String ip = request.getHeader("X-Forwarded-For");
		try {
			if (logger.isInfoEnabled()) {
				logger.info("getIpAddress(HttpServletRequest) - X-Forwarded-For - String ip=" + ip);
			}

			if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
				if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
					ip = request.getHeader("Proxy-Client-IP");
					if (logger.isInfoEnabled()) {
						logger.info("getIpAddress(HttpServletRequest) - Proxy-Client-IP - String ip=" + ip);
					}
				}
				if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
					ip = request.getHeader("WL-Proxy-Client-IP");
					if (logger.isInfoEnabled()) {
						logger.info("getIpAddress(HttpServletRequest) - WL-Proxy-Client-IP - String ip=" + ip);
					}
				}
				if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
					ip = request.getHeader("HTTP_CLIENT_IP");
					if (logger.isInfoEnabled()) {
						logger.info("getIpAddress(HttpServletRequest) - HTTP_CLIENT_IP - String ip=" + ip);
					}
				}
				if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
					ip = request.getHeader("HTTP_X_FORWARDED_FOR");
					if (logger.isInfoEnabled()) {
						logger.info("getIpAddress(HttpServletRequest) - HTTP_X_FORWARDED_FOR - String ip=" + ip);
					}
				}
				if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
					ip = request.getRemoteAddr();
					if (logger.isInfoEnabled()) {
						logger.info("getIpAddress(HttpServletRequest) - getRemoteAddr - String ip=" + ip);
					}
				}
			} else if (ip.length() > 15) {
				String[] ips = ip.split(",");
				for (int index = 0; index < ips.length; index++) {
					String strIp = (String) ips[index];
					if (!("unknown".equalsIgnoreCase(strIp))) {
						ip = strIp;
						break;
					}
				}
			}

		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return ip;
	}

	public static String getUserAgent(HttpServletRequest request) {
		String browserDetails = request.getHeader("User-Agent");
		String userAgent = browserDetails;
		String user = userAgent.toLowerCase();

		String os = "";
		String browser = "";

		// =================OS Info=======================
		if (userAgent.toLowerCase().indexOf("windows") >= 0) {
			os = "Windows";
		} else if (userAgent.toLowerCase().indexOf("mac") >= 0) {
			os = "Mac";
		} else if (userAgent.toLowerCase().indexOf("x11") >= 0) {
			os = "Unix";
		} else if (userAgent.toLowerCase().indexOf("android") >= 0) {
			os = "Android";
		} else if (userAgent.toLowerCase().indexOf("iphone") >= 0) {
			os = "IPhone";
		} else {
			os = "UnKnown, More-Info: " + userAgent;
		}
		// ===============Browser===========================
		return os + " --- " + userAgent;
	}

	public static String getSource(HttpServletRequest request) {
		String agent = request.getHeader("User-Agent").toLowerCase();

		if(agent.contains("micromessenger")) {
			return "wechat";
		} else if(agent.contains("iphone") || agent.contains("android")) {
			return "app";
		}
		return "";
	}

}
