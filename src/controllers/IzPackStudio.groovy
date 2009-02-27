package controllers

import models.*
import views.*
import actions.*
import java.awt.Dimension
import javax.swing.JComponent
import java.awt.event.ActionEvent



/**
 * IzPackStudio main class
 *
 */
class IzPackStudio extends Controller
{

   /**
    * The current project controller
    *
    */
    private project

   /**
    * The JFrame containing whole GUI
    *
    */
    private GUI

   /**
    * Thumbs list
    *
    */
    private listModel

   /**
    * Animation used to slide between panels
    *
    */
    private animation

   /**
    * Is GUI Loaded ?
    *
    */
    private loaded

    /**
     * GUI Perspective
     *
     */
    def perspective

    /**
     * Project Actions
     *
     */
    def projectActions



   /**
    * IzPackStudio Constructor
    *
    * @param    m   The model used by the constructor
    * @param    v   The view used by the constructor
    * @param    p   Parent controller
    */
    IzPackStudio(m = null, v = null, p = null)
    {
        super(m, v, p)
        loaded = false
    }

    /**
    * Start IzPack Studio
    *
    */
    public start(args = null)
    {

        // Display the Splash Screen
        displaySplash()

        setProgressBar(1, "Loading GUI...")

        /**
         * Building Actions
         *
         */
        projectActions = view.build(ProjectActions)
        perspective = new Perspective(view)
        view.build(PerspectiveActions)

        GUI = view.build(IzPackStudioView)

        // Assign the our own List Model to the ThumbList
        listModel = view.thumbList.getModel()

        // Create the new empty project
        // TO-DO : Args to load projects (no empty project)
        project = new ProjectController(new ProjectModel(), null, this)

        //setActionsEnabled(false)

        sleep(1000)
        setProgressBar(10, "Loaded !")        

        // Display the GUI
        GUI.show()

        // Create a new Animation Controller to slide between panels
        animation = new AnimationController(null, view.panelScrollPane, project)
        loaded = true

        //project.loadXML("install.xml")

    }


    /**
    * Start Splash Screen
    *
    */
    private displaySplash()
    {

        def splash = view.build(SplashView)

        // Display SplashView
        splash.show()
    }


    /**
    * Start Splash Screen
    *
    * @param e  Event thrown by GUI
    */
    private toggleProjectSettings(e)
    {
        project.toggleProjectSettings()
    }


    /**
    * Changed ProgressBar value
    *
    * @param    num     Value of the ProgressBar
    * @param    text    Text associated with the ProgressBar value
    */
    private setProgressBar(num, text = null) throws Exception
    {

        view.build
        {
            // Set the value of the progress bar
            view.splashProgress.setValue(num)

            // If the text isnt null, set it
            if(text != null)
            {
                view.loadingText.setText(text)
            }

            // If the current value is the maximum value, then close the splash
            if(num == view.splashProgress.getMaximum())
            {

                view.splashWindow.with
                {
                    setVisible(false)
                    dispose()
                }

            }

        }

    }

    /**
    * Set the current selected panel
    *
    * @param index  Index of the panel in the list
    */
    def setSelectedPanel(index)
    {
        if(index < listModel.size())
        {
            view.thumbList.setSelectedIndex(index)
        }
    }


    /**
    * Slide the scroll pane to the right index
    *
    * @param index  Index of the panel in the list
    */
    def slideTo(index)
    {
        if(index < listModel.size())
        {

            def pos = view.panelPreview.getComponents()[index].getX()

            animation.slideViewPositionTo(pos)

            view.propertiesPanel.removeAll()

            def prop = project.getPanels()[index].propertiesPanel

            if(prop instanceof JComponent)
            {
                view.propertiesPanel.add(prop)
            }

            GUI.validate()
            GUI.repaint()
        }
    }


    /**
    * Slide the scroll pane to the right index
    * using the given X position
    *
    * @param position  MouseListener click position in viewport
    */
    def slideOnPosition(position)
    {
        def vpYnitSize = animation.size
        def size = project.getSize()
        def index = (int) Math.floor(position / (vpYnitSize + size.width + 1))
        if(index < listModel.size())
        {
            if(view.thumbList.getSelectedIndex() == index)
            {
                slideTo(index)
            }
            else
            {
                view.thumbList.setSelectedIndex(index)
            }
        }
    }

    /**
    * Break the current animation if any
    *
    */
    def breakAnimation()
    {
        animation.breakAnimation()
    }

    /**
    * Get the preview size, including the reflection
    *
    * @return Dimension of the preview panel
    */
    def getPreviewSize()
    {
        def size = project.getSize()
        return new Dimension((int) size.width, (int) (size.height * 4/3))
    }


