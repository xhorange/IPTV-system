package view;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class SubPanel  extends JPanel {
    private Image image;
    public SubPanel()
    {
        try {
            image= ImageIO.read(new File("images/b.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.drawImage(image,0,0,600,600,null);
    }
}