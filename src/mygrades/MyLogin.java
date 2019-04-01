package mygrades;

import javafx.scene.layout.BackgroundImage;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.lang.String;
import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MyLogin extends JFrame implements ActionListener {                 //登陆界面
    private JPanel jpanel = new JPanel();
    private JLabel userlabel = new JLabel("用户名");
    private JLabel passlable = new JLabel("密码");
    private JTextField usertext = new JTextField();
    private JPasswordField passtext = new JPasswordField();

    public JButton login = new JButton("登陆");
    public JButton updatepass = new JButton("修改密码");

    public MyLogin()                                                    //设置组件的属性
    {
        super.setTitle("郑州轻工业大学个人成绩管理系统");
        jpanel.setLayout(null);
        userlabel.setBounds(20,20,60,30);
        usertext.setBounds(90,20,140,30);
        passlable.setBounds(20,60,60,30);
        passtext.setBounds(90,60,140,30);
        login.setBounds(30,120,90,20);
        updatepass.setBounds(140,120,90,20);

        jpanel.add(userlabel);                                          //把各个组件添加到面板
        jpanel.add(passlable);
        jpanel.add(usertext);
        jpanel.add(passtext);
        jpanel.add(login);
        jpanel.add(updatepass);

        login.addActionListener(this);                             //添加事件监听机制（接口）
        updatepass.addActionListener(this);
        passtext.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyChar()==KeyEvent.VK_ENTER)
                    login();
            }
        });

        super.add(jpanel);                                          //面板属性
        super.setBounds(450,220,400,300);
        super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        super.setVisible(true);
    }
    public static void main(String[] args){

        new MyLogin();                                  //事件处理
    }
    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getSource()==login){
            login();                                            //登陆
        }

        else if(e.getSource()==updatepass){
            updatepass();                                       //注册
        }
    }
    public void login(){
        LoginJdbc d = new LoginJdbc();                            //访问数据库对象
        String username = usertext.getText();
        String password = String.valueOf(passtext.getPassword());
        /*尝试获取密码框中输入的密码
          对于getText();方法获取密码的方法不再适用
          将char[] 类型装换成String 还是会出现乱码

          String.valueOf( nameTextField.getPassword());
          只有这个方法能真实地返回密码文本框中输入的字符串。。。。。+（2days)
          一开始以为是编译器和数据库的编码方式不一样才会出现乱码，改了mysql的默认编码，发现还是不行
          一直输出类似 [@4432e@123 的鬼东西，后来发现在getText();方法获取密码不适用后，获取的格式是char[]类型的。
          格式转换后就正常输出了
         */

        if(d.compare(username,password)){
            JOptionPane.showMessageDialog(null,"登陆成功");
            new Grades();                                                   //显示成绩界面
            super.setVisible(false);
        }
    }
    public void updatepass(){                                           //修改密码界面
        JFrame jframe = new JFrame("修改密码");
        jframe.setBounds(500,270,300,200);
        JPanel updatepass = new JPanel();
        JLabel namelable = new JLabel("用户名");
        JLabel passlable = new JLabel("旧密码");
        JLabel newpasslable = new JLabel("新密码");
        JTextField nametext = new JTextField(""+usertext.getText());
        JPasswordField passtext = new JPasswordField();
        JPasswordField newpasstext = new JPasswordField();
        JButton xiugai = new JButton("修改");
        JButton resert = new JButton("重新输入");

        updatepass.setLayout(null);


        namelable.setBounds(5,5,70,20);
        nametext.setBounds(80,5,120,20);
        passlable.setBounds(5,30,70,20);
        passtext.setBounds(80,30,120,20);
        newpasslable.setBounds(5,60,70,20);
        newpasstext.setBounds(80,60,120,20);
        xiugai.setBounds(10,110,60,20);
        resert.setBounds(90,110,60,20);

        updatepass.add(namelable);
        updatepass.add(passlable);
        updatepass.add(newpasslable);
        updatepass.add(nametext);
        updatepass.add(passtext);
        updatepass.add(newpasstext);
        updatepass.add(xiugai);
        updatepass.add(resert);
        jframe.add(updatepass);
        jframe.setVisible(true);

        xiugai.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LoginJdbc d= new LoginJdbc();
                String username = nametext.getText();
                String password= String.valueOf(passtext.getPassword());
                String newpassword= String.valueOf(newpasstext.getPassword());
                if(d.compare(username,password)) {
                    System.out.println("修改密码验证成功！！！");
                    d.update(username,newpassword);
                }
                else {
                    JOptionPane.showMessageDialog(null, "修改失败");
                        jframe.setVisible(false);
                    }
                }

        });
        resert.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                nametext.setText("");
                passtext.setText("");
                newpasstext.setText("");
            }
        });


    }
}
