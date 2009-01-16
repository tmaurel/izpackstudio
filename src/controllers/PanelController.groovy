package controllers

import java.awt.geom.AffineTransform
import java.awt.image.BufferedImage
import java.awt.image.AffineTransformOp
import models.ThumbEntry
import javax.swing.ImageIcon
import javax.swing.BorderFactory
import java.awt.Color

/**
* Controller for IzPack Panels
*
*/
abstract class PanelController extends Controller {


    /**
    * Contains the IzPack Panel name
    *
    */
    def name


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
    * Instantiate a new IzPack HelloPanel using InstallFrame and InstallData
    *
    * @return    The IzPanel Object
    */
    def buildPanel()
    {
        // Instantiate a new IzPack HelloPanel using the parent model
        panel = Class.forName(name).newInstance(parent.model.getInstallerframe(), parent.model.getInstallerframe().installdata)

        // Define PreferredSize for the IzPanel
        panel.setPreferredSize(parent.model.getSize())

        // Define Black Borders for the IzPack HelloPanel
        panel.setBorder(BorderFactory.createMatteBorder(1,1,1,1,Color.BLACK))
        
        return panel
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
        // Define width ratio
        def scaleX = 120/parent.model.width

        // Define height ratio
        def scaleY = 80/parent.model.height

        // Create a new BufferedImage
        def image = new BufferedImage(parent.model.width, parent.model.height, BufferedImage.TYPE_INT_RGB)

        // Create a new Graphic using the buffered image
        def g2 = image.createGraphics()

        // Create a new AffineTransform
        def tx = new AffineTransform()

        // Get the panel printed view through g2
        panel.paint(g2)

        // Dispose of g2
        g2.dispose()

        // Define the scale of the Image
        tx.scale(scaleX, scaleY)

        // Transform (scale) the Image
        def op = new AffineTransformOp(tx, AffineTransformOp.TYPE_BILINEAR)

        // Create a new scaled BufferedImage
        def biNew = new BufferedImage( (int) (image.getWidth() * scaleX), (int) (image.getHeight() * scaleY), image.getType())

        // Get the class name
        def name = panel.class.name

        // Return a new ThumbEntry containing the image and the title of the thumb
        return new ThumbEntry(new ImageIcon(op.filter(image, biNew)), name.substring(name.lastIndexOf ('.') + 1))
    }


}