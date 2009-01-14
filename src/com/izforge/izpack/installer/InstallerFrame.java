package com.izforge.izpack.installer;

import com.izforge.izpack.Info;
import com.izforge.izpack.LocaleDatabase;
import com.izforge.izpack.gui.IconsDatabase;


public class InstallerFrame
{

    public InstallData installdata;

    public LocaleDatabase langpack;

    public IconsDatabase icons;

    public Info info;

    public InstallerFrame()
    {
        installdata = new InstallData();
        info = new Info();       
    }

}
