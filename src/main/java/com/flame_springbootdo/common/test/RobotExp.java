package com.flame_springbootdo.common.test;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.image.BufferedImage;
import java.awt.image.RescaleOp;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author Flame
 * @Date 2018/11/20 16:45
 * @Version 1.0
 */
public class RobotExp extends JFrame {
    private static final long serialVersionUID = 1L;
    int orgx,orgy,endx,endy;
    Dimension d=Toolkit.getDefaultToolkit().getScreenSize();
    BufferedImage image;
    BufferedImage tempImage;
    BufferedImage saveImage;
    Graphics g;

    @Override
    public void paint(Graphics g) {
        //缩放因子和偏移量
        RescaleOp ro=new RescaleOp(0.8f, 0, null);
        tempImage=ro.filter(image, null);
        g.drawImage(tempImage, 0, 0,this);
    }

    public RobotExp(){
        snapshot();
        setVisible(true);
        //setSize(d);//最大化窗口
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        this.addMouseListener(new MouseAdapter(){
            public void mousePressed(MouseEvent e){
                orgx=e.getX();
                orgy=e.getY();
            }
        });
        //鼠标运动监听器
        this.addMouseMotionListener(new MouseMotionAdapter() {
            //鼠标拖拽事件
            public void mouseDragged(MouseEvent e) {
                endx=e.getX();
                endy=e.getY();
                g=getGraphics();
                g.drawImage(tempImage, 0, 0, RobotExp.this);
                int x=Math.min(orgx, endx);
                int y=Math.min(orgy,endy);
                //加上1，防止width,height为0
                int width=Math.abs(endx-orgx)+1;
                int height=Math.abs(endy-orgy)+1;
                g.setColor(Color.BLUE);
                g.drawRect(x-1, y-1, width+1, height+1);
                //减1，加1都是为了防止图片将矩形框覆盖掉
                saveImage=image.getSubimage(x, y, width, height);
                g.drawImage(saveImage, x, y,RobotExp.this);
            }
        });

        this.addKeyListener(new KeyAdapter() {
            @Override
            //按键释放
            public void keyReleased(KeyEvent e){
                //按Esc键退出
                if(e.getKeyCode()==27){
                    saveToFile();
                    System.exit(0);
                }
            }
        });
    }
    public void saveToFile(){
        SimpleDateFormat sdf=new SimpleDateFormat("yyyymmddHHmmss");
        String name=sdf.format(new Date());
        File path=FileSystemView.getFileSystemView().getHomeDirectory();
        String format="jpg";
        File f=new File(path+File.separator+name+"."+format);
        System.out.println(f);
        try {
            ImageIO.write(saveImage, format, f);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void snapshot(){

        try {
            Robot robot= new Robot();
            Dimension d=Toolkit.getDefaultToolkit().getScreenSize();
            image=robot.createScreenCapture(new Rectangle(0,0,d.width,d.height));

        } catch (AWTException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        //全屏运行
        RobotExp rd = new RobotExp();
        GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment()
                .getDefaultScreenDevice();
        gd.setFullScreenWindow(rd);
    }


   /* public static void main(String[] args) {
        try {
            Robot robot=new Robot();
            //根据指定的区域抓取屏幕的指定区域，1300是长度，800是宽度。
            BufferedImage bi=robot.createScreenCapture(new Rectangle(1300,800));
            //把抓取到的内容写到一个jpg文件中
            ImageIO.write(bi, "jpg", new File("H:\\FTP\\test\\bootstrap栅格1.png"));

        } catch (AWTException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }*/

}
