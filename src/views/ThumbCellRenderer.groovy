package views

import javax.swing.JList
import java.awt.Dimension
import javax.swing.ListCellRenderer
import java.awt.Component
import models.ThumbEntry
import javax.swing.JLabel
import javax.swing.BorderFactory
import javax.swing.SwingConstants
import java.awt.Color



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