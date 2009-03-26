package com.izforge.izpack.studio.controllers.panels

import java.awt.GridLayout
import java.awt.BorderLayout
import javax.swing.BorderFactory
import java.awt.Color
import com.izforge.izpack.studio.views.panels.PacksPanelPropertiesView
import java.awt.event.ActionListener


/**
* Controller for IzPack HelloPanels
*
*/
class PacksPanelController extends PanelController
{

    /**
    * HelloPanel Constructor
    *
    * @param    m   The model used by the constructor
    * @param    v   The view used by the constructor
    * @param    p   Parent controller
    */
    PacksPanelController(m = null, v = null, p = null)
    {
        super(m, v, p)
        model.setName("com.izforge.izpack.panels.PacksPanel")
    }

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
            //panel.panelActivate()

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
    * Start method of the Controller
    *
    */
    public start(args = null)
    {
        // Build Panel
        buildPanel()
        buildPropertiesPanel(PacksPanelPropertiesView)
    }

    public toXML()
    {
             return {
                        panel(classname:"PacksPanel")
                    }
    }

}