package views

import net.miginfocom.layout.AC
import net.miginfocom.swing.MigLayout
import net.miginfocom.layout.LC
import javax.swing.BorderFactory
import java.awt.Color
import java.awt.event.MouseListener
import java.awt.Point

panel(
    id: 'stuckPanel',
    constraints: '0, 0',
    layout: new MigLayout(
        new LC().fill().noVisualPadding(),
        new AC(),
        new AC().align("center")),
 ) {

    panel(
        id: 'centerPanel',
        constraints: 'w 100%, h 100%',
        layout: new MigLayout(),
    ) {

        scrollPane(
            id: 'panelScrollPane',
            constraints: 'w 100%, h 100%',
            opaque: false
        ) {

            panelScrollPane.getViewport().setOpaque(false)
            panelScrollPane.getViewport().setBorder(null)
            def v = new Vector()

            container(
                new GradientPanel(),
                id: 'panelPreview',
                background: Color.WHITE,
                layout: new MigLayout(
                        new LC().fillY().insets("0"),
                        new AC(),
                        new AC().align("center")),                
            ) {
                def listener = [ mouseClicked:
                                 {
                                     def location = panelScrollPane.getHorizontalScrollBar().getValue()
                                     width = panelScrollPane.getHorizontalScrollBar().getMaximum()-405
                                     if(!v.contains(width))
                                        v.add(width)
                                     i=0
                                     while(location>v[i])
                                     {
                                        i++
                                     }
                                     if(i == controller.view.thumbList.getSelectedIndex())
                                     {
                                         pos = panelPreview.getComponents()[i].getX()
                                         controller.animation.slideViewPositionTo(pos)
                                     }
                                     else
                                         controller.view.thumbList.setSelectedIndex(i)
                                 },
                                 mousePressed: {},
                                 mouseReleased: {},
                                 mouseEntered: {},
                                 mouseExited: {}, ] as MouseListener

                panelPreview.addMouseListener(listener)
                }


        }
    }
}