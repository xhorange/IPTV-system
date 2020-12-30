package view;

import controller.LoginController;
import controller.SubscribeController;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SubView extends JFrame {
    private JTable table;
    private JButton okBtn;
    private JButton upBtn;
    private JButton downBtn;
    private JPanel mainJpanel;
    private JRadioButton box;

    //查询订阅的节目，频道；订阅频道
    public SubView(String[][] info, String[] title) {
        setBounds(400, 200, 600, 500);
        JPanel mainJpanel = new JPanel(new BorderLayout());
        okBtn = new JButton("订阅/取消订阅");
        table = new JTable(info, title);
        final JRadioButton box = new JRadioButton();
        table.getColumnModel().getColumn(0).setCellRenderer(new TableCellRenderer() {
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                box.setSelected(isSelected);
                box.setHorizontalAlignment((int) 0.5f);
                return box;
            }
        });
        JPanel btnPanel = new JPanel(new FlowLayout());
        btnPanel.add(okBtn);
        mainJpanel.add(table.getTableHeader(), BorderLayout.NORTH);
        mainJpanel.add(table, BorderLayout.CENTER);
        mainJpanel.add(btnPanel, BorderLayout.SOUTH);
        add(mainJpanel);
        addListener();
    }

    //节目订阅频道
    public SubView(String[][] info, String[] title, String channel) {
        setBounds(400, 200, 600, 500);
        mainJpanel = new JPanel(new BorderLayout());
        JLabel channelLabel = new JLabel("当前频道" + channel);
        okBtn = new JButton("订阅/取消订阅");
        upBtn = new JButton("上一频道");
        downBtn = new JButton("下一频道");
        table = new JTable(info, title);
        box = new JRadioButton();
        table.getColumnModel().getColumn(0).setCellRenderer(new TableCellRenderer() {
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                box.setSelected(isSelected);
                box.setHorizontalAlignment((int) 0.5f);
                return box;
            }
        });
        JPanel btnPanel = new JPanel(new FlowLayout());
        btnPanel.add(upBtn);
        btnPanel.add(okBtn);
        btnPanel.add(downBtn);
        mainJpanel.add(channelLabel, BorderLayout.NORTH);
        mainJpanel.add(table.getTableHeader(), BorderLayout.NORTH);
        mainJpanel.add(table, BorderLayout.CENTER);
        add(btnPanel, BorderLayout.SOUTH);
        add(mainJpanel);
        addListener();
    }

    public void updateTable(String[][] info, String[] title) {
        mainJpanel.remove(table);
        table = null;
        table = new JTable(info, title);
        box = new JRadioButton();
        table.getColumnModel().getColumn(0).setCellRenderer(new TableCellRenderer() {
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                box.setSelected(isSelected);
                box.setHorizontalAlignment((int) 0.5f);
                return box;
            }
        });
        mainJpanel.add(table.getTableHeader(), BorderLayout.NORTH);
        mainJpanel.add(table, BorderLayout.CENTER);
        mainJpanel.revalidate();
    }

    private void addListener() {
        okBtn.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        SubscribeController.getInstance().subscribeShow(table);
                        // TODO: 2020/12/29 订阅失败的结果
                        JOptionPane.showMessageDialog(null, "订阅成功！", "订阅结果", JOptionPane.CLOSED_OPTION);
                    }
                });
        if (upBtn == null || downBtn == null) {
            return;
        }
        upBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                SubscribeController.getInstance().upDate(false);
            }
        });
        downBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                SubscribeController.getInstance().upDate(true);
            }
        });
    }

}
