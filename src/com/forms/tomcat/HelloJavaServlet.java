package com.forms.tomcat;

import java.io.IOException;

public class HelloJavaServlet extends MyServlet{

	@Override
	public void doGet(MyReq req, MyRes res) {
		try {
			res.write("get HelloJava");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void doPost(MyReq req, MyRes res) {
		try {
			res.write("post HelloJava");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
