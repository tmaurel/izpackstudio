package controllers

import models.*
import views.*

class IzPackStudio extends Controller
{

    IzPackStudio(m = null, v = null)
    {
        super(m, v)
    }

    def start()
    {

        displaySplash()
        sleep(200)
        setProgressBar(1, "Loading GUI...")
        def GUI = view.build(IzPackStudioView)
        sleep(200)
        setProgressBar(2)
        sleep(200)
        setProgressBar(3)
        sleep(200)
        setProgressBar(4)
        sleep(200)
        setProgressBar(5, "Loading Panels...")
        def hellopanel = new HelloPanelController(new InstallerData())
        hellopanel.start()
        view.panelPreview.add(hellopanel.getPanel())
        sleep(200)
        setProgressBar(5)
        sleep(200)
        setProgressBar(6)
        sleep(200)
        setProgressBar(7)
        sleep(200)
        setProgressBar(8)
        sleep(200)
        setProgressBar(9)
        sleep(200)
        setProgressBar(10, "Loaded !")
        GUI.show()       

    }


    def displaySplash()
    {
        def splash = view.build(SplashView)
        splash.show()
    }

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

    def static main(args)
    {
        def m = null
        def c = new IzPackStudio(m)
        
        c.start()
    }

    def static exit()
    {
        System.exit(0)
    }

}


