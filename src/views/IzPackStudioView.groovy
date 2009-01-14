package views

import static javax.swing.WindowConstants.EXIT_ON_CLOSE
import helpers.*
import net.miginfocom.swing.MigLayout
import javax.swing.BorderFactory
import java.awt.Color
import net.miginfocom.layout.AC
import net.miginfocom.layout.LC


frame(
        id: 'mainFrame',
        title: 'IzPack Studio',
        location: [0, 0],
        size: Positionning.getFullScreenSize(),
        defaultCloseOperation: EXIT_ON_CLOSE,
        background: Color.WHITE,
        layout: new MigLayout()
) {
     panel(
        id: 'topPanel',
        border: BorderFactory.createMatteBorder(1,1,1,1,Color.RED),
        constraints:'span, w 100%, h 15%'
     ) {}

     panel(
        border: BorderFactory.createMatteBorder(1,1,1,1,Color.RED),
        id: 'leftPanel',
        constraints: 'w 20%, h 70%'
     ) {}

     panel(
        id: 'centerPanel',
        constraints: 'w 60%, h 70%',
        background: Color.WHITE,
        layout: new MigLayout()
     ) {
        scrollPane(
                id: 'panelScrollPane',
                constraints: 'w 100%, h 100%',
                border: BorderFactory.createEmptyBorder(),
        ) {
           panel(
                   id: 'panelPreview',
                   background: Color.BLUE,
                   layout: new MigLayout(
                           new LC().fillX().fillY(),
                           new AC().align("center"),
                           new AC().align("center"))
           ) {}
        }

     }
     panel(
        id: 'rightPanel',
        border: BorderFactory.createMatteBorder(1,1,1,1,Color.RED),
        constraints:'wrap, w 20%, h 70%'
     ) {}

     panel(
        id: 'bottomPanel',
        border: BorderFactory.createMatteBorder(1,1,1,1,Color.RED),
        constraints:'span, w 100%, h 15%'
     ) {}

}