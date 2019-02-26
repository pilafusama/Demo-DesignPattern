package com.forms.tomcat;

public abstract class MyServlet {
	public abstract void doGet(MyReq req, MyRes res);
	public abstract void doPost(MyReq req, MyRes res);
	
	public void service(MyReq req, MyRes res) {
		if ("POST".equalsIgnoreCase(req.getMethod())) {
			doPost(req, res);
		} else if ("GET".equalsIgnoreCase(req.getMethod())) {
			doGet(req, res);
		}
	}
	
}
