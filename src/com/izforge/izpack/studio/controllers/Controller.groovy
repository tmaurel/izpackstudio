package com.izforge.izpack.studio.controllers

import groovy.swing.SwingBuilder


/**
 * Abstract Controller class to be inherit by all controllers
 *
 */
abstract class Controller
{
    /**
    * Model used by the controller
    *
    */
    protected model


    /**
    * View (SwingBuilder, ...) used by the controller
    *
    */
    protected view

    /**
    * Parent controller
    *
    */
    protected parent


    /**
    * Constructor
    *
    * @param    m   The model used by the constructor
    * @param    v   The view used by the constructor
    * @param    p   Parent controller 
    */
    Controller(m = null, v = null, p = null)
    {
        // Set model
        model = m

        // Set view (SwingBuilder by default)
        if(v != null) view = v
        else view = new SwingBuilder()

        // Assign self to view
        view.controller = this

        // Define parent controller
        parent = p
    }

    /**
    * Abstract method implemented by inherited classes
    * Define the controller start method
    *
    */
    def abstract public start(args = null) {}

}