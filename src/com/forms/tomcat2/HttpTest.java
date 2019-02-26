package com.forms.tomcat2;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class HttpTest {
	private static ServerSocket ss;

	public static void main(String[] args) {
		try {
			ss = new ServerSocket(8888);
			Socket s = ss.accept();
			InputStream in = s.getInputStream();
			byte[] b = new byte[1024];
			int length = in.read(b);
			String str = new String(b, 0, length);
			System.out.println(str);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
//		System.out.println('\n' == 10);
//		System.out.println('\r' == 13);
	}
}
