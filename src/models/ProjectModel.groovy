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

/**
 * Project Model containing all global vars needed for a project
 *
 */
class ProjectModel {

    def installerframe

    def width

    def height

    ProjectModel()
    {
        installerframe = new InstallerFrame()
        load()
        width = 800
        height = 600
    }

    def getSize()
    {
        return new Dimension(width, height)
    }


    def getInstallerframe()
    {
        return installerframe
    }    


    def addInfos()
    {
        installerframe.info.setAppName("Test")
        installerframe.info.setAppVersion("1.0")
        installerframe.info.addAuthor(new Author("Alexis Plantin", "alexis@roux.com"))
        installerframe.info.addAuthor(new Author("Thomas Maurel", "thomas@roux.com"))
        installerframe.info.setAppURL("http://www.roux.com")
        installerframe.installdata.info = installerframe.info
    }

    def addLangPack()
    {
        installerframe.installdata.xmlData.setAttribute("langpack", "fra")
        installerframe.installdata.localeISO3 = "fra"

        def test = new File('../bin/langpacks/installer/fra.xml')

        InputStream ine = new FileInputStream(test.getAbsolutePath())
        installerframe.installdata.langpack = new LocaleDatabase(ine)
        installerframe.langpack = installerframe.installdata.langpack
    }

    def addGUIPrefs()
    {
        def prefs = new GUIPrefs()
        prefs.width = 800;
        prefs.height = 600;
        prefs.resizable = false;
        installerframe.installdata.guiPrefs = prefs
    }

    def addResourceManager()
    {
        ResourceManager.create(installerframe.installdata)
    }

    private void addIcons() throws Exception
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


    def load()
    {
        addInfos()
        addLangPack()
        addGUIPrefs()
        addIcons()
        addResourceManager()
    }

}