package controllers.panels


/**
* Controller for IzPack HelloPanels
*
*/
class TargetPanelController extends PanelController
{

    /**
    * HelloPanel Constructor
    *
    * @param    m   The model used by the constructor
    * @param    v   The view used by the constructor
    * @param    p   Parent controller
    */
    TargetPanelController(m = null, v = null, p = null)
    {
        super(m, v, p)
        model.setName("com.izforge.izpack.panels.TargetPanel")
    }

    /**
    * Start method of the Controller
    *
    */
    public start(args = null)
    {
        // Build Panel
        buildPanel()
    }

    public toXML()
    {
             return {
                        panel(classname:"TargetPanel")
                    }
    }

}