package GUI;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class JFrameDemo {
    public static void main(String[] args){
        JFrame JF = new JFrame("My Frame");
        JF.setBounds(400,300,400,300);
        JF.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel panel = new JPanel();
        JF.add(panel);
        JF.setUndecorated(true);
        addpanel(panel);
        JF.setVisible(true);
    }
    private static void addpanel(JPanel panel){
        panel.setLayout(null);

        JLabel userLable = new JLabel("username:");
        userLable.setBounds(10,20,80,25);
        panel.add(userLable);

        JTextField userText = new JTextField(20);
        userText.setBounds(100,20,200,25);
        
        panel.add(userText);

        JLabel passwordLable = new JLabel("password:");
        passwordLable.setBounds(10,50,80,25);
        panel.add(passwordLable);

        JPasswordField passwordText = new JPasswordField(20);
        passwordText.setBounds(100,50,200,25);
        panel.add(passwordText);

        JButton loginButton = new JButton("login");
        loginButton.setBounds(100,80,100,25);
        panel.add(loginButton);

        loginButton.addMouseListener(new MouseAdapter() {
            private int mouseClickedCount = 1;
            @Override
            public void mouseClicked(MouseEvent e) {
                {
                  System.out.println("login"+mouseClickedCount++);
                }
            }
        });

        JButton newButton = new JButton("registered");
        newButton.setBounds(200,80,100,25);
        panel.add(newButton);


    }
}


