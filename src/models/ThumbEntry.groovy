package models

import javax.swing.JLabel

/**
 * Entries for the Thumb List
 *
 */
class ThumbEntry
{

    /**
     * Image of the Entry
     *
     */
    def image


    /**
     * Title of the Entry
     *
     */
    def title

    /**
     * Constructor
     *
     * @param img   Image of the Entry
     * @param ttl   Title of the Entry
     */
    ThumbEntry(img, ttl)
    {
        image = img
        title = ttl
    }

    /**
     * Getter to retrieve the Title of the entry
     *
     * @return Title of the Entry
     */
    def getTitle()
    {
        return title
    }

    /**
     * Getter to retrieve the Image of the entry
     *
     * @return Image of the Entry
     */
    def getImage()
    {
        return image    
    }

}