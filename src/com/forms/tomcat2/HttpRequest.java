package com.forms.tomcat2;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.Socket;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

public class HttpRequest {
	private InputStream is;
	
	private String method;
	private String url;
	private String protocol;
	
	private String requestURI; //请求资源路径
	private String queryString; //请求参数字符串
	
	private Map<String,String> parameters = new HashMap<String,String>();
	private Map<String,String> headers = new HashMap<String,String>();

	public HttpRequest(Socket socket) throws EmptyRequestException {
		try {
			is = socket.getInputStream();
			//1.解析请求行
			parseRequestLine();
			//2.解析请求头
			parseHeaders();
			//3.解析消息正文（post请求时参数在消息正文中，请求头中包含Content-Type和Content-Length）
			parseContent();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 解析post消息正文
	 */
	private void parseContent() {
		try {
			if (headers.containsKey("Content-Length")) {
				int length = Integer.parseInt(headers.get("Content-Length")); //post请求体长度
				byte[] b = new byte[length];
				is.read(b);
				String type = headers.get("Content-Type");
				
				if ("application/x-www-form-urlencoded".equals(type)) {
					String str = new String(b, "ISO8859-1"); //html编码问题
					str = URLDecoder.decode(str, "utf-8");
					parseParameter(str);
				}
			}
		} catch (Exception e) {
			e.getStackTrace();
		}
	}

	/**
	 * 解析请求头装入map
	 */
	private void parseHeaders() {
		while (true) {
			String line = readLine();
			if ("".equals(line)) {
				break;
			}
			String[] data = line.split(":\\s");
			headers.put(data[0], data[1]);
		}
	}

	/**
	 * 读取一行内容
	 * 第一次调用时，读取请求行
	 * 此后直到读取到空行为止的内容为请求头
	 * @return
	 */
	public String readLine() {
		int i = -1;
		StringBuilder sb = new StringBuilder();
		char c1 = ' ', c2 = ' ';
		try {
			while ((i = is.read()) != -1) {
				c2 = (char)i;
				
				//13和10分别表示回车'\r'和换行'\n'，10和13连续出现时，代表一行读完
				if (c1 == 13 && c2 == 10) {
					break;
				}
				sb.append(c2);
				c1 = c2;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return sb.toString().trim();
	}
	
	/**
	 * 解析请求行
	 * 空请求时，抛出异常
	 * @throws EmptyRequestException 
	 * @throws UnsupportedEncodingException 
	 */
	private void parseRequestLine() throws EmptyRequestException, UnsupportedEncodingException {
		String line = readLine();
		//通过空格拆分字符串
		String[] strs = line.split("\\s");
		
		if (strs.length < 3) {
			throw new EmptyRequestException();
		}
		
		method = strs[0];
		url = strs[1];
		protocol = strs[2]; //协议版本
		
		parseUrl();
	}

	/**
	 * 解析url，得到请求路径
	 * @throws UnsupportedEncodingException 
	 */
	private void parseUrl() throws UnsupportedEncodingException {
		url = URLDecoder.decode(this.url, "utf-8"); //解码使用的字符集与html页面编码保持一致
		//请求路径中包含？时，需要解析请求参数
		if (url.contains("?")) {
			String[] data = url.split("\\?");
			requestURI = data[0];
			
			//防止参数为空造成角标越界
			if (data.length > 1) {
				queryString = data[1];
				parseParameter(queryString);
			} else {
				queryString = "";
			}
		} else {
			requestURI = url;
		}
	}

	/**
	 * 解析请求参数装入map
	 * @param queryString
	 */
	private void parseParameter(String queryString) {
		String[] data = queryString.split("&");
		for (String s : data) {
			String[] d = s.split("=");
			if (d.length > 1) {
				parameters.put(d[0], d[1]);
			} else { //如果参数值为空则存入空串，不判断会有越界异常问题
				parameters.put(d[0], "");
			}
		}
	}

	public String getMethod() {
		return method;
	}

	public String getUrl() {
		return url;
	}

	public String getProtocol() {
		return protocol;
	}

	public String getRequestURI() {
		return requestURI;
	}

	public String getQueryString() {
		return queryString;
	}
	
	public String getParameter(String name) {
		return parameters.get(name);
	}

}
