package controllers

import com.izforge.izpack.panels.HelloPanel
import java.awt.Dimension
import java.awt.Color
import javax.swing.BorderFactory


/**
* Controller for IzPack HelloPanels
*
*/
class HelloPanelController extends PanelController {



    /**
    * HelloPanel Constructor
    *
    * @param    m   The model used by the constructor
    * @param    v   The view used by the constructor
    * @param    p   Parent controller
    */
    HelloPanelController(m = null, v = null, p = null)
    {
        super(m, v, p)
        model.load()
    }

    /**
    * Start method of the Controller
    *
    */
    def start()
    {
        // Instantiate a new IzPack HelloPanel using InstallFrame and InstallData
        panel = new HelloPanel(model.installerframe, model.installerframe.installdata)

        // Define PreferredSize for the IzPack HelloPanel
        panel.setPreferredSize(new Dimension(800,600))

        // Define Black Borders for the IzPack HelloPanel
        panel.setBorder(BorderFactory.createMatteBorder(1,1,1,1,Color.BLACK))
    }

}