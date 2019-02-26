package com.forms.tomcat;

import java.io.IOException;
import java.io.InputStream;

public class MyReq {
	private String url;
	private String method;
	
	public MyReq(InputStream is) throws IOException {
		String httpReq = "";
		byte[] httpReqBytes = new byte[1024];
		int length = 0;
		if ((length = is.read(httpReqBytes)) > 0) {
			httpReq = new String(httpReqBytes, 0, length);
		}
		
		String httpHead = httpReq.split("\n")[0];
		url = httpHead.split("\\s")[1];
		method = httpHead.split("\\s")[0];
		System.out.println(this);
	}

	@Override
	public String toString() {
		return "MyReq [url=" + url + ", method=" + method + "]";
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}
	
	
	
}
