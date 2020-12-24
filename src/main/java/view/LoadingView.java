package view;

import javax.swing.*;
import java.awt.*;

public class LoadingView extends JPanel {
    public LoadingView() {
        setLayout(new BorderLayout());
        JTextField jTextField=new JTextField("加载中",SwingConstants.CENTER);
    }
}
