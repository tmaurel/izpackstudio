package views

import models.PanelModel
import controllers.HelloPanelController
import controllers.FinishPanelController
import java.awt.BorderLayout


toolBar(
    constraints: BorderLayout.NORTH
) {

    button(
        id: 'addHelloPanel',
        icon: imageIcon(resource: '../images/hellopanel.png'),
        actionPerformed: {
            def hello = new HelloPanelController(new PanelModel(), null, controller.project)
            hello.start()
            controller.project.addPanel(hello)
        }
    )


    button(
        id: 'addFinishPanel',
        icon: imageIcon(resource: '../images/finishpanel.png'),
        actionPerformed: {
            def finish = new FinishPanelController(new PanelModel(), null, controller.project)
            finish.start()
            controller.project.addPanel(finish)
        }
    )
        
}