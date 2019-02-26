package com.forms.tomcat2;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class WebServer {
	//服务器对象
	private ServerSocket ss;
	//线程池对象
	private ExecutorService es;
	
	/**
	 * 构造函数
	 * @param port 监听的端口
	 * @param nThreads 线程池最大线程数
	 */
	public WebServer(int port,int nThreads) {
		try {
			ss = new ServerSocket(port);
			es = Executors.newFixedThreadPool(nThreads);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void start() {
		try {
			while (true) {
				System.out.println("等待客户端连接");
				Socket s = ss.accept();
				System.out.println("客户端"+s.getPort()+"已连接");
				ClientHandler ch = new ClientHandler(s);
				es.execute(ch);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	public static void main(String[] args) {
		WebServer ws = new WebServer(8080,40);
		ws.start();
	}
}
