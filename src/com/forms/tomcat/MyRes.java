package com.forms.tomcat;

import java.io.IOException;
import java.io.OutputStream;

public class MyRes {
	private OutputStream os;

	public MyRes(OutputStream os) {
		super();
		this.os = os;
	}
	
	public void write(String content) throws IOException {
		StringBuffer httpRes = new StringBuffer();
		httpRes.append("HTTP/1.1 200 OK\n")
			.append("Content-Type: text/html\n")
			.append("\r\n")
			.append("<html><body>")
			.append(content)
			.append("</body></html>");
		os.write(httpRes.toString().getBytes());
		os.close();
	}
}
