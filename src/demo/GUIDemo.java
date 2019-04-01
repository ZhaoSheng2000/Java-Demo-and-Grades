package demo;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GUIDemo {
    public static void main(String[] args) {
        //创建JFrame实例
        JFrame JF = new JFrame("这是一个登陆界面！！！");
        //设置窗口宽度和高度
        JF.setSize(400,300);
        //设置点击关闭按钮后停止后台运行
        JF.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        /*创建面板
         *可以指定面板在JFrame 中的指定位置
         *面板可以添加指定文本字段，或者按钮等其他组件
         */
        JPanel panel = new JPanel();
        //实例化一个面板
        JF.add(panel);
        //调用用户定义的方法并添加组件到面板
        addpanel(panel);
        //设置界面可见
        JF.setVisible(true);
    }
    private static void addpanel(JPanel panel) {
        /*
         * 下面是布局
         *设置为默认null
         */
        panel.setLayout(null);
        //创建JLable
        JLabel userLabel = new JLabel("用户名:");
        /*
         * 下面定义组件的位置
         * setBounds(x, y, ,width, height)
         * x 和 y 指定左上角的位置，由 width 和 height 指定新的大小
         */
        userLabel.setBounds(10,20,80,25);
        panel.add(userLabel);

        /*
         * 下面创建文本域用于输入
         */
        JTextField userText = new JTextField(20);
        userText.setBounds(100,20,180,25);
        panel.add(userText);
        /*
         * 下面是密码文本域
         */
        JLabel passwordLabel = new JLabel("密码：");
        passwordLabel.setBounds(10,50,80,25);
        panel.add(passwordLabel);
        /*
         *下面这个和文本域输入类似
         *但是输入的内容会以*号代替，用于确保密码的安全性
         */
        JPasswordField PasswordText = new JPasswordField(20);
        PasswordText.setBounds(100,50,180,25);
        panel.add(PasswordText);
        /*
         * 下面是结束语
         */
        JLabel finalLabel =new JLabel("批注：。。。。。。。。。");
        finalLabel.setBounds(100,100,180,200);
        panel.add(finalLabel);

        //下面创建一个登录按钮
        JButton loginButton = new JButton("登陆");
        /*监听键盘
          监听鼠标
         */
        loginButton.addMouseListener(new MouseAdapter() {
            private int MouseCount = 1;
            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println("鼠标点击登陆"+MouseCount+++"次");
            }
        });
        loginButton.addMouseListener(new MouseAdapter() {
            private int MouseEnterCount = 1;
            @Override
            public void mouseEntered(MouseEvent e) {
                System.out.println("鼠标进入登陆按钮"+MouseEnterCount+++"次");
            }
        });
        loginButton.addKeyListener(new KeyAdapter() {
            private int KeyPressedCount = 1;
            @Override
            public void keyPressed(KeyEvent e) {
                {
                    System.out.println("选中登陆时键盘按下"+KeyPressedCount+++"次");
                }
            }
        });
        loginButton.setBounds(100,80,80,25);
        panel.add(loginButton);
        /*
         * 下面创建一个注册按钮
         */
        JButton newButton = new JButton("注册");
        newButton.setBounds(200,80,80,25);
        panel.add(newButton);
    }

}
