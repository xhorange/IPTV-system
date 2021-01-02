package view;

import controller.LoginController;
import controller.SubscribeController;
import main.Callback;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import main.Callback;


import static controller.LoginController.LOGIN_SUCCESS;
import static controller.LoginController.USER_OR_PASSWORD_NULL;

public class MenuView extends JFrame implements ActionListener,MouseListener {

    private JButton showTvshows;
    private JButton showChannels;
    private JButton subTvshows;
    private JButton subChannels;
    private JButton exit;


    private JLabel usernameLabel;
    private JLabel welcomeLabel;

    public MenuView() {

        this.setBounds(400, 200, 550, 450);

        this.setUndecorated(true);
        this.setLocationRelativeTo(null);

        welcomeLabel = new JLabel("欢迎登录网络电视频道订阅系统");
        welcomeLabel.setBounds(140, 10, 300, 35);
        welcomeLabel.setForeground(Color.white);
        welcomeLabel.setFont(new Font("微软雅黑", Font.PLAIN, 18));
        this.add(welcomeLabel);

        usernameLabel = new JLabel("当前用户：" + LoginController.getInstance().getUserModel().getUserName());
        usernameLabel.setBounds(160, 60, 200, 35);
        usernameLabel.setForeground(Color.white);
        usernameLabel.setFont(new Font("微软雅黑", Font.PLAIN, 18));
        this.add(usernameLabel);


        showTvshows = new JButton("已订阅的节目");
        showTvshows.setFont(new Font("微软雅黑",Font.PLAIN,18));
        showTvshows.setBounds(170, 120, 210, 35);
        showTvshows.setForeground(Color.white);
        showTvshows.setOpaque(false);
        showTvshows.setContentAreaFilled(false);
        this.add(showTvshows);
        showTvshows.addActionListener(this);


        showChannels = new JButton("已订阅的频道");
        showChannels.setFont(new Font("微软雅黑",Font.PLAIN,18));
        showChannels.setBounds(170, 190, 210, 35);
        showChannels.setForeground(Color.white);
        showChannels.setOpaque(false);
        showChannels.setContentAreaFilled(false);
        this.add(showChannels);
        showChannels.addActionListener(this);


        subTvshows = new JButton("订阅节目");
        subTvshows.setFont(new Font("微软雅黑",Font.PLAIN,18));
        subTvshows.setBounds(170, 260, 210, 35);
        subTvshows.setForeground(Color.white);
        subTvshows.setOpaque(false);
        subTvshows.setContentAreaFilled(false);
        this.add(subTvshows);
        subTvshows.addActionListener(this);



        subChannels = new JButton("订阅频道");
        subChannels.setFont(new Font("微软雅黑",Font.PLAIN,18));
        subChannels.setBounds(170, 330, 210, 35);
        subChannels.setForeground(Color.white);
        subChannels.setOpaque(false);
        subChannels.setContentAreaFilled(false);
        this.add(subChannels);
        subChannels.addActionListener(this);


        exit = new JButton("退 出");
        exit.setFont(new Font("微软雅黑",Font.PLAIN,18));
        exit.setBounds(170, 400, 210, 35);
        exit.setOpaque(false);
        exit.setContentAreaFilled(false);
        exit.setForeground(Color.white);
        this.add(exit);
        exit.addActionListener(this);


        this.add(new SubPanel());
        this.setVisible(true);



    }
    public void actionPerformed(ActionEvent e) {

        if(e.getSource() == showTvshows){
            SubView subView = SubscribeController.getInstance().showSubSHows();
            subView.setTitle("查看已订阅节目");
            subView.setVisible(true);
        }

        if (e.getSource() == showChannels) {
            SubView subView = SubscribeController.getInstance().showSubChannels();
            subView.setTitle("查看已订阅频道");
            subView.setVisible(true);
        }

        if (e.getSource() == subTvshows) {
            SubView subView = SubscribeController.getInstance().goToSubShows();
            subView.setTitle("订阅节目");
            subView.setVisible(true);
        }

        if (e.getSource() == subChannels) {
            SubView subView = SubscribeController.getInstance().goToSubChannels();
            subView.setTitle("查看已订阅频道");
            subView.setVisible(true);
        }

        if (e.getSource() == exit) {
            JOptionPane.showMessageDialog(null, "退出成功！");
            setVisible(false);
        }
    }

    public void mouseClicked(MouseEvent e) {

    }

    public void mousePressed(MouseEvent e) {

    }
    public void mouseReleased(MouseEvent e) {

    }
    public void mouseEntered(MouseEvent e) {


    }
    public void mouseExited(MouseEvent e) {


    }
}