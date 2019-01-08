package com.flame_springbootdo.common.test;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;

/**
 * @Author Flame
 * @Date 2018/10/23 15:59
 * @Version 1.0
 */
public class thread_star extends Thread {
    int x0;
    int y0;
    int r0;
    int d0;
    double angle;

    thread_star(int x, int y, int r, double a) {
        x0 = x;
        y0 = y;
        r0 = r;
        d0 = x0 - 960;
        angle = a;
    }

    public void run() {
        double an = angle / 3;
        while (true) {
            x0 = (int) (960 + d0 * Math.cos(angle));
            y0 = (int) (540 + d0 * Math.sin(angle));

            angle = angle + an / 10;
            try {
                sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}


class draw_star extends JPanel {
    thread_star s[] = {new thread_star(1300, 540, 30, (Math.PI / 20)), new thread_star(1700, 540, 40, (Math.PI / 40))
            , new thread_star(1400, 540, 25, (Math.PI / 30)), new thread_star(1620, 540, 30, (Math.PI / 36)),
            new thread_star(1180, 540, 18, (Math.PI / 10))
    };

    draw_star() {
        for (int i = 0; i < s.length; i++) s[i].start();
        this.setBackground(Color.black);
    }

    public void paint(Graphics g) {
        g.setColor(Color.black);
        g.fillRect(0, 0, getWidth(), getHeight());
        super.paint(g);
    }

    public void paintComponent(Graphics g0) {
        this.setBackground(Color.black);
        Graphics2D g = (Graphics2D) (g0);
        g.setColor(Color.yellow);
        g.fill(new Ellipse2D.Double(960 - 80, 540 - 80, 160, 160));
        Color c[] = {Color.blue, Color.GRAY, Color.orange, Color.RED, Color.red};
        for (int i = 0; i < s.length; i++) {
            g = (Graphics2D) (g0);
            g.setColor(c[i]);
            g.fill(new Ellipse2D.Double(s[i].x0 - s[i].r0, s[i].y0 - s[i].r0, 2 * s[i].r0, 2 * s[i].r0));
        }
        repaint();
    }

}


