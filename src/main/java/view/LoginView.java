package view;

import controller.UserController;
import main.Callback;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import static controller.UserController.LOGIN_SUCCESS;
import static controller.UserController.USER_OR_PASSWORD_NULL;

public class LoginView extends JFrame {
    private JTextField usernameField = new JTextField();
    private JPasswordField passwordField = new JPasswordField();
    private JButton loginBtn = new JButton("登录");
    private JButton registerBtn = new JButton("注册");
    Callback callback;

    public LoginView(Callback callback) {
        this.callback = callback;
        // 初始化窗口和监听
        initView();
        initLister();
        setVisible(true);
    }

    private void initView() {
        setTitle("网络电视频道订阅系统登陆界面");
        setBounds(600, 200, 400, 200);
        Container container = getContentPane();
        container.setLayout(new BorderLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel fieldPanel = new JPanel();
        fieldPanel.setLayout(null);
        JLabel userLabel = new JLabel("用户名:");
        userLabel.setBounds(50, 20, 50, 20);
        JLabel passLabel = new JLabel("  密码:");
        passLabel.setBounds(50, 60, 50, 20);
        fieldPanel.add(userLabel);
        fieldPanel.add(passLabel);
        usernameField.setBounds(110, 20, 160, 20);
        passwordField.setBounds(110, 60, 160, 20);
        fieldPanel.add(usernameField);
        fieldPanel.add(passwordField);
        container.add(fieldPanel, "Center");
        /*按钮部分*/
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());
        buttonPanel.add(loginBtn);
        buttonPanel.add(registerBtn);
        container.add(buttonPanel, "South");
    }

    //按钮监听
    public void initLister() {
        /** 登录系统 */
        loginBtn.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        String username = usernameField.getText();
                        String password = passwordField.getText();
                        int loginFlag = UserController.getInstance().login(username, password);
                        if (loginFlag == USER_OR_PASSWORD_NULL) {
                            JOptionPane.showMessageDialog(null, "用户名密码不能为空");


                        } else if (loginFlag == LOGIN_SUCCESS) {
                            JOptionPane.showMessageDialog(null, "登录成功");
                            callback.onEnd();
                            setVisible(false);
                        } else {
                            JOptionPane.showMessageDialog(null, "登录失败");
                            passwordField.setText("");
                        }
                    }
                });
        /** 注册 */
        registerBtn.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        String name = usernameField.getText();
                        String password = passwordField.getText();
                        JOptionPane.showMessageDialog(null, "注册成功");
                        usernameField.setText("");
                        passwordField.setText("");
                    }
                });
    }
}

