package helpers

import java.awt.*

class Positionning
{

    def static Dimension FullScreenSize = Toolkit.getDefaultToolkit().getScreenSize()

    def static CenterPosition(dimension)
    {

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize()
        Dimension componentSize = dimension
        Point position = null

        if (componentSize.height > screenSize.height)
        {
            componentSize.height = screenSize.height
        }

        componentSize.height = (screenSize.height - componentSize.height) / 2


        if (componentSize.width > screenSize.width)
        {
            componentSize.width = screenSize.width
        }

        componentSize.width = (screenSize.width - componentSize.width) / 2

        return [(int) componentSize.width, (int) componentSize.height]
    }

    def static getFullScreenSize()
    {
        return FullScreenSize
    }
}