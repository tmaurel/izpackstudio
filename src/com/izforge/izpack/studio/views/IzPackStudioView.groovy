package com.izforge.izpack.studio.views

import com.izforge.izpack.studio.helpers.Positionning
import info.clearthought.layout.TableLayout
import java.awt.Color
import javax.swing.SwingUtilities
import javax.swing.UIManager
import java.awt.event.WindowEvent
import java.awt.event.WindowListener
import javax.swing.WindowConstants


/**
 * Building GUI
 *
 */
frame(
        id: 'mainFrame',
        title: 'IzPack Studio',
        location: [0, 0],
        size: Positionning.getFullScreenSize(),
        defaultCloseOperation: WindowConstants.DO_NOTHING_ON_CLOSE,
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
        ) {
            menuItem(newProject)
            menuItem(loadProject)
            menuItem(closeProject)
            menuItem(saveProject)
            menuItem(toggleProjectSettings)
            menuItem(buildProject)
            menuItem(exit)
        }
        menu(
             text: 'Edit',
             mnemonic: 'E'
        ) {
            menuItem(deletePanel)
        }
        menu(
             text: 'Insert',
             mnemonic: 'I'
        ) {
            menuItem(addHelloPanel)
            menuItem(addInfoPanel)
            menuItem(addLicencePanel)
            menuItem(addPathInputPanel)
            menuItem(addTargetPanel)
            menuItem(addPacksPanel)
            menuItem(addSummaryPanel)
            menuItem(addInstallPanel)
            menuItem(addFinishPanel)
        }
        menu(
             text: 'Window',
             mnemonic: 'W'
        ) {
            menuItem(restoreDefault)
        }
     }


     mainFrame.getContentPane().add(controller.perspective.toolWindowManager, "1,1,")

     mainFrame.addWindowListener( { e ->
        switch (e.getID())
        {
            case WindowEvent.WINDOW_OPENED:
                controller.perspective.loadPerspective()
            break

            case WindowEvent.WINDOW_CLOSING:
                controller.perspective.savePerspective()
                exit.actionPerformed()
            break
        }
    } as WindowListener)
}
