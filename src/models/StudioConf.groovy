package models
import sun.misc.JarFilter


class StudioConf
{
    def static IZPACK_HOME = "C:/Utils/IzPack"


    StudioConf()
    {

        
    }

    def static checkIzPackHome()
    {
        def valid = true
        def file = [
                        IZPACK_HOME + "/lib/installer.jar",
                        IZPACK_HOME + "/bin/compile",
                        IZPACK_HOME + "/bin/panels/"
                   ]

        def f = null

        file.each
        {
             f = new File(it)
             if (!f.exists())
             {
                valid = false
             }
        }
        return valid
    }


    
}
