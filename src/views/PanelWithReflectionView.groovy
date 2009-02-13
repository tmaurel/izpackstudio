package views

import net.miginfocom.swing.MigLayout
import javax.swing.BorderFactory
import net.miginfocom.layout.LC
import net.miginfocom.layout.AC


panel(
    layout : new MigLayout(
        new LC().insets("0"),
        new AC(),
        new AC()),
    opaque: false,

    border: BorderFactory.createEmptyBorder(),
)
{

}
