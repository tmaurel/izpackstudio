package views

import actions.ProjectSettingsActions
import helpers.Positionning
import net.miginfocom.swing.MigLayout
import views.GeneralProjectSettingsView
import views.PacksProjectSettingsView

build(ProjectSettingsActions)

dialog(
    id: 'projectSettings',    
    modal: true,
    title: 'Project Settings',
    location: Positionning.CenterPosition([400,400]),
    size: [400,600],
    resizable : false,     
    layout: new MigLayout()
) {
    tabbedPane(
        id: 'middlepanel',
        constraints:' w 100%, h 90%, wrap'
    ) {
        build(GeneralProjectSettingsView)
        build(PacksProjectSettingsView)
    }

    panel(
        id: 'bottomPanel',
        constraints:'span, w 30%, h 10%, align right'
    ) {

        button (
            id: 'ok',
            action: saveSettings,
        )

        button (
            id: 'cancel',
            action: closeSettings
        )

    }
    
}
