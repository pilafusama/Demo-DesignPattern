package com.forms.tomcat2;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class HttpContext {
	/**
	 * 介质类型映射,key:资源后缀名,value:Content—Type中对应的值
	 */
	private static Map<String,String> MIME_MAPPING = new HashMap<String,String>();
	
	/**
	 * 状态代码与描述映射
	 */
	private static Map<Integer,String> STATUS_CODE_REASON_MAPPING = new HashMap<Integer,String>();
	
	static {
		initMimeMapping();
		initStatusCodeReasonMapping();
	}

	/**
	 * 使用DOM4J读取并解析conf/web.xml文件
	 * 将根标签中所有名为<mime-mapping>的子标签获取出来，
	 * 并将其子标签<extension>中间的文本作key,<mime-type>中间的文本作value,存入MIME_MAPPING完成初始化操作
	 */
	@SuppressWarnings("unchecked")
	private static void initMimeMapping() {
		try {
			SAXReader reader = new SAXReader();
			Document doc = reader.read("conf/web.xml");
			Element root = doc.getRootElement();
			List<Element> mimemappingEles = root.elements("mime-mapping");
			
			for(Element mimemappingEle : mimemappingEles) {
				String key = mimemappingEle.elementText("extension");
				String value = mimemappingEle.elementText("mime-type");
				MIME_MAPPING.put(key, value);
			}
		} catch (DocumentException e) {
			e.printStackTrace();
		}
	}

	private static void initStatusCodeReasonMapping() {
		STATUS_CODE_REASON_MAPPING.put(200, "OK");
		STATUS_CODE_REASON_MAPPING.put(201, "Created");
		STATUS_CODE_REASON_MAPPING.put(202, "Accepted");
		STATUS_CODE_REASON_MAPPING.put(204, "No Content");
		STATUS_CODE_REASON_MAPPING.put(300, "Multiple Choices");
		STATUS_CODE_REASON_MAPPING.put(301, "Moved Permanently");
		STATUS_CODE_REASON_MAPPING.put(302, "Moved Temporarily");
		STATUS_CODE_REASON_MAPPING.put(304, "Not Modified");
		STATUS_CODE_REASON_MAPPING.put(400, "Bad Request");
		STATUS_CODE_REASON_MAPPING.put(401, "Unauthorized");
		STATUS_CODE_REASON_MAPPING.put(403, "Forbidden");
		STATUS_CODE_REASON_MAPPING.put(404, "Not Found");
		STATUS_CODE_REASON_MAPPING.put(500, "Internal Server Error");
		STATUS_CODE_REASON_MAPPING.put(501, "Not Implemented");
		STATUS_CODE_REASON_MAPPING.put(502, "Bad Gateway");
		STATUS_CODE_REASON_MAPPING.put(503, "Service Unavailable");
	}
	
	public static String getStatus(Integer code) {
		return STATUS_CODE_REASON_MAPPING.get(code);
	}
	
	public static String getMimeType(String name) {
		return MIME_MAPPING.get(name);
	}
}
