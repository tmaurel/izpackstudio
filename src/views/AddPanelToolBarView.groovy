package views

import models.PanelModel
import controllers.HelloPanelController
import controllers.FinishPanelController


toolBar(
    constraints: 'dock north, grow'
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