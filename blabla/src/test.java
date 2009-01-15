import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.*;
import javax.imageio.*;
import javax.swing.*;

public class SavingAComponent {

  public static void main(String[] args) {
    JButton button = new JButton("save");
    JPanel northPanel = new JPanel();
    northPanel.add(button);
    final JPanel panel = new JPanel();
    panel.setBackground(Color.blue);
    panel.add(new JLabel("This is a panel to save"));
    button.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        int width = panel.getWidth();
        int height = panel.getHeight();
        BufferedImage image = new BufferedImage(width, height,
                                                BufferedImage.TYPE_INT_RGB);
        Graphics2D g2 = image.createGraphics();
        panel.paint(g2);
        g2.dispose();
        try {
          ImageIO.write(image, "png", new File("mypanel.png"));
        }
        catch(IOException ioe) {
          System.out.println(ioe.getMessage());
        }
      }
    });
    JFrame f = new JFrame();
    f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    f.getContentPane().add(northPanel, "North");
    f.getContentPane().add(panel);
    f.setSize(400,300);
    f.setLocation(400,300);
    f.setVisible(true);
  }
}

