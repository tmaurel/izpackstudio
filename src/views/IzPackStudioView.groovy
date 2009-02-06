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


     menuBar(id: 'studioMenu') { menu(text: 'Menu')}

    def toolWindowManager

    // Create a new instance of MyDoggyToolWindowManager passing the frame.
    MyDoggyToolWindowManager myDoggyToolWindowManager = new MyDoggyToolWindowManager()
    toolWindowManager = myDoggyToolWindowManager

    // Register a Tool.
    toolWindowManager.registerToolWindow("Thumbs List",       // Id
                                       "",                 // Title
                                       null,                         // Icon
                                       build(ThumbListView),    // Component
                                       ToolWindowAnchor.LEFT)       // Anchor

    ToolWindow thumbListView = toolWindowManager.getToolWindow("Thumbs List")
    setThumbListProperties(thumbListView)

    toolWindowManager.registerToolWindow("Quick Actions",       // Id
                                       "",                 // Title
                                       null,                         // Icon
                                       build(ProjectActionsView),    // Component
                                       ToolWindowAnchor.TOP)       // Anchor

    ToolWindow projectActions = toolWindowManager.getToolWindow("Quick Actions")
    setProjectActionsProperties(projectActions)

    toolWindowManager.registerToolWindow("Create Panel",       // Id
                                       "",                 // Title
                                       null,                         // Icon
                                       build(AddPanelToolBarView),    // Component
                                       ToolWindowAnchor.TOP)       // Anchor

    ToolWindow createPanel = toolWindowManager.getToolWindow("Create Panel")
    setCreatePanelProperties(createPanel)

    toolWindowManager.registerToolWindow("Panel Properties",       // Id
                                       "",                 // Title
                                       null,                         // Icon
                                       build(PanelPropertiesView),    // Component
                                       ToolWindowAnchor.RIGHT)       // Anchor

    ToolWindow panelProperties = toolWindowManager.getToolWindow("Panel Properties")

    setPanelPropertiesProperties(panelProperties)

    ContentManager contentManager = toolWindowManager.getContentManager()
    contentManager.addContent(
                        "Panels Previews",
                        "Panel Previews",
                        null,           // An icon
                        build(PanelPreviewView)
    )

    mainFrame.getContentPane().add(myDoggyToolWindowManager, "1,1,")

    mainFrame.addWindowListener( { e ->
        switch (e.getID())
        {
            case WindowEvent.WINDOW_OPENED:
                
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
                        for (ToolWindow window : toolWindowManager.getToolWindows())
                        {
                            setDefaultProperties(window)
                            window.setVisible(true)
                            window.setAvailable(true)                         
                        }
                    }
                }
                catch (Exception e1)
                {
                    e1.printStackTrace();
                }

            break

            case WindowEvent.WINDOW_CLOSING:

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

            break

        }

    } as WindowListener)




}

def setDefaultProperties(toolwindow)
{
    DockedTypeDescriptor dockedTypeDescriptor = (DockedTypeDescriptor) toolwindow.getTypeDescriptor(ToolWindowType.DOCKED)
    dockedTypeDescriptor.setAnimating(true)
    dockedTypeDescriptor.setPreviewEnabled(true)
    dockedTypeDescriptor.setPreviewDelay(1000)
    dockedTypeDescriptor.setPreviewTransparentRatio(0.9f)

    SlidingTypeDescriptor slidingTypeDescriptor = (SlidingTypeDescriptor) toolwindow.getTypeDescriptor(ToolWindowType.SLIDING)
    slidingTypeDescriptor.setEnabled(false)

    FloatingTypeDescriptor floatingTypeDescriptor = (FloatingTypeDescriptor) toolwindow.getTypeDescriptor(ToolWindowType.FLOATING)
    floatingTypeDescriptor.setEnabled(true)

    floatingTypeDescriptor.setModal(false)
    floatingTypeDescriptor.setTransparentMode(true)
    floatingTypeDescriptor.setTransparentRatio(0.2f)
    floatingTypeDescriptor.setTransparentDelay(1000)
    floatingTypeDescriptor.setAnimating(true)    
}

def setThumbListProperties(toolwindow)
{
    DockedTypeDescriptor dockedTypeDescriptor = (DockedTypeDescriptor) toolwindow.getTypeDescriptor(ToolWindowType.DOCKED)
    dockedTypeDescriptor.setDockLength(175)
    dockedTypeDescriptor.setMinimumDockLength(175)
    def pos = Positionning.CenterPosition([179,500])
    FloatingTypeDescriptor floatingTypeDescriptor = (FloatingTypeDescriptor) toolwindow.getTypeDescriptor(ToolWindowType.FLOATING)
    floatingTypeDescriptor.setLocation((int)pos[0], (int)pos[1])
    floatingTypeDescriptor.setSize(179,400)
}

def setProjectActionsProperties(toolwindow)
{
    DockedTypeDescriptor dockedTypeDescriptor = (DockedTypeDescriptor) toolwindow.getTypeDescriptor(ToolWindowType.DOCKED)
    dockedTypeDescriptor.setDockLength(136)
    dockedTypeDescriptor.setMinimumDockLength(136)
    def pos = Positionning.CenterPosition([500,140])
    FloatingTypeDescriptor floatingTypeDescriptor = (FloatingTypeDescriptor) toolwindow.getTypeDescriptor(ToolWindowType.FLOATING)
    floatingTypeDescriptor.setLocation((int)pos[0], (int)pos[1])
    floatingTypeDescriptor.setSize(500,140)
}


def setCreatePanelProperties(toolwindow)
{
    DockedTypeDescriptor dockedTypeDescriptor = (DockedTypeDescriptor) toolwindow.getTypeDescriptor(ToolWindowType.DOCKED)
    dockedTypeDescriptor.setDockLength(136)
    dockedTypeDescriptor.setMinimumDockLength(136)
    def pos = Positionning.CenterPosition([500,140])
    FloatingTypeDescriptor floatingTypeDescriptor = (FloatingTypeDescriptor) toolwindow.getTypeDescriptor(ToolWindowType.FLOATING)
    floatingTypeDescriptor.setLocation((int)pos[0], (int)pos[1])
    floatingTypeDescriptor.setSize(500,140)
}


def setPanelPropertiesProperties(toolwindow)
{

}


