package com.forms.socket.bio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {
	public static void main(String[] args) {
		int port = genPort(args);
		ServerSocket server = null;
		// 使用线程池
		ExecutorService service = Executors.newFixedThreadPool(50);
		try {
			server = new ServerSocket(port);
			System.out.println("server started!");
			while (true) {
				Socket socket = server.accept();
//				new Thread(new Handler(socket)).start();
				service.execute(new Handler(socket));
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (server != null) {
				try {
					server.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			server = null;
		}
	}
	
	static class Handler implements Runnable {
		private Socket socket;
		public Handler(Socket socket) {
			this.socket = socket;
		}
		
		@Override
		public void run() {
			BufferedReader reader = null;
			PrintWriter writer = null;
			try {
				reader = new BufferedReader(new InputStreamReader(socket.getInputStream(), "UTF-8"));
				writer = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), "UTF-8"), true);
				String msg = null;
				while (true) {
					System.out.println("Server reading...");
					msg = reader.readLine();
					if (msg == null) {
						break;
					}
					System.out.println(msg);
					writer.println("Server receive" + msg);
					writer.flush();
				}
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				if (socket != null) {
					try {
						socket.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				socket = null;
				
				if (reader != null) {
					try {
						reader.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				reader = null;
				
				if (writer != null) {
					writer.close();
				}
				writer = null;
			}
		}
		
	}

	private static int genPort(String[] args) {
		if (args.length > 0) {
			try {
				return Integer.parseInt(args[0]);
			} catch (NumberFormatException e) {
				return 9999;
			}
		} else {
			return 9999;
		}
	}
}
