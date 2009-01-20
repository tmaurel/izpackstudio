package helpers

import java.awt.*


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
    * Calc the gap needed for a container to only show one element at once
    *
    * @param    component   Component
    * @return   gap needed for a container
    */
    def static getGap(component)
    {
        return (int) component.getSize().width / 2
    }


    /**
    * Calc the initial poisition of the container considering the component size
    * and the gap
    *
    * @param    component   Component included
    * @param    container   Container where the component is included
    * @return   gap needed for a container
    */
    def static initialPosition(component, container)
    {
        int dec = (int) (container.getSize().width - component.getSize().width) / 2
        int pos = (int) Positionning.getGap(container) - dec
        return (int) pos
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