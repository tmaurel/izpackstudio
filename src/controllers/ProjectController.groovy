package controllers

import views.ProjectSettingsView
import controllers.panels.*
import models.panels.PanelModel
import groovy.xml.StreamingMarkupBuilder
import com.izforge.izpack.Info.Author
import views.panels.NavigationPanel
import javax.swing.ImageIcon
import groovy.swing.SwingXBuilder


/**
* Controller for Projects
*
*/
class ProjectController extends Controller {


    /**
    * Boolean telling if u'r currently in a project or not
    *
    */
    def isInProject = false


    /**
    * ProjectController Constructor
    *
    * @param    m   The model used by the constructor
    * @param    v   The view used by the constructor
    * @param    p   Parent controller
    */
    ProjectController(m = null, v = null, p = null)
    {
        super(m, v, p)
    }


    /**
    * Add a controller to panel's list
    *
    * @param controller The Panel Controller that need to be added
    */
    public addPanel(controller) {
        model.panels.add(controller)
        parent.printPanel(controller)
    }


    /**
    * Returns the panels list
    *
    * @return The panels list
    */
    public getPanels() {
        return model.panels
    }


    /**
    * Move a panel
    *
    * @param from The first panel
    * @param to The second panel
    */
    def movePanel(from, to)
    {
        def panels = getPanels()
        def controller = panels.get(from)
        panels.remove(from)
        panels.add(to, controller)
    }


    /**
    * Delete a panel
    *
    * @param index Index of the panel to be deleted
    */
    def deletePanel(index)
    {
        def panels = getPanels()
        panels.remove(index)
    }


    /**
    * Get the model's size
    *
    * @return Preferred size for the panels
    */
    public getSize() {
        return model.getSize()
    }



    /**
    * Slide to next panel
    *
    * @param controller Current Panel
    */
    def slideNext(controller)
    {
        def index = getPanels().indexOf(controller) + 1
        if(index < getPanels().size() && index >= 0)
        {
            parent.setSelectedPanel(index)
        }
    }


    /**
    * Slide to previous panel
    *
    * @param controller Current Panel
    */
    def slidePrev(controller)
    {
        def index = getPanels().indexOf(controller) - 1
        if(index < getPanels().size() && index >= 0)
        {
            parent.setSelectedPanel(index)
        }
    }

    /**
    * Toggle the project settings view
    *

    */
    def toggleProjectSettings()
    {
        view.with
        {
            doLater
            {
                build(ProjectSettingsView)
                projectSettings.setVisible(true)
                projectSettings.validate()
            }
        }

    }


    /**
    * Start method of the Controller
    *
    */
    public start(args = null) {
        isInProject = true
        if(args == "new")
            model.setDefaults()
        else if(args == "load")
            loadXML(model.fileName)
    }

    /**
    * Stop method of the Controller
    *
    */
    public stop() {
        isInProject = false
    }



    /**
    * Refresh all panels
    *
    */
    public refresh()
    {
        model.panels.each(){
            it.refresh()
        }
    }

    /**
    * Create the Navigation Panel of the Installer Frame
    *
    * @return The navigation Panel
    */
    public Object createNavPanel()
    {
        def navPanel = view.build(NavigationPanel)
        return navPanel
    }

    /**
    * Create a new panel
    *
    * @param panelName Name of the panel
    */
    public createPanel(String panelName)
    {
        def panel
        switch(panelName)
            {
                case "FinishPanel":
                    panel = new FinishPanelController(new PanelModel(), null, this)
                    break
                case "InfoPanel":
                case "HTMLInfoPanel":
                case "GeneralInfoPanel":
                    panel = new GeneralInfoPanelController(new PanelModel(), null, this)
                    break
                case "LicencePanel":
                case "HTMLLicencePanel":
                case "GeneralLicencePanel":
                    panel = new GeneralLicencePanelController(new PanelModel(), null, this)
                    break
                 case "HelloPanel":
                    panel = new HelloPanelController(new PanelModel(), null, this)
                    break
                 case "InstallPanel":
                    panel = new InstallPanelController(new PanelModel(), null, this)
                    break
                 case "PacksPanel":
                    panel = new PacksPanelController(new PanelModel(), null, this)
                    break
                 case "PathInputPanel":
                    panel = new PathInputPanelController(new PanelModel(), null, this)
                    break
                 case "SimpleFinishPanel":
                    panel = new SimpleFinishPanelController(new PanelModel(), null, this)
                    break
                 case "SummaryPanel":
                    panel = new SummaryPanelController(new PanelModel(), null, this)
                    break
                 case "TargetPanel":
                    panel = new TargetPanelController(new PanelModel(), null, this)
                    break
                default:
                    println "And this is not"
            }
        panel.start()
        view.build {
            //doLater
            //{
                addPanel(panel)
            //}
        }
    }

