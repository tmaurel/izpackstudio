package com.izforge.izpack.studio.views.panels

import net.miginfocom.swing.MigLayout
import javax.swing.JFileChooser
import com.izforge.izpack.studio.actions.GeneralPanelPropertiesActions

build(GeneralPanelPropertiesActions)

panel(
        layout: new MigLayout("fill"),
        opaque: false,
        visible: true,
        id:"panelProperties"
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
}




