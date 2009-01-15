package controllers

import com.izforge.izpack.panels.HelloPanel
import java.awt.Dimension
import java.awt.Color
import javax.swing.BorderFactory

class HelloPanelController extends Controller {

    def panel

    HelloPanelController(m = null, v = null)
    {
        super(m, v)
        model.load()
    }

    def start()
    {
        panel = new HelloPanel(model.installerframe, model.installerframe.installdata)
        panel.setPreferredSize(new Dimension(800,600))
        panel.setBorder(BorderFactory.createMatteBorder(1,1,1,1,Color.BLACK))
    }

    def getPanel()
    {
        panel.setVisible(true)
        return panel
    }

}