package actions

import javax.swing.JFileChooser
import javax.swing.filechooser.FileFilter


actions
{
	action(
		id: 'popFileChooser',
        name: 'Find',
		closure: {
            def fC = fileChooser(
                        id: 'myFileChooser',
                        fileFilter: [getDescription: {-> "*.txt"}, accept:{file-> file ==~ /.*?\.txt/ || file.isDirectory() }] as FileFilter,
                        dialogTitle: 'Select a file',
                        acceptAllFileFilterUsed: false
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