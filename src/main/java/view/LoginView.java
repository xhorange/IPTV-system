package view;

import controller.LoginController;
import main.Callback;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;


import static controller.LoginController.LOGIN_SUCCESS;
import static controller.LoginController.USER_OR_PASSWORD_NULL;

public class LoginView extends JFrame implements ActionListener,MouseListener {
    private JTextField usernameText;
    private JPasswordField passwordText;
    private JButton loginBtn;
    private JButton registerBtn;
    private JButton quit;
    private JLabel usernameLabel;
    private JLabel passwordLabel;
    Callback callback;

    public LoginView(Callback callback) {

        this.callback = callback;
        this.setBounds(400, 200, 400, 300);

        this.setUndecorated(true);
        this.setLocationRelativeTo(null);


        usernameLabel = new JLabel("登录界面");
        usernameLabel.setBounds(160, 10, 120, 35);
        usernameLabel.setForeground(Color.white);
        usernameLabel.setFont(new Font("微软雅黑", Font.PLAIN, 18));
        this.add(usernameLabel);


        usernameLabel = new JLabel("用户名");
        usernameLabel.setBounds(80, 70, 60, 35);
        usernameLabel.setForeground(Color.white);
        usernameLabel.setFont(new Font("微软雅黑", Font.PLAIN, 18));
        this.add(usernameLabel);

        usernameText = new JTextField();
        usernameText.setBounds(160, 70, 150, 35);
        usernameText.setFont(new Font("微软雅黑",Font.PLAIN,16));
        usernameText.setOpaque(false);
        usernameText.setBorder(null);
        usernameText.addMouseListener(this);
        this.add(usernameText);

        passwordLabel = new JLabel(" 密 码");
        passwordLabel.setBounds(80, 120, 60, 35);
        passwordLabel.setForeground(Color.white);
        passwordLabel.setFont(new Font("微软雅黑", Font.PLAIN, 18));
        this.add(passwordLabel);

        passwordText = new JPasswordField();
        passwordText.setBounds(160, 120, 150, 35);
        passwordText.setOpaque(false);
        passwordText.setBorder(null);
        passwordText.addMouseListener(this);
        this.add(passwordText);


        loginBtn = new JButton("登录");
        loginBtn.setFont(new Font("微软雅黑",Font.PLAIN,18));
        loginBtn.setBounds(150, 160, 100, 35);
        loginBtn.setForeground(Color.white);
        loginBtn.setOpaque(false);
        loginBtn.setContentAreaFilled(false);
        this.add(loginBtn);
        loginBtn.addActionListener(this);


        quit = new JButton("退出");
        quit.setFont(new Font("微软雅黑",Font.PLAIN,18));
        quit.setBounds(150, 240, 100, 35);
        quit.setForeground(Color.white);
        quit.setOpaque(false);
        quit.setContentAreaFilled(false);
        this.add(quit);
        quit.addActionListener(this);


        registerBtn = new JButton("注册");
        registerBtn.setFont(new Font("微软雅黑",Font.PLAIN,18));
        registerBtn.setBounds(150, 200, 100, 35);
        registerBtn.setOpaque(false);
        registerBtn.setContentAreaFilled(false);
        registerBtn.setForeground(Color.white);
        this.add(registerBtn);
        registerBtn.addActionListener(this);
        this.add(new LoginPanel());
        this.setVisible(true);



    }
    public void actionPerformed(ActionEvent e) {

        if(e.getSource() == loginBtn){
            String username = usernameText.getText();
            String password = passwordText.getText();
            int loginFlag = LoginController.getInstance().login(username, password);
            if (loginFlag == USER_OR_PASSWORD_NULL) {
                JOptionPane.showMessageDialog(null, "用户名密码不能为空");

            } else if (loginFlag == LOGIN_SUCCESS) {
                JOptionPane.showMessageDialog(null, "登录成功");
                setVisible(false);
                callback.onEnd();

            } else {
                JOptionPane.showMessageDialog(null, "登录失败");
                passwordText.setText("");
            }
        }

        if (e.getSource() == registerBtn) {
            String name = usernameText.getText();
            String password = passwordText.getText();
            JOptionPane.showMessageDialog(null, "注册成功");
            usernameText.setText("");
            passwordText.setText("");
        }



        if (e.getSource() == quit) {
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
        if(e.getSource()==usernameText)
        {
            usernameText.setBorder(BorderFactory.createLineBorder(new Color(255,255,255)));
        }else if(e.getSource()==passwordText)
        {
            passwordText.setBorder(BorderFactory.createLineBorder(new Color(255,255,255)));
        }

    }
    public void mouseExited(MouseEvent e) {
        if(e.getSource()==usernameText)
        {
            usernameText.setBorder(null);
        }else if(e.getSource()==passwordText)
        {
            passwordText.setBorder(null);
        }

    }
}