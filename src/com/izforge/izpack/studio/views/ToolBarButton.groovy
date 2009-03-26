package com.izforge.izpack.studio.views

import javax.swing.JButton
import java.awt.Dimension
import java.awt.Graphics
import java.awt.Image
import java.awt.image.BufferedImage
import java.awt.Graphics2D
import java.awt.font.TextLayout
import java.awt.geom.Rectangle2D
import java.awt.font.FontRenderContext
import java.awt.Color
import java.awt.RenderingHints
import java.awt.image.ConvolveOp
import java.awt.image.BufferedImageOp
import java.awt.image.Kernel
import org.jdesktop.animation.timing.triggers.MouseTrigger
import org.jdesktop.animation.timing.interpolation.PropertySetter
import org.jdesktop.animation.timing.triggers.MouseTriggerEvent
import java.awt.geom.QuadCurve2D
import java.awt.geom.Line2D


class ToolBarButton extends JButton
{

    def title

    def convolvedImage

    def originalImage

    def alt = 0.0f

    def defaultDec = 5.0f

    ToolBarButton(msg, icon)
    {
        super()
        addTriggers()
        title = msg
        Image image = icon.getImage();
        originalImage = new BufferedImage((int) icon.getIconWidth(), (int) icon.getIconHeight(),
            BufferedImage.TYPE_INT_ARGB);
        convolvedImage = new BufferedImage((int) icon.getIconWidth(), (int) icon.getIconHeight(),
            BufferedImage.TYPE_INT_ARGB);
        Graphics g = originalImage.createGraphics();
        g.drawImage(image, 0, 0, this);
        g.dispose();
        setBorderPainted(false)
        setBrightness(0.9f)
        setOpaque(false)
        setPreferredSize(new Dimension(90,80))
        setFocusPainted(false)       
    }

    public void setEnabled(boolean b)
    {
        super.setEnabled(b)
        if(b)
        {
            addTriggers();
            setBrightness(0.9f)
        }
        else
        {
            removeTriggers();
            setBrightness(0.1f)
        }
    }

    public void paintComponent(Graphics g)
    {

        if (convolvedImage != null) {

            int width = getWidth();
            int height = getHeight();

            synchronized (convolvedImage) {
                Graphics2D g2 = (Graphics2D) g;
                def plus = 0
                g2.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS, RenderingHints.VALUE_FRACTIONALMETRICS_ON);

                g2.setColor(Color.lightGray)
                
                if(model.isEnabled())
                {
                    if(model.isArmed())
                    {
                        plus = -alt
                        def rightShape = new QuadCurve2D.Float ((float)(width - defaultDec), 0.0f, (float)(width - defaultDec - alt), (float)(height/2),
                                    (float)(width - defaultDec), (float)height)

                        g2.setColor(new Color(200,200,220))
                        g2.draw(new Line2D.Float(0.0f, 0.0f, 0.0f, (float)height))
                        g2.draw(new Line2D.Float (0.0f, 0.0f, (float)(width - defaultDec), 0.0f))

                        g2.draw(rightShape)
                        g2.draw(new Line2D.Float (0.0f, (float)(height-1), (float)(width - defaultDec), (float)(height-1)))
                        g2.setColor(new Color(185,185,185))
                        g2.fill(rightShape)
                        g2.setColor(Color.blue)

                    }
                    else if(model.isRollover() || alt != 0.0f)
                    {
                        plus = alt
                        def leftShape = new QuadCurve2D.Float (0.0f, 0.0f, alt, (float)(height/2),
                                    0.0f, (float)height)
                        g2.setColor(new Color(110,120,160))
                        g2.draw(leftShape)
                        g2.draw(new Line2D.Float (0.0f, 0.0f, (float)(width - defaultDec), 0.0f))

                        g2.setColor(new Color(185,185,185))
                        g2.fill(leftShape)


                        g2.setColor(new Color(200,200,220))
                        g2.draw(new QuadCurve2D.Float ((float)(width - defaultDec), 0.0f, (float)(width - defaultDec + alt), (float)(height/2),
                                    (float)(width - defaultDec), (float)height))
                        g2.draw(new Line2D.Float (0.0f, (float)(height-1), (float)(width - defaultDec), (float)(height-1)))

                        g2.setColor(Color.blue)
                    }
                    else
                    {
                        g2.setColor(Color.black)   
                    }
                }


                FontRenderContext context = g2.getFontRenderContext();
                TextLayout layout = new TextLayout((String) title, getFont().deriveFont((float)(10.0f + alt/20)), context);
                Rectangle2D bounds = layout.getBounds();

                int x = (plus/2) + (width - convolvedImage.getWidth(null)) / 2;
                int y = (int) (height - (convolvedImage.getHeight(null) + bounds.getHeight() + layout.getAscent())) / 2;

                g2.drawImage(convolvedImage, x, y, this);                 
                layout.draw(g2, (float) ((width - bounds.getWidth()) / 2),
                    (float) (y + convolvedImage.getHeight(null) + bounds.getHeight() + layout.getAscent()));
            }
        }
    }


    def setBrightness(float multiple) {
        float[] brightKernel = [ multiple ];
        RenderingHints hints = new RenderingHints(RenderingHints.KEY_RENDERING,
            RenderingHints.VALUE_RENDER_QUALITY);
        BufferedImageOp bright = new ConvolveOp(new Kernel(1, 1, brightKernel),
            ConvolveOp.EDGE_NO_OP, hints);
        bright.filter(originalImage, convolvedImage);
        repaint();
    }

    def setAltitude(float altitude)
    {
        alt = altitude
        repaint()
    }


    def addTriggers()
    {
        def animatorBrightness = PropertySetter.createAnimator(250, this,
                "brightness", 0.9f, 1.2f);
        MouseTrigger.addTrigger(this, animatorBrightness, MouseTriggerEvent.ENTER, true)

        def animatorAltitude = PropertySetter.createAnimator(100, this,
                "altitude", 0.0f, 5.0f);
        MouseTrigger.addTrigger(this, animatorAltitude, MouseTriggerEvent.ENTER, true)
    }

    def removeTriggers()
    {
        getMouseListeners().each() {
            if(it instanceof MouseTrigger)
            {
                removeMouseListener(it)
            }
        }
    }

}