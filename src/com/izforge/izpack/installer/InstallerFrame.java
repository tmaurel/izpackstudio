package com.izforge.izpack.installer;

import com.izforge.izpack.Info;
import com.izforge.izpack.LocaleDatabase;
import com.izforge.izpack.gui.IconsDatabase;
import com.izforge.izpack.gui.EtchedLineBorder;
import com.izforge.izpack.gui.ButtonFactory;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;


/**
 * IzPackStudio version of the IzPack InstallerFrame
 *
 */
public class InstallerFrame extends JFrame
{

    /**
    * IzPack InstallData used to instantiate panels.
    * Can only be instantiated within Installer Package
    * @see com.izforge.izpack.installer.InstallData
    *
    */
    public InstallData installdata;


    /**
    * LangPack Database
    *
    */
    public LocaleDatabase langpack;


    /**
    * Icons Database
    *
    */
    public IconsDatabase icons;


    /**
    * Global Infos (appname, appversion, authors, ...)
    *
    */
    public Info info;

    /**
    * Constructor
    *
    */
    public InstallerFrame()
    {
        // Create a new InstallData Instance (need to be in Installer package)
        installdata = new InstallData();

        // Create a new Info Instance
        info = new Info();

    }


    /**
    * Create the Navigation Panel of the Installer Frame
    *
    * @return The navigation Panel
    */
    public JPanel createNavPanel()
    {
        JPanel navPanel = new JPanel();
        navPanel.setLayout(new BoxLayout(navPanel, BoxLayout.X_AXIS));
        navPanel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(8, 8,
                8, 8), BorderFactory.createTitledBorder(new EtchedLineBorder(), langpack
                .getString("installer.madewith")
                + " ", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, new Font(
                "Dialog", Font.PLAIN, 10))));
        navPanel.add(Box.createHorizontalGlue());

        JButton prevButton = ButtonFactory.createButton(langpack.getString("installer.prev"), icons
                .getImageIcon("stepback"), installdata.buttonsHColor);
        prevButton.setName("prevButton");
        navPanel.add(prevButton);

        navPanel.add(Box.createRigidArea(new Dimension(5, 0)));

        JButton nextButton = ButtonFactory.createButton(langpack.getString("installer.next"), icons
                .getImageIcon("stepforward"), installdata.buttonsHColor);
        nextButton.setName("nextButton");
        navPanel.add(nextButton);

        navPanel.add(Box.createRigidArea(new Dimension(5, 0)));

        JButton quitButton = ButtonFactory.createButton(langpack.getString("installer.quit"), icons
                .getImageIcon("stop"), installdata.buttonsHColor);
        quitButton.setName("quitButton");
        navPanel.add(quitButton);

        return(navPanel);

    }

    /**
    * Locks the 'previous' button.
    */
    public void lockPrevButton()
    {
    }

    /**
    * Locks the 'next' button.
    */
    public void lockNextButton()
    {
    }

    /**
    * Unlocks the 'previous' button.
    */
    public void unlockPrevButton()
    {
    }

    /**
    * Unlocks the 'next' button.
    */
    public void unlockNextButton()
    {
    }    

    public void setQuitButtonText(String text)
    {
    }

    /**
     * Sets a new icon into the quit button if icons should be used, else nothing will be done.
     *
     * @param iconName name of the icon to be used
     */
    public void setQuitButtonIcon(String iconName)
    {
    }

}
