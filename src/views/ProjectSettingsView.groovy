package views

import net.miginfocom.swing.MigLayout

import java.awt.Color
import java.awt.BorderLayout
import javax.swing.SwingConstants
import javax.swing.BorderFactory
import java.awt.Dimension
import helpers.Positionning




dialog(
        id: 'projectSettings',
        title: 'Project Settings',
        location: Positionning.CenterPosition([500,600]),
        size: [500,600],
        resizable : false,
        visible: true,
        background: Color.DARK_GRAY,
        modal: true,
        layout: new MigLayout()      
) {

    panel(
        id: 'toppanel',
        constraints:'span, w 100%, h 10%',
        layout: new BorderLayout(),
    ) {
        label(
            text : 'Application Settings',
            horizontalAlignment : SwingConstants.CENTER
        )
    }

    panel(
        id: 'middlepanel',
        constraints:' w 100%, h 80%, wrap',
        border: BorderFactory.createMatteBorder(1,1,1,1,Color.lightGray),
        layout: new MigLayout("fill","[right w 150px]20px[left w 150px]","")
    ) {

        label (
            text : 'Project Name',
        )

        textField(
            id:'projName',
            text: bind { controller.model.info.appName },
            constraints : 'w 150px, wrap'
        )

        label (
            text : 'Application Version'
        )

        textField(
            id:'appVersion',
            text: bind { controller.model.info.appVersion },
            constraints : 'w 150px, wrap'
        )

        label (
            text : 'Application URL'
        )

        textField(
            id:'appURL',
            text: bind { controller.model.info.appURL },
            constraints : 'w 150px, wrap'
        )

        label (
            text : 'Author(s)'
        )

        panel (
            constraints : 'wrap'
        ) {

            button (
                id: 'addAuthor',
                preferredSize : new Dimension(70,25),
                text :'Add',
                actionPerformed: {
                    addAuthors.setVisible(true)
                }
            )

            button (
                id: 'remAuthor',
                preferredSize : new Dimension(70,25),
                text :'Remove',
                constraints :'wrap'
            )

            table (
                id : 'listAuthors',
                constraints : 'w 80px, h 20px'
            )
        }

        label (
            text : 'Width'
        )

        textField(
            id:'appWidth',
            text: bind { controller.model.prefs.width },
            constraints : 'w 150px, wrap'
        )

        label (
            text : 'Heigth'
        )

        textField(
            id:'appHeight',
            text: bind { controller.model.prefs.height },
            constraints : 'w 150px, wrap'
        )

    }

    panel(
        id: 'bottompanel',
        constraints:'span, w 100%, h 10%',
        border: BorderFactory.createMatteBorder(1,1,1,1,Color.lightGray),
        layout: new MigLayout("","57% [] 15px []","19% [] ")
    ) {

        button (
            id: 'valid',
            preferredSize : new Dimension(90,20),
            text :'Valid',
        )

        button (
            id: 'cancel',
            preferredSize : new Dimension(90,20),
            text :'Cancel',
            actionPerformed:
            {
                projectSettings.dispose()                        // not so clean ?
            }
        )
    }


    dialog(
        id : 'addAuthors',
        title : 'Add an author',
        location : Positionning.CenterPosition([310,210]),
        size : [310,210],
        resizable : false,
        background: Color.DARK_GRAY,
        layout: new MigLayout(),
        visible: false,
        modal: true
    ) {

        panel(
            id:'mainPanel',
            constraints : 'w 100%, h 100%',
            layout: new MigLayout("fill","4% [] [185px] 1%","5% [] 12% [] 12% [] 12% [] 5%")
        ) {

            label(
                text : 'First Name'
            )

            textField(
                id : 'firstName',
                constraints : 'w 100%, wrap'
            )

            label(
                text : 'Last Name'
            )

            textField(
                id : 'lastName',
                constraints : 'w 100%, wrap'
            )

            label(
                text : 'Email Adress'
            )

            textField(
                id : 'mail',
                constraints : 'w 100%, wrap'
            )

            button (
                id: 'valid',
                preferredSize : new Dimension(70,20),
                text :'Valid',
                actionPerformed:
                {
                //   controller.addAuthorTest()
                }
            )

            button (
                id: 'cancel',
                preferredSize : new Dimension(70,20),
                text :'Cancel',
                constraints : 'align right',
                actionPerformed:
                {
                    addAuthors.setVisible(false)           // not so clean ?
                }
            )
        }
    }
    
}
