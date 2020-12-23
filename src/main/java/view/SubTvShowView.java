package view;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class SubTvShowView extends JScrollPane {
    private JTable table;

    public SubTvShowView(String[][] info, String[] title) {
        table = new JTable(info, title);
        this.add(table);
    }
}
