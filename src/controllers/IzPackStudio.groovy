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
        def GUI = swing.build(view)
        GUI.show()

        if(!StudioConf.checkIzPackHome())
        {


        }
        else
        {
            def hellopanel = new HelloPanelController(new InstallerData())
            hellopanel.start()
            swing.panelPreview.add(hellopanel.getPanel())
            GUI.show()
        }



    }


    def static main(args)
    {
        def m = new StudioConf()
        def v = IzPackStudioView
        def c = new IzPackStudio(m, v)
        
        c.start()
    }

    def static exit()
    {
        System.exit(0)
    }

}


