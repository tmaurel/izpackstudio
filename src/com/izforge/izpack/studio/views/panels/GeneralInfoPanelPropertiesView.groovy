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
            text: 'InfoPanel Properties',
            constraints: 'span'
    )

    label(
            text: 'Select your info document',
            constraints: 'span'
    )

    button(
            text: "Select",
            constraints: 'span',
            action: popFileChooser
    )
}




