package view;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class SubView extends JPanel {
    private JTable table;

    public SubView(String[][] info, String[] title) {
        setLayout(new BorderLayout());
        table = new JTable(info, title);
        final JRadioButton box = new JRadioButton();
        table.getColumnModel().getColumn(0).setCellRenderer(new TableCellRenderer() {
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                box.setSelected(isSelected);
                box.setHorizontalAlignment((int)0.5f);
                return box;
            }
        });
        add(table.getTableHeader(), BorderLayout.NORTH);
        add(table, BorderLayout.CENTER);
    }

}