    /**
    * Switch two Thumbs
    *
    * @param from The first thumb
    * @param to The second thumb
    */
    def switchThumbs(from, to)
    {
        def size = listModel.size()
        if(from < size && to < size)
        {
            Object obj = (Object) listModel.remove(from);
            listModel.add(to,obj);
        }
    }


    /**
    * Move a panel 
    *
    * @param from The first panel
    * @param to The second panel
    */
    def movePanel(from, to)
    {
        view.build
        {
            project.movePanel(from, to)
            doLater
            {
                def obj = view.panelPreview.getComponent(from);
                view.panelPreview.remove(from)
                addPreview(obj, to)
            }
        }
    }


    /**
    * Delete a panel (through ActionEvent)
    *
    * @param ae ActionEvent thrown by view 
    */
    def deletePanel(ActionEvent ae)
    {
        def panel = view.thumbList.getSelectedIndex()
        deletePanel(panel)
    }


    /**
    * Delete a panel
    *
    * @param panel Panel number
    */
    def deletePanel(int panel)
    {
        
        view.build
        {
            doLater
            {
                breakAnimation()

                listModel.remove(panel)
                view.panelPreview.remove(panel)

                GUI.validate()

                def prev = panel - 1
                if(prev >= 0 && prev < listModel.size())
                {
                    view.thumbList.setSelectedIndex(prev)
                }
                project.deletePanel(panel)

            }
        }

    }

    /**
    * Display Panel preview and thumb
    *
    * @param controller Controller of the panel u want to print
    */
    def printPanel(controller)
    {
        // Make sur u print the Panel before printing the Thumb
        view.build
        {
            // Print the Panel
            printPreview(controller)

            doLater
            {
                // Print the Thumb
                printThumb(controller)
                view.thumbList.setSelectedIndex(project.model.panels.size() - 1)
            }

            GUI.validate()
        }
    }


    /**
    * Display Panel preview
    *
    * @param controller Controller of the panel u want to print
    */
    def printPreview(controller)
    {

        def index = view.panelPreview.getComponents().size()

        view.build
        {
            // Make sure it is visible
            controller.showPanel()

            def panelPreview = view.build(PanelWithReflectionView)

            panelPreview.add(controller.getPanel(), 'wrap')

            addPreview(panelPreview, index)

            doLater
            {
                panelPreview.add(controller.getReflection())
            }

        }

    }


    /**
    * Add the preview inside the panelPreview container
    *
    * @param panel Panel to be added
    * @param index Where you want it to be added 
    */
    def addPreview(panel, index)
    {
        view.build
        {
            view.panelPreview.with
            {

                 // Add the Panel to the preview section of the GUI
                add(panel, "gapleft "
                        + animation.getGap() +
                        ", gapright "
                        + animation.getGap(), index)

                validate()
            }
        }
    }


    /**
    * Display Panel thumb
    *
    * @param controller Controller of the panel u want to print
    */
    def printThumb(controller)
    {
        // Add the Thumb to the Thumbs List
        listModel.addElement(controller.getThumb())
    }


    /**
    * Enable/Disable all the buttons of the given component
    *
    * @param component The component containing the buttons you wanna enable/disable
    * @param enabled True or false 
    */
    def setActionsEnabled(enabled = true)
    {
        projectActions.each {
            it.enabled = enabled
        }
        view.newProject.setEnabled(true)
    }


   /**
    * Create a panel
    *
    * @param panel String for panel name
    */
    public createPanel(panel)
    {
        if(project != null)
            project.createPanel(panel)
    }


   /**
    * Create a new project
    *
    */
    public newProject(e)
    {
        if(project.isInProject)
        {
            closeProject(e)
        }
        //project.model = new ProjectModel()
        setActionsEnabled(true)
        project.start()       
    }


   /**
    * Close current project project
    *
    */
    public closeProject(e)
    {
        /*
        * TO DO
        */
        clean()
        project.stop()
    }

   /**
    * Load an existing project
    *
    */
    public loadProject(e)
    {

    }


    /**
    * Clean the GUI
    *
    */
    private clean()
    {
        setActionsEnabled(false)
        for(def i=listModel.size(); i> 0; --i)
        {
            deletePanel(i-1)
        }
    }


    
    /**
    * Main method called when launching app
    *
    */
    static void main(args)
    {
        def m = null
        def c = new IzPackStudio(m)

        c.start()
    }


    /**
    * Exit method to close the app
    *
    */
    def static public exit()
    {
        System.exit(0)
    }

}


