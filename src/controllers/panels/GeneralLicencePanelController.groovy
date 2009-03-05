package controllers.panels

import views.panels.GeneralLicencePanelPropertiesView

/**
* Controller for IzPack HelloPanels
*
*/
class GeneralLicencePanelController extends PanelController
{


    def panelType

    /**
    * HelloPanel Constructor
    *
    * @param    m   The model used by the constructor
    * @param    v   The view used by the constructor
    * @param    p   Parent controller
    */
    GeneralLicencePanelController(m = null, v = null, p = null, choice = 0)
    {
        super(m, v, p)
        panelType = choice
        if(choice == 0)
            model.setName("com.izforge.izpack.panels.LicencePanel")
        else if(choice == 1)
            model.setName("com.izforge.izpack.panels.HTMLLicencePanel")
    }

    /**
    * Start method of the Controller
    *
    */
    public start(args = null)
    {
        // Build Panel
        buildPanel()
        buildPropertiesPanel(GeneralLicencePanelPropertiesView)
    }

    /**
    * Refresh this panel
    *
    */
    public refresh()
    {
        if(panelType == 0)
        {
            getIzPanel().loadLicence()
            getIzPanel().getTextArea().setText(getIzPanel().licence)
        }

    }

    public toXML()
    {
             return {
                        panel(classname:"LicencePanel")
                    }
    }
}