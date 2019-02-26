 
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
 
public class InsertTest {
 
    static AtomicLong id = new AtomicLong();
    static long count = 100_000_000;
    static int threadsize = 4;
 
    static class InsertDb {
 
        Connection conn = null;
        private int persize = 200_000;
 
        public  void initConn() throws ClassNotFoundException, SQLException {
 
//            String sql;
            String url = "jdbc:mysql://localhost:3306/test?"
                    + "user=root&password=123456&useUnicode=true&characterEncoding=UTF8&useSSL=false&serverTimezone=UTC";
 
            try {
                // 之所以要使用下面这条语句，是因为要使用MySQL的驱动，所以我们要把它驱动起来，
                // 可以通过Class.forName把它加载进去，也可以通过初始化来驱动起来，下面三种形式都可以
                Class.forName("com.mysql.cj.jdbc.Driver");// 动态加载mysql驱动
 
                System.out.println("成功加载MySQL驱动程序");
                // 一个Connection代表一个数据库连接
                conn = DriverManager.getConnection(url);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
 
 
        public  String randomStr(int size) {
            //定义一个空字符串
            String result = "";
 
            for (int i = 0; i < 6; ++i) {
                //生成一个97~122之间的int类型整数
                int intVal = (int) (Math.random() * 26 + 97);
                //强制转换（char）intVal 将对应的数值转换为对应的字符
                //将字符进行拼接
                result = result + (char) intVal;
            }
            //输出字符串
            return result;
        }
 
 
        public  void insert() {
            // 开时时间
            Long begin = new Date().getTime();
            // sql前缀
//            String prefix = "INSERT INTO tb_big_data (count, create_time, random,str) VALUES (?,?,?,?)";
 
            try {
                // 保存sql后缀
//                StringBuffer suffix = new StringBuffer();
                // 设置事务为非自动提交
                conn.setAutoCommit(false);
                //      Statement pst = conn.createStatement();
                // 比起st，pst会更好些
                PreparedStatement pst = conn.prepareStatement("INSERT INTO t_salary_detail_2018100 "
                		+ "(Fsalary_overview_id, Fcategory,Fdetail_name, Fdetail_amount, Fcreate_time, Fmodify_time) "
                		+ "VALUES (?,'SALARY',?,?,SYSDATE(),SYSDATE())");
                // 外层循环，总提交事务次数
                for (int i = 1; i <= count / persize /threadsize; i++) {
                    // 第次提交步长
                    for (int j = 1; j <= persize; j++) {
//                    	pst.setLong(1, id.incrementAndGet());
                    	pst.setLong(1, i*j*1);
                    	pst.setString(2, randomStr(6));
                    	pst.setLong(3, i*j*10);
                    	pst.addBatch();
                    	
                        // 构建sql后缀
//                        suffix.append("(" + id.incrementAndGet() + ", SYSDATE(), " + i * j
//                                * 1 + ",'" + randomStr(6) + "'),");
                    }
                    // 构建完整sql
//                    String sql = prefix + suffix.substring(0, suffix.length() - 1);
                    // 添加执行sql
//                    pst.addBatch(sql);
                    // 执行操作
                    pst.executeBatch();
                    // 提交事务
                    conn.commit();
                    // 清空上一次添加的数据
//                    suffix = new StringBuffer();
                }
                // 头等连接
                pst.close();
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            // 结束时间
            Long end = new Date().getTime();
            // 耗时
            System.out.println("cast : " + (end - begin) / 1000f + " ms");
        }
 
    }
 
 
 
    public static void main(String[] args) throws SQLException, ClassNotFoundException, InterruptedException {
 
        long s = System.currentTimeMillis();
        List<Thread> list = new ArrayList<>();
        for (int i = 0; i < threadsize; i++) {
            Thread th = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        InsertDb db = new InsertDb();
                        db.initConn();
                        db.insert();
 
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            });
            th.start();
            list.add(th);
        }
        for(Thread th : list){
            th.join();
        }
 
        long e = System.currentTimeMillis();
        System.out.println("total耗时"+(e -s)/1000f+"s");
    }
 
}