package views

import net.miginfocom.swing.MigLayout
import actions.PacksProjectSettingsActions
import java.awt.Dimension
import net.miginfocom.layout.LC
import net.miginfocom.layout.AC
import helpers.Positionning
import javax.swing.JList
import javax.swing.ListSelectionModel
import java.awt.Color
import javax.swing.BorderFactory


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

dialog(
    id: 'addPackDialog',
    modal: true,
    title: 'Add Pack',
    location: Positionning.CenterPosition([600,515]),
    size: [600,515],
    resizable : false,
    layout: new MigLayout("fill,","[right w 210px]20px[left w 370px]","[top][top]")
) {

    label (
        text : 'Pack name :',
    )

    textField(
        id:'packName',
        border: BorderFactory.createMatteBorder(1,1,1,1,Color.gray),
        constraints : 'growX, wrap'
    )

    label (
        text : 'Description :',
    )

    textArea(
        id:'packDesc',
        border: BorderFactory.createMatteBorder(1,1,1,1,Color.gray),
        constraints : 'growX, h 70px, wrap'
    )

    label (
        text : 'Behaviour :',
    )

    list(
        id: 'packBehaviour',
        listData: ['Required','Preselected', 'Optional'],
        selectionMode: ListSelectionModel.SINGLE_SELECTION,
        layoutOrientation: JList.VERTICAL,
        border: BorderFactory.createMatteBorder(1,1,1,1,Color.gray),
        constraints: 'wrap'
    )

    label (
        text : 'Parent :',
    )

    list(
        id: 'packParent',
        listData: ['none'],
        selectionMode: ListSelectionModel.SINGLE_SELECTION,
        layoutOrientation: JList.VERTICAL,
        border: BorderFactory.createMatteBorder(1,1,1,1,Color.gray),
        constraints: 'wrap'
    )


    panel(
        constraints: 'w 590px, span 2',
        border: BorderFactory.createMatteBorder(1,1,1,1,Color.gray)
    ) {
      fileChooser(
          id: 'packFiles',
          constraints: 'grow'
      )
    }





}