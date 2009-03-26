package views

import net.miginfocom.swing.MigLayout
import javax.swing.BorderFactory
import helpers.Positionning
import java.awt.Color
import javax.swing.ListSelectionModel
import javax.swing.JList
import com.izforge.izpack.compiler.PackInfo
import net.miginfocom.layout.LC
import net.miginfocom.layout.AC
import java.awt.Dimension

dialog(
    id: 'addPackDialog',
    modal: true,
    title: 'Add Pack',
    location: Positionning.CenterPosition([450,450]),
    size: [450,450],
    resizable : false,
    layout: new MigLayout("fill,","[right w 150px]20px[left w 280px]","[top][top]")
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
        selectedIndex: 0,
        layoutOrientation: JList.VERTICAL,
        border: BorderFactory.createMatteBorder(1,1,1,1,Color.gray),
        constraints: 'wrap'
    )

    label (
        text : 'Parent :',
    )


    scrollPane(
        constraints: 'h 130px, growX, wrap'
    ) {

        def data = packTree.getTreeTableModel().getFilteredArray(PackInfo)
        data.add(0, "None")
        list(
            id: 'packParent',
            listData: data,
            selectionMode: ListSelectionModel.SINGLE_SELECTION,
            selectedIndex: 0,
            layoutOrientation: JList.VERTICAL
        )
    }

    label (
        text : 'File(s) :'
    )

    panel (
        constraints : 'wrap',
        layout: new MigLayout(
                    new LC().insets("0"),
                    new AC().gap("0"),
                    new AC())
    ) {


        button (
            preferredSize: new Dimension(30, 30),
            action: addFile
        )

        button (
            preferredSize: new Dimension(30, 30),
            constraints: 'wrap',
            action: remFile
        )


        scrollPane(
            constraints : 'span, h 150px'
        ) {
            table (
                id : 'tableFiles',
                fillsViewportHeight: true,
                rowSelectionAllowed: false

            ) {
                tableModel(
                    id: 'filesModel',
                ) {
                     propertyColumn(header:'Path',  propertyName:'path')
                     propertyColumn(header:'Target', propertyName:'target')
                }
            }
        }

    }

    panel(
        constraints:'span, w 30%, h 10%, align right'
    ) {

        button (
            action: insertPack
        )

        button (
            action: cancelPack
        )

    }


}


