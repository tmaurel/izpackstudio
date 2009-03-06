package controllers.panels

import com.izforge.izpack.panels.HelloPanel


/**
* Controller for IzPack HelloPanels
*
*/
class HelloPanelController extends PanelController
{    

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
        model.setName("com.izforge.izpack.panels.HelloPanel")
    }

    /**
    * Start method of the Controller
    *
    */
    public start(args = null)
    {
        // Build Panel
        buildPanel()
        def nav = getNavPanel().getComponents()
        nav[1].setEnabled(false)
    }

    public refresh()
    {
        
    }

    public toXML()
    {
             return {
                        panel(classname:"HelloPanel")
                    }
    }

}