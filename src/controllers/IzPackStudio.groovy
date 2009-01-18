package controllers

import models.*
import views.*
import javax.swing.DefaultListModel
import groovy.swing.SwingBuilder
import javax.swing.Box
import helpers.Positionning


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
    private listModel = new DefaultListModel()



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
    }

    /**
    * Start IzPack Studio
    *
    */
    public start()
    {

        // Display the Splash Screen
        displaySplash()


        setProgressBar(1, "Loading GUI...")


        // Build the main View
        GUI = view.build(IzPackStudioView)


        setProgressBar(10, "Loaded !")

        // Assign the our own List Model to the ThumbList
        view.thumbList.setModel(listModel)

        // Display the GUI
        GUI.show()

        // Create the new empty project
        // TO-DO : Args to load projects (no empty project)
        project = new ProjectController(new ProjectModel(), null, this)
        project.start()

    }

    /**
    * Start Splash Screen
    *
    */
    private displaySplash()
    {
        // Build SplashView
        def splash = view.build(SplashView)

        // Display SplashView
        splash.show()
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
    * Slide the scroll pane to the right index
    *
    * @param index  Index of the panel in the list
    */
    def slideTo(index)
    {
        Positionning.slideViewPositionTo(project, view.panelScrollPane, index)
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
        
        view.build
        {
            view.panelPreview.with
            {
                add(Box.createHorizontalGlue())
                // Add the Panel to the preview section of the GUI
                add(controller.getPanel(), "gapleft "
                        + Positionning.getGap(view.panelScrollPane.getViewport()) +
                        ", gapright "
                        + Positionning.getGap(view.panelScrollPane.getViewport()))

                // Make sure it is visible
                controller.showPanel()

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
    * Main method called when launching app
    *
    */
    def static public main(args)
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


