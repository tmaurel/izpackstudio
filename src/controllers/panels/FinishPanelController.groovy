package controllers.panels


/**
* Controller for IzPack FinishPanels
*
*/
class FinishPanelController extends PanelController
{

    /**
    * FinishPanel Constructor
    *
    * @param    m   The model used by the constructor
    * @param    v   The view used by the constructor
    * @param    p   Parent controller
    */
    FinishPanelController(m = null, v = null, p = null)
    {
        super(m, v, p)
        model.setName("com.izforge.izpack.panels.FinishPanel")
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
        nav[3].setEnabled(false)
        nav[5].setText(parent.getInstallerFrame().langpack.getString("FinishPanel.done"))

        def useButtonIcons = parent.getInstallerFrame().installdata.guiPrefs.modifier.get("useButtonIcons");

        if (useButtonIcons == null || "yes".equalsIgnoreCase(useButtonIcons))
        {
            nav[5].setIcon(parent.getInstallerFrame().icons.getImageIcon("done"))
        }


    }

    public refresh()
    {

    }


}