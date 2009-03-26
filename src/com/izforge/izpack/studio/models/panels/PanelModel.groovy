package com.izforge.izpack.studio.models.panels


/**
 * Project Model containing all global vars needed for a project
 *
 */
class PanelModel {


    /**
    * Contains the IzPack Panel name
    *
    */
    protected name

    /**
    * Container for the panel controllers
    *
    */
    protected panel

    /**
    * Contains the resource of the panel
    *
    */
    protected resource

    /**
    * Constructor
    *
    */
    PanelModel()
    {
        
    }

    /**
    * Set the panel
    *
    * @param   pan   The IzPack Panel Object
    */
    public setPanel(pan)
    {
        panel = pan
    }    

    /**
    * Get the panel
    *
    * @return The IzPack Panel Object
    */
    public getPanel()
    {
        return panel
    }

    /**
    * Set the IzPanel name
    *
    * @param   nam   The IzPack Panel name
    */
    public setName(nam)
    {
        name = nam
    }

    /**
    * Get the IzPanel name
    *
    * @return The IzPack Panel name
    */
    public getName()
    {
        return name
    }

    /**
    * Get the resource of the panel
    *
    * @return The resource of the panel
    */
    public getResource()
    {
        return resource
    }

    /**
    * Set the resource
    *
    * @param   res   The resource
    */
    public setResource(res)
    {
        resource = res
    }



}