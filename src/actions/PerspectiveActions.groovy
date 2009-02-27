package actions


actions
{
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