    /**
    * Load an XML file
    *
    * @param fileName File to be loaded
    */
    def loadXML(String fileName)
    {
        StringBuffer fileData = new StringBuffer(1000)
        BufferedReader reader = new BufferedReader(new FileReader(fileName))
        char[] buf = new char[1024]
        int numRead=0
        while((numRead=reader.read(buf)) != -1)
        {
            String readData = String.valueOf(buf, 0, numRead)
            fileData.append(readData)
            buf = new char[1024]
        }
        reader.close()
        def xml = new XmlParser().parseText(fileData.toString())
        model.info.setAppName(xml.info.appname.text())
        model.info.setAppVersion(xml.info.appversion.text())
        def authorsList = xml.info.authors.author
        model.info.getAuthors().clear()
        authorsList.each
        {
            def author = new Author(it['@name'],it['@email'])
            model.info.addAuthor(author)
        }
        model.info.setAppURL(xml.info.url.text())
        model.prefs.width = Integer.parseInt(xml.guiprefs['@width'].text())
        model.prefs.height = Integer.parseInt(xml.guiprefs['@height'].text())
        model.prefs.resizable = xml.info.guiprefs['@resizable']
        def langs = xml.locale.langpack
        langs.each
        {
            lang ->
            model.selectedLangPacks.add("${lang.'@iso3'}".toString())
        }
        def panelsToBeInstanciated = xml.panels.panel
        panelsToBeInstanciated.each
        {
            panel ->
            createPanel("${panel.'@classname'}")
        }
        def resources = xml.resources.res
        resources.each
        {
            res ->
            switch("${res.'@id'}".toString())
            {
                case "LicencePanel.licence":
                    def i=0
                    while(i<model.panels.size() && model.panels[i].model.name!="com.izforge.izpack.panels.LicencePanel")
                    {
                        i++
                    }
                    if(i<model.panels.size())
                    {
                        model.panels[i].model.resource = "${res.'@src'}".toString()
                    }
                    break
                case "InfoPanel.info":
                    def i=0
                    while(i<model.panels.size() && model.panels[i].model.name!="com.izforge.izpack.panels.InfoPanel")
                    {
                        i++
                    }
                    if(i<model.panels.size())
                    {
                        model.panels[i].model.resource = "${res.'@src'}".toString()
                    }
                    break
            }
        }
        refresh()
     }

     def toXML(String fileName)
     {
       def myBuilder = new StreamingMarkupBuilder()
       def xml = myBuilder.bind{
        installation(version:"1.0")
        {
            "info"()
            {
                "appname"(model.info.getAppName())
                "appversion"(model.info.getAppVersion())
                "authors"()
                {
                    model.info.getAuthors().each
                    {
                        "author"(name:it.getName(), email:it.getEmail())
                    }
                }
                "url"(model.info.getAppURL())
            }
            "guiprefs"(width:model.prefs.width,height:model.prefs.height,resizable:"no")
            panels{
                model.panels.each{
                    it2 ->
                    mkp.yield it2.toXML()
                }
            }
        }}

        try {
                 def out = new BufferedWriter(new FileWriter(fileName));
                 out.write(xml.toString());
                 out.close();
             } catch (IOException e) {
             }

     }

    public updateProjectSettings(authors, appName, appVersion, appURL, appLangs, width, height, resizable)
    {
        model.setInfos(authors, appName, appVersion, appURL)
        model.setPrefs(width, height, resizable)
        model.setLangs(appLangs)
    }


    public getLangIcon(String lang)
    {
        def path = "../bin/langpacks/flags/" + lang + ".gif"
        if (new File(path).exists()) {
            return new ImageIcon(path, lang);
        } else {
            System.err.println("Couldn't find file: " + path);
            return null;
        }

    }

}