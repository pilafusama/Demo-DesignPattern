package com.forms.tomcat2;

import java.io.File;
import java.io.IOException;
import java.net.Socket;

public class ClientHandler implements Runnable {

	private Socket socket;
	
	public ClientHandler(Socket s) {
		this.socket = s;
	}

	@Override
	public void run() {
		try {
			HttpRequest request = new HttpRequest(socket);
			HttpResponse response = new HttpResponse(socket);
			
			String requestURI = request.getRequestURI();
			File entity = new File("webapps" + requestURI);
			if (entity.exists()) {
				System.out.println("文件已找到");
			} else {
				response.setStatusCode(404);
				entity = new File("webapps/myweb/404.html");
				System.out.println("404!");
			}
			response.setEntity(entity);
			
			response.flush();
		} catch (EmptyRequestException e) {
			System.out.println("空请求");
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				socket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
