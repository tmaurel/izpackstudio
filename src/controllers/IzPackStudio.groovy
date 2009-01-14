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
        def GUI = view.build(IzPackStudioView)
        GUI.show()

        if(!StudioConf.checkIzPackHome())
        {


        }
        else
        {
            def hellopanel = new HelloPanelController(new InstallerData())
            hellopanel.start()
            view.panelPreview.add(hellopanel.getPanel())
            GUI.show()
        }



    }


    def static main(args)
    {
        def m = new StudioConf()
        def c = new IzPackStudio(m)
        
        c.start()
    }

    def static exit()
    {
        System.exit(0)
    }

}


