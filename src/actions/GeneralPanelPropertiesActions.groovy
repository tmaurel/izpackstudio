package actions

import javax.swing.JFileChooser


actions
{
	action(
		id: 'popFileChooser',
        name: 'Find',
		closure: {
            if (myFileChooser.showOpenDialog(test)==JFileChooser.APPROVE_OPTION)
            {
                File f = myFileChooser.getSelectedFile()
                controller.model.setResource(f.toString())
                controller.refresh()
            }
        },
		shortDescription: 'Search the file',
    )
}  