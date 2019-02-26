import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Random;

public class SearchTest {
	
	static class SearchDb {
		Connection conn = null;	
		
		public void initConn() throws ClassNotFoundException, SQLException {
			 
	      String url = "jdbc:mysql://localhost:3306/test?"
	              + "user=root&password=123456&useUnicode=true&characterEncoding=UTF8&useSSL=false&serverTimezone=UTC";

	      try {
	          Class.forName("com.mysql.cj.jdbc.Driver");// 动态加载mysql驱动

	          System.out.println("成功加载MySQL驱动程序");
	          // 一个Connection代表一个数据库连接
	          conn = DriverManager.getConnection(url);
	      } catch (Exception e) {
	          e.printStackTrace();
	      }
	  }
		
		public void search() {
			try {
				PreparedStatement pst = conn.prepareStatement("SELECT * FROM t_salary_detail_2018100 where Fsalary_overview_id = ?");
				Random random = new Random(100);
				pst.setLong(1, random.nextInt(10000));
				pst.executeQuery();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	
	public static void main(String[] args) {
		for (int i = 0; i<50; i++) {
			Thread th = new Thread(new Runnable(){

				@Override
				public void run() {
					try {
						SearchDb db = new SearchDb();
						db.initConn();	
						while (true) {
							long s = System.currentTimeMillis();
							db.search();
							long e = System.currentTimeMillis();
							System.out.println(Thread.currentThread().getName()+"用时"+(e -s)/1000f+"s");
							Thread.currentThread().sleep(1000);
						}
					} catch (ClassNotFoundException | SQLException | InterruptedException e) {
						e.printStackTrace();
					}
				}
				
			});
			
			th.start();

		}
	}
}
