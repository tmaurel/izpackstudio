package views

import javax.swing.ListSelectionModel
import javax.swing.JList
import net.miginfocom.swing.MigLayout
import javax.swing.BorderFactory
import javax.swing.event.ListSelectionListener
import javax.swing.JScrollPane


panel(
    id: 'thumbPanel',
    layout: new MigLayout('fill'),
    constraints: '0, 0'
) {

    scrollPane(
        id: 'thumbScrollPane',
        constraints: 'width 100%, h 100%',
        verticalScrollBarPolicy: JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
        border: BorderFactory.createEmptyBorder()
    ) {
        list(
            id: 'thumbList',
            selectionMode: ListSelectionModel.SINGLE_SELECTION,
            layoutOrientation: JList.VERTICAL,
            cellRenderer: new ThumbCellRenderer(),
            selectedIndex: -1
        ) {
            thumbList.addListSelectionListener( {e ->
                if (!e.getValueIsAdjusting())
                {
                    if (thumbList.getSelectedIndex() != -1)
                    {
                        controller.slideTo(thumbList.getSelectedIndex())
                    }
                }
            }  as ListSelectionListener)
        }
    }
}