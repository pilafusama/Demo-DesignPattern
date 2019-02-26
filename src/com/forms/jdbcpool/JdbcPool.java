package com.forms.jdbcpool;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.LinkedList;
import java.util.Properties;
import java.util.logging.Logger;

import javax.sql.DataSource;

/**
 * 实现DataSource接口，并实现连接池功能的步骤：
 * 在构造函数中批量创建与数据库的连接，并把创建的连接加入LinkedList对象中。
 * 实现getConnection方法，每次调用时，从LinkedList中取一个Connection返回给用户。
 * 当用户使用完Connection，调用Connection.close()方法时，Collection对象应保证将自己返回到LinkedList中,而不要把conn还给数据库。
 * Collection保证将自己返回到LinkedList中是此处编程的难点。
 * @author v_dongzhao
 *
 */
public class JdbcPool implements DataSource {
	
	/**
	 * @Field: listConnections
	 * 由于要频繁读写List集合，所以这里使用LinkedList存储数据库连接比较合适
	 */
	private static LinkedList<Connection> listConnections = new LinkedList<Connection>();
	
	static {
		InputStream in = JdbcPool.class.getClassLoader().getResourceAsStream("db.properties");
		Properties props = new Properties();
		
		try {
			props.load(in);
			String driver = props.getProperty("driver");
			String url = props.getProperty("url");
			String username = props.getProperty("username");
			String password = props.getProperty("password");
			
			// 连接池初始化时连接数大小
			int jdbcPoolInitSize = Integer.parseInt(props.getProperty("jdbcPoolInitSize"));
			
			// 加载驱动
			Class.forName(driver);
			for (int i = 0; i < jdbcPoolInitSize; i++) {
				Connection conn = DriverManager.getConnection(url, username, password);
				System.out.println("获取到了链接" + conn);
				listConnections.add(conn);
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public Connection getConnection() throws SQLException {
		if (listConnections.size() > 0) {
			final Connection conn = listConnections.getLast();
			System.out.println("listConnections数据库连接池大小是" + listConnections.size());
			
			// Connection对象有close()方法来释放连接，通过代理实现：
			// 当用户使用完Connection，调用Connection.close()方法时，Collection对象应保证将自己返回到LinkedList中,而不要把conn还给数据库。
			return (Connection) Proxy.newProxyInstance(JdbcPool.class.getClassLoader(), 
					JdbcPool.class.getInterfaces(), new InvocationHandler() {
						@Override
						public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
							if (method.getName().equals("close")) {
								listConnections.addFirst(conn);
								System.out.println(conn + "被还给listConnections数据库连接池了！！");
								System.out.println("listConnections数据库连接池大小为" + listConnections.size());
								return null;
							} else {
								return method.invoke(conn, args);
							}
						}
			});
		} else {
			throw new RuntimeException("对不起，数据库忙！");
		}
		
	}

	@Override
	public Connection getConnection(String username, String password) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PrintWriter getLogWriter() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setLogWriter(PrintWriter out) throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public void setLoginTimeout(int seconds) throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public int getLoginTimeout() throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Logger getParentLogger() throws SQLFeatureNotSupportedException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> T unwrap(Class<T> iface) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isWrapperFor(Class<?> iface) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

}
