package views

import static javax.swing.WindowConstants.EXIT_ON_CLOSE
import helpers.*
import net.miginfocom.swing.MigLayout
import java.awt.Color
import java.awt.BorderLayout




frame(
        id: 'mainFrame',
        title: 'IzPack Studio',
        location: [0, 0],
        size: Positionning.getFullScreenSize(),
        defaultCloseOperation: EXIT_ON_CLOSE,
        background: Color.WHITE,
        layout: new MigLayout(),
) {
     mainFrame.setExtendedState(mainFrame.getExtendedState() | mainFrame.MAXIMIZED_BOTH); 

     menuBar(id: 'studioMenu') { menu(text: 'Menu')}


     panel(
        id: 'globalPanel',
        constraints:'span, w 100%, h 100%',
        layout: new BorderLayout()
     ) {
        build(ThumbListView)
        build(AddPanelToolBarView)
        build(PanelPreviewView)
        build(PanelPropertiesView)
    }

}