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
import java.awt.Insets


scrollPane(
    constraints: '0,0',
    verticalScrollBarPolicy: JScrollPane.VERTICAL_SCROLLBAR_NEVER,
    horizontalScrollBarPolicy: JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS
) {
    panel(
        layout: new MigLayout("align left")
    ) {

        def inset = new Insets(0,0,0,0)

        button(
        id: 'addHelloPanel',
        icon: imageIcon(resource: '../images/hellopanel.png'),
        size: [100,100],
        margin: inset,
        actionPerformed: {def hello = new HelloPanelController(new PanelModel(), null, controller.project)
            hello.start()
            controller.project.addPanel(hello)
        }
    )


    button(
        id: 'addFinishPanel',
        icon: imageIcon(resource: '../images/finishpanel.png'),
        margin: inset,
        actionPerformed: {
            def finish = new FinishPanelController(new PanelModel(), null, controller.project)
            finish.start()
            controller.project.addPanel(finish)
        }
    )


    button(
        id: 'addLicencePanel',
        icon: imageIcon(resource: '../images/licencepanel.png'),
        margin: inset,
        actionPerformed: {def licence = new GeneralLicencePanelController(new PanelModel(), null, controller.project,0)
            licence.start()
            controller.project.addPanel(licence)
            controller.project.refresh()
        }
    )

     button(
        id: 'addInfoPanel',
        icon: imageIcon(resource: '../images/infopanel.png'),
        margin: inset,
        actionPerformed: {def info = new GeneralInfoPanelController(new PanelModel(), null, controller.project,1)
            info.start()
            controller.project.addPanel(info)
        }
    )

    button(
        id: 'addSummaryPanel',
        icon: imageIcon(resource: '../images/summarypanel.png'),
        margin: inset,
        actionPerformed: {def summary = new SummaryPanelController(new PanelModel(), null, controller.project)
            summary.start()
            controller.project.addPanel(summary)
        }
    )

     button(
        id: 'addInstallPanel',
        icon: imageIcon(resource: '../images/installpanel.png'),
        margin: inset,
        actionPerformed: {def install = new InstallPanelController(new PanelModel(), null, controller.project)
            install.start()
            controller.project.addPanel(install)
        }
    )

    button(
        id: 'addPathInputPanel',
        icon: imageIcon(resource: '../images/pathinputpanel.png'),
        margin: inset,
        actionPerformed: {def pathInput = new PathInputPanelController(new PanelModel(), null, controller.project)
            pathInput.start()
            controller.project.addPanel(pathInput)
        }
    )

    button(
        id: 'addTargetPanel',
        icon: imageIcon(resource: '../images/targetpanel.png'),
        margin: inset,
        actionPerformed: {def target = new TargetPanelController(new PanelModel(), null, controller.project)
            target.start()
            controller.project.addPanel(target)
        }
    )

    button(
        id: 'addXInfoPanel',
        icon: imageIcon(resource: '../images/xinfopanel.png'),
        margin: inset,
        actionPerformed: {def xInfo = new XInfoPanelController(new PanelModel(), null, controller.project)
            xInfo.start()
            controller.project.addPanel(xInfo)
        }
    )

    button(
        id: 'addPacksPanel',
        icon: imageIcon(resource: '../images/packspanel.png'),
        margin: inset,    
        actionPerformed: {def packs = new PacksPanelController(new PanelModel(), null, controller.project)
            packs.start()
            controller.project.addPanel(packs)
        }
    )
    }
        
}