package mygrades;

import javax.swing.*;
import java.sql.*;
import java.util.Vector;

public class GradesJdbc {
    //数据库表中的数据
    public static Vector getRows() {                            //容器
        String driver = "com.mysql.cj.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/grades";//
        String name = "root";
        String passwd = "123456789";
        Connection connection;
        PreparedStatement preparedStatement = null;
        Vector rows = null;
        Vector columnHeads = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url, name, passwd);
            preparedStatement = connection.prepareStatement("select * from grades.mygrades");
            ResultSet resultSet = preparedStatement.executeQuery();
            if (!resultSet.next())
                JOptionPane.showMessageDialog(null,"没有记录");
            rows = new Vector();
            ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
            /*
            指针指向下一行数据
            第一行显示不出来
             */
            while (resultSet.next()){
                rows.addElement(getNextRow(resultSet,resultSetMetaData));
            }

        } catch (ClassNotFoundException e) {
            System.out.println("对不起，找不到这个Driver");
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rows;
    }


    //表头
    public static Vector getHead() {
        String url = "jdbc:mysql://localhost:3306/grades";//
        String name = "root";
        String passwd = "123456789";
        Connection connection;
        PreparedStatement preparedStatement = null;
        Vector columnHeads = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url, name, passwd);
            preparedStatement = connection.prepareStatement("select * from mygrades");
            ResultSet resultSet1 = preparedStatement.executeQuery();
            //if (!resultSet1.next())
               // JOptionPane.showMessageDialog(null,"没有记录");
            columnHeads = new Vector();
            ResultSetMetaData resultSetMetaData = resultSet1.getMetaData();
            for (int i=1; i<=resultSetMetaData.getColumnCount();i++)
                columnHeads.addElement(resultSetMetaData.getColumnName(i));
        } catch (ClassNotFoundException e) {
            System.out.println("对不起，找不到这个Driver");
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return columnHeads;
    }



    //本行数据
    private static Vector getNextRow(ResultSet resultSet,ResultSetMetaData resultSetMetaData)throws SQLException{
        Vector currentRow = new Vector();
        for (int i=1;i<=resultSetMetaData.getColumnCount();i++){
            currentRow.addElement(resultSet.getString(i));
        }

    return currentRow;
    }
}

