package view;

import controller.SubscribeController;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

public class SubView extends JFrame {
    private JTable table;
    private JButton okButton;

    public SubView(String[][] info, String[] title) {
        setBounds(400, 200, 600, 500);
        setLayout(new BorderLayout());
        okButton = new JButton("订阅/取消订阅");
        table = new JTable(info, title);
        final JRadioButton box = new JRadioButton();
        table.getColumnModel().getColumn(0).setCellRenderer(new TableCellRenderer() {
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                box.setSelected(isSelected);
                box.setHorizontalAlignment((int) 0.5f);
                return box;
            }
        });
        add(table.getTableHeader(), BorderLayout.NORTH);
        add(table, BorderLayout.CENTER);
        add(okButton, BorderLayout.SOUTH);
        addListener();
    }

    private void addListener() {
        okButton.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        SubscribeController.getInstance().subscribeShow(table);
                        System.out.println("***" + Arrays.toString(table.getSelectedRows()));
                        JOptionPane.showMessageDialog(null, "操作成功！", "结果", JOptionPane.CLOSED_OPTION);
                    }
                });
    }

}
