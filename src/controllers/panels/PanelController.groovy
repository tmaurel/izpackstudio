package controllers.panels

import controllers.Controller
import java.awt.BorderLayout
import java.awt.Color
import java.awt.Container
import java.awt.GridLayout
import java.awt.geom.AffineTransform
import java.awt.image.AffineTransformOp
import java.awt.image.BufferedImage
import javax.swing.BorderFactory
import javax.swing.ImageIcon
import javax.swing.JButton
import models.ThumbEntry
import views.Reflection
import groovy.swing.SwingBuilder
import java.awt.event.ActionListener
import java.awt.Dimension

/**
* Controller for IzPack Panels
*
*/
abstract class PanelController extends Controller {


    /**
    * The properties toolbar for this Panel
    *
    */
    def propertiesPanel

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

        def container = null
        
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
            def panel = Class.forName(model.getName()).newInstance(parent.model, parent.model.installdata)
            panel.panelActivate()

            this.removeAL(panel)


            panelsContainer.add(panel)

            def navPanel = parent.createNavPanel()
            def navCompo = navPanel.getComponents()
            def actionListener =
            [
                actionPerformed:
                {
                    def source = it.getSource();
                    if (source == navCompo[1])
                    {
                        parent.slidePrev(this)
                    }
                    else if (source == navCompo[3])
                    {
                        parent.slideNext(this)
                    }
                }
            ] as ActionListener

            navCompo[1].addActionListener(actionListener)
            navCompo[3].addActionListener(actionListener)
            

            container.add(navPanel, BorderLayout.SOUTH)

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
    * @param container Origin container were u want to disable all the action listeners
    *
    */
    public removeAL(container)
    {

        if(container instanceof JButton)
        {
            container.getActionListeners().each
            {
                container.removeActionListener(it)
            }
        }

        if(container instanceof Container)
        {
            container.getComponents().each
            {
                removeAL(it)
            }
        }
    }


    /**
    * Getter for the Panel container
    * @return JPanel container
    *
    */
    public getPanel()
    {
        // return IzPack HelloPanel Object
        return model.getPanel()
    }

    /**
    * Getter for the IzPanel Object
    * @return IzPanel Object
    *
    */
    public getIzPanel()
    {
        return model.getPanel().getComponents()[0].getComponents()[0]
    }



    /**
    * Setter for the IzPanel Object
    * @param panel The new IzPanel
    *
    */
    public setIzPanel(panel)
    {
        def container = model.getPanel().getComponents()[0]
        container.remove(0)
        container.add(panel)
        view.doLater
        {

            container.repaint()
        }
    }



    /**
    * Getter for the navPanel
    * @return JPanel container the navigation panel
    *
    */
    public getNavPanel()
    {
        return model.getPanel().getComponents()[1]
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
    * Getter for the IzPack Panel reflection effect
    * @return Component containing the reflection effect
    *
    */
    public getReflection()
    {

        // Create a new BufferedImage
        def image = new BufferedImage((int) parent.getSize().width, (int) parent.getSize().height, BufferedImage.TYPE_INT_RGB)

        // Create a new Graphic using the buffered image
        def g2 = image.createGraphics()

        panel.paint(g2)

        // Dispose of g2
        g2.dispose()

        return new Reflection(image)
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
     * Build the properties toolbar for this panel
     *
     * @param toolbar The Toolbar
     */
    protected buildPropertiesPanel(toolbar)
    {
        if(view instanceof SwingBuilder)
        {
            propertiesPanel = view.build(toolbar)
        }
    }


    /**
     * Start the controller
     *
     */
    public start(args = null)
    {
        showPanel()
    }
    
    /**
    * Refresh this panel
    *
    */
    public refresh()
    {
        def size = model.panel.getSize()
        parent.parent.view.build
        {
            if(size != parent.getSize())
            {
                model.panel.setPreferredSize(parent.getSize())
                model.panel.getParent().remove(1)
                panelPreview.validate()
                panelPreview.repaint()

                doLater
                {
                  model.panel.getParent().add(getReflection())
                  panelPreview.validate()
                  panelPreview.repaint()
                }

            }
            parent.projectHasChanged = true
        }


    }

}