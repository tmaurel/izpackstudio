package views

import helpers.Positionning
import info.clearthought.layout.TableLayout
import java.awt.Color
import javax.swing.SwingUtilities
import javax.swing.UIManager
import static javax.swing.WindowConstants.EXIT_ON_CLOSE
import java.awt.event.WindowEvent
import java.awt.event.WindowListener

def perspective = new Perspective(this)

actions
{
    action(
            id: 'restoreDefault',
            name: 'Restore perspective',
            closure: perspective.&restoreDefault,
            mnemonic: 'R',
            accelerator: 'ctrl R',
            shortDescription: 'Restore default perspective',
            smallIcon: imageIcon(resource:"/images/photo-multiple.png")
    )
	
	action(
		id: 'toggleProjectSettings',
		name: 'Toggle Project Settings',
		closure: perspective.&toggleProjectSettings,
		mnemonic: 'P',
		accelerator: 'ctrl P',
		shortDescription: 'toggle project settings'
)
}



frame(
        id: 'mainFrame',
        title: 'IzPack Studio',
        location: [0, 0],
        size: Positionning.getFullScreenSize(),
        defaultCloseOperation: EXIT_ON_CLOSE,
        background: Color.WHITE,
        layout: new TableLayout((double[][]) [[0, -1, 0], [0, -1, 0]])
) {

     mainFrame.setExtendedState(mainFrame.getExtendedState() | mainFrame.MAXIMIZED_BOTH)

     UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName())
     SwingUtilities.updateComponentTreeUI(mainFrame)

     menuBar(
             id: 'studioMenu'
     ) {
          menu(
                 text: 'File',
                 mnemonic: 'F'
         ) { }
          menu(
                 text: 'Edit',
                 mnemonic: 'E'
         ) {}
         menu(
                 text: 'Window',
                 mnemonic: 'W'
         ) {
                menuItem(restoreDefault)
				menuItem(toggleProjectSettings)
         }
     }


     mainFrame.getContentPane().add(perspective.toolWindowManager, "1,1,")

     mainFrame.addWindowListener( { e ->
        switch (e.getID())
        {
            case WindowEvent.WINDOW_OPENED:
                perspective.loadPerspective()
            break

            case WindowEvent.WINDOW_CLOSING:
                perspective.savePerspective()
            break
        }
    } as WindowListener)
}
