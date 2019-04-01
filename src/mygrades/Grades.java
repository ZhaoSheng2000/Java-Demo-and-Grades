package mygrades;


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.Vector;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.Date;
import java.text.SimpleDateFormat;


public class Grades extends JFrame   {
    DefaultTableModel defaultTableModel;                //组件
    JButton add,del,save;
    JLabel  school,myclass,numbre,name,printtime,schoolyear;
    JPanel jPaneldate,jPanelmessage;
    JTable jTable;

        public Grades(){
            this.setTitle("郑州轻工业大学学生成绩明细");
            this.setBounds(100,100,1000,550);
            this.setLayout(new BorderLayout());
            add = new JButton("增加成绩");
            del = new JButton("删除成绩");
            save = new JButton("保存成绩");

            school = new JLabel("院（系）/部:软件学院    ");
            myclass = new JLabel("行政班级：软件工程18-01    ");
            numbre = new JLabel("学号：541813460149    ");
            name = new JLabel("姓名：赵盛    ");
            Date date = new Date();
            SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd ");
            printtime = new JLabel("打印时间："+dateFormat.format(date));
            schoolyear = new JLabel("学年学期：2018-2019学年第一学期   ");

            jPaneldate = new JPanel();                                   //数据处理面板
            jPaneldate.setLayout(new FlowLayout(FlowLayout.CENTER));     //数据处理面板组件布局
            jPaneldate.add(add);
            jPaneldate.add(del);
            jPaneldate.add(save);

            jPanelmessage = new JPanel();                                            //信息面板
            jPanelmessage.setLayout(new FlowLayout(FlowLayout.LEFT));
            jPanelmessage.add(school);
            jPanelmessage.add(myclass);
            jPanelmessage.add(numbre);
            jPanelmessage.add(name);
            jPanelmessage.add(printtime);
            jPanelmessage.add(schoolyear);




            Vector rowData = GradesJdbc.getRows();  //行
            Vector columnNames = GradesJdbc.getHead();  //表头

            defaultTableModel = new DefaultTableModel(rowData,columnNames);     //表头和所有行数据
            jTable = new JTable(defaultTableModel);                            //创建表格
            jTable.setSelectionBackground(Color.LIGHT_GRAY);                   // 选中后字体背景
            jTable.setGridColor(Color.GRAY);                     // 网格颜色

            jTable.getTableHeader().setReorderingAllowed(false);             //不允许拖动排列各列的数据
            jTable.getTableHeader().setResizingAllowed(false);               //不允许手动改变列宽
            jTable.getColumnModel().getColumn(0).setPreferredWidth(40);  //列宽
            jTable.getColumnModel().getColumn(1).setPreferredWidth(160);  //列宽
            jTable.getColumnModel().getColumn(2).setPreferredWidth(40);  //列宽
            jTable.getColumnModel().getColumn(3).setPreferredWidth(180);  //列宽
            jTable.getColumnModel().getColumn(4).setPreferredWidth(70);  //列宽
            jTable.getColumnModel().getColumn(5).setPreferredWidth(70);  //列宽
            jTable.getColumnModel().getColumn(6).setPreferredWidth(70);  //列宽
            jTable.getColumnModel().getColumn(7).setPreferredWidth(70);  //列宽
            jTable.getColumnModel().getColumn(8).setPreferredWidth(70);  //列宽
            jTable.getColumnModel().getColumn(9).setPreferredWidth(70);  //列宽
            jTable.getColumnModel().getColumn(10).setPreferredWidth(70);  //列宽
            jTable.getColumnModel().getColumn(11).setPreferredWidth(70);  //列宽
            jTable.getColumnModel().getColumn(12).setPreferredWidth(150);  //列宽

            JScrollPane jScrollPane = new JScrollPane(jTable);              //创建滚动条

            this.add(jPaneldate,BorderLayout.SOUTH);          //将数据处理面板添加到表格底部
            this.add(jPanelmessage,BorderLayout.NORTH);
            this.add(jScrollPane);                              //添加滚动条

            MyEvent();                                          //动作处理

            this.setVisible(true);
            this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        }
        //动作实现
        public void MyEvent(){


            add.addActionListener(new ActionListener() {
                    @Override
                public void actionPerformed(ActionEvent e) {
                    defaultTableModel.addRow(new Vector());
                }
            });


            del.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    int rowcount = jTable.getSelectedRow();
                    if (rowcount >= 0){
                        defaultTableModel.removeRow(rowcount);
                    }

                }
            });

            save.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    int column = jTable.getColumnCount();
                    int row = jTable.getRowCount();


                    String url = "jdbc:mysql://localhost:3306/grades";
                    String name = "root";
                    String passwd = "123456789";
                    Connection connection;
                    PreparedStatement preparedStatement = null;

                    try {
                        String[][] value = new String[row][column];
                        System.out.println(row + "   " + column);
                        for (int i = 0; i < row; i++) {
                            for (int j = 0; j < column; j++) {
                                value[i][j] = String.valueOf(jTable.getValueAt(i, j));     //避免空指针
                            }
                        }

                        Class.forName("com.mysql.cj.jdbc.Driver");                      //以下为数据库操作
                        connection = DriverManager.getConnection(url, name, passwd);
                        if (!connection.isClosed())
                            System.out.println("成功连接数据库");
                         preparedStatement = connection.prepareStatement("delete from grades.mygrades");
                         preparedStatement.executeUpdate();
                         System.out.println("删除所有数据");
                         connection.close();


                        Class.forName("com.mysql.cj.jdbc.Driver");                      //以下为数据库操作
                        connection = DriverManager.getConnection(url, name, passwd);
                        if (!connection.isClosed())
                            System.out.println("成功连接数据库");
                        for (int i = 0; i < row; i++) {
                            String sql = "insert into grades.mygrades values(?,?,?,?,?,?,?,?,?,?,?,?,?)";
                             preparedStatement = connection.prepareStatement(sql);
                            preparedStatement.setString(1,value[i][0]);
                            preparedStatement.setString(2,value[i][1]);
                            preparedStatement.setString(3,value[i][2]);
                            preparedStatement.setString(4,value[i][3]);
                            preparedStatement.setString(5,value[i][4]);
                            preparedStatement.setString(6,value[i][5]);
                            preparedStatement.setString(7,value[i][6]);
                            preparedStatement.setString(8,value[i][7]);
                            preparedStatement.setString(9,value[i][8]);
                            preparedStatement.setString(10,value[i][9]);
                            preparedStatement.setString(11,value[i][10]);
                            preparedStatement.setString(12,value[i][11]);
                            preparedStatement.setString(13,value[i][12]);

                            preparedStatement.executeUpdate();
                        }

                            System.out.println("成功重新写入数据！");
                    } catch (ClassNotFoundException e1) {
                        // TODO Auto-generated catch block
                        System.out.println("未成功加载驱动。");
                        e1.printStackTrace();
                    } catch (SQLException e1) {
                        // TODO Auto-generated catch block
                        System.out.println("未成功修改数据库。");
                        JOptionPane.showMessageDialog(null,"请输入正确的序号（请勿重复）！");
                        e1.printStackTrace();
                    }

                }

            });
        }




}
