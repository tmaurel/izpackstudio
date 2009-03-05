package controllers.panels


/**
* Controller for IzPack FinishPanels
*
*/
class SimpleFinishPanelController extends PanelController
{

    /**
    * FinishPanel Constructor
    *
    * @param    m   The model used by the constructor
    * @param    v   The view used by the constructor
    * @param    p   Parent controller
    */
    SimpleFinishPanelController(m = null, v = null, p = null)
    {
        super(m, v, p)
        model.setName("com.izforge.izpack.panels.SimpleFinishPanel")
    }

    /**
    * Start method of the Controller
    *
    */
    public start(args = null)
    {
        // Build Panel
        buildPanel()

        def nav = getPanel().getComponents()[1].getComponents()
        nav[1].setEnabled(false)
        nav[3].setEnabled(false)
        nav[5].setText(parent.model.langpack.getString("FinishPanel.done"))

        def useButtonIcons = parent.model.installdata.guiPrefs.modifier.get("useButtonIcons");

        if (useButtonIcons == null || "yes".equalsIgnoreCase(useButtonIcons))
        {
            nav[5].setIcon(parent.model.icons.getImageIcon("done"))
        }


    }

   public refresh()
    {

    }

    public toXML()
    {
             return {
                        panel(classname:"SimpleFinishPanel")
                    }
    }

}