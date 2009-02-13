package views

import net.miginfocom.layout.AC
import net.miginfocom.swing.MigLayout
import net.miginfocom.layout.LC
import javax.swing.BorderFactory
import java.awt.Color

panel(
    id: 'stuckPanel',
    constraints: '0, 0',
    layout: new MigLayout(
        new LC().fill().noVisualPadding(),
        new AC(),
        new AC().align("center")),
 ) {

    panel(
        id: 'centerPanel',
        constraints: 'w 100%, h 100%',
        layout: new MigLayout(),
    ) {

        scrollPane(
            id: 'panelScrollPane',
            constraints: 'w 100%, h 100%',
            opaque: false
        ) {

            panelScrollPane.getViewport().setOpaque(false)
            panelScrollPane.getViewport().setBorder(null)

            container(
                new GradientPanel(),    
                id: 'panelPreview',
                background: Color.WHITE,
                layout: new MigLayout(
                        new LC().fillY().insets("0"),
                        new AC(),
                        new AC().align("center")),                
            ) { }
        }
    }
}