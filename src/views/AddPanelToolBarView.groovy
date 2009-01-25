package views

import models.PanelModel
import controllers.HelloPanelController
import controllers.FinishPanelController
import controllers.InfoPanelController
import controllers.LicencePanelController
import controllers.SummaryPanelController


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
        id: 'addInfoPanel',
        icon: imageIcon(resource: '../images/infopanel.png'),
        actionPerformed: {
            def info = new InfoPanelController(new PanelModel(), null, controller.project)
            info.start()
            controller.project.addPanel(info)
        }
    )

    button(
        id: 'addLicencePanel',
        icon: imageIcon(resource: '../images/licencepanel.png'),
        actionPerformed: {
            def licence = new LicencePanelController(new PanelModel(), null, controller.project)
            licence.start()
            controller.project.addPanel(licence)
        }
    )

    button(
        id: 'addSummaryPanel',
        icon: imageIcon(resource: '../images/summarypanel.png'),
        actionPerformed: {
            def summary = new SummaryPanelController(new PanelModel(), null, controller.project)
            summary.start()
            controller.project.addPanel(summary)
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