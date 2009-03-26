package views

import actions.PacksProjectSettingsActions
import java.awt.Dimension
import javax.swing.ListSelectionModel
import net.miginfocom.layout.AC
import net.miginfocom.layout.LC
import net.miginfocom.swing.MigLayout
import org.jdesktop.swingx.JXTreeTable

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
            preferredSize: new Dimension(30, 30),
            action: addPack
        )

        button (
            preferredSize: new Dimension(30, 30),
            action: delPack
        )

        button (
            preferredSize: new Dimension(30, 30),
            action: upPack
        )

        button (
            preferredSize: new Dimension(30, 30),
            action: downPack
        )

    }

    scrollPane(
        constraints: 'w 100%, h 93%'
    ) {

        widget(
            id: 'packTree',
            new JXTreeTable(controller.model.packs)
        )
        packTree.setShowGrid(false, true)
        packTree.setEditable(false)
        packTree.setSelectionMode(ListSelectionModel.SINGLE_SELECTION)
        packTree.setColumnControlVisible(true)
        packTree.setHorizontalScrollEnabled(true)
        packTree.setFillsViewportHeight(false)
        packTree.setRootVisible(true)
        packTree.expandAll()
    }

}
