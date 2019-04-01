package demo;
import java.sql.*;


public class Jdbc {


        Connection con = null;
        Statement statement = null;
        String driver = "com.mysql.cj.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/user";
        String user = "root";
        String password = "123456789";
        public Jdbc()
        {
            try {
                Class.forName(driver);
                System.out.println("数据库加载成功");
            } catch (ClassNotFoundException e) {
                System.out.println("数据库加载失败");
                e.printStackTrace();

            }
            try {
                con = DriverManager.getConnection(url, user, password);
                System.out.println("数据库连接成功");
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                System.out.println("MySQL link go die");
                e.printStackTrace();
            }

        }
        public static void main(String[] args) {

            new Jdbc();

        }

}
