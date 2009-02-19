package views

import org.noos.xing.mydoggy.plaf.MyDoggyToolWindowManager
import org.noos.xing.mydoggy.ToolWindow
import org.noos.xing.mydoggy.DockedTypeDescriptor
import org.noos.xing.mydoggy.FloatingTypeDescriptor
import org.noos.xing.mydoggy.SlidingTypeDescriptor
import org.noos.xing.mydoggy.ToolWindowAnchor
import org.noos.xing.mydoggy.plaf.ui.util.SwingUtil
import org.noos.xing.mydoggy.ToolWindowType
import helpers.Positionning

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

            setDefaultProperties(toolWindowManager.getToolWindow(it.id))
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

    def toggleProjectSettings()                                              //TEMPORAIRE
    {
        parent.controller.ps.show()
    }

    def toggleProjectSettings(e)                                             //TEMPORAIRE
    {
        toggleProjectSettings()
    }	
	
    /**
    * Set default properties for ToolBars
    *
    * @param    toolWindow   The Toolwindow which will get the new settings
    */
    def setDefaultProperties(ToolWindow toolwindow)
    {
        toolwindow.setIndex(-1)          
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
        dockedTypeDescriptor.setDockLength(115)
        dockedTypeDescriptor.setMinimumDockLength(115)
        def pos = Positionning.CenterPosition([495,300])
        FloatingTypeDescriptor floatingTypeDescriptor = (FloatingTypeDescriptor) toolwindow.getTypeDescriptor(ToolWindowType.FLOATING)
        floatingTypeDescriptor.setLocation((int)pos[0], (int)pos[1])
        floatingTypeDescriptor.setSize(495,300)
    }

    /**
    * Set default properties for  CreatePanels ToolBar
    *
    * @param    toolWindow   The Toolwindow which will get the new settings
    */
    def setCreatePanelProperties(ToolWindow toolwindow)
    {

        DockedTypeDescriptor dockedTypeDescriptor = (DockedTypeDescriptor) toolwindow.getTypeDescriptor(ToolWindowType.DOCKED)
        dockedTypeDescriptor.setDockLength(115)
        dockedTypeDescriptor.setMinimumDockLength(115)
        def pos = Positionning.CenterPosition([495,300])
        FloatingTypeDescriptor floatingTypeDescriptor = (FloatingTypeDescriptor) toolwindow.getTypeDescriptor(ToolWindowType.FLOATING)
        floatingTypeDescriptor.setLocation((int)pos[0], (int)pos[1])
        floatingTypeDescriptor.setSize(495,300)
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