package views

import static javax.swing.WindowConstants.EXIT_ON_CLOSE
import helpers.*
import net.miginfocom.swing.MigLayout
import javax.swing.BorderFactory
import java.awt.Color
import net.miginfocom.layout.AC
import net.miginfocom.layout.LC
import controllers.HelloPanelController
import models.PanelModel
import controllers.FinishPanelController



frame(
        id: 'mainFrame',
        title: 'IzPack Studio',
        location: [0, 0],
        size: Positionning.getFullScreenSize(),
        defaultCloseOperation: EXIT_ON_CLOSE,
        background: Color.WHITE,
        layout: new MigLayout(
                           new LC().fill().noVisualPadding(),
                           new AC().gap("0"),
                           new AC().align("center")),
) {
     panel(
        id: 'topPanel',
        constraints:'span, w 100%, h 15%'
     ) {

        button(
            id: 'addHelloPanel',
            text: 'Add HelloPanel',
            actionPerformed: {
                def hello = new HelloPanelController(new PanelModel(), null, controller.project)
                hello.start()
                controller.project.addPanel(hello)            
            }
        )


        button(
            id: 'addFinishPanel',
            text: 'Add FinishPanel',
            actionPerformed: {
                def finish = new FinishPanelController(new PanelModel(), null, controller.project)
                finish.start()
                controller.project.addPanel(finish)
            }
        )
     }

     panel(
        id: 'leftPanel',
        layout: new MigLayout("fill"),
        border: BorderFactory.createMatteBorder(1,1,1,0,Color.lightGray),
        constraints: 'w 176px, h 70%'

     ) {
        build(ThumbListView)
     }

     panel(
        id: 'centerPanel',
        constraints: 'grow, w 60%, h 70%',
        background: Color.WHITE,
        border: BorderFactory.createMatteBorder(1,1,1,1,Color.lightGray),
        layout: new MigLayout()
     ) {
        scrollPane(
                id: 'panelScrollPane',
                constraints: 'w 100%, h 100%',
                border: BorderFactory.createEmptyBorder(),
        ) {

           panel(
                   id: 'panelPreview',
                   background: Color.WHITE,
                   layout: new MigLayout(
                           new LC().fillY(),
                           new AC().gap("0"),
                           new AC().align("center"))
           ) { }
        }

     }
     panel(
        id: 'rightPanel',
        constraints:'wrap, w 20%, h 70%'
     ) {}

     panel(
        id: 'bottomPanel',
        constraints:'span, w 100%, h 15%'
     ) {}

}