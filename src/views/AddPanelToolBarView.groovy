package views

import models.panels.PanelModel
import controllers.panels.HelloPanelController
import controllers.panels.FinishPanelController
import controllers.panels.SummaryPanelController
import controllers.panels.PacksPanelController
import controllers.panels.GeneralInfoPanelController
import controllers.panels.GeneralLicencePanelController
import controllers.panels.InstallPanelController
import controllers.panels.PathInputPanelController
import controllers.panels.XInfoPanelController
import controllers.panels.TargetPanelController
import helpers.WrapLayout


scrollPane(
    constraints: '0,0',
) {
    toolBar(            
            floatable: false,
            rollover: true,
            layout: new WrapLayout(WrapLayout.LEFT)
    ) {

        button(
        id: 'addHelloPanel',
        icon: imageIcon(resource: '../images/hellopanel.png'),
        size: [100,100],
        actionPerformed: {def hello = new HelloPanelController(new PanelModel(), null, controller.project)
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


        button(
            id: 'addLicencePanel',
            icon: imageIcon(resource: '../images/licencepanel.png'),
            actionPerformed: {def licence = new GeneralLicencePanelController(new PanelModel(), null, controller.project,0)
                licence.start()
                controller.project.addPanel(licence)
                controller.project.refresh()
            }
        )

         button(
            id: 'addInfoPanel',
            icon: imageIcon(resource: '../images/infopanel.png'),
            actionPerformed: {def info = new GeneralInfoPanelController(new PanelModel(), null, controller.project,1)
                info.start()
                controller.project.addPanel(info)
            }
        )

        button(
            id: 'addSummaryPanel',
            icon: imageIcon(resource: '../images/summarypanel.png'),
            actionPerformed: {def summary = new SummaryPanelController(new PanelModel(), null, controller.project)
                summary.start()
                controller.project.addPanel(summary)
            }
        )

        button(
            id: 'addInstallPanel',
            icon: imageIcon(resource: '../images/installpanel.png'),
            actionPerformed: {def install = new InstallPanelController(new PanelModel(), null, controller.project)
                install.start()
                controller.project.addPanel(install)
            }
        )

        button(
            id: 'addPathInputPanel',
            icon: imageIcon(resource: '../images/pathinputpanel.png'),
            actionPerformed: {def pathInput = new PathInputPanelController(new PanelModel(), null, controller.project)
                pathInput.start()
                controller.project.addPanel(pathInput)
            }
        )

        button(
            id: 'addTargetPanel',
            icon: imageIcon(resource: '../images/targetpanel.png'),
            actionPerformed: {def target = new TargetPanelController(new PanelModel(), null, controller.project)
                target.start()
                controller.project.addPanel(target)
            }
        )

        button(
            id: 'addXInfoPanel',
            icon: imageIcon(resource: '../images/xinfopanel.png'),
            actionPerformed: {def xInfo = new XInfoPanelController(new PanelModel(), null, controller.project)
                xInfo.start()
                controller.project.addPanel(xInfo)
            }
        )

        button(
            id: 'addPacksPanel',
            icon: imageIcon(resource: '../images/packspanel.png'),
            actionPerformed: {def packs = new PacksPanelController(new PanelModel(), null, controller.project)
                packs.start()
                controller.project.addPanel(packs)
            }
        )
    }
}
