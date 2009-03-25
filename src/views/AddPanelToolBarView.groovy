package views

import helpers.WrapLayout


/**
 * Building GUI
 *
 */
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
            action: addHelloPanel
        ) 

        widget(
            new ToolBarButton(
                    'InfoPanel',
                    imageIcon(resource: '../images/infopanel.png')
            ),
            action: addInfoPanel
        )

        widget(
            new ToolBarButton(
                    'LicencePanel',
                    imageIcon(resource: '../images/licencepanel.png')
            ),
            action: addLicencePanel
        )

        widget(
            new ToolBarButton(
                    'PathInputPanel',
                    imageIcon(resource: '../images/pathinputpanel.png')
            ),
            action: addPathInputPanel
        )

        widget(
            new ToolBarButton(
                    'TargetPanel',
                    imageIcon(resource: '../images/targetpanel.png')
            ),
            action: addTargetPanel
        )

        widget(
            new ToolBarButton(
                    'PacksPanel',
                    imageIcon(resource: '../images/packspanel.png')
            ),
            action: addPacksPanel
        )

        widget(
            new ToolBarButton(
                    'SummaryPanel',
                    imageIcon(resource: '../images/summarypanel.png')
            ),
            action: addSummaryPanel
        )        

        widget(
            new ToolBarButton(
                    'InstallPanel',
                    imageIcon(resource: '../images/installpanel.png')
            ),
            action: addInstallPanel
        )

        widget(
            new ToolBarButton(
                    'FinishPanel',
                    imageIcon(resource: '../images/finishpanel.png')
            ),
            action: addFinishPanel
        )
    }
}
