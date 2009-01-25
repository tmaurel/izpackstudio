package views

import static javax.swing.WindowConstants.EXIT_ON_CLOSE
import helpers.*
import net.miginfocom.swing.MigLayout
import java.awt.Color
import javax.swing.UIManager
import javax.swing.SwingUtilities


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

     UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
     SwingUtilities.updateComponentTreeUI(mainFrame);


     menuBar(id: 'studioMenu') { menu(text: 'Menu')}


     panel(
        id: 'globalPanel',
        constraints:'span, w 100%, h 100%',
        layout: new MigLayout('fill')
     ) {
        build(AddPanelToolBarView)
        build(ThumbListView)
        build(PanelPreviewView)
        build(PanelPropertiesView)

    }

}