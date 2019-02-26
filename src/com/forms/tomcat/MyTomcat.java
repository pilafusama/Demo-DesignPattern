package com.forms.tomcat;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

/**
 * https://mp.weixin.qq.com/s/JmenXagf3OtjCIadqpJoog
 * 手写tomcat四大要素：
 * 1，servlet容器——用ServerSocket监听端口，分发请求
 * 2，servlet实现类——处理请求
 * 3，request类
 * 4，response类
 * @author v_dongzhao
 *
 */
public class MyTomcat {
	private int port = 8005;
	
	private Map<String, String> urlServletMap = new HashMap<String, String>();

	public MyTomcat(int port) {
		super();
		this.port = port;
	}
	
	/**
	 * 必备要素：监听本机上的一个端口，获取请求，将请求分发到不同的servlet实现类，来处理请求
	 */
	public void start() {
		initServletMapping();
		
		ServerSocket ss = null;
		
		try {
			ss = new ServerSocket(port);
			System.out.println("mytomcat is running...");
			
			while (true) {
				Socket s = ss.accept();
				InputStream is = s.getInputStream();
				OutputStream os = s.getOutputStream();
				
				MyReq req = new MyReq(is);
				MyRes res = new MyRes(os);
				
				dispatch(req, res);
				
				s.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (ss != null) {
				try {
					ss.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	private void initServletMapping() {
		for (ServletMapping mapping: ServletMappingConfig.servletMappingList) {
			urlServletMap.put(mapping.getUrl(), mapping.getClazz());
		}
	}
	
	private void dispatch(MyReq req, MyRes res) {
		String clazz = urlServletMap.get(req.getUrl());
		
		try {
			Class<MyServlet> myservletClass = (Class<MyServlet>) Class.forName(clazz);
			MyServlet servlet = myservletClass.newInstance();
			servlet.service(req, res);
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		new MyTomcat(8005).start();
	}
}
