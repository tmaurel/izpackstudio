package views

import helpers.Positionning
import java.awt.BorderLayout
import java.awt.Color
import java.awt.Dimension
import javax.swing.BorderFactory
import javax.swing.SwingConstants
import net.miginfocom.layout.AC
import net.miginfocom.layout.LC
import net.miginfocom.swing.MigLayout

dialog(
    id: 'projectSettings',    
    modal: true,
    title: 'Project Settings',
    location: Positionning.CenterPosition([400,400]),
    size: [400,400],
    resizable : false,     
    layout: new MigLayout()
) {
    tabbedPane(
        id: 'middlepanel',
        constraints:' w 100%, h 90%, wrap'
    )
    {
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
                constraints : 'wrap, growy',
                layout: new MigLayout(
                            new LC().insets("0"),
                            new AC().gap("0"),
                            new AC())
            ) {

                button (
                    id: 'addAuthor',
                    preferredSize : new Dimension(70,25),
                    text :'New',
                    actionPerformed: {
                        def authors = authorsModel.getRowsModel().getValue()
                        authors.add([name: "name", email: "email"])
                        authorsModel.fireTableDataChanged()
                    }
                )

                button (
                    id: 'remAuthor',
                    preferredSize : new Dimension(70,25),
                    text :'Remove',
                    constraints :'wrap',
                    actionPerformed: {
                        def authors = authorsModel.getRowsModel().getValue()
                        def indexes = tableAuthors.getSelectedRows()
                        indexes.each
                        {
                            authors.remove(it)
                        }
                        authorsModel.fireTableDataChanged()
                    }
                )

                scrollPane(
                    constraints : 'span 2, w 150px'
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

        }
    }

    panel(
        id: 'bottompanel',
        constraints:'span, w 100%, h 10%',
        border: BorderFactory.createMatteBorder(1,1,1,1,Color.lightGray),
        layout: new MigLayout("","40% [] 5px [] 5px [] 5px","[center] ")
    ) {

        button (
            id: 'save',
            preferredSize : new Dimension(90,20),
            text :'Save',
        )

        button (
            id: 'cancel',
            preferredSize : new Dimension(90,20),
            text :'Cancel',
            actionPerformed:
            {
                projectSettings.dispose()                        // not so clean ?
            }
        )

        button (
            id: 'apply',
            preferredSize : new Dimension(90,20),
            text :'Apply',
            actionPerformed:
            {
                projectSettings.dispose()                        // not so clean ?
            }
        )
    }    
    
}
