package models

import com.izforge.izpack.GUIPrefs
import com.izforge.izpack.Info
import com.izforge.izpack.Info.Author
import com.izforge.izpack.LocaleDatabase
import com.izforge.izpack.gui.ButtonFactory
import com.izforge.izpack.gui.LabelFactory
import com.izforge.izpack.installer.InstallData
import com.izforge.izpack.installer.InstallerBase
import com.izforge.izpack.installer.InstallerFrame
import com.izforge.izpack.util.AbstractUIProgressHandler
import java.awt.Dimension
import views.PacksTreeTableModel
import org.jdesktop.swingx.treetable.DefaultMutableTreeTableNode

class ProjectModel extends InstallerFrame {

    /**
    * Container for the panel controllers
    *
    */
    private panels = []

    def prefs

    def info

    def availableLangPacks = []

    def selectedLangPacks = []

    def fileName

    def packs

    ProjectModel()
    {
        super("", new InstallData(), new InstallerBase())
        info = new Info()
        installdata.info = info
        prefs = new GUIPrefs()
        installdata.guiPrefs = prefs
        loadLangPacks()
        if(availableLangPacks.contains("eng"))
        {
          setLangPack("eng")
        }
        addGUIPrefs()
        installdata.installSuccess = true
        fileName = "install.xml"
        packs = new PacksTreeTableModel(new DefaultMutableTreeTableNode("Installer"), ["Pack", "Behaviour", "Description"])
    }

    public setDefaults()
    {
        prefs.width = 600;
        prefs.height = 400;
        prefs.resizable = false
        selectedLangPacks = availableLangPacks.clone()
    }

    @Override
    protected void loadPanels() throws Exception{}

    @Override
    protected void buildGUI() {}

    @Override
    protected void showFrame() {}

    @Override
    protected void switchPanel(int last) {}

    public Dimension getPanelsContainerSize()
    {
        return getSize();
    }

    public InputStream getResource(String a)
    {
        return getClass().getResourceAsStream("/");
    }

    public void skipPanel() {}

    public void lockPrevButton() {}

    public void lockNextButton() {}

    public void unlockPrevButton() {}

    public void unlockNextButton() {}

    public void setQuitButtonText(String text) {}

    public void setQuitButtonIcon(String iconName) {}

    public void install(AbstractUIProgressHandler listener) {}

    public Dimension getSize()
    {
        return new Dimension(installdata.guiPrefs.width, installdata.guiPrefs.height)
    }

    private setLangPack(lang)
    {
        installdata.xmlData.setAttribute("langpack", lang)
        installdata.localeISO3 = lang

        def test = new File('../bin/langpacks/installer/' + lang + '.xml')

        InputStream ine = new FileInputStream(test.getAbsolutePath())
        installdata.langpack = new LocaleDatabase(ine)
        langpack = installdata.langpack
    }

    private addGUIPrefs()
    {
        prefs.modifier.put("useButtonIcons", "yes")
        prefs.modifier.put("useLabelIcons", "yes")

        boolean useButtonIcons = true;
        if (installdata.guiPrefs.modifier.containsKey("useButtonIcons")
                && "no".equalsIgnoreCase(installdata.guiPrefs.modifier
                .get("useButtonIcons")))
        {
            useButtonIcons = false;
        }
        ButtonFactory.useButtonIcons(useButtonIcons)

        boolean useLabelIcons = true;
        if (installdata.guiPrefs.modifier.containsKey("useLabelIcons")
                && "no".equalsIgnoreCase(installdata.guiPrefs.modifier
                .get("useLabelIcons")))
        {
            useLabelIcons = false;
        }
        LabelFactory.setUseLabelIcons(useLabelIcons);
    }

    public setInfos(authors, appName, appVersion, appURL)
    {
        def newInfo = new Info()
        newInfo.setAppName(appName)
        newInfo.setAppVersion(appVersion)
        authors.each
        {
            newInfo.addAuthor(new Author(it.name, it.email))
        }
        newInfo.setAppURL(appURL)
        info = newInfo
        installdata.info = info
    }

    public setPrefs(width, height, resizable)
    {
        prefs.width = width
        prefs.height = height
        prefs.resizable = resizable
    }

    public setLangs(appLangs)
    {
        selectedLangPacks.clear()
        appLangs.each
        {
            selectedLangPacks.add(availableLangPacks[it])
        }
    }

    public getSelectedLangPacksIndices()
    {
        def selected = []
        selectedLangPacks.each
        {
            selected.add(availableLangPacks.indexOf(it))
        }
        return selected
    }

    public loadLangPacks()
    {
        def langPacks = new File('../bin/langpacks/installer/').listFiles(
                            {
                              dir, file-> file ==~ /.*?\.xml/
                            } as FilenameFilter
                        ).toList()*.name

        langPacks.each
        {
            it = it.substring(0, it.lastIndexOf("."))
            availableLangPacks.add(it)
        }

    }

    public clean()
    {
        //panels = []
        selectedLangPacks.clear()
        setInfos([],"","","")
    }

}