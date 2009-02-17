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
        g2d.setPaint( new GradientPaint( 0, (int)(getHeight()/2), Color.white, 0, getHeight(), new Color(180,200,230) ) );
        g2d.fillRect( 0, 0, getWidth(), getHeight() );
    }

}