package views

import net.miginfocom.layout.AC
import net.miginfocom.swing.MigLayout
import net.miginfocom.layout.LC
import javax.swing.BorderFactory
import java.awt.Color

panel(
    id: 'stuckPanel',
    constraints: 'width 30%, height 60%, grow',
    layout: new MigLayout(
        new LC().fill().noVisualPadding(),
        new AC().gap("0"),
        new AC().align("center")),
 ) {

    panel(
        id: 'centerPanel',
        constraints: 'w 100%, h 100%',
        background: Color.WHITE,
        border: BorderFactory.createMatteBorder(1,1,1,1,Color.lightGray),
        layout: new MigLayout()
    ) {

        scrollPane(
            id: 'panelScrollPane',
            constraints: 'w 100%, h 100%',
            border: BorderFactory.createEmptyBorder(),
        ) {

            panel(
            id: 'panelPreview',
            background: Color.WHITE,
            layout: new MigLayout(
                    new LC().fillY(),
                    new AC().gap("0"),
                    new AC().align("center"))
            ) { }
        }
    }
}