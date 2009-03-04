package models

import com.izforge.izpack.GUIPrefs
import com.izforge.izpack.Info
import com.izforge.izpack.Info.Author
import com.izforge.izpack.LocaleDatabase
import com.izforge.izpack.gui.ButtonFactory
import com.izforge.izpack.gui.EtchedLineBorder
import com.izforge.izpack.gui.LabelFactory
import com.izforge.izpack.installer.InstallData
import com.izforge.izpack.installer.InstallerBase
import com.izforge.izpack.installer.InstallerFrame
import com.izforge.izpack.util.AbstractUIProgressHandler
import groovy.swing.SwingBuilder
import java.awt.Dimension
import java.awt.Font
import javax.swing.BorderFactory
import javax.swing.BoxLayout
import javax.swing.JPanel
import javax.swing.border.TitledBorder
import groovy.beans.Bindable

class ProjectModel extends InstallerFrame {

    /**
    * Container for the panel controllers
    *
    */
    private panels = []

    def prefs

    def info

    ProjectModel()
    {
        super("", new InstallData(), new InstallerBase())
        info = new Info()
        installdata.info = info
        prefs = new GUIPrefs()
        installdata.guiPrefs = prefs
        addInfos()
        setLangPack("fra")
        addGUIPrefs()
        installdata.installSuccess = true
        setDefaults()
    }

    public setDefaults()
    {
        prefs.width = 600;
        prefs.height = 400;
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


    /**
    * Create the Navigation Panel of the Installer Frame
    *
    * @return The navigation Panel
    */
    public JPanel createNavPanel()
    {
        def swing = new SwingBuilder()
        def navPanel = swing.panel(
            id: 'navPanel',
            border: BorderFactory.createCompoundBorder(
                            BorderFactory.createEmptyBorder(8, 8, 8, 8),
                            BorderFactory.createTitledBorder(
                                    new EtchedLineBorder(),
                                    langpack.getString("installer.madewith") + " ",
                                    TitledBorder.DEFAULT_JUSTIFICATION,
                                    TitledBorder.DEFAULT_POSITION,
                                    new Font("Dialog", Font.PLAIN, 10)))
        ) {

            navPanel.setLayout(new BoxLayout((JPanel) navPanel, BoxLayout.X_AXIS))

            hglue()

            widget(
                    ButtonFactory.createButton(
                        langpack.getString("installer.prev"),
                        icons.getImageIcon("stepback"),
                        installdata.buttonsHColor
                    ),
                    name: "prevButton"
            )

            rigidArea(width:5, height:0)

            widget(
                    ButtonFactory.createButton(
                        langpack.getString("installer.next"),
                        icons.getImageIcon("stepforward"),
                        installdata.buttonsHColor
                    ),
                    name: "nextButton"
            )

            rigidArea(width:5, height:0)

            widget(
                    ButtonFactory.createButton(
                        langpack.getString("installer.quit"),
                        icons.getImageIcon("stop"),
                        installdata.buttonsHColor
                    ),
                    name: "quitButton"
            )
        }

        return(navPanel)

    }

    public Dimension getSize()
    {
        return new Dimension(installdata.guiPrefs.width, installdata.guiPrefs.height)
    }

    private addInfos()
    {
        info.setAppName("IzPackStudio")
        info.setAppVersion("0.1")
        info.addAuthor(new Author("Alexis Plantin", "alexis@izs.com"))
        info.addAuthor(new Author("Thomas Maurel", "thomas@izs.com"))
        info.addAuthor(new Author("Mickael Lasnes", "mickael@izs.com"))
        info.addAuthor(new Author("Samuel Djian", "samuel@izs.com"))
        info.setAppURL("http://www.izpack.org")
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



 
}