package views

import models.PanelModel
import controllers.HelloPanelController
import controllers.FinishPanelController
import controllers.InfoPanelController
import controllers.LicencePanelController
import controllers.SummaryPanelController
import javax.swing.JScrollPane
import net.miginfocom.layout.LC
import net.miginfocom.swing.MigLayout
import net.miginfocom.layout.AC
import controllers.PacksPanelController
import controllers.SimpleFinishPanelController
import controllers.GeneralInfoPanelController
import controllers.GeneralLicencePanelController
import controllers.InstallPanelController
import controllers.PathInputPanelController
import controllers.XInfoPanelController
import controllers.TargetPanelController


scrollPane(
    constraints: '0,0',
    verticalScrollBarPolicy: JScrollPane.VERTICAL_SCROLLBAR_NEVER,
    horizontalScrollBarPolicy: JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS
) {
    panel(
        layout: new MigLayout("align left")
    ) {

        button(
        id: 'addHelloPanel',
        icon: imageIcon(resource: '../images/hellopanel.png'),
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
        icon: imageIcon(resource: '../images/hellopanel.png'),
        actionPerformed: {def install = new InstallPanelController(new PanelModel(), null, controller.project)
            install.start()
            controller.project.addPanel(install)
        }
    )

    button(
        id: 'addPathInputPanel',
        icon: imageIcon(resource: '../images/hellopanel.png'),
        actionPerformed: {def pathInput = new PathInputPanelController(new PanelModel(), null, controller.project)
            pathInput.start()
            controller.project.addPanel(pathInput)
        }
    )

    button(
        id: 'addTargetPanel',
        icon: imageIcon(resource: '../images/hellopanel.png'),
        actionPerformed: {def target = new TargetPanelController(new PanelModel(), null, controller.project)
            target.start()
            controller.project.addPanel(target)
        }
    )

    button(
        id: 'addXInfoPanel',
        icon: imageIcon(resource: '../images/hellopanel.png'),
        actionPerformed: {def xInfo = new XInfoPanelController(new PanelModel(), null, controller.project)
            xInfo.start()
            controller.project.addPanel(xInfo)
        }
    )

    button(
        id: 'addSimpleFinishPanel',
        icon: imageIcon(resource: '../images/hellopanel.png'),
        actionPerformed: {def simpleFinish = new SimpleFinishPanelController(new PanelModel(), null, controller.project)
            simpleFinish.start()
            controller.project.addPanel(simpleFinish)
        }
    )

    button(
        id: 'addPacksPanel',
        icon: imageIcon(resource: '../images/hellopanel.png'),
        actionPerformed: {def packs = new PacksPanelController(new PanelModel(), null, controller.project)
            packs.start()
            controller.project.addPanel(packs)
        }
    )
    }
        
}