package controllers

import groovy.swing.SwingBuilder

abstract class Controller
{

    def model
    def view
    def swing = new SwingBuilder()

    Controller(m = null, v = null)
    {
        model = m
        view = v
        swing.controller = this
    }

    def abstract start() {}

}