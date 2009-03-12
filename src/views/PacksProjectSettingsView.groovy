package views

import net.miginfocom.swing.MigLayout
import actions.PacksProjectSettingsActions
import java.awt.Dimension
import net.miginfocom.layout.LC
import net.miginfocom.layout.AC


build(PacksProjectSettingsActions)


panel(
    title:'Packs Settings',
    tabToolTip:'Packs Settings',
    layout: new MigLayout()
) {

    panel(
            constraints: 'h 7%, wrap',
            layout: new MigLayout(
                    new LC().insets("0"),
                    new AC().gap("0"),
                    new AC())
    ) {

        button (
            id: 'addPack',
            preferredSize: new Dimension(30, 30),
            action: addPack
        )

        button (
            id: 'delPack',
            preferredSize: new Dimension(30, 30),
            action: delPack
        )

        button (
            id: 'upPack',
            preferredSize: new Dimension(30, 30),
            action: upPack
        )

        button (
            id: 'downPack',
            preferredSize: new Dimension(30, 30),
            action: downPack
        )

    }

    scrollPane(
        constraints: 'w 100%, h 93%'
    ) {
        tree(


        ) {
          
        }
    }

}