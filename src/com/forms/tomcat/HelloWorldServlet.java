package com.forms.tomcat;

import java.io.IOException;

public class HelloWorldServlet extends MyServlet{

	@Override
	public void doGet(MyReq req, MyRes res) {
		try {
			res.write("get HelloWorld");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void doPost(MyReq req, MyRes res) {
		try {
			res.write("post HelloWorld");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
