package actions

import javax.swing.JFileChooser
import javax.swing.filechooser.FileFilter
import javax.swing.JOptionPane

actions
{

    action(
		id: 'newProject',
		name: 'New project',
		closure: {

            closeProject.actionPerformed()

            if(!controller.project.isInProject)
            {

                def chooser = fileChooser(
                   dialogTitle: 'Select a project destination file',
                   fileFilter: [getDescription: {-> "*.xml"}, accept:{file-> file ==~ /.*?\.xml/ || file.isDirectory() }] as FileFilter,
                   acceptAllFileFilterUsed: false
                )

                if(chooser.showSaveDialog() != JFileChooser.APPROVE_OPTION)
                {
                    return
                }

                def file = chooser.selectedFile.toString()
                def selectedFile = (file.endsWith(".xml"))?file:file+".xml"
                controller.project.model.fileName = selectedFile
                controller.project.start("new")
                controller.project.isInProject = true

            }
          
        },
		mnemonic: 'N',
		accelerator: 'ctrl N',
		shortDescription: 'Create a new installer project',
        smallIcon: imageIcon(resource:"/images/newproject_mini.png")
    )

    action(
		id: 'loadProject',
		name: 'Load Project',
		closure: {

            closeProject.actionPerformed()

            if(!controller.project.isInProject)
            {

                def chooser = fileChooser(
                   dialogTitle: 'Select your project file',
                   fileFilter: [getDescription: {-> "*.xml"}, accept:{file-> file ==~ /.*?\.xml/ || file.isDirectory() }] as FileFilter,
                   acceptAllFileFilterUsed: false
                )

                if(chooser.showOpenDialog() != JFileChooser.APPROVE_OPTION)
                {
                    return
                }

                def file = chooser.selectedFile.toString()
                controller.project.model.fileName = file
                controller.project.start("load")
                controller.project.isInProject = true
                controller.project.projectHasChanged = false

            }

        },
		mnemonic: 'L',
		accelerator: 'ctrl L',
		shortDescription: 'Load a project',
        smallIcon: imageIcon(resource:"/images/loadproject_mini.png")
    )

    action(
        enabled: bind { controller.project.isInProject },
		name: 'Close Project',
        id: 'closeProject',
		closure: {
            if(controller.project.isInProject)
            {
                if(controller.project.projectHasChanged)
                {
                  def answer = JOptionPane.showConfirmDialog(mainFrame,
                                   "Save current project ?",
                                   "Save project",
                                    JOptionPane.YES_NO_CANCEL_OPTION)
                  if (answer == JOptionPane.CANCEL_OPTION)
                  {
                      return
                  }
                  else if (answer == JOptionPane.YES_OPTION)
                  {
                      saveProject.actionPerformed()
                  }
                }
              
                controller.clean()
                controller.project.stop()
                controller.project.isInProject = false
                controller.project.projectHasChanged = false
            }

        },
		mnemonic: 'X',
		accelerator: 'ctrl X',
		shortDescription: 'Close the current project',
        smallIcon: imageIcon(resource:"/images/closeproject_mini.png")
    )

    action(
		id: 'saveProject',
        enabled: bind { controller.project.projectHasChanged },
		name: 'Save Project',
		closure: {
            if(controller.project.isInProject && controller.project.projectHasChanged)
            {
                controller.project.save()
                controller.project.projectHasChanged = false   
            }
        },
		mnemonic: 'S',
		accelerator: 'ctrl S',
		shortDescription: 'Save the current project',
        smallIcon: imageIcon(resource:"/images/saveproject_mini.png")
    )

    action(
		id: 'toggleProjectSettings',
        enabled: bind { controller.project.isInProject },
		name: 'Project Settings',
		closure: controller.&toggleProjectSettings,
		mnemonic: 'P',
		accelerator: 'ctrl  P',
		shortDescription: 'Toggle project settings',
        smallIcon: imageIcon(resource:"/images/projectsettings_mini.png")
    )

    action(
		id: 'buildProject',
        enabled: bind { controller.project.isInProject },
		name: 'Build Project',
		closure: { },
		mnemonic: 'B',
		accelerator: 'ctrl  B',
		shortDescription: 'Build the Project',
        smallIcon: imageIcon(resource:"/images/buildproject_mini.png")
    )

    action(
		id: 'addHelloPanel',
        enabled: bind { controller.project.isInProject },
		name: 'HelloPanel',
		closure: { controller.createPanel("HelloPanel") },
		mnemonic: 'H',
		accelerator: 'ctrl shift H',
		shortDescription: 'Add HelloPanel',
        smallIcon: imageIcon(resource:"/images/addpanel.png")
    )

	action(
		id: 'addLicencePanel',
        enabled: bind { controller.project.isInProject },
		name: 'LicencePanel',
		closure: { controller.createPanel("LicencePanel") },
		mnemonic: 'L',
		accelerator: 'ctrl shift L',
		shortDescription: 'Add LicencePanel',
        smallIcon: imageIcon(resource:"/images/addpanel.png")
    )

	action(
		id: 'addInfoPanel',
        enabled: bind { controller.project.isInProject },
		name: 'InfoPanel',
		closure: { controller.createPanel("InfoPanel") },
		mnemonic: 'I',
		accelerator: 'ctrl shift I',
		shortDescription: 'Add InfoPanel',
        smallIcon: imageIcon(resource:"/images/addpanel.png")
    )

	action(
		id: 'addSummaryPanel',
        enabled: bind { controller.project.isInProject },
		name: 'SummaryPanel',
		closure: { controller.createPanel("SummaryPanel") },
		mnemonic: 'S',
		accelerator: 'ctrl shift S',
		shortDescription: 'Add SummaryPanel',
        smallIcon: imageIcon(resource:"/images/addpanel.png")
    )

	action(
		id: 'addInstallPanel',
        enabled: bind { controller.project.isInProject },
		name: 'InstallPanel',
		closure: { controller.createPanel("InstallPanel") },
		mnemonic: 'N',
		accelerator: 'ctrl shift N',
		shortDescription: 'Add InstallPanel',
        smallIcon: imageIcon(resource:"/images/addpanel.png")
    )

	action(
		id: 'addPathInputPanel',
        enabled: bind { controller.project.isInProject },
		name: 'PathInputPanel',
		closure: { controller.createPanel("PathInputPanel") },
		mnemonic: 'P',
		accelerator: 'ctrl shift P',
		shortDescription: 'Add PathInputPanel',
        smallIcon: imageIcon(resource:"/images/addpanel.png")
    )

    action(
		id: 'addTargetPanel',
        enabled: bind { controller.project.isInProject },
		name: 'TargetPanel',
		closure: { controller.createPanel("TargetPanel") },
		mnemonic: 'T',
		accelerator: 'ctrl shift T',
		shortDescription: 'Add TargetPanel',
        smallIcon: imageIcon(resource:"/images/addpanel.png")
    )

    action(
		id: 'addPacksPanel',
        enabled: bind { controller.project.isInProject },
		name: 'PacksPanel',
		closure: { controller.createPanel("PacksPanel") },
		mnemonic: 'K',
		accelerator: 'ctrl shift K',
		shortDescription: 'Add PacksPanel',
        smallIcon: imageIcon(resource:"/images/addpanel.png")
    )

    action(
		id: 'addFinishPanel',
        enabled: bind { controller.project.isInProject },
		name: 'FinishPanel',
		closure: { controller.createPanel("FinishPanel") },
		mnemonic: 'F',
		accelerator: 'ctrl shift F',
		shortDescription: 'Add FinishPanel',
        smallIcon: imageIcon(resource:"/images/addpanel.png")
    )

    action(
        id: 'deletePanel',
        enabled: bind { controller.project.isInProject },
        name: 'Delete Panel',
        closure: controller.&deletePanel,
        mnemonic: 'R',
        accelerator: 'ctrl D',
        shortDescription: 'Delete selected panel',
        smallIcon: imageIcon(resource:"/images/removepanel.png")
    )
    
}