package views

import helpers.*
import net.miginfocom.swing.MigLayout
import javax.swing.BorderFactory
import java.awt.Color
import javax.swing.OverlayLayout
import java.awt.Dimension





window(
        id: 'splashWindow',
        size: [600,450],
        location: Positionning.CenterPosition([600,450])
) {
        panel(
            id: 'splashPanel',
        ) {
            overlay = new OverlayLayout(splashPanel)
            splashPanel.setLayout(overlay)

            panel(
                    id: 'componentPanel',
                    layout: new MigLayout(),
                    opaque: false,
                    border: BorderFactory.createMatteBorder(1,1,1,1,Color.GRAY)
            ) {
                panel(
                        id: 'topPanel',
                        constraints: 'w 100%, h 85%, span, wrap',
                        opaque: false,
                )

                panel(
                        id: 'bottomLeftPanel',
                        constraints: 'w 40%, h 15%',
                        opaque: false,
                ) {
                    
                        progressBar(
                           id: 'splashProgress',
                           maximumSize: new Dimension(200, 30),
                           preferredSize: new Dimension(200, 30),
                           minimumSize: new Dimension(200,30),
                           background: Color.decode("#b3a87f"),
                           foreground: Color.decode("#4c321b"),
                           border: BorderFactory.createMatteBorder(4,4,4,4,Color.decode("#4c321b")),
                           paintBorder: false,
                           minimum: 0,
                           maximum: 10

                        ) 
                    
                }

                panel(
                        id: 'bottomRightPanel',
                        constraints: 'w 60%, h 10%, align left',
                        opaque: false,
                ) {
                        label(
                           id: 'loadingText',
                           text: 'Loading...',
                           foreground: Color.WHITE,
                           constraints: 'align left'
                        )
                }


            }

            label(
                    id: 'splashPicture',
                    icon: imageIcon(resource: '../images/splash.jpg'),
                    alignmentX: 0.0f,
                    alignmentY: 0.0f
            )
        }




}