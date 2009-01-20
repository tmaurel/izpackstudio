package views

import javax.swing.ListSelectionModel
import javax.swing.ListCellRenderer
import javax.swing.JLabel
import javax.swing.JList
import java.awt.Color
import java.awt.Component
import models.ThumbEntry
import net.miginfocom.swing.MigLayout
import javax.swing.BorderFactory
import javax.swing.SpringLayout
import java.awt.Dimension
import javax.swing.SwingConstants
import javax.swing.event.ListSelectionListener
import javax.swing.JScrollPane




class ThumbCellRenderer extends JLabel implements ListCellRenderer {

    ThumbCellRenderer() {
        setForeground(Color.black)
        setVerticalTextPosition(SwingConstants.BOTTOM)
        setHorizontalTextPosition(SwingConstants.CENTER)
        setOpaque(false)
    }

    public Component getListCellRendererComponent(JList list, Object value,
                                int index, boolean isSelected, boolean cellHasFocus) {

        def whiteBorder = BorderFactory.createMatteBorder(4,4,4,4,Color.WHITE)
        def redBorder = BorderFactory.createMatteBorder(4,4,4,4,Color.RED)

        setPreferredSize(new Dimension(142,120))
        setAlignmentX(3)
        ThumbEntry entry = (ThumbEntry) value
        setText(entry.getTitle())
        setIcon(entry.getImage())

        if (isSelected) {
            setBorder(redBorder)
        }
        else
        {
            setBorder(whiteBorder)
        }
        return this;
    }
}



    scrollPane(
        id: 'thumbScrollPane',
        constraints: 'w 100%, h 100%',
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
  