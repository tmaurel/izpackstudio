package actions


actions
{
    action(
        id: 'exit',
        name: 'Quit',
        closure: {
            closeProject.actionPerformed()
            doLater
            {
                if(!controller.project.isInProject)
                {
                    controller.exit()
                }
            }
        },
        mnemonic: 'Q',
        accelerator: 'ctrl Q',
        shortDescription: 'Exit IzPackStudio'
    )

    action(
        id: 'restoreDefault',
        name: 'Restore perspective',
        closure: controller.perspective.&restoreDefault,
        mnemonic: 'R',
        accelerator: 'ctrl R',
        shortDescription: 'Restore default perspective',
        smallIcon: imageIcon(resource:"/images/photo-multiple.png")
    )    
}