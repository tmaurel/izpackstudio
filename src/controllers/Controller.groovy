package controllers

import groovy.swing.SwingBuilder

abstract class Controller
{

    def model
    def view

    Controller(m = null, v = null)
    {
        model = m
        if(v != null) view = v
        else view = new SwingBuilder()
        view.controller = this
    }

    def abstract start() {}

}