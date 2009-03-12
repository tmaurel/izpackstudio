package views.panels

import net.miginfocom.swing.MigLayout
import javax.swing.JFileChooser
import actions.GeneralPanelPropertiesActions

build(GeneralPanelPropertiesActions)

panel(
        layout: new MigLayout("fill"),
        opaque: false,
        visible: true,
        id:"test"
)
{
    label(
            text: 'Licence Properties',
            constraints: 'span'
    )

    label(
            text: 'Select your licence document',
            constraints: 'span'
    )

    button(
            text: "Select",
            constraints: 'span',
            action: popFileChooser
    )
    dialog(
            id: "selectFile",
            visible: false,
            size: [600,400]
    ) {
        fileChooser(
                id: "myFileChooser",
                dialogTitle: "Select a Text File"
        ) {

        }
    }
}




