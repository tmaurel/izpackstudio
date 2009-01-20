package helpers

import java.awt.Dimension
import java.awt.Toolkit



/**
* Helper class for Positionning Containers or Components
*
*/
class Positionning
{

    /**
    * Get the size of the screen
    *
    */
    def static Dimension FullScreenSize = Toolkit.getDefaultToolkit().getScreenSize()


    /**
    * Give the location to center frames/windows using the size
    *
    * @param    dimension   Dimension of the frame/window
    * @return   top left location used to center the frame/window
    */
    def static CenterPosition(dimension)
    {
        // Get component size
        Dimension componentSize = dimension
        // If the component height is bigger than screensize, use the screensize
        if (componentSize.height > FullScreenSize.height)
        {
            componentSize.height = FullScreenSize.height
        }

        // Vertical position is the screen height minus the component height divided by 2
        componentSize.height = (FullScreenSize.height - componentSize.height) / 2

        // If the component width is bigger than screensize, use the screensize
        if (componentSize.width > FullScreenSize.width)
        {
            componentSize.width = FullScreenSize.width
        }

        // Horizontal position is the screen width minus the component width divided by 2
        componentSize.width = (FullScreenSize.width - componentSize.width) / 2

        // Return the position as [int, int]
        return [(int) componentSize.width, (int) componentSize.height]
    }


    /**
    * Getter for the screen size
    *
    * @return   Screen size
    */
    def static getFullScreenSize()
    {
        return FullScreenSize
    }
}