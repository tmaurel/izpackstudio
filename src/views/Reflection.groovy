package views

import java.awt.Graphics
import javax.swing.JComponent
import java.awt.image.BufferedImage
import java.awt.Graphics2D
import java.awt.GradientPaint
import java.awt.Color
import java.awt.AlphaComposite
import java.awt.Dimension


class Reflection extends JComponent {

    def image

    Reflection(img)
    {
        image = img
    }

    public void paintComponent( Graphics g ) {

        Graphics2D g2d = (Graphics2D)g;
        int imageWidth = image.getWidth();
        int imageHeight = image.getHeight();
        float opacity = 0.4f;
        float fadeHeight = 0.7f;


        BufferedImage reflection = new BufferedImage( imageWidth, imageHeight, BufferedImage.TYPE_INT_ARGB );
		Graphics2D rg = reflection.createGraphics();
        rg.drawRenderedImage( image, null );

        g2d.translate( 0, imageHeight);
        g2d.scale( 1, -1 );

        rg.setComposite( AlphaComposite.getInstance( AlphaComposite.DST_IN ) );
        rg.setPaint(
            new GradientPaint(
                (float)0, (float)(imageHeight*fadeHeight), new Color( 0.0f, 0.0f, 0.0f, 0.0f ),
                (float)0, (float)imageHeight, new Color( 0.0f, 0.0f, 0.0f, opacity )
            )
        );
        rg.fillRect( 0, 0, imageWidth, imageHeight );
        rg.dispose();
        g2d.drawRenderedImage( reflection, null );

    }

    public Dimension getPreferredSize() {
        return new Dimension((int)image.getWidth(),(int)(image.getHeight()/3));
    }


}