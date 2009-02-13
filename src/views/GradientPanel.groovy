package views

import javax.swing.JPanel
import java.awt.Graphics2D
import java.awt.Graphics
import java.awt.GradientPaint
import java.awt.Color



class GradientPanel extends JPanel {

    GradientPanel()
    {
        super()
    }

    public void paintComponent( Graphics g ) {
        Graphics2D g2d = (Graphics2D)g;
        g2d.setPaint( new GradientPaint( 0, 0, new Color(238,238,238), 0, getHeight(), Color.white ) );
        g2d.fillRect( 0, 0, getWidth(), getHeight() );
    }

}