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
    )

    action(
		id: 'closeProject',
		name: 'Close Project',
		closure: controller.&closeProject,
		mnemonic: 'X',
		accelerator: 'ctrl X',
		shortDescription: 'Close the current project',
    )

    action(
		id: 'toggleProjectSettings',
		name: 'Project Settings',
		closure: controller.&toggleProjectSettings,
		mnemonic: 'P',
		accelerator: 'ctrl  P',
		shortDescription: 'Toggle project settings',
    )

	action(
		id: 'addHelloPanel',
		name: 'HelloPanel',
		closure: { controller.createPanel("HelloPanel") },
		mnemonic: 'H',
		accelerator: 'ctrl shift H',
		shortDescription: 'Add HelloPanel',
    )

	action(
		id: 'addFinishPanel',
		name: 'FinishPanel',
		closure: { controller.createPanel("FinishPanel") },
		mnemonic: 'F',
		accelerator: 'ctrl shit F',
		shortDescription: 'Add FinishPanel',
    )

	action(
		id: 'addLicencePanel',
		name: 'LicencePanel',
		closure: { controller.createPanel("LicencePanel") },
		mnemonic: 'L',
		accelerator: 'ctrl shift L',
		shortDescription: 'Add LicencePanel',
    )

	action(
		id: 'addInfoPanel',
		name: 'InfoPanel',
		closure: { controller.createPanel("InfoPanel") },
		mnemonic: 'I',
		accelerator: 'ctrl shift I',
		shortDescription: 'Add InfoPanel',
    )

	action(
		id: 'addSummaryPanel',
		name: 'SummaryPanel',
		closure: { controller.createPanel("SummaryPanel") },
		mnemonic: 'S',
		accelerator: 'ctrl shift S',
		shortDescription: 'Add SummaryPanel',
    )

	action(
		id: 'addInstallPanel',
		name: 'InstallPanel',
		closure: { controller.createPanel("InstallPanel") },
		mnemonic: 'N',
		accelerator: 'ctrl shift N',
		shortDescription: 'Add InstallPanel',
    )

	action(
		id: 'addPathInputPanel',
		name: 'PathInputPanel',
		closure: { controller.createPanel("PathInputPanel") },
		mnemonic: 'P',
		accelerator: 'ctrl shift P',
		shortDescription: 'Add PathInputPanel',
    )

    action(
		id: 'addTargetPanel',
		name: 'TargetPanel',
		closure: { controller.createPanel("TargetPanel") },
		mnemonic: 'T',
		accelerator: 'ctrl shift T',
		shortDescription: 'Add TargetPanel',
    )

    action(
		id: 'addPacksPanel',
		name: 'PacksPanel',
		closure: { controller.createPanel("PacksPanel") },
		mnemonic: 'K',
		accelerator: 'ctrl shift K',
		shortDescription: 'Add PacksPanel',
    )

}