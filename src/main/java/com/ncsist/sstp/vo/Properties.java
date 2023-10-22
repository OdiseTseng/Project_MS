package com.ncsist.sstp.vo;

public class Properties {
	private static String SERVER_IP = "127.0.0.1";
	private static Integer SERVER_PORT = 8080;

	private static String NETTY_IP = "localhost";
	private static Integer NETTY_PORT = 6666;

	public static String getServerIp() {
		return SERVER_IP;
	}

	public static void setServerIp( String serverIp ) {
		SERVER_IP = serverIp;
	}

	public static Integer getServerPort() {
		return SERVER_PORT;
	}

	public static void setServerPort( Integer serverPort ) {
		SERVER_PORT = serverPort;
	}

	public static String getNettyIp() {
		return NETTY_IP;
	}

	public static void setNettyIp(String nettyIp) {
		NETTY_IP = nettyIp;
	}

	public static Integer getNettyPort() {
		return NETTY_PORT;
	}

	public static void setNettyPort(Integer nettyPort) {
		NETTY_PORT = nettyPort;
	}
}
