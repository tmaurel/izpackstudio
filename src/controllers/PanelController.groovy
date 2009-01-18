package controllers

import java.awt.geom.AffineTransform
import java.awt.image.BufferedImage
import java.awt.image.AffineTransformOp
import models.ThumbEntry
import javax.swing.ImageIcon
import javax.swing.BorderFactory
import java.awt.Color
import groovy.swing.SwingBuilder
import java.awt.GridLayout
import java.awt.BorderLayout

/**
* Controller for IzPack Panels
*
*/
abstract class PanelController extends Controller {

   
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
    protected buildPanel()
    {
        def container
        view.build {

            container = panel(
                layout: new BorderLayout()
            )

            panelsContainer = panel(
                border: BorderFactory.createEmptyBorder(10, 10, 0, 10),
                layout: new GridLayout(1, 1)
            )

            container.add(panelsContainer, BorderLayout.CENTER);

            // Instantiate a new IzPack HelloPanel using the parent model
            def panel = Class.forName(model.getName()).newInstance(parent.getInstallerFrame(), parent.getInstallerFrame().installdata)
            panel.panelActivate()

            this.removeAL(panel)


            panelsContainer.add(panel)
            container.add(parent.getInstallerFrame().createNavPanel(), BorderLayout.SOUTH)


            // Define PreferredSize for the Container
            container.setPreferredSize(parent.getSize())

            // Define Black Borders for the IzPack HelloPanel
            container.setBorder(BorderFactory.createMatteBorder(1,1,1,1,Color.BLACK))

            container.validate()

            model.setPanel(container)
            
        }

        
        return container
    }


    /**
    * Recursive remover of ActionListeners
    * @return IzPack HelloPanel
    *
    */
    public removeAL(container)
    {

        try
        {

            try
            {
                def al = container.getActionListeners()
                al.each
                {
                    container.removeActionListener(it)                  
                }
            }
            catch(MissingMethodException Exception)
            {
            }
            
            container.getComponents().each
            {
                removeAL(it)
            }
            
        }
        catch(MissingMethodException Exception)
        {
        }

    }


    /**
    * Getter for the IzPack Panel Object
    * @return IzPack HelloPanel
    *
    */
    public getPanel()
    {
        // return IzPack HelloPanel Object
        return model.getPanel()
    }

    /**
    * Getter for the IzPack Panel Thumb
    * @return IzPack HelloPanel
    *
    */
    public getThumb()
    {

        // Define width ratio
        def scaleX = 133/parent.getSize().width

        // Define height ratio
        def scaleY = 88/parent.getSize().height

        // Create a new BufferedImage
        def image = new BufferedImage((int) parent.getSize().width, (int) parent.getSize().height, BufferedImage.TYPE_INT_RGB)


        // Create a new Graphic using the buffered image
        def g2 = image.createGraphics()

        // Create a new AffineTransform
        def tx = new AffineTransform()

        panel.paint(g2)

        // Dispose of g2
        g2.dispose()

        // Define the scale of the Image
        tx.scale(scaleX, scaleY)

        // Transform (scale) the Image
        def op = new AffineTransformOp(tx, AffineTransformOp.TYPE_BILINEAR)

        // Create a new scaled BufferedImage
        def biNew = new BufferedImage( (int) (image.getWidth() * scaleX), (int) (image.getHeight() * scaleY), image.getType())

        // Return a new ThumbEntry containing the image and the title of the thumb
        return new ThumbEntry(new ImageIcon(op.filter(image, biNew)), model.getName().substring(model.getName().lastIndexOf ('.') + 1))
 
    }

    /**
     * Show the Panel
     *
     */
    public showPanel()
    {
        model.getPanel().setVisible(true)
        model.getPanel().setOpaque(true)
    }

    /**
     * Hide the Panel
     *
     */
    public hidePanel()
    {
        model.getPanel().setVisible(false)
    }

    /**
     * Start the controller
     *
     */
    public start()
    {
        showPanel()
    }


}