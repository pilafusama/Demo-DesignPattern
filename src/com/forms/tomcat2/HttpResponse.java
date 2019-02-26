package com.forms.tomcat2;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class HttpResponse {
	private OutputStream os;
	
	private int statusCode = 200; //状态码，默认值为200
	private String statusReason = "OK"; //状态说明，默认值为OK
	
	private File entity; //响应页面文件，由此文件读入字节码，写到响应消息正文
	
	private Map<String,String> headers = new HashMap<String,String>(); //响应头键值对
	
	public HttpResponse(Socket socket) {
		try {
			os = socket.getOutputStream();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void flush() {
		//1.发送响应行
		sendResponseLine();
		
		//2.发送响应头
		sendHeader();
		
		//3.发送响应消息正文
		sendContent();
	}
	
	/**
	 * 发送响应行
	 */
	private void sendResponseLine() {
		String line = "HTTP/1.1" + " " + statusCode + " " + statusReason;
		println(line);
	}

	/**
	 * 发送响应头
	 */
	private void sendHeader() {
		// 本例主要设置Content-Type和Content-Length两个响应头信息，从文件获取
		for (Entry<String,String> entry : headers.entrySet()) {
			String key = entry.getKey();
			String value = entry.getValue();
			String str = key + ": " + value;
			println(str);
		}
	}

	/**
	 * 发送响应消息正文
	 */
	private void sendContent() {
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(entity);
			byte[] b = new byte[1024*10];
			int length = -1;
			while ((length = fis.read(b)) !=-1) {
				os.write(b, 0, length);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				fis.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public int getStatusCode() {
		return statusCode;
	}
	
	/**
	 * 状态码与说明一一对应
	 * @param statusCode
	 */
	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
		this.statusReason = HttpContext.getStatus(statusCode);
	}
	
	public File getEntity() {
		return entity;
	}
	
	public void setEntity(File entity) {
		this.entity = entity;
		String name = entity.getName();
		if (name.contains(".")) {
			String suffix = name.split("\\.")[1];
			headers.put("Content-Type", HttpContext.getMimeType(suffix));
		}
		headers.put("Content-Length", entity.length()+"");
	}
	
	/**
	 * 输出一行信息
	 * 考虑到html协议的编码问题，因此数据必须按照“ISO8859-1”进行编码
	 * @param message
	 */
	private void println(String message) {
		byte[] data;
		try {
			data = message.getBytes("ISO8859-1");
			os.write(data);
			println();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 输出回车符
	 */
	private void println() {
		try {
			os.write(13);
			os.write(10);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	

}
