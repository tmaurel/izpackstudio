package controllers

import views.ProjectSettingsView


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
    public start() {

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
    

}