package views.panels

import net.miginfocom.swing.MigLayout
import javax.swing.JFileChooser


panel(
        layout: new MigLayout("fill"),
        opaque: false,
        visible: true,
        id:"test"
)
{
    label(text: 'LicencePanel Properties', constraints: 'span')
    label(text: 'Select your licence document', constraints: 'span')
    button(text: "Select", constraints: 'span', actionPerformed : {
                if (myFileChooser.showOpenDialog(test)==JFileChooser.APPROVE_OPTION)
                {
                    File f = myFileChooser.getSelectedFile()
                    controller.model.setResource(f.toString())
                    controller.refresh()
                }
            }
        )
    dialog(
            id: "selectFile",
            visible: false,
            size: [600,400]
    )
    {
        fileChooser(id:"myFileChooser",dialogTitle:"Select a Text File")
        {

        }
    }
}




