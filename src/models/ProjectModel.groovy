package models

import com.izforge.izpack.installer.InstallerFrame
import com.izforge.izpack.Info.Author
import com.izforge.izpack.LocaleDatabase
import com.izforge.izpack.GUIPrefs
import com.izforge.izpack.installer.ResourceManager
import java.io.File
import com.izforge.izpack.gui.IconsDatabase
import net.n3.nanoxml.*
import javax.swing.ImageIcon
import java.awt.Dimension
import com.izforge.izpack.gui.ButtonFactory
import com.izforge.izpack.gui.LabelFactory

/**
 * Project Model containing all global vars needed for a project
 *
 */
class ProjectModel {

    /**
    * Container for the panel controllers
    *
    */
    private panels = []

    private installerframe

    private width

    private height

    ProjectModel()
    {
        installerframe = new InstallerFrame()
        load()
        width = 600
        height = 450
    }

    public getSize()
    {
        return new Dimension(width, height)
    }

    
    public getInstallerFrame()
    {
        return installerframe
    }    


    private addInfos()
    {
        installerframe.info.setAppName("IzPackStudio")
        installerframe.info.setAppVersion("0.1")
        installerframe.info.addAuthor(new Author("Alexis Plantin", "alexis@izs.com"))
        installerframe.info.addAuthor(new Author("Thomas Maurel", "thomas@izs.com"))
        installerframe.info.addAuthor(new Author("Mickael Lasnes", "mickael@izs.com"))
        installerframe.info.addAuthor(new Author("Samuel Djian", "samuel@izs.com"))
        installerframe.info.setAppURL("http://www.izpack.org")
        installerframe.installdata.info = installerframe.info
    }

    private addLangPack()
    {
        installerframe.installdata.xmlData.setAttribute("langpack", "fra")
        installerframe.installdata.localeISO3 = "fra"

        def test = new File('../bin/langpacks/installer/fra.xml')

        InputStream ine = new FileInputStream(test.getAbsolutePath())
        installerframe.installdata.langpack = new LocaleDatabase(ine)
        installerframe.langpack = installerframe.installdata.langpack
    }

    private addGUIPrefs()
    {
        def prefs = new GUIPrefs()
        prefs.width = 800;
        prefs.height = 600;
        prefs.resizable = false;
        prefs.modifier.put("useButtonIcons", "yes")
        prefs.modifier.put("useLabelIcons", "yes")
        installerframe.installdata.guiPrefs = prefs

        boolean useButtonIcons = true;
        if (installerframe.installdata.guiPrefs.modifier.containsKey("useButtonIcons")
                && "no".equalsIgnoreCase(installerframe.installdata.guiPrefs.modifier
                .get("useButtonIcons")))
        {
            useButtonIcons = false;
        }
        ButtonFactory.useButtonIcons(useButtonIcons)

        boolean useLabelIcons = true;
        if (installerframe.installdata.guiPrefs.modifier.containsKey("useLabelIcons")
                && "no".equalsIgnoreCase(installerframe.installdata.guiPrefs.modifier
                .get("useLabelIcons")))
        {
            useLabelIcons = false;
        }
        LabelFactory.setUseLabelIcons(useLabelIcons);


    }

    private addResourceManager()
    {
        ResourceManager.create(installerframe.installdata)
    }

    private addIcons() throws Exception
    {
        // Initialisations
        installerframe.icons = new IconsDatabase()
        URL url
        ImageIcon img
        XMLElement icon

        InputStream inXML = InstallerFrame.class
                .getResourceAsStream("/com/izforge/izpack/installer/icons.xml")

        // Initialises the parser
        StdXMLParser parser = new StdXMLParser()
        parser.setBuilder(XMLBuilderFactory.createXMLBuilder())
        parser.setReader(new StdXMLReader(inXML))
        parser.setValidator(new NonValidator())

        // We get the data
        XMLElement data = (XMLElement) parser.parse()

        // We load the icons
        Vector<XMLElement> children = data.getChildrenNamed("icon")
        int size = children.size()
        for (int i = 0; i < size; i++)
        {
            icon = children.get(i)
            url = InstallerFrame.class.getResource(icon.getAttribute("res"))
            img = new ImageIcon(url)
            installerframe.icons.put(icon.getAttribute("id"), img)
        }


    }


    private load()
    {
        addInfos()
        addLangPack()
        addGUIPrefs()
        addIcons()
        addResourceManager()
        installerframe.createNavPanel()
        installerframe.installdata.installSuccess = true
    }

}