package views

import java.awt.event.MouseListener
import java.awt.event.MouseMotionAdapter
import javax.swing.*
import javax.swing.event.ListSelectionListener
import net.miginfocom.swing.MigLayout



popupMenu(
    id: 'thumbPopupMenu'
) {
    menuItem(deletePanel)
}


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
            model : new DefaultListModel(),
            selectionMode: ListSelectionModel.SINGLE_SELECTION,
            layoutOrientation: JList.VERTICAL,
            cellRenderer: new ThumbCellRenderer(),
            selectedIndex: -1,
        ) {
            def from = null
            def fromBuff = null
            def to = null
            def dragged = false

            thumbList.addListSelectionListener( {e ->
                if (!e.getValueIsAdjusting())
                {
                    if (thumbList.getSelectedIndex() != -1 && thumbList.getModel().getSize() != 0)
                    {
                        controller.slideTo(thumbList.getSelectedIndex())
                    }
                }
            }  as ListSelectionListener)


            def mouselistener = [
                    mouseClicked: {
                    },
                    mousePressed: {
                        from = fromBuff = thumbList.getSelectedIndex()
                    },
                    mouseReleased: {
                        if(it.isPopupTrigger() && !dragged)
                        {
                            thumbList.setSelectedIndex( thumbList.locationToIndex(it.getPoint()) )
                            thumbPopupMenu.show(thumbList, it.getX(), it.getY() );
                        }
                        else
                        {
                            if((to != from) && dragged)
                            {
                                controller.movePanel(from, to)
                                changed = false

                            }
                        }
                        dragged = false
                    },
                    mouseEntered: {},
                    mouseExited: {},
            ] as MouseListener


            def motionlistener = [
                mouseDragged: {
                    dragged = true
                    to  = thumbList.getSelectedIndex()
                    if(to == fromBuff)
                        return
                    controller.switchThumbs(fromBuff, to)
                    fromBuff = to
                },
                mouseMoved: {}
            ] as MouseMotionAdapter


            thumbList.addMouseListener(mouselistener)
            thumbList.addMouseMotionListener(motionlistener)



        }
    }

}