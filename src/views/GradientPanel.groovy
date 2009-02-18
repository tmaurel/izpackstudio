package views

import java.awt.Graphics2D
import java.awt.Graphics
import java.awt.GradientPaint
import java.awt.Color
import javax.swing.JComponent



class GradientContainer extends JComponent {

    def percentage

    def firstColor

    def secondColor


    GradientContainer(float percent, Color first, Color second)
    {
        super()
        percentage = percent 
        firstColor = first
        secondColor = second
    }

    public void paintComponent( Graphics g ) {
        Graphics2D g2d = (Graphics2D)g;
        g2d.setPaint( new GradientPaint( 0, (int)(getHeight() * percentage), (Color) firstColor, 0, getHeight(), (Color) secondColor) );
        g2d.fillRect( 0, 0, getWidth(), getHeight() );
    }

}