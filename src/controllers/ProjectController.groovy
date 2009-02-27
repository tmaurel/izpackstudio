package controllers

import views.ProjectSettingsView
import controllers.panels.*
import models.panels.PanelModel


/**
* Controller for Projects
*
*/
class ProjectController extends Controller {



    /**
    * The Project Settings View
    *
    */    
    def projectSettingsView


    /**
    * Boolean telling if u'r currently in a project or not
    *
    */
    def isInProject = false

    
    /**
    * ProjectController Constructor
    *
    * @param    m   The model used by the constructor
    * @param    v   The view used by the constructor
    * @param    p   Parent controller
    */
    ProjectController(m = null, v = null, p = null)
    {
        super(m, v, p)
        projectSettingsView = view.build(ProjectSettingsView)
    }
    
    
    /**
    * Add a controller to panel's list
    *
    * @param controller The Panel Controller that need to be added
    */
    public addPanel(controller) {
        model.panels.add(controller)
        parent.printPanel(controller)
    }


    /**
    * Returns the panels list
    *
    * @return The panels list
    */
    public getPanels() {
        return model.panels
    }


    /**
    * Move a panel
    *
    * @param from The first panel
    * @param to The second panel
    */
    def movePanel(from, to)
    {
        def panels = getPanels()
        def controller = panels.get(from)
        panels.remove(from)
        panels.add(to, controller)
    }


    /**
    * Delete a panel
    *
    * @param index Index of the panel to be deleted
    */
    def deletePanel(index)
    {
        def panels = getPanels()
        panels.remove(index)
    }
    

    /**
    * Get the model's installerframe
    *
    * @return Current installer frame
    */
    public getInstallerFrame() {
        return model.getInstallerFrame()
    }


    /**
    * Get the model's size
    *
    * @return Preferred size for the panels 
    */
    public getSize() {
        return model.getSize()
    }



    /**
    * Slide to next panel
    *
    * @param controller Current Panel
    */
    def slideNext(controller)
    {
        def index = getPanels().indexOf(controller) + 1
        if(index < getPanels().size() && index >= 0)
        {
            parent.setSelectedPanel(index)
        }
    }


    /**
    * Slide to previous panel
    *
    * @param controller Current Panel
    */
    def slidePrev(controller)
    {
        def index = getPanels().indexOf(controller) - 1
        if(index < getPanels().size() && index >= 0)
        {
            parent.setSelectedPanel(index)
        }
    }

    /**
    * Toggle the project settings view
    *

    */
    def toggleProjectSettings()
    {
        projectSettingsView.setVisible(true)        
    }

    /**
    * Display the Authors view
    *
    */
    def dispAuthors()
    {
          view.addAuthors.setVisible(true)
    }

    /**
    * Start method of the Controller
    *
    */
    public start(args = null) {
        isInProject = true
    }

    /**
    * Stop method of the Controller
    *
    */
    public stop() {
        isInProject = false
    }


    /**
    * Refresh all panels
    *
    */
    public refresh()
    {
        model.panels.each(){
            it.refresh()
        }
    }

    /**
    * Create a new panel
    *
    * @param panelName Name of the panel 
    */
    public createPanel(String panelName)
    {
        def panel
        switch(panelName)
            {
                case "FinishPanel":
                    panel = new FinishPanelController(new PanelModel(), null, parent.project)
                    break
                case "InfoPanel":
                case "HTMLInfoPanel":
                case "GeneralInfoPanel":
                    panel = new GeneralInfoPanelController(new PanelModel(), null, parent.project)
                    break
                case "LicencePanel":
                case "HTMLLicencePanel":
                case "GeneralLicencePanel":
                    panel = new GeneralLicencePanelController(new PanelModel(), null, parent.project)
                    break
                 case "HelloPanel":
                    panel = new HelloPanelController(new PanelModel(), null, parent.project)
                    break
                 case "InstallPanel":
                    panel = new InstallPanelController(new PanelModel(), null, parent.project)
                    break
                 case "PacksPanel":
                    panel = new PacksPanelController(new PanelModel(), null, parent.project)
                    break
                 case "PathInputPanel":
                    panel = new PathInputPanelController(new PanelModel(), null, parent.project)
                    break
                 case "SimpleFinishPanel":
                    panel = new SimpleFinishPanelController(new PanelModel(), null, parent.project)
                    break
                 case "SummaryPanel":
                    panel = new SummaryPanelController(new PanelModel(), null, parent.project)
                    break
                 case "TargetPanel":
                    panel = new TargetPanelController(new PanelModel(), null, parent.project)
                    break
                default:
                    println "And this is not"
            }
        panel.start()
        view.build {
            doLater
            {
                addPanel(panel)
            }
        }
    }

    /**
    * Load an XML file
    *
    * @param fileName File to be loaded
    */
    def loadXML(String fileName)
    {
        StringBuffer fileData = new StringBuffer(1000)
        BufferedReader reader = new BufferedReader(new FileReader(fileName))
        char[] buf = new char[1024]
        int numRead=0
        while((numRead=reader.read(buf)) != -1)
        {
            String readData = String.valueOf(buf, 0, numRead)
            fileData.append(readData)
            buf = new char[1024]
        }
        reader.close()
        def xml = new XmlParser().parseText(fileData.toString())
        def panelsToBeInstanciated = xml.panels.panel
        panelsToBeInstanciated.each
        {panel ->
            createPanel("${panel.'@classname'}")
        }
     }

}