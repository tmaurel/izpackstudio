package controllers

import java.awt.geom.AffineTransform
import java.awt.image.BufferedImage
import java.awt.image.AffineTransformOp
import models.ThumbEntry
import javax.swing.ImageIcon

/**
* Controller for IzPack Panels
*
*/
abstract class PanelController extends Controller {


    /**
    * Contains the IzPack Panel object
    *
    */
    def panel

    
    /**
    * Constructor
    *
    * @param    m   The model used by the constructor
    * @param    v   The view used by the constructor
    * @param    p   Parent controller
    */
    PanelController(m = null, v = null, p = null)
    {
        super(m, v, p)
    }


    /**
    * Getter for the IzPack Panel Object
    * @return IzPack HelloPanel
    *
    */
    def getPanel()
    {
        // Set the panel as visible
        panel.setVisible(true)

        // return IzPack HelloPanel Object
        return panel
    }

    /**
    * Getter for the IzPack Panel Thumb
    * @return IzPack HelloPanel
    *
    */
    def getThumb()
    {
        def scaleX = 120/800
        def scaleY = 80/600
        def image = new BufferedImage(800, 600, BufferedImage.TYPE_INT_RGB)
        def g2 = image.createGraphics()
        def tx = new AffineTransform()


        panel.paint(g2)
        g2.dispose()
        tx.scale(scaleX, scaleY)

        def op = new AffineTransformOp(tx, AffineTransformOp.TYPE_BILINEAR)

        def biNew = new BufferedImage( (int) (image.getWidth() * scaleX), (int) (image.getHeight() * scaleY), image.getType())

        def name = panel.class.name

        return new ThumbEntry(new ImageIcon(op.filter(image, biNew)), name.substring(name.lastIndexOf ('.') + 1))
    }


}