package com.izforge.izpack.studio.views

import net.miginfocom.layout.AC
import net.miginfocom.swing.MigLayout
import net.miginfocom.layout.LC
import java.awt.Color
import java.awt.event.MouseListener
import java.awt.event.ComponentListener

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
         def sizeListener =
        [
                componentHidden: {},
                componentMoved: {},
                componentResized:
                {
                    if(controller.loaded && controller.project.getPanels().size>0 && controller.view.thumbList.getSelectedIndex() != -1)
                    {
                        doLater{
                            controller.slideTo(controller.view.thumbList.getSelectedIndex())
                        }
                    }
                },
                componentShown: {}
        ] as ComponentListener

        centerPanel.addComponentListener(sizeListener)
        scrollPane(
            id: 'panelScrollPane',
            constraints: 'w 100%, h 100%',
            opaque: false
        ) {

            def vp = panelScrollPane.getViewport()
            vp.setOpaque(false)
            vp.setBorder(null)

            def sBListener =
            [
                mouseClicked: {},
                mousePressed:
                {
                    controller.breakAnimation()
                },
                mouseReleased: {},
                mouseEntered: {},
                mouseExited: {},
            ] as MouseListener


            panelScrollPane.getHorizontalScrollBar().addMouseListener(sBListener)


            container(
                new GradientContainer((float) 0.5, Color.white, new Color(210,220,230)),
                layout: new MigLayout(
                        new LC().fillY().insets("0"),
                        new AC().gap("0"),
                        new AC().align("center")),
            ) {
                panel(
                id: 'panelPreview',
                background: Color.WHITE,
                opaque: false,
                layout: new MigLayout(
                        new LC().fillY().insets("0"),
                        new AC().gap("0"),
                        new AC().align("center")),
                ) {

                    def listener =
                    [
                        mouseClicked: {},
                        mousePressed: {},
                        mouseReleased:
                        {
                            controller.slideOnPosition(it.getX())
                        },
                        mouseEntered: {},
                        mouseExited: {},
                    ] as MouseListener

                    panelPreview.addMouseListener(listener)
                }

            }
        }
    }
}