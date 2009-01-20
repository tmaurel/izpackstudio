package views

import java.awt.BorderLayout
import net.miginfocom.swing.MigLayout
import net.miginfocom.layout.AC
import net.miginfocom.layout.LC
import javax.swing.JToolBar


toolBar(
    constraints: BorderLayout.EAST,
    orientation: JToolBar.VERTICAL,
    layout: new MigLayout(
        new LC().fill().noVisualPadding(),
        new AC().gap("0"),
        new AC().align("center").align("top"))
) {

   label(text: "Panel Properties")

}