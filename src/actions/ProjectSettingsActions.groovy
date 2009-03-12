package actions


actions
{
	action(
		id: 'saveSettings',
        name: 'OK',
		closure: {
          controller.updateProjectSettings(
                        authorsModel.getRowsModel().getValue(),
                        projName.getText(),
                        appVersion.getText(),
                        appURL.getText(),
                        langPacksList.getSelectedIndices(),
                        Integer.parseInt(appWidth.getText()),
                        Integer.parseInt(appHeight.getText()),
                        appResizable.isSelected()
                )
          projectSettings.dispose()
        },
		shortDescription: 'Save settings'
    )

	action(
		id: 'closeSettings',
        name: 'Close',
		closure: {
          projectSettings.dispose()
        },
		shortDescription: 'Close Settings'
    )

}
