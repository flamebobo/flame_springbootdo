package com.flame_springbootdo.common.test;

import javax.swing.*;
import java.awt.*;

/**
 * @Author Flame
 * @Date 2018/10/23 16:00
 * @Version 1.0
 */
public class J_Frame extends JFrame {
    J_Frame()
    {
        super("太阳星系图");
        draw_star d=new draw_star();
        Container c=getContentPane();
        c.setBackground(Color.BLACK);
        c.add(d);
        this.setBackground(Color.black);
    }
    public static void main(String args[])
    {

        J_Frame j=new J_Frame();
        j.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        j.setSize(1920, 1080);
        j.setVisible(true);
    }
}
