package views.panels

import com.izforge.izpack.gui.EtchedLineBorder
import java.awt.Font
import javax.swing.BoxLayout
import javax.swing.JPanel
import javax.swing.border.TitledBorder
import javax.swing.BorderFactory
import com.izforge.izpack.gui.ButtonFactory



panel(
    id: 'navPanel',
    border: BorderFactory.createCompoundBorder(
                    BorderFactory.createEmptyBorder(8, 8, 8, 8),
                    BorderFactory.createTitledBorder(
                            new EtchedLineBorder(),
                            controller.model.langpack.getString("installer.madewith") + " ",
                            TitledBorder.DEFAULT_JUSTIFICATION,
                            TitledBorder.DEFAULT_POSITION,
                            new Font("Dialog", Font.PLAIN, 10)))
) {

    navPanel.setLayout(new BoxLayout((JPanel) navPanel, BoxLayout.X_AXIS))

    hglue()

    widget(
            ButtonFactory.createButton(
                controller.model.langpack.getString("installer.prev"),
                controller.model.icons.getImageIcon("stepback"),
                controller.model.installdata.buttonsHColor
            ),
            name: "prevButton"
    )

    rigidArea(width:5, height:0)

    widget(
            ButtonFactory.createButton(
                controller.model.langpack.getString("installer.next"),
                controller.model.icons.getImageIcon("stepforward"),
                controller.model.installdata.buttonsHColor
            ),
            name: "nextButton"
    )

    rigidArea(width:5, height:0)

    widget(
            ButtonFactory.createButton(
                controller.model.langpack.getString("installer.quit"),
                controller.model.icons.getImageIcon("stop"),
                controller.model.installdata.buttonsHColor
            ),
            name: "quitButton"
    )
}
