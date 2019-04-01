package mygrades;

import java.sql.*;
import java.*;
import javax.swing.JOptionPane;

public class LoginJdbc {                        //数据库连接
    Connection con = null;
    Statement statement = null;
    ResultSet res = null;
    String driver = "com.mysql.cj.jdbc.Driver";
    String url  = "jdbc:mysql://localhost:3306/grades";//
    String name = "root";
    String passwd = "123456789";

    public LoginJdbc(){
        try{
            Class.forName(driver).newInstance();
            con = DriverManager.getConnection(url,name,passwd);
            statement = con.createStatement();

        }catch(ClassNotFoundException e){
            System.out.println("对不起，找不到这个Driver");
            e.printStackTrace();
        }catch(SQLException e){
            e.printStackTrace();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    //验证用户名和密码
    public boolean compare(String username, String password){
        boolean m = false;
        String sql = "select password from user where username=\""+username+"\"";
        try{
            res = statement.executeQuery(sql);
            if(res.next()){
                String p = res.getString(1);


                if(p.equals(password)){
                    System.out.println(username+" "+password);

                    m = true;
                }else {
                    JOptionPane.showMessageDialog(null, "密码错误！");
                }
            }else {
                JOptionPane.showMessageDialog(null, "用户名不存在！");
            }
          //  res.close();
          //  statement.close();
          //  con.close();
        }catch(SQLException e){
            e.printStackTrace();
        }
        return m;
    }
    //密码的修改
    public boolean update(String username,String newpassword)  {
        boolean b = true;
        String sql = "update user set password=\""+newpassword+"\"where username=\""+username+"\"";
        try {
            if(statement.executeUpdate(sql)==1){
                JOptionPane.showMessageDialog(null,"密码修改成功！");
            }
            statement.close();
            con.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "用户名不存在!!!");

            e.printStackTrace();
        }


        return b;
    }

}

