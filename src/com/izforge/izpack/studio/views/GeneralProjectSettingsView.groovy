package com.izforge.izpack.studio.views

import com.izforge.izpack.studio.actions.GeneralProjectSettingsActions
import java.awt.Color
import javax.swing.JList
import javax.swing.ListSelectionModel
import net.miginfocom.layout.AC
import net.miginfocom.layout.LC
import net.miginfocom.swing.MigLayout
import java.awt.Dimension

build(GeneralProjectSettingsActions)

panel(
    title:'Global Settings',
    tabToolTip:'Global Settings',
    layout: new MigLayout("fill","[right w 150px]20px[left w 170px]","[top][top]")
) {

    label (
        text : 'Project Name',
    )

    textField(
        id:'projName',
        text: controller.model.info.appName,
        constraints : 'w 150px, wrap'
    )

    label (
        text : 'Application Version'
    )

    textField(
        id:'appVersion',
        text: controller.model.info.appVersion,
        constraints : 'w 150px, wrap'
    )

    label (
        text : 'Application URL'
    )

    textField(
        id:'appURL',
        text: controller.model.info.appURL,
        constraints : 'w 150px, wrap'
    )

    label (
        text : 'Author(s)'
    )

    panel (
        constraints : 'wrap',
        layout: new MigLayout(
                    new LC().insets("0"),
                    new AC().gap("0"),
                    new AC())
    ) {


        button (
            id: 'addAuthor',
            preferredSize: new Dimension(30, 30),
            action: addAuthor
        )

        button (
            id: 'remAuthor',
            preferredSize: new Dimension(30, 30),
            constraints: 'wrap',
            action: delAuthor
        )


        scrollPane(
            constraints : 'span, w 150px, h 150px'
        ) {

            table (
                id : 'tableAuthors',
                fillsViewportHeight: true

            ) {
                tableModel(
                    id: 'authorsModel',
                    list: controller.model.info.authors
                ) {
                     propertyColumn(header:'Name',  propertyName:'name')
                     propertyColumn(header:'Email', propertyName:'email')
                }
            }
        }

    }

    label (
        text : 'Langpack(s)'
    )

    panel (
        constraints: 'w 150px, wrap',
        layout: new MigLayout(
                    new LC().insets("0"),
                    new AC().gap("0"),
                    new AC())
    ) {
      scrollPane(
           constraints: 'w 150px'
      ) {

          list (
              id : 'langPacksList',
              selectionMode: ListSelectionModel.MULTIPLE_INTERVAL_SELECTION,
              layoutOrientation: JList.VERTICAL,
              listData: controller.model.availableLangPacks,
              visibleRowCount: 5,
              selectedIndices: controller.model.getSelectedLangPacksIndices(),
              cellRenderer: listCellRenderer {
                def listcell = label(background: Color.decode("#C0D9F7"))
                onRender()
                {
                  listcell.opaque = selected
                  if (value) {
                      listcell.icon = controller.getLangIcon(value)
                      listcell.text = value
                  } else {
                      listcell.icon = null
                      listcell.text = null
                  }
                }
              },
              constraints: 'grow',
          ) {

          }
      }
    }

    label (
        text : 'Width'
    )

    textField(
        id:'appWidth',
        text: controller.model.prefs.width,
        constraints : 'w 150px, wrap'
    )

    label (
        text : 'Heigth'
    )

    textField(
        id:'appHeight',
        text: controller.model.prefs.height,
        constraints : 'w 150px, wrap'
    )


    label (
        text : 'Resizable'
    )

    def resizableButtonGroup = buttonGroup()

    box(

    ){
        radioButton (
            id: 'appResizable',
            text: 'Yes',
            buttonGroup: resizableButtonGroup,
            selected: controller.model.prefs.resizable
        )

        radioButton (
            text: 'No',
            buttonGroup: resizableButtonGroup,
            selected: !controller.model.prefs.resizable
        )
    }

}