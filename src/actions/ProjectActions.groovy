package actions

actions
{

    action(
		id: 'newProject',
		name: 'New project',
		closure: controller.&newProject,
		mnemonic: 'N',
		accelerator: 'ctrl N',
		shortDescription: 'Create a new installer project',
        smallIcon: imageIcon(resource:"/images/newproject_mini.png")
    )

    action(
		id: 'closeProject',
		name: 'Close Project',
		closure: controller.&closeProject,
		mnemonic: 'X',
		accelerator: 'ctrl X',
		shortDescription: 'Close the current project',
        smallIcon: imageIcon(resource:"/images/closeproject_mini.png")
    )

    action(
		id: 'toggleProjectSettings',
		name: 'Project Settings',
		closure: controller.&toggleProjectSettings,
		mnemonic: 'P',
		accelerator: 'ctrl  P',
		shortDescription: 'Toggle project settings',
        smallIcon: imageIcon(resource:"/images/projectsettings_mini.png")
    )

	action(
		id: 'addHelloPanel',
		name: 'HelloPanel',
		closure: { controller.createPanel("HelloPanel") },
		mnemonic: 'H',
		accelerator: 'ctrl shift H',
		shortDescription: 'Add HelloPanel',
        smallIcon: imageIcon(resource:"/images/addpanel.png")
    )

	action(
		id: 'addLicencePanel',
		name: 'LicencePanel',
		closure: { controller.createPanel("LicencePanel") },
		mnemonic: 'L',
		accelerator: 'ctrl shift L',
		shortDescription: 'Add LicencePanel',
        smallIcon: imageIcon(resource:"/images/addpanel.png")
    )

	action(
		id: 'addInfoPanel',
		name: 'InfoPanel',
		closure: { controller.createPanel("InfoPanel") },
		mnemonic: 'I',
		accelerator: 'ctrl shift I',
		shortDescription: 'Add InfoPanel',
        smallIcon: imageIcon(resource:"/images/addpanel.png")
    )

	action(
		id: 'addSummaryPanel',
		name: 'SummaryPanel',
		closure: { controller.createPanel("SummaryPanel") },
		mnemonic: 'S',
		accelerator: 'ctrl shift S',
		shortDescription: 'Add SummaryPanel',
        smallIcon: imageIcon(resource:"/images/addpanel.png")
    )

	action(
		id: 'addInstallPanel',
		name: 'InstallPanel',
		closure: { controller.createPanel("InstallPanel") },
		mnemonic: 'N',
		accelerator: 'ctrl shift N',
		shortDescription: 'Add InstallPanel',
        smallIcon: imageIcon(resource:"/images/addpanel.png")
    )

	action(
		id: 'addPathInputPanel',
		name: 'PathInputPanel',
		closure: { controller.createPanel("PathInputPanel") },
		mnemonic: 'P',
		accelerator: 'ctrl shift P',
		shortDescription: 'Add PathInputPanel',
        smallIcon: imageIcon(resource:"/images/addpanel.png")
    )

    action(
		id: 'addTargetPanel',
		name: 'TargetPanel',
		closure: { controller.createPanel("TargetPanel") },
		mnemonic: 'T',
		accelerator: 'ctrl shift T',
		shortDescription: 'Add TargetPanel',
        smallIcon: imageIcon(resource:"/images/addpanel.png")
    )

    action(
		id: 'addPacksPanel',
		name: 'PacksPanel',
		closure: { controller.createPanel("PacksPanel") },
		mnemonic: 'K',
		accelerator: 'ctrl shift K',
		shortDescription: 'Add PacksPanel',
        smallIcon: imageIcon(resource:"/images/addpanel.png")
    )

    action(
		id: 'addFinishPanel',
		name: 'FinishPanel',
		closure: { controller.createPanel("FinishPanel") },
		mnemonic: 'F',
		accelerator: 'ctrl shift F',
		shortDescription: 'Add FinishPanel',
        smallIcon: imageIcon(resource:"/images/addpanel.png")
    )

    action(
        id: 'deletePanel',
        name: 'Delete Panel',
        closure: controller.&deletePanel,
        mnemonic: 'R',
        accelerator: 'ctrl D',
        shortDescription: 'Delete selected panel',
        smallIcon: imageIcon(resource:"/images/removepanel.png")
    )
    
}