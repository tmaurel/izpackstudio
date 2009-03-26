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
        super.refresh()
        if(panelType == 0)
        {
            StringBuffer fileData = new StringBuffer(1000)
            BufferedReader reader = new BufferedReader(new FileReader(model.getResource()))
            char[] buf = new char[1024]
            int numRead=0
            while((numRead=reader.read(buf)) != -1)
            {
                String readData = String.valueOf(buf, 0, numRead)
                fileData.append(readData)
                buf = new char[1024]
            }
            reader.close()
            getIzPanel().getTextArea().setText(fileData.toString())
        }

    }

    public toXML()
    {
             return {
                        panel(classname:"LicencePanel")
                    }
    }
}