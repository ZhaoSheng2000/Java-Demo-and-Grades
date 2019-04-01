package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Awt {
    public static void main(String[] args){
        Frame f = new Frame("myframe");
        f.setSize(500,400);
        f.setLocation(200,300);
        f.setLayout(new FlowLayout());
        Button b = new Button("点击");
        f.add(b);
        f.setVisible(true);
        b.addMouseListener(new MouseAdapter() {
            private int count = 1;
            @Override
            public void mouseClicked(MouseEvent e) {
                {
                    System.out.println("鼠标点击"+count++);
                }
            }
        });
        b.addMouseListener(new MouseAdapter() {
            private int MouseCount=0;
            @Override
            public void mouseEntered(MouseEvent e) {
                {
                    System.out.println("鼠标进入"+MouseCount++);
                }
            }
        });

    }
}
