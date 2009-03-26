package com.izforge.izpack.studio.views

import javax.swing.BorderFactory
import net.miginfocom.swing.MigLayout

scrollPane(
    constraints: '0,0',
    border: BorderFactory.createEmptyBorder()
) {
    toolBar(
        id: 'propertiesPanel',
        floatable: false,
        rollover: true,
        layout: new MigLayout("fill", "left", "top")
    ) 
}