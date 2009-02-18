package controllers.panels

import views.panels.GeneralInfoPanelPropertiesView




/**
* Controller for IzPack HelloPanels
*
*/
class GeneralInfoPanelController extends PanelController
{


    def panelType

    /**
    * HelloPanel Constructor
    *
    * @param    m   The model used by the constructor
    * @param    v   The view used by the constructor
    * @param    p   Parent controller
    */
    GeneralInfoPanelController(m = null, v = null, p = null, choice = 0)
    {
        super(m, v, p)
        panelType = choice
        if(choice == 0)
            model.setName("com.izforge.izpack.panels.InfoPanel")
        else if(choice == 1)
            model.setName("com.izforge.izpack.panels.HTMLInfoPanel")
    }

 
    /**
    * Start method of the Controller
    *
    */
    public start()
    {
        // Build Panel
        buildPanel()
        buildPropertiesPanel(GeneralInfoPanelPropertiesView)
    }

    /**
    * Refresh this panel
    *
    */   
    public refresh()
    {
        if(panelType == 0)
        {
            getIzPanel().loadInfo()
            getIzPanel().getTextArea().setText(getIzPanel().info)
        }
    }


}