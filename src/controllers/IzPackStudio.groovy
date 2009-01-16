package controllers

import models.*
import views.*
import javax.swing.DefaultListModel


/**
 * IzPackStudio main class
 *
 */
class IzPackStudio extends Controller
{

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
    def start()
    {

        displaySplash()
        setProgressBar(1, "Loading GUI...")
        def GUI = view.build(IzPackStudioView)



        def project = new ProjectController(new ProjectModel(), view)
        //setProgressBar(5, "Loading Panels...")
        def hellopanel = new HelloPanelController(null, view, project)
        hellopanel.start()

        view.panelPreview.add(hellopanel.getPanel())

        setProgressBar(10, "Loaded !")
        GUI.show()


        def listModel = new DefaultListModel()
        listModel.addElement(hellopanel.getThumb())
        listModel.addElement(hellopanel.getThumb())
        listModel.addElement(hellopanel.getThumb())
        listModel.addElement(hellopanel.getThumb())
        listModel.addElement(hellopanel.getThumb())
        listModel.addElement(hellopanel.getThumb())
        view.thumbList.setModel(listModel)



    }

    /**
    * Start Splash Screen
    *
    */
    def displaySplash()
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
    def setProgressBar(num, text = null) throws Exception
    {
        view.splashProgress.setValue(num)

        if(text != null)
        {
            view.loadingText.setText(text)
        }

        if(num == view.splashProgress.getMaximum())
        {
            javax.swing.SwingUtilities.invokeAndWait(
               Thread.start {
                   sleep(1000)
                   view.splashWindow.setVisible(false)
                   view.splashWindow.dispose()                       
               }
            )
        }

    }


    /**
    * Main method called when launching app
    *
    */
    def static main(args)
    {
        def m = null
        def c = new IzPackStudio(m)
        
        c.start()
    }

    /**
    * Exit method to close the app
    *
    */
    def static exit()
    {
        System.exit(0)
    }

}


