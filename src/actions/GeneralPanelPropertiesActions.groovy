package actions

import javax.swing.JFileChooser


actions
{
	action(
		id: 'popFileChooser',
        name: 'Find',
		closure: {
            def fC = fileChooser(
                        id: 'myFileChooser',
                        dialogTitle: 'Select a file'
                     )

            if (fC.showOpenDialog(panelProperties)==JFileChooser.APPROVE_OPTION)
            {
                File f = fC.getSelectedFile()
                controller.model.setResource(f.toString())
                controller.refresh()
            }
        },
		shortDescription: 'Search the file',
    )
}  