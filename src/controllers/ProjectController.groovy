package controllers

import views.ProjectSettingsView
import controllers.panels.*
import models.panels.PanelModel
import groovy.xml.StreamingMarkupBuilder
import com.izforge.izpack.Info.Author
import views.panels.NavigationPanel
import javax.swing.ImageIcon
import com.izforge.izpack.compiler.PackInfo
import groovy.beans.Bindable
import org.jdesktop.swingx.treetable.DefaultMutableTreeTableNode
import helpers.URIHelper


/**
* Controller for Projects
*
*/
@Bindable
class ProjectController extends Controller {


    /**
    * Boolean telling if u'r currently in a project or not
    *
    */
    boolean isInProject = false

    /**
    * Boolean telling if there has been any changes since last save
    *
    */
    boolean projectHasChanged = false


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
        view.build 
        {
            projectHasChanged = true
        }
        def name = controller.model.name
        name = name.substring(name.lastIndexOf(".")+1, name.length())
        println(model.info.getAppName() + " : Added " + name)
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
        view.build 
        {
            projectHasChanged = true
        }
        def name = controller.model.name
        name = name.substring(name.lastIndexOf(".")+1, name.length())
        println(model.info.getAppName() + " : Moved " + name)
    }


    /**
    * Delete a panel
    *
    * @param index Index of the panel to be deleted
    */
    def deletePanel(index)
    {
        def panels = getPanels()
        def controller = panels.get(index)
        def name = controller.model.name
        name = name.substring(name.lastIndexOf(".")+1, name.length())
        println(model.info.getAppName() + " : Removed  " + name)
        panels.remove(index)
        view.build 
        {
            projectHasChanged = true
        }
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
        if(args == "new")
        {
            println("Creating new Project...")
            model.setDefaults()
        }
        else if(args == "load")
        {
            println("Loading Project...")
            loadXML(model.fileName)
        }
    }

    /**
    * Stop method of the Controller
    *
    */
    public stop() {
        println("Closing Project...")  
    }

     /**
    * Stop method of the Controller
    *
    */
    public save() {
        if(isInProject)
          toXML(model.fileName)
    }

     /**
    * Build the jar
    *
    */
    public build() {
        def tempName = model.fileName.substring(model.fileName.lastIndexOf(File.separator)+1,model.fileName.size())
        def path = model.fileName.substring(0,model.fileName.lastIndexOf(File.separator)+1)
        def nameJar = path+tempName.substring(0,tempName.size()-3)+"jar"
        tempName = path+"~"+tempName
        String[] args = [tempName,"-o",nameJar,"-b",path]
        toXML(tempName)
        com.izforge.izpack.compiler.Compiler.main(args);
        def tempFile = new File((String)tempName)
        tempFile.delete()
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
        println("Loading from " + fileName)  
        model.fileName = fileName
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
        def absolutePath
        absolutePath = fileName.substring(0,fileName.lastIndexOf(File.separator)+1)
        model.info.setAppName(xml.info.appname.text())
        println(model.info.getAppName() + " : Set Application Name -> " + xml.info.appname.text())
        model.info.setAppVersion(xml.info.appversion.text())
        println(model.info.getAppName() + " : Set Application Version -> " + xml.info.appversion.text())
        def authorsList = xml.info.authors.author
        model.info.getAuthors().clear()
        authorsList.each
        {
            def author = new Author(it['@name'],it['@email'])
            model.info.addAuthor(author)
            println(model.info.getAppName() + " : Added author -> " + it['@name'])
        }
        model.info.setAppURL(xml.info.url.text())
        println(model.info.getAppName() + " : Set Application URL -> " + xml.info.url.text())
        model.prefs.width = Integer.parseInt(xml.guiprefs['@width'].text())
        println(model.info.getAppName() + " : Set Application Width -> " + xml.guiprefs['@width'].text())
        model.prefs.height = Integer.parseInt(xml.guiprefs['@height'].text())
        println(model.info.getAppName() + " : Set Application Height -> " + xml.guiprefs['@height'].text())
        model.prefs.resizable = xml.info.guiprefs['@resizable']
        println(model.info.getAppName() + " : Set Application Resizable -> " + xml.info.guiprefs['@resizable']) 
        def langs = xml.locale.langpack
        langs.each
        {
            lang ->
            model.selectedLangPacks.add("${lang.'@iso3'}".toString())
            println(model.info.getAppName() + " : Added Langpack -> ${lang.'@iso3'}")
        }
        def panelsToBeInstanciated = xml.panels.panel
        panelsToBeInstanciated.each
        {
            panel ->
            createPanel("${panel.'@classname'}")
            println(model.info.getAppName() + " : Added Panel -> ${panel.'@classname'}")
        }
        def resources = xml.resources.res
        resources.each
        {
            res ->
            println(model.info.getAppName() + " : Added Ressource -> ${res.'@src'}")
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
                        model.panels[i].model.resource = absolutePath + "${res.'@src'}".toString()
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
                        model.panels[i].model.resource = absolutePath + "${res.'@src'}".toString()
                    }
                    break
            }
        }
        def pack = xml.packs.pack
        pack.each
        {
            currentPack ->
            println(model.info.getAppName() + " : Added Pack -> ${currentPack.'@name'}")
            def required = true
            if("${currentPack.'@required'}".toString() == "no")
            {
                required = false
            }
            def myPackInfo = new PackInfo("${currentPack.'@name'}".toString(), "", "${currentPack.description.text()}", required, false, "", true)
            def myNode = new DefaultMutableTreeTableNode(myPackInfo)
            def files = currentPack.file
            files.each
            {
                file ->
                    def myFile = new File(absolutePath+"${file.'@src'}".toString())
                    myPackInfo.addFile(null, myFile, "${file.'@targetdir'}".toString(), null, 0, null, "")
                    myNode.add(new DefaultMutableTreeTableNode([path: "${file.'@src'}".toString(), target: "${file.'@targetdir'}".toString()]))
            }
            model.packs.getRoot().add(myNode)
        }
        refresh()
     }

     def toXML(String fileName)
     {
       println(model.info.getAppName() + " : Saving into " + fileName)      
       def myBuilder = new StreamingMarkupBuilder()
       def xml = myBuilder.bind{
        mkp.xmlDeclaration()
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
            "locale"{
                model.selectedLangPacks.each{
                    "langpack"(iso3:it)
                }
            }
            def absolutePath
            absolutePath = fileName.substring(0,fileName.lastIndexOf(File.separator)+1)
            "resources"{
                model.panels.each{
                    if(it.model.resource != null)
                    {
                        if(it instanceof GeneralLicencePanelController)
                        {
                             //"res"(id:"LicencePanel.licence", src:URIHelper.convertToRelativePath(absolutePath, it.model.resource))
                             "res"(id:"LicencePanel.licence", src:URIHelper.absoluteToRelative(it.model.resource, absolutePath))
                        }
                        if(it instanceof GeneralInfoPanelController)
                        {
                             "res"(id:"InfoPanel.info", src:URIHelper.absoluteToRelative(it.model.resource, absolutePath))
                        }
                    }
                }
            }
            panels{
                model.panels.each{
                    it2 ->
                    mkp.yield it2.toXML()
                }
            }
            //Todo Gestion parent 
            "packs"{
                model.packs.getFilteredArray(PackInfo).each{
                    def myPackInfo = it.getUserObject()
                    if(myPackInfo.getPack().required)
                        "pack"(name:myPackInfo.getPack().name,
                                 mkp.yield {if(myPackInfo.getPack().parent != null) return {parent:myPackInfo.getPack().parent}},
                               required:"yes")
                        {
                            "description"(myPackInfo.getPack().description)
                            myPackInfo.files.keySet().each{ currentFileName ->
                                "file"(src:URIHelper.absoluteToRelative(myPackInfo.files.get(currentFileName).toString(),  absolutePath),
                                       targetdir:currentFileName.getTargetPath())
                            }
                        }
                    else if(myPackInfo.getPack().preselected)
                        "pack"(name:myPackInfo.getPack().name,
                               mkp.yield {if(myPackInfo.getPack().parent != null) return {parent:myPackInfo.getPack().parent}},
                               required:"no",preselected:"yes")
                        {
                            "description"(myPackInfo.getPack().description)
                            myPackInfo.files.keySet().each{ currentFileName ->
                                "file"(src:URIHelper.absoluteToRelative(myPackInfo.files.get(currentFileName).toString(),  absolutePath),
                                       targetdir:currentFileName.getTargetPath())
                            }
                        }
                    else
                        "pack"(name:myPackInfo.getPack().name,
                               mkp.yield {if(myPackInfo.getPack().parent != null) return {parent:myPackInfo.getPack().parent}},
                               required:"no")
                        {
                            "description"(myPackInfo.getPack().description)
                            myPackInfo.files.keySet().each{ currentFileName ->
                                "file"(src:URIHelper.absoluteToRelative(myPackInfo.files.get(currentFileName).toString(),  absolutePath),
                                       targetdir:currentFileName.getTargetPath())
                            }
                        }
                }
            }
        }}

        try {
                 def out = new BufferedWriter(new FileWriter(fileName));
                 out.write(xml.toString());
                 out.close();
                 println(model.info.getAppName() + " : Saved!")
             } catch (IOException e) {
                    println(model.info.getAppName() + " : Couldnt save! ") 
             }

     }

    public updateProjectSettings(authors, appName, appVersion, appURL, appLangs, width, height, resizable)
    {
        model.setInfos(authors, appName, appVersion, appURL)
        model.setPrefs(width, height, resizable)
        model.setLangs(appLangs)
        view.build
        {
            projectHasChanged = true
        }
        println(model.info.getAppName() + " : Updated Project Settings") 
    }


    public updatePacksSettings(packsTree)
    {
        model.packs = packsTree
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