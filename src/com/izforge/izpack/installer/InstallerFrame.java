package com.izforge.izpack.installer;

import com.izforge.izpack.Info;
import com.izforge.izpack.LocaleDatabase;
import com.izforge.izpack.gui.IconsDatabase;



/**
 * IzPackStudio version of the IzPack InstallerFrame
 *
 */
public class InstallerFrame
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

}
