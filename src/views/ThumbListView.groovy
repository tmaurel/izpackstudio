package views

import javax.swing.ListSelectionModel
import javax.swing.JList
import net.miginfocom.swing.MigLayout
import javax.swing.BorderFactory
import javax.swing.event.ListSelectionListener
import javax.swing.JScrollPane
import javax.swing.DropMode
import javax.swing.TransferHandler
import javax.swing.DefaultListModel
import java.awt.datatransfer.DataFlavor
import java.awt.datatransfer.Transferable
import javax.swing.JComponent
import java.awt.datatransfer.StringSelection
import java.awt.event.MouseMotionAdapter
import java.awt.event.MouseEvent
import java.awt.event.MouseAdapter
import java.awt.event.MouseListener


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
            def model = thumbList.getModel()
            def dragged = false

            thumbList.addListSelectionListener( {e ->
                if (!e.getValueIsAdjusting())
                {
                    if (thumbList.getSelectedIndex() != -1)
                    {
                        controller.slideTo(thumbList.getSelectedIndex())
                    }
                }
            }  as ListSelectionListener)


            def mouselistener = [
                    mouseClicked: {},
                    mousePressed: { from = fromBuff = thumbList.getSelectedIndex() }, 
                    mouseReleased: { if((to != from) && dragged) { controller.movePanel(from, to) ; changed = false ; dragged = false } },
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