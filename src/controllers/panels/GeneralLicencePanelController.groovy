package controllers.panels


import controllers.panels.PanelController


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

    /*
    protected buildPanel()
    {
        def container
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
            def panel = Class.forName(model.getName()).newInstance(parent.getInstallerFrame(), parent.getInstallerFrame().installdata)
            panel.panelActivate()

            this.removeAL(panel)


            panelsContainer.add(panel)
            container.add(parent.getInstallerFrame().createNavPanel(), BorderLayout.SOUTH)


            // Define PreferredSize for the Container
            container.setPreferredSize(parent.getSize())

            // Define Black Borders for the IzPack HelloPanel
            container.setBorder(BorderFactory.createMatteBorder(1,1,1,1,Color.BLACK))

            container.validate()

            model.setPanel(container)

        }


        return container
    }*/

    /**
    * Start method of the Controller
    *
    */
    public start()
    {
        // Build Panel
        buildPanel()
    }

    public refresh()
    {
        if(panelType == 0)
        {
            getPanel().getComponents()[0].getComponents()[0].loadLicence()
            getPanel().getComponents()[0].getComponents()[0].getComponents()[1].getComponents()[0].getComponents()[0].setText(getPanel().getComponents()[0].getComponents()[0].licence)
        }

    }


}