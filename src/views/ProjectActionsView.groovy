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
            id: 'startNewProject',
            actionPerformed: {

            }
        )

        widget(
            new ToolBarButton(
                    'Close Project',
                    imageIcon(resource: '../images/closeproject.png')
            ),
            id: 'closeProject',
            actionPerformed: {

            }
        )

    }
}