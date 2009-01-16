package controllers


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
        name = "com.izforge.izpack.panels.HelloPanel"
    }

    /**
    * Start method of the Controller
    *
    */
    def start()
    {
        // Build Panel
        buildPanel()
        
    }

}