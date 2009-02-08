package views

import helpers.Positionning
import info.clearthought.layout.TableLayout
import java.awt.Color
import javax.swing.SwingUtilities
import javax.swing.UIManager
import static javax.swing.WindowConstants.EXIT_ON_CLOSE
import org.noos.xing.mydoggy.*
import org.noos.xing.mydoggy.plaf.MyDoggyToolWindowManager
import java.awt.event.WindowEvent
import java.awt.event.WindowListener
import org.noos.xing.mydoggy.plaf.ui.util.SwingUtil

def perspective = new Perspective(this)

actions
{
    action(
            id: 'restoreDefault',
            name: 'RestoreDefault',
            closure: perspective.&restoreDefault,
            mnemonic: 'D',
            accelerator: 'ctrl D',
            shortDescription: 'Restore default perspective'
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
                 text: 'Window',
                 mnemonic: 'W'
         ) {
                menuItem(restoreDefault)
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


/**
* Default Perspective handler
*
*/
class Perspective
{
    /**
    * Parent of the perspective handler
    *
    */
    def parent

    /**
    * The manager for Tool Windows
    *
    */
    def toolWindowManager


    /**
    * The manager for Contents
    *
    */
    def contentManager


    /**
    * Map of needed ToolBars
    *
    */
    def toolBars =
    [
        [
            "id": "Thumbs List",
            "anchor" : ToolWindowAnchor.LEFT,
            "icon" : "images/movie.png",
            "view" : ThumbListView,
            "settings" : "setThumbListProperties"
        ],
        [
            "id": "Quick Actions",
            "anchor" : ToolWindowAnchor.TOP,
            "icon" : "images/folder-option-insert.png",
            "view" : ProjectActionsView,
            "settings" : "setProjectActionsProperties"
        ],
        [
            "id": "Create Panel",
            "anchor" : ToolWindowAnchor.TOP,
            "icon" : "images/photo-option-add.png",
            "view" : AddPanelToolBarView,
            "settings" : "setCreatePanelProperties"
        ],
        [
            "id": "Panel Properties",
            "anchor" : ToolWindowAnchor.RIGHT,
            "icon" : "images/doc-option-edit.png",
            "view" : PanelPropertiesView,
            "settings" : "setPanelPropertiesProperties"
        ],

    ]


    /**
    * Map of needed Contents
    *
    */
    def contents =
    [
            [
                    "id" : "Panels Previews",
                    "icon" : "images/photo-multiple.png",
                    "view" : PanelPreviewView
            ]
    ]


    /**
    * Constructor
    *
    * @param    par   The parent object who uses the perspective
    */
    Perspective(par)
    {
        parent = par
        toolWindowManager = new MyDoggyToolWindowManager()
        contentManager = toolWindowManager.getContentManager()
        createToolBars()
        createContent()
    }

    /**
    * Create the ToolBars
    *
    */
    def createToolBars()
    {
        toolBars.each()
        {
            // Register a Tool.
            toolWindowManager.registerToolWindow(it.id,       // Id
                                               "",                 // Title
                                               SwingUtil.loadIcon(it.icon), // Icon
                                               parent.build(it.view),    // Component
                                               it.anchor)       // Anchor
                                                                                     
        }
    }

    /**
    * Create the Contents
    *
    */
    def createContent()
    {
        contents.each()
        {
            contentManager.addContent(
                                it.id,
                                it.id,
                                SwingUtil.loadIcon(it.icon),           // An icon
                                parent.build(it.view)
            )
        }
    }

    /**
    * Loads the perspective
    * If a perspective as already been saved, load it
    * Else, load default perspective
    *
    */
    def loadPerspective()
    {
        try
        {
            File workspaceFile = new File("workspace.xml");
            if (workspaceFile.exists())
            {
                FileInputStream inputStream = new FileInputStream("workspace.xml");
                toolWindowManager.getPersistenceDelegate().apply(inputStream);
                inputStream.close();
            }
            else
            {
                restoreDefault()
            }
        }
        catch (Exception e1)
        {
            e1.printStackTrace();
        }
    }


    /**
    * Save current perspective
    *
    */
    def savePerspective()
    {
        try
        {
            FileOutputStream output = new FileOutputStream("workspace.xml");
            toolWindowManager.getPersistenceDelegate().save(output);
            output.close();
        }
        catch (Exception e1)
        {
            e1.printStackTrace();
        }
    }

    /**
    * Restore the default Perspective
    *
    */
    def restoreDefault()
    {
        def tool
        def method

        toolBars.each()
        {
            tool = toolWindowManager.getToolWindow(it.id)
            tool.setAnchor(it.anchor)
            setDefaultProperties(tool)
            method = this.class.getMethod(it.settings, (Class[]) [ToolWindow])
            method.invoke(this, tool)
            tool.setVisible(true)
            tool.setAvailable(true)
        }
    }

    /**
    * Restore the default perspective (called by action with event arg)
    *
    */
    def restoreDefault(e)
    {
        restoreDefault()
    }



    /**
    * Set default properties for ToolBars
    *
    * @param    toolWindow   The Toolwindow which will get the new settings
    */
    def setDefaultProperties(ToolWindow toolwindow)
    {
        toolwindow.setType(ToolWindowType.DOCKED)
        DockedTypeDescriptor dockedTypeDescriptor = (DockedTypeDescriptor) toolwindow.getTypeDescriptor(ToolWindowType.DOCKED)
        dockedTypeDescriptor.setAnimating(true)
        dockedTypeDescriptor.setPreviewEnabled(true)
        dockedTypeDescriptor.setPreviewDelay(1000)
        dockedTypeDescriptor.setPreviewTransparentRatio(0.9f)

        SlidingTypeDescriptor slidingTypeDescriptor = (SlidingTypeDescriptor) toolwindow.getTypeDescriptor(ToolWindowType.SLIDING)
        slidingTypeDescriptor.setEnabled(true)

        FloatingTypeDescriptor floatingTypeDescriptor = (FloatingTypeDescriptor) toolwindow.getTypeDescriptor(ToolWindowType.FLOATING)
        floatingTypeDescriptor.setEnabled(true)

        floatingTypeDescriptor.setModal(false)
        floatingTypeDescriptor.setTransparentMode(true)
        floatingTypeDescriptor.setTransparentRatio(0.2f)
        floatingTypeDescriptor.setTransparentDelay(1000)
        floatingTypeDescriptor.setAnimating(true)
    }


    /**
    * Set default properties for ThumbList ToolBar
    *
    * @param    toolWindow   The Toolwindow which will get the new settings
    */
    def setThumbListProperties(ToolWindow toolwindow)
    {
        DockedTypeDescriptor dockedTypeDescriptor = (DockedTypeDescriptor) toolwindow.getTypeDescriptor(ToolWindowType.DOCKED)
        dockedTypeDescriptor.setDockLength(175)
        dockedTypeDescriptor.setMinimumDockLength(175)
        def pos = Positionning.CenterPosition([179,500])
        FloatingTypeDescriptor floatingTypeDescriptor = (FloatingTypeDescriptor) toolwindow.getTypeDescriptor(ToolWindowType.FLOATING)
        floatingTypeDescriptor.setLocation((int)pos[0], (int)pos[1])
        floatingTypeDescriptor.setSize(179,400)
    }


    /**
    * Set default properties for ActionProperties ToolBar
    *
    * @param    toolWindow   The Toolwindow which will get the new settings
    */
    def setProjectActionsProperties(ToolWindow toolwindow)
    {
        DockedTypeDescriptor dockedTypeDescriptor = (DockedTypeDescriptor) toolwindow.getTypeDescriptor(ToolWindowType.DOCKED)
        dockedTypeDescriptor.setDockLength(127)
        dockedTypeDescriptor.setMinimumDockLength(127)
        def pos = Positionning.CenterPosition([500,140])
        FloatingTypeDescriptor floatingTypeDescriptor = (FloatingTypeDescriptor) toolwindow.getTypeDescriptor(ToolWindowType.FLOATING)
        floatingTypeDescriptor.setLocation((int)pos[0], (int)pos[1])
        floatingTypeDescriptor.setSize(500,140)
    }

    /**
    * Set default properties for  CreatePanels ToolBar
    *
    * @param    toolWindow   The Toolwindow which will get the new settings
    */
    def setCreatePanelProperties(ToolWindow toolwindow)
    {

        DockedTypeDescriptor dockedTypeDescriptor = (DockedTypeDescriptor) toolwindow.getTypeDescriptor(ToolWindowType.DOCKED)
        dockedTypeDescriptor.setDockLength(127)
        dockedTypeDescriptor.setMinimumDockLength(127)
        def pos = Positionning.CenterPosition([500,140])
        FloatingTypeDescriptor floatingTypeDescriptor = (FloatingTypeDescriptor) toolwindow.getTypeDescriptor(ToolWindowType.FLOATING)
        floatingTypeDescriptor.setLocation((int)pos[0], (int)pos[1])
        floatingTypeDescriptor.setSize(500,140)
    }


    /**
    * Set default properties for PanelProperties ToolBar
    *
    * @param    toolWindow   The Toolwindow which will get the new settings
    */
    def setPanelPropertiesProperties(ToolWindow toolwindow)
    {

    }

}


