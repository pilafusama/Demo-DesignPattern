package com.forms.tomcat;

import java.util.ArrayList;
import java.util.List;

public class ServletMappingConfig {
	public static List<ServletMapping> servletMappingList = new ArrayList<>();
	
	/**
	 * 实现web.xml中通过servlet和servlet-mapping标签，来指定哪个URL交给哪个servlet进行处理。
	 */
	static {
		servletMappingList.add(new ServletMapping("helloWorld", 
				"/world", "com.forms.tomcat.HelloWorldServlet"));
		servletMappingList.add(new ServletMapping("helloJava", 
				"/java", "com.forms.tomcat.HelloJavaServlet"));
	}
}
