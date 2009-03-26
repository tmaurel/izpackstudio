package com.izforge.izpack.studio.views

import net.miginfocom.swing.MigLayout


panel(
    layout : new MigLayout("fill, insets 0", "center", "top"),
    opaque: false,
    preferredSize: controller.getPreviewSize(),
    constraints: 'leading'
) { }
