package views

import models.panels.PanelModel
import controllers.panels.*
import helpers.WrapLayout


scrollPane(
    constraints: '0,0',
) {
    toolBar(
            floatable: false,
            rollover: true,
            layout: new WrapLayout(WrapLayout.LEFT)
    ) {


        widget(
            new ToolBarButton(
                    'HelloPanel',
                    imageIcon(resource: '../images/hellopanel.png')
            ),
            id: 'addHelloPanel',
            actionPerformed: {
                def hello = new HelloPanelController(new PanelModel(), null, controller.project)
                hello.start()
                controller.project.addPanel(hello)
            }
        )


        widget(
            new ToolBarButton(
                    'FinishPanel',
                    imageIcon(resource: '../images/finishpanel.png')
            ),
            id: 'addFinishPanel',
            actionPerformed: {
                def finish = new FinishPanelController(new PanelModel(), null, controller.project)
                finish.start()
                controller.project.addPanel(finish)
            }
        )


        widget(
            new ToolBarButton(
                    'LicencePanel',
                    imageIcon(resource: '../images/licencepanel.png')
            ),
            id: 'addLicencePanel',
            actionPerformed: {
                def licence = new GeneralLicencePanelController(new PanelModel(), null, controller.project,0)
                licence.start()
                controller.project.addPanel(licence)
            }
        )

        widget(
            new ToolBarButton(
                    'InfoPanel',
                    imageIcon(resource: '../images/infopanel.png')
            ),
            id: 'addInfoPanel',
            actionPerformed: {
                def info = new GeneralInfoPanelController(new PanelModel(), null, controller.project,1)
                info.start()
                controller.project.addPanel(info)
            }
        )

        widget(
            new ToolBarButton(
                    'SummaryPanel',
                    imageIcon(resource: '../images/summarypanel.png')
            ),
            id: 'addSummaryPanel',
            actionPerformed: {
                def summary = new SummaryPanelController(new PanelModel(), null, controller.project)
                summary.start()
                controller.project.addPanel(summary)
            }
        )

        widget(
            new ToolBarButton(
                    'InstallPanel',
                    imageIcon(resource: '../images/installpanel.png')
            ),
            id: 'addInstalPanel',
            actionPerformed: {
                def install = new InstallPanelController(new PanelModel(), null, controller.project)
                install.start()
                controller.project.addPanel(install)
            }
        )

        widget(
            new ToolBarButton(
                    'PathInputPanel',
                    imageIcon(resource: '../images/pathinputpanel.png')
            ),
            id: 'addPathInputPanel',
            actionPerformed: {
                def pathInput = new PathInputPanelController(new PanelModel(), null, controller.project)
                pathInput.start()
                controller.project.addPanel(pathInput)
            }
        )

        widget(
            new ToolBarButton(
                    'TargetPanel',
                    imageIcon(resource: '../images/targetpanel.png')
            ),
            id: 'addTargetPanel',
            actionPerformed: {def target = new TargetPanelController(new PanelModel(), null, controller.project)
                target.start()
                controller.project.addPanel(target)
            }
        )

        widget(
            new ToolBarButton(
                    'XInfoPanel',
                    imageIcon(resource: '../images/xinfopanel.png')
            ),
            id: 'addXInfoPanel',
            actionPerformed: {
                def xInfo = new XInfoPanelController(new PanelModel(), null, controller.project)
                xInfo.start()
                controller.project.addPanel(xInfo)
            }
        )

        widget(
            new ToolBarButton(
                    'PacksPanel',
                    imageIcon(resource: '../images/packspanel.png')
            ),
            id: 'addPacksPanel',
            actionPerformed: {
                def packs = new PacksPanelController(new PanelModel(), null, controller.project)
                packs.start()
                controller.project.addPanel(packs)
            }
        )
    }
}
