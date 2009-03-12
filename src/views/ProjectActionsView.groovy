package views

import helpers.WrapLayout


scrollPane(
    constraints: '0,0',
) {
    toolBar(
            floatable: false,
            rollover: true,             
            layout: new WrapLayout(WrapLayout.LEFT),
            borderPainted: false
    ) {
        widget(
            new ToolBarButton(
                    'New Project',
                    imageIcon(resource: '../images/newproject.png')
            ),
            action: newProject
        )

        widget(
            new ToolBarButton(
                    'Load Project',
                    imageIcon(resource: '../images/loadproject.png')
            ),
            action: loadProject
        )

        widget(
            new ToolBarButton(
                    'Close Project',
                    imageIcon(resource: '../images/closeproject.png')
            ),
            action: closeProject
        )

        widget(
            new ToolBarButton(
                    'Save Project',
                    imageIcon(resource: '../images/saveproject.png')
            ),
            action: saveProject
        )

        widget(
            new ToolBarButton(
                    'Project Settings',
                    imageIcon(resource: '../images/projectsettings.png')
            ),
            action: toggleProjectSettings
        )

        widget(
            new ToolBarButton(
                    'Build Project',
                    imageIcon(resource: '../images/buildproject.png')
            ),
            action: buildProject
        )

    }
}